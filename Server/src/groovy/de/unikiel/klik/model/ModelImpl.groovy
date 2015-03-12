package de.unikiel.klik.model

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.subject.Subject;

import de.unikiel.klik.model.activitymanagement.IActivity;
import de.unikiel.klik.model.profilmanagement.IProfil;
import de.unikiel.klik.model.profilmanagement.ProfilManager;
import de.unikiel.klik.model.profilmanagement.teammanagement.ITeam;
import de.unikiel.klik.model.profilmanagement.usermanagement.IUser;
import de.unikiel.klik.model.proposalmangagement.IProposal;
import de.unikiel.klik.model.statistic.IStats;

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
	public ArrayList<IActivity> getAllActivitys(Subject currentUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<IProposal> getProposals() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ArrayList<IActivity> getAllActivitys() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ArrayList<IActivity> getRecentActivitys(Subject currentUser) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ArrayList<IActivity> getAllCompletedActivitys(long ID) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ArrayList<IActivity> getRecentCompletedActivitys(long ID) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void addProposal(String name) {
		// TODO Auto-generated method stub
		
	}

}
