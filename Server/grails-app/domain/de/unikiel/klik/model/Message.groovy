package de.unikiel.klik.model

abstract class Message {

	static protected belongsTo = [receiver: User]
	
    static constraints = {
		receiver(nullable: false)
    }
}
