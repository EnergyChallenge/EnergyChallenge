package de.unikiel.klik.model

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
//import grails.test.mixin.domain.DomainClassUnitTestMixin
import spock.lang.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
//@TestFor(KlikUserController)
@Mock(KlikUser)
class KlikUserSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

	@Ignore
    void "email should contain uni-kiel.de"() {
		setup:
		def testUser = new KlikUser(name: "Wolfgang", email: "wolfgang@mail.uni-kiel.de")
		
		expect:
		testUser.getEmail().contains("uni-kiel.de")
    }
}
