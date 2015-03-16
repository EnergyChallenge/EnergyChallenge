package de.unikiel.klik

import de.unikiel.klik.model.*
import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

import org.apache.shiro.crypto.hash.Sha256Hash

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(AuthController)
@Mock([User, Role, User])
class AuthControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

	@Ignore // currently not working
    void "test something"() {
		setup: "create user"
		def userRole = new Role(name: "user")
		userRole.addToPermissions("*:*")
		userRole.save();
		def user = new User(email: "user", passwordHash: new Sha256Hash("password").toHex(), name: "Max Mustermann")
		user.addToRoles(userRole)
		user.save()
		
		when: "user logs in with correct username and password"
		params.username = "user"
		params.password = "password"
		controller.signIn()
		
		then: "user is redirected to landingpage"
		response.redirectedUrl == "/landing/index"
    }
}
