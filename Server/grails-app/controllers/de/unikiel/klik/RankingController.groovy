package de.unikiel.klik

import de.unikiel.klik.model.Institute
import de.unikiel.klik.model.Role
import de.unikiel.klik.model.User;
import de.unikiel.klik.model.Team;
import org.apache.shiro.crypto.hash.Sha256Hash

class RankingController {

    def index() {
		users();
	}
	def users() {
		
		def ranking =  [];
		for (user in User.findAll()) {
			ranking << [name: user.getName(), id: user.id, points: user.getPoints()];
		}
		ranking.sort { -it.points } //Sort DESC
		def model = [tableTitle: "Benutzer", ranking: ranking, action: "users"];
		showRanking(model);
	}
	def teams() {
		def ranking = [];
		for (team in Team.findAll()) {
			ranking << [name: team.name, id: team.id, points: team.getPoints()];
		}
		ranking.sort { -it.points }  //Sort DESC
		def model = [tableTitle: "Teams", ranking: ranking, action: "teams"];
		showRanking(model);
	}
	private def showRanking(def model) {
		render(view: "index", model: model);
	}

}
