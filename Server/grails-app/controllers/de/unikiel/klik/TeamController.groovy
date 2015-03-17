package de.unikiel.klik

class TeamController {

    def index() {

    }

    def edit() {

        //Get the team service to edit the team
        //TODO can anything beside name be changed?
        TeamService.setName(params.name, params.teamId)
        redirect(action = "team")
    }

    def uploadAvatar() {

        //Get the team service to set the avatar
        //TODO implement avatar upload
        TeamService.setAvatar(params.avatar, params.teamId)
        redirect(action = "team")
    }

}
