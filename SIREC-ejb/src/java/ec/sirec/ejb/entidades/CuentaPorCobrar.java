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
import javax.validation.constraints.Size;

/**
 *
 * @author DAVID GUAN
 */
@Entity
@Table(name = "sirec.cuenta_por_cobrar")
@NamedQueries({
    @NamedQuery(name = "CuentaPorCobrar.findAll", query = "SELECT c FROM CuentaPorCobrar c")})
public class CuentaPorCobrar implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cxc_codigo")
    private Integer cxcCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cxc_anio")
    private int cxcAnio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cxc_mes")
    private int cxcMes;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "cxc_tipo")
    private String cxcTipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "cxc_referencia")
    private String cxcReferencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cxc_fecha")
    @Temporal(TemporalType.DATE)
    private Date cxcFecha;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "cxc_valor_total")
    private BigDecimal cxcValorTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cxc_saldo")
    private BigDecimal cxcSaldo;
    @Column(name = "cxc_fecha_vencimiento")
    @Temporal(TemporalType.DATE)
    private Date cxcFechaVencimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "cxc_estado")
    private String cxcEstado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cxc_cod_ref")
    private int cxcCodRef;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cxc_activo")
    private boolean cxcActivo;
    @JoinColumn(name = "pro_ci", referencedColumnName = "pro_ci")
    @ManyToOne(optional = false)
    private Propietario proCi;

    public CuentaPorCobrar() {
    }

    public CuentaPorCobrar(Integer cxcCodigo) {
        this.cxcCodigo = cxcCodigo;
    }

    public CuentaPorCobrar(Integer cxcCodigo, int cxcAnio, int cxcMes, String cxcTipo, String cxcReferencia, Date cxcFecha, BigDecimal cxcValorTotal, BigDecimal cxcSaldo, String cxcEstado, int cxcCodRef, boolean cxcActivo) {
        this.cxcCodigo = cxcCodigo;
        this.cxcAnio = cxcAnio;
        this.cxcMes = cxcMes;
        this.cxcTipo = cxcTipo;
        this.cxcReferencia = cxcReferencia;
        this.cxcFecha = cxcFecha;
        this.cxcValorTotal = cxcValorTotal;
        this.cxcSaldo = cxcSaldo;
        this.cxcEstado = cxcEstado;
        this.cxcCodRef = cxcCodRef;
        this.cxcActivo = cxcActivo;
    }

    public Integer getCxcCodigo() {
        return cxcCodigo;
    }

    public void setCxcCodigo(Integer cxcCodigo) {
        this.cxcCodigo = cxcCodigo;
    }

    public int getCxcAnio() {
        return cxcAnio;
    }

    public void setCxcAnio(int cxcAnio) {
        this.cxcAnio = cxcAnio;
    }

    public int getCxcMes() {
        return cxcMes;
    }

    public void setCxcMes(int cxcMes) {
        this.cxcMes = cxcMes;
    }

    public String getCxcTipo() {
        return cxcTipo;
    }

    public void setCxcTipo(String cxcTipo) {
        this.cxcTipo = cxcTipo;
    }

    public String getCxcReferencia() {
        return cxcReferencia;
    }

    public void setCxcReferencia(String cxcReferencia) {
        this.cxcReferencia = cxcReferencia;
    }

    public Date getCxcFecha() {
        return cxcFecha;
    }

    public void setCxcFecha(Date cxcFecha) {
        this.cxcFecha = cxcFecha;
    }

    public BigDecimal getCxcValorTotal() {
        return cxcValorTotal;
    }

    public void setCxcValorTotal(BigDecimal cxcValorTotal) {
        this.cxcValorTotal = cxcValorTotal;
    }

    public BigDecimal getCxcSaldo() {
        return cxcSaldo;
    }

    public void setCxcSaldo(BigDecimal cxcSaldo) {
        this.cxcSaldo = cxcSaldo;
    }

    public Date getCxcFechaVencimiento() {
        return cxcFechaVencimiento;
    }

    public void setCxcFechaVencimiento(Date cxcFechaVencimiento) {
        this.cxcFechaVencimiento = cxcFechaVencimiento;
    }

    public String getCxcEstado() {
        return cxcEstado;
    }

    public void setCxcEstado(String cxcEstado) {
        this.cxcEstado = cxcEstado;
    }

    public int getCxcCodRef() {
        return cxcCodRef;
    }

    public void setCxcCodRef(int cxcCodRef) {
        this.cxcCodRef = cxcCodRef;
    }

    public boolean getCxcActivo() {
        return cxcActivo;
    }

    public void setCxcActivo(boolean cxcActivo) {
        this.cxcActivo = cxcActivo;
    }

    public Propietario getProCi() {
        return proCi;
    }

    public void setProCi(Propietario proCi) {
        this.proCi = proCi;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cxcCodigo != null ? cxcCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CuentaPorCobrar)) {
            return false;
        }
        CuentaPorCobrar other = (CuentaPorCobrar) object;
        if ((this.cxcCodigo == null && other.cxcCodigo != null) || (this.cxcCodigo != null && !this.cxcCodigo.equals(other.cxcCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sirec.ejb.entidades.CuentaPorCobrar[ cxcCodigo=" + cxcCodigo + " ]";
    }
    
}
