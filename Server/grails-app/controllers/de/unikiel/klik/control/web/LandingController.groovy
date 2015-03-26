package de.unikiel.klik.control.web

import de.unikiel.klik.persistence.Team
import de.unikiel.klik.persistence.User

import de.unikiel.klik.service.ActivityService

import org.apache.shiro.SecurityUtils
import java.util.Random

class LandingController {
	
	def ActivityService
	Random rand = new Random();

  def index() {
    User user = User.findByEmail(SecurityUtils.subject.getPrincipal())
    Team team =  user.getTeam()
	def members = []
	def teamProposals = []
	if(team != null){
		team?.members.sort{it.points}
	} else {	// generate team proposals
		def allTeams = Team.findAll()
		for(int i = 0; i < 5; i++) {
			def t = allTeams.get(rand.nextInt(allTeams.size()))
			teamProposals << t
			allTeams.remove(t)
		}
	}
	members.reverse(true)
    def favoriteActivities = []
	def userFavorites = []
	if(user.favorites!= null){
		userFavorites = user.favorites.sort {it.id}
	}
	for(activity in userFavorites) {
		favoriteActivities << [activity: activity, executable: ActivityService.isExecutable(activity, SecurityUtils.subject), countdown: ActivityService.getActivityCountdown(activity, SecurityUtils.subject)]
	}

    [user: user, members: members, favoriteActivities: favoriteActivities, team:team, teamProposals:teamProposals] 
  }
}
