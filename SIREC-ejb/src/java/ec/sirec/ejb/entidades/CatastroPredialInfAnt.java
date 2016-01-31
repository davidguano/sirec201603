/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.sirec.ejb.entidades;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author DAVID GUAN
 */
@Entity
@Table(name = "sirec.catastro_predial_inf_ant")
@NamedQueries({
    @NamedQuery(name = "CatastroPredialInfAnt.findAll", query = "SELECT c FROM CatastroPredialInfAnt c")})
public class CatastroPredialInfAnt implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "catpreinfa_codigo")
    private Integer catpreinfaCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "catpreinfa_tipo")
    private String catpreinfaTipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "catpreinfa_valor")
    private String catpreinfaValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "catpreinfa_marcatiempo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date catpreinfaMarcatiempo;
    @JoinColumn(name = "catpre_codigo", referencedColumnName = "catpre_codigo")
    @ManyToOne(optional = false)
    private CatastroPredial catpreCodigo;

    public CatastroPredialInfAnt() {
    }

    public CatastroPredialInfAnt(Integer catpreinfaCodigo) {
        this.catpreinfaCodigo = catpreinfaCodigo;
    }

    public CatastroPredialInfAnt(Integer catpreinfaCodigo, String catpreinfaTipo, String catpreinfaValor, Date catpreinfaMarcatiempo) {
        this.catpreinfaCodigo = catpreinfaCodigo;
        this.catpreinfaTipo = catpreinfaTipo;
        this.catpreinfaValor = catpreinfaValor;
        this.catpreinfaMarcatiempo = catpreinfaMarcatiempo;
    }

    public Integer getCatpreinfaCodigo() {
        return catpreinfaCodigo;
    }

    public void setCatpreinfaCodigo(Integer catpreinfaCodigo) {
        this.catpreinfaCodigo = catpreinfaCodigo;
    }

    public String getCatpreinfaTipo() {
        return catpreinfaTipo;
    }

    public void setCatpreinfaTipo(String catpreinfaTipo) {
        this.catpreinfaTipo = catpreinfaTipo;
    }

    public String getCatpreinfaValor() {
        return catpreinfaValor;
    }

    public void setCatpreinfaValor(String catpreinfaValor) {
        this.catpreinfaValor = catpreinfaValor;
    }

    public Date getCatpreinfaMarcatiempo() {
        return catpreinfaMarcatiempo;
    }

    public void setCatpreinfaMarcatiempo(Date catpreinfaMarcatiempo) {
        this.catpreinfaMarcatiempo = catpreinfaMarcatiempo;
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
        hash += (catpreinfaCodigo != null ? catpreinfaCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatastroPredialInfAnt)) {
            return false;
        }
        CatastroPredialInfAnt other = (CatastroPredialInfAnt) object;
        if ((this.catpreinfaCodigo == null && other.catpreinfaCodigo != null) || (this.catpreinfaCodigo != null && !this.catpreinfaCodigo.equals(other.catpreinfaCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sirec.ejb.entidades.CatastroPredialInfAnt[ catpreinfaCodigo=" + catpreinfaCodigo + " ]";
    }
    
}
