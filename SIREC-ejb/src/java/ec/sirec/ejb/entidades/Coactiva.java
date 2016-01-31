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
@Table(name = "sirec.coactiva")
@NamedQueries({
    @NamedQuery(name = "Coactiva.findAll", query = "SELECT c FROM Coactiva c")})
public class Coactiva implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "coa_codigo")
    private Integer coaCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "coa_fecha")
    @Temporal(TemporalType.DATE)
    private Date coaFecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "coa_anio")
    private int coaAnio;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "coa_total_deuda")
    private BigDecimal coaTotalDeuda;
    @Column(name = "coa_notificacion1")
    private Boolean coaNotificacion1;
    @Column(name = "coa_notificacion2")
    private Boolean coaNotificacion2;
    @Column(name = "coa_notificacion3")
    private Boolean coaNotificacion3;
    @Column(name = "coa_notificacion")
    private Boolean coaNotificacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "coa_estado")
    private String coaEstado;
    @JoinColumn(name = "usu_identificacion", referencedColumnName = "usu_identificacion")
    @ManyToOne(optional = false)
    private SegUsuario usuIdentificacion;
     @JoinColumn(name = "cxc_codigo", referencedColumnName = "cxc_codigo")
    @ManyToOne(optional = false)
    private CuentaPorCobrar cxcCodigo;

    public Coactiva() {
    }

    public Coactiva(Integer coaCodigo) {
        this.coaCodigo = coaCodigo;
    }

    public Coactiva(Integer coaCodigo, Date coaFecha, int coaAnio, String coaEstado) {
        this.coaCodigo = coaCodigo;
        this.coaFecha = coaFecha;
        this.coaAnio = coaAnio;
        this.coaEstado = coaEstado;
    }

    public Integer getCoaCodigo() {
        return coaCodigo;
    }

    public void setCoaCodigo(Integer coaCodigo) {
        this.coaCodigo = coaCodigo;
    }

    public Date getCoaFecha() {
        return coaFecha;
    }

    public void setCoaFecha(Date coaFecha) {
        this.coaFecha = coaFecha;
    }

    public int getCoaAnio() {
        return coaAnio;
    }

    public void setCoaAnio(int coaAnio) {
        this.coaAnio = coaAnio;
    }

    public BigDecimal getCoaTotalDeuda() {
        return coaTotalDeuda;
    }

    public void setCoaTotalDeuda(BigDecimal coaTotalDeuda) {
        this.coaTotalDeuda = coaTotalDeuda;
    }

    public Boolean getCoaNotificacion1() {
        return coaNotificacion1;
    }

    public void setCoaNotificacion1(Boolean coaNotificacion1) {
        this.coaNotificacion1 = coaNotificacion1;
    }

    public Boolean getCoaNotificacion2() {
        return coaNotificacion2;
    }

    public void setCoaNotificacion2(Boolean coaNotificacion2) {
        this.coaNotificacion2 = coaNotificacion2;
    }

    public Boolean getCoaNotificacion3() {
        return coaNotificacion3;
    }

    public void setCoaNotificacion3(Boolean coaNotificacion3) {
        this.coaNotificacion3 = coaNotificacion3;
    }

    public Boolean getCoaNotificacion() {
        return coaNotificacion;
    }

    public void setCoaNotificacion(Boolean coaNotificacion) {
        this.coaNotificacion = coaNotificacion;
    }

    public String getCoaEstado() {
        return coaEstado;
    }

    public void setCoaEstado(String coaEstado) {
        this.coaEstado = coaEstado;
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
        hash += (coaCodigo != null ? coaCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Coactiva)) {
            return false;
        }
        Coactiva other = (Coactiva) object;
        if ((this.coaCodigo == null && other.coaCodigo != null) || (this.coaCodigo != null && !this.coaCodigo.equals(other.coaCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sirec.ejb.entidades.Coactiva[ coaCodigo=" + coaCodigo + " ]";
    }
    
}
