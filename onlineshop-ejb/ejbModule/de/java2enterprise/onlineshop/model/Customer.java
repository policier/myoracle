package de.java2enterprise.onlineshop.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;   


/**
 * The persistent class for the CUSTOMER database table.
 * 
 */   
@Entity
@Table(name="customer", schema="OMT")
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CUSTOMER_ID_GENERATOR", sequenceName="SEQ_CUSTOMER", schema="OMT", allocationSize=1,initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CUSTOMER_ID_GENERATOR")
	private long id;

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
	//bi-directional many-to-one association to Item
	@OneToMany(mappedBy="seller")
	private Set<Item> offers;

	//bi-directional many-to-one association to Item
	@OneToMany(mappedBy="byer")
	private Set<Item> purchases;

	public Customer() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public Set<Item> getOffers() {
		return this.offers;
	}

	public void setOffers(Set<Item> offers) {
		this.offers = offers;
	}

	public Item addOffer(Item newOffer) {
		Set<Item> allOffers = getOffers();
		if(allOffers == null){
			allOffers=new HashSet<Item>();
		}
		allOffers.add(newOffer);
		newOffer.setSeller(this);
		return newOffer;
	}


	public Item removeOffer(Item offer) {
		getOffers().remove(offer);
		offer.setSeller(null);
		return offer;
	}

	public Set<Item> getPurchases() {
		return this.purchases;
	}

	public void setPurchases(Set<Item> purchases) {
		this.purchases = purchases;
	}

	public Item addPurchas(Item newPurchas) {
		Set<Item> allPurchase = getPurchases();
		if(allPurchase==null){
			allPurchase= new HashSet<Item>();
		}
		allPurchase.add(newPurchas);
		newPurchas.setByer(this);
		return newPurchas;
	}

	public Item removePurchas(Item purchas) {
		getPurchases().remove(purchas);
		purchas.setByer(null);

		return purchas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (id != other.id)
			return false;
		return true;
	}
	public String toString(){
		return id + "-"+email+"-"+password;
	}  
}