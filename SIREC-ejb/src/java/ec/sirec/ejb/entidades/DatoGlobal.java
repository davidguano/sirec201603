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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author DAVID GUAN
 */
@Entity
@Table(name = "sirec.dato_global")
@NamedQueries({
    @NamedQuery(name = "DatoGlobal.findAll", query = "SELECT d FROM DatoGlobal d")})
public class DatoGlobal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "datglo_codigo")
    private Integer datgloCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "datglo_nombre")
    private String datgloNombre;
    @Size(max = 2147483647)
    @Column(name = "datglo_descripcion")
    private String datgloDescripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "datglo_valor")
    private String datgloValor;

    public DatoGlobal() {
    }

    public DatoGlobal(Integer datgloCodigo) {
        this.datgloCodigo = datgloCodigo;
    }

    public DatoGlobal(Integer datgloCodigo, String datgloNombre, String datgloValor) {
        this.datgloCodigo = datgloCodigo;
        this.datgloNombre = datgloNombre;
        this.datgloValor = datgloValor;
    }

    public Integer getDatgloCodigo() {
        return datgloCodigo;
    }

    public void setDatgloCodigo(Integer datgloCodigo) {
        this.datgloCodigo = datgloCodigo;
    }

    public String getDatgloNombre() {
        return datgloNombre;
    }

    public void setDatgloNombre(String datgloNombre) {
        this.datgloNombre = datgloNombre;
    }

    public String getDatgloDescripcion() {
        return datgloDescripcion;
    }

    public void setDatgloDescripcion(String datgloDescripcion) {
        this.datgloDescripcion = datgloDescripcion;
    }

    public String getDatgloValor() {
        return datgloValor;
    }

    public void setDatgloValor(String datgloValor) {
        this.datgloValor = datgloValor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (datgloCodigo != null ? datgloCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DatoGlobal)) {
            return false;
        }
        DatoGlobal other = (DatoGlobal) object;
        if ((this.datgloCodigo == null && other.datgloCodigo != null) || (this.datgloCodigo != null && !this.datgloCodigo.equals(other.datgloCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sirec.ejb.entidades.DatoGlobal[ datgloCodigo=" + datgloCodigo + " ]";
    }
    
}
