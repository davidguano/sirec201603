/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sirec.ejb.servicios;

import ec.sirec.ejb.entidades.CatalogoDetalle;
import ec.sirec.ejb.entidades.CatastroPredial;
import ec.sirec.ejb.entidades.Propietario;
import ec.sirec.ejb.entidades.PropietarioPredio;
import ec.sirec.ejb.facade.PropietarioFacade;
import ec.sirec.ejb.facade.PropietarioPredioFacade;
import ec.sirec.ejb.util.Utilitarios;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author DAVID GUAN
 */
@Stateless
@LocalBean
public class PropietarioServicio {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB
    private PropietarioFacade propietarioDao;
    private final String ENTIDAD_PROPIETARIO = "Propietario";

    @EJB
    private PropietarioPredioFacade propietarioPredioDao;

    @EJB
    private CatalogoDetalleServicio catalogoDetServicio;

    public void crearPropietario(Propietario vpropietario) throws Exception {

        if (vpropietario.getProTipoPersona().equals("N")) {
            if (vpropietario.getProCi().equals("9999999999")) {
                vpropietario.setProCi(propietarioDao.codigoAutomaticoDesconocidoNatural());
            }
        }
        if (vpropietario.getProTipoPersona().equals("J")) {
            if (vpropietario.getProCi().equals("9999999999999")) {
                vpropietario.setProCi(propietarioDao.codigoAutomaticoDesconocidoJuridico());
            }
        }

        propietarioDao.crear(vpropietario);
    }

    public void editarPropietario(Propietario vpropietario) throws Exception {
        propietarioDao.editar(vpropietario);
    }

    public boolean existePropietarioEnRegistros(Propietario vprop) throws Exception {
        //evaluamos en predios por el momento
        return propietarioPredioDao.existePorCampo("PropietarioPredio", "proCi", vprop);
    }

    public boolean existePropietarioPorCedula(String vcedula) throws Exception {
        //evaluamos en predios por el momento
        return propietarioDao.existePorCampo("Propietario", "proCi", vcedula);
    }

    public String eliminarPropietario(Propietario vpropietario) throws Exception {
        if (existePropietarioEnRegistros(vpropietario)) {
            return "No se puede eliminar. Propietario tiene predios registrados. ";
        } else {
            propietarioDao.eliminar(vpropietario);
            return "Propietario Eliminado Correctamente";
        }

    }

    public List<Propietario> listarPropietariosTodos() throws Exception {
        return propietarioDao.listarOrdenada(ENTIDAD_PROPIETARIO, "proCi", "asc");
    }

    public List<CatalogoDetalle> listarCiudades() throws Exception {
        return catalogoDetServicio.listarPorNemonicoCatalogo("CIUDADES");
    }

    public List<CatalogoDetalle> listarTiposPersonasJuridicas() throws Exception {
        return catalogoDetServicio.listarPorNemonicoCatalogo("TIP_PER_JUR");
    }

    public Propietario buscarPropietario(String cedula) throws Exception {
        return propietarioDao.buscarPorCampo(ENTIDAD_PROPIETARIO, "proCi", cedula);
    }

    public Propietario buscarPropietarioPorCatastro(int codCatastro) throws Exception {
        return propietarioDao.retornaObjPropietarioPorCatastroPred(codCatastro);
    }

    public List<PropietarioPredio> listarPropietariosPredio(Integer idCatastroPre) throws Exception {
        return propietarioPredioDao.listarPorCampoOrdenada("PropietarioPredio", "catpreCodigo.catpreCodigo", idCatastroPre, "propreCodigo", "asc");
    }

    public List<PropietarioPredio> listarPropietariosPredioPorPropietario(Propietario prop) throws Exception {
        return propietarioPredioDao.listarPorCampoOrdenada("PropietarioPredio", "proCi", prop, "propreCodigo", "asc");
    }

