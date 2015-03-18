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
		
		//TestService.saveSomeExampleData();
		
		
		def ranking =  [];
			//TODO TEMP Some default users
			ranking << [name: "Prof. Dr. Sebastian Vettel", id: 9991, points: 123];
			ranking << [name: "Dipl.-Ing. Lewis Hamilton", id: 9993, points: 75];
			ranking << [name: "Fernando Alonso", id: 9992, points: 100];
		for (user in User.findAll()) {
			ranking << [name: user.getName(), id: user.id, points: user.getPoints()];
		}
		ranking.sort { -it.points } //Sort DESC
		def model = [tableTitle: "Benutzer", ranking: ranking, action: "users"];
		showRanking(model);
	}
	def teams() {
		def ranking = [];
			//TODO TEMP Some default team
			ranking << [name: "McLaren F1 Team", id: 991, points: 100];
			ranking << [name: "Scuderia Ferrari", id: 990, points: 150];
			ranking << [name: "Mercedes Formel 1", id: 992, points: 120];
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
