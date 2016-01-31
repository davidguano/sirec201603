/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sirec.web.impuestos;

import ec.sirec.ejb.entidades.CatalogoDetalle;
import ec.sirec.ejb.entidades.CatastroPredial;
import ec.sirec.ejb.entidades.Cementerio;
import ec.sirec.ejb.entidades.CementerioArchivo;
import ec.sirec.ejb.entidades.DatoGlobal;
import ec.sirec.ejb.entidades.Patente;
import ec.sirec.ejb.entidades.PatenteArchivo;
import ec.sirec.ejb.entidades.PredioArchivo;
import ec.sirec.ejb.entidades.Propietario;
import ec.sirec.ejb.entidades.SegUsuario;
import ec.sirec.ejb.servicios.CatalogoDetalleServicio;
import ec.sirec.ejb.servicios.CatastroPredialServicio;
import ec.sirec.ejb.servicios.CementerioServicio;
import ec.sirec.ejb.servicios.PatenteArchivoServicio;
import ec.sirec.ejb.servicios.PatenteServicio;
import ec.sirec.ejb.servicios.PropietarioServicio;
import ec.sirec.web.base.BaseControlador;
import ec.sirec.web.util.ParametrosFile;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.TabChangeEvent;

/**
 *
 * @author Darwin Aldas
 */
@ManagedBean
@ViewScoped
public class GestionCementeriosControlador extends BaseControlador {

    @EJB
    private PropietarioServicio propietarioServicio;

    @EJB
    private CementerioServicio cementerioServicio;

    @EJB
    private PatenteArchivoServicio patenteArchivoServicio;

    @EJB
    private CatalogoDetalleServicio catalogoDetalleServicio;

    @EJB
    private PatenteServicio patenteServicio;

    private Cementerio cementerioActual;
    private Propietario propietarioActual;
    private CatalogoDetalle catDetGenero;
    private CatalogoDetalle catDetUbicAtaud;
    private CatalogoDetalle catDetEstadoDefun;
    private CatalogoDetalle catDetTipNicho;
    private CatalogoDetalle catDetParroquia;
    private boolean habilitaEditar;
    private Date fechaFallece;
    private Date fehcaFinContrato;

    private DatoGlobal datoGlobalActual;
    private SegUsuario usuarioActual;
    private List<CatalogoDetalle> listGenero;
    private List<CatalogoDetalle> listaUbicAtaud;
    private List<CatalogoDetalle> listEstadoDefun;
    private List<CatalogoDetalle> listTipoNicho;
    private List<ParametrosFile> listaFiles;
    private List<PatenteArchivo> listadoArchivos;
    private List<CatalogoDetalle> listaHorarioFunciona;
    private List<CatalogoDetalle> listaCatDetParroquias;
    private String ciRuc;

    /**
     * Creates a new instance of GestionPatenteControlador
     */
    private String numPatente;
    private static final Logger LOGGER = Logger.getLogger(GestionCementeriosControlador.class.getName());

