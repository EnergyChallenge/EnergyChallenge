package de.unikiel.klik.model

import org.joda.time.DateTime;

class CompletedActivity {

	DateTime dateCreated
	Activity activity
	 
    static constraints = {
		dateCreated(nullable: false)
		activity(nullable: false)
		//myDate defaultValue: "now()" // TODO make now() the default date
    }
	
}
