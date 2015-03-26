package de.unikiel.klik.persistence

import de.unikiel.klik.service.TestService
import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.validation.ValidationException

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Comment)
@Mock([Comment,Proposal,User,Role,Profile,Institute])
class CommentSpec extends Specification {
	
    def setup() {
    }

    def cleanup() {
		// clear database
		for(comment in Comment.list()) {
			comment.delete()
		}			
    }

    void "creating vaild Comment"() {
		when: "Creating one Comment"
		User user = TestService.getExampleUser()
		Proposal proposal = TestService.getExampleProposal()
		int before = Comment.count()
		Comment comment = new Comment(text: "Example",rating: 1, author: user, proposal: proposal)
		if(!comment.validate()) {
			comment.errors.allErrors.each{
				println it
			}
		}
		comment.save(flush: true);
		then: "There should be one comment more"
		Comment.count() == before + 1
		 
    }
	void "creating vaild Comment with only Text"() {
		when:
		User user = TestService.getExampleUser()
		Proposal proposal = TestService.getExampleProposal()
		int before = Comment.count()
		Comment comment = new Comment(text: "Example", author: user, proposal: proposal)
		if(!comment.validate()) {
			comment.errors.allErrors.each{
				println it
			}
		}
		comment.save(flush: true);
		
		then:
		Comment.count() == before + 1
	}
	void "creating vaild Comment with only rating"() {
		when:
		User user = TestService.getExampleUser()
		Proposal proposal = TestService.getExampleProposal()
		int before = Comment.count()
		Comment comment = new Comment(rating: 1, author: user, proposal: proposal)
		comment.save();
		
		then:
		Comment.count() == before + 1
	}
	void "Commenting on the same proposal twice should not be possible"() {
		when:
		User user = TestService.getExampleUser()
		user.save(flush: true)
		Proposal proposal = TestService.getExampleProposal()
		proposal.save(flush: true)
		Comment comment = new Comment(text: "Example",rating: 1, author: user, proposal: proposal)
		comment.save(flush: true);
		Comment secondComment = new Comment(text: "otherExample",rating: 1, author: user, proposal: proposal)
		secondComment.save(failOnError: true)
		
		then:
		thrown(ValidationException)
		!secondComment.validate()
		
	}
	void "Comment without anything should not save"() {
		when: "Comment wihout Parameters"
		Comment comment = new Comment();
		then: "Comment should not Save"
		!comment.save();
	}
	void "Comment without Author should not save"(){
		when: "Comment without User"
			Proposal proposal = TestService.getExampleProposal();
			Comment comment = new Comment(text: "Example", rating:1, proposal: proposal);
		then: "Comment should not save"
			!comment.save()
	}
	void "Comment without Proposal should not save"(){
		when: "Comment without Proposal"
		User user = TestService.getExampleUser();
		Comment comment = new Comment(text: "Example", rating:1, author: user);
	then: "Comment should not save"
		!comment.save()
	}
	void "Comment should be in the past"(){
		when: "Comment created"
		User user = TestService.getExampleUser();
		Proposal proposal = TestService.getExampleProposal();
		Comment comment = new Comment(text: "Example", rating:1, author: user, proposal: proposal);
		comment.save()
	then: "Comment should be in the past"
		comment.dateCreated.beforeNow
	}
}
