package de.unikiel.klik.model

class Team extends Profile{
	
	static hasMany = [members: KlikUser]
    static constraints = {
    }
}
