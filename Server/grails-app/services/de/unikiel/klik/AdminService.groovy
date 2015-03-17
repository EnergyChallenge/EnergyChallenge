package de.unikiel.klik

import de.unikiel.klik.model.*
import grails.transaction.Transactional
import org.joda.time.Duration;

@Transactional
class AdminService {

    void createActivity(String description, int points, Duration duration) {

        //Declare the activity and save it
        def newActivity = new Activity(description: description, points: points, duration: duration)
        newActivity.save()
    }
	
	void createActivityFromProposal(String description, int points, Duration duration, long proposalId) {

        //Create the new activity from the proposal information
		def newActivity = new Activity(description: description, points: points, duration: duration)
        newActivity.save()

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
        modifiedActivity.save()
	}

	void deleteActivity(long activityId) {

        //Find the activity
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
        blockedUser.blocked = "true"
        blockedUser.save()
	}

	void unblockUser(long userId) {

        //Find the user
        User unblockedUser = User.get(userId)

        //Unblock once found
        blockedUser.blocked = "false"
        blockedUser.save()
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
        blockedTeam.blocked = "true"
        blockedTeam.save()
	}

	void unblockTeam(long teamId) {

        //Find the team
        Team unblockedTeam = Team.get(teamId)

        //Block once found
        unblockedTeam.blocked = "false"
        unblockedTeam.save()
	}

	void deleteTeam(long teamId) {

        //Find the team
        Team deletionTeam = Team.get(teamId)

        //Remove once found
        deletionTeam.delete()
	}
	
}
