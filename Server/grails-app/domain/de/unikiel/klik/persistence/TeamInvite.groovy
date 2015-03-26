package de.unikiel.klik.persistence

class TeamInvite extends Message {

	User sender
	
    static constraints = {
		sender(nullable: false)
    }
	
}
