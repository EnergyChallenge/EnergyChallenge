package de.unikiel.klik

import geb.Page

class GebLandingPage extends Page{
	
	//define relative location
	static url = "landing/index"
	static at = {title == "Home-EnergyChallenge"}
	
	static content = {
		activityLink {$("href")}
	}
	
	void followActivityLink() {
		activityLink.click()
	}
}
