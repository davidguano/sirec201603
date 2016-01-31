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
 * @author DAVID GUAN
 */
@Entity
@Table(name = "sirec.cp_plusvalia_valoracion_extras")
@NamedQueries({
    @NamedQuery(name = "CpPlusvaliaValoracionExtras.findAll", query = "SELECT c FROM CpPlusvaliaValoracionExtras c")})
public class CpPlusvaliaValoracionExtras implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cpplusvalext_codigo")
    private Integer cpplusvalextCodigo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cpplusvalext_base")
    private BigDecimal cpplusvalextBase;
    @Column(name = "cpplusvalext_valor")
    private BigDecimal cpplusvalextValor;
    @JoinColumn(name = "catprepluval_codigo", referencedColumnName = "catprepluval_codigo")
    @ManyToOne(optional = false)
    private CatastroPredialPlusvaliaValoracion catprepluvalCodigo;
    @JoinColumn(name = "adided_codigo", referencedColumnName = "adided_codigo")
    @ManyToOne(optional = false)
    private AdicionalesDeductivos adidedCodigo;

    public CpPlusvaliaValoracionExtras() {
    }

    public CpPlusvaliaValoracionExtras(Integer cpplusvalextCodigo) {
        this.cpplusvalextCodigo = cpplusvalextCodigo;
    }

    public Integer getCpplusvalextCodigo() {
        return cpplusvalextCodigo;
    }

    public void setCpplusvalextCodigo(Integer cpplusvalextCodigo) {
        this.cpplusvalextCodigo = cpplusvalextCodigo;
    }

    public BigDecimal getCpplusvalextBase() {
        return cpplusvalextBase;
    }

    public void setCpplusvalextBase(BigDecimal cpplusvalextBase) {
        this.cpplusvalextBase = cpplusvalextBase;
    }

    public BigDecimal getCpplusvalextValor() {
        return cpplusvalextValor;
    }

    public void setCpplusvalextValor(BigDecimal cpplusvalextValor) {
        this.cpplusvalextValor = cpplusvalextValor;
    }

    public CatastroPredialPlusvaliaValoracion getCatprepluvalCodigo() {
        return catprepluvalCodigo;
    }

    public void setCatprepluvalCodigo(CatastroPredialPlusvaliaValoracion catprepluvalCodigo) {
        this.catprepluvalCodigo = catprepluvalCodigo;
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
        hash += (cpplusvalextCodigo != null ? cpplusvalextCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CpPlusvaliaValoracionExtras)) {
            return false;
        }
        CpPlusvaliaValoracionExtras other = (CpPlusvaliaValoracionExtras) object;
        if ((this.cpplusvalextCodigo == null && other.cpplusvalextCodigo != null) || (this.cpplusvalextCodigo != null && !this.cpplusvalextCodigo.equals(other.cpplusvalextCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sirec.ejb.entidades.CpPlusvaliaValoracionExtras[ cpplusvalextCodigo=" + cpplusvalextCodigo + " ]";
    }
    
}
