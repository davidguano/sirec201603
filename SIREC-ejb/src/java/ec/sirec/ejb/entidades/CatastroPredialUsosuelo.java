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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

/**
 *
 * @author DAVID GUAN
 */
@Entity
@Table(name = "sirec.catastro_predial_usosuelo")
@NamedQueries({
    @NamedQuery(name = "CatastroPredialUsosuelo.findAll", query = "SELECT c FROM CatastroPredialUsosuelo c")})
public class CatastroPredialUsosuelo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "catpreusu_codigo")
    private Integer catpreusuCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "catpreusu_item")
    private int catpreusuItem;
    @Column(name = "catpreusu_valor")
    private Integer catpreusuValor;
    @Column(name = "catpreusu_aplica")
    private Boolean catpreusuAplica;
  
    @JoinColumn(name = "catpre_codigo", referencedColumnName = "catpre_codigo")
    @ManyToOne(optional = false)
    private CatastroPredial catpreCodigo;
    @JoinColumn(name = "catdet_codigo", referencedColumnName = "catdet_codigo")
    @ManyToOne(optional = false)
    private CatalogoDetalle catdetCodigo;
    @Transient
    private String grupo;
    @Transient
    private String subgrupo;
    

    public CatastroPredialUsosuelo() {
    }

    public CatastroPredialUsosuelo(Integer catpreusuCodigo) {
        this.catpreusuCodigo = catpreusuCodigo;
    }

    public CatastroPredialUsosuelo(Integer catpreusuCodigo, int catpreusuItem) {
        this.catpreusuCodigo = catpreusuCodigo;
        this.catpreusuItem = catpreusuItem;
    }

    public Integer getCatpreusuCodigo() {
        return catpreusuCodigo;
    }

    public void setCatpreusuCodigo(Integer catpreusuCodigo) {
        this.catpreusuCodigo = catpreusuCodigo;
    }

    public int getCatpreusuItem() {
        return catpreusuItem;
    }

    public void setCatpreusuItem(int catpreusuItem) {
        this.catpreusuItem = catpreusuItem;
    }

    public Integer getCatpreusuValor() {
        return catpreusuValor;
    }

    public void setCatpreusuValor(Integer catpreusuValor) {
        this.catpreusuValor = catpreusuValor;
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

    public Boolean getCatpreusuAplica() {
        return catpreusuAplica;
    }

    public void setCatpreusuAplica(Boolean catpreusuAplica) {
        this.catpreusuAplica = catpreusuAplica;
    }

    public String getGrupo() {
        if(catdetCodigo.getCatdetCod().substring(0, 1).equals("1")){
            grupo="Produccion";
        }else if(catdetCodigo.getCatdetCod().substring(0, 1).equals("2")){
            grupo="Consumo";
        }else if(catdetCodigo.getCatdetCod().substring(0, 1).equals("3")){
            grupo="Intercambio";
        }else if(catdetCodigo.getCatdetCod().substring(0, 1).equals("4")){
            grupo="Gestion";
        }
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getSubgrupo() {
        if(catdetCodigo.getCatdetCod().substring(0, 2).equals("11")){
            subgrupo="Industrial";
        }else if(catdetCodigo.getCatdetCod().substring(0, 2).equals("12")){
            subgrupo="Artesanal";
        }else if(catdetCodigo.getCatdetCod().substring(0, 2).equals("13")){
            subgrupo="Agropecuario";
        }else if(catdetCodigo.getCatdetCod().substring(0, 2).equals("21")){
            subgrupo="Residencial";
        }else if(catdetCodigo.getCatdetCod().substring(0, 2).equals("22")){
            subgrupo="Alimentos y Bebidas";
        }else if(catdetCodigo.getCatdetCod().substring(0, 2).equals("23")){
            subgrupo="Recreacion";
        }else if(catdetCodigo.getCatdetCod().substring(0, 2).equals("24")){
            subgrupo="Educacion";
        }else if(catdetCodigo.getCatdetCod().substring(0, 2).equals("25")){
            subgrupo="Salud";
        }else if(catdetCodigo.getCatdetCod().substring(0, 2).equals("31")){
            subgrupo="Servicios Privados";
        }else if(catdetCodigo.getCatdetCod().substring(0, 2).equals("32")){
            subgrupo="Comercio";
        }else if(catdetCodigo.getCatdetCod().substring(0, 2).equals("33")){
            subgrupo="Comunicacion";
        }else if(catdetCodigo.getCatdetCod().substring(0, 2).equals("41")){
            subgrupo="Institucion publica";
        }else if(catdetCodigo.getCatdetCod().substring(0, 2).equals("42")){
            subgrupo="Institucion privada";
        }
        return subgrupo;
    }

    public void setSubgrupo(String subgrupo) {
        this.subgrupo = subgrupo;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (catpreusuCodigo != null ? catpreusuCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatastroPredialUsosuelo)) {
            return false;
        }
        CatastroPredialUsosuelo other = (CatastroPredialUsosuelo) object;
        if ((this.catpreusuCodigo == null && other.catpreusuCodigo != null) || (this.catpreusuCodigo != null && !this.catpreusuCodigo.equals(other.catpreusuCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sirec.ejb.entidades.CatastroPredialUsosuelo[ catpreusuCodigo=" + catpreusuCodigo + " ]";
    }
    
}
