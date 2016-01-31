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

/**
 *
 * @author DAVID GUAN
 */
@Entity
@Table(name = "sirec.catastro_predial_valoracion")
@NamedQueries({
    @NamedQuery(name = "CatastroPredialValoracion.findAll", query = "SELECT c FROM CatastroPredialValoracion c")})
public class CatastroPredialValoracion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "catpreval_codigo")
    private Integer catprevalCodigo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "catpreval_avaluo_edif")
    private BigDecimal catprevalAvaluoEdif;
    @Column(name = "catpreval_avaluo_terr")
    private BigDecimal catprevalAvaluoTerr;
    @Column(name = "catpreval_avaluo_tot")
    private BigDecimal catprevalAvaluoTot;
    @Column(name = "catpreval_valor_propiedad")
    private BigDecimal catprevalValorPropieda;
    @Column(name = "catpreval_base_imponible")
    private BigDecimal catprevalBaseImponible;
    @Column(name = "catpreval_impuesto")
    private BigDecimal catprevalImpuesto;
    @Column(name = "catpreval_bomberos")
    private BigDecimal catprevalBomberos;
    @Column(name = "catpreval_solar_noedificado")
    private BigDecimal catprevalSolarNoedificado;
    @Column(name = "catpreval_tasa_adm")
    private BigDecimal catprevalTasaAdm;
    @Column(name = "catpreval_anio")
    private Integer catprevalAnio;
    @Column(name = "catpreval_activo")
    private Boolean catprevalActivo;
    @JoinColumn(name = "catpre_codigo", referencedColumnName = "catpre_codigo")
    @ManyToOne(optional = false)
    private CatastroPredial catpreCodigo;
    

    public CatastroPredialValoracion() {
        catprevalActivo=false;
    }

    public CatastroPredialValoracion(Integer catprevalCodigo) {
        this.catprevalCodigo = catprevalCodigo;
    }

    public Integer getCatprevalCodigo() {
        return catprevalCodigo;
    }

    public void setCatprevalCodigo(Integer catprevalCodigo) {
        this.catprevalCodigo = catprevalCodigo;
    }

    public BigDecimal getCatprevalAvaluoEdif() {
        return catprevalAvaluoEdif;
    }

    public void setCatprevalAvaluoEdif(BigDecimal catprevalAvaluoEdif) {
        this.catprevalAvaluoEdif = catprevalAvaluoEdif;
    }

    public BigDecimal getCatprevalAvaluoTerr() {
        return catprevalAvaluoTerr;
    }

    public void setCatprevalAvaluoTerr(BigDecimal catprevalAvaluoTerr) {
        this.catprevalAvaluoTerr = catprevalAvaluoTerr;
    }

    public BigDecimal getCatprevalAvaluoTot() {
        return catprevalAvaluoTot;
    }

    public void setCatprevalAvaluoTot(BigDecimal catprevalAvaluoTot) {
        this.catprevalAvaluoTot = catprevalAvaluoTot;
    }

    public CatastroPredial getCatpreCodigo() {
        return catpreCodigo;
    }

    public void setCatpreCodigo(CatastroPredial catpreCodigo) {
        this.catpreCodigo = catpreCodigo;
    }

   

    public BigDecimal getCatprevalValorPropieda() {
        return catprevalValorPropieda;
    }

    public void setCatprevalValorPropieda(BigDecimal catprevalValorPropieda) {
        this.catprevalValorPropieda = catprevalValorPropieda;
    }

    public BigDecimal getCatprevalBaseImponible() {
        return catprevalBaseImponible;
    }

    public void setCatprevalBaseImponible(BigDecimal catprevalBaseImponible) {
        this.catprevalBaseImponible = catprevalBaseImponible;
    }

    public BigDecimal getCatprevalImpuesto() {
        return catprevalImpuesto;
    }

    public void setCatprevalImpuesto(BigDecimal catprevalImpuesto) {
        this.catprevalImpuesto = catprevalImpuesto;
    }

    public BigDecimal getCatprevalBomberos() {
        return catprevalBomberos;
    }

    public void setCatprevalBomberos(BigDecimal catprevalBomberos) {
        this.catprevalBomberos = catprevalBomberos;
    }

    public BigDecimal getCatprevalSolarNoedificado() {
        return catprevalSolarNoedificado;
    }

    public void setCatprevalSolarNoedificado(BigDecimal catprevalSolarNoedificado) {
        this.catprevalSolarNoedificado = catprevalSolarNoedificado;
    }

    public BigDecimal getCatprevalTasaAdm() {
        return catprevalTasaAdm;
    }

    public void setCatprevalTasaAdm(BigDecimal catprevalTasaAdm) {
        this.catprevalTasaAdm = catprevalTasaAdm;
    }

    public Integer getCatprevalAnio() {
        return catprevalAnio;
    }

    public void setCatprevalAnio(Integer catprevalAnio) {
        this.catprevalAnio = catprevalAnio;
    }

    public Boolean isCatprevalActivo() {
        return catprevalActivo;
    }

    public void setCatprevalActivo(Boolean catprevalActivo) {
        this.catprevalActivo = catprevalActivo;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (catprevalCodigo != null ? catprevalCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatastroPredialValoracion)) {
            return false;
        }
        CatastroPredialValoracion other = (CatastroPredialValoracion) object;
        if ((this.catprevalCodigo == null && other.catprevalCodigo != null) || (this.catprevalCodigo != null && !this.catprevalCodigo.equals(other.catprevalCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sirec.ejb.entidades.CatastroPredialValoracion[ catprevalCodigo=" + catprevalCodigo + " ]";
    }
    
}
