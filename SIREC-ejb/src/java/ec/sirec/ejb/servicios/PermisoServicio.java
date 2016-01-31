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
public class PermisoServicio {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB
    private SegPermisoFacade segPermisoDao;
    private String ENTIDAD_PERMISO = "SegPermiso";

    public List<SegPermiso> listarPermisos() throws Exception {
        return segPermisoDao.listarTodos();
    }

    public List<SegPermiso> listarPermisosRolNoAsignados(List<SegPermiso> vPermisos) throws Exception {
        return segPermisoDao.listarPermisosRolNoAsignados(vPermisos);
    }

    public List<SegPermiso> listarPermisosRolAsignados(Integer vidRol) throws Exception {
        return segPermisoDao.listarPermisosRolAsignados(vidRol);
    }

    public List<SegPermiso> listarPermisoPorIdentificacion(String identificacion) throws Exception {
        return segPermisoDao.listarPermisosIdentificacion(identificacion);
    }

    public List<SegPermiso> listarPermisosDeRolesNoAsignadosParamertoAplicacion(List<SegPermiso> vPermiso, Integer valApl) throws Exception {
        return segPermisoDao.listarPermisosDeRolesNoAsignadosPrmrtApli(vPermiso, valApl);
    }

    public List<SegPermiso> listarPermisoParamApli(Integer valApl) throws Exception {
        return segPermisoDao.listarPermisoPrmtApli(valApl);
    }

    public String crearPermiso(SegPermiso vPermiso) throws Exception {
        segPermisoDao.crear(vPermiso);
        return "Se ha creado el permiso" + vPermiso.getPerCodigo();
    }

    public String editarPemiso(SegPermiso vPermiso) throws Exception {
        segPermisoDao.editar(vPermiso);
        return "Se ha modificado el permiso" + vPermiso.getPerCodigo();
    }

    public boolean existePermiso(Integer vPermiso) throws Exception {
        return segPermisoDao.existePorCampo(ENTIDAD_PERMISO, "perCodigo", vPermiso);
    }

    public SegPermiso buscarPermiso(Integer vPermiso) throws Exception {
        return segPermisoDao.buscarPorCampo(ENTIDAD_PERMISO, "perCodigo", vPermiso);
    }

    public String eliminarPermiso(SegPermiso codPermiso) throws Exception {
        segPermisoDao.eliminarGenerico(ENTIDAD_PERMISO, "perCodigo", codPermiso.getPerCodigo());
        return "se ha eliminado el permiso" + codPermiso;
    }
}
