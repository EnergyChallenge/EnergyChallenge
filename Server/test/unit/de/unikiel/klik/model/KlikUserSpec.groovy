package de.unikiel.klik.model

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
//import grails.test.mixin.domain.DomainClassUnitTestMixin
import spock.lang.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */

@Mock([KlikUser, Activity, CompletedActivity])
class KlikUserSpec extends Specification {

    def setupSpec() { // executed before the first feature method
		// create fixture for all feature methods
    }

	def setup() { // executed before each feature method
		def user1 = new KlikUser(name: "User1")
		user1.save(flush: true)
	}
    
	def "completing an activity should add it to completed activities and increase the users points"() {
		setup: "create a user and an activity"
		// create fixture for THIS test
		def testUser = new KlikUser(name: "Wolfgang")
		testUser.save(flush: true)
		def activity = new Activity(title: "Fahrradfahren", points: 3)
		
		expect: "new users have no points"
		testUser.getPoints() == 0
		
		when: "user completes activity once"
		testUser.completeActivityNow(activity)
		
		then: "activity is added to set of completed activities"
		testUser.getCompletedActivities().size() == 1
		testUser.getCompletedActivities().toArray(new CompletedActivity[0])[0].getActivity() == activity
		
		and: "activity points are added to the users points"
		testUser.getPoints() == activity.getPoints()
		
		when: "activity is completed twice"
		testUser.completeActivityNow(activity)
		
		then: "users points should double"
		testUser.getPoints() == 2*activity.getPoints()
    }
	
	def "test setupSpec"() {
		expect:
		KlikUser.list().size() == 1
		KlikUser.findByName("User1").getName() == "User1"
	} 
}
