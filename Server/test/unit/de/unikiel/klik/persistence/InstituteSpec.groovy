package de.unikiel.klik.persistence

import de.unikiel.klik.service.TestService

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Institute)
@Mock([Institute])
class InstituteSpec extends Specification {

	Institute institute
    def setup() {
		institute = TestService.getExampleInstitute()
		institute.save()
    }

    def cleanup() {
    }

    void "Name of institute should not be null"() {
		expect:
		institute.getName() != null
    }
	void "There should not be two institutes with the same name" (){
		when:
		  Institute cloneInstitute =  new Institute(name: institute.getName())
		then: "cloneInstitute should be invalid"
		  !cloneInstitute.validate()
	}
}
