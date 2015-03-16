package de.unikiel.klik

import de.unikiel.klik.model.*
import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(TestService)
@Mock([User, Activity, Institute, Proposal, Team, User])
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
		when: "getExampleUser"
		then: "Save Shoudl work"
			TestService.getExampleUser().save()
	}
	void "save 2example Users"() {
		when: "getExampleUser"
			TestService.getExampleUser("User1").save()
			TestService.getExampleUser("User2").save()
		then: "Save Shoudl work"
			User.count()==2
	}
}
