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
public class GestionAplicacionControlador extends BaseControlador {

    /**
     * Creates a new instance of GestionAgenciasControlador
     */
    //LOGGER 
    private static final Logger LOGGER = Logger.getLogger(GestionPermisosControlador.class.getName());
    //VARIABLES - ATRIBUTOS
    private SegAplicacion aplicacionActual = new SegAplicacion();
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

            listarAplicaciones();
            habilitaEdicion = false;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public GestionAplicacionControlador() {
    }
//METODOS

    public void guardarAplicacion() {
        try {
            if (habilitaEdicion == false) {
                if (aplicacionServicio.existeAplicacion(aplicacionActual.getApliCodigo())) {
                    addWarningMessage("Existe Código");
                } else {

                    aplicacionServicio.crearAplicacion(aplicacionActual);
                    addSuccessMessage("Aplicación Guardado");
                    listarAplicaciones();
                    aplicacionActual = new SegAplicacion();

                }
            } else {
                aplicacionServicio.editarAplicacion(aplicacionActual);
                addSuccessMessage("Aplicación Actualizado");
                listarAplicaciones();
                aplicacionActual = new SegAplicacion();
                habilitaEdicion = false;
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
    }

    public void refrescaNuevo() {
        try {
            aplicacionActual = new SegAplicacion();
            habilitaEdicion = false;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
    }

    public void recuperarAplicacionCampos(SegAplicacion aplicacion) {
        try {
            aplicacionActual = new SegAplicacion();
            aplicacionActual = aplicacion;
            habilitaEdicion = true;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        }

    }

    public void confirmaEliminar(SegAplicacion aplicacion) {
        try {
            aplicacionServicio.eliminarAplicacion(aplicacion);
            addSuccessMessage("Registro Eliminado");
            listarAplicaciones();
            aplicacionActual = new SegAplicacion();
            habilitaEdicion = false;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
            addWarningMessage("No se puede eliminar el regitro");
        }
    }

    public void listarAplicaciones() throws Exception {
        listadoAplicaciones = aplicacionServicio.listarAplicacionesTodos();
    }

   
    //GET Y SET 
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
   
}
