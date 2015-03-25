package de.unikiel.klik

import geb.spock.GebSpec

class ActivityControllerSpec extends GebSpec {

    def setup() {
        to GebActivityPage
    }

    def cleanup() {
    }

    void "completing an Activity sould increase Points"() {
        given: "Points at beginning"
          //TODO
        expect: "being at ActivityPage"
          at GebActivityPage
        when: "executing a Activity"
          //TODO
        then: "Points shoudl increase"
          //TODO
        and: "Activity has now a countdown"
    }
    void "kliking a favorite"(){
        expect: "being at ActivityPage"
          at GebActivityPage
        when: "kliking on favorite"
          //TODO
        then: "activity is now a favorite"
          //TODO
        when: "kliking again"
          //TODO
        then: "activiy is not a favorite anymore"
          //TODO
    }
    
}
