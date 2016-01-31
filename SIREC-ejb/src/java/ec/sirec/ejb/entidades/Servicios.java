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
@Table(name = "sirec.servicios")
@NamedQueries({
    @NamedQuery(name = "Servicios.findAll", query = "SELECT s FROM Servicios s")})
public class Servicios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ser_codigo")
    private Integer serCodigo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ser_valor")
    private BigDecimal serValor;
    @Column(name = "ser_subtotal")
    private BigDecimal serSubtotal;
    @Column(name = "ser_descuento")
    private BigDecimal serDescuento;
    @Column(name = "ser_iva")
    private BigDecimal serIva;
    @Column(name = "ser_total")
    private BigDecimal serTotal;
    @Column(name = "ser_activo")
    private Boolean serActivo;
    @JoinColumn(name = "tas_codigo", referencedColumnName = "tas_codigo")
    @ManyToOne(optional = false)
    private Tasa tasCodigo;
    @JoinColumn(name = "pro_ci", referencedColumnName = "pro_ci")
    @ManyToOne(optional = false)
    private Propietario proCi;

    public Servicios() {
    }

    public Servicios(Integer serCodigo) {
        this.serCodigo = serCodigo;
    }

    public Integer getSerCodigo() {
        return serCodigo;
    }

    public void setSerCodigo(Integer serCodigo) {
        this.serCodigo = serCodigo;
    }

    public BigDecimal getSerValor() {
        return serValor;
    }

    public void setSerValor(BigDecimal serValor) {
        this.serValor = serValor;
    }

    public BigDecimal getSerSubtotal() {
        return serSubtotal;
    }

    public void setSerSubtotal(BigDecimal serSubtotal) {
        this.serSubtotal = serSubtotal;
    }

    public BigDecimal getSerDescuento() {
        return serDescuento;
    }

    public void setSerDescuento(BigDecimal serDescuento) {
        this.serDescuento = serDescuento;
    }

    public BigDecimal getSerIva() {
        return serIva;
    }

    public void setSerIva(BigDecimal serIva) {
        this.serIva = serIva;
    }

    public BigDecimal getSerTotal() {
        return serTotal;
    }

    public void setSerTotal(BigDecimal serTotal) {
        this.serTotal = serTotal;
    }

    public Tasa getTasCodigo() {
        return tasCodigo;
    }

    public void setTasCodigo(Tasa tasCodigo) {
        this.tasCodigo = tasCodigo;
    }

    public Propietario getProCi() {
        return proCi;
    }

    public void setProCi(Propietario proCi) {
        this.proCi = proCi;
    }

    public Boolean isSerActivo() {
        return serActivo;
    }

    public void setSerActivo(Boolean serActivo) {
        this.serActivo = serActivo;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (serCodigo != null ? serCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Servicios)) {
            return false;
        }
        Servicios other = (Servicios) object;
        if ((this.serCodigo == null && other.serCodigo != null) || (this.serCodigo != null && !this.serCodigo.equals(other.serCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sirec.ejb.entidades.Servicios[ serCodigo=" + serCodigo + " ]";
    }
    
}
