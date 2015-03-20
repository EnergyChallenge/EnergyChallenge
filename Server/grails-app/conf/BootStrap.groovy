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

			def userRole = new Role(name: "user")
			userRole.addToPermissions("*:*")
			userRole.save();
			Institute institute = new Institute(name: "none")
			institute.save()
			def user = new User(email:"user@example.com", passwordHash: new Sha256Hash("password").toHex(), firstName: "Max", lastName: "Mustermann", institute: institute)
			user.addToRoles(userRole)
			user.save()
			TestService.saveSomeExampleData()
			
			// initialization for productive use
			InitService.initKlikRoles()
			InitService.initKlikActivities()
			InitService.initCauInstitutions()
			TestService.createAndSaveExampleUsersForExistingInstitutes(10)
			TestService.createAndSaveCompletedActivitiesForExistingUsersAndActivities(20)
			
			println "Running in Development Mode"
		} else if (currentEnv == Environment.TEST) {
			// do custom init for test here

		} else if (currentEnv == Environment.PRODUCTION) {
			// do custom init for prod here
		}
	}
	def destroy = {
	}
}
