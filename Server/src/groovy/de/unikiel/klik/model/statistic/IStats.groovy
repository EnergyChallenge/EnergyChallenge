package de.unikiel.klik.model.statistic

interface IStats {
	public def getTop10Activitys();
	public int[] getLoginsLast10Days();
	public int getTotalTeams();
	public int getTotalPoints();
	public int getPoints(Date from, Date to);
}
