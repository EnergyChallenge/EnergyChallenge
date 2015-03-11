package de.unikiel.klik

import de.unikiel.klik.model.ModelImpl;

class RankingController {

    def index() {
		if(params.view=="team") {
			render view: "index", model: [profils:ModelImpl.getInstance().getTeams(), typeprefix: "Team"]
		}else {
			render view: "index", model: [profils:ModelImpl.getInstance().getUsers(), typeprefix: "User"]
		}
	}
	def profil() {
		
	}
}
