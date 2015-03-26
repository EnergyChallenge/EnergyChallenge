package de.unikiel.klik.service

import de.unikiel.klik.persistence.User;
import de.unikiel.klik.persistence.Team;
import de.unikiel.klik.persistence.Institute;
import grails.transaction.Transactional
import grails.validation.ValidationException
import org.apache.shiro.crypto.hash.Sha256Hash
import org.apache.shiro.subject.Subject

@Transactional
class UserService {

    static final okcontents = ['image/png', 'image/jpeg', 'image/gif']
    void setName(String title, String firstName, String lastName, Subject subject) throws ValidationException {

        //Set the users names and title
        User user = User.findByEmail(subject.getPrincipal())
        user.title = title
        user.firstName = firstName
        user.lastName = lastName
        user.save(flush: true)
    }

	void setPassword(String password, String password2, Subject subject) throws ValidationException {
	if(password == password2){
        //Set the users password to the hashed input
        User user = User.findByEmail(subject.getPrincipal())
        user.passwordHash = new Sha256Hash(password).toHex()
        user.save(flush: true)
	}else{
		throw new ValidationException()
        }
    }

  void setAvatar(def avatar, Subject subject) throws ValidationException{
        User user = User.findByEmail(subject.getPrincipal())
      // List of OK mime-types
    if (!okcontents.contains(avatar.getContentType())) {
      throw new ValidationException()
    }
    // Save the image and mime type
    user.avatar = avatar.bytes
    user.avatarType = avatar.contentType
    log.info("File uploaded: $user.avatarType")
    user.save(flush: true, failOnError: true)
  }

	void setInstitute(long institutedId, Subject subject) throws ValidationException{

		//Set the users institute via the id passed
        Institute institute = Institute.findById(institutedId)
        User user = User.findByEmail(subject.getPrincipal())
        user.institute = institute
        user.save()
	}

	void setTeam(long teamId, Subject subject) throws ValidationException{

        //Set the users team via the id passed
        Team team = Team.findById(teamId)
        User user = User.findByEmail(subject.getPrincipal())
        team.addToMembers(user)
        user.save()
	}

	void setEmailNotification(boolean emailNotification, Subject subject) throws ValidationException {

        //Set the users email notification setting
        User user = User.findByEmail(subject.getPrincipal())
        user.emailNotification = emailNotification
        user.save()
	}

	void createTeamAndJoin(String name, Subject subject) throws ValidationException {

        //Create a team with the user who created it as the first member
        User user = User.findByEmail(subject.getPrincipal())
        Team newTeam = new Team(name: name)
        newTeam.addToMembers(user)
        newTeam.save()
	}
	
}
