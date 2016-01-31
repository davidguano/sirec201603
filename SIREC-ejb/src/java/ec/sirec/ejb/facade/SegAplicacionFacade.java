/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.sirec.ejb.facade;

import ec.sirec.ejb.entidades.SegAplicacion;
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
public class SegAplicacionFacade extends AbstractFacade<SegAplicacion> {
    @PersistenceContext(unitName = "SIREC-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SegAplicacionFacade() {
        super(SegAplicacion.class);
    }
    public List<SegAplicacion> listarAccesoAplicacion(String usuario) throws Exception {
        String sql = " select distinct sa from SegAplicacion sa,SegRol sr,SegUsuarioRol sur,SegUsuario su "
                + " where sa.apliCodigo=sr.apliCodigo "
                + " and sr.rolCodigo=sur.rolCodigo "
                + " and sur.usuIdentificacion=su.usuIdentificacion "
                + " and su.usuUsuario=:valUsuario";
        Query q = em.createQuery(sql);
        q.setParameter("valUsuario", usuario);
        return q.getResultList();
    }
}
