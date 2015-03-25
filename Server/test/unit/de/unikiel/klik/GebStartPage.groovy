package de.unikiel.klik

import geb.Page

class GebStartPage extends Page{
	
	//define relative location
	static url = ""
	//at check allows to verify that the loaded page is correct
	static at = { title == "EnergyChallenge" }
	
	static content = {
		loginForm {$("g:form", action: "signIn")}
		loginButton(to: GebLandingPage) {
			loginForm.find("input", type: "submit")
		}
	}
	
	//perform a login
	void login(String email, String password, Boolean rememberMe) {
		loginForm.email = email
		loginForm.password = password
		loginForm.rememberMe = rememberMe
		loginButton.click()
	}
}