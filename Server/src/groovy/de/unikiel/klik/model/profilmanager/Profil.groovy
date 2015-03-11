package de.unikiel.klik.model.profilmanager

import de.unikiel.klik.model.IProfil

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

}
