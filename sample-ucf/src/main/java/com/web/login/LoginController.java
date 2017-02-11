package com.web.login;

import java.io.IOException;
import java.io.Serializable;
import java.security.Principal;
import java.util.Date;
import java.util.logging.Logger;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;



@SessionScoped
@Named("loginController")
public class LoginController implements Serializable {
	
	private static final long serialVersionUID = 4022264384801621436L;
	private static final Logger logger = Logger.getLogger(LoginController.class.getName()); 
	
	@Inject
	Principal principal;
	
	private String username;
	private String password;
	
	public String getLoginUsername(){		
		return principal.getName();
	}
	
	public void login(){
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
		try{
			request.login(username, password);	
			logger.info("User logged in at  "+ new Date()  + "   "+ this.username);
			String navigateString = "/user/home.xhtml";			
			facesContext.getExternalContext().redirect(request.getContextPath() + navigateString);			
		} catch (ServletException e) {
			logger.info("Login failed...........");
			logger.severe("Invalid username "+ username +" password, login denied.");
			logger.severe(e.getMessage());
		} catch (IOException e) {
			logger.severe("IO Exception ...................");
			logger.severe(e.getMessage());
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

}
