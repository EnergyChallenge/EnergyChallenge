package de.unikiel.klik

import de.unikiel.klik.model.*

class SearchController {

	static defaultAction = "displaySearchResults"
	
    def displaySearchResults() {
		def results = User.findAllBySearchNameIlike("%" + params.query + "%")
		
		render(view: "displaySearchResults", model: [query: params.query, results: results])
    }
}
