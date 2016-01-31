/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.sirec.ejb.facade;

import ec.sirec.ejb.entidades.SegUsuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author dguano
 */
@Stateless
public class SegUsuarioFacade extends AbstractFacade<SegUsuario> {
    @PersistenceContext(unitName = "SIREC-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SegUsuarioFacade() {
        super(SegUsuario.class);
    }
    public boolean existeUsuarioPorCampo(String usuario) throws Exception {
        String sql = "select count(u) from SegUsuario u where u.usuUsuario=:valUsuario";
        Query q = getEntityManager().createQuery(sql);
        q.setParameter("valUsuario", usuario);
        Long num = (Long) q.getSingleResult();
        if (num.intValue() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean existeUsuarioPorCampoClave(String usuario, String clave) throws Exception {
        String sql = "select count(u) from SegUsuario u where u.usuUsuario=:valUsuario and u.usuClave=:valClave";
        Query q = getEntityManager().createQuery(sql);
        q.setParameter("valUsuario", usuario).setParameter("valClave", clave);
        Long num = (Long) q.getSingleResult();
        if (num.intValue() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public SegUsuario buscarUsuarioIdent(String identificacion) throws Exception {
        String sql = "select c from SegUsuario c where c.usuIdentificacion=:videntificacion";
        Query q = em.createQuery(sql);
        q.setParameter("videntificacion", identificacion);
        return (SegUsuario) q.getResultList().get(0);
    }
}
