package de.unikiel.klik.control.web

import de.unikiel.klik.service.PageViewService
import de.unikiel.klik.service.StatisticsService

import org.apache.shiro.SecurityUtils
import org.apache.shiro.authc.AuthenticationException
import org.apache.shiro.authc.UsernamePasswordToken
import org.apache.shiro.crypto.SecureRandomNumberGenerator
import org.apache.shiro.crypto.hash.Sha256Hash
import org.apache.shiro.web.util.SavedRequest
import org.apache.shiro.web.util.WebUtils
import org.joda.time.DateTime
import org.joda.time.Period

class StartpageController {

	// injected by Grails; used for SQL Queries
	def dataSource
    def PageViewService
	
    def index() {
    
		
		//Check if EnergyChallenge should be available
		
		DateTime ENERGYCHALLENGE_START_TIME = new DateTime(2015, 06, 01, 0, 0, 0, 0);
		DateTime ENERGYCHALLENGE_END_TIME = new DateTime(2015, 07, 01, 0, 0, 0, 0);
		DateTime ENERGYCHALLENGE_REG_START_TIME = new DateTime(2015, 05, 18, 0, 0, 0, 0);
		
		def countdown = null;
		def showCountdown = false;
		def challengeIsOver = false;
		def enableLogin = false;
		
		def enableReg = ENERGYCHALLENGE_REG_START_TIME.isBeforeNow() && ENERGYCHALLENGE_END_TIME.isAfterNow();
		
		if (ENERGYCHALLENGE_START_TIME.isAfterNow()) {
			showCountdown = true;
			
			Period diff = new Period(DateTime.now(), ENERGYCHALLENGE_START_TIME);
			
			countdown = [seconds: diff.getSeconds(),
						minutes : diff.getMinutes(),
						hours: diff.getHours(),
						days : diff.toStandardDays().getDays(),
						dataSeconds: diff.toStandardSeconds().getSeconds()]
		} else if (ENERGYCHALLENGE_END_TIME.isBeforeNow()) {
			challengeIsOver = true;
		} else {
			enableLogin = true;
		}
		
		
		
		PageViewService.visitPage("/index")
		// query
		groovy.sql.Sql sql = new groovy.sql.Sql(dataSource)
		log.info(sql)
		log.info("datasource " + dataSource)
		def activityList = sql.rows("SELECT a.description AS description, counts.n AS n\n" +
		"FROM activity a, (SELECT activity_id, count(*) AS n FROM completed_activity GROUP BY activity_id) counts\n" +
		"WHERE a.id = counts.activity_id\n" +
		"ORDER BY n DESC")
		sql.close()
		
		// TODO move the query to the StatisticsController
		//def statsService = new StatisticsService()
		//def activityList = statsService.getActivitiesAndCompletionFrequencies(dataSource)
		
		def teaser = [] 
		for(int i = 0; i < 5; i++) {
			teaser << activityList[i]
		}
		render(view:"index", model: [teaser: teaser,
									showCountdown : showCountdown,
									challengeIsOver : challengeIsOver,
									enableLogin : enableLogin,
									enableReg : enableReg,
									countdown: countdown])
    }

	def hints() {
		render(view:"hints")
	}
	
	def contact() {
		render(view:"contact")
	}
}
