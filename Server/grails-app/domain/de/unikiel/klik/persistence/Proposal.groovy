package de.unikiel.klik.persistence

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
		points(nullable: false, min: 1, max: 5)
		author(nullable: false)
		comments(nullable:true)
		dateCreated(nullable: false, defaultValue: "DateTime.now()")
	}

	static mapping = {
	dateCreated column: "DATE_CREATED", sqlType: "VARBINARY(300)"
    }
	
	// TODO implement
	float getRating() {
		float rating = 0;
		for(Comment comment: comments){
			rating += comment.getRating()
		}
		if(rating != 0){
			return rating/comments.size()
		}else{
			return 0
		}
	}
	
}
