package de.unikiel.klik.control.web

import de.unikiel.klik.persistence.*

class SearchController {

	static defaultAction = "displaySearchResults"
	
    def displaySearchResults() {
    
		def results = [];
		def matchingUsers = User.findAllBySearchNameIlike("%" + params.query + "%")
		for (user in matchingUsers) {
			results << [id: user.getId(), name: user.getName(), type: "user", points: user.getPoints()];
		}
		def matchingTeams = Team.findAllByNameIlike("%" + params.query + "%")
		for (team in matchingTeams) {
			results << [id: team.getId(), name: team.getName(), type: "team", points: team.getPoints()];
		}
		results.sort { -it.points } //Sort DESC
		
		render(view: "displaySearchResults", model: [query: params.query, results: results])
    }
}
