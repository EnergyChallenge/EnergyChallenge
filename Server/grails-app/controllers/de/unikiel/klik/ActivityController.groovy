package de.unikiel.klik

import java.util.concurrent.TimeUnit

import org.apache.shiro.SecurityUtils
import org.apache.shiro.subject.Subject
import org.joda.time.DateTime

import de.unikiel.klik.model.Activity
import de.unikiel.klik.model.User

class ActivityController {
	
	def ActivityService
	def subject
	def user
	def activity

    def index() {
		def activities = Activity.list()
		[activities : activities]
	}
	
	//TODO reduce, simplify this method
	def completeActivity() {
		subject = SecurityUtils.subject
		user = User.findByEmail(subject.getPrincipal())
		activity = Activity.get(params.id)
		def completedActivity
		def currentTime = new DateTime()
		//TODO search only the last completed activities
		def recentlyCompletedActivities = user.getCompletedActivities()
		if(recentlyCompletedActivities) {
			recentlyCompletedActivities = query.list()
		} else {
			ActivityService.completeActivity(activity.id, subject)
			flash.message = "Activity executed"
			redirect(action: "index")
		}
		//check if activity was done yet
		if((completedActivity = recentlyCompletedActivities?.find {activity == activity})) {
			flash.error = "Activity not executable!"
			redirect(action: "index")
			
		//if the activity wasn't executed by the user during critical time, just execute it
		} else {
			ActivityService.completeActivity(activity.id, subject)
			flash.message = "Activity executed"
			//redirect(action: "index")
		}
	}
	
	def addToFavorites() {
		//get the current user and the selected activity
		subject = SecurityUtils.subject
		user = User.findByEmail(subject.getPrincipal())
		def activityId = (long)params.id
		//TODO Check if if case is necessary
		//check if activity is yet a favorite
		if(user.favorites.contains(activityId)) {
			flash.message = "Activity already a favorite!"
			redirect(action: index)
		} else {
		ActivityService.addToFavorites(activityId, subject)
		redirect(action: index)
		}
	}	
	
	def removeFromFavorites() {
		subject = SecurityUtils.subject
		activityId = params.id
		ActivityService.removeFromFavorites(activityId, subject)
		redirect(action: index)
	}
}