package de.unikiel.klik

import de.unikiel.klik.model.*
import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import org.apache.shiro.crypto.hash.Sha256Hash
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(TestService)
@Mock([User, Role, Profile, Activity, Institute, Proposal, Team])
class TestServiceSpec extends Specification {

	
    def setup() {
		def TestService
    }

    def cleanup() {
    }

    void "save example Activity"() {
		when: "getExampleActivity"
		then: "Save Shoudl work"
			TestService.getExampleActivity().save()
    }
	void "save example Institude"() {
		when: "getExampleInstitude"
		then: "Save Shoudl work"
			TestService.getExampleInstitute().save()
	}
	void "save example Proposal"() {
		when: "getExampleProposal"
		then: "Save Shoudl work"
			TestService.getExampleProposal().save()
	}
	void "save example Role"(){
		when: "getExampleRole"
		then: "Save Shoudl work"
			TestService.getExampleRole().save()
	}
	
	void "save example Team"() {
		when: "getExampleTeam"
		then: "Save Shoudl work"
			TestService.getExampleTeam().save()
	}
	void "save 2 example Teams"() {
		when: "getExampleTeam"
		TestService.getExampleTeam("Team1").save()
		TestService.getExampleTeam("Team1").save()
		then: "Save Shoudl work"
		Team.count()==2
			
	}
	void "save example User"() {
		setup: "create example user"
		//def institute = TestService.getExampleInstitute()
		//institute.save(flush: true)
		//def userRole = new Role(name: "user")
		//userRole.addToPermissions("*:*")
		//userRole.save();
		//def user = new User(email:"example@example.com", passwordHash: new Sha256Hash("password").toHex(), roles: [userRole], firstName: "Example", lastName: "Example", institute: institute)
		def user = TestService.getExampleUser();
		if(!user.validate()) {
			user.errors.allErrors.each {
				println it
			}
		}
				
		expect: "user should be valid"
		user.validate()
		
		and: "savable"
		user.save(flush: true) != null
	}
	void "save 2example Users"() {
		when: "getExampleUser"
			TestService.getExampleUser("User1").save(flush: true)
			TestService.getExampleUser("User2").save(flush: true)
		then: "Save Shoudl work"
			User.count()==2
	}
	void "save Example Data"() {
		when: "getExampleUser"
			TestService.saveSomeExampleData()
		then: "Save Shoudl work"
			User.count()>=2
			Team.count()>=2
			Activity.count()>=2
			Proposal.count()>=2
	}
}
