package de.unikiel.klik.model.proposalmangagement

import org.apache.shiro.subject.Subject

interface IProposal {
	public String getName();
	public String getDescription();
	public int getDurationInMinutes();
	public int getRating();
	public void rate(Subject currentUser);
	public void comment(String comment, Subject currentUser);
	//TODO getComments();
}
