/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sirec.web.impuestos;

import ec.sirec.ejb.entidades.AdicionalesDeductivos;
import ec.sirec.ejb.entidades.CatalogoDetalle;
import ec.sirec.ejb.entidades.DatoGlobal;
import ec.sirec.ejb.entidades.Patente;
import ec.sirec.ejb.entidades.PatenteArchivo;
import ec.sirec.ejb.entidades.PatenteValoracion;
import ec.sirec.ejb.entidades.PatenteValoracionExtras;
import ec.sirec.ejb.entidades.SegUsuario;
import ec.sirec.ejb.servicios.AdicionalesDeductivosServicio;
import ec.sirec.ejb.servicios.CatalogoDetalleServicio;
import ec.sirec.ejb.servicios.PatenteArchivoServicio;
import ec.sirec.ejb.servicios.PatenteServicio;
import ec.sirec.web.base.BaseControlador;
import ec.sirec.web.util.ParametrosFile;
import java.io.InputStream;
import java.io.Serializable;
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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import static org.apache.poi.ss.util.CellUtil.createCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author Darwin Aldas
 */
@ManagedBean
@ViewScoped
public class GestionEmisionPatenteControlador extends BaseControlador {

    @EJB
    private CatalogoDetalleServicio catalogoDetalleServicio;

    @EJB
    private PatenteServicio patenteServicio;

    private Patente patenteActual;
    private PatenteValoracion patValoracionActual;
    private CatalogoDetalle catDetParroquias;
    private List<CatalogoDetalle> listDetParroquias;
    private List<CatalogoDetalle> listAnios;
    private static final Logger LOGGER = Logger.getLogger(GestionEmisionPatenteControlador.class.getName());
    private List<Object[]> listaEmisionPatente;
    private String numPatente;
    private CatalogoDetalle catDetAnio;
    private int busOpcion;
    private int verBusParroquias;
    private int verBusPatente;
    private int verBusGlobal;
    private boolean global;

