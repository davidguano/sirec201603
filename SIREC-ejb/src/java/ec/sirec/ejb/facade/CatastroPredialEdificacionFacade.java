/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.sirec.ejb.facade;

import ec.sirec.ejb.entidades.CatastroPredialEdificacion;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author dguano
 */
@Stateless
public class CatastroPredialEdificacionFacade extends AbstractFacade<CatastroPredialEdificacion> {
    @PersistenceContext(unitName = "SIREC-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CatastroPredialEdificacionFacade() {
        super(CatastroPredialEdificacion.class);
    }
    
    public List<CatastroPredialEdificacion> listarPor2CamposOrdenadaMenosSanitaria(String ventidad, String vcampo1, Object vvalor1, String vcampo2, Object vvalor2, String vcampoOrd, String vforma) throws Exception {
        String sql = "select e from " + ventidad + " e where e." + vcampo1 + " =:vvalor1 and e." + vcampo2 + "=:vvalor2 and e.catpreediSubgrupo <> '1' order by e." + vcampoOrd + " " + vforma;
        Query q = getEntityManager().createQuery(sql);
        q.setParameter("vvalor1", vvalor1).setParameter("vvalor2", vvalor2);
        return q.getResultList();

    }
    
}
