package de.unikiel.klik

import grails.transaction.Transactional
import org.apache.shiro.SecurityUtils
import org.apache.shiro.authc.AuthenticationException
import org.apache.shiro.authc.UsernamePasswordToken
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
	void register (String email, String password, String password2,String institute) {
		
	}
}
