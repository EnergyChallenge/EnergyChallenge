package de.unikiel.klik

import java.util.concurrent.TimeUnit

import org.apache.shiro.SecurityUtils
import org.apache.shiro.subject.Subject

import de.unikiel.klik.model.Activity
import de.unikiel.klik.model.User

class ActivityController {
	
	def ActivityService

    def index() {
		def activities = []
		for(activity in Activity.findAll()) {
			activities << [activity: activity, executable: ActivityService.isExecutable(activity, SecurityUtils.subject), countdown: ActivityService.getActivityCountdown(activity, SecurityUtils.subject), favorite: ActivityService.isFavorite(activity, SecurityUtils.subject)]
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
}