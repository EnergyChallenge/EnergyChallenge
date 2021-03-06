package de.unikiel.klik.control.web

import geb.Page

class GebActivityPage extends Page {
	
	//define relative location
	static url = "activity/index"
	static at = {title == "Aktivitšten-EnergyChallenge"}
	
	static content = {
		completeActivityButtons {$("g:actionSubmitImage", value: "Aktivitšt erledigen").findAll()}
		favoriseButtons {$("g:actionSubmitImage", value: "favorisieren").findAll()}
	}

}
