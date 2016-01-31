package ec.sirec.web.seguridad;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import ec.sirec.ejb.entidades.SegAplicacion;
import ec.sirec.ejb.entidades.SegPermiso;
import ec.sirec.ejb.entidades.SegPermisoRol;
import ec.sirec.ejb.entidades.SegRol;
import ec.sirec.ejb.servicios.AplicacionServicio;
import ec.sirec.ejb.servicios.PermisoRolServicio;
import ec.sirec.ejb.servicios.PermisoServicio;
import ec.sirec.ejb.servicios.RolServicio;
import ec.sirec.web.base.BaseControlador;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DualListModel;

/**
 * @author dvaldas
 */
@ManagedBean
@ViewScoped
public class GestionRolControlador extends BaseControlador {

    /**
     * Creates a new instance of GestionAgenciasControlador
     */
    //LOGGER 
    private static final Logger LOGGER = Logger.getLogger(GestionRolControlador.class.getName());
    //VARIABLES - ATRIBUTOS

    private List<SegRol> listadoRoles;
    private List<SegAplicacion> listadoAplicaciones;
    private DualListModel<SegPermiso> permisos;
    private List<SegPermiso> permisosAsignados;
    private List<SegPermiso> permisoNoAsignado;
    private boolean habilitaEdicion;
    private SegRol rolActual;
    private SegAplicacion aplicacionActual;
    private SegAplicacion aplicacionActualCambios;
    //SERVICIOS
    @EJB
    private RolServicio rolServicio;
    @EJB
    private AplicacionServicio aplicacionServicio;
    @EJB
    private PermisoServicio permisosServicio;
    @EJB
    private PermisoRolServicio permisoRolServicio;

