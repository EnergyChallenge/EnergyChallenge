package de.unikiel.klik

import de.unikiel.klik.model.Proposal

class ProposalController {

	def ProposalService
    def index() {
		def proposals = Proposal.findAll("from Proposal as p order by p.dateCreated", [max: 10, offset: 10*params.page])
		int lastPage = Proposal.count()/10;
		[proposals: proposals, page: params.page, lastPage: lastPage]
	}
	/**
	 * Adds Proposal
	 * @return
	 */
	def add() {
		PropsalService.addProposal(params.description, params.points, SecurityUtils.subject)
		redirect (action = "index");
	}
	def view() {
		Proposal proposal = Proposal.get(params.id)
		def comments = proposal.comments;
		
		[proposal: proposal, comments: comments]
	}
	def addComment() {
		redirect(action = "view", params = "")
	}
}
