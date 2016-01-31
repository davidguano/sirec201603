/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.sirec.web.base;
import ec.sirec.ejb.entidades.SegUsuario;
import java.io.IOException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author dguano
 */
@ManagedBean
@SessionScoped
public class BaseControlador {

    /**
     * Creates a new instance of BaseControlador
     */
    public BaseControlador() {
    }
    /**
     * Se encarga de recuperar el nombre del contexto de la apliacacion web.
     *
     * @return Una cadena con el nombre del contexto
     */
    public final String getContextName() {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
    }

    /**
     * Se encarga de ejecutar una redireccion.
     *
     * @param url url de destino
     * @throws IOException en caso de no poder hacer la redireccion
     */
    public void redirect(final String url) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect(url);
    }

    /**
     * Se encarga de recuperar HttpServletRequest.
     *
     * @return El objeto HttpServletRequest encontrado
     */
    public static HttpServletRequest getRequest() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        if (request == null) {
            throw new RuntimeException(
                    "No se pudo recuperar HttpServletRequest");
        }
        return request;
    }

    /**
     * Retorna la session http.
     *
     * @return session
     */
    public HttpSession getSession() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(Boolean.TRUE);
        return session;
    }

    public void addErrorMessage(Exception ex, String defaultMsg) {
        String msg = ex.getLocalizedMessage();
        if (msg != null && msg.length() > 0) {
            addErrorMessage(msg);
        } else {
            addErrorMessage(defaultMsg);
        }
    }

    public void addErrorMessages(List<String> messages) {
        for (String message : messages) {
            addErrorMessage(message);
        }
    }

    public void addErrorMessage(String msg1, String msg2) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg1, msg2);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    public void addWarningMessage(String msg1, String msg2) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, msg1, msg2);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    public void addSuccessMessage(String msg1, String msg2) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg1, msg2);
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }

    public void addErrorMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    public void addWarningMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, "", msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    public void addSuccessMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "", msg);
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }

    /**
     * Obtiene usuario autenticado.
     *
     * @return Usuario
     */
    public SegUsuario obtenerUsuarioAutenticado() {
        return (SegUsuario) this.getSession().getAttribute("usuario");
    }

    public String obtenerIp() {
        //String ipCliente = getRequest().getRemoteAddr();
        String ipCliente = getRequest().getHeader("X-FORWARDED-FOR");
        if (ipCliente == null) {
            ipCliente = getRequest().getRemoteAddr();
        }
        return ipCliente;
    }




    
    /**
     * Envia mensajes de error a un componente especifico
     *
     * @param idElemento id del Componente ej: frmEgresos:fechaCritica
     * @param resumen Titulo del mensaje, puede ir null
     * @param detalle Detalle del mensaje
     */
    public static void mensajeErrorComponente(String idElemento, String resumen, String detalle) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(idElemento, new FacesMessage(FacesMessage.SEVERITY_ERROR, resumen, detalle));
    }
}
