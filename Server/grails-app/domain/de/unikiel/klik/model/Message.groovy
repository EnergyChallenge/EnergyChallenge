package de.unikiel.klik.model

abstract class Message {

	static belongsTo = [receiver: User]
	
    static constraints = {
		receiver(nullable: false)
    }
}
