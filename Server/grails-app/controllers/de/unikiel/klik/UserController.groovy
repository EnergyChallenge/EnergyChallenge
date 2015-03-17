package de.unikiel.klik

import de.unikiel.klik.model.Profile;
import de.unikiel.klik.model.User;
import org.apache.shiro.SecurityUtils
import org.apache.shiro.crypto.hash.Sha256Hash
//From: https://grails.org/wiki/Simple%20Avatar%20Uploader

class UserController {

	private static final okcontents = ['image/png', 'image/jpeg', 'image/gif']
	
    def index() {

    }

	def edit() {

        //Get the user service to edit the user
        UserService.setName(params.title, params.firstName, params.lastName, SecurityUtils.subject)
        UserService.setInstitute(params.instituteId, SecurityUtils.subject)
        redirect(action = "settings")
	}

	def uploadAvatar() {

	  Profile user = User.findByEmail(org.apache.shiro.SecurityUtils.getSubject().getPrincipal()); //TODO
	  //springSecurityService.currentUser // or however you select the current user
	  // Get the avatar file from the multi-part request
	  def f = request.getFile('avatar')
	
	  // List of OK mime-types
	  if (!okcontents.contains(f.getContentType())) {
		flash.message = "Avatar must be one of: ${okcontents}"
		render(view:'select_avatar', model:[user:user])
		return
	  }
	
	  // Save the image and mime type
	  user.avatar = f.bytes
	  user.avatarType = f.contentType
	  log.info("File uploaded: $user.avatarType")
	
	  // Validation works, will check if the image is too big
	  if (!user.save(flush: true)) {
		render(view:'select_avatar', model:[user:user])
		return
	  }
	  flash.message = "Avatar (${user.avatarType}, ${user.avatar.size()} bytes) uploaded."
	  //redirect(action:'show',model:[user:user])
	  redirect(action:'show')
	}

	def avatarImage() {

		def avatarUser = Profile.get(params.id)
		if (!avatarUser || !avatarUser.avatar || !avatarUser.avatarType) {
		  response.sendError(404)
		  return
		}
		response.contentType = avatarUser.avatarType
		response.contentLength = avatarUser.avatar.size()
		OutputStream out = response.outputStream
		out.write(avatarUser.avatar)
		out.close()
    }

	def show() {

		Profile user = User.findByEmail(org.apache.shiro.SecurityUtils.getSubject().getPrincipal()); //TODO
		[user:user]
	}

    def joinTeam() {

        //Get the user service to join a team
        UserService.setTeam(params.teamId, SecurityUtils.subject)
        redirect(action = "user")
    }

    def newTeam() {

        //Get the user service to create a team
        UserService.createTeamAndJoin(params.name, SecurityUtils.subject)
        redirect(action = "team")
    }

    def changePassword(){

        //Check the passwords match
        if (params.password != params.password2) {
            //TODO ensure the message returned works with the user controller
            flash.message = "Passwords do not match"
            redirect(action:'changePassword')
        }else {
            //Hash the passwords
            String hashedPassword = new Sha256Hash(params.password).toHex()
            String hashedPassword2 = new Sha256Hash(params.password2).toHex()

            //Get the user service to change the password
            UserService.setPassword(password: hashedPassword, password2: hashedPassword2, SecurityUtils.subject)
            flash.message = "Passwords changed"
            redirect(action = "settings")
        }
    }

    def changeEmailNotification(){

        //Get the user service to change email notification settings
        UserService.setEmailNotification(params.emailNotification, SecurityUtils.subject)
        redirect(action = "settings")
    }
}
