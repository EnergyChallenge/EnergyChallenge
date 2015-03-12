package de.unikiel.klik

import grails.converters.JSON

class AppController {

    def index() { 
		def test = [["Hallo"],["Welt"]];
		
		render test as JSON;
	}
	
	def getRankingTeams() {
		
		TimeZone reference = TimeZone.getTimeZone("GMT");
		Calendar myCal = Calendar.getInstance(reference);
		def currentTime = myCal.getTime();
		
		
		def team1 = [
			name : "Team 1",
			punkte : 2048];
		
		def team2 = [
			name : "Team 2",
			punkte : 2048];
		
		def team3 = [
			name : "Team 3",
			punkte : 512];
		
		def team4 = [
			name : "Team 4",
			punkte : 256];
		
		def team5 = [
			name : "CurrentTime Team - " + currentTime,
			punkte : 128];
		
		def ranking = [team1, team2, team3, team4, team5];
		
		def rankingRequest = [
			optionales : "Optional weitere Daten",
			ranking : ranking];
		
		render rankingRequest as JSON;
		
	}
}
