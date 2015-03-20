package de.unikiel.klik;

import de.unikiel.klik.model.PageView;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import grails.converters.JSON;
import org.apache.shiro.subject.Subject
import org.apache.shiro.*
import grails.util.GrailsUtil
//import au.com.bytecode.opencsv.CSVWriter
import org.grails.plugins.csv.CSVWriter

class StatisticsController {

    def index() { 
		def mostPopularActivitys = getMostPopularActivitys();
		def pageVisitsIndex = getVisitsOf("/",10)  
		[mostPopularActivitys : mostPopularActivitys as JSON, pageVisitsIndex: pageVisitsIndex as JSON];
	}

	def exportCsv() {
	
	def sw = new StringWriter()
	def b = new CSVWriter(sw, {
		col1 { it.val1 }
		col2 { it.val2 }
		})
	b << [val1: 'a', val2: 'b']
	b << [val1: 'c', val2: 'd']

	byte[] bytes = b.writer.toString().bytes

	response.setContentType("text/csv")
	response.setHeader("Content-disposition", "filename=\"foo.csv\"")
	response.setContentLength(bytes.size())
	response.outputStream << bytes
	
	render(view: "index")
	}

  private def getMostPopularActivitys(){
    return [["Haendewaschen mit kaltem Wasser",10],["Mit dem Fahrrad zur Uni fahren bis 3km",20],["Persönlichen CO2-Abdruck berechnen",15],["Treppe statt Fahrstuhl nutzen pro Tag",2],["Mit dem Fahrrad zur Uni fahren bis 5km",12],["Mit dem Fahrrad zur Uni fahren über 5km",7]]
  }
  private def getVisitsOf(String url, int maxData){
    def rawData = PageView.findAll("from PageView as pageView where pageView.url=? order by pageView.dateCreated", [url], [max:maxData])
    def data = [];
    DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd h:ma");
    for (PageView pageView : rawData){
        data += [[fmt.print(pageView.getDateCreated()), pageView.getViews()]]
    }
    return data
  }
}
