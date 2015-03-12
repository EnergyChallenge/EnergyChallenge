package de.unikiel.klik.model.activitymanagement

/**
 * Activitys are assigned to a User
 * 
 * @author lennard
 *
 */
interface IActivity {
	public String getName();
	public int getDurationInMinutes();
	/**
	 * Allways true if in public view
	 * @return true if User can Complete this Activity
	 */
	public boolean isAvailible();
	public int getPoints();
	/**
	 * Date is somewhere in the Past if the Activity is availible
	 * @return when User is able to Complete the Activity again
	 */
	public Date availibleAt();
	public void completeActivity();
}
