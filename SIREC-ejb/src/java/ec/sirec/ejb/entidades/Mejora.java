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

/**
 *
 * @author DAVID GUAN
 */
@Entity
@Table(name = "sirec.mejora")
@NamedQueries({
    @NamedQuery(name = "Mejora.findAll", query = "SELECT m FROM Mejora m")})
public class Mejora implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "mej_codigo")
    private Integer mejCodigo;
    @JoinColumn(name = "obr_codigo", referencedColumnName = "obr_codigo")
    @ManyToOne(optional = false)
    private ObraProyecto obrCodigo;
    @JoinColumn(name = "catpre_codigo", referencedColumnName = "catpre_codigo")
    @ManyToOne(optional = false)
    private CatastroPredial catpreCodigo;

    public Mejora() {
    }

    public Mejora(Integer mejCodigo) {
        this.mejCodigo = mejCodigo;
    }

    public Integer getMejCodigo() {
        return mejCodigo;
    }

    public void setMejCodigo(Integer mejCodigo) {
        this.mejCodigo = mejCodigo;
    }

    public ObraProyecto getObrCodigo() {
        return obrCodigo;
    }

    public void setObrCodigo(ObraProyecto obrCodigo) {
        this.obrCodigo = obrCodigo;
    }

    public CatastroPredial getCatpreCodigo() {
        return catpreCodigo;
    }

    public void setCatpreCodigo(CatastroPredial catpreCodigo) {
        this.catpreCodigo = catpreCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mejCodigo != null ? mejCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mejora)) {
            return false;
        }
        Mejora other = (Mejora) object;
        if ((this.mejCodigo == null && other.mejCodigo != null) || (this.mejCodigo != null && !this.mejCodigo.equals(other.mejCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sirec.ejb.entidades.Mejora[ mejCodigo=" + mejCodigo + " ]";
    }
    
}
