/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.sirec.ejb.facade;

import ec.sirec.ejb.entidades.SegPermiso;
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
public class SegPermisoFacade extends AbstractFacade<SegPermiso> {
    @PersistenceContext(unitName = "SIREC-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SegPermisoFacade() {
        super(SegPermiso.class);
    }
    public List<SegPermiso> listarPermisosRolNoAsignados(List<SegPermiso> vPermisos) throws Exception {
        String sql = "select p from SegPermiso p where p not in (:vPermisos) order by p.perCodigo";
        Query q = em.createQuery(sql);
        q.setParameter("vPermisos", vPermisos);
        return q.getResultList();
    }

    public List<SegPermiso> listarPermisosRolAsignados(Integer vidRol) throws Exception {
        String sql = "select p from SegPermiso p, SegPermisoRol rp where"
                + " rp.perCodigo.perCodigo=p and rp.rolCodigo.rolCodigo=:vidRol order by p.perCodigo";
        Query q = em.createQuery(sql);
        q.setParameter("vidRol", vidRol);
        return q.getResultList();
    }

    public List<SegPermiso> listarPermisosIdentificacion(String vIdentificacion) throws Exception {
        String sql = " select distinct sp from SegPermiso sp,SegPermisoRol spr,SegRol sr,"
                + " SegUsuarioRol sur,SegUsuario su "
                + " where su.usuIdentificacion=:vIdentificacion and"
                + "  su=sur.usuIdentificacion and"
                + " sur.rolCodigo=sr and"
                + " sr=spr.rolCodigo and"
                + " spr.perCodigo=sp";
        Query q = em.createQuery(sql);
        q.setParameter("vIdentificacion", vIdentificacion);
        return q.getResultList();
    }

    public List<SegPermiso> listarPermisosDeRolesNoAsignadosPrmrtApli(List<SegPermiso> vPermiso, Integer aplCodigo) throws Exception {
        String sql = "select r from SegPermiso r where r not in (:vPermiso) and r.apliCodigo.apliCodigo=:vaplcod order by r.perNombre";
        Query q = em.createQuery(sql);
        q.setParameter("vPermiso", vPermiso).setParameter("vaplcod", aplCodigo);
        return q.getResultList();
    }

    public List<SegPermiso> listarPermisoPrmtApli(Integer aplCodigo) throws Exception {
        String sql = "select r from SegPermiso r where  r.apliCodigo.apliCodigo=:vaplcod order by r.perNombre";
        Query q = em.createQuery(sql);
        q.setParameter("vaplcod", aplCodigo);
        return q.getResultList();
    }
}
