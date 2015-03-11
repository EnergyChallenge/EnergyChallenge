package de.unikiel.klik.model

interface IActivity {
	public String getName();
	public int getDurationInMinutes();
	public boolean isAvailible();
	public int getPoints();
	public Date availibleAt();
	public void completeActivity();
}
