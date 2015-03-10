package de.unikiel.klik

import de.unikiel.klik.model.AuthorisationImpl;
import de.unikiel.klik.model.FacultyNotFoundException
import de.unikiel.klik.model.InvalidPasswordException
import de.unikiel.klik.model.InvalidUserNameException
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
    
	def login = {}

    def signIn = {
        // Handle requests saved by Shiro filters.
        SavedRequest savedRequest = WebUtils.getSavedRequest(request)
        if (savedRequest) {
            targetUri = savedRequest.requestURI - request.contextPath
            if (savedRequest.queryString) targetUri = targetUri + '?' + savedRequest.queryString
        }
        // Actualy log in
        try{
            AuthorisationImpl.getInstance().login(params.username, params.password as String , params.rememberMe as boolean)
			if (params.targetUri) {
				log.info "Redirecting to '${targetUri}'."
				redirect(uri: targetUri)
			}
			redirect(controller:"landing")
        }
        catch (AuthenticationException ex){
            log.info "Authentication failure for user '${params.username}'."
            flash.message = message(code: "login.failed")

            def m = [ username: params.username ]
            if (params.rememberMe) {
                m["rememberMe"] = true
            }
			if (params.targetUri) {
                m["targetUri"] = params.targetUri
            }
			redirect(uri: "/")
        }
    }

    def signOut = {
        AuthorisationImpl.getInstance().logout();
        redirect(uri: "/")
    }
	def register = {}
	def signUp = {
		try 
		{
			AuthorisationImpl.getInstance().register(params.username,params.password, params.password2, params.faculty);
			redirect(controller: 'landing', action: 'index')
		}
		catch(InvalidUserNameException e) 
		{
			flash.message = "Failed: invalid Username"
		}
		catch(InvalidPasswordException e)
		{
			flash.message = "Failed Invalid Password"
		}
		catch(FacultyNotFoundException e)
		{
			flash.message = "Failed: Faculty not found"
		}catch(IOException e)
		{
			flash.message = "Failed to save User"
		}
		redirect(action:'register', params:params)
	}
    def unauthorized = {
        render "You do not have permission to access this page."
    }
}
