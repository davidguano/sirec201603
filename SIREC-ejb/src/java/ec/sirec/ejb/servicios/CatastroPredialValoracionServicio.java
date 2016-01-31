/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.sirec.ejb.servicios;

import ec.sirec.ejb.entidades.CatalogoDetalle;
import ec.sirec.ejb.entidades.CatastroPredial;
import ec.sirec.ejb.entidades.CatastroPredialValoracion;
import ec.sirec.ejb.facade.CatastroPredialValoracionFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author vespinoza
 */
@Stateless
@LocalBean
public class CatastroPredialValoracionServicio {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB
    private CatastroPredialValoracionFacade catastroPredialValoracionDao;
    private final String ENTIDAD_CATASTRO_PREDIAL_VALORACION="CatastroPredialValoracion";
    
    public String crearAplicacion(CatastroPredialValoracion catastroPredialValoracion) throws Exception {
        catastroPredialValoracionDao.crear(catastroPredialValoracion);
        return "Se ha creado la aplicación" + catastroPredialValoracion.getCatpreCodigo();
    }

    public String editarAplicacion(CatastroPredialValoracion catastroPredialValoracion) throws Exception {
        catastroPredialValoracionDao.editar(catastroPredialValoracion);
        return "Se ha modificado la aplicación" + catastroPredialValoracion.getCatpreCodigo();
    }
    
     public CatastroPredialValoracion buscarPorCatastroPredial(CatastroPredial catastroPredial) throws Exception {
        return catastroPredialValoracionDao.buscarPorCampo(ENTIDAD_CATASTRO_PREDIAL_VALORACION, "catpreCodigo", catastroPredial);
    }
    
     public CatastroPredialValoracion existeCatastroValoracion(CatastroPredial catastroPredial, Integer anio) throws Exception {
        return catastroPredialValoracionDao.buscarPor2Campos(ENTIDAD_CATASTRO_PREDIAL_VALORACION, "catpreCodigo", catastroPredial, "catprevalAnio", anio);
    }
     
    public List<CatastroPredialValoracion> buscarValoracionXClaveCatastral(CatastroPredial catastroPredial, Integer anio) throws Exception {
        return catastroPredialValoracionDao.listarPor2CamposOrdenadaGenerico(ENTIDAD_CATASTRO_PREDIAL_VALORACION, "CatastroPredial", "catpreCodigo", catastroPredial.getCatpreCodigo(), "catprevalAnio", anio, "catprevalAnio", "asc");
    }
    
    public List<CatastroPredialValoracion> listarCatastroXParroquia(CatalogoDetalle codParroquia,Integer anio) throws Exception {       
       return catastroPredialValoracionDao.listarPor2CamposOrdenadaGenerico(ENTIDAD_CATASTRO_PREDIAL_VALORACION, "CatastroPredial", "catdetParroquia", codParroquia, "catprevalAnio", anio, "catprevalAnio", "asc");
    }
    
    public List<CatastroPredialValoracion> listarCatastroXSector(CatalogoDetalle codSector,Integer anio) throws Exception {       
       return catastroPredialValoracionDao.listarPor2CamposOrdenadaGenerico(ENTIDAD_CATASTRO_PREDIAL_VALORACION, "CatastroPredial", "catdetSector", codSector, "catprevalAnio", anio, "catprevalAnio", "asc");
    }
    
    public List<CatastroPredialValoracion> listarCatastroAnioTodo(Integer anio) throws Exception {       
       return catastroPredialValoracionDao.listarPor1CamposOrdenadaGenerico(ENTIDAD_CATASTRO_PREDIAL_VALORACION, "CatastroPredial", "catprevalAnio", anio, "catprevalAnio", "asc");
    }
      
}
