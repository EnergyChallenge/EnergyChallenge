package de.unikiel.klik.model

import org.joda.time.DateTime

class Comment {

	String text
	int rating
	User author
	DateTime dateCreated

	static belongsTo = [proposal: Proposal]
	
    static constraints = {
		text(nullable: true)
		rating(nullable: true, min:1, max:5) // TODO check size constraints
		proposal(nullable: false)
		author(nullable: false)
		proposal(unique: 'author')
		dateCreated(nullable: false)
    }
}
