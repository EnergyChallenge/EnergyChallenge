package de.unikiel.klik.model

import de.unikiel.klik.model.User
import de.unikiel.klik.model.Institute
import de.unikiel.klik.model.Role

import grails.transaction.Transactional
import org.apache.shiro.SecurityUtils
import org.apache.shiro.authc.AuthenticationException
import org.apache.shiro.authc.UsernamePasswordToken
import org.apache.shiro.crypto.hash.Sha256Hash
import org.apache.shiro.subject.Subject

/**
 * Generated by the Shiro plugin. This filters class protects all URLs
 * via access control by convention.
 */
class ShiroSecurityFilters {
    def filters = {
        all(uri: "/**") {
            before = {
                // Ignore direct views (e.g. the default main index page).
                if (!controllerName) return true
				if (controllerName == "assets") return true
				if (controllerName == "app") return true
				if (controllerName == "startpage") return true
				if (controllerName == "statistics") return true

                // Stop regular users accessing the admin area
                if (controllerName == "admin"){
                    def currentUser = User.findByEmail(SecurityUtils.getSubject().getPrincipal())
                    def currentRoles = currentUser.getRoles()
                    def currentRole = currentRoles[0]
                    if(currentRole.name == "admin"){
                        return true
                    }else{
                        return false
                    }
                }

                // Access control by convention.
                accessControl()
            }
        }
    }
}
