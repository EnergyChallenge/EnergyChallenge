package de.unikiel.klik.persistence

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Specification
import de.unikiel.klik.service.TestService

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(CompletedActivity)
@Mock([Activity, CompletedActivity])
class CompletedActivitySpec extends Specification {

	CompletedActivity completedActivity
	Activity activity
    def setup() {
		activity = TestService.getExampleActivity()
		activity.save()
		completedActivity = new CompletedActivity(activity: activity)

    }

    def cleanup() {
    }

    void "completed activity should have the same amount of poits as the corresponding activity"() {
      expect:
      completedActivity.getPoints() == activity.getPoints()
    }
	void "dateCreated should not be null"(){
		expect:
		completedActivity.getDateCreated() != null
	}
}