    @PostConstruct
    public void inicializar() {
        try {
            listarRoles();
            listarAplicaciones();
            aplicacionActual = new SegAplicacion();
            aplicacionActualCambios = new SegAplicacion();
            rolActual = new SegRol();
            permisosAsignados = new ArrayList<SegPermiso>();
            permisoNoAsignado = new ArrayList<SegPermiso>();
            permisos = new DualListModel<SegPermiso>(permisoNoAsignado, permisosAsignados);
            habilitaEdicion = false;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public GestionRolControlador() {
    }
//METODOS

    public void guardarRol() {
        try {
            if (habilitaEdicion == false) {
                if (rolServicio.existeCodigoRol(rolActual.getRolCodigo())) {
                    addWarningMessage("Existe Código");
                } else {
                    rolActual.setApliCodigo(aplicacionActualCambios);
                    rolServicio.creaRol(rolActual);
                    addSuccessMessage("Rol Guardado");
                    listarRoles();
                    rolActual = new SegRol();
                    aplicacionActualCambios = new SegAplicacion();
                    aplicacionActual = new SegAplicacion();
                    permisosAsignados = new ArrayList<SegPermiso>();
                    permisoNoAsignado = new ArrayList<SegPermiso>();
                    permisos = new DualListModel<SegPermiso>(permisoNoAsignado, permisosAsignados);
                }
            } else {
                rolActual.setApliCodigo(aplicacionActualCambios);
                rolServicio.editaRol(rolActual);
                addSuccessMessage("Rol Actualizado");
                listarRoles();
                rolActual = new SegRol();
                habilitaEdicion = false;
                aplicacionActualCambios = new SegAplicacion();
                aplicacionActual = new SegAplicacion();
                permisosAsignados = new ArrayList<SegPermiso>();
                permisoNoAsignado = new ArrayList<SegPermiso>();
                permisos = new DualListModel<SegPermiso>(permisoNoAsignado, permisosAsignados);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
    }

    public void refrescaNuevo() {
        try {
            aplicacionActual = new SegAplicacion();
            aplicacionActualCambios = new SegAplicacion();
            permisosAsignados = new ArrayList<SegPermiso>();
            permisoNoAsignado = new ArrayList<SegPermiso>();
            permisos = new DualListModel<SegPermiso>(permisoNoAsignado, permisosAsignados);
            rolActual = new SegRol();
            habilitaEdicion = false;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
    }

    public void recuperarRolCampos(SegRol rol) {
        try {
            rolActual = new SegRol();
            rolActual = rol;
            aplicacionActual = rolActual.getApliCodigo();
            aplicacionActualCambios = rolActual.getApliCodigo();
            habilitaEdicion = true;
            //cargar combo nombre de rol o funcionalidad seleccionada
            permisosAsignados = permisosServicio.listarPermisosRolAsignados(rolActual.getRolCodigo());
            if (permisosAsignados.isEmpty()) {
                permisoNoAsignado = permisosServicio.listarPermisos();
            } else {
                permisoNoAsignado = permisosServicio.listarPermisosRolNoAsignados(permisosAsignados);
            }
            permisos = new DualListModel<SegPermiso>(permisoNoAsignado, permisosAsignados);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
    }

    public void guardarPermisosDeRol() {
        try {
            for (SegPermiso per : permisos.getTarget()) {
                if (permisoRolServicio.existeRolPermiso(rolActual.getRolCodigo(), per.getPerCodigo())) {
                } else {
                    //grabar
                    SegPermisoRol rper = new SegPermisoRol();
                    rper.setRolCodigo(rolActual);
                    rper.setPerCodigo(per);
                    permisoRolServicio.guardarRolPermiso(rper);
                    listarPermisosGuardados();
                }
            }
            for (SegPermiso per : permisos.getSource()) {
                if (permisoRolServicio.existeRolPermiso(rolActual.getRolCodigo(), per.getPerCodigo())) {
                    //elimina
                    SegPermisoRol rper = permisoRolServicio.buscarPorRolPermiso(rolActual.getRolCodigo(), per.getPerCodigo());
                    permisoRolServicio.eliminarRolPermiso(rper.getPerrolCodigo());
                    listarPermisosGuardados();
                }
            }
            addSuccessMessage("Permisos Actualizados");
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public void listarPermisosGuardados() {
        try {
            //cargar combo nombre de rol o funcionalidad seleccionada
            permisosAsignados = permisosServicio.listarPermisosRolAsignados(rolActual.getRolCodigo());
            if (permisosAsignados.isEmpty()) {
                permisoNoAsignado = permisosServicio.listarPermisos();
            } else {
                permisoNoAsignado = permisosServicio.listarPermisosRolNoAsignados(permisosAsignados);
            }
            permisos = new DualListModel<SegPermiso>(permisoNoAsignado, permisosAsignados);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
    }

    public void cargaPicLstParametroApli() {
        try {
            permisosAsignados = permisosServicio.listarPermisosRolAsignados(rolActual.getRolCodigo());
            if (permisosAsignados.isEmpty()) {
                if (aplicacionActual.getApliCodigo() != null) {
                    permisoNoAsignado = permisosServicio.listarPermisoParamApli(aplicacionActual.getApliCodigo());
                } else {
                    permisoNoAsignado = permisosServicio.listarPermisos();
                }
            } else {
                permisoNoAsignado = permisosServicio.listarPermisosDeRolesNoAsignadosParamertoAplicacion(permisosAsignados, aplicacionActual.getApliCodigo());
            }
            permisos = new DualListModel<SegPermiso>(permisoNoAsignado, permisosAsignados);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        }

    }

    public void confirmaEliminar(SegRol rol) {
        try {
            rolServicio.eliminaRol(rol.getRolCodigo());
            addSuccessMessage("Registro Eliminado");
            listarRoles();
            rolActual = new SegRol();
            habilitaEdicion = false;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
            addWarningMessage("No se puede eliminar el regitro");
        }
    }

    public void listarRoles() throws Exception {
        listadoRoles = rolServicio.listarRolesTodos();
    }

    public void listarAplicaciones() throws Exception {
        listadoAplicaciones = aplicacionServicio.listarAplicacionesTodos();
    }

    //GET Y SET 
    public SegRol getRolActual() {
        return rolActual;
    }

    public void setRolActual(SegRol rolActual) {
        this.rolActual = rolActual;
    }

    public List<SegRol> getListadoRoles() {
        return listadoRoles;
    }

    public void setListadoRoles(List<SegRol> listadoRoles) {
        this.listadoRoles = listadoRoles;
    }

    public boolean isHabilitaEdicion() {
        return habilitaEdicion;
    }

    public void setHabilitaEdicion(boolean habilitaEdicion) {
        this.habilitaEdicion = habilitaEdicion;
    }

    public List<SegAplicacion> getListadoAplicaciones() {
        return listadoAplicaciones;
    }

    public void setListadoAplicaciones(List<SegAplicacion> listadoAplicaciones) {
        this.listadoAplicaciones = listadoAplicaciones;
    }

    public SegAplicacion getAplicacionActual() {
        return aplicacionActual;
    }

    public void setAplicacionActual(SegAplicacion aplicacionActual) {
        this.aplicacionActual = aplicacionActual;
    }

    public DualListModel<SegPermiso> getPermisos() {
        return permisos;
    }

    public void setPermisos(DualListModel<SegPermiso> permisos) {
        this.permisos = permisos;
    }

    public SegAplicacion getAplicacionActualCambios() {
        return aplicacionActualCambios;
    }

    public void setAplicacionActualCambios(SegAplicacion aplicacionActualCambios) {
        this.aplicacionActualCambios = aplicacionActualCambios;
    }

}
