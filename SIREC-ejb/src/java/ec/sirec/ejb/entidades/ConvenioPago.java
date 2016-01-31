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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author DAVID GUAN
 */
@Entity
@Table(name = "sirec.convenio_pago")
@NamedQueries({
    @NamedQuery(name = "ConvenioPago.findAll", query = "SELECT c FROM ConvenioPago c")})
public class ConvenioPago implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "conpag_codigo")
    private Integer conpagCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "conpag_fecha")
    @Temporal(TemporalType.DATE)
    private Date conpagFecha;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "conpag_total")
    private BigDecimal conpagTotal;
    @Column(name = "conpag_porcentaje")
    private Integer conpagPorcentaje;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "conpag_estado")
    private String conpagEstado;
    @Column(name = "conpag_saldo")
    private BigDecimal conpagSaldo;
    @Column(name = "conpag_num_cuotas")
    private Integer conpagNumCuotas;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conpagCodigo")
    private List<ConvenioPagoDet> convenioPagoDetList;
    @JoinColumn(name = "usu_identificacion", referencedColumnName = "usu_identificacion")
    @ManyToOne(optional = false)
    private SegUsuario usuIdentificacion;
     @JoinColumn(name = "cxc_codigo", referencedColumnName = "cxc_codigo")
    @ManyToOne(optional = false)
    private CuentaPorCobrar cxcCodigo;

    public ConvenioPago() {
    }

    public ConvenioPago(Integer conpagCodigo) {
        this.conpagCodigo = conpagCodigo;
    }

    public ConvenioPago(Integer conpagCodigo, Date conpagFecha, String conpagEstado) {
        this.conpagCodigo = conpagCodigo;
        this.conpagFecha = conpagFecha;
        this.conpagEstado = conpagEstado;
    }

    public Integer getConpagCodigo() {
        return conpagCodigo;
    }

    public void setConpagCodigo(Integer conpagCodigo) {
        this.conpagCodigo = conpagCodigo;
    }

    public Date getConpagFecha() {
        return conpagFecha;
    }

    public void setConpagFecha(Date conpagFecha) {
        this.conpagFecha = conpagFecha;
    }

    public BigDecimal getConpagTotal() {
        return conpagTotal;
    }

    public void setConpagTotal(BigDecimal conpagTotal) {
        this.conpagTotal = conpagTotal;
    }

    public Integer getConpagPorcentaje() {
        return conpagPorcentaje;
    }

    public void setConpagPorcentaje(Integer conpagPorcentaje) {
        this.conpagPorcentaje = conpagPorcentaje;
    }

    public String getConpagEstado() {
        return conpagEstado;
    }

    public void setConpagEstado(String conpagEstado) {
        this.conpagEstado = conpagEstado;
    }

    public BigDecimal getConpagSaldo() {
        return conpagSaldo;
    }

    public void setConpagSaldo(BigDecimal conpagSaldo) {
        this.conpagSaldo = conpagSaldo;
    }

    public Integer getConpagNumCuotas() {
        return conpagNumCuotas;
    }

    public void setConpagNumCuotas(Integer conpagNumCuotas) {
        this.conpagNumCuotas = conpagNumCuotas;
    }

    public List<ConvenioPagoDet> getConvenioPagoDetList() {
        return convenioPagoDetList;
    }

    public void setConvenioPagoDetList(List<ConvenioPagoDet> convenioPagoDetList) {
        this.convenioPagoDetList = convenioPagoDetList;
    }

    public SegUsuario getUsuIdentificacion() {
        return usuIdentificacion;
    }

    public void setUsuIdentificacion(SegUsuario usuIdentificacion) {
        this.usuIdentificacion = usuIdentificacion;
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
        hash += (conpagCodigo != null ? conpagCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConvenioPago)) {
            return false;
        }
        ConvenioPago other = (ConvenioPago) object;
        if ((this.conpagCodigo == null && other.conpagCodigo != null) || (this.conpagCodigo != null && !this.conpagCodigo.equals(other.conpagCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sirec.ejb.entidades.ConvenioPago[ conpagCodigo=" + conpagCodigo + " ]";
    }
    
}
