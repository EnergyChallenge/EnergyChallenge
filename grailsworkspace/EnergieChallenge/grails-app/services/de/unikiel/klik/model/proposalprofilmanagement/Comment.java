package de.unikiel.klik.model.proposalprofilmanagement;

import java.util.Date;

import de.unikiel.klik.domain.User;

public class Comment {
	
	private User author;
	private String comment;
	private Date dateCreated;
	
	public User getAuthor() {
		return author;
	}
	public String getComment() {
		return comment;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	
	
}
