package de.unikiel.klik

import grails.transaction.Transactional

import org.apache.shiro.subject.Subject
import org.apache.shiro.SecurityUtils
import org.joda.time.DateTime

import de.unikiel.klik.model.CompletedActivity
import de.unikiel.klik.model.User

import de.unikiel.klik.model.Activity

@Transactional
class ActivityService {
	
	def User user
	def Activity activity
	
    void completeActivity(long activityId, Subject subject) {
		user = User.findByEmail(subject.getPrincipal())
		activity = Activity.get(activityId)
		def completedActivity = new CompletedActivity(activity: activity)
		user.addToCompletedActivities(completedActivity)
		completedActivity.save(flush: true, failOnError: true)
		user.save(flush: true, failOnError: true)
    }
	
	void addToFavorites(long activityId, Subject subject) {
		//identify the given user and activity
		user = User.findByEmail(subject.getPrincipal())
		activity = Activity.get(activityId)
		//add the activity to the users favorites
		user.addToFavorites(description: activity.getDescription(), points: activity.getPoints(), duration: activity.getDuration())
		user.save(flush: true, failOnError: true)
	}
	
	void removeFromFavorites(long activityId, Subject subject) {
		//identify the given user and activity
		user = User.findByEmail(subject.getPrincipal())
		activity = Activity.get(activityId)
		//remove the activity from the users favorites
		user.removeFromFavorites(activity: activity)
		user.save(flush: true, failOnError: true)
	}
}
