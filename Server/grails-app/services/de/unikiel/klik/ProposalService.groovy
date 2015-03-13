package de.unikiel.klik

import grails.transaction.Transactional
import org.apache.shiro.subject.Subject

@Transactional
class ProposalService {

    void addComment( String commentText, int rating, Subject author, long proposalID ) {

    }
	def addProposal(String description, int points, Subject author) {
		
	}
	
}
