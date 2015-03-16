package de.unikiel.klik.model


import org.joda.time.Duration

class Activity {

	String title;
	int points;
	
	// TODO implement Duration property
	Duration duration
	
	
    static constraints = {
		title nullable: false
		points nullable: false
    }
	
	
}
