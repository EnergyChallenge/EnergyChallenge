package de.unikiel.klik

import de.unikiel.klik.model.*
import grails.transaction.Transactional
import org.apache.shiro.crypto.hash.Sha256Hash
import org.joda.time.Duration

@Transactional
class TestService {

	static Activity getExampleActivity() {
		return new Activity(decription: "Example", points: "1", duration: new Duration(60*60*1000))
	}
	static Institute getExampleInstitute() {
		return new Institute(name: "Example")
	}
	static Proposal getExampleProposal() {
		return new Proposal(description: "Example", points: 1, author: getExampleUser("ProposalAuthor"))
	}
	static Team getExampleTeam() {
		return getExampleTeam("Example")
	}
	static Team getExampleTeam(String name) {
		return new Team(name: name, members:[getExampleUser(name+"Member1"),getExampleUser(name+"Member2")])
	}
    static User getExampleUser() {
		return getExampleUser("Example")
    }
	static User getExampleUser(String name) {
		return new User(email:name.toLowerCase()+"@example.com", passwordHash: new Sha256Hash("password").toHex(), firstName: name, lastName: "Example", institude: getExampleInstitute())
	}
}
