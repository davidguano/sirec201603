/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sirec.ejb.servicios;

import ec.sirec.ejb.entidades.SegAplicacion;
import ec.sirec.ejb.entidades.SegPermiso;
import ec.sirec.ejb.facade.SegAplicacionFacade;
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
public class AplicacionServicio {

    @EJB
    private SegAplicacionFacade segAplicacionDao;
    private String ENTIDAD_APLICACION = "SegAplicacion";

    public List<SegAplicacion> listarAplicaciones(String usuario) throws Exception {
        return segAplicacionDao.listarAccesoAplicacion(usuario);
    }

    public List<SegAplicacion> listarAplicacionesTodos() throws Exception {
        return segAplicacionDao.listarOrdenada(ENTIDAD_APLICACION, "apliCodigo", "asc");
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public String crearAplicacion(SegAplicacion vApli) throws Exception {
        segAplicacionDao.crear(vApli);
        return "Se ha creado la aplicación" + vApli.getApliCodigo();
    }

    public String editarAplicacion(SegAplicacion vApli) throws Exception {
        segAplicacionDao.editar(vApli);
        return "Se ha modificado la aplicación" + vApli.getApliCodigo();
    }

    public boolean existeAplicacion(Integer vApli) throws Exception {
        return segAplicacionDao.existePorCampo(ENTIDAD_APLICACION, "apliCodigo", vApli);
    }

    public String eliminarAplicacion(SegAplicacion vApli) throws Exception {
        segAplicacionDao.eliminarGenerico(ENTIDAD_APLICACION, "apliCodigo", vApli.getApliCodigo());
        return "se ha eliminado el permiso" + vApli;
    }
}
