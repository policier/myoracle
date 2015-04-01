package de.java2enterprise.onlineshop;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Map;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.flow.FlowHandler;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.http.Part;
import javax.transaction.UserTransaction;

import org.jboss.logging.Logger;

import de.java2enterprise.onlineshop.model.Customer;
import de.java2enterprise.onlineshop.model.Item;

@Named
@RequestScoped
public class SellController implements Serializable {
	private static final long serialVersionUID = 1L;
	private final static Logger log = Logger.getLogger(SigninController.class.toString());
	
	@PersistenceUnit
	private EntityManagerFactory emf;
	@Resource
	private UserTransaction ut;
	
	private  Part part;
	@Inject
	private Item item;
	public Part getPart() {
		return part;
	}
	public void setPart(Part part) {
		this.part = part;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public String persist(SigninController signinController){
		try {
			ut.begin();
			EntityManager em = emf.createEntityManager();
			Customer customer = signinController.getCustomer();
			
//			FacesContext fc = FacesContext.getCurrentInstance();
//			Application app = fc.getApplication();
//			FlowHandler fh = app.getFlowHandler();
//			Map<Object, Object> cfs = fh.getCurrentFlowScope();
//			Object customerid=cfs.get("customerid");
//			
//			if(customerid != null){
				customer =em.find(Customer.class, customer.getId());
//			}
			  
			item.setSeller(customer);
			InputStream in = part.getInputStream();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte [] buffer = new byte[10240];
			for(int length=0; (length=in.read(buffer))>0;){
				out.write(buffer, 0, length);
			}
			item.setFoto(out.toByteArray());   
			em.persist(item);
//			em.merge(item);
			ut.commit();
			FacesMessage m = new FacesMessage("Succesfully saved item !", "you offered the item "+item);
			FacesContext.getCurrentInstance().addMessage("sellForm", m);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_WARN,e.getMessage(),e.getCause().getMessage());
		FacesContext.getCurrentInstance().addMessage("sellForm", m);
	}
		return "/index";
	}
}
