package de.unikiel.klik

import de.unikiel.klik.model.User;
import de.unikiel.klik.model.Team;
import de.unikiel.klik.model.Proposal;
import de.unikiel.klik.model.Activity;

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
        if(params.proposalId){
          AdminService.createActivityFromProposal(params.description, params.points as int, duration,params.proposalId as long)
          redirect(action: "proposals")
        }else if(params.activityId){
          AdminService.editActivity(params.description, params.points as int, duration, params.proposalId as long)
          redirect(action: "activitys")
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
        AdminService.unblockUser(params.id as long)
        redirect(action: "users")
    }

    def deleteTeam() {

        //Get the admin service to remove a team
        AdminService.unblockTeams(params.id as long)
        redirect(action: "teams")
    }

}
