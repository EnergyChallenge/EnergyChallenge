import de.unikiel.klik.model.Role
import de.unikiel.klik.model.User
import org.apache.shiro.crypto.hash.Sha256Hash
class BootStrap {

    def init = { servletContext ->
		def userRole = new Role(name: "user")
		userRole.addToPermissions("*:*")
		userRole.save();
		def user = new User(email: "user", passwordHash: new Sha256Hash("password").toHex(), name: "Max Mustermann")
        user.addToRoles(userRole)
		user.save()
    }
    def destroy = {
    }
}
