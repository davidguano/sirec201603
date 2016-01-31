/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.sirec.ejb.facade;

import ec.sirec.ejb.entidades.SegUsuarioRol;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author dguano
 */
@Stateless
public class SegUsuarioRolFacade extends AbstractFacade<SegUsuarioRol> {
    @PersistenceContext(unitName = "SIREC-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SegUsuarioRolFacade() {
        super(SegUsuarioRol.class);
    }
    public boolean existeUsuarioRol(String vidUsuario, Integer vidRol) throws Exception {
        String sql = "select count(ur) from SegUsuarioRol ur where ur.usuIdentificacion.usuIdentificacion=:vidUsuario and ur.rolCodigo.rolCodigo=:vidRol";
        Query q = em.createQuery(sql);
        q.setParameter("vidUsuario", vidUsuario).setParameter("vidRol", vidRol);
        Long num = (Long) q.getSingleResult();
        if (num.intValue() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public SegUsuarioRol buscarPorUsuarioRol(String vidUsuario, Integer vidRol) throws Exception {
        String sql = "select ur from SegUsuarioRol ur where ur.usuIdentificacion.usuIdentificacion=:vidUsuario and ur.rolCodigo.rolCodigo=:vidRol";
        Query q = em.createQuery(sql);
        q.setParameter("vidUsuario", vidUsuario).setParameter("vidRol", vidRol);
        return (SegUsuarioRol) q.getResultList().get(0);
    }

    public void delete(Integer vidUsuarioRol) throws Exception {
        em.createQuery("DELETE FROM SegUsuarioRol ur WHERE ur.usurolCodigo=:vidUsuarioRol")
                .setParameter("vidUsuarioRol", vidUsuarioRol)
                .executeUpdate();
    }
}
