package de.unikiel.klik.model

class User extends Profile {

	// credentials
    String email
    String passwordHash
	
	// affiliation
	Team team
	Institute institute
    
	// other
	String firstName
	String lastName
	String title	// academic title
	Boolean emailNotification = false

	static hasMany = [
		roles: Role, 
		permissions: String,
		completedActivities: CompletedActivity,
		favorites: Activity,
		messages: Message
	]

    static constraints = {
        email(nullable: false, email: true, unique: true)
		completedActivities(nullable: true)
		title(nullable: true)
		firstName(nullable: false, blank: false)
		lastName(nullable: false, blank: false)
		team(nullable: true)
		roles(nullable: false, minSize: 1)
		institute(nullable: false)
		favorites(nullable: true)
		emailNotification(defaultValue: "false") // TODO test if false is the default value
    }
	
	//TODO works??
	def String getName() {
		return title + firstName + lastName;
	}
	
	// Move to 
	def completeActivityNow(Activity activity) {
		def completedActivity = new CompletedActivity(activity: activity, date: new Date())
		this.addToCompletedActivities(completedActivity)
	}
	
	// TODO look for the way to implement this
	def int getPoints() {
		int sum = 0
		for(completedActivity in completedActivities) {
			sum += completedActivity.getActivity().getPoints()
		}
		return(sum)
	}
}
