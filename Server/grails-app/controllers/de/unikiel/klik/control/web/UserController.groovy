package de.unikiel.klik.control.web

import de.unikiel.klik.persistence.User
import de.unikiel.klik.persistence.Team

import de.unikiel.klik.service.UserService

import grails.validation.ValidationException
import org.apache.shiro.SecurityUtils
import org.apache.shiro.subject.Subject
import org.codehaus.groovy.grails.core.io.ResourceLocator
import org.springframework.core.io.Resource
//From: https://grails.org/wiki/Simple%20Avatar%20Uploader

class UserController {
	
	def UserService

  private static final okcontents = ['image/png', 'image/jpeg', 'image/gif']
  def ShiroSecurityManager
  def index() {
    //User user = User.findByEmail(org.apache.shiro.SecurityUtils.getSubject().getPrincipal());
    //[user:user]
	redirect(controller: "profile")
  }

  def edit() {
    User user = User.findByEmail(org.apache.shiro.SecurityUtils.getSubject().getPrincipal());
    Team team = user.getTeam();
    [user:user, team: team]
  }
  def save(){
    Subject subject = SecurityUtils.subject
	//TODO This works now, but still throws an exception to the console (doesn't affect functionality)
    UserService.setName(params.title, params.firstName, params.lastName, subject)
    //UserService.setInstitute(params.instituteId, subject)
    redirect(action: "edit")
  }

  def uploadAvatar() {
    Subject subject = SecurityUtils.subject
    User user = User.findByEmail(subject.getPrincipal());
    def file = request.getFile('avatar')
    try{
      UserService.setAvatar(file,subject) 
      flash.message = "Avatar (${user.avatarType}, ${user.avatar.size()} bytes) uploaded."
      //redirect(action:'show',model:[user:user])
    }catch(ValidationException ex){
      flash.message = "Upload ist fehlgeschlagen"
    }
      redirect(action:'edit')
  }


  def joinTeam() {
        //Get the user service to join a team
        UserService.setTeam(params.id as long, SecurityUtils.subject)
        redirect(controller: "profile", action: "index")
  }

  def newTeam() {
        //Get the user service to create a team
        UserService.createTeamAndJoin(params.name, SecurityUtils.subject)
        redirect(action: "edit")
  }

  def changePassword(){
    try{
      Subject subject = SecurityUtils.subject
            UserService.setPassword(params.password as String, params.password2 as String, subject)
            flash.message = "Passwords changed"
            redirect(action: "edit")
    }catch(ValidationException ex){
      flash.message = "Passwords dont Match"
      redirect(action: "edit")
    }
  }

/*
    def changeEmailNotification(){
        //TODO
        //Get the user service to change email notification settings
        UserService.setEmailNotification(params.emailNotification, SecurityUtils.subject)
        redirect(action: "settings")
    } 
*/
}