    /**
     * Creates a new instance of GestionDetPatenteControlador
     */
    @PostConstruct
    public void inicializar() {
        try {
            numPatente = "";
            patenteActual = new Patente();
            patValoracionActual = new PatenteValoracion();
            catDetAnio = new CatalogoDetalle();
            listaEmisionPatente = new ArrayList<Object[]>();
            busOpcion = 0;
            verBusParroquias = 0;
            verBusPatente = 0;
            verBusGlobal = 0;
            global = false;
            listarParroquias();
            listarAnios();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public GestionEmisionPatenteControlador() {
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

    public void listarEmisionPatente() {
        CatalogoDetalle objCatDet = new CatalogoDetalle();
        try {
            objCatDet = catalogoDetalleServicio.buscarPorCodigoCatDet(catDetAnio.getCatdetCodigo());
            if (verBusPatente == 1) {
                System.out.println("Entra opcion1");
                listaEmisionPatente = patenteServicio.listarEmisionAnioPatente(patenteActual.getPatCodigo(), Integer.parseInt(objCatDet.getCatdetTexto()));
            }
            if (verBusParroquias == 1) {
                System.out.println("Entra opcion 2");

                listaEmisionPatente = patenteServicio.listarEmisionAnioParroquia(Integer.parseInt(objCatDet.getCatdetTexto()), catDetParroquias.getCatdetCodigo());
            }
            if (verBusGlobal == 1) {
                System.out.println("Entra opcion 3");
                listaEmisionPatente = patenteServicio.listarEmisionAnioGlobal(Integer.parseInt(objCatDet.getCatdetTexto()));
            }

            if (listaEmisionPatente != null) {
                numPatente = "Positivo";
            } else {
                numPatente = null;
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
    }

    public void listarAnios() throws Exception {
        listAnios = catalogoDetalleServicio.listarPorNemonicoCatalogo("ANIOS");
    }

    public void postProcessXLS(Object document) {
        XSSFWorkbook wb = (XSSFWorkbook) document;
        XSSFSheet sheet = wb.getSheetAt(0); //Creo variable  hoja ()contiene los atributos para la hoja de calculo
        List<String> encabezadoColumna = new ArrayList<String>();
        for (Row row : sheet) { //Recorre los valores de la fila 1 (encabezado) pero en dataTable=0
            if (row.getRowNum() == 0) {
                for (Cell cell : row) {
                    encabezadoColumna.add(cell.getStringCellValue() + " ");
                }
            } else {
                break;
            }
        }
        //----inicio crea estilo
        XSSFCellStyle style = wb.createCellStyle(); //Se crea el estilo
        XSSFFont font = wb.createFont();
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(IndexedColors.WHITE.getIndex());
        style.setFont(font);
        byte[] rgb = new byte[3];
        rgb[0] = (byte) 076;
        rgb[1] = (byte) 145;
        rgb[2] = (byte) 065;
        XSSFColor myColor = new XSSFColor(rgb);
        style.setFillForegroundColor(myColor);
        style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        XSSFRow row0 = sheet.createRow((short) 0); //Creo una fila en la posicion 0
        //----fin crea estilo
        for (int i = 0; i <= encabezadoColumna.size() - 1; i++) {
            createCell(row0, i, encabezadoColumna.get(i), style); //agrego celdas en la posicion indicada con los valores de los encabezados
        }
//Ajusta el ancho de las columnas
        for (int i = 0; i < 20; i++) {
            sheet.autoSizeColumn((short) i);
        }
    }

    public void buscaSelecciona() {
        switch (busOpcion) {
            case 1:
                verBusPatente = 1;
                verBusParroquias = 0;
                verBusGlobal = 0;
                break;
            case 2:
                verBusParroquias = 1;
                verBusPatente = 0;
                verBusGlobal = 0;
                break;
            case 3:
                verBusGlobal = 1;
                verBusParroquias = 0;
                verBusPatente = 0;
                break;
        }
    }

    public void listarParroquias() throws Exception {
        listDetParroquias = catalogoDetalleServicio.listarPorNemonicoCatalogo("PARROQUIAS");
    }
    public List<Object[]> getListaEmisionPatente() {
        return listaEmisionPatente;
    }

    public void setListaEmisionPatente(List<Object[]> listaEmisionPatente) {
        this.listaEmisionPatente = listaEmisionPatente;
    }

    public CatalogoDetalle getCatDetParroquias() {
        return catDetParroquias;
    }

    public void setCatDetParroquias(CatalogoDetalle catDetParroquias) {
        this.catDetParroquias = catDetParroquias;
    }

    public List<CatalogoDetalle> getListDetParroquias() {
        return listDetParroquias;
    }

    public void setListDetParroquias(List<CatalogoDetalle> listDetParroquias) {
        this.listDetParroquias = listDetParroquias;
    }

    public Patente getPatenteActual() {
        return patenteActual;
    }

    public void setPatenteActual(Patente patenteActual) {
        this.patenteActual = patenteActual;
    }

    public String getNumPatente() {
        return numPatente;
    }

    public void setNumPatente(String numPatente) {
        this.numPatente = numPatente;
    }

    public PatenteValoracion getPatValoracionActual() {
        return patValoracionActual;
    }

    public void setPatValoracionActual(PatenteValoracion patValoracionActual) {
        this.patValoracionActual = patValoracionActual;
    }

    public List<CatalogoDetalle> getListAnios() {
        return listAnios;
    }

    public void setListAnios(List<CatalogoDetalle> listAnios) {
        this.listAnios = listAnios;
    }

    public CatalogoDetalle getCatDetAnio() {
        return catDetAnio;
    }

    public void setCatDetAnio(CatalogoDetalle catDetAnio) {
        this.catDetAnio = catDetAnio;
    }

    public int getBusOpcion() {
        return busOpcion;
    }

    public void setBusOpcion(int busOpcion) {
        this.busOpcion = busOpcion;
    }

    public int getVerBusParroquias() {
        return verBusParroquias;
    }

    public void setVerBusParroquias(int verBusParroquias) {
        this.verBusParroquias = verBusParroquias;
    }

    public int getVerBusPatente() {
        return verBusPatente;
    }

    public void setVerBusPatente(int verBusPatente) {
        this.verBusPatente = verBusPatente;
    }

    public int getVerBusGlobal() {
        return verBusGlobal;
    }

    public void setVerBusGlobal(int verBusGlobal) {
        this.verBusGlobal = verBusGlobal;
    }

}
