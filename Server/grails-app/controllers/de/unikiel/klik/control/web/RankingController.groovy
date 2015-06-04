package de.unikiel.klik.control.web

import de.unikiel.klik.persistence.Institute
import de.unikiel.klik.persistence.Role
import de.unikiel.klik.persistence.User;
import de.unikiel.klik.persistence.Team;
import de.unikiel.klik.persistence.Activity;
import org.apache.shiro.crypto.hash.Sha256Hash

class RankingController {
	static int PAGINATE_RANKINGS_MAX = 50;
	
	def index() {
		users();
	}
	
	def users() {
		render(view: "notAvailable")
		
		/*
		int offset = params.offset == null ? 0 : Integer.parseInt(params.offset);
		
		def ranking = [];
		for (user in User.findAll()) {
			ranking << [name: user.getName(), id: user.id, points: user.getPoints()];
		}
		ranking.sort { -it.points } //Sort DESC
		
		// add rank key
		for (int i = 0; i< ranking.size(); i++) {
			ranking[i]['rank'] = i + 1;
		}
		
		// keep index of last element within the bounds of the list
		int start = offset
		int end = offset + (PAGINATE_RANKINGS_MAX - 1) < (ranking.size()-1) ? (offset + PAGINATE_RANKINGS_MAX - 1) : (ranking.size() - 1);
		if(ranking.size() > 0) {
			ranking = ranking[start..end]
		}
		
		def model = [
			tableTitle: "Benutzer", 
			ranking: ranking, 
			action: "users", 
			count: User.count(),
			paginateMax: PAGINATE_RANKINGS_MAX];
		render(view: "index", model: model);
		*/
	}
	
	def teams() {
		render(view: "notAvailable")
		
		/*
		int offset = params.offset == null ? 0 : Integer.parseInt(params.offset);
	
		def ranking = [];
		for (team in Team.findAll()) {
			ranking << [name: team.name, id: team.id, points: team.getPoints()];
		}
		ranking.sort { -it.points }  //Sort DESC
		
		// add rank key
		for (int i = 0; i< ranking.size(); i++) {
			ranking[i]['rank'] = i + 1;
		}
		
		// keep index of last element within the bounds of the list
		int start = offset
		int end = offset + (PAGINATE_RANKINGS_MAX - 1) < (ranking.size()-1) ? (offset + PAGINATE_RANKINGS_MAX - 1) : (ranking.size() - 1);
		if(ranking.size() > 0) {
			ranking = ranking[start..end]
		}

		def model = [
			tableTitle: "Teams", 
			ranking: ranking, 
			action: "teams", 
			count: Team.count(),
			paginateMax: PAGINATE_RANKINGS_MAX];
		render(view: "index", model: model);
		*/
	}

	def notAvailable() {
		
	}
}
