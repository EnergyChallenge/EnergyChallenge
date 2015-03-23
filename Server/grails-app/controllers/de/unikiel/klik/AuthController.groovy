package de.unikiel.klik

import grails.converters.JSON
import de.unikiel.klik.model.User;
import de.unikiel.klik.model.Role;
import de.unikiel.klik.model.Institute;
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
        return [ username: params.username, rememberMe: (params.rememberMe != null), targetUri: params.targetUri ]
    }

    def signIn = {
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
            flash.message = message(code: "login.failed")

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
		[institutes: Institute.findAll()]		
	}
	def signUp = {
//		try {
			AuthService.register(params.email, params.firstName, params.lastName, params.password as String, params.password2 as String, params.instituteId as long)
			forward (controller: "landing", action: "index")
//		}
//                catch (AuthenticationException e){
//                    // Authentication failed, so display the appropriate message
//                    flash.message = "Registration failed."
//                }catch(Exception e){
//            		flash.message = message(code: "login.failed")
//			//Keep params
//			def m = [ email: params.email, firstName: params.firstName, lastName: params.lastName, instituteId: params.institudeId ]
//			forward (action: "register", params: m)
//		}
	}
	def forgotPassword = {
		
	}
	def requestPassword = {
		try {
			AuthService.requestPasswordChange(params.email)
		}catch (Exception e) {
			def m = [ email: params.email]
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