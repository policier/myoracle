package de.java2enterprise.onlineshop;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
@Named
@RequestScoped
public class LocalController implements Serializable {
	private static final long serialVersionUID = 1L;
	private String lang;
	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}
	
	public String change(String lang){
		this.lang=lang;
		return "/index.xhtml";
	}
}
