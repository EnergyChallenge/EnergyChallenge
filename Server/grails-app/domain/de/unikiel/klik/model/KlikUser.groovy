package de.unikiel.klik.model

class KlikUser extends Profile {

	//Team team;
	//String email; // is in ShiroUser
	static hasMany = [completedActivities: CompletedActivity]
	
	
    static constraints = {
	//email nullable: false
	completedActivities nullable: true 
	//team nullabel: true
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
