import de.unikiel.klik.model.Institute
import de.unikiel.klik.model.Role
import de.unikiel.klik.model.User
import org.apache.shiro.crypto.hash.Sha256Hash
class BootStrap {

    def init = { servletContext ->
		def TestService
		def userRole = new Role(name: "user")
		userRole.addToPermissions("*:*")
		userRole.save();
		Institute institute = new Institute(name: "none")
		institute.save()
		def user = new User(email:"user@example.com", passwordHash: new Sha256Hash("password").toHex(), firstName: "Max", lastName: "Mustermann", institute: institute)
        user.addToRoles(userRole)
		if(!user.save()) {
			println "saveFailed"
		}
		
    }
    def destroy = {
    }
}
