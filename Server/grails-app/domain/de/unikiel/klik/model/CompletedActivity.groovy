package de.unikiel.klik.model

import org.joda.time.DateTime;

class CompletedActivity {

	DateTime dateCreated = new DateTime()
	Activity activity
	 
    static constraints = {
		dateCreated(nullable: false)
		activity(nullable: false)
		dateCreated(nullable: false, defaultValue: "DateTime.now()")
    }
	
}
