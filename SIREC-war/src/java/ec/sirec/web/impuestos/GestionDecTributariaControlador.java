/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sirec.web.impuestos;

import ec.sirec.ejb.entidades.CatalogoDetalle;
import ec.sirec.ejb.entidades.CatastroPredial;
import ec.sirec.ejb.entidades.Patente;
import ec.sirec.ejb.entidades.Patente15xmilValoracion;
import ec.sirec.ejb.entidades.PatenteValoracion;
import ec.sirec.ejb.entidades.Propietario;
import ec.sirec.ejb.entidades.SegUsuario;
import ec.sirec.ejb.servicios.CatalogoDetalleServicio;
import ec.sirec.ejb.servicios.CatastroPredialServicio;
import ec.sirec.ejb.servicios.DeclaracionTributariaServicio;
import ec.sirec.ejb.servicios.PatenteServicio;
import ec.sirec.ejb.servicios.PropietarioServicio;
import ec.sirec.ejb.servicios.UnoPCinoPorMilServicio;
import ec.sirec.web.base.BaseControlador;
import ec.sirec.web.util.UtilitariosCod;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author Darwin Aldas
 */
@ManagedBean
@ViewScoped
public class GestionDecTributariaControlador extends BaseControlador {

    @EJB
    private UnoPCinoPorMilServicio unoPCinoPorMilServicio;

    @EJB
    private DeclaracionTributariaServicio declaracionTributariaServicio;

    @EJB
    private CatastroPredialServicio catastroPredialServicio;

    @EJB
    private PropietarioServicio propietarioServicio;

    @EJB
    private CatalogoDetalleServicio catalogoDetalleServicio;

    @EJB
    private PatenteServicio patenteServicio;
    private CatastroPredial catastroPredialActual;
    private Patente patenteActual;
    private SegUsuario usuarioActual;
    private Propietario propietarioActual;
    private Patente15xmilValoracion patente15milValActual;
    private PatenteValoracion patenteValoracionActual;
    private int verPanelDetalleImp;
    private boolean habilitaEdicion;
    private CatalogoDetalle catDetTipActEconActual;
    private CatalogoDetalle catDeTipoDeclaracion;
    private List<CatalogoDetalle> listCatDetTipEcoActual;
    private List<CatalogoDetalle> lisCatDeTipoDeclara;
    private List<CatalogoDetalle> listCatDetCantones;
    private static final Logger LOGGER = Logger.getLogger(GestionDecTributariaControlador.class.getName());

    /**
     * Creates a new instance of GestionDetPatenteControlador
     */
    @PostConstruct
    public void inicializar() {
        try {
            catDeTipoDeclaracion = new CatalogoDetalle();
            catDetTipActEconActual = new CatalogoDetalle();
            catastroPredialActual = new CatastroPredial();
            usuarioActual = (SegUsuario) this.getSession().getAttribute("usuario");
            propietarioActual = propietarioServicio.buscarPropietario(usuarioActual.getUsuIdentificacion());
            listarActividadEconomica();
            patenteActual = new Patente();
            patente15milValActual = new Patente15xmilValoracion();
            patenteValoracionActual = new PatenteValoracion();
            verPanelDetalleImp = 0;
            habilitaEdicion = false;
            listarTipoDeclaracion();
            listarCantones();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public GestionDecTributariaControlador() {
    }

    public void cargarInfoPatentePropietario() {
        try {
            patenteActual = patenteServicio.buscaParPrRucActEco(propietarioActual.getProCi(), catDetTipActEconActual.getCatdetCodigo());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }

    }

    public void activaPanelDetalleImpuestos() {
        verPanelDetalleImp = 1;
    }

    public void listarActividadEconomica() throws Exception {
        listCatDetTipEcoActual = declaracionTributariaServicio.buscarCatalogoDetallePorRuc(usuarioActual.getUsuIdentificacion());
    }

    public void listarCantones() throws Exception {
        listCatDetCantones = catalogoDetalleServicio.listarPorNemonicoCatalogo("CIUDADES");
    }

    public void listarTipoDeclaracion() throws Exception {
        lisCatDeTipoDeclara = catalogoDetalleServicio.listarPorNemonicoCatalogo("TIPO_DECLARA");
    }

    public void calcular() {
        try {
            patenteValoracionActual = patenteServicio.buscaPatValoracion(patenteActual.getPatCodigo());
            patente15milValActual = unoPCinoPorMilServicio.buscaPatValoracion15xMil(patenteActual.getPatCodigo());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
    }

    public String generarReporteDeclaracionTributaria() throws Exception {
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
            parameters.put("cod_patente", patenteActual.getPatCodigo());
            parameters.put("logo_gad", servletContext.getRealPath("/imagenes/icons/gadPedroMoncayo.jpg"));
            jasperReport = (JasperReport) JRLoader.loadObject(servletContext.getRealPath("/reportes/patentes/declaracionTributaria.jasper"));
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

    public int getVerPanelDetalleImp() {
        return verPanelDetalleImp;
    }

    public void setVerPanelDetalleImp(int verPanelDetalleImp) {
        this.verPanelDetalleImp = verPanelDetalleImp;
    }

    public Patente getPatenteActual() {
        return patenteActual;
    }

    public void setPatenteActual(Patente patenteActual) {
        this.patenteActual = patenteActual;
    }

    public Patente15xmilValoracion getPatente15milValActual() {
        return patente15milValActual;
    }

    public void setPatente15milValActual(Patente15xmilValoracion patente15milValActual) {
        this.patente15milValActual = patente15milValActual;
    }

    public CatalogoDetalle getCatDetTipActEconActual() {
        return catDetTipActEconActual;
    }

    public void setCatDetTipActEconActual(CatalogoDetalle catDetTipActEconActual) {
        this.catDetTipActEconActual = catDetTipActEconActual;
    }

    public List<CatalogoDetalle> getListCatDetTipEcoActual() {
        return listCatDetTipEcoActual;
    }

    public void setListCatDetTipEcoActual(List<CatalogoDetalle> listCatDetTipEcoActual) {
        this.listCatDetTipEcoActual = listCatDetTipEcoActual;
    }

    public Propietario getPropietarioActual() {
        return propietarioActual;
    }

    public void setPropietarioActual(Propietario propietarioActual) {
        this.propietarioActual = propietarioActual;
    }

    public CatalogoDetalle getCatDeTipoDeclaracion() {
        return catDeTipoDeclaracion;
    }

    public void setCatDeTipoDeclaracion(CatalogoDetalle catDeTipoDeclaracion) {
        this.catDeTipoDeclaracion = catDeTipoDeclaracion;
    }

    public List<CatalogoDetalle> getLisCatDeTipoDeclara() {
        return lisCatDeTipoDeclara;
    }

    public void setLisCatDeTipoDeclara(List<CatalogoDetalle> lisCatDeTipoDeclara) {
        this.lisCatDeTipoDeclara = lisCatDeTipoDeclara;
    }

    public List<CatalogoDetalle> getListCatDetCantones() {
        return listCatDetCantones;
    }

    public void setListCatDetCantones(List<CatalogoDetalle> listCatDetCantones) {
        this.listCatDetCantones = listCatDetCantones;
    }

    public PatenteValoracion getPatenteValoracionActual() {
        return patenteValoracionActual;
    }

    public void setPatenteValoracionActual(PatenteValoracion patenteValoracionActual) {
        this.patenteValoracionActual = patenteValoracionActual;
    }

}
