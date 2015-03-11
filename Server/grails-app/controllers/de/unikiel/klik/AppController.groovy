package de.unikiel.klik

import grails.converters.JSON

class AppController {

    def index() { 
		def test = [["Hallo"],["Welt"]];
		
		render test  as JSON;
	}
}
