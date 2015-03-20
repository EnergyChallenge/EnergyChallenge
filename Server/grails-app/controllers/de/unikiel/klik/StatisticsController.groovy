package de.unikiel.klik;

import de.unikiel.klik.model.*
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
	
    def index() { 
		def mostPopularActivities = getMostPopularActivities();
		def pageVisitsIndex = [1,2,3,4,5]	// TODO fix visitor counts 
		[mostPopularActivitys : mostPopularActivities as JSON, pageVisitsIndex: pageVisitsIndex as JSON];
	}

	def exportCsv() {
	
	// query
	groovy.sql.Sql sql = new groovy.sql.Sql(dataSource)
	log.info(sql)
	log.info("datasource " + dataSource)
	def results = sql.rows("SELECT a.description AS description, counts.n AS n\n" +
	"FROM activity a, (SELECT activity_id, count(*) AS n FROM completed_activity GROUP BY activity_id) counts\n" +
	"WHERE a.id = counts.activity_id\n" +
	"ORDER BY n DESC")
	sql.close()
	
	def sw = new StringWriter()
	def b = new CSVWriter(sw, {
		col1:"description" { it.DESCRIPTION }
		col2:"haeufigkeit" { it.N }
		})
	for(int i=0; i < results.size(); i++) {
		b << results[i]
	}

	byte[] bytes = b.writer.toString().bytes

	response.setContentType("text/csv")
	response.setHeader("Content-disposition", "filename=\"foo.csv\"")
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
		
		println result_list
		
		return result_list
		//return [["Haendewaschen mit kaltem Wasser",10],["Mit dem Fahrrad zur Uni fahren bis 3km",20],["Persönlichen CO2-Abdruck berechnen",15],["Treppe statt Fahrstuhl nutzen pro Tag",2],["Mit dem Fahrrad zur Uni fahren bis 5km",12],["Mit dem Fahrrad zur Uni fahren über 5km",7]]
	}
	
	/* TODO fix visit counts
  private def getVisitsOf(String url, int maxData){
    def rawData = PageView.findAll("from PageView as pageView where pageView.url=? order by pageView.dateCreated", [url], [max:maxData])
    def data = [];
    DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd h:ma");
    for (PageView pageView : rawData){
        data += [[fmt.print(pageView.getDateCreated()), pageView.getViews()]]
    }
    return data
  }
  */
}
