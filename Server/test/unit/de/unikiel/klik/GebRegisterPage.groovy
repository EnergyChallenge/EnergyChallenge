package de.unikiel.klik

import geb.Page

class GebRegisterPage extends Page {
	
	//define relative location
	static url = "auth/register"
	static at = {title == "Registrierung-EnergyChallenge"}
	
	static content = {
		registerForm {$("g:form", action: "signUp")}
		registerButton(to: GebLandingPage) {
			registerForm.find("input", type: "submit")
		}
	}
	
	//perform a registration
	void register(String email, String firstName, String lastName, String password, String password2, int instituteId) {
		registerForm.email = email
		registerForm.firstName = firstName
		registerForm.lastName = lastName
		registerForm.password = password
		registerForm.password2 = password2
		registerForm.instituteId = instituteId
		registerButton.click()
	}

}
