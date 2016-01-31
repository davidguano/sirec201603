/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sirec.web.recaudacion;

import ec.sirec.ejb.entidades.Propietario;
import ec.sirec.ejb.entidades.RecaudacionCab;
import ec.sirec.ejb.entidades.RecaudacionDet;
import ec.sirec.ejb.servicios.PropietarioServicio;
import ec.sirec.ejb.servicios.RecaudacionServicio;
import ec.sirec.web.base.BaseControlador;
import ec.sirec.web.util.UtilitariosCod;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author dguano
 */
@ManagedBean
@ViewScoped
public class RecaudacionControlador extends BaseControlador{

    private static final Logger LOGGER = Logger.getLogger(RecaudacionControlador.class.getName());

    @EJB
    private RecaudacionServicio recaudacionServicio;
    @EJB
    private PropietarioServicio propietarioServicio;

    private RecaudacionCab recaudacionCabeceraActual;
    private Propietario propietarioBusqueda;
    private List<RecaudacionDet> listaRecaudacionDetalleActual;
    private List<RecaudacionCab> listaRecaudacionCabActual;

    /**
     * Creates a new instance of RecaudacionControlador
     */
    public RecaudacionControlador() {
    }
    
    @PostConstruct
    public void inicializar() {
        try {
            propietarioBusqueda=new Propietario();
            recaudacionCabeceraActual = new RecaudacionCab();
            listaRecaudacionDetalleActual = new ArrayList<RecaudacionDet>();
            listaRecaudacionCabActual = new ArrayList<RecaudacionCab>();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public List<Propietario> obtenerPropietarioPorCedula(String textoBusqueda) {
        List<Propietario> lstPro = new ArrayList<Propietario>();
        try {

            lstPro = propietarioServicio.listarPorCedulaContiene(textoBusqueda);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        return lstPro;
    }

    public List<Propietario> obtenerPropietarioPorApellidos(String textoBusqueda) {
        List<Propietario> lstPro = new ArrayList<Propietario>();
        try {
            lstPro = propietarioServicio.listarPorApellidosContiene(textoBusqueda);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        return lstPro;
    }

    public List<Propietario> obtenerPropietarioPorNombres(String textoBusqueda) {
        List<Propietario> lstPro = new ArrayList<Propietario>();
        try {
            lstPro = propietarioServicio.listarPorNombresContiene(textoBusqueda);

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        return lstPro;
    }

    
     public void listarRubrosARecaudar() {
        try {
            if (propietarioBusqueda != null) {
                listaRecaudacionCabActual=new ArrayList<RecaudacionCab>();
                listaRecaudacionCabActual=recaudacionServicio.listaRecaudacionesPorPropietario(propietarioBusqueda.getProCi());
                listaRecaudacionDetalleActual=new ArrayList<RecaudacionDet>();
                recaudacionCabeceraActual.setProCi(propietarioServicio.buscarPropietario(propietarioBusqueda.getProCi()));
                listaRecaudacionDetalleActual = recaudacionServicio.listaDetallesARecaudarPorCiAnio(recaudacionCabeceraActual.getProCi().getProCi(), null);
            }

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }
     
     public void guardarRecaudacion(){
          try {
           if(recaudacionCabeceraActual.getRecCodigo()==null){
               recaudacionCabeceraActual.setUsuIdentificacion(obtenerUsuarioAutenticado());
               recaudacionServicio.guardarRecaudacion(recaudacionCabeceraActual, listaRecaudacionDetalleActual);
               addSuccessMessage("Recaudacion creada correctamente");
           }else{
               recaudacionCabeceraActual.setUsuIdentificacion(obtenerUsuarioAutenticado());
               recaudacionServicio.editarRecaudacion(recaudacionCabeceraActual, listaRecaudacionDetalleActual);
               addSuccessMessage("Recaudacion editada correctamente");
           }

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
     }
     
     public void seleccionarRecaudacion(RecaudacionCab vrec){
         try{
             recaudacionCabeceraActual=vrec;
             listaRecaudacionDetalleActual=new ArrayList<RecaudacionDet>();
             listaRecaudacionDetalleActual=recaudacionServicio.listaDetallesARecaudados(vrec);
         }catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
     }
     public void generarTituloPredialUrbanoDesdeDetalle(RecaudacionDet det) {
         try{
             generarTituloPredialUrbano(det.getRecdetCodref());
         }catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
     }
     
     public String generarTituloPredialUrbano(Integer vcodValoracion) throws Exception {
        //Conexion con local datasource
        UtilitariosCod util = new UtilitariosCod();
        Connection conexion = util.getConexion();
        byte[] fichero = null;
        JasperReport jasperReport = null;
        Map parameters = new HashMap();
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
            ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
            session.removeAttribute("reporteInforme");
            parameters.put("cod_valoracion", vcodValoracion);
            parameters.put("logo_gad", servletContext.getRealPath("/imagenes/icons/gadPedroMoncayo.jpg"));
            

            jasperReport = (JasperReport) JRLoader.loadObject(servletContext.getRealPath("/reportes/recaudacion/titulo_predial_urbano.jasper"));
            fichero = JasperRunManager.runReportToPdf(jasperReport, parameters, conexion);
            session.setAttribute("reporteInforme", fichero);

        } catch (JRException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        } finally {
            if (conexion != null) {
                conexion.close();
            }
        }
        return null;
    }

    public RecaudacionCab getRecaudacionCabeceraActual() {
        return recaudacionCabeceraActual;
    }

    public void setRecaudacionCabeceraActual(RecaudacionCab recaudacionCabeceraActual) {
        this.recaudacionCabeceraActual = recaudacionCabeceraActual;
    }

    public List<RecaudacionDet> getListaRecaudacionDetalleActual() {
        return listaRecaudacionDetalleActual;
    }

    public void setListaRecaudacionDetalleActual(List<RecaudacionDet> listaRecaudacionDetalleActual) {
        this.listaRecaudacionDetalleActual = listaRecaudacionDetalleActual;
    }

    public Propietario getPropietarioBusqueda() {
        return propietarioBusqueda;
    }

    public void setPropietarioBusqueda(Propietario propietarioBusqueda) {
        this.propietarioBusqueda = propietarioBusqueda;
    }

    public List<RecaudacionCab> getListaRecaudacionCabActual() {
        return listaRecaudacionCabActual;
    }

    public void setListaRecaudacionCabActual(List<RecaudacionCab> listaRecaudacionCabActual) {
        this.listaRecaudacionCabActual = listaRecaudacionCabActual;
    }
    
    

}
