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
@Table(name = "sirec.catalogo")
@NamedQueries({
    @NamedQuery(name = "Catalogo.findAll", query = "SELECT c FROM Catalogo c")})
public class Catalogo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cat_codigo")
    private Integer catCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "cat_nemonico")
    private String catNemonico;
    @Size(max = 2147483647)
    @Column(name = "cat_texto")
    private String catTexto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "catCodigo")
    private List<CatalogoDetalle> catalogoDetalleList;

    public Catalogo() {
    }

    public Catalogo(Integer catCodigo) {
        this.catCodigo = catCodigo;
    }

    public Catalogo(Integer catCodigo, String catNemonico) {
        this.catCodigo = catCodigo;
        this.catNemonico = catNemonico;
    }

    public Integer getCatCodigo() {
        return catCodigo;
    }

    public void setCatCodigo(Integer catCodigo) {
        this.catCodigo = catCodigo;
    }

    public String getCatNemonico() {
        return catNemonico;
    }

    public void setCatNemonico(String catNemonico) {
        this.catNemonico = catNemonico;
    }

    public String getCatTexto() {
        return catTexto;
    }

    public void setCatTexto(String catTexto) {
        this.catTexto = catTexto;
    }

    public List<CatalogoDetalle> getCatalogoDetalleList() {
        return catalogoDetalleList;
    }

    public void setCatalogoDetalleList(List<CatalogoDetalle> catalogoDetalleList) {
        this.catalogoDetalleList = catalogoDetalleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (catCodigo != null ? catCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Catalogo)) {
            return false;
        }
        Catalogo other = (Catalogo) object;
        if ((this.catCodigo == null && other.catCodigo != null) || (this.catCodigo != null && !this.catCodigo.equals(other.catCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sirec.ejb.entidades.Catalogo[ catCodigo=" + catCodigo + " ]";
    }
    
}
