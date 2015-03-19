package de.unikiel.klik

import de.unikiel.klik.model.Team
import de.unikiel.klik.model.User
import org.apache.shiro.SecurityUtils

class LandingController {

  def index() {
    User user = User.findByEmail(SecurityUtils.subject.getPrincipal())
    Team team =  user.getTeam()
    def favoriteActivitys = user.getFavorites()


    [user: user, team: team, favoriteActivitys: favoriteActivitys ] 
  }
}
