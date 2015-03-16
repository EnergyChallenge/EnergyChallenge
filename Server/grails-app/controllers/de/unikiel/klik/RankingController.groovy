package de.unikiel.klik

import de.unikiel.klik.model.User;

class RankingController {

    def index() {
		users();
	}
	def users() {
		
		def user1 = new User(firstName: "Lennard", lastName: "Hammer");
		def user2 = new User(firstName: "Wolfgang", lastName: "Ramos");
		def user3 = new User(firstName: "Marco", lastName: "Osterloh");
		def user4 = new User(title: "Prof. Dr.", firstName: "Sören", lastName: "Henning");
		println user1.save(flush: true);
		user2.save(flush: true);
		user3.save(flush: true);
		user4.save(flush: true);
		
		def ranking =  [];
		println(User.findAll());
		println "Alle Benutzer";
		for (user in User.findAll()) {
			println "Benutzer1";
			println user;
			println(user);
			ranking << [name: user.getName(), points: user.getPoints()];
		}
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

}
