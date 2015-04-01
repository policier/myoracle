package de.java2enterprise.onlineshop.ejb;

import java.util.concurrent.Future;

import javax.ejb.Remote;

@Remote
public interface RegisterBeanRemote {
	public abstract  Future <Long>  persist(String email, String password);
}
