/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sirec.web.base;

import ec.sirec.web.impuestos.*;
import ec.sirec.ejb.clases.EjecutarValoracion;
import ec.sirec.ejb.entidades.AdicionalesDeductivos;
import ec.sirec.ejb.entidades.CatalogoDetalle;
import ec.sirec.ejb.entidades.CatastroPredial;
import ec.sirec.ejb.entidades.CatastroPredialAlcabalaValoracion;
import ec.sirec.ejb.entidades.CatastroPredialPlusvaliaValoracion;
import ec.sirec.ejb.entidades.CatastroPredialValoracion;
import ec.sirec.ejb.entidades.CpAlcabalaValoracionExtras;
import ec.sirec.ejb.entidades.PredioArchivo;
import ec.sirec.ejb.entidades.Propietario;
import ec.sirec.ejb.entidades.SegUsuario;
import ec.sirec.ejb.entidades.Tasa;
import ec.sirec.ejb.servicios.AdicionalesDeductivosServicio;
import ec.sirec.ejb.servicios.CatalogoDetalleServicio;
import ec.sirec.ejb.servicios.CatastroPredialAlcabalaValoracionServicio;
import ec.sirec.ejb.servicios.CatastroPredialPlusvaliaValoracionServicio;
import ec.sirec.ejb.servicios.CatastroPredialServicio;
import ec.sirec.ejb.servicios.CatastroPredialValoracionServicio;
import ec.sirec.ejb.servicios.CpAlcabalaValoracionExtrasServicio;
import ec.sirec.ejb.servicios.CpValoracionExtrasServicio;
import ec.sirec.ejb.servicios.DatoGlobalServicio;
import ec.sirec.ejb.servicios.PredioArchivoServicio;
import ec.sirec.ejb.servicios.TasaServicio;
import ec.sirec.web.base.BaseControlador;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author vespinoza
 */
@ManagedBean
@ViewScoped
public class GestionTasasControlador extends BaseControlador {

    /**
     * Creates a new instance of GestionConceptoControlador
     */
    //LOGGER 
    private static final Logger LOGGER = Logger.getLogger(GestionTasasControlador.class.getName());
    // VARIABLES Y ATRIBUTOS

    private SegUsuario usuarioActual;
    private Tasa tasasActual;
    private String tipoSigno;
    private String opcionBoton;
    
     private List<CatalogoDetalle> listaCatalogoDetalleDepartamentos;
     private List<CatalogoDetalle> listaCatalogoDetalleTipoValor;
     private List<Tasa> listaTasas;
      
    @EJB
    private CatalogoDetalleServicio catalogoDetalleServicio;
    @EJB
    private CatastroPredialServicio catastroPredialServicio;
    @EJB
    private TasaServicio tasaServicio;

    @PostConstruct
    public void inicializar() {
        try {
             tasasActual= new Tasa();
             opcionBoton = "N";
             tipoSigno="";
             obtenerUsuario();             
             listarDepartamentos();
             listarTipoValor();
             listarTasas();
             
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    
    public void listarDepartamentos() {
        try {
            listaCatalogoDetalleDepartamentos = new ArrayList<CatalogoDetalle>();
            listaCatalogoDetalleDepartamentos = catalogoDetalleServicio.listarPorNemonicoCatalogo("UNI_ADMIN");
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }
    public void listarTipoValor() {
        try {
            listaCatalogoDetalleTipoValor = new ArrayList<CatalogoDetalle>();
            listaCatalogoDetalleTipoValor = catalogoDetalleServicio.listarPorNemonicoCatalogo("TIP_VAL_TASA");
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }
    public void definirTipo() {
        try {            
            tipoSigno = catastroPredialServicio.cargarObjetoCatalogoDetalle(tasasActual.getCatdetTipoValor().getCatdetCodigo()).getCatdetCod();                        
        } catch (Exception ex) {
            tipoSigno="";
          //  LOGGER.log(Level.SEVERE, null, ex);
        }
    }
    
    public void guardarIngresoTasa() {
        try {
            if(opcionBoton.equals("N")){
             tasaServicio.crearTasa(tasasActual);
             addSuccessMessage("Guardado exitosamente","Guardado exitosamente");     
            }else{
                if(opcionBoton.equals("E")){
                    tasaServicio.editarTasa(tasasActual);
                    
            addSuccessMessage("Se ha modificado el registro","Se ha modificado el registro");                 
            }            
            }                       
            listarTasas();                               
            nuevoIngresoTasa();             
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }
    
    public void nuevoIngresoTasa() {
        try {                        
            tasasActual = new Tasa();
            tipoSigno = "";
            opcionBoton="N";
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }
    
    public void listarTasas() {
        try {        
            listaTasas = new ArrayList<Tasa>();
            listaTasas = tasaServicio.listarTasa();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void obtenerUsuario() {
        try { 
        usuarioActual = new SegUsuario();
        usuarioActual = (SegUsuario) getSession().getAttribute("usuario");     
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }
    
    public void recuperarTasa(Tasa tasa) {
        try {
            opcionBoton="E";
            tasasActual = new Tasa(); 
            tasasActual = tasa;                        
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }
    
    public void eliminarTasa(Tasa tasa) {
        try {            
            tasaServicio.eliminarTasa(tasa);             
            addSuccessMessage("Registro Eliminado","Registro Eliminado"); 
            listarTasas();
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

    public Tasa getTasasActual() {
        return tasasActual;
    }

    public void setTasasActual(Tasa tasasActual) {
        this.tasasActual = tasasActual;
    }

    public List<CatalogoDetalle> getListaCatalogoDetalleDepartamentos() {
        return listaCatalogoDetalleDepartamentos;
    }

    public void setListaCatalogoDetalleDepartamentos(List<CatalogoDetalle> listaCatalogoDetalleDepartamentos) {
        this.listaCatalogoDetalleDepartamentos = listaCatalogoDetalleDepartamentos;
    }

    public List<CatalogoDetalle> getListaCatalogoDetalleTipoValor() {
        return listaCatalogoDetalleTipoValor;
    }

    public void setListaCatalogoDetalleTipoValor(List<CatalogoDetalle> listaCatalogoDetalleTipoValor) {
        this.listaCatalogoDetalleTipoValor = listaCatalogoDetalleTipoValor;
    }

    public String getTipoSigno() {
        return tipoSigno;
    }

    public void setTipoSigno(String tipoSigno) {
        this.tipoSigno = tipoSigno;
    }

    public List<Tasa> getListaTasas() {
        return listaTasas;
    }

    public void setListaTasas(List<Tasa> listaTasas) {
        this.listaTasas = listaTasas;
    }

    public String getOpcionBoton() {
        return opcionBoton;
    }

    public void setOpcionBoton(String opcionBoton) {
        this.opcionBoton = opcionBoton;
    }

    

    
    
}