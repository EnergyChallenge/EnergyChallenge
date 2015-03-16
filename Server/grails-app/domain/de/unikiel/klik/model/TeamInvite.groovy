package de.unikiel.klik.model

class TeamInvite extends Message {

	User sender
	
    static constraints = {
		sender(nullable: false)
    }
	
}
