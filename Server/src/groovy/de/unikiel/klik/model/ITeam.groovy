package de.unikiel.klik.model

interface ITeam extends IProfil {
	public ArrayList<IProfil> getMembers();
	public int getTotalPoints();
}
