package de.unikiel.klik.control.web

import de.unikiel.klik.persistence.User;
import de.unikiel.klik.persistence.Team;
import de.unikiel.klik.persistence.Profile;
import de.unikiel.klik.persistence.Institute;
import de.unikiel.klik.service.RankingService;
import org.apache.shiro.SecurityUtils;

class TestController {

    def index() {
		User user;
		if (params.id != null) {
			user = User.get(params.id);
		} else {
				user = User.findByEmail(SecurityUtils.getSubject().getPrincipal());
			 params.id = user.id
		}
		boolean isCurrent = (user == User.findByEmail(SecurityUtils.getSubject().getPrincipal()));
		String name = user.getName();
		Team team = user.getTeam();
		String teamname = "";
		int teamId = 0;
		if (team != null) {
			teamname = team.getName();
			teamId = team.getId();
		}
		String institute = user.getInstitute().getName();
		user.setCachedPoints(42)
		int cachedPoints = user.getCachedPoints();
		int cachedPosition = user.getCachedRankingPosition();
		//int cachedRanking = user.getCachedRanking();
		int collectedPoints = user.getPoints();
		//int rankingPosition = RankingService.getPositionOfUser(user);
		def lastActivities = user.getCompletedActivities();
			def recentActivities = lastActivities.sort{-it.getDateCreated().getMillis()}
			if (recentActivities.size() > 10){
				lastActivities = recentActivities[0..9]
			}else{
				lastActivities = recentActivities
			}
		def model = [id: params.id,type: "user", isCurrent: isCurrent,
						name: name, teamName: teamname, teamId: teamId,
					 institute: institute,
					 cachedPoints: cachedPoints,
					 cachedPosition: cachedPosition,
					 collectedPoints: collectedPoints, /*rankingPosition: rankingPosition,*/
					 lastActivities: lastActivities
					 ]
		render model
		
	}
}
