package de.unikiel.klik

import de.unikiel.klik.model.User
import de.unikiel.klik.model.Institute
import de.unikiel.klik.model.Role

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
		if(!User.findByEmail(email).blocked){
		  def authToken = new UsernamePasswordToken(email, password)
		  authToken.rememberMe = rememberMe
		  SecurityUtils.subject.login(authToken)
		}else{
		  //TODO tell that user is blocked
		  throw new AuthenticationException()
		}
    }
    void logout (Subject subject) {
		// Log the user out of the application.
		subject.logout()
		//webRequest.getCurrentRequest().session = null
    }
    void register (String email, String firstName, String lastName, String password, String password2, long instituteId) throws Exception, AuthenticationException {
        // Check to see if the username already exists
        def user = User.findByEmail(email)
        if (user) {
            //flash.message = "A user is already registered under the email '${params.email}'"
		throw new Exception();
        }else{
            // Check the passwords match
            if (password != password2) {
                //flash.message = "Passwords do not match"
		throw new Exception();
            }else{
                    //Try to actually create the user
                    def newUser = new User(email: email, passwordHash: new Sha256Hash(password).toHex(),
                                           institute: Institute.get(instituteId), firstName: firstName,
                                           lastName: lastName, roles: [Role.findByName("user")])
                    newUser.save(failOnError: true)
                login(email, password, false)
            }
        }
   }
   void requestPasswordChange(String email) {
	//TODO
	throw new UnsupportedOperationException();
   }
}
