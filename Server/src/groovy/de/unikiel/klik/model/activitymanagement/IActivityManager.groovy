package de.unikiel.klik.model.activitymanagement

import org.apache.shiro.subject.Subject

interface IActivityManager {
	public ArrayList<IActivity> getAllActivitys();
	public ArrayList<IActivity> getAllActivitys(Subject currentUser);
	public void completeActivity(IActivity activity, Subject currentUser);
	public ArrayList<ICompletedActivity> getCompletetActivitysOfProfil(long ID);
	public ArrayList<ICompletedActivity> getRecentCompletetActivitysOfProfil(long ID);
	/**
	 * @param curretUser
	 * @throws Exception if User is not permitted
	 */
	public void addActivity(Subject curretUser) throws Exception;
}
