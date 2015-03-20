package de.unikiel.klik.model

class Institute {

	String name
	
    static constraints = {
		name(nullable: false, blank: false, unique: true)
    }
}
