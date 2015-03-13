package de.unikiel.klik

import grails.transaction.Transactional
import org.apache.shiro.subject.Subject

@Transactional
class AuthService {

    void logIn(String email, String password) {

    }
	void logOut (Subject subject) {
		
	}
	void register (String email, String password, String password2,String institute) {
		
	}
}
