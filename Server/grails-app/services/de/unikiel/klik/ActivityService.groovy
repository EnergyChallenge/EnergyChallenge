package de.unikiel.klik

import grails.transaction.Transactional
import org.apache.shiro.subject.Subject

@Transactional
class ActivityService {

    void completeActivity(long activityId, Subject subject) {

    }
	void addToFavorits(long activityId, Subject subject) {
		
	}
	void removeFromFavorits(long activityId, Subject subject) {
		
	}
}
