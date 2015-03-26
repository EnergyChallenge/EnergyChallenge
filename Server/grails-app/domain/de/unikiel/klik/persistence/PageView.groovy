package de.unikiel.klik.persistence

import org.joda.time.DateTime

class PageView {

    String url
    int views
    DateTime dateCreated = new DateTime()
	
    static constraints = {
		url(nullable: false, blank: false)
		views(nullable: false, min: 0, defaultValue: "0")
		dateCreated(nullable: false, defaultValue: "DateTime.now()")
    }
    
    static mapping = {
	dateCreated sqlType: "VARBINARY(300)"
    }
    
    public void inc(){
      this.views +=1
    }
}
