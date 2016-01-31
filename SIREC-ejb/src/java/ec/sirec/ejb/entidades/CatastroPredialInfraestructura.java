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
@Table(name = "sirec.catastro_predial_infraestructura")
@NamedQueries({
    @NamedQuery(name = "CatastroPredialInfraestructura.findAll", query = "SELECT c FROM CatastroPredialInfraestructura c")})
public class CatastroPredialInfraestructura implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "catpreinf_codigo")
    private Integer catpreinfCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "catpreinf_item")
    private int catpreinfItem;
    @JoinColumn(name = "catpre_codigo", referencedColumnName = "catpre_codigo")
    @ManyToOne(optional = false)
    private CatastroPredial catpreCodigo;
    @JoinColumn(name = "catdet_codigo", referencedColumnName = "catdet_codigo")
    @ManyToOne(optional = false)
    private CatalogoDetalle catdetCodigo;

    public CatastroPredialInfraestructura() {
    }

    public CatastroPredialInfraestructura(Integer catpreinfCodigo) {
        this.catpreinfCodigo = catpreinfCodigo;
    }

    public CatastroPredialInfraestructura(Integer catpreinfCodigo, int catpreinfItem) {
        this.catpreinfCodigo = catpreinfCodigo;
        this.catpreinfItem = catpreinfItem;
    }

    public Integer getCatpreinfCodigo() {
        return catpreinfCodigo;
    }

    public void setCatpreinfCodigo(Integer catpreinfCodigo) {
        this.catpreinfCodigo = catpreinfCodigo;
    }

    public int getCatpreinfItem() {
        return catpreinfItem;
    }

    public void setCatpreinfItem(int catpreinfItem) {
        this.catpreinfItem = catpreinfItem;
    }

    public CatastroPredial getCatpreCodigo() {
        return catpreCodigo;
    }

    public void setCatpreCodigo(CatastroPredial catpreCodigo) {
        this.catpreCodigo = catpreCodigo;
    }

    public CatalogoDetalle getCatdetCodigo() {
        return catdetCodigo;
    }

    public void setCatdetCodigo(CatalogoDetalle catdetCodigo) {
        this.catdetCodigo = catdetCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (catpreinfCodigo != null ? catpreinfCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatastroPredialInfraestructura)) {
            return false;
        }
        CatastroPredialInfraestructura other = (CatastroPredialInfraestructura) object;
        if ((this.catpreinfCodigo == null && other.catpreinfCodigo != null) || (this.catpreinfCodigo != null && !this.catpreinfCodigo.equals(other.catpreinfCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sirec.ejb.entidades.CatastroPredialInfraestructura[ catpreinfCodigo=" + catpreinfCodigo + " ]";
    }
    
}
