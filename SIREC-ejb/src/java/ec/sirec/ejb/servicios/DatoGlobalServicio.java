/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sirec.ejb.servicios;


import ec.sirec.ejb.entidades.DatoGlobal;
import ec.sirec.ejb.entidades.FittoCorvini;
import ec.sirec.ejb.facade.DatoGlobalFacade;
import ec.sirec.ejb.facade.FittoCorviniFacade;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author vespinoza
 */
@Stateless
@LocalBean
public class DatoGlobalServicio {

    @EJB
    private DatoGlobalFacade datoGlobalDao;
    private String ENTIDAD_DATO_GLOBAL = "DatoGlobal";

    public DatoGlobal obtenerDatoGlobal(String datoGlobal) throws Exception {
        return datoGlobalDao.buscarPorCampo(ENTIDAD_DATO_GLOBAL, "datgloNombre", datoGlobal);
    }

}
