import de.unikiel.klik.TestService
import de.unikiel.klik.InitService
import de.unikiel.klik.model.Institute
import de.unikiel.klik.model.Role
import de.unikiel.klik.model.Team
import de.unikiel.klik.model.User
import org.apache.shiro.crypto.hash.Sha256Hash
import grails.util.Environment


class BootStrap {

	def init = { servletContext ->
		def currentEnv = Environment.current

		if (currentEnv == Environment.DEVELOPMENT) {

			// generate test data
			def userRole = new Role(name: "user")
			userRole.addToPermissions("*:*")
			userRole.save();
            def adminRole = new Role(name: "admin")
            adminRole.addToPermissions("*:*")
            adminRole.save();
			Institute institute = new Institute(name: "none")
			institute.save()
			def user = new User(email:"user@example.com", passwordHash: new Sha256Hash("password").toHex(), firstName: "Max", lastName: "Mustermann", institute: institute)
			user.addToRoles(userRole)
			user.save(flush: true)
			//TestService.saveSomeExampleData()
			
			// initialization for productive use
			InitService.initKlikRoles()
			InitService.initKlikActivities()
			InitService.initCauInstitutions()
			TestService.createAndSaveExampleUsersForExistingInstitutes(128)
			TestService.createAndSaveCompletedActivitiesForExistingUsersAndActivities(1024)
			TestService.createSomePageVisits(7,"/index")	
			TestService.createSomePageVisits(7,"/auth/signIn")
            TestService.createMessage()
			println "Running in Development Mode"
		} else if (currentEnv == Environment.TEST) {
			// do custom init for test here

		} else if (currentEnv == Environment.PRODUCTION) {
			// generate admin Accounts
			
		}
	}
	def destroy = {
	}
}
