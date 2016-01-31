/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.sirec.ejb.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author DAVID GUAN
 */
@Entity
@Table(name = "sirec.catastro_predial_plusvalia_valoracion")
@NamedQueries({
    @NamedQuery(name = "CatastroPredialPlusvaliaValoracion.findAll", query = "SELECT c FROM CatastroPredialPlusvaliaValoracion c")})
public class CatastroPredialPlusvaliaValoracion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "catprepluval_codigo")
    private Integer catprepluvalCodigo;
    @Column(name = "catprepluval_fecha_ultescr")
    @Temporal(TemporalType.DATE)
    private Date catprepluvalFechaUltescr;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "catprepluval_precioventa")
    private BigDecimal catprepluvalPrecioventa;
    @Column(name = "catprepluval_precioventa_ant")
    private BigDecimal catprepluvalPrecioventaAnt;
    @Column(name = "catprepluval_dif_bruta")
    private BigDecimal catprepluvalDifBruta;
    @Column(name = "catprepluval_valor_contrmej")
    private BigDecimal catprepluvalValorContrmej;
    @Column(name = "catprepluval_dif_neta")
    private BigDecimal catprepluvalDifNeta;
    @Column(name = "catprepluval_anios_transf")
    private Integer catprepluvalAniosTransf;
    @Column(name = "catprepluval_anios_transf_val")
    private BigDecimal catprepluvalAniosTransfVal;
    @Column(name = "catprepluval_dif_final")
    private BigDecimal catprepluvalDifFinal;
    @Column(name = "catprepluval_porc_rebaja")
    private Integer catprepluvalPorcRebaja;
    @Column(name = "catprepluval_valor_rebaja")
    private BigDecimal catprepluvalValorRebaja;
    @Column(name = "catprepluval_baseimp")
    private BigDecimal catprepluvalBaseimp;
    @Column(name = "catprepluval_tasaproc")
    private BigDecimal catprepluvalTasaproc;
    @Column(name = "catprepluval_impuesto")
    private BigDecimal catprepluvalImpuesto;
    @Column(name = "catprepluval_activo")
    private Boolean catprepluvalActivo;
    @JoinColumn(name = "catpre_codigo", referencedColumnName = "catpre_codigo")
    @ManyToOne(optional = false)
    private CatastroPredial catpreCodigo;
    @JoinColumn(name = "catdet_tipo_tarifa", referencedColumnName = "catdet_codigo")
    @ManyToOne(optional = false)
    private CatalogoDetalle catdetTipoTarifa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "catprepluvalCodigo")
    private List<CpPlusvaliaValoracionExtras> cpPlusvaliaValoracionExtrasList;

    public CatastroPredialPlusvaliaValoracion() {
    }

    public CatastroPredialPlusvaliaValoracion(Integer catprepluvalCodigo) {
        this.catprepluvalCodigo = catprepluvalCodigo;
    }

    public Integer getCatprepluvalCodigo() {
        return catprepluvalCodigo;
    }

    public void setCatprepluvalCodigo(Integer catprepluvalCodigo) {
        this.catprepluvalCodigo = catprepluvalCodigo;
    }

    public Date getCatprepluvalFechaUltescr() {
        return catprepluvalFechaUltescr;
    }

    public void setCatprepluvalFechaUltescr(Date catprepluvalFechaUltescr) {
        this.catprepluvalFechaUltescr = catprepluvalFechaUltescr;
    }

    public BigDecimal getCatprepluvalPrecioventa() {
        return catprepluvalPrecioventa;
    }

    public void setCatprepluvalPrecioventa(BigDecimal catprepluvalPrecioventa) {
        this.catprepluvalPrecioventa = catprepluvalPrecioventa;
    }

    public BigDecimal getCatprepluvalPrecioventaAnt() {
        return catprepluvalPrecioventaAnt;
    }

    public void setCatprepluvalPrecioventaAnt(BigDecimal catprepluvalPrecioventaAnt) {
        this.catprepluvalPrecioventaAnt = catprepluvalPrecioventaAnt;
    }

    public BigDecimal getCatprepluvalDifBruta() {
        return catprepluvalDifBruta;
    }

    public void setCatprepluvalDifBruta(BigDecimal catprepluvalDifBruta) {
        this.catprepluvalDifBruta = catprepluvalDifBruta;
    }

    public BigDecimal getCatprepluvalValorContrmej() {
        return catprepluvalValorContrmej;
    }

    public void setCatprepluvalValorContrmej(BigDecimal catprepluvalValorContrmej) {
        this.catprepluvalValorContrmej = catprepluvalValorContrmej;
    }

    public BigDecimal getCatprepluvalDifNeta() {
        return catprepluvalDifNeta;
    }

    public void setCatprepluvalDifNeta(BigDecimal catprepluvalDifNeta) {
        this.catprepluvalDifNeta = catprepluvalDifNeta;
    }

    public Integer getCatprepluvalAniosTransf() {
        return catprepluvalAniosTransf;
    }

    public void setCatprepluvalAniosTransf(Integer catprepluvalAniosTransf) {
        this.catprepluvalAniosTransf = catprepluvalAniosTransf;
    }

    public BigDecimal getCatprepluvalAniosTransfVal() {
        return catprepluvalAniosTransfVal;
    }

    public void setCatprepluvalAniosTransfVal(BigDecimal catprepluvalAniosTransfVal) {
        this.catprepluvalAniosTransfVal = catprepluvalAniosTransfVal;
    }

    public BigDecimal getCatprepluvalDifFinal() {
        return catprepluvalDifFinal;
    }

    public void setCatprepluvalDifFinal(BigDecimal catprepluvalDifFinal) {
        this.catprepluvalDifFinal = catprepluvalDifFinal;
    }

    public Integer getCatprepluvalPorcRebaja() {
        return catprepluvalPorcRebaja;
    }

    public void setCatprepluvalPorcRebaja(Integer catprepluvalPorcRebaja) {
        this.catprepluvalPorcRebaja = catprepluvalPorcRebaja;
    }

    public BigDecimal getCatprepluvalValorRebaja() {
        return catprepluvalValorRebaja;
    }

    public void setCatprepluvalValorRebaja(BigDecimal catprepluvalValorRebaja) {
        this.catprepluvalValorRebaja = catprepluvalValorRebaja;
    }

    public BigDecimal getCatprepluvalBaseimp() {
        return catprepluvalBaseimp;
    }

    public void setCatprepluvalBaseimp(BigDecimal catprepluvalBaseimp) {
        this.catprepluvalBaseimp = catprepluvalBaseimp;
    }

    public BigDecimal getCatprepluvalTasaproc() {
        return catprepluvalTasaproc;
    }

    public void setCatprepluvalTasaproc(BigDecimal catprepluvalTasaproc) {
        this.catprepluvalTasaproc = catprepluvalTasaproc;
    }

    public BigDecimal getCatprepluvalImpuesto() {
        return catprepluvalImpuesto;
    }

    public void setCatprepluvalImpuesto(BigDecimal catprepluvalImpuesto) {
        this.catprepluvalImpuesto = catprepluvalImpuesto;
    }

    public CatastroPredial getCatpreCodigo() {
        return catpreCodigo;
    }

    public void setCatpreCodigo(CatastroPredial catpreCodigo) {
        this.catpreCodigo = catpreCodigo;
    }

    public CatalogoDetalle getCatdetTipoTarifa() {
        return catdetTipoTarifa;
    }

    public void setCatdetTipoTarifa(CatalogoDetalle catdetTipoTarifa) {
        this.catdetTipoTarifa = catdetTipoTarifa;
    }

    public List<CpPlusvaliaValoracionExtras> getCpPlusvaliaValoracionExtrasList() {
        return cpPlusvaliaValoracionExtrasList;
    }

    public void setCpPlusvaliaValoracionExtrasList(List<CpPlusvaliaValoracionExtras> cpPlusvaliaValoracionExtrasList) {
        this.cpPlusvaliaValoracionExtrasList = cpPlusvaliaValoracionExtrasList;
    }

    public Boolean isCatprepluvalActivo() {
        return catprepluvalActivo;
    }

    public void setCatprepluvalActivo(Boolean catprepluvalActivo) {
        this.catprepluvalActivo = catprepluvalActivo;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (catprepluvalCodigo != null ? catprepluvalCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatastroPredialPlusvaliaValoracion)) {
            return false;
        }
        CatastroPredialPlusvaliaValoracion other = (CatastroPredialPlusvaliaValoracion) object;
        if ((this.catprepluvalCodigo == null && other.catprepluvalCodigo != null) || (this.catprepluvalCodigo != null && !this.catprepluvalCodigo.equals(other.catprepluvalCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sirec.ejb.entidades.CatastroPredialPlusvaliaValoracion[ catprepluvalCodigo=" + catprepluvalCodigo + " ]";
    }
    
}
