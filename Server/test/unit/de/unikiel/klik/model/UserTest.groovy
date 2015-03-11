package de.unikiel.klik.model

import org.junit.Test;
import static org.junit.Assert.*;

class UserTest {
	
	@Test
	void completeAcitivityNowShouldAddActivityToCompletedActivities() {
		User testUser = new User("Wolfgang", "schmail");
		Activity activity = new Activity("Fahrradfahren", 3);
		testUser.completeAcitivityNow(activity);
		assertEquals(activity, testUser.getCompletedActivities().get(0).getActivity());
	}
	

}
