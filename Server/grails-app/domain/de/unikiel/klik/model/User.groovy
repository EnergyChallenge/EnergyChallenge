package de.unikiel.klik.model

class User extends Profile {

	private String email;
	private ArrayList<CompletedActivity> completedActivities;
	
    static constraints = {
    }
	
	public User(String name, String email) {
		this.name = name;
		this.email = email;
		this.completedActivities = new ArrayList<CompletedActivity>();
	}
	
	void completeAcitivityNow(Activity activity) {
		CompletedActivity completedActivity = new CompletedActivity(activity, new Date())
		this.completedActivities.add(completedActivity);
	}
	
	public ArrayList<CompletedActivity> getCompletedActivities() {
		return this.completedActivities;
	}
}
