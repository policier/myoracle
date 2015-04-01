package de.java2enterprise.onlineshop;

import java.io.Serializable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
   
import de.java2enterprise.onlineshop.ejb.RegisterBeanLocal;

@Named
// @ConversationScoped
// @FlowScoped(value ="register")
// @RequestScoped
@SessionScoped
public class RegisterController implements Serializable {

	private static final long serialVersionUID = 1L;
	private String email;
	private String password;

	@EJB
	private RegisterBeanLocal registerBeanLocal;

	// public String saveEmail(){
	// registerBeanLocal.saveEmail(email);
	// return "register1";
	// }
	//
	// public String savePassword(){
	// registerBeanLocal.savePassword(password);
	// registerBeanLocal.persist();
	// return "register2";
	// }
	public String persist() {
		Future<Long> future = registerBeanLocal.persist(email, password);
		Long id = Long.valueOf(0);
		try {
			id = future.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(id + " : " + email + " : " + password));
		return "register";
	}

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

	public String step1() {
		return "register1";
	}

	public String step2() {
		return "register2";
	}

	public String start() {
		return "register";
	}

	/**
	 * @PersistenceUnit JSF Chapiter private EntityManagerFactory emf;
	 * @Resource private UserTransaction ut;
	 * @Inject private Customer customer;
	 * @Inject private Conversation conversation;
	 * 
	 *         public Customer getCustomer() { return customer; }
	 * 
	 *         public void setCustomer(Customer customer) { this.customer =
	 *         customer; }
	 * 
	 *         public String persist(){ try { ut.begin();
	 *         emf.createEntityManager().persist(customer); ut.commit();
	 *         FacesMessage m = new FacesMessage("Succesfully registery",
	 *         "you email was saved under id "+customer.getId());
	 *         FacesContext.getCurrentInstance().addMessage("registerForm", m);
	 *         if(conversation.isTransient()){ conversation.end(); } return
	 *         "success"; } catch (Exception e) { // TODO Auto-generated catch
	 *         block e.printStackTrace(); FacesMessage m = new
	 *         FacesMessage(FacesMessage
	 *         .SEVERITY_WARN,e.getLocalizedMessage(),null);
	 *         FacesContext.getCurrentInstance().addMessage("registerForm", m);
	 *         } // return "register"; return "failure"; } public String
	 *         step1(){ if(conversation.isTransient()){ conversation.begin(); }
	 *         return "register1"; }
	 * 
	 *         public String step2(){ return "register2"; } public String
	 *         start(){ return "register"; }
	 */
}
