package de.unikiel.klik.control.app

import de.unikiel.klik.control.web.ProfileController

import de.unikiel.klik.service.AuthService
import de.unikiel.klik.service.ActivityService
import grails.converters.JSON
import de.unikiel.klik.persistence.Profile
import de.unikiel.klik.persistence.User;
import de.unikiel.klik.persistence.Team;
import de.unikiel.klik.persistence.Activity;
import de.unikiel.klik.persistence.Proposal;
import de.unikiel.klik.persistence.Institute;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException
import org.codehaus.groovy.runtime.typehandling.GroovyCastException
import grails.validation.ValidationException
import java.util.ArrayList;

import org.codehaus.groovy.grails.core.io.ResourceLocator
import org.springframework.core.io.Resource

class AppController {
	
	def AuthService
	def ActivityService
	def ProposalService
	ProfileController profileController = new ProfileController()
	
    def index() {
	}
	
	def login() {
		authenticate(params.email, params.password);
		User user = User.findByEmail(SecurityUtils.getSubject().getPrincipal());
		outputToJson([user: [id: user.id, name: user.getName()]]);
	}
	
	def userProfile() {
		
		authenticate(params.email, params.password);
		
		User user;
		println params.id;
		if (params.id == null || params.id == "0") {
			user = User.findByEmail(SecurityUtils.getSubject().getPrincipal());
		} else {
   			user = User.get(params.id);
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
	
	def teamProfile() {
		
		authenticate(params.email, params.password);
		
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
	
	def userRanking() {
		
		authenticate(params.email, params.password);
		
		def ranking = [];
		for (user in User.findAll()) {
			ranking << [id: user.id, title: user.getName(), points: user.getPoints()];
		}
		ranking.sort { -it.points } //Sort DESC
		
		outputToJson([ranking: ranking]);
	}
	
	def teamRanking() {
		
		authenticate(params.email, params.password);
		
		def ranking = [];
		for (team in Team.findAll()) {
			ranking << [id: team.id, title: team.getName(), points: team.getPoints()];
		}
		ranking.sort { -it.points } //Sort DESC
		
		outputToJson([ranking: ranking]);
	}
	
	def allActivities() {
		
		authenticate(params.email, params.password);
		
		def activities = []
		for(activity in Activity.findAll{visible == true}) {
			activities << [id: activity.getId(),
							description: activity.getDescription(),
							points: activity.getPoints(),
							active: ActivityService.isExecutable(activity, SecurityUtils.subject)]
		}
		outputToJson([activities : activities])
	}
	
	def favoredActivities() {
		
		authenticate(params.email, params.password);
		
		def subject = SecurityUtils.subject
		def user = User.findByEmail(subject.getPrincipal())
		def userFavorites = user.favorites.sort {it.id}
		def favorites = []
		for(activity in userFavorites) {
			favorites << [id: activity.getId(),
						description: activity.getDescription(),
						points: activity.getPoints(),
						active: ActivityService.isExecutable(activity, subject)]
		}
		outputToJson([activities: favorites])
	}
	
	def proposals() {
		
		authenticate(params.email, params.password);
		
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
	
	def messages() {
		
		authenticate(params.email, params.password);

        def notifications = ActivityNotification.findAll { ActivityNotification.belongsTo = User.findByEmail(params.email) }

        render notifications as JSON
	}
	
	def search() {
		
		authenticate(params.email, params.password);
		
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
		
		authenticate(params.email, params.password);
		
		if(ActivityService.completeActivity(params.id as long, SecurityUtils.subject)) {
			outputToJson([completeActivity: [success: true]])
		} else {
			outputToJson([completeActivity: [success: false]])
		}
	}
	
	def commentProposal() {
		
		authenticate(params.email, params.password);
		
		try {
			ProposalService.addComment(params.comment as String, params.rating as int, SecurityUtils.getSubject(), params.id as long)
			outputToJson([commentProposal: [success: true]])
		}catch(Exception e) {
			outputToJson([commentProposal: [success: false]])
		}
	}
	
	def avatar() {
		redirect(controller: "profile", action: "avatar", id: params.id)
	}
	
	
	private void authenticate(String email, String password) {
		//TODO set better response
		try{
			AuthService.login(email, password ,false)
			//Login successful
			//TODO
            //def response = [response: "true"] // Thats wrong!
            //render response as JSON			// No JSON Output here!
			//Use
		}
		catch (AuthenticationException ex){
			//Login failed
			def response = [authentication: false, result: [loginFailed: true]]
			render response as JSON
		}
	}
	
	private void outputToJson(def data) {
		def response = [authentication: true, result: data]
		render response as JSON;
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