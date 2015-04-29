package de.unikiel.klik.persistence

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
		if(members.size() != null){
			return sum/members.size()
		}else{
			return 0;
		}
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
