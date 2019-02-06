package org.login.phaselistener;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletResponse;



public class LoginPhaseListener implements PhaseListener{

	private static final long serialVersionUID = 1L;

	
	@Override
    public void afterPhase(PhaseEvent pe) {
        FacesContext facesContext = pe.getFacesContext();
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", -1);

        String[] paginaPermitidas = {"principal"};
        FacesContext fc = pe.getFacesContext();
        String paginaActual = fc.getViewRoot().getViewId();
        NavigationHandler nh = fc.getApplication().getNavigationHandler();
        boolean paginaLogin = paginaActual.lastIndexOf("login") > -1 ? true : false;
        if (paginaActual != null) {
            boolean permitido = false;
            for (String pagina : paginaPermitidas) {
                permitido = paginaActual.lastIndexOf(pagina) > -1 ? true : false;
                if (permitido) {
                    break;
                }
            }
            if (!estaLogueado() && !paginaLogin) {
                nh.handleNavigation(fc, null, "pretty:login");
            }
        }
    }

    @Override
    public void beforePhase(PhaseEvent pe) {
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

    private boolean estaLogueado() {
        return com.pe.certicom.scpf.commons.FacesUtils.existeUsuarioLogueado();
    }

}
