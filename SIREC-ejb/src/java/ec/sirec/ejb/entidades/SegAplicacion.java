/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.sirec.ejb.entidades;

import java.io.Serializable;
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
@Table(name = "sirec.seg_aplicacion")
@NamedQueries({
    @NamedQuery(name = "SegAplicacion.findAll", query = "SELECT s FROM SegAplicacion s")})
public class SegAplicacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "apli_codigo")
    private Integer apliCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "apli_nombre")
    private String apliNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "apli_nemonico")
    private String apliNemonico;
    @Size(max = 1)
    @Column(name = "apli_tipo_logeo")
    private String apliTipoLogeo;
    @Size(max = 2147483647)
    @Column(name = "apli_url_inicio")
    private String apliUrlInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "apli_estado")
    private boolean apliEstado;
    @Size(max = 20)
    @Column(name = "apli_icono")
    private String apliIcono;
    @Size(max = 1)
    @Column(name = "apli_estado_actual")
    private String apliEstadoActual;
    @Column(name = "apli_porcentaje")
    private Integer apliPorcentaje;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "apliCodigo")
    private List<SegRol> segRolList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "apliCodigo")
    private List<SegPermiso> segPermisoList;

    public SegAplicacion() {
    }

    public SegAplicacion(Integer apliCodigo) {
        this.apliCodigo = apliCodigo;
    }

    public SegAplicacion(Integer apliCodigo, String apliNombre, String apliNemonico, boolean apliEstado) {
        this.apliCodigo = apliCodigo;
        this.apliNombre = apliNombre;
        this.apliNemonico = apliNemonico;
        this.apliEstado = apliEstado;
    }

    public Integer getApliCodigo() {
        return apliCodigo;
    }

    public void setApliCodigo(Integer apliCodigo) {
        this.apliCodigo = apliCodigo;
    }

    public String getApliNombre() {
        return apliNombre;
    }

    public void setApliNombre(String apliNombre) {
        this.apliNombre = apliNombre;
    }

    public String getApliNemonico() {
        return apliNemonico;
    }

    public void setApliNemonico(String apliNemonico) {
        this.apliNemonico = apliNemonico;
    }

    public String getApliTipoLogeo() {
        return apliTipoLogeo;
    }

    public void setApliTipoLogeo(String apliTipoLogeo) {
        this.apliTipoLogeo = apliTipoLogeo;
    }

    public String getApliUrlInicio() {
        return apliUrlInicio;
    }

    public void setApliUrlInicio(String apliUrlInicio) {
        this.apliUrlInicio = apliUrlInicio;
    }

    public boolean getApliEstado() {
        return apliEstado;
    }

    public void setApliEstado(boolean apliEstado) {
        this.apliEstado = apliEstado;
    }

    public String getApliIcono() {
        return apliIcono;
    }

    public void setApliIcono(String apliIcono) {
        this.apliIcono = apliIcono;
    }

    public String getApliEstadoActual() {
        return apliEstadoActual;
    }

    public void setApliEstadoActual(String apliEstadoActual) {
        this.apliEstadoActual = apliEstadoActual;
    }

    public Integer getApliPorcentaje() {
        return apliPorcentaje;
    }

    public void setApliPorcentaje(Integer apliPorcentaje) {
        this.apliPorcentaje = apliPorcentaje;
    }

    public List<SegRol> getSegRolList() {
        return segRolList;
    }

    public void setSegRolList(List<SegRol> segRolList) {
        this.segRolList = segRolList;
    }

    public List<SegPermiso> getSegPermisoList() {
        return segPermisoList;
    }

    public void setSegPermisoList(List<SegPermiso> segPermisoList) {
        this.segPermisoList = segPermisoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (apliCodigo != null ? apliCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SegAplicacion)) {
            return false;
        }
        SegAplicacion other = (SegAplicacion) object;
        if ((this.apliCodigo == null && other.apliCodigo != null) || (this.apliCodigo != null && !this.apliCodigo.equals(other.apliCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sirec.ejb.entidades.SegAplicacion[ apliCodigo=" + apliCodigo + " ]";
    }
    
}
