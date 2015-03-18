package de.unikiel.klik

import de.unikiel.klik.model.*
import grails.transaction.Transactional
import org.apache.shiro.crypto.hash.Sha256Hash
import org.joda.time.Duration

@Transactional
class TestService {

	static Activity getExampleActivity() {
		return getExampleActivity("Example")
	}
	static Activity getExampleActivity(String name) {
		return new Activity(description: name, points: 1, duration: new Duration(60*60*1000))
	}
	static Institute getExampleInstitute() {
		Institute institute
		if(Institute && Institute.findByName("Example")) {
			institute = Institute.findByName("Example")
		}else {
		institute = new Institute(name: "Example")
		}
		
		return institute
	}
	static Proposal getExampleProposal() {
		return getExampleProposal("Example")
	}
	static Proposal getExampleProposal(String name) {
		return new Proposal(description: name, points: 1, author: getExampleUser("ProposalAuthor"))
	}
	static Team getExampleTeam() {
		return getExampleTeam("Example")
	}
	static Team getExampleTeam(String name) {
		return new Team(name: name, members:[
			getExampleUser(name+"Member1"),
			getExampleUser(name+"Member2")
		])
	}
	static Role getExampleRole() {
		def role
		if(Role && Role.findByName("user")) {
			role = Role.findByName("user")
		}
		else {
			role = new Role(name: "user")
			role.addToPermissions("*:*")
		}

		return role
	}
	static User getExampleUser() {
		return getExampleUser("Example")
	}
	static User getExampleUser(String name) {
		return new User(email:name.toLowerCase()+"@example.com", passwordHash: new Sha256Hash("password").toHex(), roles: [getExampleRole()], firstName: name, lastName: "Example", institute: getExampleInstitute())
	}
	static void saveSomeExampleData(){
		getExampleInstitute().saveAll()
		getExampleUser("User1").saveAll()
		getExampleUser("User2").saveAll()
		getExampleTeam("Team1").saveAll()
		getExampleTeam("Team2").saveAll()
		getExampleTeam("Team3").saveAll()
		getExampleActivity("Activity1").saveAll()
		getExampleActivity("Activity2").saveAll()
		getExampleProposal("Proposal1").saveAll()
		getExampleProposal("Proposal2").saveAll()
	}
}