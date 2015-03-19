package de.unikiel.klik

import java.util.concurrent.TimeUnit

import org.apache.shiro.SecurityUtils
import org.apache.shiro.subject.Subject
import org.joda.time.DateTime
import org.joda.time.Duration
import org.joda.time.Period
import org.joda.time.PeriodType
import org.joda.time.format.PeriodFormatter
import org.joda.time.format.PeriodFormatterBuilder

import de.unikiel.klik.model.Activity
import de.unikiel.klik.model.User

class ActivityController {
	
	def ActivityService
	def subject
	def user
	def activity

    def index() {
		def activities = []
		for(activity in Activity.findAll()) {
			activities << [activity: activity, executable: isExecutable(activity), countdown: getActivityCountdown(activity)]
		}
		[activities : activities]
	}
	
	def completeActivity() {
		activity = Activity.get(params.id)
		if(isExecutable(activity)) {
			ActivityService.completeActivity(activity.id, SecurityUtils.subject)
			flash.message = "Activity executed"
			redirect(action: "index")
		} else {
			flash.message = "Activity not executable!"
			redirect(action: "index")
		}
	}
	
	def addToFavorites() {
		//get the current user and the selected activity
		user = getUser()
		//TODO Check if if case is necessary
		//check if activity is yet a favorite
		def activityId = Activity.get(params.id).id
		if(user.favorites.contains(activityId)) {
			flash.message = "Activity already a favorite!"
			redirect(action: index)
		} else {
		ActivityService.addToFavorites(activityId, subject)
		//redirect(action: index)
		}
	}	
	
	def removeFromFavorites() {
		user = getUser()
		def activityId = Activity.get(params.id).id
		if(user.favorites.contains(activityId)) {
			flash.message = "Activity not a favorite!"
			redirect(action: index)
		} else {
			ActivityService.removeFromFavorites(activityId, subject)
			//redirect(action: index)
		}
	}
	
	//check if an activity can currently be executed by a user
	def boolean isExecutable(Activity activity) {
		user = getUser()
		def recentActivities = getRecentlyCompletedActivities(activity.duration)
		if(recentActivities.find{a -> a.activity.id == activity.id}) {
			return false
			}
		return true
	}
	//TODO format the period instance!
	//returns a timer instance that works as a countdown, showing when the activity is available again
	def String getActivityCountdown(Activity activity) {
		def currentTime
		def endOfCountdown
		def completedActivities = getRecentlyCompletedActivities(activity.duration)
		def completedActivity = completedActivities?.find{a -> a.activity.id == activity.id}
		if(isExecutable(activity)) {
			currentTime = endOfCountdown = 0
		} else {
			currentTime = new DateTime()
			endOfCountdown = new DateTime(completedActivity.getDateCreated() + activity.getDuration())
		}
		PeriodFormatter formatter = new PeriodFormatterBuilder()
			.printZeroAlways()
			.minimumPrintedDigits(2)
			.appendHours()
			.appendSeparator(":")
			.appendMinutes()
			.appendSeparator(":")
			.appendSeconds()
			.toFormatter()
		
		Period period = new Period(currentTime, endOfCountdown)
		String countdown = formatter.print(period)
		return countdown
	}
	
	def User getUser() {
		subject = SecurityUtils.subject
		user = User.findByEmail(subject.getPrincipal())
		return user
	}
	
	def getRecentlyCompletedActivities(Duration duration) {
		user = getUser()
		def currentTime = new DateTime()
		def DateTime criticalPointOfTime = new DateTime(currentTime.minus(duration))
		def recentlyCompletedActivities = user.completedActivities?.collect().findAll {a -> a.dateCreated.isAfter(criticalPointOfTime)}
		
		return recentlyCompletedActivities
	}
}