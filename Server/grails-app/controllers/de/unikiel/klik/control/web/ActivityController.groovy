package de.unikiel.klik.control.web

import java.util.concurrent.TimeUnit

import org.apache.shiro.SecurityUtils
import org.apache.shiro.subject.Subject

import de.unikiel.klik.service.ActivityService
import de.unikiel.klik.persistence.Activity
import de.unikiel.klik.persistence.User

class ActivityController {
	
	def ActivityService
	def activity

    def index() {
		def activities = []
		for(activity in Activity.findAll{visible == true}) {
			activities << [activity: activity, executable: isExecutable(activity), countdown: getActivityCountdown(activity), favorite: isFavorite(activity)]
		}
		activities.sort {it.activity.duration}
		[activities : activities]
	}
	
	//executes an activity, if execution is possible
	def completeActivity() {
		if(ActivityService.completeActivity(params.id as long, SecurityUtils.subject)) {
			flash.message = "Aktivität erledigt!"
			redirect(controller: params.origin, action: "index")
		} else {
			flash.error = "Aktivität nicht ausführbar!"
			redirect(controller: params.origin, action: "index")
		}
	}
	
	//adds an activity to the favorites if it isn't already
	def addToFavorites() {
		if(ActivityService.addToFavorites(params.id as long, SecurityUtils.subject)) {
			flash.message = "Aktivität zu Favoriten hinzugefügt!"
			redirect(controller: params.origin, action: "index")
		} else {
			flash.error = "Aktivität ist bereits favorisiert!"
			redirect(controller: params.origin, action: "index")
		}
	}	
	
	//removes a selected activity from the favorites
	def removeFromFavorites() {
		if(ActivityService.removeFromFavorites(params.id as long, SecurityUtils.subject)) {
			flash.message = "Aktivität aus Favoriten entfernt!"
			redirect(controller: params.origin, action: "index")
		} else {
			flash.error = "Aktivität ist kein Favorit!"
			redirect(controller: params.origin, action: "index")
		}
	}
	
	def isExecutable(activity) {
		ActivityService.isExecutable(activity, SecurityUtils.subject)
	}
	
	def isFavorite(activity) {
		ActivityService.isFavorite(activity, SecurityUtils.subject)
	}
	
	def getActivityCountdown(activity) {
		ActivityService.getActivityCountdown(activity, SecurityUtils.subject)
	}
	
	//TODO eventually handle this exception more elegant
	//handles all NullPointerExceptions occurring in this controller
	def nullPointerException(final NullPointerException exception){
		log.error("Exception occured. ${exception?.message}", exception)
		flash.error = "Aktivität nicht verfügbar!"
		redirect(action: "index")
	}
}