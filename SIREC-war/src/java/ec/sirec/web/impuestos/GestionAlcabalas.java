/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sirec.web.impuestos;


import ec.sirec.ejb.entidades.AdicionalesDeductivos;
import ec.sirec.ejb.entidades.CatalogoDetalle;
import ec.sirec.ejb.entidades.CatastroPredial;
import ec.sirec.ejb.entidades.CatastroPredialAlcabalaValoracion;
import ec.sirec.ejb.entidades.CatastroPredialValoracion;
import ec.sirec.ejb.entidades.CpValoracionExtras;
import ec.sirec.ejb.entidades.PredioArchivo;
import ec.sirec.ejb.entidades.SegUsuario;
import ec.sirec.ejb.servicios.AdicionalesDeductivosServicio;
import ec.sirec.ejb.servicios.CatalogoDetalleServicio;
import ec.sirec.ejb.servicios.CatastroPredialAlcabalaValoracionServicio;
import ec.sirec.ejb.servicios.CatastroPredialServicio;
import ec.sirec.ejb.servicios.CatastroPredialValoracionServicio;
import ec.sirec.ejb.servicios.CpValoracionExtrasServicio;
import ec.sirec.ejb.servicios.PredioArchivoServicio;
import ec.sirec.web.base.BaseControlador;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author vespinoza
 */
@ManagedBean
@ViewScoped
public class GestionAlcabalas extends BaseControlador {

    /**
     * Creates a new instance of GestionConceptoControlador
     */
    //LOGGER 
    private static final Logger LOGGER = Logger.getLogger(GestionAlcabalas.class.getName());
    // VARIABLES Y ATRIBUTOS

     private SegUsuario usuarioActual;
     private CatastroPredial catastroPredialActual;
     private CatastroPredialAlcabalaValoracion catastroPredialAlcabalaValoracion;
     private CatalogoDetalle catalogoDetalleConcepto;
    
     private List<CatastroPredial> listaCatastroPredial;
     private List<CatalogoDetalle> listaCatalogoDetalleConcepto;
     private CatastroPredialValoracion catastroPredialValoracionActual;
     
     
     
     private AdicionalesDeductivos adicionalesDeductivosActual;
     private CpValoracionExtras cpValoracionExtrasActual;
     private StreamedContent archivo;
     
    
    // SERVICIOS
    @EJB
    private CatastroPredialServicio catastroPredialServicio;
    @EJB
    private CatalogoDetalleServicio catalogoDetalleServicio; 
    @EJB
    private CatastroPredialValoracionServicio catastroPredialValoracionServicio;
    @EJB
    private CatastroPredialAlcabalaValoracionServicio catastroPredialAlcabalaValoracionServicio;
     
     
    @EJB
    private AdicionalesDeductivosServicio adicionalesDeductivosServicio;     
    @EJB
    private PredioArchivoServicio predioArchivoServicio;   
    //@EJB
    //private AdicionalesDeductivosServicio adicionalesDeductivosServicio;
    @EJB
    private CpValoracionExtrasServicio cpValoracionExtrasServicio;
    
     

