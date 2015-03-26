package de.unikiel.klik.control.web

import de.unikiel.klik.persistence.Team
import de.unikiel.klik.persistence.User

import de.unikiel.klik.service.ActivityService

import org.apache.shiro.SecurityUtils

class LandingController {
	
	def ActivityService

  def index() {
    User user = User.findByEmail(SecurityUtils.subject.getPrincipal())
    Team team =  user.getTeam()
	def members = team?.members.sort{it.points}
	members.reverse(true)
    def favoriteActivities = []
	def userFavorites = []
	if(user.favorites!= null){
		userFavorites = user.favorites.sort {it.id}
	}
	for(activity in userFavorites) {
		favoriteActivities << [activity: activity, executable: ActivityService.isExecutable(activity, SecurityUtils.subject), countdown: ActivityService.getActivityCountdown(activity, SecurityUtils.subject)]
	}

    [user: user, members: members, favoriteActivities: favoriteActivities ] 
  }
}
