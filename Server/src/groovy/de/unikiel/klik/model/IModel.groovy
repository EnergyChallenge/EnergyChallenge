package de.unikiel.klik.model

import de.unikiel.klik.model.profilmanager.IProfil

interface IModel {
	//TODO
	IProfil getProfilByID(String name);
	// IProfil getProfils();
	IProfil getUserByID(String ID);
	//
}
