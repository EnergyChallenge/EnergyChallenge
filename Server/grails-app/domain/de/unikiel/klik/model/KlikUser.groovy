package de.unikiel.klik.model

class KlikUser extends Profile {

	//String email; // is in ShiroUser
	static hasMany = [completedActivities: CompletedActivity]
	static hasOne = [team: Team]
	
    static constraints = {
		//email nullable: false
		completedActivities nullable: true 
    }
		
	def completeAcitivityNow(Activity activity) {
		def completedActivity = new CompletedActivity(activity: activity, date: new Date())
		this.addToCompletedActivities(completedActivity)
	}
	
}
