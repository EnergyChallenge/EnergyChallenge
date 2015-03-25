package de.unikiel.klik

import geb.spock.GebSpec

class LandingControllerSpec extends GebSpec {

    def setup() {
        to GebLandingPage
    }

    def cleanup() {
    }

    void "logout shoudl log you out"() {
        expect: "Should start at LandingPage"
          at GebLandingPage
        when: "klick logout"
          //TODO
        then: "Land at StartPage"
          at GebStartPage
    }
    void "logout shoudl log you out"() {
        expect: "Should start at LandingPage"
          at GebLandingPage
        when: "klick ProfilImage"
          //TODO
        then: "land at /user/edit"
          //TODO
    }
    void "klicking on name should linkt to the users Profil"() {
        expect: "Should start at LandingPage"
          at GebLandingPage
        when: "klick Users Name"
          //TODO
        then: "land at /profil/index"
          //TODO
    }
}
