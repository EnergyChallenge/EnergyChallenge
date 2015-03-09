package de.unikiel.klik.model

import org.apache.shiro.subject.Subject;
import org.apache.shiro.authc.UsernamePasswordToken;

interface IAuthorication {
	void register();//TODO
	void signOut(Subject currentUser);
	void signIn(UsernamePasswordToken authorisationToken);
	void requestNewPassword();
}
