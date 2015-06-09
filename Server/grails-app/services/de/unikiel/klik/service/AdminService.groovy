package de.unikiel.klik.service

import de.unikiel.klik.persistence.*
import grails.transaction.Transactional
import org.joda.time.Duration;

@Transactional
class AdminService {

    def mailService

    void createActivity(String description, int points, Duration duration) {

        //Declare the activity and save it
        def newActivity = new Activity(description: description, points: points, duration: duration)
        newActivity.save(failOnError: true)
    }
	
	void createActivityFromProposal(String description, int points, Duration duration, long proposalId) {

        //Create the new activity from the proposal information
		def newActivity = new Activity(description: description, points: points, duration: duration)
        newActivity.save(failOnError: true)
		
		//reward the author of the proposal with points
		def proposal = Proposal.get(proposalId)
		def author = proposal.getAuthor()
		def activity = Activity.find{visible == false}
		def completedActivity = new CompletedActivity(activity: activity)
		author.completedActivities.add(completedActivity)
		completedActivity.save(flush: true, failOnError: true)
		author.save(flush: true, failOnError: true)
		author.calculatePoints()

        //Then delete the old proposal
        deleteProposal(proposalId)
	}

	void editActivity(String description, int points, Duration duration, long activityId) {

	    //Find the activity
        Activity modifiedActivity = Activity.get(activityId)

        //Set the attributes accordingly
        modifiedActivity.description = description
        modifiedActivity.points = points
        modifiedActivity.duration = duration
        modifiedActivity.save(failOnError: true)
	}

	void deleteActivity(long activityId) {

        //Find the activity
        //TODO references must be handled!!!
        Activity deletionActivity = Activity.get(activityId)
	if(!deletionActivity.visible){
		return
	}
		
		//delete the completed Activities of users
		def users = User.findAll {completedActivities != null}
		for(u in users) {
			u.completedActivities = u.completedActivities.findAll {it.activity.id != activityId}
			u.save(flush: true)
			u.calculatePoints();
		}
		
		//delete the favorites of users
		users = User.findAll {favorites != null}
		for(u in users) {
			u.favorites = u.favorites.findAll {it.id != activityId}
			u.save(flush: true)
		}
		
		//delete the completed activities
		def completedActivities = CompletedActivity.findAll {activity.id == activityId}
		for(ca in completedActivities) {
			ca.delete()
		}

        //Remove it once found
        deletionActivity.delete()
	}

	void deleteProposal(long proposalId) {

        //Find the proposal
        Proposal deletionProposal = Proposal.get(proposalId)

        //Remove it once found
        deletionProposal.delete()
    }

	void blockUser(long userId) {

		//Find the user
        User blockedUser = User.get(userId)

        //Block once found
        blockedUser.blocked = true
        blockedUser.save(failOnError: true)
	}

	void unblockUser(long userId) {

        //Find the user
        User blockedUser = User.get(userId)
	if(blockedUser.getRoles().contains(Role.findByName("admin"))){
		return
	}

        //Unblock once found
        blockedUser.blocked = false
        blockedUser.save(failOnError: true)
	}
	
	void deleteUser(long userId) {

        //Find the user
        User deletionUser = User.get(userId)
	if(deletionUser.getRoles().contains(Role.findByName("admin"))){
		return
	}
        //Remove once found
        deletionUser.delete()
	}

	void blockTeam(long teamId) {

        //Find the team
        Team blockedTeam = Team.get(teamId)

        //Block once found
        blockedTeam.blocked = true
        blockedTeam.save(failOnError: true)
	}

	void unblockTeam(long teamId) {

        //Find the team
        Team blockedTeam = Team.get(teamId)

        //Block once found
        blockedTeam.blocked = false
        blockedTeam.save(failOnError: true)
	}

	void deleteTeam(long teamId) {

        //Find the team
        Team deletionTeam = Team.get(teamId)
		//delete all references to the team
		for(member in deletionTeam.members) {
			member.setTeam(null)
			member.save(failOnError: true, flush: true)
		}

        //Remove once found
        deletionTeam.delete()
	}

    void sendGlobalEmail(String messageSubject, String message) {

		//To all users
		sendGlobalEmail(messageSubject, message, User.getAll().email);
    }
	
	void sendGlobalEmail(String messageSubject, String message, def receivers) {
		
		mailService.sendMail {
			async true
			from "admin@energy-challenge.uni-kiel.de"
			to "admin@energy-challenge.uni-kiel.de"
			bcc receivers
			subject messageSubject
			body message
		}
	
	}
	
	void sendSingleEmail(String receiver, String messageSubject, String message) {

		mailService.sendMail {
			async true
			from "admin@energy-challenge.uni-kiel.de"
			to receiver
			subject messageSubject
			body message
		}
	}
	
}
