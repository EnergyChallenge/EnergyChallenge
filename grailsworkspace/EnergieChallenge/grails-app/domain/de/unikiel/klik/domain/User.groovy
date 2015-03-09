package de.unikiel.klik.domain

class User {
    String username
    String passwordHash
    
    static hasMany = [ roles: Role, permissions: String, Activitys: Activity];

    static constraints = {
        username(nullable: false, blank: false, unique: true)
    }
}
