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
	def currentUser
	def activity
	def recentActivities

    def index() {
		def activities = []
		for(activity in Activity.findAll()) {
			activities << [activity: activity, executable: isExecutable(activity), countdown: getActivityCountdown(activity), favorite: isFavorite(activity)]
		}
		[activities : activities]
	}
	
	//executes an activity, if execution is possible
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
	
	//adds an activity to the favorites if it isn't already
	def addToFavorites() {
		//get the current user and the selected activity
		currentUser = getCurrentUser()
		//check if activity is yet a favorite
		activity = Activity.get(params.id)
		if(isFavorite(activity)) {
			flash.message = "Activity already a favorite!"
			redirect(action: "index")
		} else {
			ActivityService.addToFavorites(activity.id, SecurityUtils.subject)
			flash.message = "Activity added to favorites!"
			redirect(action: "index")
		}
	}	
	
	//removes a selected activity from the favorites
	def removeFromFavorites() {
		currentUser = getCurrentUser()
		activity = Activity.get(params.id)
		if(!(isFavorite(activity))) {
			flash.message = "Activity not a favorite!"
			redirect(action: "index")
		} else {
			ActivityService.removeFromFavorites(activity.id, SecurityUtils.subject)
			flash.message = "Activity removed from favorites"
			redirect(action: "index")
		}
	}
	//TODO move to service
	//returns a boolean indicating if an activity can currently be executed by a user
	def boolean isExecutable(Activity activity) {
		recentActivities = getRecentlyCompletedActivities(activity.duration)
		if(recentActivities.find{it.activity.id == activity.id}) {
			return false
		}
		return true
	}
	//TODO move to service
	//returns a boolean indicating if the activity is currently on the favorites list of a user
	def boolean isFavorite(Activity activity) {
		currentUser = getCurrentUser()
		if(currentUser.favorites.findAll().contains(activity)) {
			return true
		}
		return false
	}
	//TODO move to Service and format the output type
	//returns a timer instance that works as a countdown, showing when the activity is available again
	def String getActivityCountdown(Activity activity) {
		def currentTime
		def endOfCountdown
		recentActivities = getRecentlyCompletedActivities(activity.duration)
		def completedActivity = recentActivities?.find{it.activity.id == activity.id}
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
	
	//returns the current user, identified by the shiro subject
	def User getCurrentUser() {
		subject = SecurityUtils.subject
		currentUser = User.findByEmail(subject.getPrincipal())
		return currentUser
	}
	//TODO move to service
	//returns a collection of Activities that were completed during the critical time
	def getRecentlyCompletedActivities(Duration duration) {
		currentUser = getCurrentUser()
		def currentTime = new DateTime()
		def DateTime criticalPointOfTime = new DateTime(currentTime.minus(duration))
		def recentlyCompletedActivities = currentUser.completedActivities?.collect().findAll {it.dateCreated.isAfter(criticalPointOfTime)}
		
		return recentlyCompletedActivities
	}
	
	//TODO eventually handle this exception more elegant
	//handles all NullPointerExceptions occurring in this controller
	def nullPointerException(final NullPointerException exception){
		log.error("Exception ocurred. ${exception?.message}", exception)
	}
}