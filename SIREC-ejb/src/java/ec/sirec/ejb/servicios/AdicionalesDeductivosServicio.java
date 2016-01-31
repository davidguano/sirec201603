/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sirec.ejb.servicios;

import ec.sirec.ejb.entidades.AdicionalesDeductivos;
import ec.sirec.ejb.entidades.CatalogoDetalle;
import ec.sirec.ejb.entidades.CatastroPredialValoracion;
import ec.sirec.ejb.entidades.SegPermiso;
import ec.sirec.ejb.facade.AdicionalesDeductivosFacade;
import ec.sirec.ejb.facade.CatalogoDetalleFacade;
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
public class AdicionalesDeductivosServicio {

    @EJB
    private AdicionalesDeductivosFacade adicionalesDeductivosDao;
    private String ENTIDAD_ADICIONALES_DEDUCIBLES = "AdicionalesDeductivos";
    
    
     public String crearPermiso(AdicionalesDeductivos adicionalesDeductivos) throws Exception {
        adicionalesDeductivosDao.crear(adicionalesDeductivos);
        return "Se ha creado el permiso" + adicionalesDeductivos.getAdidedCodigo();
    }

    public String editarPemiso(AdicionalesDeductivos adicionalesDeductivos) throws Exception {
        adicionalesDeductivosDao.editar(adicionalesDeductivos);
        return "Se ha modificado el permiso" + adicionalesDeductivos.getAdidedCodigo();
    }
       
    public List<AdicionalesDeductivos> listarAdicionesDeductivosTipo(String tipo, String tipoImpuesto) throws Exception {
     return adicionalesDeductivosDao.listarPor2CamposOrdenada(ENTIDAD_ADICIONALES_DEDUCIBLES,"adidedTipo", tipo, "adidedTipoImpuesto", tipoImpuesto, "adidedCodigo", "asc");
    }
    public List<AdicionalesDeductivos> listarAdicionesDeductivosTipoImpuesto(String tipo) throws Exception {
     return adicionalesDeductivosDao.listarPorCampoOrdenada(ENTIDAD_ADICIONALES_DEDUCIBLES,"adidedTipoImpuesto", tipo, "adidedCodigo", "asc");
    }
    
    public BigDecimal obteneValorXAdicional(Integer codigo, String TipoImp, String tipo) throws Exception {
     return adicionalesDeductivosDao.obteneValorXAdicional(codigo, TipoImp, tipo);
    }
    
     public AdicionalesDeductivos buscarAdicionesDeductivosXCodigo(Integer codigo) throws Exception {
     return adicionalesDeductivosDao.buscarPorCampo(ENTIDAD_ADICIONALES_DEDUCIBLES, "adidedCodigo", codigo);
    }
     
      public AdicionalesDeductivos buscarAdicionesDeductivosXNemonico(String nemonico) throws Exception {
     return adicionalesDeductivosDao.buscarPorCampo(ENTIDAD_ADICIONALES_DEDUCIBLES, "adidedNemonico", nemonico);
    }
     public List<AdicionalesDeductivos> recuperarAdicionesDeductivos(CatastroPredialValoracion catastroPredialValoracion, String tipo ,String tipoImpuesto) throws Exception {
     return adicionalesDeductivosDao.recuperarAdicionalesDeductivos(catastroPredialValoracion, tipo, tipoImpuesto);
    }
         
     
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}