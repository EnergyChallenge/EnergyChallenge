package de.unikiel.klik.model

import grails.test.mixin.*
import org.apache.shiro.crypto.hash.Sha256Hash
import spock.lang.*

@Mock([User, Profile, Institute, Activity, CompletedActivity])

class UserSpec extends Specification {
	
	def setup() { // executed before each feature method
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
	}
	
	def cleanup(){ // executed after each feature
//		for(user in User.list()) {
//			user.delete()
//		}
//		for(activity in Activity.list()) {
//			activity.delete()
//		}
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
