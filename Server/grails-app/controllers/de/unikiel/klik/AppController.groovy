package de.unikiel.klik

import grails.converters.JSON
import de.unikiel.klik.model.User;
import de.unikiel.klik.model.Team;
import de.unikiel.klik.model.Activity;
import de.unikiel.klik.model.Proposal;
import de.unikiel.klik.model.Institute;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException
import java.util.ArrayList;

class AppController {
	
	def AuthService
	
    def index() {
	}
	
	def getUserProfile() {
		
		login(params.email, params.password);
		
		User user;
		if (params.id != null) {
			user = User.get(params.id);
		} else {
   			user = User.findByEmail(SecurityUtils.getSubject().getPrincipal());
		}
		if (user == null) {
			//TODO Error 404
			return ;
		}
		String name = user.getName();
		Team team = user.getTeam();
		String teamname = (team != null) ? team.getName() : "";
		String institute = user.getInstitute().getName();
		int points = user.getPoints();
		int position = getPositionOfUser(user);
		def lastActivities = [];
		//TODO Not all! && SORT
		for (activity in user.getCompletedActivities()) {
			lastActivities << activity.getActivity().getDescription();
		}
		def profile = [name: name, team: teamname, institute: institute,
						points: points, position: position,
						lastActivities: lastActivities];
		outputToJson([profile: profile]);
	}
	
	def getTeamProfile() {
		
		login(params.email, params.password);
		
		Team team = Team.get(params.id);
		if (team == null) {
			//TODO 404 Error?
			return ;
		}
		String name = team.getName();
		int points = team.getPoints();
		int position = getPositionOfTeam(team);
		def lastActivities = [];
		//TODO Not all! && SORT
		for (activity in team.getCompletedActivitys()) {
			lastActivities << activity.completedActivity.getActivity().getDescription();
		}
		def members = [];
		for (member in team.getMembers()) {
			members << member.getName();
		}
		def profile = [name: name, points: points, position: position,
						lastActivities: lastActivities, members: members];
		outputToJson([profile: profile]);
	}
	
	def getUserRanking() {
		
		login(params.email, params.password);
		
		def ranking = [];
		for (user in User.findAll()) {
			ranking << [id: user.id, title: user.getName(), points: user.getPoints()];
		}
		ranking.sort { -it.points } //Sort DESC
		
		outputToJson([ranking: ranking]);
	}
	
	def getTeamRanking() {
		
		login(params.email, params.password);
		
		def ranking = [];
		for (team in Team.findAll()) {
			ranking << [id: team.id, title: team.getName(), points: team.getPoints()];
		}
		ranking.sort { -it.points } //Sort DESC
		
		outputToJson([ranking: ranking]);
	}
	
	def getAllActivities() {
		
		login(params.email, params.password);
		
		def activities = []
		for(activity in Activity.findAll{visible == true}) {
			activities << [description: activity.getDescription(), points: activity.getPoints(), duration: activity.getDuration(), active: ActivityService.isExecutable(activity, SecurityUtils.subject)]
		}
		outputToJson([activities : activities])
	}
	
	def getFavoredActivities() {
		
		login(params.email, params.password);
		
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
		
		login(params.email, params.password);
		
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
	
	def getMessages() {
		
		login(params.email, params.password);
		
		//TODO
	}
	
	def performSearch() {
		
		login(params.email, params.password);
		
		def result = [];
		def matchingUsers = User.findAllBySearchNameIlike("%" + params.query + "%")
		for (user in matchingUsers) {
			result << [id: user.getId(), name: user.getName(),
						type: "user", points: user.getPoints()];
		}
		def matchingTeams = Team.findAllByNameIlike("%" + params.query + "%")
		for (team in matchingTeams) {
			result << [id: team.getId(), name: team.getName(),
						type: "team", points: team.getPoints()];
		}
		result.sort { -it.points } //Sort DESC
		
		outputToJson([searchResult: result]);
	}
	
	def completeActivity() {
		if(ActivityService.completeActivity(params.id as long, SecurityUtils.subject)) {
			outputToJson(["Succes"]) //TODO
		} else {
			outputToJson(["Failure"]) //TODO
		}
	}
	
	def commentProposal() {
		
		login(params.email, params.password);
		
		//TODO
	}
	
	
	
	private void outputToJson(def data) {
		render data as JSON;
	}
	
	private void login(String email, String password) {
		//TODO set better response
		try{
			AuthService.login(email, password ,false)
			//Login successful
		}
		catch (AuthenticationException ex){
			//Login failed
			def response = [response: "false"]
			render response as JSON
		}
	}
	
	
	
	
	
	
	//TODO Merge with ProfileController
	private int getPositionOfUser(User user){
		def ranking =  [];
		for (u in User.findAll()) {
			ranking << [name: u.getName(), id: u.id, points: u.getPoints()];
		}
		ranking.sort { -it.points } //Sort DESC
		return ranking.indexOf([name: user.getName(), id: user.id, points: user.getPoints()])+1
   }
	private int getPositionOfTeam(Team team){
		def ranking =  [];
		for (t in Team.findAll()) {
			ranking << [name: t.getName(), id: t.id, points: t.getPoints()];
		}
		ranking.sort { -it.points } //Sort DESC
		return ranking.indexOf([name: team.getName(), id: team.id, points: team.getPoints()])+1
	}
	
}