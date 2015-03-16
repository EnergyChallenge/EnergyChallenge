package de.unikiel.klik.model

class User extends Profile {
	String email
    String passwordHash
    
    static hasMany = [ roles: Role, permissions: String, completedActivities: CompletedActivity ]

    static constraints = {
        email(nullable: false, blank: false, unique: true)
		completedActivities nullable: true
    }
	
	//Move
	def completeActivityNow(Activity activity) {
		def completedActivity = new CompletedActivity(activity: activity, date: new Date())
		this.addToCompletedActivities(completedActivity)
	}
	
	
	def int getPoints() {
		int sum = 0
		for(completedActivity in completedActivities) {
			sum += completedActivity.getActivity().getPoints()
		}
		return(sum)
	}
}
