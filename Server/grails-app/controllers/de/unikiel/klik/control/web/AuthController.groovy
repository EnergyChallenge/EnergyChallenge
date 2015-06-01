package de.unikiel.klik.control.web

import grails.converters.JSON
import grails.validation.ValidationException

import de.unikiel.klik.persistence.User;
import de.unikiel.klik.persistence.Role;
import de.unikiel.klik.persistence.Institute;

import de.unikiel.klik.service.AuthService
import de.unikiel.klik.service.PageViewService
import de.unikiel.klik.service.AdminService

import org.apache.shiro.SecurityUtils
import org.apache.shiro.authc.AuthenticationException
import org.apache.shiro.authc.UsernamePasswordToken
import org.apache.shiro.crypto.SecureRandomNumberGenerator
import org.apache.shiro.crypto.hash.Sha256Hash
import org.apache.shiro.web.util.SavedRequest
import org.apache.shiro.web.util.WebUtils
import org.joda.time.DateTime

class AuthController {
    def shiroSecurityManager
	def AuthService
	def AdminService
    def index = { redirect(action: "login", params: params) }
	
	
	DateTime ENERGYCHALLENGE_START_TIME = new DateTime(2015, 06, 01, 0, 0, 0, 0); //1.6.
	DateTime ENERGYCHALLENGE_END_TIME = new DateTime(2015, 07, 01, 0, 0, 0, 0); //1.7.
	DateTime ENERGYCHALLENGE_REG_START_TIME = new DateTime(2015, 05, 18, 0, 0, 0, 0); //18.5.

    def login = {
		if (params.id != "27032014" && (ENERGYCHALLENGE_START_TIME.isAfterNow() || ENERGYCHALLENGE_END_TIME.isBeforeNow())) {
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
		if (params.id != "27032014" && (ENERGYCHALLENGE_REG_START_TIME.isAfterNow() || ENERGYCHALLENGE_END_TIME.isBeforeNow())) {
			response.sendError(404)
		}

		// alphabetically sort institutes
		def c = Institute.createCriteria();
		def institutes = c.list {
			order("name", "asc")
		}
		[institutes: institutes]		
	}
	def signUp = {
		//Check if the parameters are valid for user creation
        def user = User.findByEmail(params.email)
        if(user){
            flash.message = "Diese E-Mail-Adresse wird bereits verwendet."
            def m = [ email: params.email, firstName: params.firstName, lastName: params.lastName, instituteId: params.institudeId, id: params.id ]
           	forward (action: "register", params: m)
			   
        }else if(params.firstName.length() > 25){
            flash.message = "Vornamen dürfen nicht länger als 25 Zeichen sein."
            def m = [ email: params.email, firstName: params.firstName, lastName: params.lastName, instituteId: params.institudeId, id: params.id ]
            forward (action: "register", params: m)

        }else if(params.lastName.length() > 25){
            flash.message = "Nachnamen dürfen nicht länger als 25 Zeichen."
            def m = [ email: params.email, firstName: params.firstName, lastName: params.lastName, instituteId: params.institudeId, id: params.id ]
            forward (action: "register", params: m)

        }else if(params.password != params.password2){
            flash.message = "Die eingegebenen Passwörter stimmen nicht überein."
            def m = [ email: params.email, firstName: params.firstName, lastName: params.lastName, instituteId: params.institudeId, id: params.id ]
            forward (action: "register", params: m)

        }else if(params.password.length() == 0 || params.password2.length() == 0){
            flash.message = "Bitte geben Sie ein Passwort ein."
            def m = [ email: params.email, firstName: params.firstName, lastName: params.lastName, instituteId: params.institudeId, id: params.id ]
            forward (action: "register", params: m)

        } else if(params.agreeRules != "1"){
            flash.message = "Bitte akzeptieren Sie das Regelwerk."
            def m = [ email: params.email, firstName: params.firstName, lastName: params.lastName, instituteId: params.institudeId, id: params.id ]
            forward (action: "register", params: m)

        } else{
            //Create a new user and login
            try {
                AuthService.register(params.email, params.firstName, params.lastName, params.password as String, params.password2 as String, params.instituteId as long)
				AdminService.sendSingleEmail(params.email as String,
					"Registrierung EnergyChallenge",
					"Lieber EnergyChallenge-Teilnehmer,\nliebe EnergyChallenge-Teilnehmerin,\n\nvielen Dank, für Ihre Anmeldung bei der EnergyChallenge. Sie haben mit Ihrer Teilnahme allen Spielregeln des Regelwerks zugestimmt.\n Ab dem 1. Juni können Sie sich unter www.energy-challenge.uni-kiel.de wieder einloggen und Punkte sammeln.\n\n Wir freuen uns über Ihre Teilnahme und wünschen viel Spaß\n Nora Nording und Sebastian Starzynski")
				
				if (params.id != "27032014" && (ENERGYCHALLENGE_START_TIME.isAfterNow() || ENERGYCHALLENGE_END_TIME.isBeforeNow())) {
					flash.message = "Vielen Dank für Ihre Registrierung. Ab dem 1. Juni können sich Sie sich anmelden und teilnehmen."
					redirect (controller: "startpage", action: "index", params: [regSuccessBeforeStart: 1])
				} else {
					redirect (controller: "landing", action: "index")
				}
				
            }
            catch (AuthenticationException e){
                // Authentication failed, so display the appropriate message
                flash.message = "Login schlug fehl."
            }catch(Exception e){
				println e
                flash.message = "Die Registrierung schlug fehl."
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
			flash.message = "Sie erhalten eine E-Mail, um sich ein neues Passwort zu erstellen."
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
