package de.unikiel.klik.model

import org.joda.time.DateTime

class Proposal {

	String descripion
	int points
	DateTime submissionTime // TODO make "now()" default value
	User author
	
	static hasMany = [
		comments: Comment
	]
	
    static constraints = {
		description(nullable: false, blank: false)
		points(nullable: false, min: 1)
		submissionTiem(nullable: false) // TODO make "now()" default value
		author(nullable: false)
		comments(nullable:true)
    }
}
