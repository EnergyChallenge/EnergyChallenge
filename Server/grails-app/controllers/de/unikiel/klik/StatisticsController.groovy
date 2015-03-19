package de.unikiel.klik;

import grails.converters.JSON;
import org.apache.shiro.subject.Subject
import org.apache.shiro.*
import grails.util.GrailsUtil
//import au.com.bytecode.opencsv.CSVWriter
import org.grails.plugins.csv.CSVWriter

class StatisticsController {

	def index() { 
		def mostPopularActivitys = [["Haendewaschen mit kaltem Wasser",10],["Mit dem Fahrrad zur Uni fahren bis 3km",20],["Persönlichen CO2-Abdruck berechnen",15],["Treppe statt Fahrstuhl nutzen pro Tag",2],["Mit dem Fahrrad zur Uni fahren bis 5km",12],["Mit dem Fahrrad zur Uni fahren über 5km",7]]
		def pageVisitsLast10Days = [50,300,500,700,1000,800,900,800,950,1042] 
		[mostPopularActivitys : mostPopularActivitys as JSON, pageVisitsLast10Days: pageVisitsLast10Days as JSON];
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
}
