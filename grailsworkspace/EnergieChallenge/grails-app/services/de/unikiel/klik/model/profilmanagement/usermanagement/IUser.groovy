package de.unikiel.klik.model.profilmanagement.usermanagement

import de.unikiel.klik.model.profilmanagement.IProfil

interface IUser extends IProfil{
	String getTeam();
	//TODO getFaculty();
	
	
}
