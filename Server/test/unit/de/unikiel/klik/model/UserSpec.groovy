package de.unikiel.klik.model

import grails.test.mixin.*
import spock.lang.*

@Mock([User, Activity, CompletedActivity])
class UserSpec extends Specification {
	
	def setup() { // executed before each feature method
		def user = new User(firstName: "User1")
		user.save(flush: true)
		def activity = new Activity(title: "Fahrradfahren", points: 3)
		activity.save(flush: true)
	}
	
	def cleanup(){ // executed after each feature
		for(user in KlikUser.list()) {
			user.delete()
		}
		for(activity in Activity.list()) {
			activity.delete()
		}
	}
    
	def "new  users start with zero points"() {
		expect: "new users have zero points"
		KlikUser.findByFirstName("User1").getPoints() == 0
	}
	
	def "completing an activity should add it to completed activities"() {
		setup: "create a user and an activity"
		def user = KlikUser.findByFirstName("User1")
		def activity = Activity.findByTitle("Fahrradfahren")
		
		when: "user completes activity once"
		user.completeActivityNow(activity)
		def iter = user.getCompletedActivities().iterator()
		
		then: "activity is added to completed activities"
		user.getCompletedActivities().size() == 1
		iter.next().getActivity() == activity
    }
	
	def "completing an activty should increase the users points"() {
		setup:
		def activity = Activity.findByTitle("Fahrradfahren")
		def user = KlikUser.findByFirstName("User1")
		
		when: "activity is completed once"
		user.completeActivityNow(activity)
		
		then: "users points increase"
		user.getPoints() == activity.getPoints()
		
		when: "activity is completed twice"
		user.completeActivityNow(activity)
		
		then: "users points should double"
		user.getPoints() == 2*activity.getPoints()
	}
	
	// TODO delete completed activities of the user when he is deleted
}
