package de.unikiel.klik.model

class Team extends Profile{
	
	String name
	
	static hasMany = [members: User]
	
    static constraints = {
		members(nullable: false, minSize: 1)
    }
}
