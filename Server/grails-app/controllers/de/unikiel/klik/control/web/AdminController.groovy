package de.unikiel.klik.control.web

import de.unikiel.klik.persistence.User;
import de.unikiel.klik.persistence.Team;
import de.unikiel.klik.persistence.Proposal;
import de.unikiel.klik.persistence.Activity;
import de.unikiel.klik.persistence.CompletedActivity;
import de.unikiel.klik.service.AdminService
import grails.plugin.mail.GrailsMailException
import org.joda.time.Duration
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import org.joda.time.format.PeriodFormatter
import org.joda.time.format.PeriodFormatterBuilder

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
		
        def rawActivities = Activity.findAll()//([max: 10], offset: 10 * params.page])
		def activities = []
		def String duration
		for(act in rawActivities) {
			duration = getStringOfDuration(act.duration)
			activities << [id: act.id, description: act.description, points: act.points, duration: duration]
		}
        //int lastPage = Activity.count() / 10;
        [activities: activities]
    }
    private String getStringOfDuration(Duration duration){
        long durationInMilliSeconds= duration.getMillis() 
        long milliSecondsInAYear = 1000L*60*60*24*365
        long milliSecondsInAMonth = 1000L*60*60*24*365/12 ///Average
        long milliSecondsInAWeek = 1000L*60*60*24*7
        long milliSecondsInADay = 1000L*60*60*24
        long milliSecondsInAHour = 1000L*60*60
        long milliSecondsInAMinute = 1000L*60
        long milliSecondsInASeconds = 1000L
        if(durationInMilliSeconds > milliSecondsInAYear){
            return (((float) durationInMilliSeconds) / milliSecondsInAYear).round(2) + " Jahre"
        }else if(durationInMilliSeconds > milliSecondsInAMonth){
            return (((float) durationInMilliSeconds) / milliSecondsInAMonth).round(2) + " Monate"
        }else if(durationInMilliSeconds > milliSecondsInAWeek){
            return (((float) durationInMilliSeconds) / milliSecondsInAWeek).round(2) + " Woche"
        }else if(durationInMilliSeconds > milliSecondsInADay){
            return (((float) durationInMilliSeconds) / milliSecondsInADay).round(2) + " Tage"
        }else if(durationInMilliSeconds > milliSecondsInAHour){
            return (((float) durationInMilliSeconds) / milliSecondsInAHour).round(2) + " Stunden"
        }else if(durationInMilliSeconds > milliSecondsInAMinute){
            return (((float) durationInMilliSeconds) / milliSecondsInAMinute).round(2) + " Minuten"
        }else if(durationInMilliSeconds > milliSecondsInAHour){
            return (((float) durationInMilliSeconds) / milliSecondsInASecond).round(2) + " Sekunden"
        }else{
            return durationInMilliSeconds + " ms"
        }
    }
    
    def proposals(){
        def proposals = Proposal.findAll("from Proposal as p order by p.dateCreated")
        DateTimeFormatter fmt = DateTimeFormat.forPattern("dd.MM.yyyy")
        [proposals: proposals, fmt:fmt]
    }

    def editActivity() {
		render(view: "editActivity", model: [newActivity: params.newActivity])
    }

    def changeActivity() {
        try{
          params.durationUnits as long
          params.durationUnitInSeconds as long
          params.points as long
        }catch(java.lang.NumberFormatException ex){
          return
        }
        long durationInSeconds = (params.durationUnits as long )* (params.durationUnitInSeconds as long)
        Duration duration = new Duration(durationInSeconds*1000)
        try{
			if(params.proposalId != ""){
			AdminService.createActivityFromProposal(params.description, params.points as int, duration,params.proposalId as long)
			redirect(action: "proposals")
			}else if(params.activityId != ""){
			AdminService.editActivity(params.description, params.points as int, duration, params.activityId as long)
			redirect(action: "activities")
			}else{ 
			AdminService.createActivity(params.description, params.points as int, duration)
			redirect(action: "activities")
			}
		} catch(Exception e) {
			redirect(action: "activities")
			flash.message = "Die Beschreibung muss zwischen 1 und 255 Zeichen lang sein! Bitte versuchen Sie es erneut."
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
        AdminService.unblockTeam(params.id as long)
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
		def model = [
			allUser: User.getAll()];
		render(view: "message", model: model);
    }

    def emailMessage() {

        //Get the admin service to send a global email
		try {
			AdminService.sendGlobalEmail(params.subject as String, params.message as String)
			flash.message ="Nachricht wurde verschickt"
		} catch (Exception e) {
			//GrailsMailException instead of Exception
			flash.error = "Ein Fehler ist aufgreten. Ist der Inhalt der E-Mail eventuell leer?"
			flash.error = e
		}
		redirect(action: "message")
        
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
