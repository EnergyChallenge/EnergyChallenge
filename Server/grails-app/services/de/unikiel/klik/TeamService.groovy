package de.unikiel.klik

import grails.transaction.Transactional
import org.apache.shiro.subject.Subject

@Transactional
class TeamService {

    void setAvatar(def avatar, long teamId) {

        //Get the team
        Team modifiedTeam = Team.get(teamId)

        //Set the avatar accordingly and save
        //TODO make the avatar work
        modifiedTeam.avatar = avatar
        modifiedTeam.save
    }

	void setName(String name, long teamId) {

        //Get the team
        Team modifiedTeam = Team.get(teamId)

        //Set the name accordingly and save
        modifiedTeam.name = name
        modifiedTeam.save
	}
}
