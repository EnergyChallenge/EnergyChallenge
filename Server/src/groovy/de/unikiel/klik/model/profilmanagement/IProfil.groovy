package de.unikiel.klik.model.profilmanagement

import de.unikiel.klik.model.activitymanagement.ICompletedActivity;

interface IProfil {
	public String getName();
	public getAvatar();
	public int getPoints();
	public int getRank();
	public long getID();
	/**
	 * 
	 * @return Team if Profil is a User / Members if Profil is a Team
	 */
	public ArrayList<IProfil> getLinked();
	public boolean isUser();
	public boolean isTeam();
	ArrayList<ICompletedActivity> getRecentCompletedActivitys(int number);
	ArrayList<ICompletedActivity> getAllCompletetActivitys();
}
