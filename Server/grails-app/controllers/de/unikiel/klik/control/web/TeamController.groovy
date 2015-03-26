package de.unikiel.klik.control.web

import de.unikiel.klik.persistence.Team

import de.unikiel.klik.service.TeamService

import grails.validation.ValidationException
import org.apache.shiro.SecurityUtils

class TeamController {
    def TeamService
    def index() {

    }

    def edit() {

        //Get the team service to edit the team
        //TODO can anything beside name be changed?
      try{
        TeamService.setName(params.name, SecurityUtils.subject)
        redirect(action: "index")
      }catch(ValidationException ex){
        //TDODO
        redirect(action: "index")
      }
    }

    def uploadAvatar() {

        //Get the team service to set the avatar
        //TODO implement avatar upload
      try{
        TeamService.setAvatar(params.avatar, SecurityUtils.subject)
        redirect(action: "index")
      }catch(ValidationException ex){
        //TDODO
        redirect(action: "index")
      }
    }

}
