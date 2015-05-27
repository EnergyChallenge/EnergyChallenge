package de.unikiel.klik.service

import de.unikiel.klik.persistence.User
import de.unikiel.klik.persistence.Role
import de.unikiel.klik.persistence.Proposal
import de.unikiel.klik.persistence.Comment
import grails.transaction.Transactional
import grails.validation.ValidationException

import org.apache.shiro.SecurityUtils
import org.apache.shiro.subject.Subject
import org.joda.time.DateTime

@Transactional
class ProposalService {

	def mailService
	
    void addComment( String commentText, int rating, Subject author, long proposalId ) throws ValidationException {
		User user = User.findByEmail(author.getPrincipal()); 
		Proposal proposal =  Proposal.get(proposalId);
		Comment comment = new Comment(text: commentText, rating: rating, author: user);
		if(proposal != null){
			Comment oldComment = proposal.comments.find{it.author == user}
			if(oldComment == null){
				proposal.addToComments(comment); //TODO Review
				proposal.save(failOnError: true);
			}else{
		
				proposal.removeFromComments(oldComment)
				try{
					proposal.addToComments(comment)
					proposal.save(failOnError:true)
				}catch(ValidationException ex){
					flash.message = "Etwas ist schief gelaufen!"
					proposal = Proposal.get(proposalId)
					proposal.addToComments(oldComment)
					proposal.save()
				}
			}
		}
    }
	void addProposal(String description, int points, Subject author) throws ValidationException {
		User user = User.findByEmail(author.getPrincipal());
		//TODO this part does not work!!!!!!!
		Proposal newproposal = new Proposal(description: description, points: points, author: user);
		newproposal.save(failOnError: true);
		
		//Create E-Mail for Admins
		def admins = User.createCriteria().list{
			roles{
				eq('name', 'admin')
			}
		}
		def i
		for (i = 0; i < admins.size; i++) {
			mailService.sendMail {
				async true
				from "admin@energy-challenge.uni-kiel.de"
				to admins[i].email
				subject "EnergyChallenge: Neuer Energiesparvorschlag"
				body "Es wurde ein neuer Vorschlag erstellt:\n\nBeschreibung: " + description + "\nPunkte: " + points + "\n\n Zum Vorschlag: http://www.energy-challenge.uni-kiel.de/energy-challenge/admin/proposals"
			}
		}
	}
	
}
