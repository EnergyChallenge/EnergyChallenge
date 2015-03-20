package de.unikiel.klik.model

import de.unikiel.klik.model.User
import de.unikiel.klik.model.Institute
import de.unikiel.klik.model.Role
import de.unikiel.klik.model.Proposal
import de.unikiel.klik.model.Comment

import de.unikiel.klik.TestService
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Proposal)
@Mock([User, Institute, Role,Proposal])
class ProposalSpec extends Specification {

	User user
    def setup() {
		user = TestService.getExampleUser("Author")
		user.save();
    }

    def cleanup() {
    }

    void "A Valid Proposal should save"() {
		when:
          Proposal proposal = new Proposal(description: "Something", rating: 1, author: user)
        then:
          proposal.validate()
    }
    void "A Proposal without a description should not be valid"(){
        when:
           Proposal proposal = new Proposal(rating: "1", author: user)
        then:
           !proposal.validate()
    }
    void "A Propsal withoud a rating should not be valid" (){
         when:
            Proposal proposal = new Proposal(rating: 1, author: user)
        then:
            !proposal.validate()
    }
    void "A Proposal without a Author should not be valid" (){
        when:
           Proposal proposal = new Proposal(description: "Something", rating: 1)
        then:
           !proposal.validate()
    }
    void "A Proposal should have a dateCreated" (){
        when:
           Proposal proposal = new Proposal(description: "Something", rating: "1", author: user)
        then:
           proposal.getDateCreated() != null
    }
}
