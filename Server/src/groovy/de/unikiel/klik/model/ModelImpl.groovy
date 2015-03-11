package de.unikiel.klik.model

import java.util.List;

import org.apache.shiro.subject.Subject;

import de.unikiel.klik.model.profilmanager.ProfilManager;

class ModelImpl implements IModel {
	static private ModelImpl instance;
	private ModelImpl() {}
	static public synchronized ModelImpl getInstance() {
		if(instance==null) {
			instance = new ModelImpl();
		}
		return instance;
	}

	@Override
	public IProfil getProfilByID(long ID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<IProfil> getTeams() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<IProfil> getUsers() {
		return ProfilManager.getInstance().getUsers();
	}

	@Override
	public IUser getCurrentUser(Subject currentUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ITeam getCurrrentTeam(Subject currentUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IStats getStats() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IActivity getAllActivitys(Subject currentUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<IProposal> getProposals() {
		// TODO Auto-generated method stub
		return null;
	}

}
