/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sirec.ejb.servicios;

import ec.sirec.ejb.entidades.SegUsuario;
import ec.sirec.ejb.entidades.SegUsuarioRol;
import ec.sirec.ejb.facade.SegUsuarioRolFacade;
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
public class UsuarioRolServicio {

    @EJB
    private SegUsuarioRolFacade usuarioRolDao;
    private String ENTIDAD_USUARIO_ROL = "SegUsuarioRol";

//    public List<SegUsuarioRol> todos() throws Exception {
//       return usuarioRolDao.findAll();
    //    }  
    public SegUsuarioRol listarUsuariosPorRol(SegUsuario iden) throws Exception {
        return usuarioRolDao.buscarPorCampo(ENTIDAD_USUARIO_ROL, "usuIdentificacion", iden);
    }

    public List<SegUsuarioRol> listarUsuariosPorRol(Integer rol) throws Exception {
        return usuarioRolDao.obtenerUsuariosXRol(rol);
    }

    public void guardarUsuarioRol(SegUsuarioRol vUsuarioRol) throws Exception {
        usuarioRolDao.crear(vUsuarioRol);
    }

    public boolean existeUsuarioRol(String vidUsuario, Integer vidRol) throws Exception {
        return usuarioRolDao.existeUsuarioRol(vidUsuario, vidRol);
    }

    public SegUsuarioRol buscarPorUsuarioRol(String vidUsuario, Integer vidRol) throws Exception {
        return usuarioRolDao.buscarPorUsuarioRol(vidUsuario, vidRol);
    }

    public void eliminarUsuarioRol(Integer vidUsuarioRol) throws Exception {
        usuarioRolDao.delete(vidUsuarioRol);
    }
}
