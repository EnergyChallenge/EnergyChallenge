package de.unikiel.klik

import de.unikiel.klik.model.User;
import de.unikiel.klik.model.Team;
import de.unikiel.klik.model.Institute;
import org.apache.shiro.SecurityUtils;

class ProfilController {

   def index(){
	   user()
   }
   
   /* TODO
    * - Handle invalid id param
    */
   
   def user() {
	   def user;
	   if (params.id != null) {
		   user = User.get(params.id);
	   } else {
	   		user = User.findByEmail(SecurityUtils.getSubject().getPrincipal());
	   }
	   def name = user.getName();
	   def team = user.getTeam();
	   def teamname = "";
	   if (team != null) {
		   teamname = team.getName(); 
	   }
	   def institute = user.getInstitute().getName();
	   def collectedPoints = user.getPoints();
	   def isCurrent = (user == User.findByEmail(SecurityUtils.getSubject().getPrincipal()));
	   
	   def model = [type: "user", isCurrent: isCurrent, 
		   			name: name, teamName: teamname, image: "",
					institute: institute,
					collectedPoints: collectedPoints, rankingPosition: "",
					lastActivities: ""
					]
	   
	   showProfile(model);
   }
   
   def team() {

	   def team;
	   if (params.id != null) {
		   team = Team.get(params.id);
	   } else {
	   	   team = User.findByEmail(SecurityUtils.getSubject().getPrincipal()).getTeam();
		   /* Show current users profile, if current user has no team,
		    * but tries to see it. (E.g. bad link)
		    */
		   if (team == null) {
			   user();
			   return;
		   }
	   }
	   def name = team.getName();
	   def collectedPoints = team.getPoints();
	   def isCurrent = (team == User.findByEmail(SecurityUtils.getSubject().getPrincipal()).getTeam());
	   
	   def model = [type: "team", isCurrent: isCurrent, 
		   			name: name, image: "",
					collectedPoints: collectedPoints, rankingPosition: "",
					members: "",
					lastActivities: ""
		]

	   showProfile(model);
   }
   
   private def showProfile(def model) {
	   render(view: "index", model: model);
   }
}
