package de.unikiel.klik;

import de.unikiel.klik.model.PageView;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import grails.converters.JSON;
import org.apache.shiro.subject.Subject
import org.apache.shiro.*
class StatisticsController {

    def index() { 
		def mostPopularActivitys = getMostPopularActivitys();
		def pageVisitsIndex = getVisitsOf("/",10)  
		[mostPopularActivitys : mostPopularActivitys as JSON, pageVisitsIndex: pageVisitsIndex as JSON];
	}
	def test() {
		[userName: org.apache.shiro.SecurityUtils.getSubject().getPrincipal()]
	}
  private def getMostPopularActivitys(){
    return null
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
