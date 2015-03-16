package de.unikiel.klik.model

class Comment {

	String text
	int rating
	User author

	static belongsTo = [proposal: Proposal]
	
    static constraints = {
		text(nullable: true)
		rating(nullable: true, min:1, max:5) // TODO check size constraints
		proposal(nullable: false)
		author(nullable: false)
		proposal(unique: 'author')
    }
}
