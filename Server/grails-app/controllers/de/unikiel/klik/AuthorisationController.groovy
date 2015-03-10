package de.unikiel.klik

class AuthorisationController {

    def index() { }
	def signIn() {
		render "TODO";
	}
	def signOut() {
		flash.message = "TODO";
		redirect(view:"/index");
	}
	def forgotPassword() {
		render "TODO";
	}
	def register() {
		render "TODO";
	}
}
