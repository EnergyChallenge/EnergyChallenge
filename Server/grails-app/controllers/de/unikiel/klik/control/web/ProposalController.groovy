package de.unikiel.klik.control.web

import de.unikiel.klik.persistence.Proposal
import de.unikiel.klik.persistence.User
import de.unikiel.klik.persistence.Comment

import de.unikiel.klik.service.ProposalService

import org.codehaus.groovy.runtime.typehandling.GroovyCastException
import grails.validation.ValidationException
import org.apache.shiro.subject.Subject
import org.apache.shiro.SecurityUtils
import org.h2.jdbc.JdbcSQLException

class ProposalController {

	// maximum number of results to display on one page
	static int PAGINATE_COMMENTS_MAX = 30;
	static int PAGINATE_PROPOSALS_MAX = 20;
	
	def shiroSecurityManager
	def proposalService = new ProposalService()
	def index() {
		int offset = params.offset == null ? 0 : Integer.parseInt(params.offset);

		def c = Proposal.createCriteria()
		def proposals = c.list(max: PAGINATE_PROPOSALS_MAX, offset: offset) {
			order("dateCreated", "desc")
		}
	
		[proposals: proposals, count: proposals.totalCount, paginateMax: PAGINATE_PROPOSALS_MAX]
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
			
			flash.error = "Ein Vorschlag muss zwischen 1 und 255 Zeichen lang sein! Bitte versuchen Sie es erneut."
		}
		redirect (action : "index");
	}
	def view() {
		if (params.id) {
			int offset = params.offset == null ? 0 : Integer.parseInt(params.offset);
			
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
			
			// paginate
			// keep index of last element within the bounds of the list
			int start = offset
			int end = offset + (PAGINATE_COMMENTS_MAX - 1) < (comments.size() - 1) ? (offset + PAGINATE_COMMENTS_MAX - 1) : (comments.size() - 1);
			int totalSize = comments.size()
			if(comments.size() > 0) {
				comments = comments[start..end]
			}
			
			return [proposal: proposal,
				comments: comments,
				ownComment: ownComment,
				id: params.id,
				count: totalSize,
				paginateMax: PAGINATE_COMMENTS_MAX]
			
		} else {
			redirect (action: "index")
		}
	}
	def addComment() {
		try {
			proposalService.addComment(params.text as String, params.rating as int, SecurityUtils.getSubject(), params.id as long)
		}catch(ValidationException e) {
			flash.default = "Ein Fehler ist aufgetreten. Bitte versuchen Sie es erneut oder kontaktieren Sie einen Administrator." 
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
