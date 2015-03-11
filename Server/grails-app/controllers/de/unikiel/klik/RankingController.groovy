package de.unikiel.klik

import de.unikiel.klik.model.ModelImpl;

class RankingController {

    def index() {
		if(params.view=="team") {
			[profils:ModelImpl.getInstance().getTeams()]
		}else {
			[profils:ModelImpl.getInstance().getUsers()]
		}
	}
}
