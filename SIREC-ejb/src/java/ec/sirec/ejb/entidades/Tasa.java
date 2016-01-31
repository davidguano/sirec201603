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
import javax.validation.constraints.Size;

/**
 *
 * @author DAVID GUAN
 */
@Entity
@Table(name = "sirec.tasa")
@NamedQueries({
    @NamedQuery(name = "Tasa.findAll", query = "SELECT t FROM Tasa t")})
public class Tasa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tas_codigo")
    private Integer tasCodigo;
    @Size(max = 2147483647)
    @Column(name = "tas_descripcion")
    private String tasDescripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "tas_valor")
    private BigDecimal tasValor;
    @Column(name = "tas_con_iva")
    private Boolean tasConIva;
    @Size(max = 2147483647)
    @Column(name = "tas_observaciones")
    private String tasObservaciones;
    @JoinColumn(name = "catdet_tipo_valor", referencedColumnName = "catdet_codigo")
    @ManyToOne(optional = false)
    private CatalogoDetalle catdetTipoValor;
    @JoinColumn(name = "catdet_departamento", referencedColumnName = "catdet_codigo")
    @ManyToOne(optional = false)
    private CatalogoDetalle catdetDepartamento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tasCodigo")
    private List<Servicios> serviciosList;

    public Tasa() {
    }

    public Tasa(Integer tasCodigo) {
        this.tasCodigo = tasCodigo;
    }

    public Integer getTasCodigo() {
        return tasCodigo;
    }

    public void setTasCodigo(Integer tasCodigo) {
        this.tasCodigo = tasCodigo;
    }

    public String getTasDescripcion() {
        return tasDescripcion;
    }

    public void setTasDescripcion(String tasDescripcion) {
        this.tasDescripcion = tasDescripcion;
    }

    public BigDecimal getTasValor() {
        return tasValor;
    }

    public void setTasValor(BigDecimal tasValor) {
        this.tasValor = tasValor;
    }

    public Boolean getTasConIva() {
        return tasConIva;
    }

    public void setTasConIva(Boolean tasConIva) {
        this.tasConIva = tasConIva;
    }

    public String getTasObservaciones() {
        return tasObservaciones;
    }

    public void setTasObservaciones(String tasObservaciones) {
        this.tasObservaciones = tasObservaciones;
    }

    public CatalogoDetalle getCatdetTipoValor() {
        return catdetTipoValor;
    }

    public void setCatdetTipoValor(CatalogoDetalle catdetTipoValor) {
        this.catdetTipoValor = catdetTipoValor;
    }

    public CatalogoDetalle getCatdetDepartamento() {
        return catdetDepartamento;
    }

    public void setCatdetDepartamento(CatalogoDetalle catdetDepartamento) {
        this.catdetDepartamento = catdetDepartamento;
    }

    public List<Servicios> getServiciosList() {
        return serviciosList;
    }

    public void setServiciosList(List<Servicios> serviciosList) {
        this.serviciosList = serviciosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tasCodigo != null ? tasCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tasa)) {
            return false;
        }
        Tasa other = (Tasa) object;
        if ((this.tasCodigo == null && other.tasCodigo != null) || (this.tasCodigo != null && !this.tasCodigo.equals(other.tasCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sirec.ejb.entidades.Tasa[ tasCodigo=" + tasCodigo + " ]";
    }
    
}
