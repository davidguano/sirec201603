/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sirec.web.impuestos;

import ec.sirec.ejb.entidades.CatalogoDetalle;
import ec.sirec.ejb.entidades.CatastroPredial;
import ec.sirec.ejb.entidades.DatoGlobal;
import ec.sirec.ejb.entidades.Patente;
import ec.sirec.ejb.entidades.PatenteArchivo;
import ec.sirec.ejb.entidades.PredioArchivo;
import ec.sirec.ejb.entidades.Propietario;
import ec.sirec.ejb.entidades.SegUsuario;
import ec.sirec.ejb.servicios.CatalogoDetalleServicio;
import ec.sirec.ejb.servicios.CatastroPredialServicio;
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
public class GestionPatenteControlador extends BaseControlador {

    @EJB
    private PatenteArchivoServicio patenteArchivoServicio;

    @EJB
    private PropietarioServicio propietarioServicio;

    @EJB
    private CatastroPredialServicio catastroPredialServicio;

    @EJB
    private CatalogoDetalleServicio catalogoDetalleServicio;

    @EJB
    private PatenteServicio patenteServicio;

    private Patente patenteActual;
    private Propietario propietarioActual;
    private CatalogoDetalle catDetEstablecimientoActual;
    private CatalogoDetalle catDetTipoEmpresActual;
    private CatalogoDetalle catDetTipoLocalActual;
    private CatalogoDetalle catDetTipActEcoActual;
    private CatalogoDetalle catDetEspTurisActual;
    private CatalogoDetalle catDetIdentEstadoActual;
    private CatastroPredial catastroPredialActual;
    private CatalogoDetalle catDetHorFuncionaActual;
    private CatalogoDetalle catDetParroquia;
    private boolean habilitaEditar;
    private boolean d1, d2, d3, d4, d5, d6, d7;
    private Date horarioDesde;
    private Date horarioHasta;
    private Date fecActividadEconomica;
    private boolean artesCalificado;
    private boolean llevaConta;
    private DatoGlobal datoGlobalActual;
    private SegUsuario usuarioActual;
    private List<CatalogoDetalle> listEstablecimiento;
    private List<CatalogoDetalle> listaTipoEmpresa;
    private List<CatalogoDetalle> lisTipoLocal;
    private List<CatalogoDetalle> listTipoActEconomica;
    private List<CatalogoDetalle> listEspTuris;
    private List<CatalogoDetalle> listaIdentEstado;
    private List<CatalogoDetalle> listaCatDetParroquias;
    private List<CatastroPredial> listaCatastroPredial;
    private List<ParametrosFile> listaFiles;
    private List<PatenteArchivo> listadoArchivos;
    private List<CatalogoDetalle> listaHorarioFunciona;
    private boolean habilitaCamposPropietario;
    private int verIngresaPlaca;
    private String catastroPredBusca;
    private int verGeneraContrasenia;
    private String contraseniaUsuario;
    /**
     * Creates a new instance of GestionPatenteControlador
     */
    private String numPatente;
    private static final Logger LOGGER = Logger.getLogger(GestionPatenteControlador.class.getName());

