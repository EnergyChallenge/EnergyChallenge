package de.unikiel.klik.model

import org.apache.shiro.subject.Subject

import de.unikiel.klik.model.activitymanagement.IActivity;
import de.unikiel.klik.model.profilmanagement.IProfil;
import de.unikiel.klik.model.profilmanagement.teammanagement.ITeam;
import de.unikiel.klik.model.profilmanagement.usermanagement.IUser;
import de.unikiel.klik.model.proposalmangagement.IProposal;
import de.unikiel.klik.model.statistic.IStats;


interface IModel {
	//TODO
	
	/**
	 * @param ID
	 * @return The Team or User with the requested ID
	 */
	public IProfil getProfilByID(long ID);
	public ArrayList<IProfil> getTeams();
	public ArrayList<IProfil> getUsers();
	public IUser getCurrentUser(Subject currentUser);
	public ITeam getCurrrentTeam(Subject currentUser);
	public IStats getStats();
	/**
	 * 
	 * @return All Activity for the Public view
	 */
	public ArrayList<IActivity> getAllActivitys();
	public ArrayList<IActivity> getAllActivitys(Subject currentUser);
	public ArrayList<IActivity> getRecentActivitys(Subject currentUser);
	/**
	 * 
	 * @param ID
	 * @return All Activitys that are completed by a Team or a User
	 */
	public ArrayList<IActivity> getAllCompletedActivitys(long ID);
	public ArrayList<IActivity> getRecentCompletedActivitys(long ID);
	
	public ArrayList<IProposal> getProposals();
	public void addProposal(String name);
}
