package de.unikiel.klik

import de.unikiel.klik.model.ShiroUser;
import de.unikiel.klik.model.ShiroRole;
import org.apache.shiro.SecurityUtils
import org.apache.shiro.authc.AuthenticationException
import org.apache.shiro.authc.UsernamePasswordToken
import org.apache.shiro.crypto.SecureRandomNumberGenerator
import org.apache.shiro.crypto.hash.Sha256Hash
import org.apache.shiro.web.util.SavedRequest
import org.apache.shiro.web.util.WebUtils

class AuthController {
    def shiroSecurityManager

    def index = { redirect(action: "login", params: params) }

    def login = {
        return [ username: params.username, rememberMe: (params.rememberMe != null), targetUri: params.targetUri ]
    }

    def signIn = {
        def authToken = new UsernamePasswordToken(params.username, params.password as String)

        // Support for "remember me"
        if (params.rememberMe) {
            authToken.rememberMe = true
        }
        
        // If a controller redirected to this page, redirect back
        // to it. Otherwise redirect to the root URI.
        def targetUri = params.targetUri ?: "/"
        
        // Handle requests saved by Shiro filters.
        SavedRequest savedRequest = WebUtils.getSavedRequest(request)
        if (savedRequest) {
            targetUri = savedRequest.requestURI - request.contextPath
            if (savedRequest.queryString) targetUri = targetUri + '?' + savedRequest.queryString
        }
        
        try{
            // Perform the actual login. An AuthenticationException
            // will be thrown if the username is unrecognised or the
            // password is incorrect.
            SecurityUtils.subject.login(authToken)

            log.info "Redirecting to '${targetUri}'."
			//if (params.targetUri) {
			//	redirect(uri: targetUri)
			//}
			redirect(controller:"landing")
        }
        catch (AuthenticationException ex){
            // Authentication failed, so display the appropriate message
            // on the login page.
            log.info "Authentication failure for user '${params.username}'."
            flash.message = message(code: "login.failed")

            // Keep the username and "remember me" setting so that the
            // user doesn't have to enter them again.
            def m = [ username: params.username ]
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
        // Log the user out of the application.
        SecurityUtils.subject?.logout()
        webRequest.getCurrentRequest().session = null

        // For now, redirect back to the home page.
        redirect(uri: "/")
    }
	def register = {
		
	}
	def signUp = {
		// Check to see if the username already exists
		def user = ShiroUser.findByUsername(params.username)
		if (user) {
			flash.message = "User already exists with the username '${params.username}'"
			redirect(action:'register')
		}
		// User doesn't exist with username. Let's create one
		else {
			// Make sure the passwords match
			if (params.password != params.password2) {
				flash.message = "Passwords do not match"
				redirect(action:'register')
			}
			// Passwords match. Let's attempt to save the user
			else {
				// Create user
				def passwordSalt = new SecureRandomNumberGenerator().nextBytes().getBytes()
				def password=params.password
				user = new ShiroUser(username:params.username,passwordHash: new Sha256Hash(password).toHex(),passwordSalt:passwordSalt)
			   //TODO add Facultaet
				if (user.save()) {
					// Add USER role to new user
					def userRole =  ShrioRole.findByName('user') //TODO does not work
                    user.addToRoles(userRole)
					user.save()
					// Login user
					def authToken = new UsernamePasswordToken(user.username, password)
					SecurityUtils.subject.login(authToken)
					
					redirect(controller: 'landing', action: 'index')
				}
				else {
					//TODO login failed
				}
			}
		}
	}
    def unauthorized = {
        render "You do not have permission to access this page."
    }
}
