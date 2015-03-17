package de.unikiel.klik

import grails.transaction.Transactional
import org.apache.shiro.subject.Subject

@Transactional
class ActivityService {

    void completeActivity(long activityId, Subject subject) {

    }
	void addToFavorites(long activityId, Subject subject) {
		
	}
	void removeFromFavorites(long activityId, Subject subject) {
		
	}
}
