package de.unikiel.klik.model.profilmanagement.teammanagement

import de.unikiel.klik.model.profilmanagement.IProfil;
import org.apache.shiro.subject.Subject

/**
 * this is used to Change the Profil of a Team
 * @author lennard
 *
 */
interface ITeam extends IProfil {
	public int getTotalPoints();
	public void changeAvatar(Subject currentUser);
	public void inviteUser(long ID);
}
