package de.unikiel.klik.persistence

abstract class Profile {
	
	byte[] avatar
	String avatarType
	Boolean blocked = false 

	static constraints = {
		avatar(nullable:true, maxSize: 512*1024 /* 512K */)
		avatarType(nullable:true)
		blocked(defaultValue: "false")
    }
}
