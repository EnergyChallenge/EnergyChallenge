package de.unikiel.klik.model

import de.unikiel.klik.TestService
import grails.test.mixin.*
import org.apache.shiro.crypto.hash.Sha256Hash
import spock.lang.*

@Mock([User, Role, Profile, Institute, Activity, CompletedActivity])

class UserSpec extends Specification {
	User user
	def setup() { // executed before each feature methoe
		user = TestService.getExampleUser();
		user.save(flush: true)
	}
	
	def cleanup(){ // executed after each feature
//		for(user in User.list()) {
//			user.delete()
//		}
//		for(activity in Activity.list()) {
//			activity.delete()
//		}
	}
    
	def "blocked and emailNotification default to false"() {
		expect: "example user should not be blocked"
		!user.getBlocked()
		!user.getEmailNotification()
	}
	

	def "new  users start with zero points"() {
        expect: "user should start with 0 points"
           user.getPoints() == 0
	}

	def "completing an activity should add it to completed activities"() {
        setup:
            int completedActivitiesBefore = user.getCompletedActivities();
        when: "completing and Acitivity"
            user.completeActivityNow(TestService.getExampleActivity())
        then: "user has one more completed activity"
            completedActivitiesBefore + 1 == user.getCompletedActivities();
            user.validate()
    }

	def "completing an activty should increase the users points"() {
        setup:
        int pointsBefore = user.getPoints();
        when: "completing and Acitivity"
        user.completeActivityNow(TestService.getExampleActivity())
        then: "user has one more completed activity"
        pointsBefore + TestService.getExampleActivity().getPoints() == user.getPoints();
        user.validate()
	}
	
}
