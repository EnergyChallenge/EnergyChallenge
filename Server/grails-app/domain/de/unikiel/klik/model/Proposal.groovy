package de.unikiel.klik.model

import org.joda.time.DateTime

class Proposal {

	String description
	int points
	DateTime dateCreated = new DateTime()
	User author
	static hasMany = [
		comments: Comment
	]
	
    static constraints = {
		description(nullable: false, blank: false)
		points(nullable: false, min: 1)
		author(nullable: false)
		comments(nullable:true)
		dateCreated(nullable: false, defaultValue: "DateTime.now()")
    }
	
	// TODO implement
	int getRating() {
		return(0)
	}
	
}
