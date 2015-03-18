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
    * - implement get last activities
    */
   
   def user() {
	   User user;
	   if (params.id != null) {
		   user = User.get(params.id);
	   } else {
	   		user = User.findByEmail(SecurityUtils.getSubject().getPrincipal());
	   }
	   String name = user.getName();
	   Team team = user.getTeam();
	   String teamname = "";
	   if (team != null) {
		   teamname = team.getName(); 
	   }
	   String institute = user.getInstitute().getName();
	   int collectedPoints = user.getPoints();
	   boolean isCurrent = (user == User.findByEmail(SecurityUtils.getSubject().getPrincipal()));
	   
	   def model = [type: "user", isCurrent: isCurrent, 
		   			name: name, teamName: teamname, image: "",
					institute: institute,
					collectedPoints: collectedPoints, rankingPosition: "",
					lastActivities: ""
					]
	   
	   showProfile(model);
   }
   
   def team() {

	   Team team;
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
	   
	   String name = team.getName();
	   int collectedPoints = team.getPoints();
	   boolean isCurrent = (team == User.findByEmail(SecurityUtils.getSubject().getPrincipal()).getTeam());
	   def members = [];
	   for (member in team.getMembers()) {
		   members << [name: member.getName(), id: member.id];
	   }
	   
	   def model = [type: "team", isCurrent: isCurrent, 
		   			name: name, image: "",
					collectedPoints: collectedPoints, rankingPosition: "",
					members: members,
					lastActivities: ""
		]

	   showProfile(model);
   }
   
   private def showProfile(def model) {
	   render(view: "index", model: model);
   }
}
