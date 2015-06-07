package de.unikiel.klik.persistence

class User extends Profile {

	// credentials
    String email
    String passwordHash
	
	// affiliation
	Team team
	Institute institute
	int pointsCollectedForTeam
	int cachedPoints
	int cachedRankingPosition
    
	// other
	String firstName
	String lastName
	String title		// academic title
	String searchName	// full name (incl. academic title) for convenient searching
	String passwordRequestToken
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
        searchName(nullable: false)
		completedActivities(nullable: true)
		title(nullable: true)
		firstName(nullable: false, blank: false)
		lastName(nullable: false, blank: false)
		team(nullable: true)
		roles(nullable: false, minSize: 1)
		institute(nullable: false)
		pointsCollectedForTeam(defaultValue: 0)
		favorites(nullable: true)
		emailNotification(defaultValue: "false") // TODO test if false is the default value
		passwordRequestToken(nullable: true, blank: true)
    }
	
	// create searchName before insert
	def beforeInsert() {
		searchName = createSearchName() //firstName + ' ' + lastName
	}
	
	// keep searchName up to date
	def beforeUpdate() {
		searchName = createSearchName() //firstName + ' ' + lastName
	}
	
	// create searchName before validation
	def beforeValidate() {
		searchName = createSearchName() //firstName + ' ' + lastName
	}
	
	def String getName() {
		String name = firstName + " " + lastName;
		if (title != null) {
			name = title + " " + name;
		}
		return name;
	}
	
	// returns a full name from academic title, firstName and lastName
	String createSearchName() {
		String name = firstName + " " + lastName;
		if (title != null) {
			name = title + " " + name;
		}
		return name;
	}
	
	// Move to 
	def completeActivityNow(Activity activity) {
		def completedActivity = new CompletedActivity(activity: activity, date: new Date())
		this.addToCompletedActivities(completedActivity)
	}


	def int getPoints() {
		calculatePoints() //Remove later
		return cachedPoints;
	}
	
	def calculatePoints() {
		int sum = 0;
		for(completedActivity in completedActivities) {
			sum += completedActivity.getActivity().getPoints();
		}
		cachedPoints = sum;
		save(flush: true);
	}

	def void attemptToAddToTeamContribution(int points) {
		if(team != null){
			pointsCollectedForTeam += points
		}
	}

}
