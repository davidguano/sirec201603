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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "sirec.seg_permiso")
@NamedQueries({
    @NamedQuery(name = "SegPermiso.findAll", query = "SELECT s FROM SegPermiso s")})
public class SegPermiso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "per_codigo")
    private Integer perCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "per_nemonico")
    private String perNemonico;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "per_nombre")
    private String perNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "per_tipo_permiso")
    private String perTipoPermiso;
    @Size(max = 2147483647)
    @Column(name = "per_url_pagina")
    private String perUrlPagina;
    @Column(name = "per_codigo_padre")
    private Integer perCodigoPadre;
    @Column(name = "per_nivel")
    private Integer perNivel;
    @Size(max = 20)
    @Column(name = "per_icono")
    private String perIcono;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "perCodigo")
    private List<SegPermisoRol> segPermisoRolList;
    @JoinColumn(name = "apli_codigo", referencedColumnName = "apli_codigo")
    @ManyToOne(optional = false)
    private SegAplicacion apliCodigo;

    public SegPermiso() {
    }

    public SegPermiso(Integer perCodigo) {
        this.perCodigo = perCodigo;
    }

    public SegPermiso(Integer perCodigo, String perNemonico, String perNombre, String perTipoPermiso) {
        this.perCodigo = perCodigo;
        this.perNemonico = perNemonico;
        this.perNombre = perNombre;
        this.perTipoPermiso = perTipoPermiso;
    }

    public Integer getPerCodigo() {
        return perCodigo;
    }

    public void setPerCodigo(Integer perCodigo) {
        this.perCodigo = perCodigo;
    }

    public String getPerNemonico() {
        return perNemonico;
    }

    public void setPerNemonico(String perNemonico) {
        this.perNemonico = perNemonico;
    }

    public String getPerNombre() {
        return perNombre;
    }

    public void setPerNombre(String perNombre) {
        this.perNombre = perNombre;
    }

    public String getPerTipoPermiso() {
        return perTipoPermiso;
    }

    public void setPerTipoPermiso(String perTipoPermiso) {
        this.perTipoPermiso = perTipoPermiso;
    }

    public String getPerUrlPagina() {
        return perUrlPagina;
    }

    public void setPerUrlPagina(String perUrlPagina) {
        this.perUrlPagina = perUrlPagina;
    }

    public Integer getPerCodigoPadre() {
        return perCodigoPadre;
    }

    public void setPerCodigoPadre(Integer perCodigoPadre) {
        this.perCodigoPadre = perCodigoPadre;
    }

    public Integer getPerNivel() {
        return perNivel;
    }

    public void setPerNivel(Integer perNivel) {
        this.perNivel = perNivel;
    }

    public String getPerIcono() {
        return perIcono;
    }

    public void setPerIcono(String perIcono) {
        this.perIcono = perIcono;
    }

    public List<SegPermisoRol> getSegPermisoRolList() {
        return segPermisoRolList;
    }

    public void setSegPermisoRolList(List<SegPermisoRol> segPermisoRolList) {
        this.segPermisoRolList = segPermisoRolList;
    }

    public SegAplicacion getApliCodigo() {
        return apliCodigo;
    }

    public void setApliCodigo(SegAplicacion apliCodigo) {
        this.apliCodigo = apliCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (perCodigo != null ? perCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SegPermiso)) {
            return false;
        }
        SegPermiso other = (SegPermiso) object;
        if ((this.perCodigo == null && other.perCodigo != null) || (this.perCodigo != null && !this.perCodigo.equals(other.perCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sirec.ejb.entidades.SegPermiso[ perCodigo=" + perCodigo + " ]";
    }
    
}
