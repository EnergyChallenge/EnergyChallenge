package de.unikiel.klik.persistence

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
	
}
