package de.unikiel.klik.service

import de.unikiel.klik.persistence.Activity
import de.unikiel.klik.persistence.ActivityNotification
import de.unikiel.klik.persistence.SpecificActivityNotification
import de.unikiel.klik.persistence.TeamInvite
import de.unikiel.klik.persistence.Message
import de.unikiel.klik.persistence.User
import grails.transaction.Transactional
import org.apache.shiro.subject.Subject

@Transactional
class MessageService {

    void inviteUserToTeam(long receiverId, Subject subject) {

        //Get the user the message belongs to via the id
        User receiver = User.findById(receiverId)

        //Get the sender via subject
        User sender = User.findByEmail(subject.getPrincipal())

        //Create the invitation
        TeamInvite invitation = new TeamInvite(receiver: receiver, sender: sender)
        invitation.save()
    }

	void remindUser(long receiverId) {

        //Get the user the reminder belongs to via the id
        User receiver = User.findById(receiverId)

        //Create the reminder
        ActivityNotification activityNotification = new ActivityNotification(receiver: receiver)
        activityNotification.save()
	}

	void remindUser(long receiverId, long activityId) {

        //Get the user the reminder belongs to via the id
        User receiver = User.findById(receiverId)

        //Get the user the reminder belongs to via the id
        Activity specificActivity = Activity.findById(activityId)

        //TODO No facility in SpecificActivityNotification for passing the specific activity
        //Create the reminder
        SpecificActivityNotification specificActivityNotification = new SpecificActivityNotification(receiver: receiver)
        specificActivityNotification.save()
	}
    /**
    * deletes a Message
    * return if delete was performed
    */
    boolean deleteMessage(long messageId, Subject subject){
      Message message = Message.get(messageId)
      if(message.receiver == User.findByEmail(subject.getPrincipal())){
        message.delete()
        return true;
      }else{
        return false;
      }
   }
}
