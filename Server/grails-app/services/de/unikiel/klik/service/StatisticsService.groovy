package de.unikiel.klik.service

import de.unikiel.klik.persistence.*
import grails.transaction.Transactional
//import grails.util.GrailsUtil
//import groovy.sql.Sql
import groovy.sql.GroovyRowResult

@Transactional
class StatisticsService {

	// used for SQL Queries
	//def dataSource

	// TODO currently not working - I don't know why :(
	def getActivitiesAndCompletionFrequencies(Object dataSource) {
	
		groovy.sql.Sql sql = new groovy.sql.Sql(dataSource)
		log.info(sql)
		log.info("datasource " + dataSource)
		def results = sql.rows("SELECT a.description AS description, counts.n AS n\n" +
				"FROM activity a, (SELECT activity_id, count(*) AS n FROM completed_activity GROUP BY activity_id) counts\n" +
				"WHERE a.id = counts.activity_id\n" +
				"ORDER BY n DESC")
		sql.close()
		
		// for debugging
		//println results

		// explicit type conversion
		def result_list = []
		for(int i=0; i < results.size(); i++) {
			result_list << [results[i].DESCRIPTION, (int) results[i].N]
		}

		return(result_list)
	}
}
