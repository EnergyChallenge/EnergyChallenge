package de.unikiel.klik

import grails.transaction.Transactional

@Transactional
class AdminService {

    void createActivity(String descrition, int points, Duration duration) {

    }
	
	void createActivityFromProposal(String descriotion, int points, Duration duration, long proposalId) {
		
	}
	void editActivity(String descrition, int points, Duration duration,long activityId) {
	
	}
	void deleteActivity(long activityId) {
		
	}
	void deleteProposal(long proposalId) {
		
	}
	void blockUser(long userId) {
		
	}
	void unblockUser(long userId) {
		
	}
	
	void deleteUser(long userId) {
		
	}
	void blockTeam(long teamId) {
	
	}
	void unblockTeam(long teamId) {
	
	}

	void deleteTeam(long teamId) {
	
	}
	
	
	
}
