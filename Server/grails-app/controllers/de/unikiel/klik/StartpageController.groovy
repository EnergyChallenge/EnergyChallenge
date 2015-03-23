package de.unikiel.klik

import org.apache.shiro.SecurityUtils
import org.apache.shiro.authc.AuthenticationException
import org.apache.shiro.authc.UsernamePasswordToken
import org.apache.shiro.crypto.SecureRandomNumberGenerator
import org.apache.shiro.crypto.hash.Sha256Hash
import org.apache.shiro.web.util.SavedRequest
import org.apache.shiro.web.util.WebUtils

class StartpageController {

	// injected by Grails; used for SQL Queries
	def dataSource
	
    def index() {
    
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
		render(view:"index", model: [teaser: teaser])
    }
}
