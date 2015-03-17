package de.unikiel.klik

import de.unikiel.klik.model.User
import de.unikiel.klik.model.Proposal
import de.unikiel.klik.model.Comment
import grails.transaction.Transactional
import grails.validation.ValidationException

import org.apache.shiro.SecurityUtils
import org.apache.shiro.subject.Subject
import org.joda.time.DateTime

@Transactional
class ProposalService {

    void addComment( String commentText, int rating, Subject author, long proposalId ) throws ValidationException {
		User user = User.findByEmail(author.getPrincipal()); 
		Proposal proposal =  Proposal.get(proposalId);
		Comment comment = new Comment(text: commentText, rating: rating, author: user);
		proposal.addToComments(comment); //TODO Review
		proposal.save(failOnError: true);
		
    }
    void addProposal(String description, int points, Subject author) throws ValidationException {
	User user = User.findByEmail(author.getPrincipal());
	Proposal proposal = new Proposal(description: description, points: points, author: user);
	proposal.save(failOnError: true);
    }
	
}
