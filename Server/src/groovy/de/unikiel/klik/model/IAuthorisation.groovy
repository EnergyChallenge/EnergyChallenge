package de.unikiel.klik.model

import org.apache.shiro.authc.AuthenticationException;

import de.unikiel.klik.model.exceptions.FacultyNotFoundException;
import de.unikiel.klik.model.exceptions.InvalidPasswordException;
import de.unikiel.klik.model.exceptions.InvalidUserNameException;

interface IAuthorisation {
	void register(String emailAdress, String password, String passwordConfirmation, String Faculty) throws InvalidUserNameException,InvalidPasswordException,FacultyNotFoundException,IOException;
	void login(String emailAdress, String password, boolean rememberMe)throws AuthenticationException;
	void requestPassword(String emailAdress)throws InvalidUserNameException;
	void logout();
}
