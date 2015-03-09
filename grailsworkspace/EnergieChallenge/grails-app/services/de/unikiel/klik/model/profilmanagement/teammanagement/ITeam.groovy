package de.unikiel.klik.model.profilmanagement.teammanagement

import de.unikiel.klik.model.profilmanagement.usermanagement.IUser

interface ITeam {

	IUser[] getUsers();
	int getTotalPoints();
}
