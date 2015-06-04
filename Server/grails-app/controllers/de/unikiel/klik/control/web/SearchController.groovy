package de.unikiel.klik.control.web

import de.unikiel.klik.persistence.*
import de.unikiel.klik.service.RankingService;

class SearchController {
	def RankingService;
	static defaultAction = "displaySearchResults"
	
    def displaySearchResults() {
		
		def results = [];
		if(params.query != null && params.query != "") {
			params.query=params.query.replaceAll(/\*/,"_")
			def matchingUsers = User.findAllBySearchNameIlike("%" + params.query + "%")
			for (user in matchingUsers) {
				results << [id: user.getId(), 
								name: user.getName(), 
								type: "user" /*, WEGEN SPEICHERPROBLEMEN
								points: user.getPoints(),
								rankingPosition: RankingService.getPositionOfUser(user) */
							];
			}
			def matchingTeams = Team.findAllByNameIlike("%" + params.query + "%")
			for (team in matchingTeams) {
				results << [id: team.getId(),
								name: team.getName(),
								type: "team" /*, WEGEN SPEICHERPROBLEMEN
								points: team.getPoints(),
								rankingPosition: RankingService.getPositionOfTeam(team) */
							];
			}
			//results.sort { -it.points } //Sort DESC
		}
		render(view: "displaySearchResults", model: [query: params.query, results: results])
    }
}
