package de.unikiel.klik;

import de.unikiel.klik.model.*
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import grails.converters.JSON;
import org.apache.shiro.subject.Subject
import org.apache.shiro.*
import grails.util.GrailsUtil
//import au.com.bytecode.opencsv.CSVWriter
import org.grails.plugins.csv.CSVWriter

class StatisticsController {

	// used for SQL Queries
	def dataSource
	
	// TODO move the query to the StatisticsController
	//def statsService = new StatisticsService()
	
    def index() {
		Subject currentUser = SecurityUtils.getSubject();
		
		def mostPopularActivities = getTop10Activities();
		def pageVisitsIndex = getVisitsOf("/index")	// TODO fix visitor counts 
		def pageVisitsSignIn = getVisitsOf("/auth/signIn")	// TODO fix visitor counts 
		def pointsOverDays = getPointsOverDays()
		[authenticatedUser: currentUser.isAuthenticated(),
			mostPopularActivitys : mostPopularActivities as JSON,
			pageVisitsIndex: pageVisitsIndex as JSON,
			pointsOverDays: pointsOverDays as JSON,
			pageVisitsSignIn: pageVisitsSignIn as JSON];
	}

	def exportCsv() {
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
		//def activityList = statsService.getActivitiesAndCompletionFrequencies(dataSource)
		
		def sw = new StringWriter()
		def b = new CSVWriter(sw, {
			col1:"description" { it.DESCRIPTION }
			col2:"haeufigkeit" { it.N }
			})
		for(int i=0; i < activityList.size(); i++) {
			b << activityList[i]
		}

		byte[] bytes = b.writer.toString().bytes

		response.setContentType("text/csv")
		response.setHeader("Content-disposition", "filename=\"klik_activity_popularity.csv\"")
		response.setContentLength(bytes.size())
		response.outputStream << bytes
		
		render(view: "index")
	}

	private def getMostPopularActivities(){
		// query
		groovy.sql.Sql sql = new groovy.sql.Sql(dataSource)
		log.info(sql)
		log.info("datasource " + dataSource)
		def results = sql.rows("SELECT a.description AS description, counts.n AS n\n" +
		"FROM activity a, (SELECT activity_id, count(*) AS n FROM completed_activity GROUP BY activity_id) counts\n" +
		"WHERE a.id = counts.activity_id\n" +
		"ORDER BY n DESC") 
		sql.close()
		
		def result_list = []
		
		for(int i=0; i < results.size(); i++) {
			result_list << [results[i].DESCRIPTION, (int) results[i].N]
		}
		
		
		return result_list//.sort{it[1]}
		//return [["Haendewaschen mit kaltem Wasser",10],["Mit dem Fahrrad zur Uni fahren bis 3km",20],["Persönlichen CO2-Abdruck berechnen",15],["Treppe statt Fahrstuhl nutzen pro Tag",2],["Mit dem Fahrrad zur Uni fahren bis 5km",12],["Mit dem Fahrrad zur Uni fahren über 5km",7]]
	}
	private def getTop10Activities(){
		def allActivities = getMostPopularActivities()
		def top10Activities = allActivities[0..9]
		top10Activities << ["Andere", 0]
		for(int i = 10; i< allActivities.size(); i++){
			top10Activities[10][1]+=allActivities[i][1]
		}
		return top10Activities
	}
	private def getVisitsOf(String url){
		def rawData = PageView.findAll("from PageView as pageView where pageView.url=? order by pageView.dateCreated", [url])
		def data = [];
		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd 0:00");
		for (PageView pageView : rawData){
			data << [fmt.print(pageView.getDateCreated())+"AM", pageView.getViews()]
		}
		if (data != []){
			return data
		}else{
			return[0]
		}
	}
	private def getPointsOverDays(){
		def rawData = CompletedActivity.findAll("from CompletedActivity as ca order by ca.dateCreated")
		def data = [];
		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd 0:00");
		DateTime dateOfFirstCompletedActivity = rawData[0].getDateCreated()
		data = [[fmt.print(dateOfFirstCompletedActivity)+"AM", 0]]
		for(completedActivity in rawData){
			if(completedActivity.getDateCreated().getDayOfYear() == dateOfFirstCompletedActivity.getDayOfYear()){
				//incremet the points at the current day
				data[-1][1] += completedActivity.getPoints()
			}else{
				dateOfFirstCompletedActivity = completedActivity.getDateCreated()
				data << [fmt.print(dateOfFirstCompletedActivity)+"AM", completedActivity.getPoints()]
			}
		}
		return data
	}
}
