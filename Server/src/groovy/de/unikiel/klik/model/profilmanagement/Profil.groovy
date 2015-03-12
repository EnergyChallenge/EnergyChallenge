package de.unikiel.klik.model.profilmanagement

import java.util.ArrayList;

import de.unikiel.klik.model.activitymanagement.ICompletedActivity;

class Profil implements IProfil {
	private de.unikiel.klik.model.Profile profil;
	private int rang;
	
	public Profil(de.unikiel.klik.model.Profile profil, int rang) {
		this.profil = profil;
		this.rang = rang;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return profil.getName();
	}

	@Override
	public String getAvatar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPoints() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getID() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getRank() {
		return this.rang;
	}
	@Override
	public ArrayList<IProfil> getLinked() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isUser() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isTeam() {
		// TODO Auto-generated method stub
		return !isUser();
	}
	@Override
	public ArrayList<ICompletedActivity> getRecentCompletedActivitys(int number) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ArrayList<ICompletedActivity> getAllCompletetActivitys() {
		// TODO Auto-generated method stub
		return null;
	}

}
