import de.unikiel.klik.service.TestService
import de.unikiel.klik.service.InitService
import de.unikiel.klik.persistence.Institute
import de.unikiel.klik.persistence.Role
import de.unikiel.klik.persistence.Team
import de.unikiel.klik.persistence.User
import org.apache.shiro.crypto.hash.Sha256Hash
import grails.util.Environment


class BootStrap {

	def init = { servletContext ->
		def currentEnv = Environment.current
		//define the user and admin role
	    	def userRole = new Role(name: "user")
	    	userRole.addToPermissions("*:*")
	    	userRole.save(flush: true, failOnError: true);
            	def adminRole = new Role(name: "admin")
            	adminRole.addToPermissions("*:*")
            	adminRole.save(flush: true, failOnError: true);

		// initialization for productive use
		InitService.initKlikRoles()
		InitService.initKlikActivities()
		InitService.initCauInstitutions()
		if (currentEnv == Environment.DEVELOPMENT) {

			// generate test data
			Institute institute = new Institute(name: "development")
			institute.save()
			def user = new User(email:"user@example.com", passwordHash: new Sha256Hash("password").toHex(), firstName: "Max", lastName: "Mustermann", institute: institute)
			user.addToRoles(userRole)
			user.save(flush: true)
			def admin = new User(email:"admin@example.com", passwordHash: new Sha256Hash("password").toHex(), firstName: "Matilda", lastName: "Mustermann", institute: institute)
			admin.addToRoles(adminRole)
			admin.save(flush: true)

			//TestService.saveSomeExampleData()
			
			TestService.createAndSaveExampleUsersForExistingInstitutes(128)
			TestService.createAndSaveCompletedActivitiesForExistingUsersAndActivities(1024)
			TestService.createSomePageVisits(7,"/index")	
			TestService.createSomePageVisits(7,"/auth/signIn")	
			println "Running in Development Mode"
		} else if (currentEnv == Environment.TEST) {
			// do custom init for test here
			Institute institute = new Institute(name: "test")
			institute.save()
			def user = new User(email:"user@example.com", passwordHash: new Sha256Hash("password").toHex(), firstName: "Max", lastName: "Mustermann", institute: institute)
			user.addToRoles(userRole)
			user.save(flush: true)
			def admin = new User(email:"admin@example.com", passwordHash: new Sha256Hash("password").toHex(), firstName: "Matilda", lastName: "Mustermann", institute: institute)
			admin.addToRoles(adminRole)
			admin.save(flush: true)

		} else if (currentEnv == Environment.PRODUCTION) {
			// generate admin Accounts
			Institute institute = Institute.get(0) //TODO find the right Institute
			institute.save()
			def admin = new User(email:"admin@example.com", passwordHash: new Sha256Hash("password").toHex(), firstName: "Matilda", lastName: "Mustermann", institute: institute)
			admin.addToRoles(adminRole)
			admin.save(flush: true)
			
		}
	}
	def destroy = {
	}
}
