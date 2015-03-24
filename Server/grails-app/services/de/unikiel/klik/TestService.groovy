package de.unikiel.klik

import de.unikiel.klik.model.*
import grails.transaction.Transactional
import org.apache.shiro.crypto.hash.Sha256Hash
import org.joda.time.Duration
import org.joda.time.DateTime
import java.util.Random

@Transactional
class TestService {

	static Random rand = new Random()
	
	static Activity getExampleActivity() {
		return getExampleActivity("Example")
	}
	static Activity getExampleActivity(String name) {
		return new Activity(description: name, points: 1, duration: new Duration(60*60*1000))
	}
	static Institute getExampleInstitute() {
		Institute institute
		if(Institute && Institute.findByName("Example")) {
			institute = Institute.findByName("Example")
		}else {
		institute = new Institute(name: "Example")
		}
		
		return institute
	}
	static Proposal getExampleProposal() {
		return getExampleProposal("Example")
	}
	static Proposal getExampleProposal(String name) {
		return new Proposal(description: name, points: 1, author: getExampleUser("ProposalAuthor"))
	}
	static Team getExampleTeam() {
		return getExampleTeam("Example")
	}
	static Team getExampleTeam(String name) {
		Team team =  new Team(name: name)
		team.addToMembers(getExampleUser(name+"Member1"))
		team.addToMembers(getExampleUser(name+"Member2"))
                return team
	}
	static Role getExampleRole() {
		def role
		if(Role && Role.findByName("user")) {
			role = Role.findByName("user")
		}
		else {
			role = new Role(name: "user")
			role.addToPermissions("*:*")
		}

		return role
	}
	static User getExampleUser() {
		return getExampleUser(getFirstName(User.count()),getLastName(User.count()) )
	}
	static User getExampleUser(String name) {
		return getExampleUser(name, "Example")
	}
	static User getExampleUser(String firstName, String lastName) {
		return new User(email:firstName.toLowerCase()+"@example.com", passwordHash: new Sha256Hash("password").toHex(), roles: [getExampleRole()], firstname: firstName, lastname: lastName, institute: getExampleInstitute())
	}
	static void saveSomeExampleData(){
		getExampleInstitute().save()
		//TODO FIX
		getExampleUser("User1").save()
		getExampleUser("User2").save()
		//TODO FIX
		//getExampleTeam("Team1").save()
		//getExampleTeam("Team2").save()
		//getExampleTeam("Team3").save()
		//getExampleActivity("Activity1").save()
		//getExampleActivity("Activity2").save()
		//TODO Example Proposal!	
	}
	
	// needs predefined institutes
	static void createAndSaveExampleUsersForExistingInstitutes(int nrOfUsers) {
		def user_role = Role.findOrSaveByName("user")
		int nrOfInstitutes = Institute.count()
		int userCount = User.count()	
		for(int i=userCount; i <= userCount+nrOfUsers; i++) {
			new User(email: getFirstName(i) +"@example.com", 
				passwordHash: new Sha256Hash("password").toHex(), 
				roles: [user_role], 
				firstName: getFirstName(i), 
				lastName: getLastName(i), 
				institute: Institute.get(rand.nextInt(nrOfInstitutes) + 1)	// get a random Institute
				).save(flush: true)
		}
		if(User.count() > 50){
			def users = User.findAll()
			int i = 0;
			Team team1 =  new Team(name: "Peperoni Pizza")
			Team team2 =  new Team(name: "Salat")
			Team team3 =  new Team(name: "Nudel Suppe")
			Team team4 =  new Team(name: "Toast Brot")
			Team team5 =  new Team(name: "Schokladen Eis mit Baylies")
			for(;i<User.count()/5;i++){//
				team1.addToMembers(users[5*i+0])
				team2.addToMembers(users[5*i+1])
				team3.addToMembers(users[5*i+2])
				team4.addToMembers(users[5*i+3])
				team5.addToMembers(users[5*i+4])
			}
			team1.save()
			team2.save()
			team3.save()
			team4.save()
			team5.save()
		}
	}
	
