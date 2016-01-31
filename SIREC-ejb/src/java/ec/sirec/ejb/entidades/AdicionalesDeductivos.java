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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author DAVID GUAN
 */
@Entity
@Table(name = "sirec.adicionales_deductivos")
@NamedQueries({
    @NamedQuery(name = "AdicionalesDeductivos.findAll", query = "SELECT a FROM AdicionalesDeductivos a")})
public class AdicionalesDeductivos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "adided_codigo")
    private Integer adidedCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "adided_tipo_impuesto")
    private String adidedTipoImpuesto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "adided_tipo")
    private String adidedTipo;
    @Size(max = 2147483647)
    @Column(name = "adided_descripcion")
    private String adidedDescripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "adided_porcentaje")
    private Double adidedPorcentaje;
    @Column(name = "adided_valorfijo")
    private BigDecimal adidedValorfijo;
    @Column(name = "adided_estado")
    private Boolean adidedEstado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "adidedCodigo")
    private List<CpPlusvaliaValoracionExtras> cpPlusvaliaValoracionExtrasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "adidedCodigo")
    private List<PatenteValoracionExtras> patenteValoracionExtrasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "adidedCodigo")
    private List<Patente15xmilValoracionExtras> patente15xmilValoracionExtrasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "adidedCodigo")
    private List<CpValoracionExtras> cpValoracionExtrasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "adidedCodigo")
    private List<CpAlcabalaValoracionExtras> cpAlcabalaValoracionExtrasList;
    @Size(max = 10)
    @Column(name = "adided_nemonico")
    private String adidedNemonico;
    
    public AdicionalesDeductivos() {
    }

    public AdicionalesDeductivos(Integer adidedCodigo) {
        this.adidedCodigo = adidedCodigo;
    }

    public AdicionalesDeductivos(Integer adidedCodigo, String adidedTipoImpuesto, String adidedTipo) {
        this.adidedCodigo = adidedCodigo;
        this.adidedTipoImpuesto = adidedTipoImpuesto;
        this.adidedTipo = adidedTipo;
    }

    public Integer getAdidedCodigo() {
        return adidedCodigo;
    }

    public void setAdidedCodigo(Integer adidedCodigo) {
        this.adidedCodigo = adidedCodigo;
    }

    public String getAdidedTipoImpuesto() {
        return adidedTipoImpuesto;
    }

    public void setAdidedTipoImpuesto(String adidedTipoImpuesto) {
        this.adidedTipoImpuesto = adidedTipoImpuesto;
    }

    public String getAdidedTipo() {
        return adidedTipo;
    }

    public void setAdidedTipo(String adidedTipo) {
        this.adidedTipo = adidedTipo;
    }

    public String getAdidedDescripcion() {
        return adidedDescripcion;
    }

    public void setAdidedDescripcion(String adidedDescripcion) {
        this.adidedDescripcion = adidedDescripcion;
    }

    public Double getAdidedPorcentaje() {
        return adidedPorcentaje;
    }

    public void setAdidedPorcentaje(Double adidedPorcentaje) {
        this.adidedPorcentaje = adidedPorcentaje;
    }

    public BigDecimal getAdidedValorfijo() {
        return adidedValorfijo;
    }

    public void setAdidedValorfijo(BigDecimal adidedValorfijo) {
        this.adidedValorfijo = adidedValorfijo;
    }

    public Boolean getAdidedEstado() {
        return adidedEstado;
    }

    public void setAdidedEstado(Boolean adidedEstado) {
        this.adidedEstado = adidedEstado;
    }

    public List<CpPlusvaliaValoracionExtras> getCpPlusvaliaValoracionExtrasList() {
        return cpPlusvaliaValoracionExtrasList;
    }

    public void setCpPlusvaliaValoracionExtrasList(List<CpPlusvaliaValoracionExtras> cpPlusvaliaValoracionExtrasList) {
        this.cpPlusvaliaValoracionExtrasList = cpPlusvaliaValoracionExtrasList;
    }

    public List<PatenteValoracionExtras> getPatenteValoracionExtrasList() {
        return patenteValoracionExtrasList;
    }

    public void setPatenteValoracionExtrasList(List<PatenteValoracionExtras> patenteValoracionExtrasList) {
        this.patenteValoracionExtrasList = patenteValoracionExtrasList;
    }

    public List<Patente15xmilValoracionExtras> getPatente15xmilValoracionExtrasList() {
        return patente15xmilValoracionExtrasList;
    }

    public void setPatente15xmilValoracionExtrasList(List<Patente15xmilValoracionExtras> patente15xmilValoracionExtrasList) {
        this.patente15xmilValoracionExtrasList = patente15xmilValoracionExtrasList;
    }

    public List<CpValoracionExtras> getCpValoracionExtrasList() {
        return cpValoracionExtrasList;
    }

    public void setCpValoracionExtrasList(List<CpValoracionExtras> cpValoracionExtrasList) {
        this.cpValoracionExtrasList = cpValoracionExtrasList;
    }

    public List<CpAlcabalaValoracionExtras> getCpAlcabalaValoracionExtrasList() {
        return cpAlcabalaValoracionExtrasList;
    }

    public void setCpAlcabalaValoracionExtrasList(List<CpAlcabalaValoracionExtras> cpAlcabalaValoracionExtrasList) {
        this.cpAlcabalaValoracionExtrasList = cpAlcabalaValoracionExtrasList;
    }

    public String getAdidedNemonico() {
        return adidedNemonico;
    }

    public void setAdidedNemonico(String adidedNemonico) {
        this.adidedNemonico = adidedNemonico;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (adidedCodigo != null ? adidedCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdicionalesDeductivos)) {
            return false;
        }
        AdicionalesDeductivos other = (AdicionalesDeductivos) object;
        if ((this.adidedCodigo == null && other.adidedCodigo != null) || (this.adidedCodigo != null && !this.adidedCodigo.equals(other.adidedCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sirec.ejb.entidades.AdicionalesDeductivos[ adidedCodigo=" + adidedCodigo + " ]";
    }
    
}
