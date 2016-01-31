/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sirec.ejb.servicios;


import ec.sirec.ejb.entidades.CpAlcabalaValoracionExtras;
import ec.sirec.ejb.entidades.RecaudacionCab;
import ec.sirec.ejb.facade.CpAlcabalaValoracionExtrasFacade;
import ec.sirec.ejb.facade.RecaudacionCabFacade;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;


/**
 *
 * @author vespinoza
 */
@Stateless
@LocalBean
public class RecaudacionCabServicio {

    @EJB
    private RecaudacionCabFacade RecaudacionCabDao;
    private String ENTIDAD_RECAUDACION_CAB = "RecaudacionCab";
    
    
     public String crearRecaudacionCab(RecaudacionCab recaudacionCab) throws Exception {
        RecaudacionCabDao.crear(recaudacionCab);
        return "Se ha creado elRecaudacionCab" + recaudacionCab.getRecCodigo();
    }

    public String editarRecaudacionCab(RecaudacionCab recaudacionCab) throws Exception {
        RecaudacionCabDao.editar(recaudacionCab);
        return "Se ha modificado el RecaudacionCab" + recaudacionCab.getRecCodigo();
    }
    

}