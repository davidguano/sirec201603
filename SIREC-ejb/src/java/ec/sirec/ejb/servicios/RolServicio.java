/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sirec.ejb.servicios;

import ec.sirec.ejb.entidades.SegRol;
import ec.sirec.ejb.facade.SegRolFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author vespinoza
 */
@Stateless
@LocalBean
public class RolServicio {

    @EJB
    private SegRolFacade rolDao;
    private String ENTIDAD_ROL = "SegRol";

//    public List<SegUsuarioRol> todos() throws Exception {
//       return usuarioRolDao.findAll();
//    }
    public List<SegRol> listarRoles(String nemonico) throws Exception {
        return rolDao.obtenerRoles(nemonico);
    }

    public List<SegRol> listarRolesPorUsuario(String usuario) throws Exception {
        return rolDao.listarRolesPorUsuario(usuario);
    }

    public List<SegRol> listarRolesDeUsuarioAsignados(String vidUsuario) throws Exception {
        return rolDao.listarRolesDeUsuarioAsignados(vidUsuario);
    }

    public List<SegRol> listarRolesDeUsuarioNoAsignados(List<SegRol> vRoles) throws Exception {
        return rolDao.listarRolesDeUsuarioNoAsignados(vRoles);
    }

    public List<SegRol> listarRolesDeUsuarioNoAsignadosParamertoAplicacion(List<SegRol> vRoles, Integer valApl) throws Exception {
        return rolDao.listarRolesDeUsuarioNoAsignadosPrmrtApli(vRoles, valApl);
    }

    public List<SegRol> listarRolsParamApli(Integer valApl) throws Exception {
        return rolDao.listarRolesPrmtApli(valApl);
    }

    public List<SegRol> listarRoles() throws Exception {
        return rolDao.listarOrdenada(ENTIDAD_ROL, "rolCodigo", "asc");
    }

    public List<SegRol> listarRolesIdentificacion(String identificacion) throws Exception {
        return rolDao.listarRolesIdentificacion(identificacion);
    }

    public String creaRol(SegRol codRol) throws Exception {
        rolDao.crear(codRol);
        return "se ha creado el Rol" + codRol;
    }

    public String editaRol(SegRol codAgencia) throws Exception {
        rolDao.editar(codAgencia);
        return "se ha modificado el Rol" + codAgencia;
    }

    public String eliminaRol(Integer codRol) throws Exception {
        rolDao.eliminarGenerico(ENTIDAD_ROL, "rolCodigo", codRol);
        return "se ha eliminado el Rol" + codRol;
    }

    public List<SegRol> listarRolesTodos() throws Exception {
        return rolDao.listarOrdenada(ENTIDAD_ROL, "rolCodigo", "asc");
    }

    public boolean existeCodigoRol(Integer codRol) throws Exception {
        return rolDao.existePorCampo(ENTIDAD_ROL, "rolCodigo", codRol);
    }
}
