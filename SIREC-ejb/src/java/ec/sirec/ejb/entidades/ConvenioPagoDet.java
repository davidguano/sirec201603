/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.sirec.ejb.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author DAVID GUAN
 */
@Entity
@Table(name = "sirec.convenio_pago_det")
@NamedQueries({
    @NamedQuery(name = "ConvenioPagoDet.findAll", query = "SELECT c FROM ConvenioPagoDet c")})
public class ConvenioPagoDet implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "conpagdet_codigo")
    private Integer conpagdetCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "conpagdet_fecha_pago")
    @Temporal(TemporalType.DATE)
    private Date conpagdetFechaPago;
    @Basic(optional = false)
    @NotNull
    @Column(name = "conpagdet_num_cuota")
    private int conpagdetNumCuota;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "conpagdet_valor_cuota")
    private BigDecimal conpagdetValorCuota;
    @Basic(optional = false)
    @NotNull
    @Column(name = "conpagdet_tasa_interes")
    private double conpagdetTasaInteres;
    @Basic(optional = false)
    @NotNull
    @Column(name = "conpagdet_valor_interes")
    private BigDecimal conpagdetValorInteres;
    @Basic(optional = false)
    @NotNull
    @Column(name = "conpagdet_valor")
    private BigDecimal conpagdetValor;
    @JoinColumn(name = "conpag_codigo", referencedColumnName = "conpag_codigo")
    @ManyToOne(optional = false)
    private ConvenioPago conpagCodigo;

    public ConvenioPagoDet() {
    }

    public ConvenioPagoDet(Integer conpagdetCodigo) {
        this.conpagdetCodigo = conpagdetCodigo;
    }

    public ConvenioPagoDet(Integer conpagdetCodigo, Date conpagdetFechaPago, int conpagdetNumCuota, BigDecimal conpagdetValorCuota, double conpagdetTasaInteres, BigDecimal conpagdetValorInteres, BigDecimal conpagdetValor) {
        this.conpagdetCodigo = conpagdetCodigo;
        this.conpagdetFechaPago = conpagdetFechaPago;
        this.conpagdetNumCuota = conpagdetNumCuota;
        this.conpagdetValorCuota = conpagdetValorCuota;
        this.conpagdetTasaInteres = conpagdetTasaInteres;
        this.conpagdetValorInteres = conpagdetValorInteres;
        this.conpagdetValor = conpagdetValor;
    }

    public Integer getConpagdetCodigo() {
        return conpagdetCodigo;
    }

    public void setConpagdetCodigo(Integer conpagdetCodigo) {
        this.conpagdetCodigo = conpagdetCodigo;
    }

    public Date getConpagdetFechaPago() {
        return conpagdetFechaPago;
    }

    public void setConpagdetFechaPago(Date conpagdetFechaPago) {
        this.conpagdetFechaPago = conpagdetFechaPago;
    }

    public int getConpagdetNumCuota() {
        return conpagdetNumCuota;
    }

    public void setConpagdetNumCuota(int conpagdetNumCuota) {
        this.conpagdetNumCuota = conpagdetNumCuota;
    }

    public BigDecimal getConpagdetValorCuota() {
        return conpagdetValorCuota;
    }

    public void setConpagdetValorCuota(BigDecimal conpagdetValorCuota) {
        this.conpagdetValorCuota = conpagdetValorCuota;
    }

    public double getConpagdetTasaInteres() {
        return conpagdetTasaInteres;
    }

    public void setConpagdetTasaInteres(double conpagdetTasaInteres) {
        this.conpagdetTasaInteres = conpagdetTasaInteres;
    }

    public BigDecimal getConpagdetValorInteres() {
        return conpagdetValorInteres;
    }

    public void setConpagdetValorInteres(BigDecimal conpagdetValorInteres) {
        this.conpagdetValorInteres = conpagdetValorInteres;
    }

    public BigDecimal getConpagdetValor() {
        return conpagdetValor;
    }

    public void setConpagdetValor(BigDecimal conpagdetValor) {
        this.conpagdetValor = conpagdetValor;
    }

    public ConvenioPago getConpagCodigo() {
        return conpagCodigo;
    }

    public void setConpagCodigo(ConvenioPago conpagCodigo) {
        this.conpagCodigo = conpagCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (conpagdetCodigo != null ? conpagdetCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConvenioPagoDet)) {
            return false;
        }
        ConvenioPagoDet other = (ConvenioPagoDet) object;
        if ((this.conpagdetCodigo == null && other.conpagdetCodigo != null) || (this.conpagdetCodigo != null && !this.conpagdetCodigo.equals(other.conpagdetCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sirec.ejb.entidades.ConvenioPagoDet[ conpagdetCodigo=" + conpagdetCodigo + " ]";
    }
    
}
