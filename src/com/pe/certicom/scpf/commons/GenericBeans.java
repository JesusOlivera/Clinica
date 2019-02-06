package com.pe.certicom.scpf.commons;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.util.ClassUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class GenericBeans {

	private Object bean;
	private Object beanBusqueda;
	

	public Object getSpringBean(String name) {
		return getWebContext().getBean(name);
	}

	/**
	 * Metodo donde se obtiene un objeto segun el nombre de la clase <br/>
	 */
	public Object getSpringBean(Class nameObj) {
		return getSpringBean(ClassUtils.getShortName(nameObj));
	}

	/**
	 * Metodo donde se obtiene un objeto de la session
	 */
	public Object getSpringBean(Object nameObj) {
		return getSpringBean(nameObj.getClass());
	}

	/**
	 * Obtiene el Contenedor web de Spring
	 */
	public WebApplicationContext getWebContext() {
		return WebApplicationContextUtils
				.getWebApplicationContext(getServletContext());
	}

	/**
	 * Obtiene el contexto de ejecuci�n del Servlet
	 */
	public ServletContext getServletContext() {
		return (ServletContext) FacesContext.getCurrentInstance()
				.getExternalContext().getContext();
	}

	public void forward(String rule) {
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.getApplication().getNavigationHandler()
				.handleNavigation(fc, null, rule);
	}

	public Object getBean() {
		return bean;
	}

	public void setBean(Object bean) {
		this.bean = bean;
	}

	public Object getBeanBusqueda() {
		return beanBusqueda;
	}

	public void setBeanBusqueda(Object beanBusqueda) {
		this.beanBusqueda = beanBusqueda;
	}

}
