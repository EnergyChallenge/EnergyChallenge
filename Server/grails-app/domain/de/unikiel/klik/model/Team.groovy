package de.unikiel.klik.model

class Team extends Profile{
	
	String name
	
	static hasMany = [members: User]
	
    static constraints = {
		name(nullable: false, blank: false)
		members(nullable: false, minSize: 1)
    }
	
	int getPoints() {
		int sum = 0;
		for(member in members) {
			sum += member.getPoints();
		}
		return sum/members.size();
	}
	//TODO Refactor to getCompletedActivities
	def getCompletedActivitys(){
          def completedActivitys = []
          for(member in members){
            for(completedActivity in member.getCompletedActivities()){
              completedActivitys << [member: member, completedActivity: completedActivity]
            }
          }
		  return completedActivitys;
       }
}
