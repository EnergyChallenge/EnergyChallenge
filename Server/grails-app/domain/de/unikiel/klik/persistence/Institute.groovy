package de.unikiel.klik.persistence

class Institute {

	String name
	
    static constraints = {
		name(nullable: false, blank: false, unique: true)
    }
}
