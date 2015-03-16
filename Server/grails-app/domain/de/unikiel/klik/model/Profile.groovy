package de.unikiel.klik.model

// TODO avatar
//import java.awt.image.BufferedImage

class Profile {

	String name
	
	byte[] avatar
	String avatarType
	
    static constraints = {
		name(nullable: false, blank: false)
		avatar(nullable:true, maxSize: 512*1024 /* 512K */)
		avatarType(nullable:true)
    }
}
