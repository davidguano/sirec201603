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
import javax.validation.constraints.Size;

/**
 *
 * @author DAVID GUAN
 */
@Entity
@Table(name = "sirec.propietario_predio")
@NamedQueries({
    @NamedQuery(name = "PropietarioPredio.findAll", query = "SELECT p FROM PropietarioPredio p")})
public class PropietarioPredio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "propre_codigo")
    private Integer propreCodigo;
    @JoinColumn(name = "catpre_codigo", referencedColumnName = "catpre_codigo")
    @ManyToOne(optional = false)
    private CatastroPredial catpreCodigo;
    @JoinColumn(name = "pro_ci", referencedColumnName = "pro_ci")
    @ManyToOne(optional = false)
    private Propietario proCi;
    @Size(max = 1)
    @Column(name = "propre_tipo")
    private String propreTipo;

    public PropietarioPredio() {
    }

    public PropietarioPredio(Integer propreCodigo) {
        this.propreCodigo = propreCodigo;
    }

    public Integer getPropreCodigo() {
        return propreCodigo;
    }

    public void setPropreCodigo(Integer propreCodigo) {
        this.propreCodigo = propreCodigo;
    }

    public CatastroPredial getCatpreCodigo() {
        return catpreCodigo;
    }

    public void setCatpreCodigo(CatastroPredial catpreCodigo) {
        this.catpreCodigo = catpreCodigo;
    }

    public Propietario getProCi() {
        return proCi;
    }

    public void setProCi(Propietario proCi) {
        this.proCi = proCi;
    }
    
    

    public String getPropreTipo() {
        return propreTipo;
    }

    public void setPropreTipo(String propreTipo) {
        this.propreTipo = propreTipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (propreCodigo != null ? propreCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PropietarioPredio)) {
            return false;
        }
        PropietarioPredio other = (PropietarioPredio) object;
        if ((this.propreCodigo == null && other.propreCodigo != null) || (this.propreCodigo != null && !this.propreCodigo.equals(other.propreCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sirec.ejb.entidades.PropietarioPredio[ propreCodigo=" + propreCodigo + " ]";
    }
    
}
