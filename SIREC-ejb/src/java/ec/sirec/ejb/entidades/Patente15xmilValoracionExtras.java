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
import javax.validation.constraints.NotNull;

/**
 *
 * @author DAVID GUAN
 */
@Entity
@Table(name = "sirec.patente_15xmil_valoracion_extras")
@NamedQueries({
    @NamedQuery(name = "Patente15xmilValoracionExtras.findAll", query = "SELECT p FROM Patente15xmilValoracionExtras p")})
public class Patente15xmilValoracionExtras implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pat15valext_codigo")
    private Integer pat15valextCodigo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "pat15valext_base")
    private BigDecimal pat15valextBase;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pat15valext_valor")
    private BigDecimal pat15valextValor;
    @JoinColumn(name = "pat15val_codigo", referencedColumnName = "pat15val_codigo")
    @ManyToOne(optional = false)
    private Patente15xmilValoracion pat15valCodigo;
    @JoinColumn(name = "adided_codigo", referencedColumnName = "adided_codigo")
    @ManyToOne(optional = false)    
    private AdicionalesDeductivos adidedCodigo;
   //Nuevos Cmpos Adicionales 
    @Column(name = "pat15valext_enti_pub")
    private Boolean pat15valextEntiPub;
    @Column(name = "pat15valext_fun_ben_edu")
    private Boolean pat15valextFunBenEdu;
    @Column(name = "pat15valext_ley_fom_artes")
    private Boolean pat15valextLeyFomArtes;
    @Column(name = "pat15valext_act_agro")
    private Boolean pat15valextActAgro;
    @Column(name = "pat15valext_coop")
    private Boolean pat15valextCoop;
    @Column(name = "pat15valext_multi_nac")
    private Boolean pat15valextMultiNac;
   @Column(name = "pat15val_num_meses_incum")
    private Integer pat15valNumMesesIncum;
   @Column(name = "pat15val_evalua_dat_falsos")
    private Integer pat15valEvaluaDatFalsos;
   @Column(name = "pat15val_proceso_liquidacion")
    private Integer pat15valProcesoLiquidacion;
   
   
    public Patente15xmilValoracionExtras() {
    }

    public Patente15xmilValoracionExtras(Integer pat15valextCodigo) {
        this.pat15valextCodigo = pat15valextCodigo;
    }

    public Patente15xmilValoracionExtras(Integer pat15valextCodigo, BigDecimal pat15valextBase, BigDecimal pat15valextValor) {
        this.pat15valextCodigo = pat15valextCodigo;
        this.pat15valextBase = pat15valextBase;
        this.pat15valextValor = pat15valextValor;
    }

    public Integer getPat15valextCodigo() {
        return pat15valextCodigo;
    }

    public void setPat15valextCodigo(Integer pat15valextCodigo) {
        this.pat15valextCodigo = pat15valextCodigo;
    }

    public BigDecimal getPat15valextBase() {
        return pat15valextBase;
    }

    public void setPat15valextBase(BigDecimal pat15valextBase) {
        this.pat15valextBase = pat15valextBase;
    }

    public BigDecimal getPat15valextValor() {
        return pat15valextValor;
    }

    public void setPat15valextValor(BigDecimal pat15valextValor) {
        this.pat15valextValor = pat15valextValor;
    }

    public Patente15xmilValoracion getPat15valCodigo() {
        return pat15valCodigo;
    }

    public void setPat15valCodigo(Patente15xmilValoracion pat15valCodigo) {
        this.pat15valCodigo = pat15valCodigo;
    }

    public AdicionalesDeductivos getAdidedCodigo() {
        return adidedCodigo;
    }

    public void setAdidedCodigo(AdicionalesDeductivos adidedCodigo) {
        this.adidedCodigo = adidedCodigo;
    }

    public Boolean getPat15valextEntiPub() {
        return pat15valextEntiPub;
    }

    public void setPat15valextEntiPub(Boolean pat15valextEntiPub) {
        this.pat15valextEntiPub = pat15valextEntiPub;
    }

    public Boolean getPat15valextFunBenEdu() {
        return pat15valextFunBenEdu;
    }

    public void setPat15valextFunBenEdu(Boolean pat15valextFunBenEdu) {
        this.pat15valextFunBenEdu = pat15valextFunBenEdu;
    }

    public Boolean getPat15valextLeyFomArtes() {
        return pat15valextLeyFomArtes;
    }

    public void setPat15valextLeyFomArtes(Boolean pat15valextLeyFomArtes) {
        this.pat15valextLeyFomArtes = pat15valextLeyFomArtes;
    }

    public Boolean getPat15valextActAgro() {
        return pat15valextActAgro;
    }

    public void setPat15valextActAgro(Boolean pat15valextActAgro) {
        this.pat15valextActAgro = pat15valextActAgro;
    }

    public Boolean getPat15valextMultiNac() {
        return pat15valextMultiNac;
    }

    public void setPat15valextMultiNac(Boolean pat15valextMultiNac) {
        this.pat15valextMultiNac = pat15valextMultiNac;
    }
 
    public Boolean getPat15valextCoop() {
        return pat15valextCoop;
    }

    public void setPat15valextCoop(Boolean pat15valextCoop) {
        this.pat15valextCoop = pat15valextCoop;
    }

    public Integer getPat15valNumMesesIncum() {
        return pat15valNumMesesIncum;
    }

    public void setPat15valNumMesesIncum(Integer pat15valNumMesesIncum) {
        this.pat15valNumMesesIncum = pat15valNumMesesIncum;
    }

    public Integer getPat15valEvaluaDatFalsos() {
        return pat15valEvaluaDatFalsos;
    }

    public void setPat15valEvaluaDatFalsos(Integer pat15valEvaluaDatFalsos) {
        this.pat15valEvaluaDatFalsos = pat15valEvaluaDatFalsos;
    }

    public Integer getPat15valProcesoLiquidacion() {
        return pat15valProcesoLiquidacion;
    }

    public void setPat15valProcesoLiquidacion(Integer pat15valProcesoLiquidacion) {
        this.pat15valProcesoLiquidacion = pat15valProcesoLiquidacion;
    }

     

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pat15valextCodigo != null ? pat15valextCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Patente15xmilValoracionExtras)) {
            return false;
        }
        Patente15xmilValoracionExtras other = (Patente15xmilValoracionExtras) object;
        if ((this.pat15valextCodigo == null && other.pat15valextCodigo != null) || (this.pat15valextCodigo != null && !this.pat15valextCodigo.equals(other.pat15valextCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sirec.ejb.entidades.Patente15xmilValoracionExtras[ pat15valextCodigo=" + pat15valextCodigo + " ]";
    }
    
}