    @PostConstruct
    public void inicializar() {
        try {
            cementerioActual = new Cementerio();
            propietarioActual = new Propietario();
            numPatente = generaNumPatente();
            catDetGenero = new CatalogoDetalle();
            catDetUbicAtaud = new CatalogoDetalle();
            catDetEstadoDefun = new CatalogoDetalle();
            catDetTipNicho = new CatalogoDetalle();
            catDetParroquia = new CatalogoDetalle();
            propietarioActual = new Propietario();
            habilitaEditar = false;
            listaFiles = new ArrayList<ParametrosFile>();
            ciRuc = "";
            listarGeneroSexo();
            listarUbicacionAtaud();
            listarTipoNicho();
            listarEstadoDefuncion();
            listarHorarioFuncionamiento();
            listarParroquias();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public GestionCementeriosControlador() {
    }

    public void cargaObjetosBitacora() {
        try {
            datoGlobalActual = new DatoGlobal();
            usuarioActual = new SegUsuario();
            datoGlobalActual = patenteServicio.cargarObjDatGloPorNombre("Msj_cementerio_In");
            usuarioActual = (SegUsuario) this.getSession().getAttribute("usuario");
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public void limpiarObjetosBitacora() {
        datoGlobalActual = new DatoGlobal();
        usuarioActual = new SegUsuario();
    }

    public void guardarCementerios() {
        try {
            if (habilitaEditar == false) {
                if (existeRuc()) {
                    cargaObjetosBitacora();
                    CatalogoDetalle objCatDet = new CatalogoDetalle();
                    cementerioActual.setProCi(propietarioActual);
                    cementerioActual.setCatdetParroquia(catDetParroquia);
                    objCatDet = catalogoDetalleServicio.buscarPorCodigoCatDet(catDetGenero.getCatdetCodigo());
                    cementerioActual.setCemGenero(objCatDet.getCatdetCod());
                    objCatDet = catalogoDetalleServicio.buscarPorCodigoCatDet(catDetUbicAtaud.getCatdetCodigo());
                    cementerioActual.setCemUbicacion(objCatDet.getCatdetCod());
                    objCatDet = catalogoDetalleServicio.buscarPorCodigoCatDet(catDetEstadoDefun.getCatdetCodigo());
                    cementerioActual.setCemEstado(objCatDet.getCatdetCod());
                    objCatDet = catalogoDetalleServicio.buscarPorCodigoCatDet(catDetTipNicho.getCatdetCodigo());
                    cementerioActual.setCemTipo(objCatDet.getCatdetCod());
                    cementerioActual.setCemFechaFallece(fechaFallece);
                    cementerioActual.setCemFechaFinContrato(fehcaFinContrato);
                    cementerioActual.setUsuIdentificacion(usuarioActual);
                    cementerioActual.setUltaccDetalle(datoGlobalActual.getDatgloValor());
                    cementerioActual.setUltaccMarcatiempo(java.util.Calendar.getInstance().getTime());
                    cementerioServicio.crearCementerio(cementerioActual);
                    if (!listaFiles.isEmpty()) {
                        guardarArchivos();
                    }
                    addSuccessMessage("Guardado Exitosamente");
                    cementerioActual = new Cementerio();
                    limpiarObjetosBitacora();
                    inicializar();

                } else {
                    addWarningMessage("Cédula/Ruc no existe en la base de datos");
                }

            } else {
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
    }

    public boolean existeRuc() {
        boolean existe = false;
        try {
            propietarioActual = propietarioServicio.buscarPropietario(ciRuc);
            if (propietarioActual != null) {
                existe = true;
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }

        return existe;
    }

    public void onTabChange(TabChangeEvent event) {
//        if (event.getTab().getId().equals("patDet")) {
//            GestionDetPatenteControlador objGesDetControlador = new GestionDetPatenteControlador();
//            objGesDetControlador.inicializar();
//        }
//        if (event.getTab().getId().equals("exoDedMulPat")) {
//            GestionExoDedMulPatenteControlador objGesExoDedMulPat = new GestionExoDedMulPatenteControlador();
//            objGesExoDedMulPat.inicializar();
//        }
    }

    public void guardarArchivos() {
        Iterator<ParametrosFile> itera = listaFiles.iterator();
        try {

            while (itera.hasNext()) {
                ParametrosFile elemento = itera.next();
                CementerioArchivo cemArchivo = new CementerioArchivo();
                cemArchivo.setCemCodigo(cementerioActual);
                cemArchivo.setCemarcNombre(elemento.getName());
                cemArchivo.setCemarcData(elemento.getData());
                cemArchivo.setCemarcTipo("CA"); //Archivo de Cementerios
                cemArchivo.setUsuIdentificacion(usuarioActual.getUsuIdentificacion());
                cemArchivo.setUltaccDetalle(datoGlobalActual.getDatgloValor());
                cemArchivo.setUltaccMarcaTiempo(java.util.Calendar.getInstance().getTime());
                patenteArchivoServicio.guardarCementerioArchivo(cemArchivo);
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public String generaNumPatente() { //Genera numero de patente aleatorio
        String numeroPatente = "";
        try {
            Patente objPatente = new Patente();
            objPatente = patenteServicio.cargarMaxObjPatente();
            int valorRetornado = objPatente.getPatCodigo() + 1;
            StringBuffer numSecuencial = new StringBuffer(valorRetornado + "");
            int valRequerido = 6;
            int valRetorno = numSecuencial.length();
            int valNecesita = valRequerido - valRetorno;
            StringBuffer sb = new StringBuffer(valNecesita);
            for (int i = 0; i < valNecesita; i++) {
                sb.append("0");
            }
            numeroPatente = "AE-MPM-" + sb.toString() + valorRetornado;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        return numeroPatente;
    }

    public void listarGeneroSexo() throws Exception {
        listGenero = catalogoDetalleServicio.listarPorNemonicoCatalogo("GENERO_SX");
    }

    public void listarUbicacionAtaud() throws Exception {
        listaUbicAtaud = catalogoDetalleServicio.listarPorNemonicoCatalogo("UBIC_ATAUD");
    }

    public void listarEstadoDefuncion() throws Exception {
        listEstadoDefun = catalogoDetalleServicio.listarPorNemonicoCatalogo("DEFUN_ESTADO");
    }

    public void listarParroquias() throws Exception {
        listaCatDetParroquias = catalogoDetalleServicio.listarPorNemonicoCatalogo("PARROQUIAS");
    }

    public void listarTipoNicho() throws Exception {
        listTipoNicho = catalogoDetalleServicio.listarPorNemonicoCatalogo("TIPO_NICHO");
    }

    public void listarHorarioFuncionamiento() throws Exception {
        listaHorarioFunciona = catalogoDetalleServicio.listarPorNemonicoCatalogo("HOR_FUNCIONA");
    }
//-----Carga de archivos

    public void handleFileUpload(FileUploadEvent event) throws Exception {
        try {
            InputStream is = event.getFile().getInputstream();
            ParametrosFile archivo = new ParametrosFile();
            archivo.setLength(event.getFile().getSize());
            archivo.setName(event.getFile().getFileName());
            archivo.setData(event.getFile().getContents());
            listaFiles.add(archivo);
            addSuccessMessage("Archivo Cargado", "");
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public void confirmaEliminarArchivo(ParametrosFile archivo) {
        try {
            listaFiles.remove(archivo);
            addSuccessMessage("Archivo Eliminado");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
            addWarningMessage("No se puede eliminar el regitro");
        }
    }

    public void confirmaEliminarPatArchivo(PatenteArchivo file) {
        try {
//            cementerioServicio.eliminarArchivo(file);
            addSuccessMessage("Registro Eliminado");
//            listadoArchivos = patenteArchivoServicio.listarArchivoPorPatente(cementerioActual);

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
            addWarningMessage("No se puede eliminar el regitro");
        }
    }

    public String getNumPatente() {
        return numPatente;
    }

    public void setNumPatente(String numPatente) {
        this.numPatente = numPatente;
    }

    public Propietario getPropietarioActual() {
        return propietarioActual;
    }

    public void setPropietarioActual(Propietario propietarioActual) {
        this.propietarioActual = propietarioActual;
    }

    public CatalogoDetalle getCatDetGenero() {
        return catDetGenero;
    }

    public void setCatDetGenero(CatalogoDetalle catDetGenero) {
        this.catDetGenero = catDetGenero;
    }

    public List<CatalogoDetalle> getListGenero() {
        return listGenero;
    }

    public void setListGenero(List<CatalogoDetalle> listGenero) {
        this.listGenero = listGenero;
    }

    public CatalogoDetalle getCatDetUbicAtaud() {
        return catDetUbicAtaud;
    }

    public void setCatDetUbicAtaud(CatalogoDetalle catDetUbicAtaud) {
        this.catDetUbicAtaud = catDetUbicAtaud;
    }

    public List<CatalogoDetalle> getListaUbicAtaud() {
        return listaUbicAtaud;
    }

    public void setListaUbicAtaud(List<CatalogoDetalle> listaUbicAtaud) {
        this.listaUbicAtaud = listaUbicAtaud;
    }

    public CatalogoDetalle getCatDetEstadoDefun() {
        return catDetEstadoDefun;
    }

    public void setCatDetEstadoDefun(CatalogoDetalle catDetEstadoDefun) {
        this.catDetEstadoDefun = catDetEstadoDefun;
    }

    public List<CatalogoDetalle> getListEstadoDefun() {
        return listEstadoDefun;
    }

    public void setListEstadoDefun(List<CatalogoDetalle> listEstadoDefun) {
        this.listEstadoDefun = listEstadoDefun;
    }

    public CatalogoDetalle getCatDetTipNicho() {
        return catDetTipNicho;
    }

    public void setCatDetTipNicho(CatalogoDetalle catDetTipNicho) {
        this.catDetTipNicho = catDetTipNicho;
    }

    public List<CatalogoDetalle> getListTipoNicho() {
        return listTipoNicho;
    }

    public void setListTipoNicho(List<CatalogoDetalle> listTipoNicho) {
        this.listTipoNicho = listTipoNicho;
    }

    public Cementerio getCementerioActual() {
        return cementerioActual;
    }

    public void setCementerioActual(Cementerio cementerioActual) {
        this.cementerioActual = cementerioActual;
    }

    public Date getFechaFallece() {
        return fechaFallece;
    }

    public void setFechaFallece(Date fechaFallece) {
        this.fechaFallece = fechaFallece;
    }

    public Date getFehcaFinContrato() {
        return fehcaFinContrato;
    }

    public void setFehcaFinContrato(Date fehcaFinContrato) {
        this.fehcaFinContrato = fehcaFinContrato;
    }

    public List<ParametrosFile> getListaFiles() {
        return listaFiles;
    }

    public void setListaFiles(List<ParametrosFile> listaFiles) {
        this.listaFiles = listaFiles;
    }

    public List<PatenteArchivo> getListadoArchivos() {
        return listadoArchivos;
    }

    public void setListadoArchivos(List<PatenteArchivo> listadoArchivos) {
        this.listadoArchivos = listadoArchivos;
    }

    public List<CatalogoDetalle> getListaHorarioFunciona() {
        return listaHorarioFunciona;
    }

    public void setListaHorarioFunciona(List<CatalogoDetalle> listaHorarioFunciona) {
        this.listaHorarioFunciona = listaHorarioFunciona;
    }

    public CatalogoDetalle getCatDetParroquia() {
        return catDetParroquia;
    }

    public void setCatDetParroquia(CatalogoDetalle catDetParroquia) {
        this.catDetParroquia = catDetParroquia;
    }

    public List<CatalogoDetalle> getListaCatDetParroquias() {
        return listaCatDetParroquias;
    }

    public void setListaCatDetParroquias(List<CatalogoDetalle> listaCatDetParroquias) {
        this.listaCatDetParroquias = listaCatDetParroquias;
    }

    public String getCiRuc() {
        return ciRuc;
    }

    public void setCiRuc(String ciRuc) {
        this.ciRuc = ciRuc;
    }

}
