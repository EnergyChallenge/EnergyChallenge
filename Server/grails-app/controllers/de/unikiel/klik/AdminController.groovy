package de.unikiel.klik

import de.unikiel.klik.model.User;
import de.unikiel.klik.model.Team;
import de.unikiel.klik.model.Proposal;
import de.unikiel.klik.model.Activity;
import de.unikiel.klik.model.CompletedActivity;
import org.joda.time.Duration

class AdminController {

    def AdminService

    def index(){

    }
    def users(){
        def users = User.findAll("from User as p order by p.lastName")//, [max: 10] , offset: 10 * params.page])
        //int lastPage = User.count() / 10;
        [users: users]
      
    }
    def teams(){
        def teams = Team.findAll("from Team as p order by p.name")//, [max: 10], offset: 10 * params.page])
        //int lastPage = Team.count() / 10;
        [teams: teams]
    }
    def activities(){
        def activities = Activity.findAll()//([max: 10], offset: 10 * params.page])
        //int lastPage = Activity.count() / 10;
        [activities: activities]
    }
    def proposals(){
        def proposals = Proposal.findAll("from Proposal as p order by p.dateCreated")//, [max: 10], offset: 10 * params.page])
        //int lastPage = Proposal.count() / 10;
        [proposals: proposals]
    }

    def editActivity() {

    }

    def changeActivity() {
        try{
          params.durationUnits as long
          params.durationUnitInSeconds as long
          params.points as long
        }catch(java.lang.NumberFormatException ex){
          return
        }
        long durationInSeconds = ((long)params.durationUnits)* ((long)params.durationUnitInSeconds)
        Duration duration = new Duration(durationInSeconds*1000)
        if(params.proposalId != ""){
          AdminService.createActivityFromProposal(params.description, params.points as int, duration,params.proposalId as long)
          redirect(action: "proposals")
        }else if(params.activityId != ""){
          AdminService.editActivity(params.description, params.points as int, duration, params.activityId as long)
          redirect(action: "activities")
        }else{ 
          AdminService.createActivity(params.description, params.points as int, duration)
          redirect(action: "editActivity")
        }
    }

    def blockUser() {

        //Get the admin service to block a user
        AdminService.blockUser(params.id as long)
        redirect(action: "users")
    }

    def blockTeam() {

        //Get the admin service to block a team
        AdminService.blockTeam(params.id as long)
        redirect(action: "teams")
    }

    def unblockUser() {

        //Get the admin service to unblock a user
        AdminService.unblockUser(params.id as long)
        redirect(action: "users")
    }

    def unblockTeam() {

        //Get the admin service to unblock a team
        AdminService.unblockTeams(params.id as long)
        redirect(action: "teams")
    }

    def deleteUser() {

        //Get the admin service to remove a user
        AdminService.deleteUser(params.id as long)
        redirect(action: "users")
    }

    def deleteTeam() {

        //Get the admin service to remove a team
        AdminService.deleteTeam(params.id as long)
        redirect(action: "teams")
    }
    
    // show all completed activities of a user
    def completedActivities() {
		def user = User.get(params.userid)
        
        render(view: "completedActivities", model: [fullName: user.getName(), userid: params.userid, completedActivities: user.completedActivities.sort{it.id}])
	}
	
	def deleteProposal() {
		AdminService.deleteProposal(params.proposalId as long)
		redirect(action: "proposals")
	}
	
	def deleteActivity() {
		AdminService.deleteActivity(params.activityId as long)
		redirect(action: "activities")
	}

    def message() {

    }

    def emailMessage() {

        //Get the admin service to send a global email
        AdminService.sendGlobalEmail(params.subject as String, params.message as String)
        redirect(action: "admin")
    }

    // delete ONE completed Activity
    def deleteCompletedActivity() {
		def user = User.get(params.userid)
		def completedActivity = CompletedActivity.get(params.completedActivityId)
		user.removeFromCompletedActivities(completedActivity)
		completedActivity.delete(flush: true)
		
        redirect(action: "completedActivities", params: [userid: params.userid])
    }
    
    // delete ALL completed Activities of a user
    def deleteAllCompletedActivities() {
		def user = User.get(params.userid)
		def ids = []
		
		for(ca in user.completedActivities) {
			ids.add(ca.id)
		}
		
		for(id in ids) {
			def ca = CompletedActivity.get(id)
			user.removeFromCompletedActivities(ca)
			ca.delete(flush: true)
		}
		
        redirect(action: "completedActivities", params: [userid: params.userid])
    }
}
