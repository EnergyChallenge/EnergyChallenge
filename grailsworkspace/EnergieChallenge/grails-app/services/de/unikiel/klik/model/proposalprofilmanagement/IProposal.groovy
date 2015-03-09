package de.unikiel.klik.model.proposalprofilmanagement

interface IProposal {
	String getTitle();
	int getRating();
	Comment[] getComments();
}
