package de.unikiel.klik.control.web

import geb.spock.GebSpec

class UserControllerSpec extends GebSpec {

    def setup() {
    }

    def cleanup() {
    }

    void "navigate from User Profil to Team"() {
      given: "visitig a Profile with a Team"
        //TODO
      expect: "Being on the ProfilPage"
        //TODO
      and: "Seeing the Users Name"
        //TODO
      when: "Klicking the Team of the user"
        //TODO
      then: "should be on the Page of the Team"
        //TODO
      and: "TeamName should be the name of the Users Team"
        //TODO
    }
   
    void "navigate from Team to one of Its Members"(){
      given: "visiting a Teams Profil"
        //TODO
      expect: "being on the Team Profil Page"
        //TODO
      when: "Kliking on a User"
        //TODO
      then: "should be at the Users profile"
        //TODO
      and: "The Users name is the expected one"
        //TODO
     }
}
