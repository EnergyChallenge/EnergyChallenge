package de.unikiel.klik.model.profilmanagement.usermanagement

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject

import de.unikiel.klik.model.profilmanagement.IProfil;
import de.unikiel.klik.model.profilmanagement.teammanagement.IInvite

/**
 * this is used to change to Profil of a User
 * and to get aditional private Informations for the Current User
 * @author lennard
 *
 */
interface IUser extends IProfil {
	public void changeAvatar(Subject currentUser, String Image) throws AuthenticationException;
	public void acceptInvite(Subject currentUser, IInvite invite) throws AuthenticationException;
	public int getNumberOfUnreadMessages();
	public ArrayList<String> getMessages();
	//TODO change notification settings
	
}
