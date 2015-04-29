package de.unikiel.klik.control.web

import de.unikiel.klik.persistence.Team
import de.unikiel.klik.persistence.User

import de.unikiel.klik.service.TeamService

import grails.validation.ValidationException
import org.apache.shiro.SecurityUtils
import org.codehaus.groovy.grails.core.io.ResourceLocator
import org.springframework.core.io.Resource

class TeamController {
    def TeamService
	def UserService
	def ShiroSecurityManager
	
    def index() {
		User user = User.findByEmail(org.apache.shiro.SecurityUtils.getSubject().getPrincipal());
		Team team = user.getTeam();
		[team:team]
    }

    def edit() {

        //Get the team service to edit the team
      try{
        TeamService.setName(params.name, SecurityUtils.subject)
        redirect(action: "index")
      }catch(ValidationException ex){
        //TDODO
        redirect(action: "index")
      }
    }

	def uploadAvatar() {
		// let team service set the avatar
		def file = request.getFile('avatar')
		try{
			TeamService.setAvatar(file, SecurityUtils.subject)
			flash.message = "Avatar wurde hochgeladen!"
		} catch(Exception ex){
			flash.error = "Das Hochladen ist fehlgeschlagen!"
		}
			redirect(action: "index")
	}
}
