package de.unikiel.klik.model

import org.junit.Test
import org.junit.Ignore
import static org.junit.Assert.*

class UserTest {
	
	@Ignore("not ready yet")
	void completeAcitivityNowShouldAddActivityToCompletedActivities() {
		def testUser = new User(name: "Wolfgang", email: "schmail")
		testUser.save(flush: true)
		def activity = new Activity(title: "Fahrradfahren", points: 3)
		activity.save(flush:true)
		testUser.completeAcitivityNow(activity)
		assertEquals(1, testUser.getCompletedActivities.size());
	}
	
	@Test
	void doPass() {
		assertTrue(true)
	}

}
