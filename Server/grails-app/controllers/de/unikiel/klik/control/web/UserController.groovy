package de.unikiel.klik.control.web

import de.unikiel.klik.persistence.User
import de.unikiel.klik.persistence.Team

import de.unikiel.klik.service.UserService

import grails.validation.ValidationException
import org.apache.shiro.SecurityUtils
import org.apache.shiro.subject.Subject
import org.codehaus.groovy.grails.core.io.ResourceLocator
import org.springframework.core.io.Resource

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
      flash.message = "Avatar wurde hochgeladen!"
      //redirect(action:'show',model:[user:user])
    }catch(Exception ex){
      flash.error = "Das Hochladen ist fehlgeschlagen!"
    }
      redirect(action:'edit')
  }


  def joinTeam() {
        //Get the user service to join a team
        UserService.setTeam(params.id as long, SecurityUtils.subject)
        flash.message = "Du bist jetzt ein Mitglied des Teams"
        redirect(controller: "profile", action: "index")
  }

    def leaveTeam() {
        //Get the user service to leave a team
        UserService.unsetTeam(params.id as long, SecurityUtils.subject)
        flash.message = "You have left the team"
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
      if(params.password == params.password2) {
          UserService.setPassword(params.password as String, subject)
          flash.message = "Passwort geändert!"
          redirect(action: "edit")
      }else{
          flash.message = "Passwörter stimmen nicht �berein!"
          redirect(action: "edit")
      }
    }catch(ValidationException ex){
      flash.message = "Passwörter stimmen nicht �berein!"
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
