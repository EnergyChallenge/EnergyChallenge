package de.unikiel.klik.control.web

import de.unikiel.klik.persistence.Institute
import de.unikiel.klik.persistence.Role
import de.unikiel.klik.persistence.User;
import de.unikiel.klik.persistence.Team;
import de.unikiel.klik.persistence.Activity;
import org.apache.shiro.crypto.hash.Sha256Hash

class RankingController {
	
    def index() {
		users();
	}
	
	def users() {
		int offset = 0;
		int max = 50;
	
		if(params.offset != null) offset = Integer.parseInt(params.offset);
		if(params.max != null) max = Integer.parseInt(params.max);
		
		/* experimenting with criteria
		def c = User.createCriteria()
		def ranking = c.list (max: max, offset: offset) {
			order("id", "desc") // sorting by points is not possible as long points are not persisted to the DB
		}
		*/
		
		def ranking =  [];
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
		int end = offset + (max - 1) < (ranking.size()-1) ? (offset + max - 1) : (ranking.size() - 1);
		
		def model = [
			tableTitle: "Benutzer", 
			ranking: ranking[start..end], 
			action: "users", 
			count: User.count()];
		render(view: "index", model: model);
	}
	
	def teams() {
		int offset = 0;
		int max = 50;
	
		if(params.offset != null) offset = Integer.parseInt(params.offset);
		if(params.max != null) max = Integer.parseInt(params.max);
	
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
		int end = offset + (max - 1) < (ranking.size()-1) ? (offset + max - 1) : (ranking.size() - 1);
		
		def model = [
			tableTitle: "Teams", 
			ranking: ranking[start..end], 
			action: "teams", 
			count: Team.count()];
		render(view: "index", model: model);
	}

}
