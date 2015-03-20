package de.unikiel.klik

import de.unikiel.klik.model.Team

import grails.validation.ValidationException
import org.apache.shiro.SecurityUtils

import org.codehaus.groovy.grails.core.io.ResourceLocator
import org.springframework.core.io.Resource

class TeamController {

    def index() {

    }

    def edit() {

        //Get the team service to edit the team
        //TODO can anything beside name be changed?
      try{
        TeamService.setName(params.name, SecurityUtils.subject)
        redirect(action = "index")
      }catch(ValidationException ex){
        //TDODO
        redirect(action = "index")
      }
    }

    def uploadAvatar() {

        //Get the team service to set the avatar
        //TODO implement avatar upload
      try{
        TeamService.setAvatar(params.avatar, SecurityUtils.subject)
        redirect(action = "team")
      }catch(ValidationException ex){
        //TDODO
        redirect(action = "index")
      }
    }

  def avatar() {
    Team avatarTeam = Team.get(params.id)
    if (!avatarTeam || !avatarTeam.avatar || !avatarTeam.avatarType) {
      //response.sendError(404)
      //return
      final Resource image = grailsResourceLocator.findResourceForURI('/images/grails_logo.png')
      render file: image.inputStream, contentType: 'image/png'
    }
    response.contentType = avatarTeam.avatarType
    response.contentLength = avatarTeam.avatar.size()
    OutputStream out = response.outputStream
    out.write(avatarTeam.avatar)
    out.close()
  }

}
