/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sirec.ejb.servicios;

import ec.sirec.ejb.entidades.Tasa;
import ec.sirec.ejb.facade.TasaFacade;
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
public class TasaServicio {

    @EJB
    private TasaFacade tasaDao;
    private String ENTIDAD_TASA = "Tasa";
    
    
     public String crearTasa(Tasa tasa) throws Exception {
        tasaDao.crear(tasa);
        return "Se ha creado el Tasa" + tasa.getTasCodigo();
    }

    public String editarTasa(Tasa tasa) throws Exception {
        tasaDao.editar(tasa);
        return "Se ha modificado el Tasa" + tasa.getTasCodigo();
    }
    
    public List<Tasa> listarTasa() throws Exception {
        return tasaDao.listarTodos();
    }

    public String eliminarTasa(Tasa tasa) throws Exception {
        tasaDao.eliminarGenerico(ENTIDAD_TASA, "tasCodigo", tasa.getTasCodigo());
        return "se ha eliminado la Tasa" + tasa;
    }
    
}
