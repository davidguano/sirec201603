/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.sirec.ejb.servicios;

import ec.sirec.ejb.entidades.Catalogo;
import ec.sirec.ejb.entidades.CatalogoDetalle;
import ec.sirec.ejb.facade.CatalogoDetalleFacade;
import ec.sirec.ejb.facade.CatalogoFacade;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author DAVID GUAN
 */
@Stateless
@LocalBean
public class CatalogoDetalleServicio {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB
    private CatalogoDetalleFacade catalogoDetalleDao;
    
    @EJB
    private CatalogoFacade catalogoDao;
    private final String ENTIDAD_CAT_DETALLE="CatalogoDetalle";
    
    public void crearCatDetalle(CatalogoDetalle catdet) throws Exception{
        catalogoDetalleDao.crear(catdet);
    }
    public void editarCatDetalle(CatalogoDetalle catdet) throws Exception{
        catalogoDetalleDao.editar(catdet);
    }
    public List<CatalogoDetalle> listarPorNemonicoCatalogo(String vnemonicoCatalogo) throws Exception{
        return catalogoDetalleDao.listarPor2CamposOrdenada(ENTIDAD_CAT_DETALLE, "catCodigo.catNemonico", vnemonicoCatalogo, "catdetEstado", true, "catdetOrden", "asc");
    }
    public List<CatalogoDetalle> listarPorCodigoCatalogo(Integer codigo) throws Exception{
        return catalogoDetalleDao.listarPor2CamposOrdenada(ENTIDAD_CAT_DETALLE, "catCodigo.catCodigo", codigo, "catdetEstado", true, "catdetOrden", "asc");
    }
    public List<CatalogoDetalle> listarPorNemonicoyCodigo(String vnemonicoCatalogo, String codigo) throws Exception{
        return catalogoDetalleDao.listarPor3CamposOrdenada(ENTIDAD_CAT_DETALLE, "catCodigo.catNemonico", vnemonicoCatalogo, "catdetCod",codigo,"catdetEstado", true, "catdetOrden", "asc");
    }
    
    public List<CatalogoDetalle> listarPorNemonicoyCodigoCatalogo(String vnemonicoCatalogo, String vcod) throws Exception{
        return catalogoDetalleDao.listarPor3CamposOrdenada(ENTIDAD_CAT_DETALLE, "catCodigo.catNemonico", vnemonicoCatalogo,"catdetCod",vcod, "catdetEstado", true, "catdetOrden", "asc");
    }
    public  CatalogoDetalle buscarPorCodigoCatDet(int codCatDet) throws Exception{
    return catalogoDetalleDao.buscarPorCampo(ENTIDAD_CAT_DETALLE, "catdetCodigo", codCatDet);
    }
    public List<CatalogoDetalle> listarPorNemonicoyTextoContiene(String vnemonicoCatalogo,String vTexto) throws Exception{
        return catalogoDetalleDao.listarPor1Campo1ContieneOrdenada(ENTIDAD_CAT_DETALLE, "catCodigo.catNemonico", vnemonicoCatalogo,"catdetTexto", vTexto.toUpperCase(),  "catdetOrden", "asc");
    }
    
    public List<CatalogoDetalle> listarPorNemonicoyItemsIn(String vnemonicoCatalogo, String listaItems) throws Exception{
        List<String> items = Arrays.asList(listaItems.split(","));
        return catalogoDetalleDao.listarPor1Campo1InOrdenada(ENTIDAD_CAT_DETALLE, "catCodigo.catNemonico", vnemonicoCatalogo, "catdetCod", items, "catdetOrden", "asc");
    }
    public List<CatalogoDetalle> listarPorCatalogosIn(String listaCatalogos) throws Exception{
        List<String> items = Arrays.asList(listaCatalogos.split(","));
        List<Integer> itemsI=new ArrayList<Integer>();
        for(String s:items){
            itemsI.add(Integer.valueOf(s));
        }
        return catalogoDetalleDao.listarPor1Campo1InOrdenada(ENTIDAD_CAT_DETALLE, "catdetEstado", true, "catCodigo.catCodigo", itemsI, "catCodigo.catCodigo,catdetOrden", "asc");
    }
    
    public List<Catalogo> listaCatalogos() throws Exception{
        return catalogoDao.listarOrdenada("Catalogo", "catNemonico", "asc");
    }
    public CatalogoDetalle buscarPoCatdetTexCatdetCod(String catdetTexto, String catdetCod) throws Exception {
        return catalogoDetalleDao.buscarPor2Campos(ENTIDAD_CAT_DETALLE, "catdetTexto", catdetTexto, "catdetCod", catdetCod);
    }
}
