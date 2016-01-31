/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.sirec.ejb.facade;

import ec.sirec.ejb.entidades.CatastroPredialUsosuelo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author dguano
 */
@Stateless
public class CatastroPredialUsosueloFacade extends AbstractFacade<CatastroPredialUsosuelo> {
    @PersistenceContext(unitName = "SIREC-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CatastroPredialUsosueloFacade() {
        super(CatastroPredialUsosuelo.class);
    }
    
    public void actualizarRegistrosUsoSuelo(Integer vcodCat, int itemIni, int itemFin) throws Exception {
        String sql = " update CatastroPredialUsosuelo u set u.catpreusuAplica=false where u.catpreCodigo.catpreCodigo=:vcodCat and u.catpreusuItem>=:itemIni and u.catpreusuItem<=:itemFin";
        Query q = getEntityManager().createQuery(sql);
        q.setParameter("vcodCat", vcodCat).setParameter("itemIni", itemIni).setParameter("itemFin", itemFin);
        q.executeUpdate();
    }
    
}
