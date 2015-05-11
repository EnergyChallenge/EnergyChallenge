package de.unikiel.klik.control.web

import de.unikiel.klik.persistence.TeamInvite
import de.unikiel.klik.persistence.ActivityNotification
import de.unikiel.klik.persistence.User

import de.unikiel.klik.service.MessageService

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
                activityNotifications << activityNotification
            }
        }
        [teamInvites: teamInvites, activityNotifications: activityNotifications]
    }
    def delete() {
      if(!MessageService.deleteMessage(params.id as long, SecurityUtils.subject)){
        
      }
      redirect(action: "index")
    }
    //invites user to the Team of the current user
    def inviteUserToTeam() {
      MessageService.inviteUserToTeam(params.id as long, SecurityUtils.subject)

      def invitee = User.get(params.id as long);
      flash.message = invitee.getName() + " hat eine Einladung in Ihr Team erhalten."
      def targetUri = params.targetUri ?: "/"
      redirect(uri: targetUri)
    }

}
