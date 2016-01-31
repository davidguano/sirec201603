/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sirec.ejb.servicios;

import ec.sirec.ejb.entidades.SegUsuario;
import ec.sirec.ejb.facade.SegUsuarioFacade;
import java.util.Date;
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
public class UsuarioServicio {

    @EJB
    private SegUsuarioFacade segUsuarioDao;
    private String ENTIDAD_USUARIO = "SegUsuario";

    public boolean obtenerUsuarioPorAutenticacion(String username) throws Exception {
        return segUsuarioDao.existeUsuarioPorCampo(username);
    }

    public boolean obtenerUsuarioParametrPassUser(String usuario, String clave) throws Exception {
        return segUsuarioDao.existeUsuarioPorCampoClave(usuario, clave);
    }

    public SegUsuario obtenerUsuarioPorUserName(String username) throws Exception {
        return segUsuarioDao.buscarPorCampo(ENTIDAD_USUARIO, "usuUsuario", username);
    }

    public String crearUsuarioBaseLocal(SegUsuario identificacion) throws Exception {
        identificacion.setUsuFechaCreacion(java.util.Calendar.getInstance().getTime());
        identificacion.setUsuFechaModificacion(java.util.Calendar.getInstance().getTime());
        segUsuarioDao.crear(identificacion);
        return "se ha creado el usuario" + identificacion;
    }

    public boolean obtenerUsuarioPorAutenticacion(String username, String password) throws Exception {
        return segUsuarioDao.existeUsuarioPorCampoClave(username, password);
    }

    public boolean existeIdenUsuario(String usuIdentificacion) throws Exception {
        return segUsuarioDao.existePorCampo(ENTIDAD_USUARIO, "usuIdentificacion", usuIdentificacion);
    }

    public String crearUsuario(SegUsuario usuIdent) throws Exception {
         usuIdent.setUsuFechaCreacion(java.util.Calendar.getInstance().getTime());
        usuIdent.setUsuFechaModificacion(java.util.Calendar.getInstance().getTime());
        segUsuarioDao.crear(usuIdent);
        return "se ha creado el usuario" + usuIdent;
    }

    public String editarUsuario(SegUsuario usuIdent) throws Exception {
        usuIdent.setUsuFechaModificacion(java.util.Calendar.getInstance().getTime());
        segUsuarioDao.editar(usuIdent);
        return "se ha modificado al usuario" + usuIdent;
    }

    public String eliminarUsuario(SegUsuario usuIdent) throws Exception {
        segUsuarioDao.eliminarGenerico(ENTIDAD_USUARIO, "usuIdentificacion", usuIdent.getUsuIdentificacion());
        return "se ha eliminado al usuario" + usuIdent;
    }

    public List<SegUsuario> listarUsuariosTodos() throws Exception {
        return segUsuarioDao.listarOrdenada(ENTIDAD_USUARIO, "usuIdentificacion", "asc");
    }

    public SegUsuario buscaUsuarioDatos(String identificacion) throws Exception {
        return segUsuarioDao.buscarUsuarioIdent(identificacion);
    }
    
     public List<SegUsuario> buscarUsuarioXRolNemonico(String nemonico) throws Exception {
        return segUsuarioDao.obtenerUsuariosXRolNemonico(nemonico); 
    }
}
