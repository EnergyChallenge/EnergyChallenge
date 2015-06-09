package de.unikiel.klik.control.web

import de.unikiel.klik.persistence.*

import de.unikiel.klik.service.StatisticsService

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import grails.converters.JSON;
import org.apache.shiro.subject.Subject
import org.apache.shiro.*
import grails.util.GrailsUtil
import org.grails.plugins.csv.CSVWriter

class StatisticsController {

	// used for SQL Queries
	def dataSource
	
	// TODO move the query to the StatisticsController
	//def statsService = new StatisticsService()
	
    def index() {
		Subject currentUser = SecurityUtils.getSubject();
		
		
		//render(view: "notAvailable")
		
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
	def download(){
		def sw = new StringWriter()
		def b = new CSVWriter(sw, {
			col1:"Beschreibung" { it.val1 }
			col2:"Daten" { it.val2 }
		})
		if(params.data == "activities"){
			exportCsv(getMostPopularActivities(),"Aktivitaeten",b)
		}else if(params.data == "points"){
			exportCsv(getPointsOverDays(),"Punkte",b)
		}else if(params.data == "visitsOnIndex"){
			exportCsv(getVisitsOf("/index"),"BesucheAufStartseite",b)
		}else if(params.data == "logins"){
			exportCsv(getVisitsOf("/auth/signIn"),"logins",b)
		}else{
			flash.message = "Daten nicht gefunden"
			redirect(action: "index")
		}
	}
	//This is a realy dirty solution, but for some mystytrious Reason the CSVWriter does not work when it is called from another funktion ?!
	private void exportCsv(def data, String fileName, def b) {
		// TODO move the query to the StatisticsController
		//def activityList = statsService.getActivitiesAndCompletionFrequencies(dataSource)
		def refactoredData = []
		for(row in data){
			refactoredData << [val1: row.get(0), val2: row.get(1)]
		}
		for(int i=0; i < refactoredData.size(); i++) {
			b << refactoredData[i]
		}

		byte[] bytes = b.writer.toString().bytes

		response.setContentType("text/csv")
		response.setHeader("Content-disposition", "filename=\""+fileName+".csv\"")
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
		def sortedData = rawData.sort{it.getDateCreated().getMillis()}
		def data = [];
		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd 0:00");
		for (PageView pageView : sortedData){
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
		def sortedData = rawData.sort{it.getDateCreated().getMillis()}
		def data = [];
		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd 0:00");
		DateTime dateOfFirstCompletedActivity = sortedData[0].getDateCreated()
		data = [[fmt.print(dateOfFirstCompletedActivity)+"AM", 0]]
		for(completedActivity in sortedData){
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
	
	def notAvailable() {
		
	}
}
