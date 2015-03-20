package de.unikiel.klik

import de.unikiel.klik.model.Team
import grails.transaction.Transactional
import grails.validation.ValidationException
import org.apache.shiro.subject.Subject


@Transactional
class TeamService {

    void setAvatar(def avatar, Subject subject) throws ValidationException{

        //Get the team
        Team modifiedTeam = User.findByEmail(subject.getPrincipal()).team

        //Set the avatar accordingly and save
        //TODO make the avatar work
    if (!okcontents.contains(avatar.getContentType())) {
      throw new ValidationException()  
    }
    // Save the image and mime type
    modifiedTeam.avatar = avatar.bytes
    modifiedTeam.avatarType = avatar.contentType
    log.info("File uploaded: $user.avatarType")
    modifiedTeam.save(flush: true, failOnError: true)
    }

	void setName(String name, Subject subject) throws ValidationException {

        //Get the team
        Team modifiedTeam = User.findByEmail(subject.getPrincipal()).team

        //Set the name accordingly and save
        modifiedTeam.name = name
        modifiedTeam.save(failOnError: true)
	}
}
