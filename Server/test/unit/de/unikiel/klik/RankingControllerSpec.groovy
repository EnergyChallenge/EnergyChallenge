package de.unikiel.klik

import grails.test.mixin.*
import spock.lang.*

@TestFor(RankingController)

class RankingControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    def "in team ranking table title should be Teamname"() {
		when : "team ranking is chosen"
		params.view = "team"
		controller.index()
		
		then: "index.gsp is rendered"
		view == "/ranking/index"
		
		and: "tabletitle is Teamname"
		model.tabletitle == "Teamname"
		
		
    }
}
