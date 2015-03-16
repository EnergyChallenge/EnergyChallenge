package de.unikiel.klik

import de.unikiel.klik.model.User
import de.unikiel.klik.model.Proposal
import de.unikiel.klik.model.Comment
import grails.transaction.Transactional

import org.apache.shiro.subject.Subject

@Transactional
class ProposalService {

    void addComment( String commentText, int rating, Subject author, long proposalId ) {
		User user = User.findByEmail(author.getPrincipal());
		Proposal proposal =  Proposal.get(proposalId);
		Comment comment = new Comment(text: commentText, rating: rating, author: user);
		propsal.addToComments(comment); //TODO Review
		propsal.save();
		
    }
	def addProposal(String description, int points, Subject author) {
		User user = User.findByEmail(author.getPrincipal());
		Proposal proposal = new Proposal(description: description, points: points, author: user);
		propsal.save();
	}
	
}
