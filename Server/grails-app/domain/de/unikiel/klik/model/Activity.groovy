package de.unikiel.klik.model


import org.joda.time.Duration

class Activity {

	String description
	int points
	Duration duration
	boolean visible = true
	
    static constraints = {
		description(nullable: false, blank: false)
		points(nullable: false, min: 1, max: 5)
		duration(nullable: false)
    }
	
	// TODO check db mapping
	//static mapping = {
	//	table 'activity'
	//	id column: 'id'
	//	points column: 'points'
	//}
}
