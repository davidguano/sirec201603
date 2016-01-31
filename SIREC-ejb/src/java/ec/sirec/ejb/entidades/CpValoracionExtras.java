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

/**
 *
 * @author new
 */
@Entity
@Table(name = "sirec.cp_valoracion_extras")
@NamedQueries({
    @NamedQuery(name = "CpValoracionExtras.findAll", query = "SELECT c FROM CpValoracionExtras c")})
public class CpValoracionExtras implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cpvalext_codigo")
    private Integer cpvalextCodigo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cpvalext_base")
    private BigDecimal cpvalextBase;
    @Column(name = "cpvalext_valor")
    private BigDecimal cpvalextValor;
    @JoinColumn(name = "catpreval_codigo", referencedColumnName = "catpreval_codigo")
    @ManyToOne(optional = false)
    private CatastroPredialValoracion catprevalCodigo;
    @JoinColumn(name = "adided_codigo", referencedColumnName = "adided_codigo")
    @ManyToOne(optional = false)
    private AdicionalesDeductivos adidedCodigo;

    public CpValoracionExtras() {
    }

    public CpValoracionExtras(Integer cpvalextCodigo) {
        this.cpvalextCodigo = cpvalextCodigo;
    }

    public Integer getCpvalextCodigo() {
        return cpvalextCodigo;
    }

    public void setCpvalextCodigo(Integer cpvalextCodigo) {
        this.cpvalextCodigo = cpvalextCodigo;
    }

    public BigDecimal getCpvalextBase() {
        return cpvalextBase;
    }

    public void setCpvalextBase(BigDecimal cpvalextBase) {
        this.cpvalextBase = cpvalextBase;
    }

    public BigDecimal getCpvalextValor() {
        return cpvalextValor;
    }

    public void setCpvalextValor(BigDecimal cpvalextValor) {
        this.cpvalextValor = cpvalextValor;
    }

    public CatastroPredialValoracion getCatprevalCodigo() {
        return catprevalCodigo;
    }

    public void setCatprevalCodigo(CatastroPredialValoracion catprevalCodigo) {
        this.catprevalCodigo = catprevalCodigo;
    }

    public AdicionalesDeductivos getAdidedCodigo() {
        return adidedCodigo;
    }

    public void setAdidedCodigo(AdicionalesDeductivos adidedCodigo) {
        this.adidedCodigo = adidedCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cpvalextCodigo != null ? cpvalextCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CpValoracionExtras)) {
            return false;
        }
        CpValoracionExtras other = (CpValoracionExtras) object;
        if ((this.cpvalextCodigo == null && other.cpvalextCodigo != null) || (this.cpvalextCodigo != null && !this.cpvalextCodigo.equals(other.cpvalextCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sirec.ejb.entidades.CpValoracionExtras[ cpvalextCodigo=" + cpvalextCodigo + " ]";
    }
    
}
