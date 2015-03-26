package de.unikiel.klik.control.web

import geb.spock.GebSpec

class ProposalControllerSpec extends GebSpec {

    def setup() {
      to GebProposalPage
    }

    def cleanup() {
    }

    void "Adding a new Proposal"() {
      expect: "being at Proposal"
        at GebProposalPage
      when: "creating a new Propsal"
        //TODO
      then: "Stating on page"
        at GebProposalPage
      and: "a new Proposal is added"
        //TODO
    }
    void "Adding a proposal without a desciption should not do anything"(){
      expect: "being at ProposalPage"
        at GebProposalPage
      when: "klicking 'einreichen' "
        //TODO
      then: "stay at the Page"
        at GebProposalPage
    }
    void "Getting to a Proposal view"(){
      given: "a Proposal"
        //TODO
      expect: "being at Proposal and seeing that proposal"
        //TODO
      when: "Kliking on that proposal"
        //TODO
      then: "Being a /proposal/view/ID"
        //TODO
      and: "seing the Description of the klicked Proposal"
        //TODO
    }
    void "Commenting on a Proposal"(){
      given: "visiting a Proposal"
        //TODO
      expect: "seeing that Proposal"
        //TODO
      when: "commenting"
        //TODO
      then: "comment sould appere"
        //TODO
      and: "Staing on that Page"
        //TODO
      when: "commenting again"
        //TODO
      then: "the new comment should appere"
        //TODO
      and: "The old comment should be gone"
        //TODO
    }
}
