/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sirec.ejb.facade;

import ec.sirec.ejb.entidades.CatalogoDetalle;
import ec.sirec.ejb.entidades.Patente;
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
public class CatalogoDetalleFacade extends AbstractFacade<CatalogoDetalle> {

    @PersistenceContext(unitName = "SIREC-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CatalogoDetalleFacade() {
        super(CatalogoDetalle.class);
    }
 
    public List <CatalogoDetalle> buscaCatDetPorRuc(String cedula) throws Exception {
        String sql = "select distinct(cd) from CatalogoDetalle cd,Patente p,Propietario pr, "
                + " PropietarioPredio pp ,CatastroPredial cp"
                + " where pr.proCi=pp.proCi"
                + " and pp.catpreCodigo=cp.catpreCodigo"
                + " and cp.catpreCodigo=cp.catpreCodigo"
                + " and p.catdetTipoActEco=cd.catdetCodigo"
                + " and pr.proCi=:cedula  ";
        Query q = em.createQuery(sql);
        q.setParameter("cedula", cedula);
        if (q.getResultList().isEmpty()) {
            return null;
        } else {
            return q.getResultList();
        }

    }

}
