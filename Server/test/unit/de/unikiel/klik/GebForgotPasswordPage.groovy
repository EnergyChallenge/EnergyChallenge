package de.unikiel.klik

import geb.Page

class GebForgotPasswordPage extends Page {
	
	//define relative location
	static url = "auth/forgotPassword"
	static at = {title == "neues Passwort anfordern-EnergyChallenge"}
	
	static content = {
		forgotPasswordForm {$("g:form", action: "requestPassword")}
		requestPasswordButton(to: GebLoginPage) {
			forgotPasswordForm.find("input", type: "submit")
		}
	}
	
	void requestNewPassword(String email) {
		forgotPasswordForm.email = email
		requestPasswordButton.click()
	}

}
