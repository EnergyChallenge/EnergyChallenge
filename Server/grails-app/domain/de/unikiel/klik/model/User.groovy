package de.unikiel.klik.model

class User extends Profile {

	String name
	String email
	ArrayList<CompletedActivity> completedActivities
	
    static constraints = {
    }
	
	def completeAcitivty(Activity activity) {
		this.completedActivties.add(activity)
	}
}
