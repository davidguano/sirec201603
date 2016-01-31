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
import javax.validation.constraints.Size;

/**
 *
 * @author DAVID GUAN
 */
@Entity
@Table(name = "sirec.patente_valoracion_extras")
@NamedQueries({
    @NamedQuery(name = "PatenteValoracionExtras.findAll", query = "SELECT p FROM PatenteValoracionExtras p")})
public class PatenteValoracionExtras implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "patvalext_codigo")
    private Integer patvalextCodigo;
    @Column(name = "patvalext_num_meses_incum")
    private Integer patvalextNumMesesIncum;
    @Column(name = "patvalext_obligado")
    private Boolean patvalextObligado;
    @Column(name = "patvalext_reduccion_mitad")
    private Boolean patvalextReduccionMitad;
    @Column(name = "patvalext_reduccion_3eraparte")
    private Boolean patvalextReduccion3eraparte;
    @Column(name = "patvalext_exon_art_calificado")
    private Boolean patvalextExonArtCalificado;
    @Column(name = "patvalext_incum_plazo_decla")
    private Boolean patvalextIncumPlazoDecla;
    @Column(name = "patente_porc_datosfalsos")
    private Integer patentePorcDatosfalsos;
    @Size(max = 1)
    @Column(name = "patente_evasion_tributaria")
    private String patenteEvasionTributaria;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "patente_porc_ingreso")
    private Double patentePorcIngreso;
    @Column(name = "patente_baseimp_negativa")
    private BigDecimal patenteBaseimpNegativa;
    @JoinColumn(name = "patval_codigo", referencedColumnName = "patval_codigo")
    @ManyToOne(optional = false)
    private PatenteValoracion patvalCodigo;
    @JoinColumn(name = "adided_codigo", referencedColumnName = "adided_codigo")
    @ManyToOne(optional = false)
    private AdicionalesDeductivos adidedCodigo;

    public PatenteValoracionExtras() {
    }

    public PatenteValoracionExtras(Integer patvalextCodigo) {
        this.patvalextCodigo = patvalextCodigo;
    }

    public Integer getPatvalextCodigo() {
        return patvalextCodigo;
    }

    public void setPatvalextCodigo(Integer patvalextCodigo) {
        this.patvalextCodigo = patvalextCodigo;
    }

    public Integer getPatvalextNumMesesIncum() {
        return patvalextNumMesesIncum;
    }

    public void setPatvalextNumMesesIncum(Integer patvalextNumMesesIncum) {
        this.patvalextNumMesesIncum = patvalextNumMesesIncum;
    }

    public Boolean getPatvalextObligado() {
        return patvalextObligado;
    }

    public void setPatvalextObligado(Boolean patvalextObligado) {
        this.patvalextObligado = patvalextObligado;
    }

    public Boolean getPatvalextReduccionMitad() {
        return patvalextReduccionMitad;
    }

    public void setPatvalextReduccionMitad(Boolean patvalextReduccionMitad) {
        this.patvalextReduccionMitad = patvalextReduccionMitad;
    }

    public Boolean getPatvalextReduccion3eraparte() {
        return patvalextReduccion3eraparte;
    }

    public void setPatvalextReduccion3eraparte(Boolean patvalextReduccion3eraparte) {
        this.patvalextReduccion3eraparte = patvalextReduccion3eraparte;
    }

    public Boolean getPatvalextExonArtCalificado() {
        return patvalextExonArtCalificado;
    }

    public void setPatvalextExonArtCalificado(Boolean patvalextExonArtCalificado) {
        this.patvalextExonArtCalificado = patvalextExonArtCalificado;
    }

    public Boolean getPatvalextIncumPlazoDecla() {
        return patvalextIncumPlazoDecla;
    }

    public void setPatvalextIncumPlazoDecla(Boolean patvalextIncumPlazoDecla) {
        this.patvalextIncumPlazoDecla = patvalextIncumPlazoDecla;
    }

    public Integer getPatentePorcDatosfalsos() {
        return patentePorcDatosfalsos;
    }

    public void setPatentePorcDatosfalsos(Integer patentePorcDatosfalsos) {
        this.patentePorcDatosfalsos = patentePorcDatosfalsos;
    }

    public String getPatenteEvasionTributaria() {
        return patenteEvasionTributaria;
    }

    public void setPatenteEvasionTributaria(String patenteEvasionTributaria) {
        this.patenteEvasionTributaria = patenteEvasionTributaria;
    }

    public Double getPatentePorcIngreso() {
        return patentePorcIngreso;
    }

    public void setPatentePorcIngreso(Double patentePorcIngreso) {
        this.patentePorcIngreso = patentePorcIngreso;
    }

    public BigDecimal getPatenteBaseimpNegativa() {
        return patenteBaseimpNegativa;
    }

    public void setPatenteBaseimpNegativa(BigDecimal patenteBaseimpNegativa) {
        this.patenteBaseimpNegativa = patenteBaseimpNegativa;
    }

    public PatenteValoracion getPatvalCodigo() {
        return patvalCodigo;
    }

    public void setPatvalCodigo(PatenteValoracion patvalCodigo) {
        this.patvalCodigo = patvalCodigo;
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
        hash += (patvalextCodigo != null ? patvalextCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatenteValoracionExtras)) {
            return false;
        }
        PatenteValoracionExtras other = (PatenteValoracionExtras) object;
        if ((this.patvalextCodigo == null && other.patvalextCodigo != null) || (this.patvalextCodigo != null && !this.patvalextCodigo.equals(other.patvalextCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sirec.ejb.entidades.PatenteValoracionExtras[ patvalextCodigo=" + patvalextCodigo + " ]";
    }
    
}
