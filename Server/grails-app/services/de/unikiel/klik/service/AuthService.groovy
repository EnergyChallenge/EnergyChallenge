package de.unikiel.klik.service

import de.unikiel.klik.persistence.User
import de.unikiel.klik.persistence.Institute
import de.unikiel.klik.persistence.Role

import grails.transaction.Transactional
import grails.validation.ValidationException

import org.apache.shiro.SecurityUtils
import org.apache.shiro.authc.AuthenticationException
import org.apache.shiro.authc.UsernamePasswordToken
import org.apache.shiro.crypto.hash.Sha256Hash
import org.apache.shiro.subject.Subject

import java.security.SecureRandom

@Transactional
class AuthService {

	def shiroSecurityManager
	def mailService
	private SecureRandom random = new SecureRandom() 
    void login(String email, String password, boolean rememberMe) throws AuthenticationException {
		User user = User.findByEmail(email);
		if(user == null){
			throw new AuthenticationException()
		}else if(!user.blocked){
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
                                           lastName: lastName, roles: [Role.findByName("benutzer")])
                    newUser.save(flush: true, failOnError: true)
                login(email, password, false)
            }
        }
   }
   void requestPasswordChange(String email) throws ValidationException {
	//TODO
  	User user = User.findByEmail(email)
	if(user != null){
		String requestToken = new BigInteger(100,random).toString(32)
		user.setPasswordRequestToken(requestToken)
		user.save(flush: true, failOnError: true)
		
		//TODO
		//ATTENZIONE!
		//URL is hard coded: http://www.energy-challenge.uni-kiel.de/energy-challenge
		
		mailService.sendMail {
			async true
			from "admin@energy-challenge.uni-kiel.de"
			to email
			subject "EnergyChallenge: Ihr neues Passwort"
			body "Hallo,\n\nSie haben uns mitgeteilt, dass Sie Ihr Passwort vergessen haben. Über den folgenden Link können Sie sich ein neues erstellen:\nhttp://www.energy-challenge.uni-kiel.de/energy-challenge/auth/forgotPassword?token=" + requestToken;
			
		}
	}else{
		throw new ValidationException()
	}
   }
   private String getPasswordRequestMessage(String requestToken){
	String message = ""+"../auth/forgotPassword?token="+requestToken+" " //TODO
        return message
   }
   void changePassword(String requestToken,String email, String password, String password2) throws ValidationException{
        User user = User.findByPasswordRequestToken(requestToken)
        User userByEmail = User.findByEmail(email)
        if (user != null && user == userByEmail){
            if (password == password2){
                user.passwordHash = new Sha256Hash(password).toHex()
                user.save(failOnError: true)
                login(email,password,false)
            }else{
                //throw new ValidationException()
            }
       }else{
            //throw new ValidationException()
       }
   }
}
