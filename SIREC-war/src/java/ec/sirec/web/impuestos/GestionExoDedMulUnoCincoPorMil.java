/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sirec.web.impuestos;

import ec.sirec.ejb.entidades.AdicionalesDeductivos;
import ec.sirec.ejb.entidades.DatoGlobal;
import ec.sirec.ejb.entidades.Patente;
import ec.sirec.ejb.entidades.Patente15xmilValoracion;
import ec.sirec.ejb.entidades.Patente15xmilValoracionExtras;
import ec.sirec.ejb.entidades.PatenteArchivo;
import ec.sirec.ejb.entidades.SegUsuario;
import ec.sirec.ejb.servicios.AdicionalesDeductivosServicio;
import ec.sirec.ejb.servicios.PatenteArchivoServicio;
import ec.sirec.ejb.servicios.PatenteServicio;
import ec.sirec.ejb.servicios.UnoPCinoPorMilServicio;
import ec.sirec.web.base.BaseControlador;
import ec.sirec.web.util.ParametrosFile;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author Darwin Aldas
 */
@ManagedBean
@ViewScoped
public class GestionExoDedMulUnoCincoPorMil extends BaseControlador {
    @EJB
    private AdicionalesDeductivosServicio adicionalesDeductivosServicio1;

    @EJB
    private PatenteServicio patenteServicio;

    @EJB
    private UnoPCinoPorMilServicio unoPCinoPorMilServicio;

    @EJB
    private PatenteArchivoServicio patenteArchivoServicio;
    @EJB
    private AdicionalesDeductivosServicio adicionalesDeductivosServicio;

    private Patente patenteActual;
    private Patente15xmilValoracionExtras patValEx15xMilActual;
    private Patente15xmilValoracion patValo15xMilActal;
    private String numPatente;
    private boolean habilitaEdicion;
    private static final Logger LOGGER = Logger.getLogger(GestionExoDedMulUnoCincoPorMil.class.getName());
    private int verArchivos;
    private int cargarArchivos;
    private AdicionalesDeductivos adiDeductivoActual;
    private List<AdicionalesDeductivos> listAdicionalDeductivo;
    private List<ParametrosFile> listaFiles;
    private List<PatenteArchivo> listadoArchivos;
    private DatoGlobal datoGlobalActual;
    private SegUsuario usuarioActual;
    private PatenteArchivo patenteArchivoActual;
    private String buscNumPat;
    private int verBuscaPatente;
    private int verResultado;

