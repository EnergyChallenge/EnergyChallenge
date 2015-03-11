package de.unikiel.klik.model

class KlikUser extends Profile {

	//String email; // is in ShiroUser
	static hasMany = [completedActivities: CompletedActivity]
	static hasOne = [team: Team]
	
    static constraints = {
		completedActivities(nullable: true) 
    }
	
	
	def completeActivityNow(Activity activity) {
		def completedActivity = new CompletedActivity(activity: activity, date: new Date())
		this.addToCompletedActivities(completedActivity)
	}
	
	
	def int countPoints() {
		int sum = 0
		for(completedActivity in completedActivities) {
			sum += completedActivity.getActivity().getPoints()
		}
		return(sum)
	}
}
