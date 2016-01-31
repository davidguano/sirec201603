/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.sirec.web.base;

import ec.sirec.ejb.entidades.Catalogo;
import ec.sirec.ejb.entidades.CatalogoDetalle;
import ec.sirec.ejb.servicios.CatalogoDetalleServicio;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author DAVID GUAN
 */
@ManagedBean
@ViewScoped
public class CatalogoDetControlador extends BaseControlador{
    
    private static final Logger LOGGER = Logger.getLogger(CatalogoDetControlador.class.getName());
    
    @EJB
    private CatalogoDetalleServicio catdetServicio;
    
    private List<CatalogoDetalle> listaCatDetallesEdificacion;
    
    
    private CatalogoDetalle catalogoDetalleActual;
    private List<CatalogoDetalle> listaCatDetalles;
    private List<Catalogo> listaCatalogos;
    /**
     * Creates a new instance of CatalogoDetControlador
     */
    public CatalogoDetControlador() {
    }
    
    @PostConstruct
    public void inicializar(){
        try{
            catalogoDetalleActual=new CatalogoDetalle();
            listaCatDetalles=new ArrayList<CatalogoDetalle>();
            listaCatDetallesEdificacion=new ArrayList<CatalogoDetalle>();
            listaCatalogos=catdetServicio.listaCatalogos();
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE,null,ex);
        }
    }
    
    public void listarDetallesPorCatalogo(){
        try{
            if(catalogoDetalleActual.getCatCodigo()!=null){
                
                listaCatDetalles=new ArrayList<CatalogoDetalle>();
                listaCatDetalles=catdetServicio.listarPorCodigoCatalogo(catalogoDetalleActual.getCatCodigo().getCatCodigo());
            }
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE,null,ex);
        }
    }
    
    public void guardarCatDetalle(){
        try{
            if(catalogoDetalleActual.getCatdetCodigo()==null){
                catdetServicio.crearCatDetalle(catalogoDetalleActual);
                addSuccessMessage("Detalle creado correctamente");
                listarDetallesPorCatalogo();
            }else{
                catdetServicio.editarCatDetalle(catalogoDetalleActual);
                addSuccessMessage("Detalle editado correctamente");
            }
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE,null,ex);
        }
    }
    
    public void seleccionarDetalle(CatalogoDetalle det){
        try{
            catalogoDetalleActual=det;
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE,null,ex);
        }
    }
    
    public void listarCatEdificacion(){
        try{
            listaCatDetallesEdificacion=new ArrayList<CatalogoDetalle>();
            listaCatDetallesEdificacion=catdetServicio.listarPorCatalogosIn("28,29,30");
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE,null,ex);
        }
    }
    
    public void onRowEdit(RowEditEvent event){
        try{
            catdetServicio.editarCatDetalle((CatalogoDetalle)event.getObject());
            addSuccessMessage("Valor editado correctamente");
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE,null,ex);
        }
    }
    public void onRowCancel(RowEditEvent event){
        
    }

    public List<CatalogoDetalle> getListaCatDetallesEdificacion() {
        return listaCatDetallesEdificacion;
    }

    public void setListaCatDetallesEdificacion(List<CatalogoDetalle> listaCatDetallesEdificacion) {
        this.listaCatDetallesEdificacion = listaCatDetallesEdificacion;
    }

    public CatalogoDetalle getCatalogoDetalleActual() {
        return catalogoDetalleActual;
    }

    public void setCatalogoDetalleActual(CatalogoDetalle catalogoDetalleActual) {
        this.catalogoDetalleActual = catalogoDetalleActual;
    }

    public List<CatalogoDetalle> getListaCatDetalles() {
        return listaCatDetalles;
    }

    public void setListaCatDetalles(List<CatalogoDetalle> listaCatDetalles) {
        this.listaCatDetalles = listaCatDetalles;
    }

    public List<Catalogo> getListaCatalogos() {
        return listaCatalogos;
    }

    public void setListaCatalogos(List<Catalogo> listaCatalogos) {
        this.listaCatalogos = listaCatalogos;
    }
    
    
    
}
