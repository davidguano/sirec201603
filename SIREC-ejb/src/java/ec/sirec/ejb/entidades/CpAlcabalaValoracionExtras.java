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
@Table(name = "sirec.cp_alcabala_valoracion_extras")
@NamedQueries({
    @NamedQuery(name = "CpAlcabalaValoracionExtras.findAll", query = "SELECT c FROM CpAlcabalaValoracionExtras c")})
public class CpAlcabalaValoracionExtras implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cpalcvalext_codigo")
    private Integer cpalcvalextCodigo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cpalcvalext_base")
    private BigDecimal cpalcvalextBase;
    @Column(name = "cpalcvalext_valor")
    private BigDecimal cpalcvalextValor;
    @JoinColumn(name = "catprealcval_codigo", referencedColumnName = "catprealcval_codigo")
    @ManyToOne(optional = false)
    private CatastroPredialAlcabalaValoracion catprealcvalCodigo;
    @JoinColumn(name = "adided_codigo", referencedColumnName = "adided_codigo")
    @ManyToOne(optional = false)
    private AdicionalesDeductivos adidedCodigo;

    public CpAlcabalaValoracionExtras() {
    }

    public CpAlcabalaValoracionExtras(Integer cpalcvalextCodigo) {
        this.cpalcvalextCodigo = cpalcvalextCodigo;
    }

    public Integer getCpalcvalextCodigo() {
        return cpalcvalextCodigo;
    }

    public void setCpalcvalextCodigo(Integer cpalcvalextCodigo) {
        this.cpalcvalextCodigo = cpalcvalextCodigo;
    }

    public BigDecimal getCpalcvalextBase() {
        return cpalcvalextBase;
    }

    public void setCpalcvalextBase(BigDecimal cpalcvalextBase) {
        this.cpalcvalextBase = cpalcvalextBase;
    }

    public BigDecimal getCpalcvalextValor() {
        return cpalcvalextValor;
    }

    public void setCpalcvalextValor(BigDecimal cpalcvalextValor) {
        this.cpalcvalextValor = cpalcvalextValor;
    }

    public CatastroPredialAlcabalaValoracion getCatprealcvalCodigo() {
        return catprealcvalCodigo;
    }

    public void setCatprealcvalCodigo(CatastroPredialAlcabalaValoracion catprealcvalCodigo) {
        this.catprealcvalCodigo = catprealcvalCodigo;
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
        hash += (cpalcvalextCodigo != null ? cpalcvalextCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CpAlcabalaValoracionExtras)) {
            return false;
        }
        CpAlcabalaValoracionExtras other = (CpAlcabalaValoracionExtras) object;
        if ((this.cpalcvalextCodigo == null && other.cpalcvalextCodigo != null) || (this.cpalcvalextCodigo != null && !this.cpalcvalextCodigo.equals(other.cpalcvalextCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sirec.ejb.entidades.CpAlcabalaValoracionExtras[ cpalcvalextCodigo=" + cpalcvalextCodigo + " ]";
    }
    
}
