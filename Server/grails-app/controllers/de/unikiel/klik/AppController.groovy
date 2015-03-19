package de.unikiel.klik

import grails.converters.JSON
import de.unikiel.klik.model.User;
import de.unikiel.klik.model.Team;
import de.unikiel.klik.model.Activity;
import de.unikiel.klik.model.Proposal;
import de.unikiel.klik.model.Institute;
import org.apache.shiro.SecurityUtils;
import java.util.ArrayList;

class AppController {

    def index() {
	}

    def getRankingUsers() {

        //Get the appropriate information from the users
        for (user in User.findAll()) {
            rankedUsers = [name: user.getName(), id: user.id, points: user.getPoints()];
        }

        //Sort the users
        rankedUsers.sort { -it.points }

        //Return them as a JSON
        render rankedUsers as JSON;
    }
	
	def getRankingTeams() {

        //Get the appropriate information from the teams
        for (team in Team.findAll()) {
            rankedTeams = [name: team.getName(), id: team.id, points: team.getPoints()];
        }

        //Sort the teams
        rankedTeams.sort { -it.points }

        //Return them as a JSON
        render rankedTeams as JSON;
	}

    def getUserProfile(){

        //Get the user to be returned
        User user;
        if (params.id != null) {
            user = User.get(params.id);
        } else {
            user = User.findByEmail(SecurityUtils.getSubject().getPrincipal());
        }

        //Return the full object in JSON
        render user as JSON;
    }

    def getTeamProfile(){

        //Get the team to be returned
        Team team = Team.get(params.id);

        //Return the full object in JSON
        render team as JSON;
    }

    def getActivities(){

        //Get all the activities
        def activities = Activity.getAll();

        //Return the full objects in JSON
        render activities as JSON;
    }

    def getFavoriteActivities(){

        //Get the user and then retrive their favorites
        User user;
        if (params.id != null) {
            user = User.get(params.id);
        } else {
            user = User.findByEmail(SecurityUtils.getSubject().getPrincipal());
        }
        def favorites = user.getFavorites();

        //Return the favorites in JSON
        render favorites as JSON;
    }

    def getProposals(){

        //Get all the proposals
        def proposals = Proposal.getAll();

        //Return the full objects in JSON
        render proposals as JSON;
    }

    def getProposal(){

        //Get the proposal to be returned
        Proposal proposal = Proposal.get(params.id);

        //Return the full object in JSON
        render team as JSON;
    }

    def search(){

        //Get all users and teams matching the name given as the search parameter
        //TODO I understand this isn't a great way to implement search (if this even works), any ideas?
        Team team = Team.getByName(params.searchQuery);
        User user = User.getByName(params.searchQuery);
        def search;
        search.add(user);
        search.add(team);

        //Return them as JSON
        render search as JSON;
    }

    def doActivity(){

        //Get the activity controller to complete an activity
        ActivityService.completeActivity(params.activityId, SecurityUtils.getSubject())
    }

    def commentProposal(){

        //Get the proposal service to add the comment
        ProposalService.addComment(params.commentText, params.rating, SecurityUtils.getSubject(), params.proposalId);
    }
}
