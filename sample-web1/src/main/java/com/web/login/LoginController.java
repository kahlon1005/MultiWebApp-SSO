package com.web.login;

import java.io.IOException;
import java.io.Serializable;
import java.security.Principal;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@SessionScoped
@Named("loginController")
public class LoginController implements Serializable {
	
	private static final long serialVersionUID = 4022264384801621436L;
	
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
			System.out.println("logged in at  "+ new Date()  + "   "+ this.username);
			String navigateString = "/user/home.xhtml";			
			facesContext.getExternalContext().redirect(request.getContextPath() + navigateString);			
		} catch (ServletException e) {
			System.out.println("Invalid username password, login denied.");
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println("IO Exception ...................");
			e.printStackTrace();
		}
	}
	
	public String logout() {
	    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	    session.invalidate();
		System.out.println("logout at " + new Date() +"  "+ getLoginUsername());
	    return "/login.xhtml?faces-redirect=true";
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
