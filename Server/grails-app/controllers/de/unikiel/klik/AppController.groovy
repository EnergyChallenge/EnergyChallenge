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
	
	def getUserProfile() {
		//TODO
		User user;
		if (params.id != null) {
			user = User.get(params.id);
		} else {
   			user = User.findByEmail(SecurityUtils.getSubject().getPrincipal());
		}
		String name = user.getName();
		Team team = user.getTeam();
		String teamname = (team != null) ? team.getName() : "";
		String institute = user.getInstitute().getName();
		int points = user.getPoints();
		int position = getPositionOfUser(user);
		def lastActivities = user.getCompletedActivities(); //TODO
		
		
		
	}
	
	def getUserRanking() {
		def ranking = [];
		for (user in User.findAll()) {
			ranking << [id: user.id, title: user.getName(), points: user.getPoints()];
		}
		ranking.sort { -it.points } //Sort DESC
		
		outputToJson([ranking: ranking]);
	}
	
	def getTeamRanking() {
		def ranking = [];
		for (team in Team.findAll()) {
			ranking << [id: team.id, title: team.getName(), points: team.getPoints()];
		}
		ranking.sort { -it.points } //Sort DESC
		
		outputToJson([ranking: ranking]);
	}
	
	def getAllActivities() {
		def ActivityService
		def activities = []
		for(activity in Activity.findAll()) {
			activities << [description: activity.getDescription(), points: activity.getPoints(), duration: activity.getDuration(), active: ActivityService.isExecutable(activity, SecurityUtils.subject)]
		}
		outputToJson([activities : activities])
	}
	
	def getFavoredActivities() {
		def ActivityService
		def subject = SecurityUtils.subject
		def user = User.findByEmail(subject.getPrincipal())
		def userFavorites = user.favorites.sort {it.id}
		def favorites = []
		for(activity in userFavorites) {
			favorites << [description: activity.getDescription(), points: activity.getPoints(), duration: activity.getDuration(), active: ActivityService.isExecutable(activity, subject)]
		}
		outputToJson([favorites: favorites])
	}
	
	def getProposals() {
		//TODO Sort comments and proposals
		def proposals = [];
		for (proposal in Proposal.findAll()) {
			def comments = []
			for (comment in proposal.getComments()) {
				
				boolean isOwn = false;
				if (comment.getAuthor() == User.findByEmail(SecurityUtils.getSubject().getPrincipal())) {
					isOwn = true;
				}
				
				comments << [id: comment.getId(),
							text: comment.getText(),
							rating: comment.getRating(),
							author: comment.getAuthor().getName(),
							isOwn: isOwn];
			}
			
			proposals << [id: proposal.getId(),
							description: proposal.getDescription(),
							author: proposal.getAuthor().getName(),
							rating: proposal.getRating(),
							comments: comments];
		}
		
		outputToJson([proposals: proposals]);
	}
	
	def performSearch() {
		//TODO
	}
	
	def completeActivity() {
		//TODO
	}
	
	
	
	private void outputToJson(def data) {
		render data as JSON;
	}
	
	
	
	
	
	
	
	
	
	
	//TODO
	
	/*
	 * 
	 * 
	 * ALL DEPRECATED!!!
	 * 
	 * 
	 * 
	 * 
	 */
	
	
	
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

    def getUserProfileOld(){

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

    def getTeamProfileOld(){

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

    def getProposalsOld(){

        //Get all the proposals
        def proposals = Proposal.getAll();

        //Return the full objects in JSON
        render proposals as JSON;
    }

    def getProposalOld(){

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
