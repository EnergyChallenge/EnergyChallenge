package de.unikiel.klik;
import grails.converters.JSON;

class StatisticsController {

    def index() { 
		def mostPopularActivitys = [["Haendewaschen mit kaltem Wasser",10],["Mit dem Fahrrad zur Uni fahren bis 3km",20],["Persönlichen CO2-Abdruck berechnen",15],["Treppe statt Fahrstuhl nutzen pro Tag",2],["Mit dem Fahrrad zur Uni fahren bis 5km",12],["Mit dem Fahrrad zur Uni fahren über 5km",7]] as grails.converters.JSON;
		def pageVisitsLast10Days = [50,300,500,700,1000,800,900,800,950,1042] as grails.converters.JSON;
		[mostPopularActivitys : mostPopularActivitys, pageVisitsLast10Days: pageVisitsLast10Days];
	}
}
