/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sirec.ejb.servicios;

import ec.sirec.ejb.entidades.CatalogoDetalle;
import ec.sirec.ejb.entidades.SegRol;
import ec.sirec.ejb.facade.CatalogoDetalleFacade;
import ec.sirec.ejb.facade.SegRolFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Darwin Aldas
 */
@Stateless
@LocalBean
public class DeclaracionTributariaServicio {
    @EJB
    private CatalogoDetalleFacade catalogoDetalleDao;
    private String ENTIDAD_CATALOGO_DETALLE="";

     
  public List<CatalogoDetalle> buscarCatalogoDetallePorRuc(String ruc) throws Exception{
    return catalogoDetalleDao.buscaCatDetPorRuc(ruc);
    }
  
    

//    public String creaRol(SegRol codRol) throws Exception {
//        catalogoDetalleDao.crear(codRol);
//        return "se ha creado el Rol" + codRol;
//    }
//
//    public String editaRol(SegRol codAgencia) throws Exception {
//        catalogoDetalleDao.editar(codAgencia);
//        return "se ha modificado el Rol" + codAgencia;
//    }
//
//    public String eliminaRol(Integer codRol) throws Exception {
//        rolDao.eliminarGenerico(ENTIDAD_ROL, "rolCodigo", codRol);
//        return "se ha eliminado el Rol" + codRol;
//    }
//
//    public List<SegRol> listarRolesTodos() throws Exception {
//        return rolDao.listarOrdenada(ENTIDAD_ROL, "rolCodigo", "asc");
//    }
//
//    public boolean existeCodigoRol(Integer codRol) throws Exception {
//        return rolDao.existePorCampo(ENTIDAD_ROL, "rolCodigo", codRol);
//    }
}
