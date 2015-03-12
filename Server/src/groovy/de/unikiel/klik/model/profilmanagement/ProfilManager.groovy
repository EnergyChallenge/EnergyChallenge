package de.unikiel.klik.model.profilmanagement

import java.util.List;

import de.unikiel.klik.model.User;
import de.unikiel.klik.model.Team

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
		ArrayList<IProfil> profils = new ArrayList<IProfil>();
		int i = 1;
		//for(KlikUser user :KlikUser.listOrderByPoints(order: "desc")) {
		for(Team team :Team.listOrderByName(order: "desc")) {
			profils.add(new Profil(team,i));
			i++;
		}
		return profils
	}

	public ArrayList<IProfil> getUsers() {
		ArrayList<IProfil> profils = new ArrayList<IProfil>();
		int i = 1;
		//for(KlikUser user :KlikUser.listOrderByPoints(order: "desc")) {
		for(User user :User.listOrderByName(order: "desc")) {
			profils.add(new Profil(user,i));
			i++;
		}
		return profils;
	}
}
