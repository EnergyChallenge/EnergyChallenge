package de.unikiel.klik

import grails.transaction.Transactional
import grails.validation.ValidationException

import org.apache.shiro.subject.Subject
import org.apache.shiro.SecurityUtils
import org.joda.time.DateTime
import org.joda.time.Duration;
import org.joda.time.Period
import org.joda.time.format.PeriodFormatter
import org.joda.time.format.PeriodFormatterBuilder

import de.unikiel.klik.model.CompletedActivity
import de.unikiel.klik.model.User
import de.unikiel.klik.model.Activity

@Transactional
class ActivityService {
	
	def currentUser
	def activity
	def recentActivities
	
    def boolean completeActivity(long activityId, Subject subject) throws ValidationException {
		currentUser = User.findByEmail(subject.getPrincipal())
		activity = Activity.get(activityId)
		if(isExecutable(activity, subject)) {
			def completedActivity = new CompletedActivity(activity: activity)
			currentUser.addToCompletedActivities(completedActivity)
			completedActivity.save(flush: true, failOnError: true)
			currentUser.save(flush: true, failOnError: true)
			return true
		} else {
			return false
		}
    }
	
	def boolean addToFavorites(long activityId, Subject subject) {
		//identify the given user and activity
		currentUser = User.findByEmail(subject.getPrincipal())
		activity = Activity.get(activityId)
		//add the activity to the users favorites
		if(!(isFavorite(activity, subject))) {
			currentUser.addToFavorites(activity)
			currentUser.save(flush: true, failOnError: true)
			return true
		} else {
			return false
		}
	}
	
	def boolean removeFromFavorites(long activityId, Subject subject) {
		//identify the given user and activity
		currentUser = User.findByEmail(subject.getPrincipal())
		activity = Activity.get(activityId)
		//remove the activity from the users favorites
		if(isFavorite(activity, subject)) {
			currentUser.removeFromFavorites(activity)
			currentUser.save(flush: true, failOnError: true)
			return true
		} else {
			return false
		}
	}
	
	//returns a boolean indicating if an activity can currently be executed by a user
	def boolean isExecutable(Activity activity, Subject subject) {
		recentActivities = getRecentlyCompletedActivities(activity.duration, subject)
		if(recentActivities.find{it.activity.id == activity.id}) {
			return false
		}
		return true
	}
	
	//returns a boolean indicating if the activity is currently on the favorites list of a user
	def boolean isFavorite(Activity activity, Subject subject) {
		currentUser = User.findByEmail(subject.getPrincipal())
		if(currentUser.favorites.findAll().contains(activity)) {
			return true
		}
		return false
	}
	
	//returns a timer instance that works as a countdown, showing when the activity is available again
	def String getActivityCountdown(Activity activity, Subject subject) {
		def currentTime
		def endOfCountdown
		recentActivities = getRecentlyCompletedActivities(activity.duration, subject)
		def completedActivity = recentActivities?.find{it.activity.id == activity.id}
		if(isExecutable(activity, subject)) {
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
	
	//returns a collection of Activities that were completed during the critical time
	def getRecentlyCompletedActivities(Duration duration, Subject subject) {
		currentUser = User.findByEmail(subject.getPrincipal())
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
