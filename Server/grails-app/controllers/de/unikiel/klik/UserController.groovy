package de.unikiel.klik

import de.unikiel.klik.model.User;
import de.unikiel.klik.model.Team;

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
    User user = User.findByEmail(org.apache.shiro.SecurityUtils.getSubject().getPrincipal());
    [user:user]
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
    User user = User.findByEmail(subject.getPrincipal()); //TODO
    def file = request.getFile('avatar')
    try{
      UserService.setAvatar(file,subject) 
      flash.message = "Avatar (${user.avatarType}, ${user.avatar.size()} bytes) uploaded."
      //redirect(action:'show',model:[user:user])
      redirect(action:'avatar', id: user.id)
    }catch(ValidationException ex){
    render(view:'select_avatar', model:[user:user])
    return
    }
  }
ResourceLocator grailsResourceLocator
  def avatar() {
    
    User avatarUser = User.get(params.id)
    if (!avatarUser || !avatarUser.avatar || !avatarUser.avatarType) {
      //response.sendError(404)
      //return
      final Resource image = grailsResourceLocator.findResourceForURI('/images/grails_logo.png')
      render file: image.inputStream, contentType: 'image/png'
    }
    response.contentType = avatarUser.avatarType
    response.contentLength = avatarUser.avatar.size()
    OutputStream out = response.outputStream
    out.write(avatarUser.avatar)
    out.close()
  }

/* TODO
  def joinTeam() {
        //Get the user service to join a team
        UserService.setTeam(params.teamId, SecurityUtils.subject)
        redirect(action = "user")
  }
*/
  def newTeam() {
        //Get the user service to create a team
        UserService.createTeamAndJoin(params.name, SecurityUtils.subject)
        redirect(action = "edit")
  }

  def changePassword(){
    try{
      Subject subject = SecurityUtils.subject
            UserService.setPassword(params.password as String, params.password2 as String, subject)
            flash.message = "Passwords changed"
            redirect(action = "edit")
    }catch(ValidationException ex){
      flash.message = "Passwords dont Match"
      redirect(action = "edit")
    }
  }

/*
    def changeEmailNotification(){

        //Get the user service to change email notification settings
        UserService.setEmailNotification(params.emailNotification, SecurityUtils.subject)
        redirect(action = "settings")
    } 
*/
}
