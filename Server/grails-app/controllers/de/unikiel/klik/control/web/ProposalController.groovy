package de.unikiel.klik.control.web

import de.unikiel.klik.persistence.Proposal
import de.unikiel.klik.persistence.User

import de.unikiel.klik.service.ProposalService

import org.codehaus.groovy.runtime.typehandling.GroovyCastException
import grails.validation.ValidationException
import org.apache.shiro.subject.Subject
import org.apache.shiro.SecurityUtils
import org.h2.jdbc.JdbcSQLException

class ProposalController {

	def shiroSecurityManager
	def proposalService = new ProposalService()
	def index() {
		int offset = 0;
		int max = 50;
	
		if(params.offset != null) offset = Integer.parseInt(params.offset);
		if(params.max != null) max = Integer.parseInt(params.max);

		def c = Proposal.createCriteria()
		def proposals = c.list(max: max, offset: offset) {
			order("dateCreated", "desc")
		}
	
		//def proposals = Proposal.findAll("from Proposal as p order by p.dateCreated", [max: max, offset: offset])
		[proposals: proposals, count: proposals.totalCount]
	}
	/**
	 * Adds Proposal
	 * @return
	 */
	def add() {
		Subject subject = SecurityUtils.subject;
		try {
			proposalService.addProposal(params.description as String, params.points as int, subject)
		}catch(Exception e) {
			
			flash.error = "Ein Vorschlag muss zwischen 1 und 255 Zeichen lang sein! Bitte versuche es erneut."
		}
		redirect (action : "index");
	}
	def view() {
		if (params.id) {
			Proposal proposal = Proposal.get(params.id)
			def comments = proposal.comments.sort {it.dateCreated};
			comments.reverse(true)

			def ownComment = null;
			User user = User.findByEmail(org.apache.shiro.SecurityUtils.getSubject().getPrincipal());
			for (comment in comments) {
				if (comment.author == user) {
					ownComment = comment;
					break;	
				}
			}
			
			return [proposal: proposal, comments: comments, ownComment: ownComment, id: params.id]
		} else {
			redirect (action : index)
		}
	}
	def addComment() {
		try {
			proposalService.addComment(params.text as String, params.rating as int, SecurityUtils.getSubject(), params.id as long)
		}catch(ValidationException e) {
			flash.default = "Ein Fehler ist aufgetreten. Bitte versuchen sie es erneut oder kontaktieren sie den Admin" 
		}catch(GroovyCastException e) {
			flash.error = "Es muss mindestens eine Bewertung abgegeben werden."
		}
		redirect (action: "view", params: params);
	}
   def nullPointerException(final NullPointerException exception){
                log.error("Exception occured. ${exception?.message}", exception)
                flash.error = "Vorschlag ist nicht verfügbar!"
                redirect(action: "index")
        }

	
}
