package de.unikiel.klik.control.web

import de.unikiel.klik.persistence.User;
import de.unikiel.klik.persistence.Team;
import de.unikiel.klik.persistence.Profile;
import de.unikiel.klik.persistence.Institute;
import de.unikiel.klik.service.RankingService;
import org.apache.shiro.SecurityUtils;

import org.codehaus.groovy.grails.core.io.ResourceLocator
import org.springframework.core.io.Resource

class ProfileController {
	def RankingService;
	def index(){
	   user()
	}
   
   /* TODO
    * - Handle invalid id param
    * - implement get last activities
    */
   
	def user() {
	   User user;
	   if (params.id != null) {
		   user = User.get(params.id);
	   } else {
	   		user = User.findByEmail(SecurityUtils.getSubject().getPrincipal());
			params.id = user.id
	   }
	   boolean isCurrent = (user == User.findByEmail(SecurityUtils.getSubject().getPrincipal()));
	   String name = user.getName();
	   Team team = user.getTeam();
	   String teamname = "";
	   int teamId = 0;
	   if (team != null) {
		   teamname = team.getName();
		   teamId = team.getId();
	   }
	   String institute = user.getInstitute().getName();
	   int collectedPoints = user.getPoints();
	   int rankingPosition = RankingService.getPositionOfUser(user);
	   def lastActivities = user.getCompletedActivities(); 
           def recentActivities = lastActivities.sort{-it.getDateCreated().getMillis()}
           if (recentActivities.size() > 10){
               lastActivities = recentActivities[0..9]
           }else{
               lastActivities = recentActivities
           }
	   def model = [id: params.id,type: "user", isCurrent: isCurrent, 
		   			name: name, teamName: teamname, teamId: teamId,
					institute: institute,
					collectedPoints: collectedPoints, rankingPosition: rankingPosition,
					lastActivities: lastActivities
					]
	   
	   showProfile(model);
	}
   
	def team() {

	   Team team;
	   User user = User.findByEmail(SecurityUtils.getSubject().getPrincipal());
	   if (params.id != null) {
		   team = Team.get(params.id);
	   } else {
	   	   team = user.getTeam();
		   /* Show current users profile, if current user has no team,
		    * but tries to see it. (E.g. bad link)
		    */
		   if (team == null) {
			   user();
			   return;
		   }
	   }
	   
	   String name = team.getName();
	   int collectedPoints = team.getPoints();
	   boolean isCurrent = (team == User.findByEmail(SecurityUtils.getSubject().getPrincipal()).getTeam());
           int rankingPosition = RankingService.getPositionOfTeam(team);
	   def members = [];
	   for (member in team.getMembers()) {
		   members << [name: member.getName(), id: member.id];
	   }
	   members.sort {it.name}
	   def lastActivities = team.getCompletedActivitys() 
           def recentActivities = lastActivities.sort{-it.completedActivity.getDateCreated().getMillis()}
           if (recentActivities.size() > 10){
               lastActivities = recentActivities[0..9]
           }else{
               lastActivities = recentActivities
           }
	   def model = [id: params.id,type: "team", isCurrent: isCurrent, 
		   			name: name, image: "",
					collectedPoints: collectedPoints, rankingPosition: rankingPosition,
					members: members,
					lastActivities: lastActivities, user: user
		]

	   showProfile(model);
	}
   
	private def showProfile(def model) {
	   render(view: "index", model: model);
	}
   
	ResourceLocator grailsResourceLocator
	def avatar() {
		Profile profile = Profile.get(params.id)
		if (!profile || !profile.avatar || !profile.avatarType) {
		  final Resource image = grailsResourceLocator.findResourceForURI('/images/default_avatar.png')
		  render file: image.inputStream, contentType: 'image/png'
		  //render (file: new File("path to file"), fileName: "avatar.png")
		  return
		}
		response.contentType = profile.avatarType
		response.contentLength = profile.avatar.size()
		OutputStream out = response.outputStream
		out.write(profile.avatar)
		out.close()
		return
	}

	def nullPointerException(final NullPointerException exception){
            log.error("Exception occured. ${exception?.message}", exception)
            flash.error = "Profil ist nicht verfügbar!"
            redirect(action: "index")
    }

}
