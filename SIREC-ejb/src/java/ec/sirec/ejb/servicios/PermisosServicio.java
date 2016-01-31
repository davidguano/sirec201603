/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sirec.ejb.servicios;

import ec.sirec.ejb.entidades.SegPermiso;
import ec.sirec.ejb.facade.SegPermisoFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author dvaldas
 */
@Stateless
@LocalBean
public class PermisosServicio {

    @EJB
    private SegPermisoFacade segPermisoDao;

    public List<SegPermiso> listarPermisos() throws Exception {
        return segPermisoDao.listarTodos();
    }

    public List<SegPermiso> listarPermisosRolNoAsignados(List<SegPermiso> vPermisos) throws Exception {
        return segPermisoDao.listarPermisosRolNoAsignados(vPermisos);
    }
    public List<SegPermiso> listarPermisosRolAsignados(Integer vidRol) throws Exception {
        return segPermisoDao.listarPermisosRolAsignados(vidRol);
    }
  
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
