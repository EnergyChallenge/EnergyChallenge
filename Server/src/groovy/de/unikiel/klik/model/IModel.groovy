package de.unikiel.klik.model

import org.apache.shiro.subject.Subject


interface IModel {
	//TODO
	public IProfil getProfilByID(long ID);
	public ArrayList<IProfil> getTeams();
	public ArrayList<IProfil> getUsers();
	public IUser getCurrentUser(Subject currentUser);
	public ITeam getCurrrentTeam(Subject currentUser);
	public IStats getStats();
	public IActivity getAllActivitys(Subject currentUser);
	public ArrayList<IProposal> getProposals();
}
