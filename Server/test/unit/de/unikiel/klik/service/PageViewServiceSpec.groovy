package de.unikiel.klik.service

import de.unikiel.klik.persistence.PageView
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(PageViewService)
@Mock([PageView])
class PageViewServiceSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "page view should be 1 if visited the first time"() {
	when: "creating new pageview"
          PageViewService.visitPage("/")
        then: "pageview views should be 1"
          PageView.findByUrl("/").views == 1
    }
    void "page view should be 2 if visited the twice"() {
	when: "visitetd twice"
          PageViewService.visitPage("/")
          PageViewService.visitPage("/")
        then: "pageview views should be 0"
          PageView.findByUrl("/").views == 2
    }
    void "visiting 2 different pages"() {
	when: "visiting two differnt pages"
          PageViewService.visitPage("/index")
          PageViewService.visitPage("/home")
        then: "pageview views should be 1 for both"
          PageView.findByUrl("/index").views == 1
          PageView.findByUrl("/home").views == 1
   }
}
