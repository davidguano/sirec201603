/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sirec.web.base;

import ec.sirec.ejb.entidades.SegAplicacion;
import ec.sirec.ejb.entidades.SegUsuario;
import ec.sirec.ejb.servicios.AplicacionServicio;
import ec.sirec.ejb.servicios.UsuarioServicio;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author dguano
 */
@ManagedBean
@SessionScoped
public class NavegacionControlador extends BaseControlador {

    /**
     * Creates a new instance of NavegacionControlador
     */
    //LOGGER 
    private static final Logger LOGGER = Logger.getLogger(NavegacionControlador.class.getName());
    //VARIABLES - ATRIBUTOS
    private SegUsuario usuarioActual;
    private MenuModel menu;
    private String username;
    private String password;
    private List listadoAplicaciones;

    //SERVICIOS
    @EJB
    private UsuarioServicio usuarioServicio;
    @EJB
    private AplicacionServicio aplicacionServicio;

    public NavegacionControlador() {
    }

    @PostConstruct
    public void inicializar() {
        try {

            
            usuarioActual = new SegUsuario();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }

    }

    public void autenticacionBaseLocal() {
        try {
            if (usuarioServicio.obtenerUsuarioPorAutenticacion(username)) {
                construirMenu();
                usuarioActual = usuarioServicio.obtenerUsuarioPorUserName(username);
                getSession().setAttribute("usuario", usuarioActual);
                cargarListaAccesoApli();
                //if (listadoAplicaciones.isEmpty()) {
                  //  addWarningMessage("El usuario no tiene roles asignados !Contáctese con un administrador de sistemas!");
                //} else {
                    redireccionarAPagina("", "inicio");
                //}
            } else {
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public void autenticaBaseLocalNoCreaUsuario() {
        try {
            if (usuarioServicio.obtenerUsuarioPorAutenticacion(username)) {
                construirMenu();
                usuarioActual = usuarioServicio.obtenerUsuarioPorUserName(username);
                getSession().setAttribute("usuario", usuarioActual);
                cargarListaAccesoApli();
                //if (listadoAplicaciones.isEmpty()) {
                    //addWarningMessage("El usuario no tiene roles asignados !Contáctese con un administrador de sistemas!");
                //} else {
                    redireccionarAPagina("", "inicio");
                //}
            } else {
                addErrorMessage("Credenciales no válidas");
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public void cargarListaAccesoApli() throws Exception {
        listadoAplicaciones = aplicacionServicio.listarAplicaciones(username);
    }

    

    

    public String generaIdentificacion() {
        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());
        int al6 = 0x3b9aca00 + rnd.nextInt(0xdbba0);
        String aleatorio = (new StringBuilder()).append(al6).append("").toString();
        String combinacion = (new StringBuilder()).append(username).append(aleatorio).toString();
        String identificacion = combinacion.substring(0, 13);
        return identificacion;
    }

    private void construirMenu() {
        try {
            menu = new DefaultMenuModel();
            DefaultMenuItem itemA2 = new DefaultMenuItem("Salir");
            itemA2.setIcon("ui-icon-power");
            itemA2.setCommand("#{navegacionControlador.cerrarSesion}");
            menu.addElement(itemA2);

            DefaultSubMenu subMenuAdmin = new DefaultSubMenu("Administracion");
            //---SubMenu Gestion Usuarios --
            DefaultMenuItem itemGesUsu = new DefaultMenuItem("Gestión de Usuarios");
            itemGesUsu.setIcon("ui-icon-person");
            itemGesUsu.setCommand("#{navegacionControlador.redireccionarAPagina('seguridad','gestionUsuarios')}");
            subMenuAdmin.addElement(itemGesUsu);
            //---SubMenu Administracion de roles
            DefaultMenuItem itemGesRol = new DefaultMenuItem("Gestión de Roles");
            itemGesRol.setIcon("ui-icon-person");
            itemGesRol.setCommand("#{navegacionControlador.redireccionarAPagina('seguridad','gestionRoles')}");
            subMenuAdmin.addElement(itemGesRol);
            //--SubMenu Permisos
            DefaultMenuItem itemGesPer = new DefaultMenuItem("Gestión de Permisos");
            itemGesPer.setIcon("ui-icon-person");
            itemGesPer.setCommand("#{navegacionControlador.redireccionarAPagina('seguridad','gestionPermisos')}");
            subMenuAdmin.addElement(itemGesPer);
            //--SubMenu Aplicaciones
            DefaultMenuItem itemGesApl = new DefaultMenuItem("Gestión de Aplicaciones");
            itemGesApl.setIcon("ui-icon-person");
            itemGesApl.setCommand("#{navegacionControlador.redireccionarAPagina('seguridad','gestionAplicaciones')}");
            subMenuAdmin.addElement(itemGesApl);
            
            DefaultMenuItem itemCatDet = new DefaultMenuItem("Gestión de Catalogos");
            itemCatDet.setIcon("ui-icon-person");
            itemCatDet.setCommand("#{navegacionControlador.redireccionarAPagina('base','catalogo_detalle')}");
            subMenuAdmin.addElement(itemCatDet);
            
            menu.addElement(subMenuAdmin);
            
            DefaultSubMenu subMenuPredios = new DefaultSubMenu("Predios");
            //---SubMenu Gestion Usuarios --
            DefaultMenuItem propietario = new DefaultMenuItem("Propietarios");
            propietario.setIcon("ui-icon-person");
            propietario.setCommand("#{navegacionControlador.redireccionarAPagina('base','propietario')}");
            subMenuPredios.addElement(propietario);
            
            DefaultMenuItem fichaCatastral = new DefaultMenuItem("Ficha Catastral");
            fichaCatastral.setIcon("ui-icon-person");
            fichaCatastral.setCommand("#{navegacionControlador.redireccionarAPagina('predio','ficha_catastral')}");
            subMenuPredios.addElement(fichaCatastral);
            
            
            
             DefaultMenuItem catalogoEdi = new DefaultMenuItem("Catalogo Materiales Edificacion");
            catalogoEdi.setIcon("ui-icon-person");
            catalogoEdi.setCommand("#{navegacionControlador.redireccionarAPagina('base','catalogo_edificaciones')}");
            subMenuPredios.addElement(catalogoEdi);
            
            menu.addElement(subMenuPredios);
            
            
            DefaultSubMenu subMenuImpuestos = new DefaultSubMenu("Impuestos y Servicios");
            
            DefaultMenuItem predial = new DefaultMenuItem("Predial");
            predial.setIcon("ui-icon-person");
            predial.setCommand("#{navegacionControlador.redireccionarAPagina('impuestos','gestionImpuestoPredial')}");
            subMenuImpuestos.addElement(predial);           
            DefaultMenuItem alcabala = new DefaultMenuItem("Alcabala y Plusvalia");
            alcabala.setIcon("ui-icon-person");
            alcabala.setCommand("#{navegacionControlador.redireccionarAPagina('impuestos','gestionAlcabalas')}");
            subMenuImpuestos.addElement(alcabala);
            DefaultMenuItem patenteImp = new DefaultMenuItem("Patente y 1.5 x 1000");
            patenteImp.setIcon("ui-icon-person");
            patenteImp.setCommand("#{navegacionControlador.redireccionarAPagina('patente','patente')}");
            subMenuImpuestos.addElement(patenteImp);
            
            DefaultMenuItem decTrib = new DefaultMenuItem("Declaración Tributaria");
            decTrib.setIcon("ui-icon-person");
            decTrib.setCommand("#{navegacionControlador.redireccionarAPagina('patente','declaracionTributaria')}");
            subMenuImpuestos.addElement(decTrib);
            
            DefaultMenuItem servicios = new DefaultMenuItem("Tasas por Servicios");
            servicios.setIcon("ui-icon-person");
            servicios.setCommand("#{navegacionControlador.redireccionarAPagina('base','gestionTasas')}");
            subMenuImpuestos.addElement(servicios);
            
            menu.addElement(subMenuImpuestos);
            
             DefaultSubMenu subMenurecaudacion = new DefaultSubMenu("Recaudacion");
            
            DefaultMenuItem recaudacion = new DefaultMenuItem("Recaudacion");
            recaudacion.setIcon("ui-icon-person");
            recaudacion.setCommand("#{navegacionControlador.redireccionarAPagina('recaudacion','recaudacion')}");
            subMenurecaudacion.addElement(recaudacion);  
            menu.addElement(subMenurecaudacion);

            DefaultMenuItem itemInicio = new DefaultMenuItem("Inicio");
            itemInicio.setIcon("ui-icon-bookmark");
            itemInicio.setCommand("#{navegacionControlador.redireccionarAPagina('','inicio')}");
            menu.addElement(itemInicio);

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Va a una pagina especifica
     */
    public void redireccionarAPagina(String carpeta, String pagina) {
        try {
            if (carpeta.equals("")) {
                redirect(getContextName() + "/paginas/" + pagina + ".xhtml");
            } else {
                redirect(getContextName() + "/paginas/" + carpeta + "/" + pagina + ".xhtml");
            }

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
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
     * Cierra la sesion del usuario logueado.
     */
    public void cerrarSesion() {
        try {
            this.getSession().setAttribute("usuario", null);
            this.getSession().invalidate();
            redirect(getContextName() + "/index.xhtml");
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public SegUsuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(SegUsuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public MenuModel getMenu() {
        return menu;
    }

    public void setMenu(MenuModel menu) {
        this.menu = menu;
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


    public List getListadoAplicaciones() {
        return listadoAplicaciones;
    }

    public void setListadoAplicaciones(List listadoAplicaciones) {
        this.listadoAplicaciones = listadoAplicaciones;
    }

}