    /**
     * Creates a new instance of GestionDetPatenteControlador
     */
    @PostConstruct
    public void inicializar() {
        try {
            numPatente = "";
            buscNumPat = "";
            verResultado = 0;
            verBuscaPatente = 0;
            adiDeductivoActual = new AdicionalesDeductivos();
            patenteActual = new Patente();
            patValo15xMilActal = new Patente15xmilValoracion();
            verArchivos = 0;
            cargarArchivos = 0;
            patValEx15xMilActual = new Patente15xmilValoracionExtras();
            habilitaEdicion = false;
            listaFiles = new ArrayList<ParametrosFile>();
            listadoArchivos = new ArrayList<PatenteArchivo>();
            patenteArchivoActual = new PatenteArchivo();
            listarAdicionalDeductivo();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public GestionExoDedMulUnoCincoPorMil() {
    }

    public void cargarNumPatente() {
        patenteActual = (Patente) this.getSession().getAttribute("patente");
        if (patenteActual == null) {
            numPatente = null;
        } else {
            numPatente = "AE-MPM-" + patenteActual.getPatCodigo();
        }
    }

    public void buscarPatente() {
        try {
            verBuscaPatente = 1;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
    }

    public void cagarPatenteActual() {
        try {
            patenteActual = patenteServicio.cargarObjPatente(Integer.parseInt(buscNumPat));
            if (patenteActual == null) {
                numPatente = null;
                patValEx15xMilActual = new Patente15xmilValoracionExtras();
                adiDeductivoActual = new AdicionalesDeductivos();
            } else {
                if (cargarExistePatVal15PorMilExtra()) {
                    patValEx15xMilActual = unoPCinoPorMilServicio.buscaPatVal15xMilExtraPorPatValoracion(patValo15xMilActal.getPat15valCodigo());
                    adiDeductivoActual.setAdidedCodigo(patValEx15xMilActual.getAdidedCodigo().getAdidedCodigo());
                    System.out.println("Si encontro el objeto");
                    numPatente = generaNumPatente(); //"AE-MPM-" + patenteActual.getPatCodigo();
                } else {
                    System.out.println("No encontro el objeto");
                    numPatente = generaNumPatente();//"AE-MPM-" + patenteActual.getPatCodigo();
                    patValEx15xMilActual = new Patente15xmilValoracionExtras();
                    patValEx15xMilActual.setPat15valNumMesesIncum(0);
                    patValEx15xMilActual.setPat15valProcesoLiquidacion(0);
                    patValEx15xMilActual.setPat15valextValor(BigDecimal.valueOf(100));
                    patValEx15xMilActual.setPat15valextBase(BigDecimal.ZERO);
                    patValEx15xMilActual.setPat15valEvaluaDatFalsos(0);
                    adiDeductivoActual = new AdicionalesDeductivos();
                }

            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
    }

    public String generaNumPatente() { //Genera numero de patente aleatorio
        String numeroPatente = "";
        try {
            int valorRetornado = patenteActual.getPatCodigo();
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

    public boolean cargarExistePatVal15PorMilExtra() {
        boolean pat15PorMilValoracion = false;
        try {
            patValo15xMilActal = unoPCinoPorMilServicio.buscaPatValoracion15xMil(patenteActual.getPatCodigo());
            if (patValo15xMilActal == null) {
                pat15PorMilValoracion = false;
            } else {
                pat15PorMilValoracion = true;
            }

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        return pat15PorMilValoracion;
    }

    public void guardaPatente15xMilValExtra() {
        try {
            if (habilitaEdicion == false) {
                guardaPatenteValoracion15PorMil();
                AdicionalesDeductivos objAdiDec=new AdicionalesDeductivos();
                objAdiDec=adicionalesDeductivosServicio.buscarAdicionesDeductivosXNemonico("ADIDED_PAT");
                patValEx15xMilActual.setAdidedCodigo(objAdiDec);
                patValEx15xMilActual.setPat15valCodigo(patValo15xMilActal);
                unoPCinoPorMilServicio.crearPatenteValoracion15xMilExtra(patValEx15xMilActual);
                addSuccessMessage("Guardado Exitosamente", "Patente Valoración Extra Guardado");
                patValEx15xMilActual = new Patente15xmilValoracionExtras();
                cargaObjetosBitacora();
                guardarArchivos();
                inicializar();
            }

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
    }

    public void guardaPatenteValoracion15PorMil() {
        BigDecimal valTemporal;
        valTemporal = BigDecimal.valueOf(0.00);
        try {
            patValo15xMilActal = new Patente15xmilValoracion();
            patValo15xMilActal.setPatCodigo(patenteActual);
            patValo15xMilActal.setPat15valNumSucursales(0);
            patValo15xMilActal.setPat15valAnioBalance(0);
            patValo15xMilActal.setPat15valIngresoAnual(valTemporal);
            patValo15xMilActal.setPat15valActivos(valTemporal);
            patValo15xMilActal.setPat15valPasivosCorriente(valTemporal);
            patValo15xMilActal.setPat15valPasivosConting(valTemporal);
            patValo15xMilActal.setPat15valOtrasDeducciones(valTemporal);
            patValo15xMilActal.setPat15valBaseImponible(valTemporal);
            patValo15xMilActal.setPat15valTasaProc(valTemporal);
            patValo15xMilActal.setPat15valImpuesto(valTemporal);
            patValo15xMilActal.setPat15valImpuesto(valTemporal);
            patValo15xMilActal.setPat15valSubtotal(valTemporal);
            patValo15xMilActal.setPat15valRecargos(valTemporal);
            patValo15xMilActal.setPat15valTotal(valTemporal);
            unoPCinoPorMilServicio.crearPatenteValoracion15xMil(patValo15xMilActal);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
    }

    //Carga los objetos para guardar en la tabla bitacora
    public void cargaObjetosBitacora() {
        try {
            datoGlobalActual = new DatoGlobal();
            usuarioActual = new SegUsuario();
            datoGlobalActual = unoPCinoPorMilServicio.buscaMensajeTransaccion("Msj_Pat_In");
            usuarioActual = (SegUsuario) this.getSession().getAttribute("usuario");
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
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
                patArchivo.setPatarcTipo("EX"); //Archivo de Patentes
                patArchivo.setUsuIdentificacion(usuarioActual);
                patArchivo.setUltaccDetalle(datoGlobalActual.getDatgloDescripcion());
                patArchivo.setUltaccMarcatiempo(java.util.Calendar.getInstance().getTime());
                patenteArchivoServicio.guardarArchivo(patArchivo);
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
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
            addSuccessMessage(event.getFile().getFileName() + "Archivo Cargado");
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
//Preparamos archivo para descarga

    public void descargarArchivo(PatenteArchivo patArchivoActual) {
        patenteArchivoActual = patArchivoActual;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("datoArchivo", patArchivoActual.getPatarcData());
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("nombreArchivo", patArchivoActual.getPatarcNombre());
    }
//Se descarga archivo por medio de  Servlet

    public String download() {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=\"" + "c://subido" + patenteArchivoActual.getPatarcNombre() + "\"");
        try {
            ServletOutputStream os = response.getOutputStream();
            os.write(patenteArchivoActual.getPatarcData());
            os.flush();
            os.close();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
        return null;
    }

    public void listarAdicionalDeductivo() throws Exception {
        listAdicionalDeductivo = adicionalesDeductivosServicio.listarAdicionesDeductivosTipoImpuesto("PA");
    }

    public void listarArchivosPatente() throws Exception {
        listadoArchivos = patenteArchivoServicio.listarArchivoPorPatente(patenteActual);
    }

    public void activPanelCargrArchivos() {
        if (patenteActual.getPatCodigo() == null) {
            addWarningMessage("Debe activar NºPatente", "Debe activar NºPatente");
            cargarArchivos = 0;
        } else {
            cargarArchivos = 1;
        }

    }

    public void activaPanerVerArchivos() {
        try {
            if (patenteActual.getPatCodigo() == null) {
                addWarningMessage("Debe activar NºPatente", "Debe activar NºPatente");
            } else {
                listarArchivosPatente();
                if (listadoArchivos.isEmpty()) {
                    verArchivos = 1;
                } else {
                    verArchivos = 0;
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        }

    }

    public Patente15xmilValoracionExtras getPatValEx15xMilActual() {
        return patValEx15xMilActual;
    }

    public void setPatValEx15xMilActual(Patente15xmilValoracionExtras patValEx15xMilActual) {
        this.patValEx15xMilActual = patValEx15xMilActual;
    }

    public int getVerArchivos() {
        return verArchivos;
    }

    public void setVerArchivos(int verArchivos) {
        this.verArchivos = verArchivos;
    }

    public int getCargarArchivos() {
        return cargarArchivos;
    }

    public void setCargarArchivos(int cargarArchivos) {
        this.cargarArchivos = cargarArchivos;
    }

    public String getNumPatente() {
        return numPatente;
    }

    public void setNumPatente(String numPatente) {
        this.numPatente = numPatente;
    }

    public AdicionalesDeductivos getAdiDeductivoActual() {
        return adiDeductivoActual;
    }

    public void setAdiDeductivoActual(AdicionalesDeductivos adiDeductivoActual) {
        this.adiDeductivoActual = adiDeductivoActual;
    }

    public List<AdicionalesDeductivos> getListAdicionalDeductivo() {
        return listAdicionalDeductivo;
    }

    public void setListAdicionalDeductivo(List<AdicionalesDeductivos> listAdicionalDeductivo) {
        this.listAdicionalDeductivo = listAdicionalDeductivo;
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

    public String getBuscNumPat() {
        return buscNumPat;
    }

    public void setBuscNumPat(String buscNumPat) {
        this.buscNumPat = buscNumPat;
    }

    public int getVerBuscaPatente() {
        return verBuscaPatente;
    }

    public void setVerBuscaPatente(int verBuscaPatente) {
        this.verBuscaPatente = verBuscaPatente;
    }

    public int getVerResultado() {
        return verResultado;
    }

    public void setVerResultado(int verResultado) {
        this.verResultado = verResultado;
    }

}
