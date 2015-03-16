package de.unikiel.klik.model

// TODO avatar
//import java.awt.image.BufferedImage

abstract class Profile {
	
	byte[] avatar
	String avatarType
	boolean blocked 

	
    static constraints = {
		avatar(nullable:true, maxSize: 512*1024 /* 512K */)
		avatarType(nullable:true)
		//blocked(defaultValue: "false") // TODO test if false is the default value
    }
}
