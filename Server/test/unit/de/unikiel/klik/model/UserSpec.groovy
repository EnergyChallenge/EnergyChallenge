package de.unikiel.klik.model

import de.unikiel.klik.TestService
import grails.test.mixin.*
import org.apache.shiro.crypto.hash.Sha256Hash
import spock.lang.*

@Mock([User, Role, Profile, Institute, Activity, CompletedActivity])

class UserSpec extends Specification {
	
	def setup() { // executed before each feature method
		/*
		def userRole = new Role(name: "user")
		userRole.addToPermissions("*:*")
		userRole.save();
		Institute institute = new Institute(name: "none")
		institute.save()
		def user = new User(email:"user@example.com", passwordHash: new Sha256Hash("password").toHex(), firstName: "Max", lastName: "Mustermann", institute: institute)
		user.addToRoles(userRole)
		if(!user.save()) {
			println "saveFailed"
		}
		*/
		//def user = TestService.getExampleUser();
		//user.save(flush: true)
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
		setup: "create example user"
		def user = TestService.getExampleUser();
		
		expect: "example user should not be blocked"
		!user.getBlocked()
		!user.getEmailNotification()
	}
	
	@Ignore
	def "new  users start with zero points"() {
	}
	
	@Ignore
	def "completing an activity should add it to completed activities"() {
    }
	
	@Ignore
	def "completing an activty should increase the users points"() {
	}
	
}
