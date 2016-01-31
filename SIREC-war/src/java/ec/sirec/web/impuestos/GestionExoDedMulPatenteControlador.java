/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sirec.web.impuestos;

import ec.sirec.ejb.entidades.AdicionalesDeductivos;
import ec.sirec.ejb.entidades.DatoGlobal;
import ec.sirec.ejb.entidades.Patente;
import ec.sirec.ejb.entidades.PatenteArchivo;
import ec.sirec.ejb.entidades.PatenteValoracion;
import ec.sirec.ejb.entidades.PatenteValoracionExtras;
import ec.sirec.ejb.entidades.SegUsuario;
import ec.sirec.ejb.servicios.AdicionalesDeductivosServicio;
import ec.sirec.ejb.servicios.PatenteArchivoServicio;
import ec.sirec.ejb.servicios.PatenteServicio;
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
public class GestionExoDedMulPatenteControlador extends BaseControlador {

    @EJB
    private PatenteArchivoServicio patenteArchivoServicio;
    @EJB
    private AdicionalesDeductivosServicio adicionalesDeductivosServicio;
    @EJB
    private PatenteServicio patenteServicio;
    private Patente patenteActual;
    private PatenteValoracionExtras patValExActual;
    private PatenteValoracion patenteValoracionActal;
    private String numPatente = "";
    private boolean habilitaEdicion;
    private static final Logger LOGGER = Logger.getLogger(GestionExoDedMulPatenteControlador.class.getName());
    private int verArchivos;
    private int cargarArchivos;
    private int verBuscaPatente;
    private AdicionalesDeductivos adiDeductivoActual;
    private List<AdicionalesDeductivos> listAdicionalDeductivo;
    private List<ParametrosFile> listaFiles;
    private List<PatenteArchivo> listadoArchivos;
    private DatoGlobal datoGlobalActual;
    private SegUsuario usuarioActual;
    private PatenteArchivo patenteArchivoActual;
    private String buscNumPat;

