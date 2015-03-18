package de.unikiel.klik

import de.unikiel.klik.model.Activity
import de.unikiel.klik.model.ActivityNotification
import de.unikiel.klik.model.SpecificActivityNotification
import de.unikiel.klik.model.TeamInvite
import de.unikiel.klik.model.User
import grails.transaction.Transactional
import org.apache.shiro.subject.Subject

@Transactional
class MessageService {

    void inviteUserToTeam(long receiverId, Subject subject) {

        //Get the user the message belongs to via the id
        User receiver = User.findById(recieverId)

        //Get the sender via subject
        User sender = User.findByEmail(subject.getPrincipal())

        //Create the invitation
        TeamInvite invitation = new TeamInvite(receiver: receiver, sender: sender)
        invitation.save()
    }

	void remindUser(long reciverId) {

        //Get the user the reminder belongs to via the id
        User receiver = User.findById(recieverId)

        //Create the reminder
        ActivityNotification activityNotification = new ActivityNotification(receiver: receiver)
        activityNotification.save()
	}

	void remindUser(long reciverId, long activityId) {

        //Get the user the reminder belongs to via the id
        User receiver = User.findById(recieverId)

        //Get the user the reminder belongs to via the id
        Activity specificActivity = Activity.findById(activityId)

        //TODO No facility in SpecificActivityNotification for passing the specific activity
        //Create the reminder
        SpecificActivityNotification specificActivityNotification = new SpecificActivityNotification(receiver: receiver)
        specificActivityNotification.save()
	}
}
