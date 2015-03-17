package de.unikiel.klik.control.app

class AdminController {

    def AdminService

    def listUsers() {

        //Display all users 10 to a page
        def users = User.findAll("from User as p order by p.lastName", [max: 10, offset: 10 * params.page])
        int lastPage = User.count() / 10;
        [users: users, page: params.page, lastPage: lastPage]
    }

    def listTeams() {

        //Display all teams 10 to a page
        def teams = Team.findAll("from Team as p order by p.name", [max: 10, offset: 10 * params.page])
        int lastPage = Team.count() / 10;
        [teams: teams, page: params.page, lastPage: lastPage]
    }

    def listProposals() {

        //Display all proposals 10 to a page
        def proposals = Proposal.findAll("from Proposal as p order by p.dateCreated", [max: 10, offset: 10 * params.page])
        int lastPage = Proposal.count() / 10;
        [proposals: proposals, page: params.page, lastPage: lastPage]
    }

    def listActivities() {

        //Display all activities 10 to a page
        def activities = Activity.findAll("from Activity as p order by p.dateCreated", [max: 10, offset: 10 * params.page])
        int lastPage = Activity.count() / 10;
        [activities: activities, page: params.page, lastPage: lastPage]
    }

    def editActivity() {

        //Get the admin service to edit an activity
        AdminService.editActivity(params.description, params.points, params.duration, params.id)
        redirect(action = "admin")
    }

    def editProposal() {

        //Get the admin service to edit a proposal
        AdminService.editProposal(params.description, params.points)
        redirect(action = "admin")
    }

    def editUser() {

        //Get the admin service to edit a user
        AdminService.editUser(params.title, params.firstName, params.lastName, params.team, params.institute)
        redirect(action = "admin")
    }

    def editTeam() {

        //Get the admin service to edit a team
        AdminService.editTeam(params.name, params.members)
        redirect(action = "admin")
    }

    def blockUser() {

        //Get the admin service to block a user
        AdminService.blockUser(params.userId)
        redirect(action = "admin")
    }

    def blockTeam() {

        //Get the admin service to block a team
        AdminService.blockTeam(params.teamId)
        redirect(action = "admin")
    }

    def unblockUser() {

        //Get the admin service to unblock a user
        AdminService.unblockUser(params.userId)
        redirect(action = "admin")
    }

    def unblockTeam() {

        //Get the admin service to unblock a team
        AdminService.unblockTeams(params.userId)
        redirect(action = "admin")
    }

    def deleteUser() {

        //Get the admin service to remove a user
        AdminService.unblockUser(params.userId)
        redirect(action = "admin")
    }

    def deleteTeam() {

        //Get the admin service to remove a team
        AdminService.unblockTeams(params.userId)
        redirect(action = "admin")
    }

}