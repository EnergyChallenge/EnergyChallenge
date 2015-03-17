package de.unikiel.klik

import de.unikiel.klik.model.User
import grails.transaction.Transactional
import org.apache.shiro.SecurityUtils
import org.apache.shiro.authc.AuthenticationException
import org.apache.shiro.authc.UsernamePasswordToken
import org.apache.shiro.crypto.hash.Sha256Hash
import org.apache.shiro.subject.Subject

@Transactional
class AuthService {

	def shiroSecurityManager
	
    void login(String email, String password, boolean rememberMe) throws AuthenticationException {
		def authToken = new UsernamePasswordToken(email, password)
		authToken.rememberMe = rememberMe
		SecurityUtils.subject.login(authToken)
    }
	void logout (Subject subject) {
		// Log the user out of the application.
		subject.logout()
		//webRequest.getCurrentRequest().session = null
	}
	void register (String email, String firstName, String lastName, String password, String password2, String institute) {
        // Check to see if the username already exists
        def user = User.findByEmail(params.email)
        if (user) {
            flash.message = "A user is already registered under the email '${params.email}'"
            redirect(action:'register')
        }else{
            // Check the passwords match
            if (params.password != params.password2) {
                flash.message = "Passwords do not match"
                redirect(action:'register')
            }else{
                try{
                    //Try to actually create the user

                    def newUser = new User(email: params.email, passwordHash: new Sha256Hash(params.password).toHex(),
                                           institute: params.institute, firstName: params.firstName,
                                           lastName: params.lastName, roles: Role.findByName("user"))
                    newUser.save()

                    // Now go to the login page.
                    redirect (action = "login")
                }
                catch (AuthenticationException ex){
                    // Authentication failed, so display the appropriate message
                    // on the login page.
                    log.info "Authentication failure for user '${params.username}'."
                    flash.message = "Registration failed."

                    // Now redirect back to the register page.
                    redirect (action = "register")
                }
            }
        }
		throw new UnsupportedOperationException();
	}
	void requestPasswordChange(String email) {
		//TODO
		throw new UnsupportedOperationException();
	}
}
