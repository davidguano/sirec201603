/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sirec.ejb.servicios;


import ec.sirec.ejb.entidades.CpAlcabalaValoracionExtras;
import ec.sirec.ejb.facade.CpAlcabalaValoracionExtrasFacade;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;


/**
 *
 * @author vespinoza
 */
@Stateless
@LocalBean
public class CpAlcabalaValoracionExtrasServicio {

    @EJB
    private CpAlcabalaValoracionExtrasFacade cpAlcabalaValoracionExtrasDao;
    private String ENTIDAD_CPALCABALA_VALORACION_EXTRAS = "CpAlcabalaValoracionExtras";
    
    
     public String crearCpAlcabalaValoracionExtras(CpAlcabalaValoracionExtras cpAlcabalaValoracionExtras) throws Exception {
        cpAlcabalaValoracionExtrasDao.crear(cpAlcabalaValoracionExtras);
        return "Se ha creado el CpAlcabalaValoracionExtras" + cpAlcabalaValoracionExtras.getCatprealcvalCodigo();
    }

    public String editarCpAlcabalaValoracionExtras(CpAlcabalaValoracionExtras cpAlcabalaValoracionExtras) throws Exception {
        cpAlcabalaValoracionExtrasDao.editar(cpAlcabalaValoracionExtras);
        return "Se ha modificado el CpAlcabalaValoracionExtras" + cpAlcabalaValoracionExtras.getCatprealcvalCodigo();
    }
    

}