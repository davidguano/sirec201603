/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sirec.ejb.servicios;


import ec.sirec.ejb.entidades.FittoCorvini;
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
public class FittoCorviniServicio {

    @EJB
    private FittoCorviniFacade fittoCorviniDao;
    private String ENTIDAD_FITTO_CORVINI = "FittoCorvini";

    public FittoCorvini obtenerValoresClase(Integer codigo) throws Exception {
        return fittoCorviniDao.buscarPorCampo(ENTIDAD_FITTO_CORVINI, "edadVida", codigo);
    }

}
