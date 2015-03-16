package de.unikiel.klik.model

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Comment)
class CommentSpec extends Specification {

    def setup() {
		User proposalAuthor =  new User();
		Proposal proposal = new Proposal()
		proposal.save();
    }

    def cleanup() {
    }

    void "crating vaild Comment"() {
		
    }
}
