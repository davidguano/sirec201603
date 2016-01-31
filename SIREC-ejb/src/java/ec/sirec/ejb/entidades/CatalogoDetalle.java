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
@Table(name = "sirec.catalogo_detalle")
@NamedQueries({
    @NamedQuery(name = "CatalogoDetalle.findAll", query = "SELECT c FROM CatalogoDetalle c")})
public class CatalogoDetalle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "catdet_codigo")
    private Integer catdetCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "catdet_texto")
    private String catdetTexto;
    @Size(max = 10)
    @Column(name = "catdet_cod")
    private String catdetCod;
    @Column(name = "catdet_orden")
    private Integer catdetOrden;
    @Column(name = "catdet_estado")
    private Boolean catdetEstado;
    @Column(name = "catdet_valor")
    private Integer catdetValor;
    @Column(name = "catdet_valor_decimal")
    private Double catdetValorDecimal;
    @JoinColumn(name = "cat_codigo", referencedColumnName = "cat_codigo")
    @ManyToOne(optional = false)
    private Catalogo catCodigo;

    public CatalogoDetalle() {
        catCodigo=new Catalogo();
    }

    public CatalogoDetalle(Integer catdetCodigo) {
        this.catdetCodigo = catdetCodigo;
    }

    public CatalogoDetalle(Integer catdetCodigo, String catdetTexto) {
        this.catdetCodigo = catdetCodigo;
        this.catdetTexto = catdetTexto;
    }

    public Integer getCatdetCodigo() {
        return catdetCodigo;
    }

    public void setCatdetCodigo(Integer catdetCodigo) {
        this.catdetCodigo = catdetCodigo;
    }

    public String getCatdetTexto() {
        return catdetTexto;
    }

    public void setCatdetTexto(String catdetTexto) {
        this.catdetTexto = catdetTexto;
    }

    public String getCatdetCod() {
        return catdetCod;
    }

    public void setCatdetCod(String catdetCod) {
        this.catdetCod = catdetCod;
    }

    public Integer getCatdetOrden() {
        return catdetOrden;
    }

    public void setCatdetOrden(Integer catdetOrden) {
        this.catdetOrden = catdetOrden;
    }

    public Boolean getCatdetEstado() {
        return catdetEstado;
    }

    public void setCatdetEstado(Boolean catdetEstado) {
        this.catdetEstado = catdetEstado;
    }

    public Catalogo getCatCodigo() {
        return catCodigo;
    }

    public void setCatCodigo(Catalogo catCodigo) {
        this.catCodigo = catCodigo;
    }

    public Integer getCatdetValor() {
        return catdetValor;
    }

    public void setCatdetValor(Integer catdetValor) {
        this.catdetValor = catdetValor;
    }

    public Double getCatdetValorDecimal() {
        return catdetValorDecimal;
    }

    public void setCatdetValorDecimal(Double catdetValorDecimal) {
        this.catdetValorDecimal = catdetValorDecimal;
    }

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (catdetCodigo != null ? catdetCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatalogoDetalle)) {
            return false;
        }
        CatalogoDetalle other = (CatalogoDetalle) object;
        if ((this.catdetCodigo == null && other.catdetCodigo != null) || (this.catdetCodigo != null && !this.catdetCodigo.equals(other.catdetCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sirec.ejb.entidades.CatalogoDetalle[ catdetCodigo=" + catdetCodigo + " ]";
    }
    
}
