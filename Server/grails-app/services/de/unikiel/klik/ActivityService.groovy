package de.unikiel.klik

import grails.transaction.Transactional
import grails.validation.ValidationException

import org.apache.shiro.subject.Subject
import org.apache.shiro.SecurityUtils
import org.joda.time.DateTime
import org.joda.time.Duration;
import org.joda.time.Period
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
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
		if(!activity.visible || recentActivities.find{it.activity.id == activity.id}) {
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
		
		def endOfCountdown
		def currentTime
		def regularity
		def completedActivity
		String countdown
		//TODO the current display isn't really good
		//defines how the countdown is displayed
		PeriodFormatter formatter = new PeriodFormatterBuilder()
		.printZeroAlways()
		.minimumPrintedDigits(2)
		.appendHours()
		.appendSeparator(":")
		.appendMinutes()
		.appendSeparator(":")
		.appendSeconds()
		.toFormatter()
		
		if(isExecutable(activity, subject)) {
			currentTime = endOfCountdown = 0
		} else {
			recentActivities = getRecentlyCompletedActivities(activity.duration, subject)
			completedActivity = recentActivities?.find{it.activity.id == activity.id}
			regularity = getRegularity(completedActivity, activity.duration, subject)
			currentTime = new DateTime()
			switch(regularity) {
				
				case "hourly":
					endOfCountdown = new DateTime(completedActivity.dateCreated.plus(activity.duration))
					Period period = new Period(currentTime, endOfCountdown)
					countdown = formatter.print(period)
					break
				
				case "daily":
					endOfCountdown = new DateTime(completedActivity.dateCreated.plus(activity.duration).withTimeAtStartOfDay())
					Period period = new Period(currentTime, endOfCountdown)
					countdown = formatter.print(period)
					break
				
				default:
					endOfCountdown = new DateTime(completedActivity.dateCreated.plus(activity.duration))
					DateTimeFormatter fmt = DateTimeFormat.forPattern("dd.MM.yyyy")
					countdown = fmt.print(endOfCountdown)
			}
		}
		return countdown
	}
	
	//returns a collection of Activities that were completed during the critical time
	def getRecentlyCompletedActivities(Duration duration, Subject subject) {
		currentUser = User.findByEmail(subject.getPrincipal())
		def DateTime criticalPointOfTime
		def currentTime = new DateTime()
		if(duration.getStandardHours() < 24) {
			criticalPointOfTime = new DateTime(currentTime.minus(duration))
		} else {
			def days = duration.getStandardDays().toInteger() - 1
			criticalPointOfTime = new DateTime(currentTime.minusDays(days).withTimeAtStartOfDay())
		}
		def recentlyCompletedActivities = currentUser.completedActivities?.collect().findAll {it.dateCreated.isAfter(criticalPointOfTime)}
		
		return recentlyCompletedActivities
	}
	
	//returns a string indicating how long the time interval is
	def String getRegularity(CompletedActivity completedActivity, Duration duration, Subject subject) {
		def endPoint = new DateTime(completedActivity.dateCreated.plus(duration))
		def endPointAsMillis = endPoint.getMillis()
		def currentTime = new DateTime()
		def currentTimeAsMillis = currentTime.getMillis()
		def criticalDuration = new Duration(endPointAsMillis - currentTimeAsMillis)
		if(duration.getStandardHours() < 24) {
			return "hourly"
		} else if(criticalDuration.getStandardHours() < 24) {
			return "daily"
		} else {
			return "default"
		}
	}
}
