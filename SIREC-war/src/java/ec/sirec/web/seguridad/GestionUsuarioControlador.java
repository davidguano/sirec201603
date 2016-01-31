/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sirec.web.seguridad;

import ec.sirec.ejb.entidades.SegAplicacion;
import ec.sirec.ejb.entidades.SegRol;
import ec.sirec.ejb.entidades.SegUsuario;
import ec.sirec.ejb.entidades.SegUsuarioRol;
import ec.sirec.ejb.servicios.AplicacionServicio;
import ec.sirec.ejb.servicios.RolServicio;
import ec.sirec.ejb.servicios.UsuarioRolServicio;
import ec.sirec.ejb.servicios.UsuarioServicio;
import ec.sirec.web.base.BaseControlador;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DualListModel;

/**
 *
 * @author dvaldas
 */
@ManagedBean
@ViewScoped
public class GestionUsuarioControlador extends BaseControlador {

    /**
     * Creates a new instance of ControladorUsuario
     */
    //LOGER
    private static final Logger LOGGER = Logger.getLogger(GestionUsuarioControlador.class.getName());
    private SegUsuario usuarioActual = new SegUsuario();
    private SegAplicacion aplicacionActual = new SegAplicacion();
    private List<SegUsuario> listaUsuarios;
    private List<SegAplicacion> listadoAplicaciones;
    private DualListModel<SegRol> roles;
    private List<SegRol> rolesAsignados;
    private List<SegRol> rolesNoAsignados;
    private boolean habilitaEdicion;
    private boolean habilitarParametroAplicacion;
    //SERVICIOS
    @EJB
    private UsuarioServicio usuarioServicio;
    @EJB
    private RolServicio rolServicio;
    @EJB
    private UsuarioRolServicio usuarioRolServicio;
    @EJB
    private AplicacionServicio aplicacionServicio;

    public GestionUsuarioControlador() {
    }

    @PostConstruct
    public void inicializar() {
        try {
            listarUsuarios();
            listarAplicaciones();
            rolesAsignados = new ArrayList<SegRol>();
            rolesNoAsignados = new ArrayList<SegRol>();
            roles = new DualListModel<SegRol>(rolesNoAsignados, rolesAsignados);
            habilitaEdicion = false;
            habilitarParametroAplicacion = false;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        }

    }

