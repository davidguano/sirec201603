/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.sirec.ejb.facade;

import ec.sirec.ejb.entidades.SegPermisoRol;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author dguano
 */
@Stateless
public class SegPermisoRolFacade extends AbstractFacade<SegPermisoRol> {
    @PersistenceContext(unitName = "SIREC-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SegPermisoRolFacade() {
        super(SegPermisoRol.class);
    }
     public boolean existeRolPermiso(Integer vidRol, Integer vidPer) throws Exception {
        String sql = "select count(rp) from SegPermisoRol rp where rp.rolCodigo.rolCodigo=:vidRol and rp.perCodigo.perCodigo=:vidPer";
        Query q = em.createQuery(sql);
        q.setParameter("vidRol", vidRol).setParameter("vidPer", vidPer);
        Long num = (Long) q.getSingleResult();
        if (num.intValue() > 0) {
            return true;
        } else {
            return false;
        }
    }
     public SegPermisoRol buscarPorRolPermiso(Integer vidRol, Integer vidPer) throws Exception {
        String sql = "select rp from SegPermisoRol rp where rp.rolCodigo.rolCodigo=:vidRol and rp.perCodigo.perCodigo=:vidPer";
        Query q = em.createQuery(sql);
        q.setParameter("vidRol", vidRol).setParameter("vidPer", vidPer);
        return (SegPermisoRol) q.getResultList().get(0);
    }
    public void delete(Integer vidRolPermiso) throws Exception {
        em.createQuery("DELETE FROM SegPermisoRol rp WHERE rp.perrolCodigo=:vidRolPermiso")
                .setParameter("vidRolPermiso", vidRolPermiso)
                .executeUpdate();
    }
}
