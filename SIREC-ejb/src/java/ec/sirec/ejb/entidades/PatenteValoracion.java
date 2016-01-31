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

/**
 *
 * @author DAVID GUAN
 */
@Entity
@Table(name = "sirec.patente_valoracion")
@NamedQueries({
    @NamedQuery(name = "PatenteValoracion.findAll", query = "SELECT p FROM PatenteValoracion p")})
public class PatenteValoracion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "patval_codigo")
    private Integer patvalCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "patval_anio")
    private int patvalAnio;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "patval_activos")
    private BigDecimal patvalActivos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "patval_pasivos")
    private BigDecimal patvalPasivos;
    @Column(name = "patval_patrimonio")
    private BigDecimal patvalPatrimonio;
    @Column(name = "patval_impuesto")
    private BigDecimal patvalImpuesto;
    @Column(name = "patval_subtotal")
    private BigDecimal patvalSubtotal;
    @Column(name = "patval_deducciones")
    private BigDecimal patvalDeducciones;
    @Column(name = "patval_tasa_proc")
    private BigDecimal patvalTasaProc;
    @Column(name = "patval_total")
    private BigDecimal patvalTotal;
     @Column(name = "patval_tasa_bomb")
    private BigDecimal patvalTasaBomb;
        @JoinColumn(name = "pat_codigo", referencedColumnName = "pat_codigo")
    @ManyToOne(optional = false)
    private Patente patCodigo;

    public PatenteValoracion() {
    }

    public PatenteValoracion(Integer patvalCodigo) {
        this.patvalCodigo = patvalCodigo;
    }

    public PatenteValoracion(Integer patvalCodigo, int patvalAnio, BigDecimal patvalActivos, BigDecimal patvalPasivos) {
        this.patvalCodigo = patvalCodigo;
        this.patvalAnio = patvalAnio;
        this.patvalActivos = patvalActivos;
        this.patvalPasivos = patvalPasivos;
    }

    public Integer getPatvalCodigo() {
        return patvalCodigo;
    }

    public void setPatvalCodigo(Integer patvalCodigo) {
        this.patvalCodigo = patvalCodigo;
    }

    public int getPatvalAnio() {
        return patvalAnio;
    }

    public void setPatvalAnio(int patvalAnio) {
        this.patvalAnio = patvalAnio;
    }

    public BigDecimal getPatvalActivos() {
        return patvalActivos;
    }

    public void setPatvalActivos(BigDecimal patvalActivos) {
        this.patvalActivos = patvalActivos;
    }

    public BigDecimal getPatvalPasivos() {
        return patvalPasivos;
    }

    public void setPatvalPasivos(BigDecimal patvalPasivos) {
        this.patvalPasivos = patvalPasivos;
    }

    public BigDecimal getPatvalPatrimonio() {
        return patvalPatrimonio;
    }

    public void setPatvalPatrimonio(BigDecimal patvalPatrimonio) {
        this.patvalPatrimonio = patvalPatrimonio;
    }

    public BigDecimal getPatvalImpuesto() {
        return patvalImpuesto;
    }

    public void setPatvalImpuesto(BigDecimal patvalImpuesto) {
        this.patvalImpuesto = patvalImpuesto;
    }

    public BigDecimal getPatvalSubtotal() {
        return patvalSubtotal;
    }

    public void setPatvalSubtotal(BigDecimal patvalSubtotal) {
        this.patvalSubtotal = patvalSubtotal;
    }

    public BigDecimal getPatvalDeducciones() {
        return patvalDeducciones;
    }

    public void setPatvalDeducciones(BigDecimal patvalDeducciones) {
        this.patvalDeducciones = patvalDeducciones;
    }

    public BigDecimal getPatvalTasaProc() {
        return patvalTasaProc;
    }

    public void setPatvalTasaProc(BigDecimal patvalTasaProc) {
        this.patvalTasaProc = patvalTasaProc;
    }

    public BigDecimal getPatvalTotal() {
        return patvalTotal;
    }

    public void setPatvalTotal(BigDecimal patvalTotal) {
        this.patvalTotal = patvalTotal;
    }

    public Patente getPatCodigo() {
        return patCodigo;
    }

    public void setPatCodigo(Patente patCodigo) {
        this.patCodigo = patCodigo;
    }

    public BigDecimal getPatvalTasaBomb() {
        return patvalTasaBomb;
    }

    public void setPatvalTasaBomb(BigDecimal patvalTasaBomb) {
        this.patvalTasaBomb = patvalTasaBomb;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (patvalCodigo != null ? patvalCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatenteValoracion)) {
            return false;
        }
        PatenteValoracion other = (PatenteValoracion) object;
        if ((this.patvalCodigo == null && other.patvalCodigo != null) || (this.patvalCodigo != null && !this.patvalCodigo.equals(other.patvalCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sirec.ejb.entidades.PatenteValoracion[ patvalCodigo=" + patvalCodigo + " ]";
    }
    
}