    @PostConstruct
    public void inicializar() {
        try {      
            
            catastroPredialAlcabalaValoracion = new CatastroPredialAlcabalaValoracion();
            catastroPredialActual = new CatastroPredial();  
            catalogoDetalleConcepto = new CatalogoDetalle();
            
            listarCatastroPredial();            
            obtenerUsuario();    
            listarConceptos();
                      
            // listaPredioArchivo =new ArrayList<PredioArchivo>();
             
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }
    
    public void obtenerUsuario(){
    usuarioActual = new SegUsuario (); 
     usuarioActual = (SegUsuario) getSession().getAttribute("usuario");           
           //System.out.println(usuarioActual.getUsuIdentificacion());         
    }

    public GestionAlcabalas() {
    }

      public void listarCatastroPredial(){
       try {
           listaCatastroPredial = new ArrayList<CatastroPredial>();
          listaCatastroPredial = catastroPredialServicio.listarClaveCatastral();
           
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
      }
      
       public void listarConceptos(){
       try {
           
           listaCatalogoDetalleConcepto = new ArrayList<CatalogoDetalle>();           
           listaCatalogoDetalleConcepto = catalogoDetalleServicio.listarPorNemonicoCatalogo("CONCEPTOS");
          
          
           
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
      }
       
      public void obtenerCamposCatPredial(){
       try {
           
          catastroPredialActual = catastroPredialServicio.cargarObjetoCatastro(catastroPredialActual.getCatpreCodigo());
          catastroPredialActual.setCatpreAreaTotal(catastroPredialActual.getCatpreAreaTotalEsc()+catastroPredialActual.getCatpreAreaTotalCons());                              
          
           catastroPredialValoracionActual = new CatastroPredialValoracion();
           catastroPredialValoracionActual = catastroPredialValoracionServicio.buscarPorCatastroPredial(catastroPredialActual);
          
         } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
      }
    
      public void calcularDeterminacion(){
       try {
           
           BigDecimal baseImponible;
           if ( catastroPredialValoracionActual.getCatprevalAvaluoTerr().compareTo(catastroPredialAlcabalaValoracion.getCatprealcvalPrecioventa()) ==-1 ){               
               baseImponible = catastroPredialAlcabalaValoracion.getCatprealcvalPrecioventa();	
           }else{
               baseImponible = catastroPredialValoracionActual.getCatprevalAvaluoTerr();
           }           
           BigDecimal impuesto = baseImponible.multiply(new BigDecimal(1)).divide(new BigDecimal(100)); 
           BigDecimal conProv = baseImponible.multiply(new BigDecimal(0.01)).divide(new BigDecimal(100)); 
           //conProv = conProv.setScale(2, RoundingMode.CEILING);
           
           catastroPredialAlcabalaValoracion.setCatprealcvalBaseimp(baseImponible);
           catastroPredialAlcabalaValoracion.setCatprealcvalImpuesto(impuesto);
           catastroPredialAlcabalaValoracion.setCatprealcvalConsejoProv(conProv); 
           catastroPredialAlcabalaValoracion.setCatprealcvalTasaProc(new BigDecimal(2)); 
           catastroPredialAlcabalaValoracion.setCatprealcvalTotal(impuesto.add(conProv).add(catastroPredialAlcabalaValoracion.getCatprealcvalTasaProc()).setScale(2, RoundingMode.CEILING)); 
                                 
       } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
      }
      
       public void guardarAlcabala(){
           try{
               
            catastroPredialAlcabalaValoracion.setCatpreCodigo(catastroPredialActual); 
           catastroPredialAlcabalaValoracionServicio.crearCatastroPredialAlcabalaValoracion(catastroPredialAlcabalaValoracion);
            addSuccessMessage("Guardado Exitosamente!");
           } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
       }
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
       
       
      
      
      
      
      
      
       public void listarArchivos(){
       try {
//           listaPredioArchivo = new ArrayList<PredioArchivo>();
//           listaPredioArchivo = predioArchivoServicio.listarArchivos(usuarioActual);
//           
//           System.out.println("s:  "+listaPredioArchivo.size());
           
           } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
           
      }
      
        public void eliminarArchivo(PredioArchivo archivo) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            predioArchivoServicio.eliminarPredioArchivo(archivo);
            context.addMessage(null, new FacesMessage("Mensaje:", "Se Elimino el Archivo  " + archivo.getPrearcNombre()));
            listarArchivos();

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }
       
       public void handleFileUpload(FileUploadEvent event){
           
            try {
//      predioArchivo = new PredioArchivo();            
//      predioArchivo.setPrearcNombre(event.getFile().getFileName());
//      predioArchivo.setCatpreCodigo(catastroPredialActual); 
//      predioArchivo.setPrearcData(event.getFile().getContents());
//      predioArchivo.setPrearcTipo("PR");      
//      predioArchivo.setUsuIdentificacion(usuarioActual);
//      predioArchivo.setUltaccDetalle("");
//      predioArchivo.setUltaccMarcatiempo(new Date());
//      
    //  predioArchivoServicio.crearPredioArchivo(predioArchivo);            
      
        FacesMessage msg = new FacesMessage("El documento ", event.getFile().getFileName() + " ha sido cargado satisfactoriamente.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
         listarArchivos();
        
        
         } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }
      
    public void startDownload(PredioArchivo archivo) {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance()
                .getExternalContext().getResponse();
        try {
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment;filename=" + archivo.getPrearcNombre());
            response.getOutputStream().write(archivo.getPrearcData());
            response.getOutputStream().flush();
            response.getOutputStream().close();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (IOException ioex) {
            LOGGER.log(Level.SEVERE, null, ioex);
        }
    }   
    
    
    public void guardarAdicionalesDeductivos(){        
     try {
                   
//       adicionalesDeductivosActual = new AdicionalesDeductivos();               
//       catastroPredialValoracionActual = new CatastroPredialValoracion();
//          try {
//      if(listaPredioArchivo.size()>0){
//            
//         for (int i = 0; i < listaAdicionalesDeductivosRecargosSeleccion.size(); i++) {
//             cpValoracionExtrasActual = new CpValoracionExtras();
//             adicionalesDeductivosActual = adicionalesDeductivosServicio.buscarAdicionesDeductivosXCodigo(Integer.parseInt(listaAdicionalesDeductivosRecargosSeleccion.get(i)));             
//             catastroPredialValoracionActual = catastroPredialValoracionServicio.buscarPorCatastroPredial(catastroPredialActual);
//             cpValoracionExtrasActual.setCatprevalCodigo(catastroPredialValoracionActual);
//             cpValoracionExtrasActual.setAdidedCodigo(adicionalesDeductivosActual);
////            cpValoracionExtrasActual.setCpvalextBase(BigDecimal.ONE); 
////            cpValoracionExtrasActual.setCpvalextValor(BigDecimal.ZERO);             
//             cpValoracionExtrasServicio.crearCpValoracionExtras(cpValoracionExtrasActual);
//         }
//         
//         for (int i = 0; i < listaAdicionalesDeductivosExoneracionesSeleccion.size(); i++) {
//             cpValoracionExtrasActual = new CpValoracionExtras();
//             adicionalesDeductivosActual = adicionalesDeductivosServicio.buscarAdicionesDeductivosXCodigo(Integer.parseInt(listaAdicionalesDeductivosExoneracionesSeleccion.get(i)));             
//             catastroPredialValoracionActual = catastroPredialValoracionServicio.buscarPorCatastroPredial(catastroPredialActual);
//             cpValoracionExtrasActual.setCatprevalCodigo(catastroPredialValoracionActual);
//             cpValoracionExtrasActual.setAdidedCodigo(adicionalesDeductivosActual);
////            cpValoracionExtrasActual.setCpvalextBase(BigDecimal.ONE); 
////            cpValoracionExtrasActual.setCpvalextValor(BigDecimal.ZERO);             
//             cpValoracionExtrasServicio.crearCpValoracionExtras(cpValoracionExtrasActual);
//         }
//
//         for (int i = 0; i < listaAdicionalesDeductivosDeduccionesSeleccion.size(); i++) {
//             cpValoracionExtrasActual = new CpValoracionExtras();
//             adicionalesDeductivosActual = adicionalesDeductivosServicio.buscarAdicionesDeductivosXCodigo(Integer.parseInt(listaAdicionalesDeductivosDeduccionesSeleccion.get(i)));             
//             catastroPredialValoracionActual = catastroPredialValoracionServicio.buscarPorCatastroPredial(catastroPredialActual);
//             cpValoracionExtrasActual.setCatprevalCodigo(catastroPredialValoracionActual);
//             cpValoracionExtrasActual.setAdidedCodigo(adicionalesDeductivosActual);
////            cpValoracionExtrasActual.setCpvalextBase(BigDecimal.ONE); 
////            cpValoracionExtrasActual.setCpvalextValor(BigDecimal.ZERO);             
//             cpValoracionExtrasServicio.crearCpValoracionExtras(cpValoracionExtrasActual);
//         }
//         
//          addSuccessMessage("Guardado Exitosamente!");
//          
//         
//         }else{
//          addSuccessMessage("No existen documentos cargados!");
//          
////          FacesMessage msg = new FacesMessage("No se han cargado documentos!");
////        FacesContext.getCurrentInstance().addMessage(null, msg);
//      
//      }
//         } catch (NullPointerException exNull) {
//           // LOGGER.log(Level.SEVERE, null, exNull);
//             addSuccessMessage("No se han cargado documentos!");
////              FacesMessage msg = new FacesMessage("No se han cargado documentos!");
////        FacesContext.getCurrentInstance().addMessage(null, msg);
//        }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
         
        }
        
    }
       
    // METODOS

   

    public StreamedContent getArchivo() {
        return archivo;
    }

    public void setArchivo(StreamedContent archivo) {
        this.archivo = archivo;
    }

    public List<CatastroPredial> getListaCatastroPredial() {
        return listaCatastroPredial;
    }

    public void setListaCatastroPredial(List<CatastroPredial> listaCatastroPredial) {
        this.listaCatastroPredial = listaCatastroPredial;
    }

    public CatastroPredial getCatastroPredialActual() {
        return catastroPredialActual;
    }

    public void setCatastroPredialActual(CatastroPredial catastroPredialActual) {
        this.catastroPredialActual = catastroPredialActual;
    }

    public CatastroPredialAlcabalaValoracion getCatastroPredialAlcabalaValoracion() {
        return catastroPredialAlcabalaValoracion;
    }

    public void setCatastroPredialAlcabalaValoracion(CatastroPredialAlcabalaValoracion catastroPredialAlcabalaValoracion) {
        this.catastroPredialAlcabalaValoracion = catastroPredialAlcabalaValoracion;
    }

    public List<CatalogoDetalle> getListaCatalogoDetalleConcepto() {
        return listaCatalogoDetalleConcepto;
    }

    public void setListaCatalogoDetalleConcepto(List<CatalogoDetalle> listaCatalogoDetalleConcepto) {
        this.listaCatalogoDetalleConcepto = listaCatalogoDetalleConcepto;
    }

    public CatalogoDetalle getCatalogoDetalleConcepto() {
        return catalogoDetalleConcepto;
    }

    public void setCatalogoDetalleConcepto(CatalogoDetalle catalogoDetalleConcepto) {
        this.catalogoDetalleConcepto = catalogoDetalleConcepto;
    }

    public CatastroPredialValoracion getCatastroPredialValoracionActual() {
        return catastroPredialValoracionActual;
    }

    public void setCatastroPredialValoracionActual(CatastroPredialValoracion catastroPredialValoracionActual) {
        this.catastroPredialValoracionActual = catastroPredialValoracionActual;
    }
    
    
}
