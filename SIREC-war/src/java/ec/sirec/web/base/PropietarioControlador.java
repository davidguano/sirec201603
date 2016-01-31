/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sirec.web.base;

import ec.sirec.ejb.entidades.CatalogoDetalle;
import ec.sirec.ejb.entidades.Propietario;
import ec.sirec.ejb.servicios.PropietarioServicio;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author DAVID GUAN
 */
@ManagedBean
@ViewScoped
public class PropietarioControlador extends BaseControlador {

    private static final Logger LOGGER = Logger.getLogger(PropietarioControlador.class.getName());
    @EJB
    private PropietarioServicio propietarioServicio;

    private Propietario propietarioActual;
    private List<Propietario> listaPropietarios;
    private List<CatalogoDetalle> listaCatCiudades;
    private List<CatalogoDetalle> listaCatTipoPersonaJuridica;
    private boolean flagEditar;

    /**
     * Creates a new instance of PropietarioControlador
     */
    public PropietarioControlador() {
    }

    @PostConstruct
    public void inicializar() {
        try {
            flagEditar = false;
            propietarioActual = new Propietario();
            listaPropietarios = new ArrayList<Propietario>();
            listaCatCiudades = propietarioServicio.listarCiudades();
            listaCatTipoPersonaJuridica = propietarioServicio.listarTiposPersonasJuridicas();
            listarPropietarios();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public void validarCedulaRuc() {
        try {
            if (propietarioServicio.esCedulaRucValida(propietarioActual.getProCi(),flagEditar).equals("valida")) {
                addSuccessMessage("Cedula valida");
            } else {
                addErrorMessage(propietarioServicio.esCedulaRucValida(propietarioActual.getProCi(),flagEditar));
                propietarioActual.setProCi(null);
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public List<CatalogoDetalle> listarCatDetalles(String valor) {
        List<CatalogoDetalle> lstDet = new ArrayList<CatalogoDetalle>();
        try {
            return propietarioServicio.listarCiudadesPorTexto(valor);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        return lstDet;
    }

    public void guardarPropietario() {
        try {
            boolean b = false;
            String s = "";
            if (propietarioActual.getProTipoPersona().equals("N")) {
                s = validarDatosPersonaNatural(propietarioActual);
            }
            if (propietarioActual.getProTipoPersona().equals("J")) {
                s = validarDatosPersonaJuridica(propietarioActual);
            }
            if (s.equals("")) {
                b = true;
            } else {
                b = false;
                addErrorMessage(s);
            }

            if (propietarioServicio.esCedulaRucValida(propietarioActual.getProCi(),flagEditar).equals("valida") && b) {
                if (propietarioServicio.esFechaNacimientoValida(propietarioActual.getProFechaNacimiento())) {
                    if (propietarioActual.getProTipoPersona().equals("N")) {
                        propietarioActual.setCatdetTipoperjur(null);
                    }
                    if (!flagEditar) {
                        propietarioActual.setUsuIdentificacion(obtenerUsuarioAutenticado());
                        propietarioServicio.crearPropietario(propietarioActual);
                        propietarioActual = new Propietario();
                        addSuccessMessage("Propietario creado correctamente");
                    } else {
                        propietarioActual.setUsuIdentificacion(obtenerUsuarioAutenticado());
                        propietarioServicio.editarPropietario(propietarioActual);
                        addSuccessMessage("Propietario editado correctamente");
                    }
                    listarPropietarios();
                } else {
                    addErrorMessage("Fecha no valida");
                }

            } else {
                addErrorMessage(propietarioServicio.esCedulaRucValida(propietarioActual.getProCi(),flagEditar));
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public String validarDatosPersonaNatural(Propietario prop) {
        String s = "";
        try {
            if (prop.getProCi().length() != 10) {
                s = "Cedula debe tener 10 digitos";
            }
            if (prop.getProNombres() == null || prop.getProApellidos() == null || prop.getProNombres().equals("") || prop.getProApellidos().equals("")) {
                s = "Nombres y Apellidos obligatorios";
            } else {
                if (prop.getProFechaNacimiento() == null) {
                    s = "Fecha de nacimiento obligatorio";
                } else {
                    if (prop.getCatdetCiudad() == null) {
                        s = "Ciudad es obligatoria";
                    }
                }
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        return s;
    }

    public String validarDatosPersonaJuridica(Propietario prop) {
        String s = "";
        try {
            if (prop.getProCi().length() != 13) {
                s = "RUC debe tener 13 digitos";
            }
            if (prop.getProRazonSocial() == null || prop.getProRazonSocial().equals("")) {
                s = "Razon social obligatoria";
            } else {
                if (prop.getCatdetCiudad() == null) {
                    s = "Ciudad es obligatoria";
                }

            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        return s;
    }

    public void seleccionarPropietario(Propietario vpropietario) {
        try {
            propietarioActual = vpropietario;
            flagEditar = true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public void eliminarPropietario(Propietario vpropietario) {
        try {
            addErrorMessage(propietarioServicio.eliminarPropietario(vpropietario));
            listarPropietarios();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public void listarPropietarios() {
        try {
            flagEditar = false;
            listaPropietarios = propietarioServicio.listarPropietariosTodos();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public Propietario getPropietarioActual() {
        return propietarioActual;
    }

    public void setPropietarioActual(Propietario propietarioActual) {
        this.propietarioActual = propietarioActual;
    }

    public List<Propietario> getListaPropietarios() {
        return listaPropietarios;
    }

    public void setListaPropietarios(List<Propietario> listaPropietarios) {
        this.listaPropietarios = listaPropietarios;
    }

    public List<CatalogoDetalle> getListaCatCiudades() {
        return listaCatCiudades;
    }

    public void setListaCatCiudades(List<CatalogoDetalle> listaCatCiudades) {
        this.listaCatCiudades = listaCatCiudades;
    }

    public List<CatalogoDetalle> getListaCatTipoPersonaJuridica() {
        return listaCatTipoPersonaJuridica;
    }

    public void setListaCatTipoPersonaJuridica(List<CatalogoDetalle> listaCatTipoPersonaJuridica) {
        this.listaCatTipoPersonaJuridica = listaCatTipoPersonaJuridica;
    }

}
