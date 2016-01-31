/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.sirec.ejb.facade;

import ec.sirec.ejb.entidades.SegRol;
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
public class SegRolFacade extends AbstractFacade<SegRol> {
    @PersistenceContext(unitName = "SIREC-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SegRolFacade() {
        super(SegRol.class);
    }
    public List<SegRol> listarRolesPorUsuario(String usuario) throws Exception {
        String sql = " select distinct sr from SegRol sr,SegUsuarioRol sur,SegUsuario su "
                + " where sr.rolCodigo=sur.rolCodigo "
                + " and sur.usuIdentificacion=su.usuIdentificacion "
                + " and su.usuIdentificacion=:valUsuario";
        Query q = em.createQuery(sql);
        q.setParameter("valUsuario", usuario);
        return q.getResultList();
    }

    public List<SegRol> listarRolesDeUsuarioAsignados(String vidUsuario) throws Exception {
        String sql = "select r from SegRol r, SegUsuarioRol ur where ur.rolCodigo=r and ur.usuIdentificacion.usuIdentificacion=:vidUsuario order by r.rolCodigo";
        Query q = em.createQuery(sql);
        q.setParameter("vidUsuario", vidUsuario);
        return q.getResultList();
    }

    public List<SegRol> listarRolesDeUsuarioNoAsignados(List<SegRol> vRoles) throws Exception {
        String sql = "select r from SegRol r where r not in (:vRoles) order by r.rolNombre";
        Query q = em.createQuery(sql);
        q.setParameter("vRoles", vRoles);
        return q.getResultList();
    }

    public List<SegRol> listarRolesDeUsuarioNoAsignadosPrmrtApli(List<SegRol> vRoles, Integer aplCodigo) throws Exception {
        String sql = "select r from SegRol r where r not in (:vRoles) and r.apliCodigo.apliCodigo=:vaplcod order by r.rolNombre";
        Query q = em.createQuery(sql);
        q.setParameter("vRoles", vRoles).setParameter("vaplcod", aplCodigo);
        return q.getResultList();
    }

    public List<SegRol> listarRolesPrmtApli(Integer aplCodigo) throws Exception {
        String sql = "select r from SegRol r where  r.apliCodigo.apliCodigo=:vaplcod order by r.rolNombre";
        Query q = em.createQuery(sql);
        q.setParameter("vaplcod", aplCodigo);
        return q.getResultList();
    }

    public List<SegRol> listarRolesIdentificacion(String vIdentificacion) throws Exception {
        String sql = " select distinct sr from SegRol sr,"
                + " SegUsuarioRol sur,SegUsuario su "
                + " where su.usuIdentificacion=:vIdentificacion and"
                + "  su=sur.usuIdentificacion and"
                + " sur.rolCodigo=sr ";
        Query q = em.createQuery(sql);
        q.setParameter("vIdentificacion", vIdentificacion);
        return q.getResultList();
    }
}
