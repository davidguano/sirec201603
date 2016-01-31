/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.sirec.ejb.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author DAVID GUAN
 */
@Entity
@Table(name = "sirec.recaudacion_det")
@NamedQueries({
    @NamedQuery(name = "RecaudacionDet.findAll", query = "SELECT r FROM RecaudacionDet r")})
public class RecaudacionDet implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "recdet_codigo")
    private Integer recdetCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "recdet_tipo")
    private String recdetTipo;
    @Size(max = 20)
    @Column(name = "recdet_referencia")
    private String recdetReferencia;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "recdet_valor")
    private BigDecimal recdetValor;
    @Column(name = "recdet_codref")
    private Integer recdetCodref;
    @JoinColumn(name = "rec_codigo", referencedColumnName = "rec_codigo")
    @ManyToOne(optional = false)
    private RecaudacionCab recCodigo;
    @JoinColumn(name = "cxc_codigo", referencedColumnName = "cxc_codigo")
    @ManyToOne(optional = false)
    private CuentaPorCobrar cxcCodigo;
    @Transient
    private boolean activo;

    public RecaudacionDet() {
    }

    public RecaudacionDet(Integer recdetCodigo) {
        this.recdetCodigo = recdetCodigo;
    }

    public RecaudacionDet(Integer recdetCodigo, String recdetTipo, BigDecimal recdetValor) {
        this.recdetCodigo = recdetCodigo;
        this.recdetTipo = recdetTipo;
        this.recdetValor = recdetValor;
    }

    public Integer getRecdetCodigo() {
        return recdetCodigo;
    }

    public void setRecdetCodigo(Integer recdetCodigo) {
        this.recdetCodigo = recdetCodigo;
    }

    public String getRecdetTipo() {
        return recdetTipo;
    }

    public void setRecdetTipo(String recdetTipo) {
        this.recdetTipo = recdetTipo;
    }

    public String getRecdetReferencia() {
        return recdetReferencia;
    }

    public void setRecdetReferencia(String recdetReferencia) {
        this.recdetReferencia = recdetReferencia;
    }

    public BigDecimal getRecdetValor() {
        return recdetValor;
    }

    public void setRecdetValor(BigDecimal recdetValor) {
        this.recdetValor = recdetValor;
    }

    public RecaudacionCab getRecCodigo() {
        return recCodigo;
    }

    public void setRecCodigo(RecaudacionCab recCodigo) {
        this.recCodigo = recCodigo;
    }

    public Integer getRecdetCodref() {
        return recdetCodref;
    }

    public void setRecdetCodref(Integer recdetCodref) {
        this.recdetCodref = recdetCodref;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public CuentaPorCobrar getCxcCodigo() {
        return cxcCodigo;
    }

    public void setCxcCodigo(CuentaPorCobrar cxcCodigo) {
        this.cxcCodigo = cxcCodigo;
    }
    
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recdetCodigo != null ? recdetCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecaudacionDet)) {
            return false;
        }
        RecaudacionDet other = (RecaudacionDet) object;
        if ((this.recdetCodigo == null && other.recdetCodigo != null) || (this.recdetCodigo != null && !this.recdetCodigo.equals(other.recdetCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sirec.ejb.entidades.RecaudacionDet[ recdetCodigo=" + recdetCodigo + " ]";
    }
    
}