    @PostConstruct
    public void inicializar() {
        try {
            catastroPredBusca = null;
            patenteActual = new Patente();
            propietarioActual = new Propietario();
            numPatente = generaNumPatente();
            catDetEstablecimientoActual = new CatalogoDetalle();
            catDetTipoEmpresActual = new CatalogoDetalle();
            catDetTipoLocalActual = new CatalogoDetalle();
            catDetTipActEcoActual = new CatalogoDetalle();
            propietarioActual = new Propietario();
            catastroPredialActual = new CatastroPredial();
            catDetIdentEstadoActual = new CatalogoDetalle();
            catDetHorFuncionaActual = new CatalogoDetalle();
            catDetEspTurisActual = new CatalogoDetalle();
            habilitaEditar = false;
            d1 = false;
            d2 = false;
            d3 = false;
            d4 = false;
            d5 = false;
            patenteActual = new Patente();
            artesCalificado = false;
            llevaConta = false;
            listaFiles = new ArrayList<ParametrosFile>();
            habilitaCamposPropietario = true;
            listarTipoEstablecimiento();
            listarTipoEmpresa();
            listarTipoActividadEconomica();
            listarTipoLocal();
            listarEspTuristica();
            listarIdentificacionEstado();
            listarClaveCatastral();
            listarHorarioFuncionamiento();
            listarParroquias();
            verIngresaPlaca = 0;
            verGeneraContrasenia = 0;
            contraseniaUsuario = "";
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public GestionPatenteControlador() {
    }

    public void cargaObjetosBitacora() {
        try {
            datoGlobalActual = new DatoGlobal();
            usuarioActual = new SegUsuario();
            datoGlobalActual = patenteServicio.cargarObjDatGloPorNombre("Msj_Pat_In");
            usuarioActual = (SegUsuario) this.getSession().getAttribute("usuario");
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public void limpiarObjetosBitacora() {
        datoGlobalActual = new DatoGlobal();
        usuarioActual = new SegUsuario();
    }

    public void guardarPatente() {
        try {
            if (habilitaEditar == false) {
                CatalogoDetalle objCatDetAux = new CatalogoDetalle();
                objCatDetAux = catalogoDetalleServicio.buscarPorCodigoCatDet(catDetIdentEstadoActual.getCatdetCodigo());
                patenteActual.setPatEstado(objCatDetAux.getCatdetCod());
                patenteActual.setCatpreCodigo(catastroPredialActual);
                patenteActual.setPatFuncLunes(d1);
                patenteActual.setPatFuncMartes(d2);
                patenteActual.setPatFuncMiercoles(d3);
                patenteActual.setPatFuncJueves(d4);
                patenteActual.setPatFuncViernes(d5);
                patenteActual.setPatFuncSabado(d6);
                patenteActual.setPatFuncDomingo(d7);
                patenteActual.setCatdetTipoEst(catDetEstablecimientoActual);
                patenteActual.setCatdetTipoEmpresa(catDetTipoEmpresActual);
                patenteActual.setCatdetTipoLocal(catDetTipoLocalActual);
                patenteActual.setCatdetTipoActEco(catDetTipActEcoActual);
                patenteActual.setCatdetEspecialidad(catDetEspTurisActual);
                patenteActual.setCatdetHorarioFunc(catDetHorFuncionaActual);
                patenteActual.setPatArtesanoCalificado(artesCalificado);
                patenteActual.setPatObligadoCont(llevaConta);
                patenteActual.setPatHorarioDesde(horarioDesde.getHours() + ":" + horarioDesde.getMinutes());
                patenteActual.setPatHorarioHasta(horarioHasta.getHours() + ":" + horarioHasta.getMinutes());
                System.out.println("Horario desde: " + horarioDesde);
                patenteActual.setPatInicioActEco(fecActividadEconomica);
                cargaObjetosBitacora();
                patenteActual.setUsuIdentificacion(usuarioActual);
                patenteActual.setUltaccDetalle(datoGlobalActual.getDatgloDescripcion());
                patenteActual.setUltaccMarcatiempo(java.util.Calendar.getInstance().getTime());
                patenteServicio.crearPatente(patenteActual);
                guardarArchivos();
                getSession().setAttribute("patente", patenteActual);
                addSuccessMessage("Guardado Exitosamente", "Patente Guardado");
                patenteActual = new Patente();
                limpiarObjetosBitacora();
                objCatDetAux = null;
                inicializar();
            } else {
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
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
                PatenteArchivo patArchivo = new PatenteArchivo();
                patArchivo.setPatCodigo(patenteActual);
                patArchivo.setPatarcNombre(elemento.getName());
                patArchivo.setPatarcData(elemento.getData());
                patArchivo.setPatarcTipo("PA"); //Archivo de Patentes
                patArchivo.setUsuIdentificacion(usuarioActual);
                patArchivo.setUltaccDetalle(datoGlobalActual.getDatgloDescripcion());
                patArchivo.setUltaccMarcatiempo(java.util.Calendar.getInstance().getTime());
                patenteArchivoServicio.guardarArchivo(patArchivo);
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

    public void activaIngresarPlaca() {
        if (catDetTipActEcoActual.getCatdetCodigo() == 464) {
            verIngresaPlaca = 1;
        } else {
            verIngresaPlaca = 0;
        }
    }

    public void activaGeneraContrasenia() {
        if (catDetIdentEstadoActual.getCatdetCodigo() == 447) {
            verGeneraContrasenia = 1;
        } else {
            verGeneraContrasenia = 0;
        }
    }

    public void generaContrasenia() {
        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());
        int al6 = 0x3b9aca00 + rnd.nextInt(0xdbba0);
        String aleatorio = (new StringBuilder()).append(al6).append("").toString();
        String combinacion = (new StringBuilder()).append("USER").append(aleatorio).toString();
        String identificacion = combinacion.substring(0, 13);
        contraseniaUsuario = identificacion;
    }

    public void cargarInformacionPropietario() throws Exception {
        try {
            StringBuffer catastroPredial = new StringBuffer(catastroPredBusca);
            if (catastroPredial.length() >= 19) {
                String codNac = catastroPredBusca.substring(0, 7);
                String codLoc = catastroPredBusca.substring(7, 19);
                if (codNac != null && codLoc != null) {
                    catastroPredialActual = catastroPredialServicio.buscarCatastroPorCodigosClave(codNac, codLoc);
                    if (catastroPredialActual == null) {
                        addWarningMessage("No se encontraron resultados", "No se encontraron resultados");
                        propietarioActual = new Propietario();
                    } else {
                        propietarioActual = propietarioServicio.buscarPropietarioPorCatastro(catastroPredialActual.getCatpreCodigo());
                        if (propietarioActual == null) {
                            addWarningMessage("No se encontraron propietarios", "No se encontraron propietarios");
                        } else {
                            catDetParroquia = catalogoDetalleServicio.buscarPorCodigoCatDet(propietarioActual.getCatdetCiudad().getCatdetCodigo());
                        }
                    }
                }
            } else {
                propietarioActual = new Propietario();
                addWarningMessage("Clave Catastral:Faltan Caracteres ", "Clave Catastral:Faltan Caracteres ");
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public void actualizaDatosPropietario() {
        try {

            propietarioActual.setCatdetCiudad(catDetParroquia);
            propietarioServicio.editarPropietario(propietarioActual);
            propietarioActual = propietarioServicio.buscarPropietarioPorCatastro(catastroPredialActual.getCatpreCodigo());
            addSuccessMessage("Registro Actualizado", "");
            habilitaCamposPropietario = true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public void habilitaCamposPropietario() {
        System.out.println("Habilita Campos");
        habilitaCamposPropietario = false;
    }

    public void actualizarCamposPropietario() {
        System.out.println("Habilita Campos");
        habilitaCamposPropietario = true;
    }

    public void listarTipoEstablecimiento() throws Exception {
        listEstablecimiento = catalogoDetalleServicio.listarPorNemonicoCatalogo("ESTABLECIMIENTO");
    }

    public void listarTipoEmpresa() throws Exception {
        listaTipoEmpresa = catalogoDetalleServicio.listarPorNemonicoCatalogo("TIPO_EMPRESA");
    }

    public void listarTipoLocal() throws Exception {
        lisTipoLocal = catalogoDetalleServicio.listarPorNemonicoCatalogo("TIPO_LOCAL");
    }

    public void listarParroquias() throws Exception {
        listaCatDetParroquias = catalogoDetalleServicio.listarPorNemonicoCatalogo("PARROQUIAS");
    }

    public void listarTipoActividadEconomica() throws Exception {
        listTipoActEconomica = catalogoDetalleServicio.listarPorNemonicoCatalogo("ACTIVIDAD_ECONOMICA");
    }

    public void listarEspTuristica() throws Exception {
        listEspTuris = catalogoDetalleServicio.listarPorNemonicoCatalogo("ESPECIALIDAD_TUR");
    }

    public void listarIdentificacionEstado() throws Exception {
        listaIdentEstado = catalogoDetalleServicio.listarPorNemonicoCatalogo("IDENT_ESTADO");
    }

    public void listarClaveCatastral() throws Exception {
        listaCatastroPredial = catastroPredialServicio.listarClaveCatastral();
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
            patenteArchivoServicio.eliminarArchivo(file);
            addSuccessMessage("Registro Eliminado");
            listadoArchivos = patenteArchivoServicio.listarArchivoPorPatente(patenteActual);

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

    public CatalogoDetalle getCatDetEstablecimientoActual() {
        return catDetEstablecimientoActual;
    }

    public void setCatDetEstablecimientoActual(CatalogoDetalle catDetEstablecimientoActual) {
        this.catDetEstablecimientoActual = catDetEstablecimientoActual;
    }

    public List<CatalogoDetalle> getListEstablecimiento() {
        return listEstablecimiento;
    }

    public void setListEstablecimiento(List<CatalogoDetalle> listEstablecimiento) {
        this.listEstablecimiento = listEstablecimiento;
    }

    public List<CatalogoDetalle> getListaTipoEmpresa() {
        return listaTipoEmpresa;
    }

    public void setListaTipoEmpresa(List<CatalogoDetalle> listaTipoEmpresa) {
        this.listaTipoEmpresa = listaTipoEmpresa;
    }

    public CatalogoDetalle getCatDetTipoEmpresActual() {
        return catDetTipoEmpresActual;
    }

    public void setCatDetTipoEmpresActual(CatalogoDetalle catDetTipoEmpresActual) {
        this.catDetTipoEmpresActual = catDetTipoEmpresActual;
    }

    public List<CatalogoDetalle> getLisTipoLocal() {
        return lisTipoLocal;
    }

    public void setLisTipoLocal(List<CatalogoDetalle> lisTipoLocal) {
        this.lisTipoLocal = lisTipoLocal;
    }

    public CatalogoDetalle getCatDetTipoLocalActual() {
        return catDetTipoLocalActual;
    }

    public void setCatDetTipoLocalActual(CatalogoDetalle catDetTipoLocalActual) {
        this.catDetTipoLocalActual = catDetTipoLocalActual;
    }

    public CatalogoDetalle getCatDetTipActEcoActual() {
        return catDetTipActEcoActual;
    }

    public void setCatDetTipActEcoActual(CatalogoDetalle catDetTipActEcoActual) {
        this.catDetTipActEcoActual = catDetTipActEcoActual;
    }

    public List<CatalogoDetalle> getListTipoActEconomica() {
        return listTipoActEconomica;
    }

    public void setListTipoActEconomica(List<CatalogoDetalle> listTipoActEconomica) {
        this.listTipoActEconomica = listTipoActEconomica;
    }

    public CatalogoDetalle getCatDetEspTurisActual() {
        return catDetEspTurisActual;
    }

    public void setCatDetEspTurisActual(CatalogoDetalle catDetEspTurisActual) {
        this.catDetEspTurisActual = catDetEspTurisActual;
    }

    public List<CatalogoDetalle> getListEspTuris() {
        return listEspTuris;
    }

    public void setListEspTuris(List<CatalogoDetalle> listEspTuris) {
        this.listEspTuris = listEspTuris;
    }

    public CatalogoDetalle getCatDetIdentEstadoActual() {
        return catDetIdentEstadoActual;
    }

    public void setCatDetIdentEstadoActual(CatalogoDetalle catDetIdentEstadoActual) {
        this.catDetIdentEstadoActual = catDetIdentEstadoActual;
    }

    public List<CatalogoDetalle> getListaIdentEstado() {
        return listaIdentEstado;
    }

    public void setListaIdentEstado(List<CatalogoDetalle> listaIdentEstado) {
        this.listaIdentEstado = listaIdentEstado;
    }

    public CatastroPredial getCatastroPredialActual() {
        return catastroPredialActual;
    }

    public void setCatastroPredialActual(CatastroPredial catastroPredialActual) {
        this.catastroPredialActual = catastroPredialActual;
    }

    public List<CatastroPredial> getListaCatastroPredial() {
        return listaCatastroPredial;
    }

    public void setListaCatastroPredial(List<CatastroPredial> listaCatastroPredial) {
        this.listaCatastroPredial = listaCatastroPredial;
    }

    public Patente getPatenteActual() {
        return patenteActual;
    }

    public void setPatenteActual(Patente patenteActual) {
        this.patenteActual = patenteActual;
    }

    public boolean isD1() {
        return d1;
    }

    public void setD1(boolean d1) {
        this.d1 = d1;
    }

    public boolean isD2() {
        return d2;
    }

    public void setD2(boolean d2) {
        this.d2 = d2;
    }

    public boolean isD3() {
        return d3;
    }

    public void setD3(boolean d3) {
        this.d3 = d3;
    }

    public boolean isD4() {
        return d4;
    }

    public void setD4(boolean d4) {
        this.d4 = d4;
    }

    public boolean isD5() {
        return d5;
    }

    public void setD5(boolean d5) {
        this.d5 = d5;
    }

    public boolean isD6() {
        return d6;
    }

    public void setD6(boolean d6) {
        this.d6 = d6;
    }

    public boolean isD7() {
        return d7;
    }

    public void setD7(boolean d7) {
        this.d7 = d7;
    }

    public Date getHorarioDesde() {
        return horarioDesde;
    }

    public void setHorarioDesde(Date horarioDesde) {
        this.horarioDesde = horarioDesde;
    }

    public Date getHorarioHasta() {
        return horarioHasta;
    }

    public void setHorarioHasta(Date horarioHasta) {
        this.horarioHasta = horarioHasta;
    }

    public Date getFecActividadEconomica() {
        return fecActividadEconomica;
    }

    public void setFecActividadEconomica(Date fecActividadEconomica) {
        this.fecActividadEconomica = fecActividadEconomica;
    }

    public boolean isArtesCalificado() {
        return artesCalificado;
    }

    public void setArtesCalificado(boolean artesCalificado) {
        this.artesCalificado = artesCalificado;
    }

    public boolean isLlevaConta() {
        return llevaConta;
    }

    public void setLlevaConta(boolean llevaConta) {
        this.llevaConta = llevaConta;
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

    public CatalogoDetalle getCatDetHorFuncionaActual() {
        return catDetHorFuncionaActual;
    }

    public void setCatDetHorFuncionaActual(CatalogoDetalle catDetHorFuncionaActual) {
        this.catDetHorFuncionaActual = catDetHorFuncionaActual;
    }

    public List<CatalogoDetalle> getListaHorarioFunciona() {
        return listaHorarioFunciona;
    }

    public void setListaHorarioFunciona(List<CatalogoDetalle> listaHorarioFunciona) {
        this.listaHorarioFunciona = listaHorarioFunciona;
    }

    public boolean isHabilitaCamposPropietario() {
        return habilitaCamposPropietario;
    }

    public void setHabilitaCamposPropietario(boolean habilitaCamposPropietario) {
        this.habilitaCamposPropietario = habilitaCamposPropietario;
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

    public int getVerIngresaPlaca() {
        return verIngresaPlaca;
    }

    public void setVerIngresaPlaca(int verIngresaPlaca) {
        this.verIngresaPlaca = verIngresaPlaca;
    }

    public String getCatastroPredBusca() {
        return catastroPredBusca;
    }

    public void setCatastroPredBusca(String catastroPredBusca) {
        this.catastroPredBusca = catastroPredBusca;
    }

    public int getVerGeneraContrasenia() {
        return verGeneraContrasenia;
    }

    public void setVerGeneraContrasenia(int verGeneraContrasenia) {
        this.verGeneraContrasenia = verGeneraContrasenia;
    }

    public String getContraseniaUsuario() {
        return contraseniaUsuario;
    }

    public void setContraseniaUsuario(String contraseniaUsuario) {
        this.contraseniaUsuario = contraseniaUsuario;
    }

}
