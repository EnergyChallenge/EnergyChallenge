package de.unikiel.klik.model

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject

interface IUser extends IProfil {
	public void changeAvatar(Subject currentUser, String Image) throws AuthenticationException;
	public void joinTeam(Subject currentUser, longID) throws AuthenticationException;
	public int getNumberOfUnreadMessages();
	public ArrayList<String> getMessages();
	
}
