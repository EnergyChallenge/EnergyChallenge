package de.unikiel.klik.control.web

import java.util.concurrent.TimeUnit

import org.apache.shiro.SecurityUtils
import org.apache.shiro.subject.Subject

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
		[activities : activities]
	}
	
	//executes an activity, if execution is possible
	def completeActivity() {
		if(ActivityService.completeActivity(params.id as long, SecurityUtils.subject)) {
			flash.message = "Activity executed"
			redirect(action: "index")
		} else {
			flash.message = "Activity not executable!"
			redirect(action: "index")
		}
	}
	
	//adds an activity to the favorites if it isn't already
	def addToFavorites() {
		if(ActivityService.addToFavorites(params.id as long, SecurityUtils.subject)) {
			flash.message = "Activity added to favorites!"
			redirect(action: "index")
		} else {
			flash.message = "Activity already a favorite!"
			redirect(action: "index")
		}
	}	
	
	//removes a selected activity from the favorites
	def removeFromFavorites() {
		if(ActivityService.removeFromFavorites(params.id as long, SecurityUtils.subject)) {
			flash.message = "Activity removed from favorites"
			redirect(action: "index")
		} else {
			flash.message = "Activity not a favorite!"
			redirect(action: "index")
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
		log.error("Exception ocurred. ${exception?.message}", exception)
		render view: "error", model: [exception: exception]
	}
}