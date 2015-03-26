package de.unikiel.klik.persistence

import org.joda.time.DateTime

abstract class Message {

	DateTime dateCreated = new DateTime()
	static belongsTo = [receiver: User]
	
    static constraints = {
		receiver(nullable: false)
		dateCreated(nullable: false, defaultValue: "DateTime.now()")
    }
    
    static mapping = {
	dateCreated column: "DATE_CREATED", sqlType: "VARBINARY(300)"
    }
}
