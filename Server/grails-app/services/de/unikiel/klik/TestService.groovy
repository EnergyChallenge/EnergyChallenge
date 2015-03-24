package de.unikiel.klik

import de.unikiel.klik.model.*
import grails.transaction.Transactional
import org.apache.shiro.crypto.hash.Sha256Hash
import org.joda.time.Duration
import org.joda.time.DateTime
import java.util.Random

@Transactional
class TestService {

	static Random rand = new Random()
	
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
		Team team =  new Team(name: name)
		team.addToMembers(getExampleUser(name+"Member1"))
		team.addToMembers(getExampleUser(name+"Member2"))
                return team
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
		getExampleInstitute().save()
		getExampleUser("User1").save()
		getExampleUser("User2").save()
		getExampleTeam("Team1").save()
		getExampleTeam("Team2").save()
		getExampleTeam("Team3").save()
		getExampleActivity("Activity1").save()
		getExampleActivity("Activity2").save()
		//TODO Example Proposal!	
	}
	
	// needs predefined institutes
	static void createAndSaveExampleUsersForExistingInstitutes(int nrOfUsers) {
		def user_role = Role.findOrSaveByName("user")
		int nrOfInstitutes = Institute.count()
		
		for(int i=1; i <= nrOfUsers; i++) {
			new User(email:"benutzer" + i +"@example.com", 
				passwordHash: new Sha256Hash("password").toHex(), 
				roles: [user_role], 
				firstName: "Benutzer" + i, 
				lastName: "Example", 
				institute: Institute.get(rand.nextInt(nrOfInstitutes) + 1)	// get a random Institute
				).save(flush: true)
		}
	}
	
	// needs predefined users and activities
	static void createAndSaveCompletedActivitiesForExistingUsersAndActivities(int nrOfCompletedActivities) {
		int nrOfUsers = User.count()
		int nrOfActivities = Activity.count()
		//User user
		//Activity activity
		//CompletedActivity completedActivity
		def users = User.findAll()
		DateTime today = new DateTime()
		for(int i=1; i <= nrOfCompletedActivities; i++) {
			//random Date in the passt week
			DateTime date = today.minus(new Duration(((long)rand.nextInt(7*24))*60L*60L*1000L))
			def user = users[rand.nextInt(nrOfUsers)]		// get a random User
			def activity = Activity.get(rand.nextInt(nrOfActivities) + 1)	// get a random Activity
			def completedActivity = new CompletedActivity(activity: activity)
			completedActivity.save(flush: true, failOnError: true)
			completedActivity.dateCreated = date
			completedActivity.save(flush: true, failOnError: true)
			user.addToCompletedActivities(completedActivity)
			user.save(flush: true, failOnError: true)
		}
	}
	static void createSomePageVisits(int numberOfDaysBack, String url){
		DateTime today = new DateTime()
		for (long i = 0; i < numberOfDaysBack; i++){
			DateTime date = today.minus(new Duration(i*24L*60L*60L*1000L))
			PageView pageView =  new PageView(url: url)
			pageView.save(flush: true, failOnError: true)
			pageView.dateCreated = date
			pageView.save(flush: true, failOnError: true)
			pageView.views = rand.nextInt(50)
			pageView.save(flush: true, failOnError: true)
		}
	}
}