    public void guardarUsuario() {
        try {
            if (habilitaEdicion == false) {
                if (usuarioServicio.existeIdenUsuario(usuarioActual.getUsuIdentificacion())) {
                    addWarningMessage("Existe Código");
                } else {
                    String usuIdentificacion = usuarioActual.getUsuIdentificacion();
                    usuarioActual.setUsuIdentificacion(usuIdentificacion.replace(" ", "_"));
                    usuarioServicio.crearUsuario(usuarioActual);
                    addSuccessMessage("Usuario Guardado");
                    listarUsuarios();
                    usuarioActual = new SegUsuario();
                }
            } else {
                usuarioServicio.editarUsuario(usuarioActual);
                addSuccessMessage("Usuario Actualizado");
                listarUsuarios();
                roles = new DualListModel<SegRol>(rolesAsignados, rolesAsignados);
                usuarioActual = new SegUsuario();
                habilitaEdicion = false;
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
    }

    public void refrescaNuevo() {
        try {
            usuarioActual = new SegUsuario();
            aplicacionActual = new SegAplicacion();
            rolesAsignados = new ArrayList<SegRol>();
            rolesNoAsignados = new ArrayList<SegRol>();
            roles = new DualListModel<SegRol>(rolesAsignados, rolesAsignados);
            habilitaEdicion = false;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
    }

    public void recuperarUsuariosCampos(SegUsuario usuario) {
        try {
            usuarioActual = new SegUsuario();
            usuarioActual = usuario;
            habilitaEdicion = true;
            rolesAsignados = rolServicio.listarRolesDeUsuarioAsignados(usuarioActual.getUsuIdentificacion());
            if (rolesAsignados.isEmpty()) {
                rolesNoAsignados = rolServicio.listarRoles();
            } else {
                rolesNoAsignados = rolServicio.listarRolesDeUsuarioNoAsignados(rolesAsignados);
            }
            roles = new DualListModel<SegRol>(rolesNoAsignados, rolesAsignados);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
    }

    public void cargaPicLstParametroApli() {
        try {
            rolesAsignados = rolServicio.listarRolesDeUsuarioAsignados(usuarioActual.getUsuIdentificacion());
            if (rolesAsignados.isEmpty()) {
                if (aplicacionActual.getApliCodigo() != null) {
                    rolesNoAsignados = rolServicio.listarRolsParamApli(aplicacionActual.getApliCodigo());
                } else {
                    rolesNoAsignados = rolServicio.listarRoles();
                }
            } else {
                rolesNoAsignados = rolServicio.listarRolesDeUsuarioNoAsignadosParamertoAplicacion(rolesAsignados, aplicacionActual.getApliCodigo());
            }
            roles = new DualListModel<SegRol>(rolesNoAsignados, rolesAsignados);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        }

    }

    public void confirmaEliminar(SegUsuario usuario) {
        try {
            usuarioServicio.eliminarUsuario(usuario);
            addSuccessMessage("Registro Eliminado");
            listarUsuarios();
            usuarioActual = new SegUsuario();
            habilitaEdicion = false;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
            addWarningMessage("No se puede eliminar el registro");
        }
    }

    public void guardarRolesAsignadosDeUsuario() {
        try {
            for (SegRol rol : roles.getTarget()) {
                if (usuarioRolServicio.existeUsuarioRol(usuarioActual.getUsuIdentificacion(), rol.getRolCodigo())) {
                } else {
                    //grabar                
                    SegUsuarioRol ur = new SegUsuarioRol();
                    ur.setUsuIdentificacion(usuarioActual);
                    ur.setRolCodigo(rol);
                    usuarioServicio.editarUsuario(usuarioActual);
                    usuarioRolServicio.guardarUsuarioRol(ur);
                    listarRolesGuardados();
                }
            }
            for (SegRol rol : roles.getSource()) {
                if (usuarioRolServicio.existeUsuarioRol(usuarioActual.getUsuIdentificacion(), rol.getRolCodigo())) {
                    //elimina
                    SegUsuarioRol ur = usuarioRolServicio.buscarPorUsuarioRol(usuarioActual.getUsuIdentificacion(), rol.getRolCodigo());
                    usuarioRolServicio.eliminarUsuarioRol(ur.getUsurolCodigo());
                     listarRolesGuardados();
                }
            }
            addSuccessMessage("Roles Actualizados");
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public void listarRolesGuardados() {
        try {
            rolesAsignados = rolServicio.listarRolesDeUsuarioAsignados(usuarioActual.getUsuIdentificacion());
            if (rolesAsignados.isEmpty()) {
                rolesNoAsignados = rolServicio.listarRoles();
            } else {
                rolesNoAsignados = rolServicio.listarRolesDeUsuarioNoAsignados(rolesAsignados);
            }
            roles = new DualListModel<SegRol>(rolesNoAsignados, rolesAsignados);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public void listarUsuarios() throws Exception {
        listaUsuarios = usuarioServicio.listarUsuariosTodos();
    }

    public void listarAplicaciones() throws Exception {
        listadoAplicaciones = aplicacionServicio.listarAplicacionesTodos();
    }
    //GET Y SET

    public SegUsuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(SegUsuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public List<SegUsuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<SegUsuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public boolean isHabilitaEdicion() {
        return habilitaEdicion;
    }

    public void setHabilitaEdicion(boolean habilitaEdicion) {
        this.habilitaEdicion = habilitaEdicion;
    }

    public DualListModel<SegRol> getRoles() {
        return roles;
    }

    public void setRoles(DualListModel<SegRol> roles) {
        this.roles = roles;
    }

    public SegAplicacion getAplicacionActual() {
        return aplicacionActual;
    }

    public void setAplicacionActual(SegAplicacion aplicacionActual) {
        this.aplicacionActual = aplicacionActual;
    }

    public List<SegAplicacion> getListadoAplicaciones() {
        return listadoAplicaciones;
    }

    public void setListadoAplicaciones(List<SegAplicacion> listadoAplicaciones) {
        this.listadoAplicaciones = listadoAplicaciones;
    }

}
