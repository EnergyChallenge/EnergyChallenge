import de.unikiel.klik.model.ShiroRole
import de.unikiel.klik.model.ShiroUser
import org.apache.shiro.crypto.hash.Sha256Hash
class BootStrap {

    def init = { servletContext ->
		def userRole = new ShiroRole(name: "user")
		userRole.addToPermissions("*:*")
		userRole.save();
		def user = new ShiroUser(username: "user", passwordHash: new Sha256Hash("password").toHex(), name: "Max Mustermann")
        user.addToRoles(userRole)
		user.save()
    }
    def destroy = {
    }
}
