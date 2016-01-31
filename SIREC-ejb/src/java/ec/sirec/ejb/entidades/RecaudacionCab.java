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
@Table(name = "sirec.recaudacion_cab")
@NamedQueries({
    @NamedQuery(name = "RecaudacionCab.findAll", query = "SELECT r FROM RecaudacionCab r")})
public class RecaudacionCab implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rec_codigo")
    private Integer recCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rec_fecha")
    @Temporal(TemporalType.DATE)
    private Date recFecha;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "rec_total")
    private BigDecimal recTotal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "rec_estado")
    private String recEstado;
    @Column(name = "rec_fecha_emision")
    @Temporal(TemporalType.DATE)
    private Date recFechaEmision;
    @Column(name = "rec_fecha_pago")
    @Temporal(TemporalType.DATE)
    private Date recFechaPago;
    @JoinColumn(name = "usu_identificacion", referencedColumnName = "usu_identificacion")
    @ManyToOne(optional = false)
    private SegUsuario usuIdentificacion;
    @JoinColumn(name = "pro_ci", referencedColumnName = "pro_ci")
    @ManyToOne(optional = false)
    private Propietario proCi;

    public RecaudacionCab() {
        proCi=new Propietario();
    }

    public RecaudacionCab(Integer recCodigo) {
        this.recCodigo = recCodigo;
    }

    public RecaudacionCab(Integer recCodigo, Date recFecha, BigDecimal recTotal, String recEstado) {
        this.recCodigo = recCodigo;
        this.recFecha = recFecha;
        this.recTotal = recTotal;
        this.recEstado = recEstado;
    }

    public Integer getRecCodigo() {
        return recCodigo;
    }

    public void setRecCodigo(Integer recCodigo) {
        this.recCodigo = recCodigo;
    }

    public Date getRecFecha() {
        return recFecha;
    }

    public void setRecFecha(Date recFecha) {
        this.recFecha = recFecha;
    }

    public BigDecimal getRecTotal() {
        return recTotal;
    }

    public void setRecTotal(BigDecimal recTotal) {
        this.recTotal = recTotal;
    }

    public String getRecEstado() {
        return recEstado;
    }

    public void setRecEstado(String recEstado) {
        this.recEstado = recEstado;
    }

    public SegUsuario getUsuIdentificacion() {
        return usuIdentificacion;
    }

    public void setUsuIdentificacion(SegUsuario usuIdentificacion) {
        this.usuIdentificacion = usuIdentificacion;
    }

    public Propietario getProCi() {
        return proCi;
    }

    public void setProCi(Propietario proCi) {
        this.proCi = proCi;
    }

    public Date getRecFechaEmision() {
        return recFechaEmision;
    }

    public void setRecFechaEmision(Date recFechaEmision) {
        this.recFechaEmision = recFechaEmision;
    }

    public Date getRecFechaPago() {
        return recFechaPago;
    }

    public void setRecFechaPago(Date recFechaPago) {
        this.recFechaPago = recFechaPago;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recCodigo != null ? recCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecaudacionCab)) {
            return false;
        }
        RecaudacionCab other = (RecaudacionCab) object;
        if ((this.recCodigo == null && other.recCodigo != null) || (this.recCodigo != null && !this.recCodigo.equals(other.recCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sirec.ejb.entidades.RecaudacionCab[ recCodigo=" + recCodigo + " ]";
    }
    
}
