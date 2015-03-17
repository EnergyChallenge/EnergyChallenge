package de.unikiel.klik

import de.unikiel.klik.model.User;
import de.unikiel.klik.model.Team;
import de.unikiel.klik.model.Institute;
import grails.transaction.Transactional
import org.apache.shiro.subject.Subject

@Transactional
class UserService {

    void setName(String title, String firstName, String lastName, Subject subject) {

        //Set the users names and title
        User user = User.findByEmail(subject.getPrincipal())
        user.title = title
        user.firstName = firstName
        user.lastName = lastName
        user.save()
    }

	void setPassword(String password, String password2, Subject subject) {

        //Set the users password to the hashed input
        User user = User.findByEmail(subject.getPrincipal())
        user.passwordHash = password
        user.save()
    }

	void setAvatar(def avatar, Subject subject) {

		//TODO avatar bitstream saving implimentation
	}

	void setInstitute(long institutedId, Subject subject) {

		//Set the users institute via the id passed
        Institute institute = Institute.findById(institutedId)
        User user = User.findByEmail(subject.getPrincipal())
        user.institute = institute
        user.save()
	}

	void setTeam(long teamId, Subject subject) {

        //Set the users team via the id passed
        Team team = Team.findById(teamId)
        User user = User.findByEmail(subject.getPrincipal())
        user.team = team
        user.save()
	}

	void setEmailNotification(boolean emailNotification, Subject subject) {

        //Set the users email notification setting
        User user = User.findByEmail(subject.getPrincipal())
        user.emailNotification = emailNotification
        user.save()
	}

	void createTeamAndJoin(String name, Subject subject) {

        //Create a team with the user who created it as the first member
        User user = User.findByEmail(subject.getPrincipal())
        Team newTeam = new Team(name: name, members: user)
        newTeam.save()
	}
	
}
