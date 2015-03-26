package de.unikiel.klik.control.web

import de.unikiel.klik.persistence.Proposal
import grails.validation.ValidationException
import org.apache.shiro.subject.Subject
import org.apache.shiro.SecurityUtils

class ProposalController {

	def shiroSecurityManager
	def proposalService = new ProposalService()
	def index() {
		int page
		if(params.page) {
			page = params.page as int
		}else {
			page = 1
		}
		def proposals = Proposal.findAll("from Proposal as p order by p.dateCreated", [max: 10, offset: 10*(page-1)])
		int lastPage = Proposal.count()/10;
		[proposals: proposals, page: page, lastPage: lastPage]
	}
	/**
	 * Adds Proposal
	 * @return
	 */
	def add() {
		Subject subject = SecurityUtils.subject;
		try {
			proposalService.addProposal(params.description as String, params.points as int, subject)
		}catch(ValidationException e) {
			
			flash.message = message(e.getMessage())
		}
		redirect (action : "index");
	}
	def view() {
		if (params.id) {
			Proposal proposal = Proposal.get(params.id)
			def comments = proposal.comments.sort {it.dateCreated};
			comments.reverse(true)

			return [proposal: proposal, comments: comments, id: params.id]
		} else {
			redirect (action : index)
		}
	}
	def addComment() {
		try {
			proposalService.addComment(params.text as String, params.rating as int, SecurityUtils.getSubject(), params.id as long)
		}catch(ValidationException e) {
			flash.default = "Ein Fehler ist aufgetreten. Bitte versuchen sie es erneut oder kontaktieren sie den Admin" 
		}
		redirect (action: "view", params: params);
	}
}
