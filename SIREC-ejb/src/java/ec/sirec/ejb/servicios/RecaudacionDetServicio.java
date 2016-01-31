/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sirec.ejb.servicios;


import ec.sirec.ejb.entidades.RecaudacionDet;
import ec.sirec.ejb.facade.RecaudacionDetFacade;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;


/**
 *
 * @author vespinoza
 */
@Stateless
@LocalBean
public class RecaudacionDetServicio {

    @EJB
    private RecaudacionDetFacade recaudacionDetDao;
    private String ENTIDAD_RECAUDACION_DET = "RecaudacionDet";
    
    
     public String crearRecaudacionDet(RecaudacionDet recaudacionDet) throws Exception {
        recaudacionDetDao.crear(recaudacionDet);
        return "Se ha creado el RecaudacionDet" + recaudacionDet.getRecdetCodigo();
    }

    public String editarRecaudacionDet(RecaudacionDet recaudacionDet) throws Exception {
        recaudacionDetDao.editar(recaudacionDet);
        return "Se ha modificado el RecaudacionDet" + recaudacionDet.getRecdetCodigo();
    }
    

}