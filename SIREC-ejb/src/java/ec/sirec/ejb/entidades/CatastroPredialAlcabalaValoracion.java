/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.sirec.ejb.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author DAVID GUAN
 */
@Entity
@Table(name = "sirec.catastro_predial_alcabala_valoracion")
@NamedQueries({
    @NamedQuery(name = "CatastroPredialAlcabalaValoracion.findAll", query = "SELECT c FROM CatastroPredialAlcabalaValoracion c")})
public class CatastroPredialAlcabalaValoracion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "catprealcval_codigo")
    private Integer catprealcvalCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "catprealcval_anio")
    private int catprealcvalAnio;
    @Size(max = 200)
    @Column(name = "catprealcval_comprador")
    private String catprealcvalComprador;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "catprealcval_precioventa")
    private BigDecimal catprealcvalPrecioventa;
    @Column(name = "catprealcval_baseimp")
    private BigDecimal catprealcvalBaseimp;
    @Column(name = "catprealcval_impuesto")
    private BigDecimal catprealcvalImpuesto;
    @Column(name = "catprealcval_consejo_prov")
    private BigDecimal catprealcvalConsejoProv;
    @Column(name = "catprealcval_tasa_proc")
    private BigDecimal catprealcvalTasaProc;
    @Column(name = "catprealcval_total")
    private BigDecimal catprealcvalTotal;
    @Size(max = 2147483647)
    @Column(name = "catprealcval_observaciones")
    private String catprealcvalObservaciones;
    @Column(name = "catprealcval_activo")
    private Boolean catprealcvalActivo;
    @JoinColumn(name = "catpre_codigo", referencedColumnName = "catpre_codigo")
    @ManyToOne(optional = false)
    private CatastroPredial catpreCodigo;
    @JoinColumn(name = "catdet_concepto", referencedColumnName = "catdet_codigo")
    @ManyToOne(optional = false)
    private CatalogoDetalle catdetConcepto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "catprealcvalCodigo")
    private List<CpAlcabalaValoracionExtras> cpAlcabalaValoracionExtrasList;

    public CatastroPredialAlcabalaValoracion() {
    }

    public CatastroPredialAlcabalaValoracion(Integer catprealcvalCodigo) {
        this.catprealcvalCodigo = catprealcvalCodigo;
    }

    public CatastroPredialAlcabalaValoracion(Integer catprealcvalCodigo, int catprealcvalAnio) {
        this.catprealcvalCodigo = catprealcvalCodigo;
        this.catprealcvalAnio = catprealcvalAnio;
    }

    public Integer getCatprealcvalCodigo() {
        return catprealcvalCodigo;
    }

    public void setCatprealcvalCodigo(Integer catprealcvalCodigo) {
        this.catprealcvalCodigo = catprealcvalCodigo;
    }

    public int getCatprealcvalAnio() {
        return catprealcvalAnio;
    }

    public void setCatprealcvalAnio(int catprealcvalAnio) {
        this.catprealcvalAnio = catprealcvalAnio;
    }

    public String getCatprealcvalComprador() {
        return catprealcvalComprador;
    }

    public void setCatprealcvalComprador(String catprealcvalComprador) {
        this.catprealcvalComprador = catprealcvalComprador;
    }

    public BigDecimal getCatprealcvalPrecioventa() {
        return catprealcvalPrecioventa;
    }

    public void setCatprealcvalPrecioventa(BigDecimal catprealcvalPrecioventa) {
        this.catprealcvalPrecioventa = catprealcvalPrecioventa;
    }

    public BigDecimal getCatprealcvalBaseimp() {
        return catprealcvalBaseimp;
    }

    public void setCatprealcvalBaseimp(BigDecimal catprealcvalBaseimp) {
        this.catprealcvalBaseimp = catprealcvalBaseimp;
    }

    public BigDecimal getCatprealcvalImpuesto() {
        return catprealcvalImpuesto;
    }

    public void setCatprealcvalImpuesto(BigDecimal catprealcvalImpuesto) {
        this.catprealcvalImpuesto = catprealcvalImpuesto;
    }

    public BigDecimal getCatprealcvalConsejoProv() {
        return catprealcvalConsejoProv;
    }

    public void setCatprealcvalConsejoProv(BigDecimal catprealcvalConsejoProv) {
        this.catprealcvalConsejoProv = catprealcvalConsejoProv;
    }

    public BigDecimal getCatprealcvalTasaProc() {
        return catprealcvalTasaProc;
    }

    public void setCatprealcvalTasaProc(BigDecimal catprealcvalTasaProc) {
        this.catprealcvalTasaProc = catprealcvalTasaProc;
    }

    public BigDecimal getCatprealcvalTotal() {
        return catprealcvalTotal;
    }

    public void setCatprealcvalTotal(BigDecimal catprealcvalTotal) {
        this.catprealcvalTotal = catprealcvalTotal;
    }

    public String getCatprealcvalObservaciones() {
        return catprealcvalObservaciones;
    }

    public void setCatprealcvalObservaciones(String catprealcvalObservaciones) {
        this.catprealcvalObservaciones = catprealcvalObservaciones;
    }

    public CatastroPredial getCatpreCodigo() {
        return catpreCodigo;
    }

    public void setCatpreCodigo(CatastroPredial catpreCodigo) {
        this.catpreCodigo = catpreCodigo;
    }

    public CatalogoDetalle getCatdetConcepto() {
        return catdetConcepto;
    }

    public void setCatdetConcepto(CatalogoDetalle catdetConcepto) {
        this.catdetConcepto = catdetConcepto;
    }

    public List<CpAlcabalaValoracionExtras> getCpAlcabalaValoracionExtrasList() {
        return cpAlcabalaValoracionExtrasList;
    }

    public void setCpAlcabalaValoracionExtrasList(List<CpAlcabalaValoracionExtras> cpAlcabalaValoracionExtrasList) {
        this.cpAlcabalaValoracionExtrasList = cpAlcabalaValoracionExtrasList;
    }

    public Boolean isCatprealcvalActivo() {
        return catprealcvalActivo;
    }

    public void setCatprealcvalActivo(Boolean catprealcvalActivo) {
        this.catprealcvalActivo = catprealcvalActivo;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (catprealcvalCodigo != null ? catprealcvalCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatastroPredialAlcabalaValoracion)) {
            return false;
        }
        CatastroPredialAlcabalaValoracion other = (CatastroPredialAlcabalaValoracion) object;
        if ((this.catprealcvalCodigo == null && other.catprealcvalCodigo != null) || (this.catprealcvalCodigo != null && !this.catprealcvalCodigo.equals(other.catprealcvalCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sirec.ejb.entidades.CatastroPredialAlcabalaValoracion[ catprealcvalCodigo=" + catprealcvalCodigo + " ]";
    }
    
}
