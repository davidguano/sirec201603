/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sirec.ejb.servicios;

import ec.sirec.ejb.entidades.AdicionalesDeductivos;
import ec.sirec.ejb.entidades.CatalogoDetalle;
import ec.sirec.ejb.entidades.CatastroPredial;
import ec.sirec.ejb.entidades.CatastroPredialValoracion;
import ec.sirec.ejb.entidades.CpValoracionExtras;
import ec.sirec.ejb.entidades.SegPermiso;
import ec.sirec.ejb.facade.AdicionalesDeductivosFacade;
import ec.sirec.ejb.facade.CatalogoDetalleFacade;
import ec.sirec.ejb.facade.CpValoracionExtrasFacade;
import ec.sirec.ejb.facade.SegPermisoFacade;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.Query;

/**
 *
 * @author vespinoza
 */
@Stateless
@LocalBean
public class CpValoracionExtrasServicio {

    @EJB
    private CpValoracionExtrasFacade cpValoracionExtrasDao;
    private String ENTIDAD_VALORACION_EXTRAS = "CpValoracionExtras";
    
    
     public String crearCpValoracionExtras(CpValoracionExtras cpValoracionExtras) throws Exception {
        cpValoracionExtrasDao.crear(cpValoracionExtras);
        return "Se ha creado el permiso" + cpValoracionExtras.getCpvalextCodigo();
    }

    public String editarCpValoracionExtras(CpValoracionExtras cpValoracionExtras) throws Exception {
        cpValoracionExtrasDao.editar(cpValoracionExtras);
        return "Se ha modificado el permiso" + cpValoracionExtras.getCpvalextCodigo();
    }
    
    public List<CpValoracionExtras> listarPermisos() throws Exception {
        return cpValoracionExtrasDao.listarTodos();
    }

    public List<CpValoracionExtras> listarCpValoracionExtrasXCatPreVal(CatastroPredialValoracion catPreValCodigo) throws Exception {
     return cpValoracionExtrasDao.listarPorCampoOrdenada(ENTIDAD_VALORACION_EXTRAS,"catprevalCodigo", catPreValCodigo,"cpvalextCodigo", "asc");
    }
        
    public String eliminarCpValoracionExtrar(CatastroPredialValoracion catPreValCodigo) throws Exception {
        List<CpValoracionExtras> listarCp = listarCpValoracionExtrasXCatPreVal(catPreValCodigo);
          for (int i = 0; i < listarCp.size(); i++) {  
              cpValoracionExtrasDao.eliminarGenerico(ENTIDAD_VALORACION_EXTRAS, "cpvalextCodigo", listarCp.get(i).getCpvalextCodigo());
          }                
        return "se han eliminado los CpVal";
    }
    
    public CpValoracionExtras buscarValoresRecargos(CatastroPredialValoracion catastroPredialValoracion, String Nemonico) throws Exception {
        return cpValoracionExtrasDao.buscarXNemonico(Nemonico, catastroPredialValoracion);
    } 
    
    public CpValoracionExtras buscarXCodigo(Integer CpValoracionExtras) throws Exception {
        return cpValoracionExtrasDao.buscarPorCampo(ENTIDAD_VALORACION_EXTRAS, "cpvalextCodigo", CpValoracionExtras);
    }

    public BigDecimal obteneValorTipoAdicional(Integer codigoC, Integer codigoCPV, String TipoImp, String tipo) throws Exception {
        return cpValoracionExtrasDao.obteneValorTipoAdicional(codigoC, codigoCPV, TipoImp, tipo);
    }
    
 
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
