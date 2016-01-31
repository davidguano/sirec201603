/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.sirec.ejb.servicios;

import ec.sirec.ejb.entidades.CatastroPredial;
import ec.sirec.ejb.entidades.CatastroPredialPlusvaliaValoracion;
import ec.sirec.ejb.facade.CatastroPredialPlusvaliaValoracionFacade;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author vespinoza
 */
@Stateless
@LocalBean
public class CatastroPredialPlusvaliaValoracionServicio {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB
    private CatastroPredialPlusvaliaValoracionFacade catastroPredialPlusvaliaValoracionDao;
    private final String ENTIDAD_CATASTRO_PREDIAL_PLUSVALIA_VALORACION="CatastroPredialPlusvaliaValoracion";
    
    public String crearCatastroPredialPlusvaliaValoracion(CatastroPredialPlusvaliaValoracion catastroPredialPlusvaliaValoracion) throws Exception {
        catastroPredialPlusvaliaValoracionDao.crear(catastroPredialPlusvaliaValoracion);
        return "Se ha creado la aplicación" + catastroPredialPlusvaliaValoracion.getCatprepluvalCodigo();
    }

    public String editarCatastroPredialPlusvaliaValoracion(CatastroPredialPlusvaliaValoracion catastroPredialPlusvaliaValoracion) throws Exception {
        catastroPredialPlusvaliaValoracionDao.editar(catastroPredialPlusvaliaValoracion);
        return "Se ha modificado la aplicación" + catastroPredialPlusvaliaValoracion.getCatprepluvalCodigo();
    }
    
     public CatastroPredialPlusvaliaValoracion buscarPorCatastroPredial(CatastroPredial catastroPredial) throws Exception {
        return catastroPredialPlusvaliaValoracionDao.buscarPorCampo(ENTIDAD_CATASTRO_PREDIAL_PLUSVALIA_VALORACION, "catpreCodigo", catastroPredial);
    }
    
}