package de.unikiel.klik.model

import org.joda.time.DateTime

class Proposal {

	String description
	int points
	Date dateCreated // TODO use DateTime class
	User author
	
	static hasMany = [
		comments: Comment
	]
	
    static constraints = {
		description(nullable: false, blank: false)
		points(nullable: false, min: 1)
		//submissionTiem(nullable: false) // TODO s.o.
		author(nullable: false)
		comments(nullable:true)
    }
	
	// TODO implement
	int getRating() {
		return(0)
	}
	
}
