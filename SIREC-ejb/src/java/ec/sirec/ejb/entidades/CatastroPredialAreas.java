/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.sirec.ejb.entidades;

import java.io.Serializable;
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
@Table(name = "sirec.catastro_predial_areas")
@NamedQueries({
    @NamedQuery(name = "CatastroPredialAreas.findAll", query = "SELECT c FROM CatastroPredialAreas c")})
public class CatastroPredialAreas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "catpreare_codigo")
    private Integer catpreareCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "catpreare_bloque")
    private int catpreareBloque;
    @Basic(optional = false)
    @NotNull
    @Column(name = "catpreare_area")
    private double catpreareArea;
    @Column(name = "catpreare_piso")
    private int catprearePiso;
    @JoinColumn(name = "catpre_codigo", referencedColumnName = "catpre_codigo")
    @ManyToOne(optional = false)
    private CatastroPredial catpreCodigo;

    public CatastroPredialAreas() {
    }

    public CatastroPredialAreas(Integer catpreareCodigo) {
        this.catpreareCodigo = catpreareCodigo;
    }

    public CatastroPredialAreas(Integer catpreareCodigo, int catpreareBloque, double catpreareArea) {
        this.catpreareCodigo = catpreareCodigo;
        this.catpreareBloque = catpreareBloque;
        this.catpreareArea = catpreareArea;
    }

    public Integer getCatpreareCodigo() {
        return catpreareCodigo;
    }

    public void setCatpreareCodigo(Integer catpreareCodigo) {
        this.catpreareCodigo = catpreareCodigo;
    }

    public int getCatpreareBloque() {
        return catpreareBloque;
    }

    public void setCatpreareBloque(int catpreareBloque) {
        this.catpreareBloque = catpreareBloque;
    }

    public double getCatpreareArea() {
        return catpreareArea;
    }

    public void setCatpreareArea(double catpreareArea) {
        this.catpreareArea = catpreareArea;
    }

    public CatastroPredial getCatpreCodigo() {
        return catpreCodigo;
    }

    public void setCatpreCodigo(CatastroPredial catpreCodigo) {
        this.catpreCodigo = catpreCodigo;
    }

    public int getCatprearePiso() {
        return catprearePiso;
    }

    public void setCatprearePiso(int catprearePiso) {
        this.catprearePiso = catprearePiso;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (catpreareCodigo != null ? catpreareCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatastroPredialAreas)) {
            return false;
        }
        CatastroPredialAreas other = (CatastroPredialAreas) object;
        if ((this.catpreareCodigo == null && other.catpreareCodigo != null) || (this.catpreareCodigo != null && !this.catpreareCodigo.equals(other.catpreareCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sirec.ejb.entidades.CatastroPredialAreas[ catpreareCodigo=" + catpreareCodigo + " ]";
    }
    
}
