package de.unikiel.klik.model

// TODO avatar
//import java.awt.image.BufferedImage

abstract class Profile {
	
	protected byte[] avatar
	protected String avatarType
	protected Boolean blocked = false
	
    static constraints = {
		avatar(nullable:true, maxSize: 512*1024 /* 512K */)
		avatarType(nullable:true)
		blocked(defaultValue: "false") // TODO test if false is the default value
    }
}
