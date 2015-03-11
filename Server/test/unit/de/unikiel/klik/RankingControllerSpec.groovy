package de.unikiel.klik

import grails.test.mixin.TestFor
import spock.lang.*

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(RankingController)
class RankingControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    def "test something"() {
		when : "team ranking is chosen"
		params.view = "team"
		controller.index()
		
		then: "team ranking is displayed"
		view == "/ranking/index"
		model.typeprefix == "Team"
    }
}