    /**
     * Creates a new instance of GestionDetPatenteControlador
     */
    @PostConstruct
    public void inicializar() {
        try {
            buscNumPat = "";
            numPatente = "";
            patenteArchivoActual = new PatenteArchivo();
            adiDeductivoActual = new AdicionalesDeductivos();
            patenteActual = new Patente();
            verArchivos = 0;
            cargarArchivos = 0;
            patValExActual = new PatenteValoracionExtras();
            habilitaEdicion = false;
            listaFiles = new ArrayList<ParametrosFile>();
            listadoArchivos = new ArrayList<PatenteArchivo>();
            listAdicionalDeductivo = new ArrayList<AdicionalesDeductivos>();
            listarAdicionalDeductivo();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public GestionExoDedMulPatenteControlador() {
    }

    public void guardaPatenteValExtra() {
        try {
            if (verificaArchivosCargados()) {
                if (habilitaEdicion == false) {

                    guardaPatenteValoracion();
                    AdicionalesDeductivos objAdiDec = new AdicionalesDeductivos();
                    objAdiDec = adicionalesDeductivosServicio.buscarAdicionesDeductivosXNemonico("ADIDED_PAT");
                    patValExActual.setAdidedCodigo(objAdiDec);
                    patValExActual.setPatvalCodigo(patenteValoracionActal);
                    patenteServicio.crearPatenteValoracionExtra(patValExActual);
                    addSuccessMessage("Guardado Exitosamente", "Patente Valoración Extra Guardado");
                    patValExActual = new PatenteValoracionExtras();
                    cargaObjetosBitacora();
                    guardarArchivos();
                    inicializar();
                }
            } else {
                addSuccessMessage("Debe Cargar Documentación", "Debe Cargar Documentación");
            }

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
    }

    public void actualizaPatenteValExtra() {
        try {
            patValExActual.setAdidedCodigo(adiDeductivoActual);
            patValExActual.setPatvalCodigo(patenteValoracionActal);
            patenteServicio.editarPatenteValoracionExtra(patValExActual);
            addSuccessMessage("Actualizado Exitosamente", "Patente Valoración Extra Actualizado");
            patValExActual = new PatenteValoracionExtras();
            cargaObjetosBitacora();
            guardarArchivos();
            inicializar();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
    }

    public boolean verificaArchivosCargados() {
        if (listaFiles.isEmpty()) {
            return false;
        } else {
            return true;
        }

    }

    public void buscarPatente() {
        try {
            verBuscaPatente = 1;
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

    public void cagarPatenteActual() {
        try {
            patenteActual = patenteServicio.cargarObjPatente(Integer.parseInt(buscNumPat));
            if (patenteActual == null) {
                numPatente = null;
                patValExActual = new PatenteValoracionExtras();
                adiDeductivoActual = new AdicionalesDeductivos();
            } else {
                if (cargarExistePatValExtra()) {
                    patValExActual = patenteServicio.buscaPatValExtraPorPatValoracion(patenteValoracionActal.getPatvalCodigo());
                    adiDeductivoActual.setAdidedCodigo(patValExActual.getAdidedCodigo().getAdidedCodigo());
                    System.out.println("Si encontro el objeto");
                    numPatente = generaNumPatente(); //  "AE-MPM-" + patenteActual.getPatCodigo();
                } else {
                    System.out.println("No encontro el objeto");
                    numPatente = generaNumPatente();// "AE-MPM-" + patenteActual.getPatCodigo();
                    patValExActual = new PatenteValoracionExtras();
                    patValExActual.setPatvalextNumMesesIncum(0);
                    patValExActual.setPatentePorcDatosfalsos(0);
                    patValExActual.setPatentePorcIngreso(Double.valueOf(100));
                    patValExActual.setPatenteBaseimpNegativa(BigDecimal.ZERO);
                    adiDeductivoActual = new AdicionalesDeductivos();
                }

            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
    }

    public boolean cargarExistePatValExtra() {
        boolean patValExActualCargado = false;
        try {
            patenteValoracionActal = patenteServicio.buscaPatValoracion(patenteActual.getPatCodigo());
            if (patenteValoracionActal == null) {
                patValExActualCargado = false;
            } else {
                patValExActualCargado = true;
            }

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        return patValExActualCargado;
    }

    public void guardaPatenteValoracion() {
        BigDecimal valTemporal;
        valTemporal = BigDecimal.valueOf(0.00);
        try {
            patenteValoracionActal = new PatenteValoracion();
            patenteValoracionActal.setPatCodigo(patenteActual);
            patenteValoracionActal.setPatvalAnio(0);
            patenteValoracionActal.setPatvalActivos(valTemporal);
            patenteValoracionActal.setPatvalPasivos(valTemporal);
            patenteValoracionActal.setPatvalPatrimonio(valTemporal);
            patenteValoracionActal.setPatvalImpuesto(valTemporal);
            patenteValoracionActal.setPatvalSubtotal(valTemporal);
            patenteValoracionActal.setPatvalDeducciones(valTemporal);
            patenteValoracionActal.setPatvalTasaProc(valTemporal);
            patenteValoracionActal.setPatvalTotal(valTemporal);
            patenteValoracionActal.setPatvalTasaBomb(valTemporal);
            patenteServicio.crearPatenteValoracion(patenteValoracionActal);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
    }

    //Carga los objetos para guardar en la tabla bitacora
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
            addSuccessMessage("Archivo Cargado", "Archivo Cargado");
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

    public void descargarArchivo(PatenteArchivo patArchivo) {
        patenteArchivoActual = patArchivo;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("datoArchivo", patenteArchivoActual.getPatarcData());
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("nombreArchivo", patenteArchivoActual.getPatarcNombre());
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

    public void activaPanelVerArchivos() {
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

    public PatenteValoracionExtras getPatValExActual() {
        return patValExActual;
    }

    public void setPatValExActual(PatenteValoracionExtras patValExActual) {
        this.patValExActual = patValExActual;
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

    public int getVerBuscaPatente() {
        return verBuscaPatente;
    }

    public void setVerBuscaPatente(int verBuscaPatente) {
        this.verBuscaPatente = verBuscaPatente;
    }

    public String getBuscNumPat() {
        return buscNumPat;
    }

    public void setBuscNumPat(String buscNumPat) {
        this.buscNumPat = buscNumPat;
    }

}
