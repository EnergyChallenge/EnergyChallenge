package de.unikiel.klik

class ProfilController {
	
    def index() {
		render "Mein Profil"
	}
	def viewProfile() {
		render (view: 'viewProfile.gsp')
}
}
