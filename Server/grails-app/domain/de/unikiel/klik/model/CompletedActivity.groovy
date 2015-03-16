package de.unikiel.klik.model

import org.joda.time.DateTime;

class CompletedActivity {

	DateTime date
	Activity activity
	 
    static constraints = {
		date(nullable: false)
		activity(nullable: false)
		//myDate defaultValue: "now()" // TODO make now() the default date
    }
	
}
