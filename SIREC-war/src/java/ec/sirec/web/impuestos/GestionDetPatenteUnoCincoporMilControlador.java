/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sirec.web.impuestos;

import ec.sirec.ejb.entidades.CatalogoDetalle;
import ec.sirec.ejb.entidades.DatoGlobal;
import ec.sirec.ejb.entidades.Patente;
import ec.sirec.ejb.entidades.Patente15xmilValoracion;
import ec.sirec.ejb.entidades.Patente15xmilValoracionExtras;
import ec.sirec.ejb.entidades.PatenteValoracion;
import ec.sirec.ejb.servicios.CatalogoDetalleServicio;
import ec.sirec.ejb.servicios.PatenteServicio;
import ec.sirec.ejb.servicios.UnoPCinoPorMilServicio;
import ec.sirec.web.base.BaseControlador;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Darwin Aldas
 */
@ManagedBean
@ViewScoped
public class GestionDetPatenteUnoCincoporMilControlador extends BaseControlador {

    @EJB
    private CatalogoDetalleServicio catalogoDetalleServicio;

    @EJB
    private PatenteServicio patenteServicio;
    @EJB
    private UnoPCinoPorMilServicio unoPCinoPorMilServicio;
    private Patente patenteActual;
    private Patente15xmilValoracion patente15milValActual;
    private Patente15xmilValoracionExtras pat15milValExtrActual;
    DatoGlobal datoGlobalActal;
    private int verPanelDetalleImp;
    private BigDecimal valBaseImponible;
    private BigDecimal valRecargos;
    private BigDecimal valImpuesto15xMil;
    private BigDecimal valTasaProc;
    private BigDecimal valSubTotal;
    private BigDecimal valTotal;
    private BigDecimal valTotAnual;
    private boolean habilitaEdicion;
    private String buscNumPat;
    private int verBuscaPatente;
    String numPatente;
    private CatalogoDetalle catDetAnioBalance;
    private CatalogoDetalle catDetAnioDeclara;
    private List<CatalogoDetalle> listAnios;

    private static final Logger LOGGER = Logger.getLogger(GestionDetPatenteUnoCincoporMilControlador.class.getName());

