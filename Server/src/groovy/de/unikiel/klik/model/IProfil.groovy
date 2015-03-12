package de.unikiel.klik.model

interface IProfil {
	public String getName();
	public getAvatar();
	public int getPoints();
	public int getRank();
	public long getID();
	public ArrayList<IProfil> getLinked();
	public boolean isUser();
	public boolean isTeam();
	ArrayList<ICompletedActivity> getRecentCompletedActivitys(int number);
	ArrayList<ICompletedActivity> getAllCompletetActivitys();
}
