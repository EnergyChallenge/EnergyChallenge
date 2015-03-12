package de.unikiel.klik.model

class Team extends Profile{
	
	static hasMany = [members: KlikUser]
    static constraints = {
		members(nullable: false, minSize: 1)
    }
}
