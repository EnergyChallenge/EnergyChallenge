package de.unikiel.klik.control.web

import grails.converters.JSON
import grails.validation.ValidationException

import de.unikiel.klik.persistence.User;
import de.unikiel.klik.persistence.Role;
import de.unikiel.klik.persistence.Institute;

import de.unikiel.klik.service.AuthService
import de.unikiel.klik.service.PageViewService

import org.apache.shiro.SecurityUtils
import org.apache.shiro.authc.AuthenticationException
import org.apache.shiro.authc.UsernamePasswordToken
import org.apache.shiro.crypto.SecureRandomNumberGenerator
import org.apache.shiro.crypto.hash.Sha256Hash
import org.apache.shiro.web.util.SavedRequest
import org.apache.shiro.web.util.WebUtils

class AuthController {
    def shiroSecurityManager
	def AuthService
    def index = { redirect(action: "login", params: params) }

    def login = {
		if (params.id != "27032014") {
			response.sendError(404)
		}
        return [ username: params.username, rememberMe: (params.rememberMe != null), targetUri: params.targetUri ]
    }

    def signIn = {
	PageViewService.visitPage("/auth/signIn")
        def targetUri = params.targetUri ?: "/"
        
        // Handle requests saved by Shiro filters.
        SavedRequest savedRequest = WebUtils.getSavedRequest(request)
        if (savedRequest) {
            targetUri = savedRequest.requestURI - request.contextPath
            if (savedRequest.queryString) targetUri = targetUri + '?' + savedRequest.queryString
        }
        
        try{
			AuthService.login(params.email, params.password as String ,params.rememberMe as boolean)
			redirect(controller: 'landing', action: 'index')
        }
        catch (AuthenticationException ex){
            //flash.message = "Der Login ist falsch oder geblockt"
            flash.error = "Der Login ist falsch oder geblockt!"

            // Keep the username and "remember me" setting so that the
            // user doesn't have to enter them again.
            def m = [ email: params.email ]
            if (params.rememberMe) {
                m["rememberMe"] = true
            }

            // Remember the target URI too.
            if (params.targetUri) {
                m["targetUri"] = params.targetUri
            }

            // Now redirect back to the login page.
            //redirect(action: "login", params: m)
			redirect(uri: "/")
        }
    }

    def signOut = {
        AuthService.logout(SecurityUtils.subject)
        // For now, redirect back to the home page.
        redirect(uri: "/")
    }
	def register = {
		if (params.id != "27032014") {
			response.sendError(404)
		}
		[institutes: Institute.findAll()]		
	}
	def signUp = {
		//Check if the parameters are valid for user creation
        //TODO convert these messages to German
        def user = User.findByEmail(params.email)
        if(user){
            flash.message = "That email is already in use."
            def m = [ email: params.email, firstName: params.firstName, lastName: params.lastName, instituteId: params.institudeId ]
            forward (action: "register", params: m)

        }else if(params.firstName.length() > 25){
            flash.message = "First names are limited to 25 characters."
            def m = [ email: params.email, firstName: params.firstName, lastName: params.lastName, instituteId: params.institudeId ]
            forward (action: "register", params: m)

        }else if(params.lastName.length() > 25){
            flash.message = "Last names are limited to 25 characters."
            def m = [ email: params.email, firstName: params.firstName, lastName: params.lastName, instituteId: params.institudeId ]
            forward (action: "register", params: m)

        }else if(params.password != params.password2){
            flash.message = "The passwords don't match."
            def m = [ email: params.email, firstName: params.firstName, lastName: params.lastName, instituteId: params.institudeId ]
            forward (action: "register", params: m)

        }else if(params.password.length() == 0 || params.password2.length() == 0){
            flash.message = "You must enter a password."
            def m = [ email: params.email, firstName: params.firstName, lastName: params.lastName, instituteId: params.institudeId ]
            forward (action: "register", params: m)

        }else{
            //Create a new user and login
            try {
                AuthService.register(params.email, params.firstName, params.lastName, params.password as String, params.password2 as String, params.instituteId as long)
                redirect (controller: "landing", action: "index")
            }
            catch (AuthenticationException e){
                // Authentication failed, so display the appropriate message
                flash.message = "Login schlug fehl."
            }catch(Exception e){
                flash.message = "Registrierung schlug fehl."
                //Keep params
                def m = [ email: params.email, firstName: params.firstName, lastName: params.lastName, instituteId: params.institudeId ]
                forward (action: "register", params: m)
            }
        }
	}
	def forgotPassword = {
		
	}
	def requestPassword = {
		try {
			AuthService.requestPasswordChange(params.email)
			flash.message = "Email wurde versand"
			redirect(controller: "startpage", action: "index")
		}catch (Exception e) {
			def m = [ email: params.email]
			flash.message = "Fehler"+e.getMessage()
			redirect(action: "forgotPassword", model: m)
		}
		
	}
	def changePassword = {
		try {
			AuthService.changePassword(params.token, params.email, params.password, params.password2)
			redirect(controller: "landing", action: "index")
		}catch(ValidationException e){
			redirect(action: "forgotPassword", params: params)
		}
	}
    def unauthorized = {
        render "You do not have permission to access this page."
    }

    def appLogin() {

        //Get credentials
        def email = params.email
        def password = params.password

        //Check the credentials
        try{
            AuthService.login(email, password ,false)
            //Login successful
            def response = [response: "true"]
            render response as JSON
        }
        catch (AuthenticationException ex){
            //Login failed
            def response = [response: "false"]
            render response as JSON
        }
    }
}
