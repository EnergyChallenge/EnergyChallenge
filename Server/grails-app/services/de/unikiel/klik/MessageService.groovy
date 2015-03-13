package de.unikiel.klik

import grails.transaction.Transactional
import org.apache.shiro.subject.Subject

@Transactional
class MessageService {

    void inviteUserToTeam(long recieverId, Subject subject) {

    }
	void remindUser(long reciverId) {
		
	}
	void remindUser(long reciverId, long activityId) {
		
	}
}
