package de.unikiel.klik.domain

class Proposal {

	String title;
	static belongsTo = [author:User];
    static constraints = {
		
    }
}
