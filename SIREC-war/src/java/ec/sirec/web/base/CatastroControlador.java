/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sirec.web.base;

import ec.sirec.ejb.entidades.CatalogoDetalle;
import ec.sirec.ejb.entidades.CatastroPredial;
import ec.sirec.ejb.entidades.CatastroPredialAreas;
import ec.sirec.ejb.entidades.CatastroPredialEdificacion;
import ec.sirec.ejb.entidades.CatastroPredialInfAnt;
import ec.sirec.ejb.entidades.CatastroPredialInfraestructura;
import ec.sirec.ejb.entidades.CatastroPredialUsosuelo;
import ec.sirec.ejb.entidades.CatastroPredialValoracion;
import ec.sirec.ejb.entidades.PredioArchivo;
import ec.sirec.ejb.entidades.Propietario;
import ec.sirec.ejb.entidades.PropietarioPredio;
import ec.sirec.ejb.servicios.CatastroPredialServicio;
import ec.sirec.ejb.servicios.PredioArchivoServicio;
import ec.sirec.web.util.OpcionesUsoSuelo;
import ec.sirec.web.util.UtilitariosCod;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author DAVID GUAN
 */
@ManagedBean
@ViewScoped
public class CatastroControlador extends BaseControlador {

    private static final Logger LOGGER = Logger.getLogger(CatastroControlador.class.getName());
    @EJB
    private CatastroPredialServicio catastroServicio;
    @EJB
    private PredioArchivoServicio predioArchivoServicio;

    private CatastroPredial catastroPredialActual;
    private String cedulaPropietarioBusqueda;
    private String claveCatastralBusqueda;
    private List<CatastroPredial> listaCatastrosDePropietario;
    private PropietarioPredio propietarioPredioBusqueda;

    private Propietario propietarioActual;
    private CatastroPredialValoracion valoracionActual;
    private CatastroPredialAreas catastroAreaActual;
    private List<CatastroPredialAreas> listaCatastroPredialAreasBloque;

    private String tipoInfAnt;
    private String valorInfAnt;
    private List<CatastroPredialInfAnt> listaInformacionAnterior;

    private List<CatalogoDetalle> listaInfServicios;
    private List<CatalogoDetalle> listaInfAlcantarillado1;
    private List<CatalogoDetalle> listaInfUso;
    private List<CatalogoDetalle> listaInfMaterial;
    private List<CatalogoDetalle> listaInfSentido;
    private List<CatalogoDetalle> listaInfEnergiaElec;
    private List<CatalogoDetalle> listaInfAbasAgua;
    private List<CatalogoDetalle> listaInfAlcantarillado2;
    private List<CatalogoDetalle> listaInfOtrosServicios;

    private List<CatalogoDetalle> listaInfServiciosSeleccionado;
    private List<CatalogoDetalle> listaInfAlcantarillado1Seleccionado;
    private List<CatalogoDetalle> listaInfUsoSeleccionado;
    private List<CatalogoDetalle> listaInfMaterialSeleccionado;
    private List<CatalogoDetalle> listaInfSentidoSeleccionado;
    private List<CatalogoDetalle> listaInfEnergiaElecSeleccionado;
    private List<CatalogoDetalle> listaInfAbasAguaSeleccionado;
    private List<CatalogoDetalle> listaInfAlcantarillado2Seleccionado;
    private List<CatalogoDetalle> listaInfOtrosServiciosSeleccionado;

    private List<CatastroPredialUsosuelo> listaUsuSuelo;
    private String grupoUsoSuelo;
    private String subgrupoUsoSuelo;
    private List<SelectItem> listaGruposUsoSuelo;
    private List<SelectItem> listaSubgruposUsoSuelo;

    private String edifBloque;
    private String edifPiso;
    private List<CatastroPredialEdificacion> listaEdifGrupo1_1;
    private List<CatastroPredialEdificacion> listaEdifGrupo1_2;
    private List<CatastroPredialEdificacion> listaEdifGrupo1_3;
    private List<CatastroPredialEdificacion> listaEdifGrupo1_4;
    private List<CatastroPredialEdificacion> listaEdifGrupo234;
    private List<CatastroPredialEdificacion> listaEdifGrupo5_14;
    private List<CatastroPredialEdificacion> listaEdifGrupo5_5;
    private List<CatastroPredialEdificacion> listaEdifGrupo5_6;
    private List<CatastroPredialEdificacion> listaEdifGrupo5_7;
    private List<CatastroPredialEdificacion> listaEdifGrupo5_8;
    private List<CatastroPredialEdificacion> listaEdifGrupo5_9;
    private List<CatalogoDetalle> listaOpcEdifGrupo1_1;
    private List<CatalogoDetalle> listaOpcEdifGrupo1_3;
    private List<CatalogoDetalle> listaOpcEdifGrupo2_1;
    private List<CatalogoDetalle> listaOpcEdifGrupo2_2;
    private List<CatalogoDetalle> listaOpcEdifGrupo2_3;
    private List<CatalogoDetalle> listaOpcEdifGrupo2_4;
    private List<CatalogoDetalle> listaOpcEdifGrupo2_5;
    private List<CatalogoDetalle> listaOpcEdifGrupo2_6;
    private List<CatalogoDetalle> listaOpcEdifGrupo3_1;
    private List<CatalogoDetalle> listaOpcEdifGrupo3_2;
    private List<CatalogoDetalle> listaOpcEdifGrupo3_3;
    private List<CatalogoDetalle> listaOpcEdifGrupo3_4;
    private List<CatalogoDetalle> listaOpcEdifGrupo3_5;
    private List<CatalogoDetalle> listaOpcEdifGrupo3_6;
    private List<CatalogoDetalle> listaOpcEdifGrupo3_7;
    private List<CatalogoDetalle> listaOpcEdifGrupo3_8;
    private List<CatalogoDetalle> listaOpcEdifGrupo3_9;
    private List<CatalogoDetalle> listaOpcEdifGrupo3_10;
    private List<CatalogoDetalle> listaOpcEdifGrupo4_1;
    private List<CatalogoDetalle> listaOpcEdifGrupo4_2;
    private List<CatalogoDetalle> listaOpcEdifGrupo4_3;
    private List<CatalogoDetalle> listaOpcEdifGrupo5_5;

    private List<CatalogoDetalle> listaOpcEdifGrupo5_6;
    private List<CatalogoDetalle> listaOpcEdifGrupo5_7;
    private List<CatalogoDetalle> listaOpcEdifGrupo5_8;
    private List<CatalogoDetalle> listaOpcEdifGrupo5_9;

    private List<CatalogoDetalle> listaParroquias;
    private List<CatalogoDetalle> listaSectores;
    private List<CatalogoDetalle> listaTipoVia;
    private List<CatalogoDetalle> listaTipoUbicacion;
    private List<CatalogoDetalle> listaTipoProp1;
    private List<CatalogoDetalle> listaTipoProp2;
    private List<CatalogoDetalle> listaRefCartograficas;
    private List<CatalogoDetalle> listaTenenciaDominio;
    private List<CatalogoDetalle> listaTenenciaTraslacionDominio;
    private List<CatalogoDetalle> listaTerrenoOcupacion;
    private List<CatalogoDetalle> listaTerrenoNoEdificado;
    private List<CatalogoDetalle> listaTerrenoConstruccion;
    private List<CatalogoDetalle> listaTerrenoCaracteristicasSuelo;
    private List<CatalogoDetalle> listaTerrenoForma;
    private List<CatalogoDetalle> listaTerrenoTopografia;
    private List<CatalogoDetalle> listaTerrenoLocalizacion;
    private List<CatalogoDetalle> listaUsoSueloTipoNegocio;
    private List<CatalogoDetalle> listaUsoSueloTiempoFuncionamiento;
    private List<CatalogoDetalle> listaOtraInfoDimensiones;
    private List<CatalogoDetalle> listaOtraInfoAlicuota;
    private List<CatalogoDetalle> listaOtraInfoFuenteInfo;
    private List<CatalogoDetalle> listaTipoDocRelevamiento;

    //
    private List<PredioArchivo> listaPredioArchivo;
    private PredioArchivo predioArchivoActual;
    private List<PredioArchivo> listaPredioFoto;
    private StreamedContent fotoActual;

    private boolean flagNuevo;
    private boolean flagMedidoresAgua;
    private boolean flagMedidoresLuz;

    /**
     * Creates a new instance of CatastroControlador
     */
    public CatastroControlador() {
    }

