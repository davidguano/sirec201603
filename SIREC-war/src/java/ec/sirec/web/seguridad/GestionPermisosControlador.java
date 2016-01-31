package ec.sirec.web.seguridad;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import ec.sirec.ejb.entidades.SegAplicacion;
import ec.sirec.ejb.entidades.SegPermiso;
import ec.sirec.ejb.servicios.AplicacionServicio;
import ec.sirec.ejb.servicios.PermisoServicio;
import ec.sirec.web.base.BaseControlador;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * @author dvaldas
 */
@ManagedBean
@ViewScoped
public class GestionPermisosControlador extends BaseControlador {

    /**
     * Creates a new instance of GestionAgenciasControlador
     */
    //LOGGER 
    private static final Logger LOGGER = Logger.getLogger(GestionPermisosControlador.class.getName());
    //VARIABLES - ATRIBUTOS
    private SegPermiso permisoActual;
    private SegAplicacion aplicacionActual;
    private List<SegPermiso> listadoPermisos;
    private List<SegAplicacion> listadoAplicaciones;
    private boolean habilitaEdicion;

    //SERVICIOS
    @EJB
    private PermisoServicio permisoServicio;
    @EJB
    private AplicacionServicio aplicacionServicio;

    @PostConstruct
    public void inicializar() {
        try {

            listarPermisos();
            listarAplicaciones();
            habilitaEdicion = false;
            permisoActual = new SegPermiso();
            aplicacionActual = new SegAplicacion();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public GestionPermisosControlador() {
    }
//METODOS

    public void guardarPermiso() {
        try {
            if (habilitaEdicion == false) {
                if (permisoServicio.existePermiso(permisoActual.getPerCodigo())) {
                    addWarningMessage("Existe Código");
                } else {
                    permisoActual.setApliCodigo(aplicacionActual);
                    permisoServicio.crearPermiso(permisoActual);
                    addSuccessMessage("Permiso Guardado");
                    listarPermisos();
                    permisoActual = new SegPermiso();
                    aplicacionActual = new SegAplicacion();
                }
            } else {
                permisoActual.setApliCodigo(aplicacionActual);
                permisoServicio.editarPemiso(permisoActual);
                addSuccessMessage("Permiso Actualizado");
                listarPermisos();
                permisoActual = new SegPermiso();
                aplicacionActual = new SegAplicacion();
                habilitaEdicion = false;
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
    }

    public void refrescaNuevo() {
        try {
            permisoActual = new SegPermiso();
            aplicacionActual = new SegAplicacion();
            habilitaEdicion = false;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
    }

    public void recuperarPermisoCampos(SegPermiso permiso) {
        try {
            permisoActual = new SegPermiso();
            permisoActual = permiso;
            aplicacionActual = permisoActual.getApliCodigo();
            habilitaEdicion = true;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        }

    }

    public void confirmaEliminar(SegPermiso permiso) {
        try {
            permisoServicio.eliminarPermiso(permiso);
            addSuccessMessage("Registro Eliminado");
            listarPermisos();
            permisoActual = new SegPermiso();
            habilitaEdicion = false;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
            addWarningMessage("No se puede eliminar el regitro");
        }
    }

    public void listarAplicaciones() throws Exception {
        listadoAplicaciones = aplicacionServicio.listarAplicacionesTodos();
    }

    public void listarPermisos() throws Exception {
        listadoPermisos = permisoServicio.listarPermisos();
    }

    //GET Y SET 
    public boolean isHabilitaEdicion() {
        return habilitaEdicion;
    }

    public void setHabilitaEdicion(boolean habilitaEdicion) {
        this.habilitaEdicion = habilitaEdicion;
    }

    public List<SegPermiso> getListadoPermisos() {
        return listadoPermisos;
    }

    public void setListadoPermisos(List<SegPermiso> listadoPermisos) {
        this.listadoPermisos = listadoPermisos;
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

    public SegPermiso getPermisoActual() {
        return permisoActual;
    }

    public void setPermisoActual(SegPermiso permisoActual) {
        this.permisoActual = permisoActual;
    }

}
