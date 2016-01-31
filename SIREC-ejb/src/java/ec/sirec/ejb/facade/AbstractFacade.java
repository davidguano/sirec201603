/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.sirec.ejb.facade;

import ec.sirec.ejb.entidades.SegRol;
import ec.sirec.ejb.entidades.SegUsuario;
import ec.sirec.ejb.entidades.SegUsuarioRol;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author dguano
 */
public abstract class AbstractFacade<T> {
    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void crear(T entity) {
        getEntityManager().persist(entity);
    }

    public void editar(T entity) {
        getEntityManager().merge(entity);
    }

    public void eliminar(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

     public int contar() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
    public int contarPorCampo(String ventidad, String vcampo1, Object vvalor1) throws Exception {
        String sql = "select count(e) from " + ventidad + " e where e." + vcampo1 + "=:vvalor1";
        Query q = getEntityManager().createQuery(sql);
        q.setParameter("vvalor1", vvalor1);
        return ((Long) q.getSingleResult()).intValue();
    }

    public List<T> listarPorCampoOrdenada(String ventidad, String vcampo1, Object vvalor1, String vcampoOrd, String vforma) throws Exception {
        String sql = "select e from " + ventidad + " e where e." + vcampo1 + " =:vvalor1 order by e." + vcampoOrd + " " + vforma;
        Query q = getEntityManager().createQuery(sql);
        q.setParameter("vvalor1", vvalor1);
        return q.getResultList();

    }

    public List<T> listarOrdenada(String ventidad, String vcampoOrd, String vforma) throws Exception {
        String sql = "select e from " + ventidad + " e order by e." + vcampoOrd + " " + vforma;
        Query q = getEntityManager().createQuery(sql);
        return q.getResultList();

    }

    public T buscarPorCampo(String ventidad, String vcampo1, Object vvalor1) throws Exception {
        String sql = "select e from " + ventidad + " e where e." + vcampo1 + "=:vvalor1";
        Query q = getEntityManager().createQuery(sql);
        q.setParameter("vvalor1", vvalor1);
        List<T> resultado = q.getResultList();
        if (resultado.size() > 0) {
            return (T) resultado.get(0);
        } else {
            return null;
        }
    }

    public T buscarPor2Campos(String ventidad, String vcampo1, Object vvalor1, String vcampo2, Object vvalor2) throws Exception {
        String sql = "select e from " + ventidad + " e where e." + vcampo1 + "=:vvalor1 and e." + vcampo2 + "=:vvalor2";
        Query q = getEntityManager().createQuery(sql);
        q.setParameter("vvalor1", vvalor1).setParameter("vvalor2", vvalor2);
        List<T> resultado = q.getResultList();
        if (resultado.size() > 0) {
            return (T) resultado.get(0);
        } else {
            return null;
        }
    }

    public T buscarPor3Campos(String ventidad, String vcampo1, Object vvalor1, String vcampo2, Object vvalor2, String vcampo3, Object vvalor3) throws Exception {
        String sql = "select e from " + ventidad + " e where e." + vcampo1 + "=:vvalor1 and e." + vcampo2 + "=:vvalor2 and e." + vcampo3 + "=:vvalor3";
        Query q = getEntityManager().createQuery(sql);
        q.setParameter("vvalor1", vvalor1).setParameter("vvalor2", vvalor2).setParameter("vvalor3", vvalor3);
        List<T> resultado = q.getResultList();
        if (resultado.size() > 0) {
            return (T) resultado.get(0);
        } else {
            return null;
        }
    }

    public T buscarPorPartes(String ventidad, String vcampo1, Object vvalor1) throws Exception {

        String sql = "select e from " + ventidad + " e where e." + vcampo1 + " like :vvalor1";
        Query q = getEntityManager().createQuery(sql);
        q.setParameter("vvalor1", "%" + vvalor1 + "%");
        return (T) q.getResultList().get(0);
    }

    public boolean existePorCampo(String ventidad, String vcampo1, Object vvalor1) throws Exception {
        String sql = "select count(e) from " + ventidad + " e where e." + vcampo1 + "=:vvalor1";
        Query q = getEntityManager().createQuery(sql);
        q.setParameter("vvalor1", vvalor1);
        Long num = (Long) q.getSingleResult();
        if (num.intValue() > 0) {
            return true;
        } else {
            return false;
        }

    }

    public boolean existePor2Campos(String ventidad, String vcampo1, Object vvalor1, String vcampo2, Object vvalor2) throws Exception {
        String sql = "select count(e) from " + ventidad + " e where e." + vcampo1 + "=:vvalor1 and e." + vcampo2 + "=:vvalor2";
        Query q = getEntityManager().createQuery(sql);
        q.setParameter("vvalor1", vvalor1).setParameter("vvalor2", vvalor2);
        Long num = (Long) q.getSingleResult();
        if (num.intValue() > 0) {
            return true;
        } else {
            return false;
        }

    }

    public boolean existePor3Campos(String ventidad, String vcampo1, Object vvalor1, String vcampo2, Object vvalor2, String vcampo3, Object vvalor3) throws Exception {
        String sql = "select count(e) from " + ventidad + " e where e." + vcampo1 + "=:vvalor1 and e." + vcampo2 + "=:vvalor2 and e." + vcampo3 + "=:vvalor3";
        Query q = getEntityManager().createQuery(sql);
        q.setParameter("vvalor1", vvalor1).setParameter("vvalor2", vvalor2).setParameter("vvalor3", vvalor3);
        Long num = (Long) q.getSingleResult();
        if (num.intValue() > 0) {
            return true;
        } else {
            return false;
        }

    }

    public List<T> listarPor2CamposOrdenada(String ventidad, String vcampo1, Object vvalor1, String vcampo2, Object vvalor2, String vcampoOrd, String vforma) throws Exception {
        String sql = "select e from " + ventidad + " e where e." + vcampo1 + " =:vvalor1 and e." + vcampo2 + "=:vvalor2 order by e." + vcampoOrd + " " + vforma;
        Query q = getEntityManager().createQuery(sql);
        q.setParameter("vvalor1", vvalor1).setParameter("vvalor2", vvalor2);
        return q.getResultList();

    }
    public List<T> listarPor1Campo1ContieneOrdenada(String ventidad, String vcampo1, Object vvalor1, String vcampo2, Object vvalor2, String vcampoOrd, String vforma) throws Exception {
        String sql = "select e from " + ventidad + " e where e." + vcampo1 + " =:vvalor1 and UPPER(e." + vcampo2 + ") like :vvalor2 order by e." + vcampoOrd + " " + vforma;
        Query q = getEntityManager().createQuery(sql);
        q.setParameter("vvalor1", vvalor1).setParameter("vvalor2", "%"+vvalor2+"%");
        return q.getResultList();

    }
    public List<T> listarPor1Campo1IniciaOrdenada(String ventidad, String vcampo1, Object vvalor1, String vcampo2, Object vvalor2, String vcampoOrd, String vforma) throws Exception {
        String sql = "select e from " + ventidad + " e where e." + vcampo1 + " =:vvalor1 and e." + vcampo2 + " like :vvalor2 order by e." + vcampoOrd + " " + vforma;
        Query q = getEntityManager().createQuery(sql);
        q.setParameter("vvalor1", vvalor1).setParameter("vvalor2", vvalor2+"%");
        return q.getResultList();

    }
    public List<T> listarPor1Campo1InOrdenada(String ventidad, String vcampo1, Object vvalor1, String vcampoIn, Object vvalorIn, String vcampoOrd, String vforma) throws Exception {
        String sql = "select e from " + ventidad + " e where e." + vcampo1 + " =:vvalor1 and e." + vcampoIn + " in :vvalorIn order by e." + vcampoOrd + " " + vforma;
        Query q = getEntityManager().createQuery(sql);
        q.setParameter("vvalor1", vvalor1).setParameter("vvalorIn", vvalorIn);
        return q.getResultList();

    }
    public List<T> listarPorCamposContieneOrdenada(String ventidad, String vcampo1, Object vvalor1, String vcampoOrd, String vforma) throws Exception {
        String sql = "select e from " + ventidad + " e where UPPER(e." + vcampo1 + ")  like :vvalor1 order by e." + vcampoOrd + " " + vforma;
        Query q = getEntityManager().createQuery(sql);
        q.setParameter("vvalor1", "%"+vvalor1+"%");
        return q.getResultList();

    }

    public List<T> listarPor2CamposyCampoNotNullOrdenada(String ventidad, String vcampo1, Object vvalor1, String vcampo2, Object vvalor2, String campoNNull, String vcampoOrd, String vforma) throws Exception {
        String sql = "select e from " + ventidad + " e where e." + vcampo1 + " =:vvalor1 and e." + vcampo2 + "=:vvalor2 and e." + campoNNull + " is not null order by e." + vcampoOrd + " " + vforma;
        Query q = getEntityManager().createQuery(sql);
        q.setParameter("vvalor1", vvalor1).setParameter("vvalor2", vvalor2);
        return q.getResultList();

    }

    public List<T> listarPor3CamposOrdenada(String ventidad, String vcampo1, Object vvalor1, String vcampo2, Object vvalor2, String vcampo3, Object vvalor3, String vcampoOrd, String vforma) throws Exception {
        String sql = "select e from " + ventidad + " e where e." + vcampo1 + " =:vvalor1 and e." + vcampo2 + "=:vvalor2 and e." + vcampo3 + "=:vvalor3 order by e." + vcampoOrd + " " + vforma;
        Query q = getEntityManager().createQuery(sql);
        q.setParameter("vvalor1", vvalor1).setParameter("vvalor2", vvalor2).setParameter("vvalor3", vvalor3);
        return q.getResultList();

    }
     public List<T> listarPor4CamposOrdenada(String ventidad, String vcampo1, Object vvalor1, String vcampo2, Object vvalor2, String vcampo3, Object vvalor3, String vcampo4, Object vvalor4,String vcampoOrd, String vforma) throws Exception {
        String sql = "select e from " + ventidad + " e where e." + vcampo1 + " =:vvalor1 and e." + vcampo2 + "=:vvalor2 and e." + vcampo3 + "=:vvalor3 and e." + vcampo4 + "=:vvalor4 order by e." + vcampoOrd + " " + vforma;
        Query q = getEntityManager().createQuery(sql);
        q.setParameter("vvalor1", vvalor1).setParameter("vvalor2", vvalor2).setParameter("vvalor3", vvalor3).setParameter("vvalor4", vvalor4);
        return q.getResultList();

    }
    
    public List<T> listarPor5CamposOrdenada(String ventidad, String vcampo1, Object vvalor1, String vcampo2, Object vvalor2, String vcampo3, Object vvalor3, String vcampo4, Object vvalor4,String vcampo5, Object vvalor5,String vcampoOrd, String vforma) throws Exception {
        String sql = "select e from " + ventidad + " e where e." + vcampo1 + " =:vvalor1 and e." + vcampo2 + "=:vvalor2 and e." + vcampo3 + "=:vvalor3 and e." + vcampo4 + "=:vvalor4 and e." + vcampo5 + "=:vvalor5 order by e." + vcampoOrd + " " + vforma;
        Query q = getEntityManager().createQuery(sql);
        q.setParameter("vvalor1", vvalor1).setParameter("vvalor2", vvalor2).setParameter("vvalor3", vvalor3).setParameter("vvalor4", vvalor4).setParameter("vvalor5", vvalor5);
        return q.getResultList();

    }

    public List<T> listarPorRangoFecha(String ventidad, String vcampo1, Object vvalor1, Object vvalor2, String vcampoOrd, String vforma) throws Exception {
        String sql = "select e from " + ventidad + " e where e." + vcampo1 + " between :vvalor1 and :vvalor2 order by e." + vcampoOrd + " " + vforma;
        Query q = getEntityManager().createQuery(sql);
        q.setParameter("vvalor1", vvalor1).setParameter("vvalor2", vvalor2);
        return q.getResultList();

    }

    public void eliminarGenerico(String ventidad, String vcampo, Object vvalor) throws Exception {
        String sql = " delete  from " + ventidad + " e where e." + vcampo + "=:vvalor";
        Query q = getEntityManager().createQuery(sql);
        q.setParameter("vvalor", vvalor);
        q.executeUpdate();
    }
    public void eliminarPor2Campos(String ventidad, String vcampo, Object vvalor, String vcampo2, Object vvalor2) throws Exception {
        String sql = " delete  from " + ventidad + " e where e." + vcampo + "=:vvalor and "+ vcampo2 + "=:vvalor2";
        Query q = getEntityManager().createQuery(sql);
        q.setParameter("vvalor", vvalor).setParameter("vvalor2", vvalor2);
        q.executeUpdate();
    }
    
   public List<SegUsuarioRol> obtenerUsuariosXRol(Integer rolCodigo) {
        String sql = "select p from SegUsuarioRol p where p.rolCodigo.rolCodigo=:rolCodigo";
        Query q = getEntityManager().createQuery(sql);
        q.setParameter("rolCodigo", rolCodigo);
        return q.getResultList();
    }
    
    public List<SegRol> obtenerRoles(String rolNemonico) {
        String sql = "select p from SegRol p where p.rolNemonico like ?";
        Query q = getEntityManager().createQuery(sql);
        q.setParameter(1, "%" + rolNemonico + "%");
        return q.getResultList();
    }
      public List<T> listarTodos() throws Exception {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }
    
    public List<SegUsuario> obtenerUsuariosXRolNemonico(String rolNemonico) {
      //  String sql = "select p from SegUsuarioRol p where p.rolCodigo.rolCodigo=:rolCodigo";
        String sql = "select u from SegRol r, SegUsuarioRol ur, SegUsuario u "
                + "where r.rolNemonico=:rolNemonico and r.rolCodigo=ur.rolCodigo and u.usuIdentificacion=ur.usuIdentificacion";
        Query q = getEntityManager().createQuery(sql);
        q.setParameter("rolNemonico", rolNemonico);
         return q.getResultList();
        
//        SELECT *FROM loginec.seg_rol r, loginec.seg_usuario_rol ur, loginec.seg_usuario u
//        where r.rol_nemonico='RIBD_JEF_GEPH_DIES' and r.rol_codigo=ur.rol_codigo and u.usu_identificacion=ur.usu_identificacion;
        
    }
    
}
