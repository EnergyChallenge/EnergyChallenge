package de.unikiel.klik.model

import org.apache.shiro.authc.AuthenticationException
import org.apache.shiro.authc.UsernamePasswordToken
import org.apache.shiro.crypto.hash.Sha256Hash
import org.apache.shiro.web.util.SavedRequest

class AuthorisationImpl implements IAuthorisation{
	private static AuthorisationImpl instance;
	private AuthorisationImpl() {
	}
	public static synchronized  AuthorisationImpl getInstance() {
		if(instance == null) {
			instance = new AuthorisationImpl ();
		  }
		  return instance;
	}
	@Override
	void register(String emailAdress, String password, String passwordConfirmation, String Faculty){
		def user = ShiroUser.findByUsername(emailAdress)
		if (user) {
			throw new InvalidUserNameException("User exists allready");
		}
		else {
			if (password != passwordConfirmation) {
				throw new InvalidPasswordException("Paswords dont match");
			}
			else {
				user = new ShiroUser(username:passwordConfirmation,passwordHash: new Sha256Hash(password).toHex())
			   //TODO add Facultaet
				if (user.save()) {
					def userRole =  ShrioRole.findByName('user')
					user.addToRoles(userRole)
					user.save()
				}
				else {
					user.delete();
					throw new IOException("Can not save User")
				}
			}
		}
	}
	@Override
	void login(String emailAdress, String password, boolean rememberMe){
				if (rememberMe) {
					authToken.rememberMe = true
				}
				def authToken = new UsernamePasswordToken(username, password)
				SecurityUtils.subject.login(authToken)
	}
	@Override
	void requestPassword(String emailAdress)throws InvalidUserNameException{
		
	}
	@Override
	void logout() {
		SecurityUtils.subject?.logout()
		webRequest.getCurrentRequest().session = null
	}
}
