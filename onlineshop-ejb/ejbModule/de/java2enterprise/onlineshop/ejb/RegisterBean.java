package de.java2enterprise.onlineshop.ejb;

import java.util.concurrent.Future;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.java2enterprise.onlineshop.model.Customer;

/**
 * Session Bean implementation class RegisterBean
 */
@Stateless
//@Stateful
@LocalBean
public class RegisterBean implements RegisterBeanRemote, RegisterBeanLocal {
    
	@PersistenceContext
    private EntityManager em;
	private String email;
	private String password;
	
	/**
     * Default constructor. 
     */
    public RegisterBean() {
        // TODO Auto-generated constructor stub
    }
    
//    @TransactionAttribute(value=TransactionAttributeType.REQUIRES_NEW)
    @Asynchronous
	public Future <Long> persist(String email, String password) {
    	Customer customer = new Customer();
		customer.setEmail(email);
		customer.setPassword(password);
		em.persist(customer);
		Long id = customer.getId();
		return new AsyncResult<Long>(id);
	}

	@Override
	public void saveEmail(String email) {
		// TODO Auto-generated method stub
		this.email=email;
	}

	@Override
	public void savePassword(String password) {
		this.password=password;
	}
	@Asynchronous
	public void persist() {
		Customer customer = new Customer();
		customer.setEmail(email);
		customer.setPassword(password);
		em.persist(customer);
	}
}
