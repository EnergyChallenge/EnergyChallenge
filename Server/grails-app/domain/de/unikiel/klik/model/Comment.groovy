package de.unikiel.klik.model

import org.joda.time.DateTime

class Comment {

	String text
	Integer rating
	User author
	DateTime dateCreated = new DateTime()

	static belongsTo = [proposal: Proposal]
	
    static constraints = {
		text(nullable: true)
		rating(nullable: true, min:1, max:5) // TODO check size constraints
		proposal(nullable: false)
		author(nullable: false, unique: 'proposal')
		dateCreated(nullable: false, defaultValue: "DateTime.now()")
    }
}
