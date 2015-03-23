package de.unikiel.klik

import de.unikiel.klik.model.*
import grails.transaction.Transactional
import org.joda.time.Duration;

@Transactional
class AdminService {

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
		def activity = Activity.findByDescription("Eine neue Klik-Aktivität beisteuern")
		def completedActivity = new CompletedActivity(activity: activity)
		author.completedActivities.add(completedActivity)
		completedActivity.save(flush: true, failOnError: true)
		author.save(flush: true, failOnError: true)

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
        //TODO how can I do this? activity has no id
        Activity deletionActivity = Activity.get(activityId)

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

        //Unblock once found
        blockedUser.blocked = false
        blockedUser.save(failOnError: true)
	}
	
	void deleteUser(long userId) {

        //Find the user
        User deletionUser = User.get(userId)

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
	
}
