/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.sirec.ejb.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.NotNull;

/**
 *
 * @author DAVID GUAN
 */
@Entity
@Table(name = "sirec.patente_15xmil_valoracion")
@NamedQueries({
    @NamedQuery(name = "Patente15xmilValoracion.findAll", query = "SELECT p FROM Patente15xmilValoracion p")})
public class Patente15xmilValoracion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pat15val_codigo")
    private Integer pat15valCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pat15val_anio_decla")
    private int pat15valAnioDecla;
    @Column(name = "pat15val_num_sucursales")
    private Integer pat15valNumSucursales;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pat15val_anio_balance")
    private int pat15valAnioBalance;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "pat15val_ingreso_anual")
    private BigDecimal pat15valIngresoAnual;
    @Column(name = "pat15val_activos")
    private BigDecimal pat15valActivos;
    @Column(name = "pat15val_pasivos_corriente")
    private BigDecimal pat15valPasivosCorriente;
    @Column(name = "pat15val_pasivos_conting")
    private BigDecimal pat15valPasivosConting;
    @Column(name = "pat15val_otras_deducciones")
    private BigDecimal pat15valOtrasDeducciones;
    @Column(name = "pat15val_base_imponible")
    private BigDecimal pat15valBaseImponible;
    @Column(name = "pat15val_tasa_proc")
    private BigDecimal pat15valTasaProc;
    @Column(name = "pat15val_impuesto")
    private BigDecimal pat15valImpuesto;
    @Column(name = "pat15val_subtotal")
    private BigDecimal pat15valSubtotal;
    @Column(name = "pat15val_recargos")
    private BigDecimal pat15valRecargos;
    @Column(name = "pat15val_total")
    private BigDecimal pat15valTotal;
    @Column(name = "pat15val_activo")
    private Boolean pat15valActivo;
    @JoinColumn(name = "pat_codigo", referencedColumnName = "pat_codigo")
    @ManyToOne(optional = false)
    private Patente patCodigo;

    public Patente15xmilValoracion() {
    }

    public Patente15xmilValoracion(Integer pat15valCodigo) {
        this.pat15valCodigo = pat15valCodigo;
    }

    public Patente15xmilValoracion(Integer pat15valCodigo, int pat15valAnioDecla, int pat15valAnioBalance) {
        this.pat15valCodigo = pat15valCodigo;
        this.pat15valAnioDecla = pat15valAnioDecla;
        this.pat15valAnioBalance = pat15valAnioBalance;
    }

    public Integer getPat15valCodigo() {
        return pat15valCodigo;
    }

    public void setPat15valCodigo(Integer pat15valCodigo) {
        this.pat15valCodigo = pat15valCodigo;
    }

    public int getPat15valAnioDecla() {
        return pat15valAnioDecla;
    }

    public void setPat15valAnioDecla(int pat15valAnioDecla) {
        this.pat15valAnioDecla = pat15valAnioDecla;
    }

    public Integer getPat15valNumSucursales() {
        return pat15valNumSucursales;
    }

    public void setPat15valNumSucursales(Integer pat15valNumSucursales) {
        this.pat15valNumSucursales = pat15valNumSucursales;
    }

    public int getPat15valAnioBalance() {
        return pat15valAnioBalance;
    }

    public void setPat15valAnioBalance(int pat15valAnioBalance) {
        this.pat15valAnioBalance = pat15valAnioBalance;
    }

    public BigDecimal getPat15valIngresoAnual() {
        return pat15valIngresoAnual;
    }

    public void setPat15valIngresoAnual(BigDecimal pat15valIngresoAnual) {
        this.pat15valIngresoAnual = pat15valIngresoAnual;
    }

    public BigDecimal getPat15valActivos() {
        return pat15valActivos;
    }

    public void setPat15valActivos(BigDecimal pat15valActivos) {
        this.pat15valActivos = pat15valActivos;
    }

    public BigDecimal getPat15valPasivosCorriente() {
        return pat15valPasivosCorriente;
    }

    public void setPat15valPasivosCorriente(BigDecimal pat15valPasivosCorriente) {
        this.pat15valPasivosCorriente = pat15valPasivosCorriente;
    }

    public BigDecimal getPat15valPasivosConting() {
        return pat15valPasivosConting;
    }

    public void setPat15valPasivosConting(BigDecimal pat15valPasivosConting) {
        this.pat15valPasivosConting = pat15valPasivosConting;
    }

    public BigDecimal getPat15valOtrasDeducciones() {
        return pat15valOtrasDeducciones;
    }

    public void setPat15valOtrasDeducciones(BigDecimal pat15valOtrasDeducciones) {
        this.pat15valOtrasDeducciones = pat15valOtrasDeducciones;
    }

    public BigDecimal getPat15valBaseImponible() {
        return pat15valBaseImponible;
    }

    public void setPat15valBaseImponible(BigDecimal pat15valBaseImponible) {
        this.pat15valBaseImponible = pat15valBaseImponible;
    }

    public BigDecimal getPat15valTasaProc() {
        return pat15valTasaProc;
    }

    public void setPat15valTasaProc(BigDecimal pat15valTasaProc) {
        this.pat15valTasaProc = pat15valTasaProc;
    }

    public BigDecimal getPat15valImpuesto() {
        return pat15valImpuesto;
    }

    public void setPat15valImpuesto(BigDecimal pat15valImpuesto) {
        this.pat15valImpuesto = pat15valImpuesto;
    }

    public BigDecimal getPat15valSubtotal() {
        return pat15valSubtotal;
    }

    public void setPat15valSubtotal(BigDecimal pat15valSubtotal) {
        this.pat15valSubtotal = pat15valSubtotal;
    }

    public BigDecimal getPat15valRecargos() {
        return pat15valRecargos;
    }

    public void setPat15valRecargos(BigDecimal pat15valRecargos) {
        this.pat15valRecargos = pat15valRecargos;
    }

    public BigDecimal getPat15valTotal() {
        return pat15valTotal;
    }

    public void setPat15valTotal(BigDecimal pat15valTotal) {
        this.pat15valTotal = pat15valTotal;
    }

    public Patente getPatCodigo() {
        return patCodigo;
    }

    public void setPatCodigo(Patente patCodigo) {
        this.patCodigo = patCodigo;
    }

    public Boolean isPat15valActivo() {
        return pat15valActivo;
    }

    public void setPat15valActivo(Boolean pat15valActivo) {
        this.pat15valActivo = pat15valActivo;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pat15valCodigo != null ? pat15valCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Patente15xmilValoracion)) {
            return false;
        }
        Patente15xmilValoracion other = (Patente15xmilValoracion) object;
        if ((this.pat15valCodigo == null && other.pat15valCodigo != null) || (this.pat15valCodigo != null && !this.pat15valCodigo.equals(other.pat15valCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sirec.ejb.entidades.Patente15xmilValoracion[ pat15valCodigo=" + pat15valCodigo + " ]";
    }
    
}
