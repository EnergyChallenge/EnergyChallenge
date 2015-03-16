package de.unikiel.klik

import de.unikiel.klik.model.Institute
import de.unikiel.klik.model.Role
import de.unikiel.klik.model.User;
import org.apache.shiro.crypto.hash.Sha256Hash

class RankingController {

    def index() {
		users();
	}
	def users() {
		
		//TODO Test
		//TODO Dieser Test nur solange, wie TestService nicht läuft
		def userRole = new Role(name: "user")
		userRole.addToPermissions("*:*")
		userRole.save();
		Institute institute = new Institute(name: "none")
		institute.save()
		def exampleUser = new User(email:"user@example.com", passwordHash: new Sha256Hash("password").toHex(), firstName: "Max", lastName: "Mustermann", institute: institute)
		exampleUser.addToRoles(userRole)
		if(!exampleUser.save()) {
			println "saveFailed"
		}
		
		/*
		def user1 = new User(firstName: "Lennard", lastName: "Hammer");
		def user2 = new User(firstName: "Wolfgang", lastName: "Ramos");
		def user3 = new User(firstName: "Marco", lastName: "Osterloh");
		def user4 = new User(title: "Prof. Dr.", firstName: "Sören", lastName: "Henning");
		println user1.save(flush: true);
		user2.save(flush: true);
		user3.save(flush: true);
		user4.save(flush: true);
		*/
		
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
