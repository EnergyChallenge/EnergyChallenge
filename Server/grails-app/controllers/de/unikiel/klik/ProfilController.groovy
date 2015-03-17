package de.unikiel.klik

import de.unikiel.klik.model.User;
import de.unikiel.klik.model.Team;
import de.unikiel.klik.model.Institute;

class ProfilController {

   def index(){
	   //user(1)
	   //TODO Call user with param current user id
   }
   
   def user() {
	   
	   def user = User.get(params.id);
	   def name = user.getName();
	   def team = user.getTeam();
	   def teamname = "";
	   if (team != null) {
		   teamname = team.getName(); 
	   }
	   def institute = user.getInstitute().getName();
	   
	   
	   def model = [type: "user",
		   			name: name, teamname: teamname, image: "",
					institute: institute,
					collectedPoints: "", rankingPosition: "",
					lastActivities: ""
					]
	   
	   showProfile(model);
   }
   
   def team() {
	   
	   def team = Team.get(params.id);
	   def name = team.getName();
	   
	   def model = [type: "team",
		   			name: name, image: "",
					collectedPoints: "", rankingPosition: "",
					members: "",
					lastActivities: ""
		]

	   showProfile(model);
   }
   
   private def showProfile(def model) {
	   render(view: "index", model: model);
   }
}