    public List<PropietarioPredio> listarPropietariosPredioPorCedulaPropietario(String cedulaProp) throws Exception {
        return propietarioPredioDao.listarPorCampoOrdenada("PropietarioPredio", "proCi.proCi", cedulaProp, "propreCodigo", "asc");
    }

    public List<PropietarioPredio> listarPropietariosPredioPorApellidoPropContiene(String vapellidos) throws Exception {
        return propietarioPredioDao.listarPorCamposContieneOrdenada("PropietarioPredio", "proCi.proApellidos", vapellidos.toUpperCase(), "propreCodigo", "asc");
    }

    public Propietario obtenerPropietarioPrincipalPredio(Integer idCatastroPre) throws Exception {
        Propietario p = new Propietario();
        List<PropietarioPredio> lstpp = new ArrayList<PropietarioPredio>();
        lstpp = this.listarPropietariosPredio(idCatastroPre);
        if (!lstpp.isEmpty()) {
            p = lstpp.get(0).getProCi();
        }
        return p;
    }

    public void guardarPropietarioPredio(PropietarioPredio vPP) throws Exception {
        propietarioPredioDao.crear(vPP);
    }
    public void editarPropietarioPredio(PropietarioPredio vPP) throws Exception {
        propietarioPredioDao.editar(vPP);
    }

    public PropietarioPredio buscarPropietarioPredioPorCodigo(Integer vPPcod) throws Exception {
        return propietarioPredioDao.buscarPorCampo("PropietarioPredio", "propreCodigo", vPPcod);
    }
    public PropietarioPredio buscarPropietarioPredioPorCatastro(Integer vcodCatastro) throws Exception {
        return propietarioPredioDao.buscarPorCampo("PropietarioPredio", "catpreCodigo.catpreCodigo", vcodCatastro);
    }

    public void eliminarPropietarioPredio(PropietarioPredio vPP) throws Exception {
        propietarioPredioDao.eliminar(vPP);
    }

    public String esCedulaRucValida(String vcedula, boolean flagEditar) throws Exception {
        String c = "";
        if (vcedula.substring(0, 4).equals("9999")) {
            return "valida";
        }
        if (vcedula.length() == 13) {
            c = vcedula.substring(0, 10);
        } else {
            c = vcedula;
        }
        if (existePropietarioPorCedula(c)) {
            if(flagEditar){
                return "valida";
            }else{
                return "Ya existe esta cedula";
            }
            
        } else {
            if (Utilitarios.validarCedula(c)) {
                return "valida";
            } else {
                return "Cedula no valida";
            }
        }

    }

    public List<CatalogoDetalle> listarCiudadesPorTexto(String texto) throws Exception {
        return catalogoDetServicio.listarPorNemonicoyTextoContiene("CIUDADES", texto);
    }

    public boolean esFechaNacimientoValida(Date vfechaNac) throws Exception {
        if (vfechaNac != null) {
            Calendar fn = java.util.Calendar.getInstance();
            fn.setTime(vfechaNac);
            Calendar fa = java.util.Calendar.getInstance();
            if ((fa.getTimeInMillis() - fn.getTimeInMillis()) > 360000) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    public List<Propietario> listarPorCedulaContiene(String vci) throws Exception {
        return propietarioDao.listarPorCamposContieneOrdenada(ENTIDAD_PROPIETARIO, "proCi", vci, "proApellidos", "asc");
    }

    public List<Propietario> listarPorApellidosContiene(String vApellidos) throws Exception {
        return propietarioDao.listarPorCamposContieneOrdenada(ENTIDAD_PROPIETARIO, "proApellidos", vApellidos.toUpperCase(), "proApellidos", "asc");
    }

    public List<Propietario> listarPorNombresContiene(String vNombres) throws Exception {
        return propietarioDao.listarPorCamposContieneOrdenada(ENTIDAD_PROPIETARIO, "proNombres", vNombres.toUpperCase(), "proApellidos", "asc");
    }
}
