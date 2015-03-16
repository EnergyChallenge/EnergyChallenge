package de.unikiel.klik


class RankingController {

    def index() {
		users();
	}
	def users() {
		def ranking;
		def model = [tableTitle: "Benutzer", ranking: ranking, action: "users"];
		showRanking(model);
	}
	def teams() {
		def ranking;
		def model = [tableTitle: "Teams", ranking: ranking, action: "teams"];
		showRanking(model);
	}
	private def showRanking(def model) {
		render(view: "index", model: model);
	}
	
	/*
	 * if(params.view=="team") {
			//render view: "index", model: [profils:ModelImpl.getInstance().getTeams(), tabletitle: "Teamname"]
		}else {
			//render view: "index", model: [profils:ModelImpl.getInstance().getUsers(), tabletitle: "Username"]
		}
	*/

}
