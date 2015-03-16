package de.unikiel.klik.model

// TODO avatar
//import java.awt.image.BufferedImage

class Profile {
	
	byte[] avatar
	String avatarType
	
    static constraints = {
		avatar(nullable:true, maxSize: 512*1024 /* 512K */)
		avatarType(nullable:true)
    }
}
