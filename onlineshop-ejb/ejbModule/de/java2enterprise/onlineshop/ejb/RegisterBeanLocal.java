package de.java2enterprise.onlineshop.ejb;

import java.util.concurrent.Future;

import javax.ejb.Local;

@Local
public interface RegisterBeanLocal {
	public abstract void saveEmail(String email);
	public abstract void savePassword(String password);
//	public abstract void persist();
//	@for stateless sesion bean
	public abstract Future <Long> persist(String email, String password);
}
