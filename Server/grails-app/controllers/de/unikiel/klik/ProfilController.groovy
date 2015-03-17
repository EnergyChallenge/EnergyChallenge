package de.unikiel.klik

import de.unikiel.klik.model.User;
import de.unikiel.klik.model.Team;

class ProfilController {

   def index(){
	   //user(1)
	   //TODO Call user with param current user id
   }
   
   def user() {
	   /*
	   def user = User.get(params.id);
	   def name = user.getName();
	   def teamname = user.getTeam().getName();
	   def institute = user.getInstitute;
	   */
	   
	   def model = [type: "user",
		   			name: "", teamname: "", image: "",
					institute: "",
					collectedPoints: "", rankingPosition: "",
					lastActivities: ""
					]
	   //user User.get(params.id)
	   
	   showProfile(model);
   }
   
   def team() {
	   def model = [type: "team",
		   			name: "", image: "",
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
