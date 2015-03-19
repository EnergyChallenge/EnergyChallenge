package de.unikiel.klik.model

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Specification
import de.unikiel.klik.TestService

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(CompletedActivity)
@Mock([Activity, CompletedActivity])
class CompletedActivitySpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "completed activity should have the same amount of poits as the corresponding activity"() {
      setup:
      Activity activity = TestService.getExampleActivity()
      activity.save()
      CompletedActivity completedActivity = new CompletedActivity(activity: activity)

      expect:
      completedActivity.getPoints() == activity.getPoints()
    }
}
