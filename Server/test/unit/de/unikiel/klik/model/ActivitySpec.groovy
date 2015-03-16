package de.unikiel.klik.model

import grails.test.mixin.TestFor
import org.joda.time.Duration
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Activity)
class ActivitySpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "creating activity with empty description should not save"() {
		when: "an Empty Activity"
			Activity activity = new Activity(description: "",points: "1", duration: new Duration(60*60*1000))	
		then: "Saving should fail"
			!activity.save()
    }
	void "creating activity with negativ Points should not save"() {
		when: "an Empty Activity"
			Activity activityWithNegativPoints = new Activity(description: "activity",points: "-1", duration: new Duration(60*60*1000))
		then: "Saving should fail"
			!activityWithNegativPoints.save()
	}
	void "creating activity with zero Points should not save"() {
		when: "an Empty Activity"
			Activity activityWith0Points = new Activity(description: "activity",points: "0", duration: new Duration(60*60*1000))
		then: "Saving should fail"
			!activityWith0Points.save()
	}
	void "creating activity with too many Points should not save"() {
		when: "Activity with 6 Points"
			Activity activityWith6Points = new Activity(description: "activity",points: "6", duration: new Duration(60*60*1000))
		then: "Saving should fail"
			!activityWith6Points.save()
	}
	void "creating activity with short Duration should not save"() {
		when: "Activity with 1ms Duration"
			Activity activity = new Activity(description: "activity",points: "1", duration: new Duration(1))
		then: "Saving should fail"
			!activity.save()
	}
	void "creating valid activity"() {
		when: "creating a vaild Activity"
			int numOfActivitys = Activity.count()
			Activity activity = new Activity(description: "activity",points: "1", duration: new Duration(60*60*1000))
		then: "Saving should work"
			activity.save()
			Activity.count() == numOfActivitys+1
	}
	
}
