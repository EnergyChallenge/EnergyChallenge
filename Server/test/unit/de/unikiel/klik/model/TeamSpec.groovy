package de.unikiel.klik.model

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Team)
@Mock([User, Team])
class TeamSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "Teams must have at least one member"() {
		setup:
		def team = new Team(name: "team1")
		def user = new User(name: "user1")
		
		expect: "a team without members is invalid"
		!team.validate()
		
		and : "a team without members can not be saved"
		team.save(flush: true) == null
		team.hasErrors()
		
		when: "a user joins the team"
		team.addToMembers(user)
		
		then: "team is valid"
		team.validate()
		
		and: "team can be saved"
		team.save(flush: true) == team
    }
}
