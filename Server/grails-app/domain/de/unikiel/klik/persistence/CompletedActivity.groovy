package de.unikiel.klik.persistence

import org.joda.time.DateTime;

class CompletedActivity {

	DateTime dateCreated = new DateTime()
	Activity activity
	 
    static constraints = {
		dateCreated(nullable: false)
		activity(nullable: false)
		dateCreated(nullable: false, defaultValue: "DateTime.now()")
    }

    // not persisted fields
    static transients = ['points']
	
    static mapping = {
      dateCreated sqlType: "VARBINARY(300)"
    }

    def int getPoints() {
      return(this.activity.getPoints())
    }
}
