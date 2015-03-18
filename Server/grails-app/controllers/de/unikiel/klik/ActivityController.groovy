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
		activity = Activity.get(params.id)
		if(isExecutable()) {
			ActivityService.completeActivity(activity.id, SecurityUtils.subject)
			flash.message = "Activity executed"
			redirect(action: "index")
		} else {
			flash.error = "Activity not executable!"
			redirect(action: "index")
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
		//redirect(action: index)
		}
	}	
	
	def removeFromFavorites() {
		subject = SecurityUtils.subject
		user = User.findByEmail(subject.getPrincipal())
		def activityId = (long)params.id
		if(user.favorites.contains(activityId)) {
			flash.message = "Activity not a favorite!"
			redirect(action: index)
		} else {
			ActivityService.removeFromFavorites(activityId, subject)
			//redirect(action: index)
		}
	}
	
	//check if an activity can currently be executed by a user
	def boolean isExecutable() {
		subject = SecurityUtils.subject
		user = User.findByEmail(subject.getPrincipal())
		activity = Activity.get(params.id)
		def recentActivities = user.getCompletedActivities()
		recentActivities = recentActivities?.findAllWhere(dateCreated.after((new DateTime()) - activity.getDuration()))
		if(recentActivities.contains(activity)) {
			return false
		}
		return true
	}
}