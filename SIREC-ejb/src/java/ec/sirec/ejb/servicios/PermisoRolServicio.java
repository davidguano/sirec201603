/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sirec.ejb.servicios;

import ec.sirec.ejb.entidades.SegPermisoRol;
import ec.sirec.ejb.facade.SegPermisoRolFacade;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author dvaldas
 */
@Stateless
@LocalBean
public class PermisoRolServicio {

    @EJB
    private SegPermisoRolFacade segPermisoRolDao;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public boolean existeRolPermiso(Integer vidRol, Integer vidPer) throws Exception {
        return segPermisoRolDao.existeRolPermiso(vidRol, vidPer);
    }

    public void guardarRolPermiso(SegPermisoRol vRolPermiso) throws Exception {
        segPermisoRolDao.crear(vRolPermiso);
    }

     public SegPermisoRol buscarPorRolPermiso(Integer vidRol, Integer vidAccionFuncionalidad) throws Exception {
        return segPermisoRolDao.buscarPorRolPermiso(vidRol, vidAccionFuncionalidad);
    }
      public void eliminarRolPermiso(Integer vidRolPermiso) throws Exception {
        segPermisoRolDao.delete(vidRolPermiso);
    }
}