    @PostConstruct
    public void inicializar() {
        try {
            flagNuevo = true;
            catastroPredialActual = new CatastroPredial();
            claveCatastralBusqueda = null;
            propietarioPredioBusqueda = new PropietarioPredio();
            tipoInfAnt = null;
            valorInfAnt = null;
            propietarioActual = new Propietario();
            listaCatastrosDePropietario = new ArrayList<CatastroPredial>();
            valoracionActual = new CatastroPredialValoracion();
            catastroAreaActual = new CatastroPredialAreas();
            listaCatastroPredialAreasBloque = new ArrayList<CatastroPredialAreas>();

            listaGruposUsoSuelo = new ArrayList<SelectItem>();
            OpcionesUsoSuelo ou = new OpcionesUsoSuelo();
            listaGruposUsoSuelo = ou.getListaGrupos();
            listaSubgruposUsoSuelo = new ArrayList<SelectItem>();
            ou.cargarSubgruposPorgrupo("1");
            listaSubgruposUsoSuelo = ou.getListaSubGrupos();

            listaUsuSuelo = new ArrayList<CatastroPredialUsosuelo>();
            listaEdifGrupo1_1 = new ArrayList<CatastroPredialEdificacion>();
            listaInfServiciosSeleccionado = new ArrayList<CatalogoDetalle>();
            listaInfAlcantarillado1Seleccionado = new ArrayList<CatalogoDetalle>();
            listaInfUsoSeleccionado = new ArrayList<CatalogoDetalle>();
            listaInfMaterialSeleccionado = new ArrayList<CatalogoDetalle>();
            listaInfSentidoSeleccionado = new ArrayList<CatalogoDetalle>();
            listaInfEnergiaElecSeleccionado = new ArrayList<CatalogoDetalle>();
            listaInfAbasAguaSeleccionado = new ArrayList<CatalogoDetalle>();
            listaInfAlcantarillado2Seleccionado = new ArrayList<CatalogoDetalle>();
            listaInfOtrosServiciosSeleccionado = new ArrayList<CatalogoDetalle>();
            listaPredioArchivo = new ArrayList<PredioArchivo>();
            listaPredioFoto = new ArrayList<PredioArchivo>();
            cargarCatalogos();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public void listarCatastrosPorCedula() {
        try {
            if (cedulaPropietarioBusqueda.length() == 10 || cedulaPropietarioBusqueda.length() == 13) {
                listaCatastrosDePropietario = catastroServicio.listarCatastroPorCedulaPropietario(cedulaPropietarioBusqueda);
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public List<PropietarioPredio> obtenerPropietarioPredioPorApellidoProp(String vapellido) {
        List<PropietarioPredio> lstPP = new ArrayList<PropietarioPredio>();
        try {
            lstPP = catastroServicio.listarPropietariosPredioPorApellidoPropContiene(vapellido);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        return lstPP;
    }

    public void onItemSelect(SelectEvent event) {
        try {
            PropietarioPredio pp = (PropietarioPredio) event.getObject();
            pp = catastroServicio.buscarPropietarioPredioPorCodigo(pp.getPropreCodigo());
            iraCatastroDesdeBusqueda(pp.getCatpreCodigo());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public void iraCatastroDesdeBusqueda(CatastroPredial vcatastro) {
        try {
            catastroPredialActual.setClaveCatastral(vcatastro.getCatpreCodNacional() + vcatastro.getCatpreCodLocal());
            claveCatastralBusqueda = catastroPredialActual.getClaveCatastral();
            if (claveCatastralBusqueda.length() == 19) {
                recuperarDatosDeCatastro();
                listaCatastrosDePropietario = new ArrayList<CatastroPredial>();
                cedulaPropietarioBusqueda = "";
            } else {
                addErrorMessage("La clave de este predio esta incorrecta.");
                inicializar();
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public void validarNuevaClave() {
        try {
            if (catastroPredialActual.getClaveCatastral().length() == 19) {
                catastroPredialActual.setCatpreCodNacional(catastroPredialActual.getClaveCatastral().substring(0, 7));
                catastroPredialActual.setCatpreCodLocal(catastroPredialActual.getClaveCatastral().substring(7, 19));
                String codNac = catastroPredialActual.getCatpreCodNacional();
                String codLoc = catastroPredialActual.getCatpreCodLocal();
                if (codNac != null && codLoc != null) {
                    catastroPredialActual = catastroServicio.buscarCatastroPorCodigosClave(codNac, codLoc);
                    if (catastroPredialActual == null) {
                        addSuccessMessage("Clave Valida");
                        catastroPredialActual = new CatastroPredial();
                        catastroPredialActual.setCatpreCodNacional(codNac);
                        catastroPredialActual.setCatpreCodLocal(codLoc);
                        listaSectores = catastroServicio.listaCatSectores(codNac);
                        if (!listaSectores.isEmpty()) {
                            catastroPredialActual.setCatdetParroquia(catastroServicio.listaCatParroquiasPorCodigo(codNac).get(0));
                        }
                    } else {
                        addErrorMessage("Clave ya existe");
                        inicializar();
                    }
                }
            } else {
                addErrorMessage("Clave Incorrecta");
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public void guardarRegistroPrincipal() {
        try {

            catastroPredialActual.setUsuIdentificacion(obtenerUsuarioAutenticado());
            catastroPredialActual.setUltaccMarcatiempo(java.util.Calendar.getInstance().getTime());
            if (catastroPredialActual.getCatpreCodigo() == null) {
                catastroPredialActual.setCatpreCodNacional(catastroPredialActual.getClaveCatastral().substring(0, 7));
                catastroPredialActual.setCatpreCodLocal(catastroPredialActual.getClaveCatastral().substring(7, 19));

                String codNac = catastroPredialActual.getCatpreCodNacional();
                String codLoc = catastroPredialActual.getCatpreCodLocal();
                boolean b = false;
                if (codNac != null && codLoc != null) {
                    b = catastroServicio.existeCatastroPorCodigosClave(codNac, codLoc);
                }
                if (!b) {
                    catastroPredialActual.setUltaccDetalle("Se ha creado el registro");
                    catastroServicio.guardarCatastroPredial(catastroPredialActual);
                    guardarPropietario();
                    crearRegistrosUsoSuelo();
                    listaUsuSuelo = catastroServicio.listarRegistrosUsuSueloPorCatastro(catastroPredialActual);
                    addSuccessMessage("Correcto", "Ficha creada exitosamente");
                    flagNuevo = false;
                } else {
                    addErrorMessage("Clave ya existe, presione Buscar.");
                }
            } else {
                catastroPredialActual.setUltaccDetalle("Se ha editado el registro");
                catastroServicio.editarCatastroPredial(catastroPredialActual);
                addSuccessMessage("Correcto", "Ficha editada exitosamente");
            }

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public void guardarRegistroPrincipalyPropietario() {
        try {

            catastroPredialActual.setUsuIdentificacion(obtenerUsuarioAutenticado());
            catastroPredialActual.setUltaccMarcatiempo(java.util.Calendar.getInstance().getTime());
            if (catastroPredialActual.getCatpreCodigo() == null) {
                catastroPredialActual.setCatpreCodNacional(catastroPredialActual.getClaveCatastral().substring(0, 7));
                catastroPredialActual.setCatpreCodLocal(catastroPredialActual.getClaveCatastral().substring(7, 19));

                String codNac = catastroPredialActual.getCatpreCodNacional();
                String codLoc = catastroPredialActual.getCatpreCodLocal();
                boolean b = false;
                if (codNac != null && codLoc != null) {
                    b = catastroServicio.existeCatastroPorCodigosClave(codNac, codLoc);
                }
                if (!b) {
                    catastroPredialActual.setUltaccDetalle("Se ha creado el registro");
                    catastroServicio.guardarCatastroPredial(catastroPredialActual);
                    guardarPropietario();
                    crearRegistrosUsoSuelo();
                    listaUsuSuelo = catastroServicio.listarRegistrosUsuSueloPorCatastro(catastroPredialActual);
                    addSuccessMessage("Correcto", "Ficha creada exitosamente");
                    flagNuevo = false;
                } else {
                    addErrorMessage("Clave ya existe, presione Buscar.");
                }
            } else {
                catastroPredialActual.setUltaccDetalle("Se ha editado el registro");
                catastroServicio.editarCatastroPredial(catastroPredialActual);
                addSuccessMessage("Correcto", "Ficha editada exitosamente");
                guardarPropietario();
            }

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public void guardarInformacionAnterior() {
        try {
            if (valorInfAnt != null && valorInfAnt.length() > 0) {
                if (catastroPredialActual.getCatpreCodigo() != null) {
                    catastroServicio.guardarInformacionAnterior(catastroPredialActual, tipoInfAnt, valorInfAnt);
                    listaInformacionAnterior = catastroServicio.listarInformacionAnteriorCatastro(catastroPredialActual);
                }
            }
            valorInfAnt = null;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public void eliminarInformacionAnterior(CatastroPredialInfAnt vinf) {
        try {
            catastroServicio.eliminarInformacionAnterior(vinf);
            listaInformacionAnterior = catastroServicio.listarInformacionAnteriorCatastro(catastroPredialActual);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public void crearRegistrosUsoSuelo() {
        try {
            catastroServicio.crearRegistrosUsoSueloCatastro(catastroPredialActual);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public void listarSubgruposUsoSuelo() {
        try {
            OpcionesUsoSuelo ou = new OpcionesUsoSuelo();
            ou.cargarSubgruposPorgrupo(grupoUsoSuelo);
            listaSubgruposUsoSuelo = ou.getListaSubGrupos();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public void listarRegistrosUsoSuelo() {
        try {
            if (catastroPredialActual.getCatpreCodigo() != null) {
                listaUsuSuelo = catastroServicio.listarRegistrosUsuSueloPorCatastroySubgrupo(catastroPredialActual, subgrupoUsoSuelo);
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public void editarRegistrosUsoSuelo() {
        try {
            catastroServicio.editarRegistroUsuSuelo(listaUsuSuelo);
            guardarRegistroPrincipal();
            addSuccessMessage("Registros Editados Correctamente");
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public void guardarPropietario() {
        try {
            if (catastroPredialActual.getCatpreCodigo() != null && propietarioActual.getUsuIdentificacion() != null) {
                PropietarioPredio pp = new PropietarioPredio();
                pp.setProCi(propietarioActual);
                pp.setCatpreCodigo(catastroPredialActual);
                addSuccessMessage(catastroServicio.guardarPropietarioPredio(pp));
                catastroServicio.cargarListaPropietariosPredio(catastroPredialActual);

            } else {
                addErrorMessage("No se pudo guardar propietario. Registrelo Nuevamente.");
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public void eliminarPropietario(PropietarioPredio vpp) {
        try {
            catastroServicio.eliminarPropietarioPredio(vpp);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public void guardarRegistrosdeArea() {
        try {
            if (catastroPredialActual.getCatpreCodigo() != null) {
                catastroServicio.guardarAreaBloque(catastroPredialActual, catastroAreaActual);
                listaCatastroPredialAreasBloque = catastroServicio.listarAreasPorCatastro(catastroPredialActual.getCatpreCodigo());
                catastroAreaActual = new CatastroPredialAreas();
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public void editarRegistrosdeArea() {
        try {
            if (!listaCatastroPredialAreasBloque.isEmpty()) {
                catastroServicio.calcularyGuardarAreaTotalConstruccion(catastroPredialActual, listaCatastroPredialAreasBloque);
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public void eliminarAreasyEdificacion() {
        try {
            if (!listaCatastroPredialAreasBloque.isEmpty()) {
                catastroServicio.eliminarAreaBloquedeCatastro(catastroPredialActual);
                addSuccessMessage("Areas eliminadas correctamente");
                catastroPredialActual.setCatpreAreaTotalCons(Double.valueOf("0"));
                catastroServicio.editarCatastroPredial(catastroPredialActual);
                listaCatastroPredialAreasBloque = new ArrayList<CatastroPredialAreas>();

            }
            if (!listaEdifGrupo1_1.isEmpty()) {
                catastroServicio.eliminarCatastroPredEdificacionPorCatastro(catastroPredialActual);
                addSuccessMessage("Registros de edificacion eliminados correctamente");
                inicializarDatosEdificaciones();
                listarInformacionEdificaciones();
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public void guardarRegistrosInfraestructura() {
        try {
            guardarRegistroPrincipal();
            catastroServicio.guardarItemsInfraestructura(catastroPredialActual, 1, listaInfServiciosSeleccionado);
            catastroServicio.guardarItemsInfraestructura(catastroPredialActual, 3, listaInfAlcantarillado1Seleccionado);
            catastroServicio.guardarItemsInfraestructura(catastroPredialActual, 11, listaInfUsoSeleccionado);
            catastroServicio.guardarItemsInfraestructura(catastroPredialActual, 12, listaInfMaterialSeleccionado);
            catastroServicio.guardarItemsInfraestructura(catastroPredialActual, 14, listaInfSentidoSeleccionado);
            catastroServicio.guardarItemsInfraestructura(catastroPredialActual, 20, listaInfEnergiaElecSeleccionado);
            catastroServicio.guardarItemsInfraestructura(catastroPredialActual, 30, listaInfAbasAguaSeleccionado);
            catastroServicio.guardarItemsInfraestructura(catastroPredialActual, 40, listaInfAlcantarillado2Seleccionado);
            catastroServicio.guardarItemsInfraestructura(catastroPredialActual, 50, listaInfOtrosServiciosSeleccionado);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public void crearRegistrosEdificaciones() {
        try {
            if (!listaCatastroPredialAreasBloque.isEmpty()) {
                if (!catastroServicio.existenRegistrosEdificacionesPorPredio(catastroPredialActual)) {
                    catastroServicio.crearRegistrosEdificacionesPorNumBloquesYPisos(catastroPredialActual, listaCatastroPredialAreasBloque);
                    recuperarDatosdeEdificaciones();
                } else {
                    addErrorMessage("Ya no puede volver a crear los registros.");
                }
            } else {
                addErrorMessage("No existen registros de areas que indiquen el numero de bloques y sus pisos");
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public void editarRegistrosEdificacion() {
        try {
            catastroServicio.editarCatastroPredEdificacion(listaEdifGrupo1_1);
            catastroServicio.editarCatastroPredEdificacion(listaEdifGrupo1_2);
            catastroServicio.editarCatastroPredEdificacion(listaEdifGrupo1_3);
            catastroServicio.editarCatastroPredEdificacion(listaEdifGrupo1_4);
            catastroServicio.editarCatastroPredEdificacion(listaEdifGrupo234);
            catastroServicio.editarCatastroPredEdificacion(listaEdifGrupo5_14);
            catastroServicio.editarCatastroPredEdificacion(listaEdifGrupo5_5);
            catastroServicio.editarCatastroPredEdificacion(listaEdifGrupo5_6);
            catastroServicio.editarCatastroPredEdificacion(listaEdifGrupo5_7);
            catastroServicio.editarCatastroPredEdificacion(listaEdifGrupo5_8);
            catastroServicio.editarCatastroPredEdificacion(listaEdifGrupo5_9);
            addSuccessMessage("Registros Editados Correctamente");
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public void recuperarDatosDeCatastro() {
        try {
            String codNac = null;
            String codLoc = null;
            if (claveCatastralBusqueda.length() == 19) {
                codNac = claveCatastralBusqueda.substring(0, 7);
                codLoc = claveCatastralBusqueda.substring(7, 19);
            }
            if (codNac != null && codLoc != null) {
                catastroPredialActual = catastroServicio.buscarCatastroPorCodigosClave(codNac, codLoc);
                if (catastroPredialActual != null) {
                    flagNuevo = false;
                    listaSectores = catastroServicio.listaCatSectores(codNac);
                    catastroServicio.cargarListaPropietariosPredio(catastroPredialActual);
                    if (!catastroPredialActual.getListaPropietariosPredio().isEmpty()) {
                        propietarioActual = catastroPredialActual.getListaPropietariosPredio().get(0).getProCi();
                    }
                    listaInformacionAnterior = catastroServicio.listarInformacionAnteriorCatastro(catastroPredialActual);
                    listaCatastroPredialAreasBloque = catastroServicio.listarAreasPorCatastro(catastroPredialActual.getCatpreCodigo());
                    recuperarDatosDeCatastroInfraestructura();
                    listaUsuSuelo = catastroServicio.listarRegistrosUsuSueloPorCatastroySubgrupo(catastroPredialActual, "11");
                    recuperarDatosdeEdificaciones();
                    valoracionActual = catastroServicio.obtenerValoracionPredio(catastroPredialActual);
                    listarArchivos();
                    listarArchivosFoto();
                } else {
                    inicializar();
                    addErrorMessage("Error", "No existe registro con esta clave");
                }
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public void obtenerPropietario() {
        try {
            String ci = propietarioActual.getProCi();
            if (!ci.isEmpty()) {
                propietarioActual = catastroServicio.buscarPropietarioPorCi(ci);
                if (propietarioActual != null) {
                    //
                } else {
                    propietarioActual = new Propietario();
                }
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public void recuperarDatosDeCatastroInfraestructura() {
        try {
            listaInfServiciosSeleccionado = catastroServicio.listarInfraestructuraPorCatastroItemSeleccionados(catastroPredialActual, 1);
            listaInfAlcantarillado1Seleccionado = catastroServicio.listarInfraestructuraPorCatastroItemSeleccionados(catastroPredialActual, 3);
            listaInfUsoSeleccionado = catastroServicio.listarInfraestructuraPorCatastroItemSeleccionados(catastroPredialActual, 11);
            listaInfMaterialSeleccionado = catastroServicio.listarInfraestructuraPorCatastroItemSeleccionados(catastroPredialActual, 12);
            listaInfSentidoSeleccionado = catastroServicio.listarInfraestructuraPorCatastroItemSeleccionados(catastroPredialActual, 14);
            listaInfEnergiaElecSeleccionado = catastroServicio.listarInfraestructuraPorCatastroItemSeleccionados(catastroPredialActual, 20);
            listaInfAbasAguaSeleccionado = catastroServicio.listarInfraestructuraPorCatastroItemSeleccionados(catastroPredialActual, 30);
            listaInfAlcantarillado2Seleccionado = catastroServicio.listarInfraestructuraPorCatastroItemSeleccionados(catastroPredialActual, 40);
            listaInfOtrosServiciosSeleccionado = catastroServicio.listarInfraestructuraPorCatastroItemSeleccionados(catastroPredialActual, 50);
            validarMedidoresInfraestructura();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public void validarMedidoresInfraestructura() {
        try {
            flagMedidoresAgua = false;
            flagMedidoresLuz = false;
            if (!listaInfServiciosSeleccionado.isEmpty()) {
                for (CatalogoDetalle inf : listaInfServiciosSeleccionado) {
                    if (inf.getCatdetCodigo() == 60) {
                        flagMedidoresAgua = true;
                    }
                    if (inf.getCatdetCodigo() == 61) {
                        flagMedidoresLuz = true;
                    }
                }
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public void recuperarDatosdeEdificaciones() {
        try {
            listaEdifGrupo1_1 = catastroServicio.listarEdificacionesGrupo1_1(catastroPredialActual, "1", "1");
            listaEdifGrupo1_2 = catastroServicio.listarEdificacionesGrupo1_2(catastroPredialActual, "1", "1");
            listaEdifGrupo1_3 = catastroServicio.listarEdificacionesGrupo1_3(catastroPredialActual, "1", "1");
            listaEdifGrupo1_4 = catastroServicio.listarEdificacionesGrupo1_4(catastroPredialActual, "1", "1");
            listaEdifGrupo234 = catastroServicio.listarEdificacionesGrupo234(catastroPredialActual, "1", "1");
            listaEdifGrupo5_14 = catastroServicio.listarEdificacionesGrupo5(catastroPredialActual, "0", "0");
            listaEdifGrupo5_5 = catastroServicio.listarEdificacionesGrupo5_5(catastroPredialActual, "0", "0");
            listaEdifGrupo5_6 = catastroServicio.listarEdificacionesGrupo5_6(catastroPredialActual, "0", "0");
            listaEdifGrupo5_7 = catastroServicio.listarEdificacionesGrupo5_7(catastroPredialActual, "0", "0");
            listaEdifGrupo5_8 = catastroServicio.listarEdificacionesGrupo5_8(catastroPredialActual, "0", "0");
            listaEdifGrupo5_9 = catastroServicio.listarEdificacionesGrupo5_9(catastroPredialActual, "0", "0");

            listaOpcEdifGrupo1_1 = catastroServicio.listaOpcionesEdificacion("1_1");
            listaOpcEdifGrupo1_3 = catastroServicio.listaOpcionesEdificacion("1_3");
            listaOpcEdifGrupo2_1 = catastroServicio.listaOpcionesEdificacionConLista("234", "1,2,3,4,6,7,8,9,10,12");
            listaOpcEdifGrupo2_2 = catastroServicio.listaOpcionesEdificacionConLista("234", "1,2,3,4,6,8,10,13");
            listaOpcEdifGrupo2_3 = catastroServicio.listaOpcionesEdificacionConLista("234", "1,2,6,8,10,26,28");
            listaOpcEdifGrupo2_4 = catastroServicio.listaOpcionesEdificacionConLista("234", "1,2,8,9,10,11,12,13,14,15,16");
            listaOpcEdifGrupo2_5 = catastroServicio.listaOpcionesEdificacionConLista("234", "1,2,3,6,7,8,9,10,11,12,13");
            listaOpcEdifGrupo2_6 = catastroServicio.listaOpcionesEdificacionConLista("234", "1,2,6,8,10,47");
            listaOpcEdifGrupo3_1 = catastroServicio.listaOpcionesEdificacionConLista("234", "1,12,13,17,18,19,20,21,22,23,24,25,26,27,28,29");
            listaOpcEdifGrupo3_2 = catastroServicio.listaOpcionesEdificacionConLista("234", "1,17,23,28,29,30,31,36");
            listaOpcEdifGrupo3_3 = catastroServicio.listaOpcionesEdificacionConLista("234", "1,17,23,28,29,30,31,36");
            listaOpcEdifGrupo3_4 = catastroServicio.listaOpcionesEdificacionConLista("234", "1,2,6,8,10,23,24");
            listaOpcEdifGrupo3_5 = catastroServicio.listaOpcionesEdificacionConLista("234", "1,10,16,35,36,100,101");
            listaOpcEdifGrupo3_6 = catastroServicio.listaOpcionesEdificacionConLista("234", "1,2,10,34,37,38,39,40");
            listaOpcEdifGrupo3_7 = catastroServicio.listaOpcionesEdificacionConLista("234", "1,6,8,10,28,32,33,36,46,66");
            listaOpcEdifGrupo3_8 = catastroServicio.listaOpcionesEdificacionConLista("234", "1,6,8,10,32");
            listaOpcEdifGrupo3_9 = catastroServicio.listaOpcionesEdificacionConLista("234", "1,6,8,32");
            listaOpcEdifGrupo3_10 = catastroServicio.listaOpcionesEdificacionConLista("234", "1,10,47,62");
            listaOpcEdifGrupo4_1 = catastroServicio.listaOpcionesEdificacionConLista("234", "1,48,49,50,51,52,99");
            listaOpcEdifGrupo4_2 = catastroServicio.listaOpcionesEdificacionConLista("234", "1,53,54,55,56,57,58");
            listaOpcEdifGrupo4_3 = catastroServicio.listaOpcionesEdificacionConLista("234", "1,60,61,62,99");
            listaOpcEdifGrupo5_5 = catastroServicio.listaOpcionesEdificacion("5_5");
            listaOpcEdifGrupo5_6 = catastroServicio.listaOpcionesEdificacion("5_6");
            listaOpcEdifGrupo5_7 = catastroServicio.listaOpcionesEdificacion("5_7");
            listaOpcEdifGrupo5_8 = catastroServicio.listaOpcionesEdificacion("5_8");
            listaOpcEdifGrupo5_9 = catastroServicio.listaOpcionesEdificacion("5_9");

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public void inicializarDatosEdificaciones() {
        try {
            listaEdifGrupo1_1 = new ArrayList<CatastroPredialEdificacion>();
            listaEdifGrupo1_2 = new ArrayList<CatastroPredialEdificacion>();
            listaEdifGrupo1_3 = new ArrayList<CatastroPredialEdificacion>();
            listaEdifGrupo1_4 = new ArrayList<CatastroPredialEdificacion>();
            listaEdifGrupo234 = new ArrayList<CatastroPredialEdificacion>();
            listaEdifGrupo5_14 = new ArrayList<CatastroPredialEdificacion>();
            listaEdifGrupo5_5 = new ArrayList<CatastroPredialEdificacion>();
            listaEdifGrupo5_6 = new ArrayList<CatastroPredialEdificacion>();
            listaEdifGrupo5_7 = new ArrayList<CatastroPredialEdificacion>();
            listaEdifGrupo5_8 = new ArrayList<CatastroPredialEdificacion>();
            listaEdifGrupo5_9 = new ArrayList<CatastroPredialEdificacion>();

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public void listarInformacionEdificaciones() {
        try {
            listaEdifGrupo1_1 = catastroServicio.listarEdificacionesGrupo1_1(catastroPredialActual, edifBloque, edifPiso);
            listaEdifGrupo1_2 = catastroServicio.listarEdificacionesGrupo1_2(catastroPredialActual, edifBloque, edifPiso);
            listaEdifGrupo1_3 = catastroServicio.listarEdificacionesGrupo1_3(catastroPredialActual, edifBloque, edifPiso);
            listaEdifGrupo1_4 = catastroServicio.listarEdificacionesGrupo1_4(catastroPredialActual, edifBloque, edifPiso);
            listaEdifGrupo234 = catastroServicio.listarEdificacionesGrupo234(catastroPredialActual, edifBloque, edifPiso);
            listaEdifGrupo5_14 = catastroServicio.listarEdificacionesGrupo5(catastroPredialActual, "0", "0");
            listaEdifGrupo5_5 = catastroServicio.listarEdificacionesGrupo5_5(catastroPredialActual, "0", "0");
            listaEdifGrupo5_6 = catastroServicio.listarEdificacionesGrupo5_6(catastroPredialActual, "0", "0");
            listaEdifGrupo5_7 = catastroServicio.listarEdificacionesGrupo5_7(catastroPredialActual, "0", "0");
            listaEdifGrupo5_8 = catastroServicio.listarEdificacionesGrupo5_8(catastroPredialActual, "0", "0");
            listaEdifGrupo5_9 = catastroServicio.listarEdificacionesGrupo5_9(catastroPredialActual, "0", "0");

            if (listaEdifGrupo1_1.isEmpty()) {
                addErrorMessage("No existen areas para este bloque y piso.");
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public void cargarCatalogos() {
        try {

            listaTipoVia = catastroServicio.listaCatTipoVia();
            listaTipoUbicacion = catastroServicio.listaCatTipoUbicacion();
            listaTipoProp1 = catastroServicio.listaCatTipoProp1();
            listaTipoProp2 = catastroServicio.listaCatTipoProp2();
            listaRefCartograficas = catastroServicio.listaCatRefCartograficas();
            listaTenenciaDominio = catastroServicio.listaTenenciaDominio();
            listaTenenciaTraslacionDominio = catastroServicio.listaTenenciaTraslacionDominio();
            listaTerrenoOcupacion = catastroServicio.listaTerrenoOcupacion();
            listaTerrenoNoEdificado = catastroServicio.listaTerrenoNoEdificado();
            listaTerrenoConstruccion = catastroServicio.listaTerrenoConstruccion();
            listaTerrenoCaracteristicasSuelo = catastroServicio.listaTerrenoCaracteristicasSuelo();
            listaTerrenoForma = catastroServicio.listaTerrenoForma();
            listaTerrenoTopografia = catastroServicio.listaTerrenoTopografia();
            listaTerrenoLocalizacion = catastroServicio.listaTerrenoLocalizacion();
            listaUsoSueloTipoNegocio = catastroServicio.listaUsoSueloTipoNegocio();
            listaUsoSueloTiempoFuncionamiento = catastroServicio.listaUsoSueloTiempoFuncionamiento();
            listaOtraInfoDimensiones = catastroServicio.listaOtraInfoDimensiones();
            listaOtraInfoAlicuota = catastroServicio.listaOtraInfoAlicuota();
            listaOtraInfoFuenteInfo = catastroServicio.listaOtraInfoFuenteInf();
            listaTipoDocRelevamiento = catastroServicio.listaTipoDocRelevamiento();

            listaInfServicios = catastroServicio.listarInfServicios();
            listaInfAlcantarillado1 = catastroServicio.listarInfAlcantarillado1();
            listaInfUso = catastroServicio.listarInfUso();
            listaInfMaterial = catastroServicio.listarInfMaterial();
            listaInfSentido = catastroServicio.listarInfSentido();
            listaInfEnergiaElec = catastroServicio.listarInfEnergiaElect();
            listaInfAbasAgua = catastroServicio.listarInfAbasAgua();
            listaInfAlcantarillado2 = catastroServicio.listarInfAlcantarillado2();
            listaInfOtrosServicios = catastroServicio.listarInfOtrosServicios();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    //ARCHIVOS
    public void listarArchivos() {
        try {
            if (catastroPredialActual != null) {
                listaPredioArchivo = new ArrayList<PredioArchivo>();
                // listaPredioArchivo = predioArchivoServicio.listarArchivos(usuarioActual);
                listaPredioArchivo = predioArchivoServicio.listarArchivosXImpuesto(catastroPredialActual, "FC");
            } else {
                listaPredioArchivo = new ArrayList<PredioArchivo>();
                addWarningMessage("Eliga la clave Catastral!");

            }

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public void listarArchivosFoto() {
        try {
            if (catastroPredialActual != null) {
                listaPredioFoto = new ArrayList<PredioArchivo>();
                // listaPredioArchivo = predioArchivoServicio.listarArchivos(usuarioActual);
                listaPredioFoto = predioArchivoServicio.listarArchivosXImpuesto(catastroPredialActual, "F");
                //if(!listaPredioFoto.isEmpty()){
                //   fotoActual=new DefaultStreamedContent(new ByteArrayInputStream(listaPredioFoto.get(0).getPrearcData()));
                //}
            } else {
                listaPredioFoto = new ArrayList<PredioArchivo>();
                addWarningMessage("Eliga la clave Catastral!");

            }

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public void eliminarArchivo(PredioArchivo archivo) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            predioArchivoServicio.eliminarPredioArchivo(archivo);
            context.addMessage(null, new FacesMessage("Mensaje:", "Se Elimino el Archivo  " + archivo.getPrearcNombre()));
            listarArchivos();

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public void handleFileUpload(FileUploadEvent event) {

        try {

            if (catastroPredialActual.getCatpreCodigo() != null) {
                predioArchivoActual = new PredioArchivo();
                predioArchivoActual.setPrearcNombre(event.getFile().getFileName().replace(" ", "_"));
                predioArchivoActual.setCatpreCodigo(catastroPredialActual);
                predioArchivoActual.setPrearcData(event.getFile().getContents());
                predioArchivoActual.setPrearcTipo("FC");
                predioArchivoActual.setUsuIdentificacion(obtenerUsuarioAutenticado());
                predioArchivoActual.setUltaccDetalle("");
                predioArchivoActual.setUltaccMarcatiempo(new Date());

                predioArchivoServicio.crearPredioArchivo(predioArchivoActual);

                FacesMessage msg = new FacesMessage("El documento ", event.getFile().getFileName() + " ha sido cargado satisfactoriamente.");
                FacesContext.getCurrentInstance().addMessage(null, msg);

                listarArchivos();
            } else {
                addErrorMessage("Seleccione Clave Catastral!!!");
            }

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public void handleFileUploadFoto(FileUploadEvent event) {

        try {

            if (catastroPredialActual.getCatpreCodigo() != null) {
                predioArchivoActual = new PredioArchivo();
                predioArchivoActual.setPrearcNombre(event.getFile().getFileName().replace(" ", "_"));
                predioArchivoActual.setCatpreCodigo(catastroPredialActual);
                predioArchivoActual.setPrearcData(event.getFile().getContents());
                predioArchivoActual.setPrearcTipo("F");
                predioArchivoActual.setUsuIdentificacion(obtenerUsuarioAutenticado());
                predioArchivoActual.setUltaccDetalle("");
                predioArchivoActual.setUltaccMarcatiempo(new Date());

                predioArchivoServicio.crearPredioArchivo(predioArchivoActual);

                FacesMessage msg = new FacesMessage("El documento ", event.getFile().getFileName() + " ha sido cargado satisfactoriamente.");
                FacesContext.getCurrentInstance().addMessage(null, msg);

                listarArchivosFoto();
            } else {
                addErrorMessage("Seleccione Clave Catastral!!!");
            }

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public void startDownload(PredioArchivo archivo) {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance()
                .getExternalContext().getResponse();
        try {
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment;filename=" + archivo.getPrearcNombre());
            response.getOutputStream().write(archivo.getPrearcData());
            response.getOutputStream().flush();
            response.getOutputStream().close();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (IOException ioex) {
            LOGGER.log(Level.SEVERE, null, ioex);
        }
    }

    public String generarReporteFichaCatastral() throws Exception {
        //Conexion con local datasource
        UtilitariosCod util = new UtilitariosCod();
        Connection conexion = util.getConexion();
        byte[] fichero = null;
        JasperReport jasperReport = null;
        Map parameters = new HashMap();
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
            ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
            session.removeAttribute("reporteInforme");
            parameters.put("catpre_codigo", catastroPredialActual.getCatpreCodigo());
            parameters.put("logo_gad", servletContext.getRealPath("/imagenes/icons/gadPedroMoncayo.jpg"));
            //-----------Sub reportes----------------
            String subrep1 = servletContext.getRealPath("/reportes/fichaCatastral");
            parameters.put("SUBREPORT_DIR1", subrep1);
            String subrep2 = servletContext.getRealPath("/reportes/fichaCatastral");
            parameters.put("SUBREPORT_DIR2", subrep2);
            String subrep3 = servletContext.getRealPath("/reportes/fichaCatastral");
            parameters.put("SUBREPORT_DIR3", subrep3);
            //---------Sub sub reportes pagina 1 

            String subrep1_1 = servletContext.getRealPath("/reportes/fichaCatastral");
            parameters.put("SUBREPORT_DIR1_1", subrep1_1);
            String subrep1_2 = servletContext.getRealPath("/reportes/fichaCatastral");
            parameters.put("SUBREPORT_DIR1_2", subrep1_2);
            String subrep1_3 = servletContext.getRealPath("/reportes/fichaCatastral");
            parameters.put("SUBREPORT_DIR1_3", subrep1_3);
            // ------------------
            //---------Sub sub reportes pagina 2 
            String subrep2_1 = servletContext.getRealPath("/reportes/fichaCatastral");
            parameters.put("SUBREPORT_DIR2_1", subrep2_1);
            String subrep2_2 = servletContext.getRealPath("/reportes/fichaCatastral");
            parameters.put("SUBREPORT_DIR2_2", subrep2_2);

            // ------------------
            //---------Sub sub reporte detalles pagina 2
            String subrep2_1_a = servletContext.getRealPath("/reportes/fichaCatastral");
            parameters.put("SUBREPORT_DIR2_1_a", subrep2_1_a);
            String subrep2_1_b = servletContext.getRealPath("/reportes/fichaCatastral");
            parameters.put("SUBREPORT_DIR2_1_b", subrep2_1_b);
            String subrep2_1_c = servletContext.getRealPath("/reportes/fichaCatastral");
            parameters.put("SUBREPORT_DIR2_1_c", subrep2_1_c);
            String subrep2_1_d = servletContext.getRealPath("/reportes/fichaCatastral");
            parameters.put("SUBREPORT_DIR2_1_d", subrep2_1_d);
            String subrep2_1_e = servletContext.getRealPath("/reportes/fichaCatastral");
            parameters.put("SUBREPORT_DIR2_1_e", subrep2_1_e);
            String subrep2_1_f = servletContext.getRealPath("/reportes/fichaCatastral");
            parameters.put("SUBREPORT_DIR2_1_f", subrep2_1_f);
            String subrep2_1_g = servletContext.getRealPath("/reportes/fichaCatastral");
            parameters.put("SUBREPORT_DIR2_1_g", subrep2_1_g);
            String subrep2_1_h = servletContext.getRealPath("/reportes/fichaCatastral");
            parameters.put("SUBREPORT_DIR2_1_h", subrep2_1_h);
            String subrep2_1_i = servletContext.getRealPath("/reportes/fichaCatastral");
            parameters.put("SUBREPORT_DIR2_1_i", subrep2_1_i);

            //---------Sub sub reportes pagina 3
            String subrep3_1 = servletContext.getRealPath("/reportes/fichaCatastral");
            parameters.put("SUBREPORT_DIR3_1", subrep3_1);
            String subrep3_2 = servletContext.getRealPath("/reportes/fichaCatastral");
            parameters.put("SUBREPORT_DIR3_2", subrep3_2);
            jasperReport = (JasperReport) JRLoader.loadObject(servletContext.getRealPath("/reportes/fichaCatastral/repFichaCatastral.jasper"));
            fichero = JasperRunManager.runReportToPdf(jasperReport, parameters, conexion);
            session.setAttribute("reporteInforme", fichero);

        } catch (JRException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        } finally {
            if (conexion != null) {
                conexion.close();
            }
        }
        return null;
    }

    //GETTER AND SETTERS
    public CatastroPredial getCatastroPredialActual() {
        return catastroPredialActual;
    }

    public void setCatastroPredialActual(CatastroPredial catastroPredialActual) {
        this.catastroPredialActual = catastroPredialActual;
    }

    public List<CatastroPredialAreas> getListaCatastroPredialAreasBloque() {
        return listaCatastroPredialAreasBloque;
    }

    public void setListaCatastroPredialAreasBloque(List<CatastroPredialAreas> listaCatastroPredialAreasBloque) {
        this.listaCatastroPredialAreasBloque = listaCatastroPredialAreasBloque;
    }

    public List<CatalogoDetalle> getListaInfServicios() {
        return listaInfServicios;
    }

    public void setListaInfServicios(List<CatalogoDetalle> listaInfServicios) {
        this.listaInfServicios = listaInfServicios;
    }

    public List<CatalogoDetalle> getListaInfAlcantarillado1() {
        return listaInfAlcantarillado1;
    }

    public void setListaInfAlcantarillado1(List<CatalogoDetalle> listaInfAlcantarillado1) {
        this.listaInfAlcantarillado1 = listaInfAlcantarillado1;
    }

    public List<CatalogoDetalle> getListaInfUso() {
        return listaInfUso;
    }

    public void setListaInfUso(List<CatalogoDetalle> listaInfUso) {
        this.listaInfUso = listaInfUso;
    }

    public List<CatalogoDetalle> getListaInfMaterial() {
        return listaInfMaterial;
    }

    public void setListaInfMaterial(List<CatalogoDetalle> listaInfMaterial) {
        this.listaInfMaterial = listaInfMaterial;
    }

    public List<CatalogoDetalle> getListaInfSentido() {
        return listaInfSentido;
    }

    public void setListaInfSentido(List<CatalogoDetalle> listaInfSentido) {
        this.listaInfSentido = listaInfSentido;
    }

    public List<CatalogoDetalle> getListaInfEnergiaElec() {
        return listaInfEnergiaElec;
    }

    public void setListaInfEnergiaElec(List<CatalogoDetalle> listaInfEnergiaElec) {
        this.listaInfEnergiaElec = listaInfEnergiaElec;
    }

    public List<CatalogoDetalle> getListaInfAbasAgua() {
        return listaInfAbasAgua;
    }

    public void setListaInfAbasAgua(List<CatalogoDetalle> listaInfAbasAgua) {
        this.listaInfAbasAgua = listaInfAbasAgua;
    }

    public List<CatalogoDetalle> getListaInfAlcantarillado2() {
        return listaInfAlcantarillado2;
    }

    public void setListaInfAlcantarillado2(List<CatalogoDetalle> listaInfAlcantarillado2) {
        this.listaInfAlcantarillado2 = listaInfAlcantarillado2;
    }

    public List<CatalogoDetalle> getListaInfOtrosServicios() {
        return listaInfOtrosServicios;
    }

    public void setListaInfOtrosServicios(List<CatalogoDetalle> listaInfOtrosServicios) {
        this.listaInfOtrosServicios = listaInfOtrosServicios;
    }

    public List<CatalogoDetalle> getListaInfServiciosSeleccionado() {
        return listaInfServiciosSeleccionado;
    }

    public void setListaInfServiciosSeleccionado(List<CatalogoDetalle> listaInfServiciosSeleccionado) {
        this.listaInfServiciosSeleccionado = listaInfServiciosSeleccionado;
    }

    public List<CatalogoDetalle> getListaInfAlcantarillado1Seleccionado() {
        return listaInfAlcantarillado1Seleccionado;
    }

    public void setListaInfAlcantarillado1Seleccionado(List<CatalogoDetalle> listaInfAlcantarillado1Seleccionado) {
        this.listaInfAlcantarillado1Seleccionado = listaInfAlcantarillado1Seleccionado;
    }

    public List<CatalogoDetalle> getListaInfUsoSeleccionado() {
        return listaInfUsoSeleccionado;
    }

    public void setListaInfUsoSeleccionado(List<CatalogoDetalle> listaInfUsoSeleccionado) {
        this.listaInfUsoSeleccionado = listaInfUsoSeleccionado;
    }

    public List<CatalogoDetalle> getListaInfMaterialSeleccionado() {
        return listaInfMaterialSeleccionado;
    }

    public void setListaInfMaterialSeleccionado(List<CatalogoDetalle> listaInfMaterialSeleccionado) {
        this.listaInfMaterialSeleccionado = listaInfMaterialSeleccionado;
    }

    public List<CatalogoDetalle> getListaInfSentidoSeleccionado() {
        return listaInfSentidoSeleccionado;
    }

    public void setListaInfSentidoSeleccionado(List<CatalogoDetalle> listaInfSentidoSeleccionado) {
        this.listaInfSentidoSeleccionado = listaInfSentidoSeleccionado;
    }

    public List<CatalogoDetalle> getListaInfEnergiaElecSeleccionado() {
        return listaInfEnergiaElecSeleccionado;
    }

    public void setListaInfEnergiaElecSeleccionado(List<CatalogoDetalle> listaInfEnergiaElecSeleccionado) {
        this.listaInfEnergiaElecSeleccionado = listaInfEnergiaElecSeleccionado;
    }

    public List<CatalogoDetalle> getListaInfAbasAguaSeleccionado() {
        return listaInfAbasAguaSeleccionado;
    }

    public void setListaInfAbasAguaSeleccionado(List<CatalogoDetalle> listaInfAbasAguaSeleccionado) {
        this.listaInfAbasAguaSeleccionado = listaInfAbasAguaSeleccionado;
    }

    public List<CatalogoDetalle> getListaInfAlcantarillado2Seleccionado() {
        return listaInfAlcantarillado2Seleccionado;
    }

    public void setListaInfAlcantarillado2Seleccionado(List<CatalogoDetalle> listaInfAlcantarillado2Seleccionado) {
        this.listaInfAlcantarillado2Seleccionado = listaInfAlcantarillado2Seleccionado;
    }

    public List<CatalogoDetalle> getListaInfOtrosServiciosSeleccionado() {
        return listaInfOtrosServiciosSeleccionado;
    }

    public void setListaInfOtrosServiciosSeleccionado(List<CatalogoDetalle> listaInfOtrosServiciosSeleccionado) {
        this.listaInfOtrosServiciosSeleccionado = listaInfOtrosServiciosSeleccionado;
    }

    public List<CatastroPredialUsosuelo> getListaUsuSuelo() {
        return listaUsuSuelo;
    }

    public void setListaUsuSuelo(List<CatastroPredialUsosuelo> listaUsuSuelo) {
        this.listaUsuSuelo = listaUsuSuelo;
    }

    public List<CatastroPredialEdificacion> getListaEdifGrupo1_1() {
        return listaEdifGrupo1_1;
    }

    public void setListaEdifGrupo1_1(List<CatastroPredialEdificacion> listaEdifGrupo1_1) {
        this.listaEdifGrupo1_1 = listaEdifGrupo1_1;
    }

    public List<CatastroPredialEdificacion> getListaEdifGrupo1_2() {
        return listaEdifGrupo1_2;
    }

    public void setListaEdifGrupo1_2(List<CatastroPredialEdificacion> listaEdifGrupo1_2) {
        this.listaEdifGrupo1_2 = listaEdifGrupo1_2;
    }

    public List<CatastroPredialEdificacion> getListaEdifGrupo1_3() {
        return listaEdifGrupo1_3;
    }

    public void setListaEdifGrupo1_3(List<CatastroPredialEdificacion> listaEdifGrupo1_3) {
        this.listaEdifGrupo1_3 = listaEdifGrupo1_3;
    }

    public List<CatastroPredialEdificacion> getListaEdifGrupo1_4() {
        return listaEdifGrupo1_4;
    }

    public void setListaEdifGrupo1_4(List<CatastroPredialEdificacion> listaEdifGrupo1_4) {
        this.listaEdifGrupo1_4 = listaEdifGrupo1_4;
    }

    public List<CatastroPredialEdificacion> getListaEdifGrupo234() {
        return listaEdifGrupo234;
    }

    public void setListaEdifGrupo234(List<CatastroPredialEdificacion> listaEdifGrupo234) {
        this.listaEdifGrupo234 = listaEdifGrupo234;
    }

    public List<CatastroPredialEdificacion> getListaEdifGrupo5_14() {
        return listaEdifGrupo5_14;
    }

    public void setListaEdifGrupo5_14(List<CatastroPredialEdificacion> listaEdifGrupo5_14) {
        this.listaEdifGrupo5_14 = listaEdifGrupo5_14;
    }

    public List<CatastroPredialEdificacion> getListaEdifGrupo5_5() {
        return listaEdifGrupo5_5;
    }

    public void setListaEdifGrupo5_5(List<CatastroPredialEdificacion> listaEdifGrupo5_5) {
        this.listaEdifGrupo5_5 = listaEdifGrupo5_5;
    }

    public List<CatastroPredialEdificacion> getListaEdifGrupo5_6() {
        return listaEdifGrupo5_6;
    }

    public void setListaEdifGrupo5_6(List<CatastroPredialEdificacion> listaEdifGrupo5_6) {
        this.listaEdifGrupo5_6 = listaEdifGrupo5_6;
    }

    public List<CatastroPredialEdificacion> getListaEdifGrupo5_7() {
        return listaEdifGrupo5_7;
    }

    public void setListaEdifGrupo5_7(List<CatastroPredialEdificacion> listaEdifGrupo5_7) {
        this.listaEdifGrupo5_7 = listaEdifGrupo5_7;
    }

    public List<CatastroPredialEdificacion> getListaEdifGrupo5_8() {
        return listaEdifGrupo5_8;
    }

    public void setListaEdifGrupo5_8(List<CatastroPredialEdificacion> listaEdifGrupo5_8) {
        this.listaEdifGrupo5_8 = listaEdifGrupo5_8;
    }

    public List<CatastroPredialEdificacion> getListaEdifGrupo5_9() {
        return listaEdifGrupo5_9;
    }

    public void setListaEdifGrupo5_9(List<CatastroPredialEdificacion> listaEdifGrupo5_9) {
        this.listaEdifGrupo5_9 = listaEdifGrupo5_9;
    }

    public List<CatalogoDetalle> getListaTipoVia() {
        return listaTipoVia;
    }

    public void setListaTipoVia(List<CatalogoDetalle> listaTipoVia) {
        this.listaTipoVia = listaTipoVia;
    }

    public List<CatalogoDetalle> getListaTipoUbicacion() {
        return listaTipoUbicacion;
    }

    public void setListaTipoUbicacion(List<CatalogoDetalle> listaTipoUbicacion) {
        this.listaTipoUbicacion = listaTipoUbicacion;
    }

    public List<CatalogoDetalle> getListaTipoProp1() {
        return listaTipoProp1;
    }

    public void setListaTipoProp1(List<CatalogoDetalle> listaTipoProp1) {
        this.listaTipoProp1 = listaTipoProp1;
    }

    public List<CatalogoDetalle> getListaTipoProp2() {
        return listaTipoProp2;
    }

    public void setListaTipoProp2(List<CatalogoDetalle> listaTipoProp2) {
        this.listaTipoProp2 = listaTipoProp2;
    }

    public List<CatalogoDetalle> getListaRefCartograficas() {
        return listaRefCartograficas;
    }

    public void setListaRefCartograficas(List<CatalogoDetalle> listaRefCartograficas) {
        this.listaRefCartograficas = listaRefCartograficas;
    }

    public List<CatalogoDetalle> getListaTenenciaDominio() {
        return listaTenenciaDominio;
    }

    public void setListaTenenciaDominio(List<CatalogoDetalle> listaTenenciaDominio) {
        this.listaTenenciaDominio = listaTenenciaDominio;
    }

    public List<CatalogoDetalle> getListaTenenciaTraslacionDominio() {
        return listaTenenciaTraslacionDominio;
    }

    public void setListaTenenciaTraslacionDominio(List<CatalogoDetalle> listaTenenciaTraslacionDominio) {
        this.listaTenenciaTraslacionDominio = listaTenenciaTraslacionDominio;
    }

    public List<CatalogoDetalle> getListaTerrenoOcupacion() {
        return listaTerrenoOcupacion;
    }

    public void setListaTerrenoOcupacion(List<CatalogoDetalle> listaTerrenoOcupacion) {
        this.listaTerrenoOcupacion = listaTerrenoOcupacion;
    }

    public List<CatalogoDetalle> getListaTerrenoNoEdificado() {
        return listaTerrenoNoEdificado;
    }

    public void setListaTerrenoNoEdificado(List<CatalogoDetalle> listaTerrenoNoEdificado) {
        this.listaTerrenoNoEdificado = listaTerrenoNoEdificado;
    }

    public List<CatalogoDetalle> getListaTerrenoConstruccion() {
        return listaTerrenoConstruccion;
    }

    public void setListaTerrenoConstruccion(List<CatalogoDetalle> listaTerrenoConstruccion) {
        this.listaTerrenoConstruccion = listaTerrenoConstruccion;
    }

    public List<CatalogoDetalle> getListaTerrenoCaracteristicasSuelo() {
        return listaTerrenoCaracteristicasSuelo;
    }

    public void setListaTerrenoCaracteristicasSuelo(List<CatalogoDetalle> listaTerrenoCaracteristicasSuelo) {
        this.listaTerrenoCaracteristicasSuelo = listaTerrenoCaracteristicasSuelo;
    }

    public List<CatalogoDetalle> getListaTerrenoForma() {
        return listaTerrenoForma;
    }

    public void setListaTerrenoForma(List<CatalogoDetalle> listaTerrenoForma) {
        this.listaTerrenoForma = listaTerrenoForma;
    }

    public List<CatalogoDetalle> getListaTerrenoTopografia() {
        return listaTerrenoTopografia;
    }

    public void setListaTerrenoTopografia(List<CatalogoDetalle> listaTerrenoTopografia) {
        this.listaTerrenoTopografia = listaTerrenoTopografia;
    }

    public List<CatalogoDetalle> getListaTerrenoLocalizacion() {
        return listaTerrenoLocalizacion;
    }

    public void setListaTerrenoLocalizacion(List<CatalogoDetalle> listaTerrenoLocalizacion) {
        this.listaTerrenoLocalizacion = listaTerrenoLocalizacion;
    }

    public List<CatalogoDetalle> getListaUsoSueloTipoNegocio() {
        return listaUsoSueloTipoNegocio;
    }

    public void setListaUsoSueloTipoNegocio(List<CatalogoDetalle> listaUsoSueloTipoNegocio) {
        this.listaUsoSueloTipoNegocio = listaUsoSueloTipoNegocio;
    }

    public List<CatalogoDetalle> getListaUsoSueloTiempoFuncionamiento() {
        return listaUsoSueloTiempoFuncionamiento;
    }

    public void setListaUsoSueloTiempoFuncionamiento(List<CatalogoDetalle> listaUsoSueloTiempoFuncionamiento) {
        this.listaUsoSueloTiempoFuncionamiento = listaUsoSueloTiempoFuncionamiento;
    }

    public List<CatalogoDetalle> getListaOtraInfoDimensiones() {
        return listaOtraInfoDimensiones;
    }

    public void setListaOtraInfoDimensiones(List<CatalogoDetalle> listaOtraInfoDimensiones) {
        this.listaOtraInfoDimensiones = listaOtraInfoDimensiones;
    }

    public List<CatalogoDetalle> getListaOtraInfoAlicuota() {
        return listaOtraInfoAlicuota;
    }

    public void setListaOtraInfoAlicuota(List<CatalogoDetalle> listaOtraInfoAlicuota) {
        this.listaOtraInfoAlicuota = listaOtraInfoAlicuota;
    }

    public List<CatalogoDetalle> getListaOtraInfoFuenteInfo() {
        return listaOtraInfoFuenteInfo;
    }

    public void setListaOtraInfoFuenteInfo(List<CatalogoDetalle> listaOtraInfoFuenteInfo) {
        this.listaOtraInfoFuenteInfo = listaOtraInfoFuenteInfo;
    }

    public List<CatalogoDetalle> getListaTipoDocRelevamiento() {
        return listaTipoDocRelevamiento;
    }

    public void setListaTipoDocRelevamiento(List<CatalogoDetalle> listaTipoDocRelevamiento) {
        this.listaTipoDocRelevamiento = listaTipoDocRelevamiento;
    }

    public List<CatalogoDetalle> getListaOpcEdifGrupo1_1() {
        return listaOpcEdifGrupo1_1;
    }

    public void setListaOpcEdifGrupo1_1(List<CatalogoDetalle> listaOpcEdifGrupo1_1) {
        this.listaOpcEdifGrupo1_1 = listaOpcEdifGrupo1_1;
    }

    public List<CatalogoDetalle> getListaOpcEdifGrupo1_3() {
        return listaOpcEdifGrupo1_3;
    }

    public void setListaOpcEdifGrupo1_3(List<CatalogoDetalle> listaOpcEdifGrupo1_3) {
        this.listaOpcEdifGrupo1_3 = listaOpcEdifGrupo1_3;
    }

    public List<CatalogoDetalle> getListaOpcEdifGrupo5_5() {
        return listaOpcEdifGrupo5_5;
    }

    public void setListaOpcEdifGrupo5_5(List<CatalogoDetalle> listaOpcEdifGrupo5_5) {
        this.listaOpcEdifGrupo5_5 = listaOpcEdifGrupo5_5;
    }

    public List<CatalogoDetalle> getListaOpcEdifGrupo5_6() {
        return listaOpcEdifGrupo5_6;
    }

    public void setListaOpcEdifGrupo5_6(List<CatalogoDetalle> listaOpcEdifGrupo5_6) {
        this.listaOpcEdifGrupo5_6 = listaOpcEdifGrupo5_6;
    }

    public List<CatalogoDetalle> getListaOpcEdifGrupo5_7() {
        return listaOpcEdifGrupo5_7;
    }

    public void setListaOpcEdifGrupo5_7(List<CatalogoDetalle> listaOpcEdifGrupo5_7) {
        this.listaOpcEdifGrupo5_7 = listaOpcEdifGrupo5_7;
    }

    public List<CatalogoDetalle> getListaOpcEdifGrupo5_8() {
        return listaOpcEdifGrupo5_8;
    }

    public void setListaOpcEdifGrupo5_8(List<CatalogoDetalle> listaOpcEdifGrupo5_8) {
        this.listaOpcEdifGrupo5_8 = listaOpcEdifGrupo5_8;
    }

    public List<CatalogoDetalle> getListaOpcEdifGrupo5_9() {
        return listaOpcEdifGrupo5_9;
    }

    public void setListaOpcEdifGrupo5_9(List<CatalogoDetalle> listaOpcEdifGrupo5_9) {
        this.listaOpcEdifGrupo5_9 = listaOpcEdifGrupo5_9;
    }

    public Propietario getPropietarioActual() {
        return propietarioActual;
    }

    public void setPropietarioActual(Propietario propietarioActual) {
        this.propietarioActual = propietarioActual;
    }

    public List<PredioArchivo> getListaPredioArchivo() {
        return listaPredioArchivo;
    }

    public void setListaPredioArchivo(List<PredioArchivo> listaPredioArchivo) {
        this.listaPredioArchivo = listaPredioArchivo;
    }

    public PredioArchivo getPredioArchivoActual() {
        return predioArchivoActual;
    }

    public void setPredioArchivoActual(PredioArchivo predioArchivoActual) {
        this.predioArchivoActual = predioArchivoActual;
    }

    public List<CatalogoDetalle> getListaParroquias() {
        return listaParroquias;
    }

    public void setListaParroquias(List<CatalogoDetalle> listaParroquias) {
        this.listaParroquias = listaParroquias;
    }

    public List<CatalogoDetalle> getListaSectores() {
        return listaSectores;
    }

    public void setListaSectores(List<CatalogoDetalle> listaSectores) {
        this.listaSectores = listaSectores;
    }

    public CatastroPredialValoracion getValoracionActual() {
        return valoracionActual;
    }

    public void setValoracionActual(CatastroPredialValoracion valoracionActual) {
        this.valoracionActual = valoracionActual;
    }

    public List<CatalogoDetalle> getListaOpcEdifGrupo2_1() {
        return listaOpcEdifGrupo2_1;
    }

    public void setListaOpcEdifGrupo2_1(List<CatalogoDetalle> listaOpcEdifGrupo2_1) {
        this.listaOpcEdifGrupo2_1 = listaOpcEdifGrupo2_1;
    }

    public List<CatalogoDetalle> getListaOpcEdifGrupo2_2() {
        return listaOpcEdifGrupo2_2;
    }

    public void setListaOpcEdifGrupo2_2(List<CatalogoDetalle> listaOpcEdifGrupo2_2) {
        this.listaOpcEdifGrupo2_2 = listaOpcEdifGrupo2_2;
    }

    public List<CatalogoDetalle> getListaOpcEdifGrupo2_3() {
        return listaOpcEdifGrupo2_3;
    }

    public void setListaOpcEdifGrupo2_3(List<CatalogoDetalle> listaOpcEdifGrupo2_3) {
        this.listaOpcEdifGrupo2_3 = listaOpcEdifGrupo2_3;
    }

    public List<CatalogoDetalle> getListaOpcEdifGrupo2_4() {
        return listaOpcEdifGrupo2_4;
    }

    public void setListaOpcEdifGrupo2_4(List<CatalogoDetalle> listaOpcEdifGrupo2_4) {
        this.listaOpcEdifGrupo2_4 = listaOpcEdifGrupo2_4;
    }

    public List<CatalogoDetalle> getListaOpcEdifGrupo2_5() {
        return listaOpcEdifGrupo2_5;
    }

    public void setListaOpcEdifGrupo2_5(List<CatalogoDetalle> listaOpcEdifGrupo2_5) {
        this.listaOpcEdifGrupo2_5 = listaOpcEdifGrupo2_5;
    }

    public List<CatalogoDetalle> getListaOpcEdifGrupo2_6() {
        return listaOpcEdifGrupo2_6;
    }

    public void setListaOpcEdifGrupo2_6(List<CatalogoDetalle> listaOpcEdifGrupo2_6) {
        this.listaOpcEdifGrupo2_6 = listaOpcEdifGrupo2_6;
    }

    public List<CatalogoDetalle> getListaOpcEdifGrupo3_1() {
        return listaOpcEdifGrupo3_1;
    }

    public void setListaOpcEdifGrupo3_1(List<CatalogoDetalle> listaOpcEdifGrupo3_1) {
        this.listaOpcEdifGrupo3_1 = listaOpcEdifGrupo3_1;
    }

    public List<CatalogoDetalle> getListaOpcEdifGrupo3_2() {
        return listaOpcEdifGrupo3_2;
    }

    public void setListaOpcEdifGrupo3_2(List<CatalogoDetalle> listaOpcEdifGrupo3_2) {
        this.listaOpcEdifGrupo3_2 = listaOpcEdifGrupo3_2;
    }

    public List<CatalogoDetalle> getListaOpcEdifGrupo3_3() {
        return listaOpcEdifGrupo3_3;
    }

    public void setListaOpcEdifGrupo3_3(List<CatalogoDetalle> listaOpcEdifGrupo3_3) {
        this.listaOpcEdifGrupo3_3 = listaOpcEdifGrupo3_3;
    }

    public List<CatalogoDetalle> getListaOpcEdifGrupo3_4() {
        return listaOpcEdifGrupo3_4;
    }

    public void setListaOpcEdifGrupo3_4(List<CatalogoDetalle> listaOpcEdifGrupo3_4) {
        this.listaOpcEdifGrupo3_4 = listaOpcEdifGrupo3_4;
    }

    public List<CatalogoDetalle> getListaOpcEdifGrupo3_5() {
        return listaOpcEdifGrupo3_5;
    }

    public void setListaOpcEdifGrupo3_5(List<CatalogoDetalle> listaOpcEdifGrupo3_5) {
        this.listaOpcEdifGrupo3_5 = listaOpcEdifGrupo3_5;
    }

    public List<CatalogoDetalle> getListaOpcEdifGrupo3_6() {
        return listaOpcEdifGrupo3_6;
    }

    public void setListaOpcEdifGrupo3_6(List<CatalogoDetalle> listaOpcEdifGrupo3_6) {
        this.listaOpcEdifGrupo3_6 = listaOpcEdifGrupo3_6;
    }

    public List<CatalogoDetalle> getListaOpcEdifGrupo3_7() {
        return listaOpcEdifGrupo3_7;
    }

    public void setListaOpcEdifGrupo3_7(List<CatalogoDetalle> listaOpcEdifGrupo3_7) {
        this.listaOpcEdifGrupo3_7 = listaOpcEdifGrupo3_7;
    }

    public List<CatalogoDetalle> getListaOpcEdifGrupo3_8() {
        return listaOpcEdifGrupo3_8;
    }

    public void setListaOpcEdifGrupo3_8(List<CatalogoDetalle> listaOpcEdifGrupo3_8) {
        this.listaOpcEdifGrupo3_8 = listaOpcEdifGrupo3_8;
    }

    public List<CatalogoDetalle> getListaOpcEdifGrupo3_9() {
        return listaOpcEdifGrupo3_9;
    }

    public void setListaOpcEdifGrupo3_9(List<CatalogoDetalle> listaOpcEdifGrupo3_9) {
        this.listaOpcEdifGrupo3_9 = listaOpcEdifGrupo3_9;
    }

    public List<CatalogoDetalle> getListaOpcEdifGrupo3_10() {
        return listaOpcEdifGrupo3_10;
    }

    public void setListaOpcEdifGrupo3_10(List<CatalogoDetalle> listaOpcEdifGrupo3_10) {
        this.listaOpcEdifGrupo3_10 = listaOpcEdifGrupo3_10;
    }

    public List<CatalogoDetalle> getListaOpcEdifGrupo4_1() {
        return listaOpcEdifGrupo4_1;
    }

    public void setListaOpcEdifGrupo4_1(List<CatalogoDetalle> listaOpcEdifGrupo4_1) {
        this.listaOpcEdifGrupo4_1 = listaOpcEdifGrupo4_1;
    }

    public List<CatalogoDetalle> getListaOpcEdifGrupo4_2() {
        return listaOpcEdifGrupo4_2;
    }

    public void setListaOpcEdifGrupo4_2(List<CatalogoDetalle> listaOpcEdifGrupo4_2) {
        this.listaOpcEdifGrupo4_2 = listaOpcEdifGrupo4_2;
    }

    public List<CatalogoDetalle> getListaOpcEdifGrupo4_3() {
        return listaOpcEdifGrupo4_3;
    }

    public void setListaOpcEdifGrupo4_3(List<CatalogoDetalle> listaOpcEdifGrupo4_3) {
        this.listaOpcEdifGrupo4_3 = listaOpcEdifGrupo4_3;
    }

    public String getCedulaPropietarioBusqueda() {
        return cedulaPropietarioBusqueda;
    }

    public void setCedulaPropietarioBusqueda(String cedulaPropietarioBusqueda) {
        this.cedulaPropietarioBusqueda = cedulaPropietarioBusqueda;
    }

    public List<CatastroPredial> getListaCatastrosDePropietario() {
        return listaCatastrosDePropietario;
    }

    public void setListaCatastrosDePropietario(List<CatastroPredial> listaCatastrosDePropietario) {
        this.listaCatastrosDePropietario = listaCatastrosDePropietario;
    }

    public String getGrupoUsoSuelo() {
        return grupoUsoSuelo;
    }

    public void setGrupoUsoSuelo(String grupoUsoSuelo) {
        this.grupoUsoSuelo = grupoUsoSuelo;
    }

    public String getSubgrupoUsoSuelo() {
        return subgrupoUsoSuelo;
    }

    public void setSubgrupoUsoSuelo(String subgrupoUsoSuelo) {
        this.subgrupoUsoSuelo = subgrupoUsoSuelo;
    }

    public List<SelectItem> getListaGruposUsoSuelo() {
        return listaGruposUsoSuelo;
    }

    public void setListaGruposUsoSuelo(List<SelectItem> listaGruposUsoSuelo) {
        this.listaGruposUsoSuelo = listaGruposUsoSuelo;
    }

    public List<SelectItem> getListaSubgruposUsoSuelo() {
        return listaSubgruposUsoSuelo;
    }

    public void setListaSubgruposUsoSuelo(List<SelectItem> listaSubgruposUsoSuelo) {
        this.listaSubgruposUsoSuelo = listaSubgruposUsoSuelo;
    }

    public String getEdifBloque() {
        return edifBloque;
    }

    public void setEdifBloque(String edifBloque) {
        this.edifBloque = edifBloque;
    }

    public String getEdifPiso() {
        return edifPiso;
    }

    public void setEdifPiso(String edifPiso) {
        this.edifPiso = edifPiso;
    }

    public boolean isFlagNuevo() {
        return flagNuevo;
    }

    public void setFlagNuevo(boolean flagNuevo) {
        this.flagNuevo = flagNuevo;
    }

    public String getClaveCatastralBusqueda() {
        return claveCatastralBusqueda;
    }

    public void setClaveCatastralBusqueda(String claveCatastralBusqueda) {
        this.claveCatastralBusqueda = claveCatastralBusqueda;
    }

    public String getTipoInfAnt() {
        return tipoInfAnt;
    }

    public void setTipoInfAnt(String tipoInfAnt) {
        this.tipoInfAnt = tipoInfAnt;
    }

    public String getValorInfAnt() {
        return valorInfAnt;
    }

    public void setValorInfAnt(String valorInfAnt) {
        this.valorInfAnt = valorInfAnt;
    }

    public List<CatastroPredialInfAnt> getListaInformacionAnterior() {
        return listaInformacionAnterior;
    }

    public void setListaInformacionAnterior(List<CatastroPredialInfAnt> listaInformacionAnterior) {
        this.listaInformacionAnterior = listaInformacionAnterior;
    }

    public CatastroPredialAreas getCatastroAreaActual() {
        return catastroAreaActual;
    }

    public void setCatastroAreaActual(CatastroPredialAreas catastroAreaActual) {
        this.catastroAreaActual = catastroAreaActual;
    }

    public boolean isFlagMedidoresAgua() {
        return flagMedidoresAgua;
    }

    public void setFlagMedidoresAgua(boolean flagMedidoresAgua) {
        this.flagMedidoresAgua = flagMedidoresAgua;
    }

    public boolean isFlagMedidoresLuz() {
        return flagMedidoresLuz;
    }

    public void setFlagMedidoresLuz(boolean flagMedidoresLuz) {
        this.flagMedidoresLuz = flagMedidoresLuz;
    }

    public List<PredioArchivo> getListaPredioFoto() {
        return listaPredioFoto;
    }

    public void setListaPredioFoto(List<PredioArchivo> listaPredioFoto) {
        this.listaPredioFoto = listaPredioFoto;
    }

    public PropietarioPredio getPropietarioPredioBusqueda() {
        return propietarioPredioBusqueda;
    }

    public void setPropietarioPredioBusqueda(PropietarioPredio propietarioPredioBusqueda) {
        this.propietarioPredioBusqueda = propietarioPredioBusqueda;
    }

    public StreamedContent getFotoActual() {
        return fotoActual;
    }

    public void setFotoActual(StreamedContent fotoActual) {
        this.fotoActual = fotoActual;
    }

}
