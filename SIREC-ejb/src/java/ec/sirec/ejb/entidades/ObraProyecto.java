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
@Table(name = "sirec.obra_proyecto")
@NamedQueries({
    @NamedQuery(name = "ObraProyecto.findAll", query = "SELECT o FROM ObraProyecto o")})
public class ObraProyecto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "obr_codigo")
    private Integer obrCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "obr_descripcion")
    private String obrDescripcion;
    @Column(name = "obr_fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date obrFechaInicio;
    @Column(name = "obr_fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date obrFechaFin;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "obr_viales")
    private BigDecimal obrViales;
    @Column(name = "obr_aceras_bordillos")
    private BigDecimal obrAcerasBordillos;
    @Column(name = "obr_servicio")
    private BigDecimal obrServicio;
    @Column(name = "obr_inf_urbana")
    private BigDecimal obrInfUrbana;
    @Column(name = "obr_desecacion_rellenos")
    private BigDecimal obrDesecacionRellenos;
    @Column(name = "obr_total")
    private BigDecimal obrTotal;
    @JoinColumn(name = "con_codigo", referencedColumnName = "con_codigo")
    @ManyToOne(optional = false)
    private Constructora conCodigo;
    @JoinColumn(name = "catdet_estado", referencedColumnName = "catdet_codigo")
    @ManyToOne(optional = false)
    private CatalogoDetalle catdetEstado;
    @JoinColumn(name = "catdet_ejecucion", referencedColumnName = "catdet_codigo")
    @ManyToOne(optional = false)
    private CatalogoDetalle catdetEjecucion;
    @JoinColumn(name = "catdet_parroquia", referencedColumnName = "catdet_codigo")
    @ManyToOne(optional = false)
    private CatalogoDetalle catdetParroquia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "obrCodigo")
    private List<Mejora> mejoraList;

    public ObraProyecto() {
    }

    public ObraProyecto(Integer obrCodigo) {
        this.obrCodigo = obrCodigo;
    }

    public ObraProyecto(Integer obrCodigo, String obrDescripcion) {
        this.obrCodigo = obrCodigo;
        this.obrDescripcion = obrDescripcion;
    }

    public Integer getObrCodigo() {
        return obrCodigo;
    }

    public void setObrCodigo(Integer obrCodigo) {
        this.obrCodigo = obrCodigo;
    }

    public String getObrDescripcion() {
        return obrDescripcion;
    }

    public void setObrDescripcion(String obrDescripcion) {
        this.obrDescripcion = obrDescripcion;
    }

    public Date getObrFechaInicio() {
        return obrFechaInicio;
    }

    public void setObrFechaInicio(Date obrFechaInicio) {
        this.obrFechaInicio = obrFechaInicio;
    }

    public Date getObrFechaFin() {
        return obrFechaFin;
    }

    public void setObrFechaFin(Date obrFechaFin) {
        this.obrFechaFin = obrFechaFin;
    }

    public BigDecimal getObrViales() {
        return obrViales;
    }

    public void setObrViales(BigDecimal obrViales) {
        this.obrViales = obrViales;
    }

    public BigDecimal getObrAcerasBordillos() {
        return obrAcerasBordillos;
    }

    public void setObrAcerasBordillos(BigDecimal obrAcerasBordillos) {
        this.obrAcerasBordillos = obrAcerasBordillos;
    }

    public BigDecimal getObrServicio() {
        return obrServicio;
    }

    public void setObrServicio(BigDecimal obrServicio) {
        this.obrServicio = obrServicio;
    }

    public BigDecimal getObrInfUrbana() {
        return obrInfUrbana;
    }

    public void setObrInfUrbana(BigDecimal obrInfUrbana) {
        this.obrInfUrbana = obrInfUrbana;
    }

    public BigDecimal getObrDesecacionRellenos() {
        return obrDesecacionRellenos;
    }

    public void setObrDesecacionRellenos(BigDecimal obrDesecacionRellenos) {
        this.obrDesecacionRellenos = obrDesecacionRellenos;
    }

    public BigDecimal getObrTotal() {
        return obrTotal;
    }

    public void setObrTotal(BigDecimal obrTotal) {
        this.obrTotal = obrTotal;
    }

    public Constructora getConCodigo() {
        return conCodigo;
    }

    public void setConCodigo(Constructora conCodigo) {
        this.conCodigo = conCodigo;
    }

    public CatalogoDetalle getCatdetEstado() {
        return catdetEstado;
    }

    public void setCatdetEstado(CatalogoDetalle catdetEstado) {
        this.catdetEstado = catdetEstado;
    }

    public CatalogoDetalle getCatdetEjecucion() {
        return catdetEjecucion;
    }

    public void setCatdetEjecucion(CatalogoDetalle catdetEjecucion) {
        this.catdetEjecucion = catdetEjecucion;
    }

    public CatalogoDetalle getCatdetParroquia() {
        return catdetParroquia;
    }

    public void setCatdetParroquia(CatalogoDetalle catdetParroquia) {
        this.catdetParroquia = catdetParroquia;
    }

    public List<Mejora> getMejoraList() {
        return mejoraList;
    }

    public void setMejoraList(List<Mejora> mejoraList) {
        this.mejoraList = mejoraList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (obrCodigo != null ? obrCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ObraProyecto)) {
            return false;
        }
        ObraProyecto other = (ObraProyecto) object;
        if ((this.obrCodigo == null && other.obrCodigo != null) || (this.obrCodigo != null && !this.obrCodigo.equals(other.obrCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sirec.ejb.entidades.ObraProyecto[ obrCodigo=" + obrCodigo + " ]";
    }
    
}
