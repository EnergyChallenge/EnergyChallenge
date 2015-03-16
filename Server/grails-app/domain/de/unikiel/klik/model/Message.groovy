package de.unikiel.klik.model

import org.joda.time.DateTime

abstract class Message {

	DateTime dateCreated
	static belongsTo = [receiver: User]
	
    static constraints = {
		receiver(nullable: false)
		dateCreated(nullable: false)
    }
}
