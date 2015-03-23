package de.unikiel.klik

import de.unikiel.klik.model.Team
import de.unikiel.klik.model.User
import org.apache.shiro.SecurityUtils

class LandingController {
	
	def ActivityService

  def index() {
    User user = User.findByEmail(SecurityUtils.subject.getPrincipal())
    Team team =  user.getTeam()
    def favoriteActivities = []
	def userFavorites = user.favorites.sort {it.id}
	for(activity in userFavorites) {
		favoriteActivities << [activity: activity, executable: ActivityService.isExecutable(activity, SecurityUtils.subject), countdown: ActivityService.getActivityCountdown(activity, SecurityUtils.subject)]
	}

    [user: user, team: team, favoriteActivities: favoriteActivities ] 
  }
}
