package com.pe.certicom.scpf.commons;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.certicom.scpf.domain.Usuario;

public class FacesUtils {

	public static Object getSessionAttribute(String attribute) {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(false);
		Object o = null;
		if (session!=null) {
			o = session.getAttribute(attribute);
		}
		return o;
	}
	
	public static void setSessionAttribute(String attribue, Object value) {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(false);
		session.setAttribute(attribue, value);
	}
	
	public static void removeSessionAttribute(String attribute) {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(false);
		session.removeAttribute(attribute);
	}
	
	public static Usuario getUsuarioLogueado() {
		return (Usuario) getSessionAttribute("usuario");
	}
	
	public static void setUsuarioLogueado(Usuario usuario) {
		setSessionAttribute("usuario", usuario);
	}
	
	public static void removeUsuarioLogueado() {
		removeSessionAttribute("usuario");
	}
	
	public static boolean existeUsuarioLogueado() {
		if (getUsuarioLogueado()!=null) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void showFacesMessage(String texto, int tipo) {
		switch (tipo) {
		case 1:
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, texto, "Error"));
			break;
		case 2:
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, texto, "Fatal"));
			break;
		case 3:
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, texto, "Informacion"));
			break;
		case 4:
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, texto, "Advertencia"));
			break;
		default:
			break;
		}
	}
}
