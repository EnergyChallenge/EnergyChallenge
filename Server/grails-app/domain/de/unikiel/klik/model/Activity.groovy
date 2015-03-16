package de.unikiel.klik.model

// TODO implement duration
//import java.time.*

import org.joda.time.Duration

class Activity {

	String description
	int points
	Duration duration
	
    static constraints = {
		title(nullable: false, blank: false)
		points(nullable: false, min: 1)
		duration(nullable: false)
    }
	
	
}
