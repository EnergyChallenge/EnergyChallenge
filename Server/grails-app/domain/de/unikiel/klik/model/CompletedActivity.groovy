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
	
	// TODO create point property for completed activities; check db mapping
	//static mapping = {
	//		table 'completed_activity'
	//		activity column: 'activity_id'
	//		points formula: "(SELECT points FROM Activity a WHERE a.id = activity_id"
	//}
}
