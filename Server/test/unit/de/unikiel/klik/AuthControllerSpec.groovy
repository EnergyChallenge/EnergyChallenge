package de.unikiel.klik

import geb.spock.GebSpec

class AuthControllerSpec extends GebSpec {

    def setup() {
        to GebStartPage
    }

    def cleanup() {
    }

	@Ignore // currently not working
    void "login with valid user should redirect to Landingpage"() {
		expect: "Visit the HomePage"
		at GebStartPage
		when: "entering correct Username and password"
		login("user@example.com","password",false)
		then: "should land at landingPage"
		at GebLandingPage
    }
    void "login with invalid user should show error"() {
		expect: "Visit the HomePage"
		at GebStartPage
		when: "entering incorrect Username and password"
		login("ThisIsNotAnEmail","password",false)
		then: "should stay on Page"
		at GebStartPage
		and: "should see an Error"
		//TODO
		and: "Infos should stay reserved"
		//TODO
    }
    void "registering should log you in"(){
		given: 
		to GebRegisterPage
		expect: "RegisterPage"
		at GebRegisterPage
		when: "entering correct infos"
		register("newUser@example.com","FirstName","LastName","password","password",1)
		then: "should land at landingPage"
		at GebLandingPage
    }
    void "registering a user with a Used Email should fail"() {
		given: 
		to GebRegisterPage
		expect: "Visit the RegisterPage"
		at GebRegisterPage
		when: "entering a allredy user Email"
		register("user@example.com","FirstName","LastName","password","password",1)
		then: "should stay on Page"
		at GebRegisterPage
		and: "should see an Error"
		//TODO
		and: "Infos should stay reserved"
		//TODO
    }
}