	// needs predefined users and activities
	static void createAndSaveCompletedActivitiesForExistingUsersAndActivities(int nrOfCompletedActivities) {
		int nrOfUsers = User.count()
		int nrOfActivities = Activity.count()
		//User user
		//Activity activity
		//CompletedActivity completedActivity
		def users = User.findAll()
		DateTime today = new DateTime()
		for(int i=1; i <= nrOfCompletedActivities; i++) {
			//random Date in the passt week
			DateTime date = today.minus(new Duration(((long)rand.nextInt(7*24*60*60))*1000L))
			def user = users[rand.nextInt(nrOfUsers)]		// get a random User
			def activity = Activity.get(rand.nextInt(nrOfActivities) + 1)	// get a random Activity
			def completedActivity = new CompletedActivity(activity: activity)
			completedActivity.save(flush: true, failOnError: true)
			completedActivity.dateCreated = date
			completedActivity.save(flush: true, failOnError: true)
			user.addToCompletedActivities(completedActivity)
			user.save(flush: true, failOnError: true)
		}
	}
	static void createSomePageVisits(int numberOfDaysBack, String url){
		DateTime today = new DateTime()
		for (long i = 0; i < numberOfDaysBack; i++){
			DateTime date = today.minus(new Duration(i*24L*60L*60L*1000L))
			PageView pageView =  new PageView(url: url)
			pageView.save(flush: true, failOnError: true)
			pageView.dateCreated = date
			pageView.views = rand.nextInt(50)
			pageView.save(flush: true, failOnError: true)
		}
	}
	static String getLastName(int i){
		def lastNames = ["Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor", "Anderson", "Thomas", "Jackson", "White", "Harris", "Martin", "Thompson", "Garcia", "Martinez", "Robinson", "Clark", "Rodriguez", "Lewis", "Lee", "Walker", "Hall", "Allen", "Young", "Hernandez", "King", "Wright", "Lopez", "Hill", "Scott", "Green", "Adams", "Baker", "Gonzalez", "Nelson", "Carter", "Mitchell", "Perez", "Roberts", "Turner", "Phillips", "Campbell", "Parker", "Evans", "Edwards", "Collins", "Stewart", "Sanchez", "Morris", "Rogers", "Reed", "Cook", "Morgan", "Bell", "Murphy", "Bailey", "Rivera", "Cooper", "Richardson", "Cox", "Howard", "Ward", "Torres", "Peterson", "Gray", "Ramirez", "James", "Watson", "Brooks", "Kelly", "Sanders", "Price", "Bennett", "Wood", "Barnes", "Ross", "Henderson", "Coleman", "Jenkins", "Perry", "Powell", "Long", "Patterson", "Hughes", "Flores", "Washington", "Butler", "Simmons", "Foster", "Gonzales", "Bryant", "Alexander", "Russell", "Griffin", "Diaz", "Hayes", "Myers", "Ford", "Hamilton", "Graham", "Sullivan", "Wallace", "Woods", "Cole", "West", "Jordan", "Owens", "Reynolds", "Fisher", "Ellis", "Harrison", "Gibson", "Mcdonald", "Cruz", "Marshall", "Ortiz", "Gomez", "Murray", "Freeman", "Wells", "Webb", "Simpson", "Stevens", "Tucker", "Porter", "Hunter", "Hicks", "Crawford", "Henry", "Boyd", "Mason", "Morales", "Kennedy", "Warren", "Dixon", "Ramos", "Reyes", "Burns", "Gordon", "Shaw", "Holmes", "Rice", "Robertson", "Hunt", "Black", "Daniels", "Palmer", "Mills", "Nichols", "Grant", "Knight", "Ferguson", "Rose", "Stone", "Hawkins", "Dunn", "Perkins", "Hudson", "Spencer", "Gardner", "Stephens", "Payne", "Pierce", "Berry", "Matthews", "Arnold", "Wagner", "Willis", "Ray", "Watkins", "Olson", "Carroll", "Duncan", "Snyder", "Hart", "Cunningham", "Bradley", "Lane", "Andrews", "Ruiz", "Harper", "Fox", "Riley", "Armstrong", "Carpenter", "Weaver", "Greene", "Lawrence", "Elliott", "Chavez", "Sims", "Austin", "Peters", "Kelley", "Franklin", "Lawson", "Fields", "Gutierrez", "Ryan", "Schmidt", "Carr", "Vasquez", "Castillo", "Wheeler", "Chapman", "Oliver", "Montgomery", "Richards", "Williamson", "Johnston", "Banks", "Meyer", "Bishop", "Mccoy", "Howell", "Alvarez", "Morrison", "Hansen", "Fernandez", "Garza", "Harvey", "Little", "Burton", "Stanley", "Nguyen", "George", "Jacobs", "Reid", "Kim", "Fuller", "Lynch", "Dean", "Gilbert", "Garrett", "Romero", "Welch", "Larson", "Frazier", "Burke", "Hanson", "Day", "Mendoza", "Moreno", "Bowman", "Medina", "Fowler", "Brewer", "Hoffman", "Carlson", "Silva", "Pearson", "Holland", "Douglas", "Fleming", "Jensen", "Vargas", "Byrd", "Davidson", "Hopkins", "May", "Terry", "Herrera", "Wade", "Soto", "Walters", "Curtis", "Neal", "Caldwell", "Lowe", "Jennings", "Barnett", "Graves", "Jimenez", "Horton", "Shelton", "Barrett", "Obrien", "Castro", "Sutton", "Gregory", "Mckinney", "Lucas", "Miles", "Craig", "Rodriquez", "Chambers", "Holt", "Lambert", "Fletcher", "Watts", "BATES"]
		return lastNames[i%lastNames.size()]
	}
	static String getFirstName(int i){
		def firstNames = ["James", "John", "Robert", "Michael", "Mary", "William", "David", "Richard", "Charles", "Joseph", "Thomas", "Patricia", "Christopher", "Linda", "Barbara", "Daniel", "Paul", "Mark", "Elizabeth", "Donald", "Jennifer", "George", "Maria", "Kenneth", "Susan", "Steven", "Edward", "Margaret", "Brian", "Ronald", "Dorothy", "Anthony", "Lisa", "Kevin", "Nancy", "Karen", "Betty", "Helen", "Jason", "Matthew", "Gary", "Timothy", "Sandra", "Jose", "Larry", "Jeffrey", "Frank", "Donna", "Carol", "Ruth", "Scott", "Eric", "Stephen", "Andrew", "Sharon", "Michelle", "Laura", "Sarah", "Kimberly", "Deborah", "Jessica", "Raymond", "Shirley", "Cynthia", "Angela", "Melissa", "Brenda", "Amy", "Jerry", "Gregory", "Anna", "Joshua", "Virginia", "Rebecca", "Kathleen", "Dennis", "Pamela", "Martha", "Debra", "Amanda", "Walter", "Stephanie", "Willie", "Patrick", "Terry", "Carolyn", "Peter", "Christine", "Marie", "Janet", "Frances", "Catherine", "Harold", "Henry", "Douglas", "Joyce", "Ann", "Diane", "Alice", "Jean", "Julie", "Carl", "Kelly", "Heather", "Arthur", "Teresa", "Gloria", "Doris", "Ryan", "Joe", "Roger", "Evelyn", "Juan", "Ashley", "Jack", "Cheryl", "Albert", "Joan", "Mildred", "Katherine", "Justin", "Jonathan", "Gerald", "Keith", "Samuel", "Judith", "Rose", "Janice", "Lawrence", "Ralph", "Nicole", "Judy", "Nicholas", "Christina", "Roy", "Kathy", "Theresa", "Benjamin", "Beverly", "Denise", "Bruce", "Brandon", "Adam", "Tammy", "Irene", "Fred", "Billy", "Harry", "Jane", "Wayne", "Louis", "Lori", "Steve", "Tracy", "Jeremy", "Rachel", "Andrea", "Aaron", "Marilyn", "Robin", "Randy", "Leslie", "Kathryn", "Eugene", "Bobby", "Howard", "Carlos", "Sara", "Louise", "Jacqueline", "Anne", "Wanda", "Russell", "Shawn", "Victor", "Julia", "Bonnie", "Ruby", "Chris", "Tina", "Lois", "Phyllis", "Jamie", "Norma", "Martin", "Paula", "Jesse", "Diana", "Annie", "Shannon", "Ernest", "Todd", "Phillip", "Lee", "Lillian", "Peggy", "Emily", "Crystal", "Kim", "Craig", "Carmen", "Gladys", "Connie", "Rita", "Alan", "Dawn", "Florence", "Dale", "Sean", "Francis", "Johnny", "Clarence", "Philip", "Edna", "Tiffany", "Tony", "Rosa", "Jimmy", "Earl", "Cindy", "Antonio", "Luis", "Mike", "Danny", "Bryan", "Grace", "Stanley", "Leonard", "Wendy", "Nathan", "Manuel", "Curtis", "Victoria", "Rodney", "Norman", "Edith", "Sherry", "Sylvia", "Josephine", "Allen", "Thelma", "Sheila", "Ethel", "Marjorie", "Lynn", "Ellen", "Elaine", "Marvin", "Carrie", "Marion", "Charlotte", "Vincent", "Glenn", "Travis", "Monica", "Jeffery", "Jeff", "Esther", "Pauline", "Jacob", "Emma", "Chad", "Kyle", "Juanita", "Dana", "Melvin", "Jessie", "Rhonda", "Anita", "Alfred", "Hazel", "Amber", "Eva", "Bradley", "Ray", "Jesus", "Debbie", "Herbert", "Eddie", "Joel", "Frederick", "April", "Lucille", "Clara", "Gail", "Joanne", "Eleanor", "Valerie", "Danielle", "Erin", "Edwin", "Megan", "Alicia", "Suzanne", "Michele", "Don", "Bertha", "Veronica", "JILL"]
		return firstNames[i%firstNames.size()]
	}
}
