package de.unikiel.klik.model.profilmanager

import java.util.List;

import de.unikiel.klik.model.IProfil;
import de.unikiel.klik.model.KlikUser;

class ProfilManager {
	static private ProfilManager instance;
	private ProfilManager() {}
	
	static public synchronized ProfilManager getInstance() {
		if(instance==null) {
			instance = new ProfilManager();
		}
		return instance;
	}
	public IProfil getProfilByID(String ID) {
		Profil.getById(ID);
	}

	public ArrayList<IProfil> getTeams() {
		return null;
	}

	public ArrayList<IProfil> getUsers() {
		ArrayList<IProfil> profils = new ArrayList<IProfil>();
		int i = 1;
		//for(KlikUser user :KlikUser.listOrderByPoints(order: "desc")) {
		for(KlikUser user :KlikUser.listOrderByName(order: "desc")) {
			profils.add(new Profil(user,i));
			profils.get(0).getRank();
			profils.get(0).getName();
			profils.get(0).getPoints();
			i++;
		}
		return profils;
	}
}
