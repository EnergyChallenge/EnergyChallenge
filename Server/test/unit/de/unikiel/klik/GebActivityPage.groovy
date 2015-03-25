package de.unikiel.klik

import geb.Page

class GebActivityPage extends Page {
	
	//define relative location
	static url = "activity/index"
	static at = {title == "Aktivit�ten-EnergyChallenge"}
	
	static content = {
		completeActivityButtons {$("g:actionSubmitImage", value: "Aktivit�t erledigen").findAll()}
		favoriseButtons {$("g:actionSubmitImage", value: "favorisieren").findAll()}
	}

}
