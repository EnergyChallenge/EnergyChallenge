package de.unikiel.klik

import grails.transaction.Transactional
import org.apache.shiro.subject.Subject

@Transactional
class UserService {

    void setName(String title, String lastName, String firstName, Subject subject) {

    }
	void setPassword(String password, String password2, Subject subject) {
		
	}
	void setAvatar(def avatar, Subject subject) {
		
	}
	void setInstitute(long institutedId, Subject subject) {
		
	}
	void setTeam(long teamId, Subject subject) {
		
	}
	void setEmailNotification(boolean emailNotification, Subject subject) {
		
	}
	void createTeamAndJoin(String name, Subject subject) {
		
	}
	
	
}