    /**
     * Creates a new instance of GestionDetPatenteControlador
     */
    @PostConstruct
    public void inicializar() {
        try {
            numPatente = "";
            verBuscaPatente = 0;
            buscNumPat = "";
            datoGlobalActal = new DatoGlobal();
            patenteActual = new Patente();
            patente15milValActual = new Patente15xmilValoracion();
            verPanelDetalleImp = 0;
            habilitaEdicion = false;
            catDetAnioBalance = new CatalogoDetalle();
            catDetAnioDeclara = new CatalogoDetalle();
            listarAnios();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public GestionDetPatenteUnoCincoporMilControlador() {
    }

    public void buscarPatente() {
        try {
            verBuscaPatente = 1;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
    }

    public void cargarNumPatente() {
        patenteActual = (Patente) this.getSession().getAttribute("patente");
        if (patenteActual == null) {
            numPatente = null;
        } else {
            numPatente = "AE-MPM-" + patenteActual.getPatCodigo();
        }
    }

    public void cagarPatenteActual() {
        try {
            patenteActual = patenteServicio.cargarObjPatente(Integer.parseInt(buscNumPat));
            if (patenteActual == null) {
                numPatente = null;
                patente15milValActual = new Patente15xmilValoracion();
                verPanelDetalleImp = 0;
            } else {
                if (cargarExistePat15porMilValoracion()) {
                    patente15milValActual = unoPCinoPorMilServicio.buscaPatValoracion15xMil(patenteActual.getPatCodigo());
                    System.out.println("Si encontro el objeto");
                    numPatente = generaNumPatente(); //"AE-MPM-" + patenteActual.getPatCodigo();
                    CatalogoDetalle objCatDetAux = new CatalogoDetalle();
                    objCatDetAux = catalogoDetalleServicio.buscarPoCatdetTexCatdetCod(patente15milValActual.getPat15valAnioDecla() + "", "A" + patente15milValActual.getPat15valAnioDecla());
                    catDetAnioDeclara = objCatDetAux;
                    objCatDetAux = catalogoDetalleServicio.buscarPoCatdetTexCatdetCod(patente15milValActual.getPat15valAnioBalance() + "", "A" + patente15milValActual.getPat15valAnioBalance());
                    catDetAnioBalance = objCatDetAux;
                } else {
                    System.out.println("No encontro el objeto");
                    numPatente = generaNumPatente(); //"AE-MPM-" + patenteActual.getPatCodigo();
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
    }

    public void listarAnios() throws Exception {
        listAnios = catalogoDetalleServicio.listarPorNemonicoCatalogo("ANIOS");
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

    public void limpiaPanelDetalleImpuestos() {
        patente15milValActual.setPat15valIngresoAnual(null);
        patente15milValActual.setPat15valNumSucursales(null);
        patente15milValActual.setPat15valActivo(null);
        patente15milValActual.setPat15valActivos(null);
        patente15milValActual.setPat15valPasivosCorriente(null);
        patente15milValActual.setPat15valPasivosConting(null);
        patente15milValActual.setPat15valOtrasDeducciones(null);
        patente15milValActual.setPat15valBaseImponible(null);
        patente15milValActual.setPat15valImpuesto(null);
        patente15milValActual.setPat15valRecargos(null);
        patente15milValActual.setPat15valTasaProc(null);
        patente15milValActual.setPat15valSubtotal(null);
        patente15milValActual.setPat15valTotal(null);
    }

    public boolean cargarExistePat15porMilValoracion() {
        boolean pa15PorMilValoracion = false;
        try {
            Patente15xmilValoracion objPat15PorMilValoracion = new Patente15xmilValoracion();
            objPat15PorMilValoracion = unoPCinoPorMilServicio.buscaPatValoracion15xMil(patenteActual.getPatCodigo());
            patenteServicio.buscaPatValoracion(patenteActual.getPatCodigo());
            if (objPat15PorMilValoracion == null) {
                pa15PorMilValoracion = false;
            } else {
                pa15PorMilValoracion = true;
            }

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        return pa15PorMilValoracion;
    }

    public void activaPanelDetalleImpuestos() {
        verPanelDetalleImp = 1;
    }

    public void calcularBaseImponible() {
        try {
            valBaseImponible = patente15milValActual.getPat15valActivos().subtract(patente15milValActual.getPat15valPasivosCorriente()).subtract(patente15milValActual.getPat15valPasivosConting()).subtract(patente15milValActual.getPat15valOtrasDeducciones());
            valBaseImponible.setScale(2, RoundingMode.HALF_UP);
            patente15milValActual.setPat15valBaseImponible(valBaseImponible);
            datoGlobalActal = unoPCinoPorMilServicio.buscaMensajeTransaccion("Val_tasa_procesamiento");
            valTasaProc = BigDecimal.valueOf(Double.parseDouble(datoGlobalActal.getDatgloValor()));
            patente15milValActual.setPat15valTasaProc(valTasaProc);
            calculaValorImpuesto15xMil();

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        }

    }

    public void calculaValorImpuesto15xMil() {
        valImpuesto15xMil = (valBaseImponible.multiply(BigDecimal.valueOf(1.5))).divide(BigDecimal.valueOf(1000));
        valImpuesto15xMil.setScale(2, RoundingMode.HALF_UP);
        patente15milValActual.setPat15valImpuesto(valImpuesto15xMil);
        calculaDeducciones();
    }

    public void calculaDeducciones() {
        try {

            Patente15xmilValoracion objPat15MilAux = new Patente15xmilValoracion();
            objPat15MilAux = unoPCinoPorMilServicio.buscaPatValoracion15xMil(patenteActual.getPatCodigo());
            Patente15xmilValoracionExtras objPat15MilExtAux = new Patente15xmilValoracionExtras();
            objPat15MilExtAux = unoPCinoPorMilServicio.buscaPatVal15xMilExtraPorPatValoracion(objPat15MilAux.getPat15valCodigo());
//**************************************EXONERACIONES************************************************           
            //--Exoneracion entidad publica
            if (objPat15MilExtAux.getPat15valextEntiPub() == true) {
                valImpuesto15xMil = BigDecimal.ZERO;
                patente15milValActual.setPat15valImpuesto(valImpuesto15xMil);
            }
            //--Exoneracion Fundaciones
            if (objPat15MilExtAux.getPat15valextFunBenEdu() == true) {
                valImpuesto15xMil = BigDecimal.ZERO;
                patente15milValActual.setPat15valImpuesto(valImpuesto15xMil);
            }
            //--Exoneracion Artesano
            if (objPat15MilExtAux.getPat15valextLeyFomArtes() == true) {
                valImpuesto15xMil = BigDecimal.ZERO;
                patente15milValActual.setPat15valImpuesto(valImpuesto15xMil);
            }
            //--Exoneracion Agro Industria
            if (objPat15MilExtAux.getPat15valextActAgro() == true) {
                valImpuesto15xMil = BigDecimal.ZERO;
                patente15milValActual.setPat15valImpuesto(valImpuesto15xMil);
            }
            //--Exoneracion coop
            if (objPat15MilExtAux.getPat15valextCoop() == true) {
                valImpuesto15xMil = BigDecimal.ZERO;
                patente15milValActual.setPat15valImpuesto(valImpuesto15xMil);
            }
            //--Exoneracion Multi Nacional
            if (objPat15MilExtAux.getPat15valextMultiNac() == true) {
                valImpuesto15xMil = BigDecimal.ZERO;
                patente15milValActual.setPat15valImpuesto(valImpuesto15xMil);
            }

//*******************************MULTAS******************************************************
            BigDecimal valDatFalso = BigDecimal.ZERO; //Valor Falsedad Datos
            BigDecimal valMultaPlazoDeclaracion = BigDecimal.ZERO;//Valor multa plazo declaración
            BigDecimal valMultaProcesoLiquidacion = BigDecimal.ZERO;//Multa a pagar por no comunicar el proceso de liquidacion
            //Incumplimiento plazo de declaración de patentes------------
            if (objPat15MilExtAux.getPat15valNumMesesIncum() != 0) {
                BigDecimal porcentajeImp = BigDecimal.ZERO;
                DatoGlobal objDatglobAux = new DatoGlobal();
                objDatglobAux = patenteServicio.cargarObjDatGloPorNombre("Val_porc_incumple_declaracion15PorMil");
                porcentajeImp = BigDecimal.valueOf(Double.parseDouble(objDatglobAux.getDatgloValor()));
                valMultaPlazoDeclaracion = (valImpuesto15xMil.multiply(porcentajeImp)).multiply(BigDecimal.valueOf(objPat15MilExtAux.getPat15valNumMesesIncum()));
                System.out.println("Multas 1.5 por mil:Incumple plazo declaracion:valor" + valMultaPlazoDeclaracion);
            }
            //Falsedad de datos------------------------------------------
            if (objPat15MilExtAux.getPat15valEvaluaDatFalsos() != 0) {
                DatoGlobal objDatglobAux = new DatoGlobal();
                objDatglobAux = patenteServicio.cargarObjDatGloPorNombre("Val_sueldo_basico");
                BigDecimal valSueldoBasico = BigDecimal.valueOf(Double.parseDouble(objDatglobAux.getDatgloValor()));
                valDatFalso = valSueldoBasico.multiply(BigDecimal.valueOf(objPat15MilExtAux.getPat15valEvaluaDatFalsos()));
                System.out.println("Multas 1.5 por mil:Falsedad de datos: valor: " + valDatFalso);
            }
            //La no justificacion de las empresas en proceso de liquidación---------------
            if (objPat15MilExtAux.getPat15valProcesoLiquidacion() != 0) {
                DatoGlobal objDatglobAux = new DatoGlobal();
                objDatglobAux = patenteServicio.cargarObjDatGloPorNombre("Val_recargo_mensual_emp_proceso_liquidacion");
                BigDecimal valPagoMensual = BigDecimal.valueOf(Double.parseDouble(objDatglobAux.getDatgloValor()));
                valMultaProcesoLiquidacion = valPagoMensual.multiply(BigDecimal.valueOf(objPat15MilExtAux.getPat15valEvaluaDatFalsos()));
                System.out.println("Multas 1.5 por mil:Multa Proceso liquidación: valor: " + valMultaProcesoLiquidacion);
            }
            //Base imponible---------------------------------
            //-----------------------------------------------
            valRecargos = valMultaPlazoDeclaracion.add(valDatFalso).add(valMultaProcesoLiquidacion);
            valRecargos.setScale(2, RoundingMode.HALF_UP);
            System.out.println("Valor de los recargos" + valRecargos);
            patente15milValActual.setPat15valRecargos(valRecargos);
            objPat15MilAux = new Patente15xmilValoracion();
            objPat15MilExtAux = new Patente15xmilValoracionExtras();
            calculaTotalSubtotal();

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        }

    }

    public void calculaTotalSubtotal() {
        valSubTotal = BigDecimal.valueOf(valTasaProc.doubleValue() + valImpuesto15xMil.doubleValue());
        valSubTotal.setScale(2, RoundingMode.HALF_UP);
        System.out.println("Valor del subtotal" + valSubTotal);
        patente15milValActual.setPat15valSubtotal(valSubTotal);
        valTotal = BigDecimal.valueOf(valSubTotal.doubleValue() + valRecargos.doubleValue());
        valTotal.setScale(2, RoundingMode.HALF_UP);
        System.out.println("Valor del total" + valTotal);
        patente15milValActual.setPat15valTotal(valTotal);
    }

    public void inicializarValores() {
        valBaseImponible = null;
        valRecargos = null;
        valImpuesto15xMil = null;
        valTasaProc = null;
        valSubTotal = null;
        valTotal = null;
        valTotAnual = null;
    }

    public void guardaPatenteDet15xMil() {
        try {
            if (habilitaEdicion == false) {
                CatalogoDetalle objCatDetAux = new CatalogoDetalle();
                objCatDetAux = catalogoDetalleServicio.buscarPorCodigoCatDet(catDetAnioBalance.getCatdetCodigo());
                patente15milValActual.setPat15valAnioBalance(Integer.parseInt(objCatDetAux.getCatdetTexto()));
                objCatDetAux = new CatalogoDetalle();
                objCatDetAux = catalogoDetalleServicio.buscarPorCodigoCatDet(catDetAnioDeclara.getCatdetCodigo());
                patente15milValActual.setPat15valAnioDecla(Integer.parseInt(objCatDetAux.getCatdetTexto()));
                patente15milValActual.setPatCodigo(patenteActual);
                unoPCinoPorMilServicio.editarPatenteValoracion15xMil(patente15milValActual);
                addSuccessMessage("Guardado Exitosamente", "Patente 1.5 Mil Valoración Guardado");
                patente15milValActual = new Patente15xmilValoracion();
                inicializar();
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
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

    public BigDecimal getValBaseImponible() {
        return valBaseImponible;
    }

    public void setValBaseImponible(BigDecimal valBaseImponible) {
        this.valBaseImponible = valBaseImponible;
    }

    public BigDecimal getValImpuesto15xMil() {
        return valImpuesto15xMil;
    }

    public void setValImpuesto15xMil(BigDecimal valImpuesto15xMil) {
        this.valImpuesto15xMil = valImpuesto15xMil;
    }

    public BigDecimal getValTotal() {
        return valTotal;
    }

    public void setValTotal(BigDecimal valTotal) {
        this.valTotal = valTotal;
    }

    public BigDecimal getValSubTotal() {
        return valSubTotal;
    }

    public void setValSubTotal(BigDecimal valSubTotal) {
        this.valSubTotal = valSubTotal;
    }

    public Patente15xmilValoracion getPatente15milValActual() {
        return patente15milValActual;
    }

    public void setPatente15milValActual(Patente15xmilValoracion patente15milValActual) {
        this.patente15milValActual = patente15milValActual;
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

    public String getNumPatente() {
        return numPatente;
    }

    public void setNumPatente(String numPatente) {
        this.numPatente = numPatente;
    }

    public BigDecimal getValTasaProc() {
        return valTasaProc;
    }

    public void setValTasaProc(BigDecimal valTasaProc) {
        this.valTasaProc = valTasaProc;
    }

    public BigDecimal getValRecargos() {
        return valRecargos;
    }

    public void setValRecargos(BigDecimal valRecargos) {
        this.valRecargos = valRecargos;
    }

    public CatalogoDetalle getCatDetAnioBalance() {
        return catDetAnioBalance;
    }

    public void setCatDetAnioBalance(CatalogoDetalle catDetAnioBalance) {
        this.catDetAnioBalance = catDetAnioBalance;
    }

    public CatalogoDetalle getCatDetAnioDeclara() {
        return catDetAnioDeclara;
    }

    public void setCatDetAnioDeclara(CatalogoDetalle catDetAnioDeclara) {
        this.catDetAnioDeclara = catDetAnioDeclara;
    }

    public List<CatalogoDetalle> getListAnios() {
        return listAnios;
    }

    public void setListAnios(List<CatalogoDetalle> listAnios) {
        this.listAnios = listAnios;
    }

}
