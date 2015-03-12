package de.unikiel.klik

import de.unikiel.klik.model.ModelImpl;

class RankingController {

    def index() {
		if(params.view=="team") {
			render view: "index", model: [profils:ModelImpl.getInstance().getTeams(), tabletitle: "Teamname"]
		}else {
			render view: "index", model: [profils:ModelImpl.getInstance().getUsers(), tabletitle: "Username"]
		}
	}
	def profil() {
		
	}
}
