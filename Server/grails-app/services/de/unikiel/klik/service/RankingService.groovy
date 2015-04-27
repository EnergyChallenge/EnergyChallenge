package de.unikiel.klik.service

import de.unikiel.klik.persistence.User;
import de.unikiel.klik.persistence.Team;
import de.unikiel.klik.persistence.Institute;
import grails.transaction.Transactional
import grails.validation.ValidationException
import org.apache.shiro.crypto.hash.Sha256Hash
import org.apache.shiro.subject.Subject

@Transactional
class RankingService {

	public int getPositionOfUser(User user){
		def ranking =  [];
		    for (u in User.findAll()) {
		            ranking << [name: u.getName(), id: u.id, points: u.getPoints()];
		    }
		    ranking.sort { -it.points } //Sort DESC
		return ranking.indexOf([name: user.getName(), id: user.id, points: user.getPoints()])+1
	}
	public int getPositionOfTeam(Team team){
		def ranking =  [];
		    for (t in Team.findAll()) {
		            ranking << [name: t.getName(), id: t.id, points: t.getPoints()];
		    }
		    ranking.sort { -it.points } //Sort DESC
		return ranking.indexOf([name: team.getName(), id: team.id, points: team.getPoints()])+1
	}
}
