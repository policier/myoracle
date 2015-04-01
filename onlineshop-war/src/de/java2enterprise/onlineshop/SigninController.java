package de.java2enterprise.onlineshop;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import javax.transaction.UserTransaction;

import de.java2enterprise.onlineshop.model.Customer;
@Named
@SessionScoped
public class SigninController implements Serializable {   
    
	private static final Logger log = Logger.getLogger(SigninController.class.getName());
	private static final long serialVersionUID = 1L;
	@PersistenceUnit
	private EntityManagerFactory emf;
	@Resource
	private UserTransaction ut;
	
	@Inject
	private Customer customer;
	
//	@Resource(lookup ="javax.faces.PROJECT_STAGE")
//	private String state;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	private String email;
	private String password;
	
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String find(){
		try {  
			System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHh");
			log.info(email);
			log.info(password);
			EntityManager em = emf.createEntityManager();
			String sql = "select c from Customer c "
					+ " where c.email =?1 "
					+ " and c.password =?2 ";
			TypedQuery<Customer> query = em.createQuery(sql, Customer.class);
			query.setParameter(1, email);
			query.setParameter(2, password);
		    customer = query.getSingleResult();
			if(customer!=null){
				FacesMessage m = new FacesMessage("Succesfully signed in", "you sign in under id "+customer.getId());
				FacesContext.getCurrentInstance().addMessage("signinForm", m);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
//			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_WARN,e.getMessage(),e.getCause().getMessage());
//			FacesContext.getCurrentInstance().addMessage("signinForm", m);
			return "/signin1.xhtml";
		}
	return "/signin.xhtml";  
//	return "failure";  
	}  
}
