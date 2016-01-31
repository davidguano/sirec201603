/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.sirec.ejb.facade;

import ec.sirec.ejb.entidades.CatastroPredialValoracion;
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
public class CatastroPredialValoracionFacade extends AbstractFacade<CatastroPredialValoracion> {
    @PersistenceContext(unitName = "SIREC-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CatastroPredialValoracionFacade() {
        super(CatastroPredialValoracion.class);
    }
    
    public List<CatastroPredialValoracion> listarPor2CamposOrdenadaGenerico(String ventidadV, String ventidadC, String vcampo1, Object vvalor1, String vcampo2, Object vvalor2, String vcampoOrd, String vforma) throws Exception {
        String sql = "select e from " + ventidadV + " e, " + ventidadC + " c "
                + "where c." + vcampo1 + "=:vvalor1 and "
                + " e." + vcampo2 + "=:vvalor2 and "
                + " e.catpreCodigo=c.catpreCodigo";
        Query q = getEntityManager().createQuery(sql);
        q.setParameter("vvalor1", vvalor1).setParameter("vvalor2", vvalor2);
        return q.getResultList();
    }
    
    public List<CatastroPredialValoracion> listarPor1CamposOrdenadaGenerico(String ventidadV, String ventidadC, String vcampo2, Object vvalor2, String vcampoOrd, String vforma) throws Exception {
        String sql = "select e from " + ventidadV + " e, " + ventidadC + " c "
                + "where e." + vcampo2 + "=:vvalor2 and "
                + " e.catpreCodigo=c.catpreCodigo";
        Query q = getEntityManager().createQuery(sql);
        q.setParameter("vvalor2", vvalor2);
        return q.getResultList();
    }
        
}
