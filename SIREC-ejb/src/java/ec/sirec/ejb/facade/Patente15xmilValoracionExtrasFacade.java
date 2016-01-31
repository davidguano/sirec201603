/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.sirec.ejb.facade;

import ec.sirec.ejb.entidades.Patente15xmilValoracionExtras;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author dguano
 */
@Stateless
public class Patente15xmilValoracionExtrasFacade extends AbstractFacade<Patente15xmilValoracionExtras> {
    @PersistenceContext(unitName = "SIREC-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Patente15xmilValoracionExtrasFacade() {
        super(Patente15xmilValoracionExtras.class);
    }
    
}
