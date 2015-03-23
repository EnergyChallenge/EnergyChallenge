package de.unikiel.klik

import de.unikiel.klik.model.TeamInvite
import de.unikiel.klik.model.User
import org.apache.shiro.SecurityUtils

class MessageController {

    def MessageService
    def index() {
        User user = User.findByEmail(SecurityUtils.subject.getPrincipal())
        def teamInvites = []
        def activityNotifications = []
        for (message in user.messages){
            if(TeamInvite.class.isInstance(message)){
                TeamInvite teamInvite = (TeamInvite) message
                teamInvites << teamInvite
            }else if(ActivityNotification.class.isInstance(message)){
                ActivityNotification activityNotification = (ActivityNotification) message
                ActivityNotifications << activityNotification
            }
        }
        [teamInvites: teamInvites, activityNotifications: activityNotifications]
    }
    def delete() {
      //TODO handle illegal delete trys
      if(!MessageService.deleteMessage(params.id as long, SecurityUtils.subject)){
        
      }
      redirect(action: "index")
    }
    //invites user to the Team of the current user
    def inviteUserToTeam() {
      MessageService.inviteUserToTeam(params.id as long, SecurityUtils.subject)
      redirect(action: "index")
    }

}
