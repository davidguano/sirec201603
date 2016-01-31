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
import javax.validation.constraints.Size;

/**
 *
 * @author DAVID GUAN
 */
@Entity
@Table(name = "sirec.catastro_predial_edificacion")
@NamedQueries({
    @NamedQuery(name = "CatastroPredialEdificacion.findAll", query = "SELECT c FROM CatastroPredialEdificacion c")})
public class CatastroPredialEdificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "catpreedi_codigo")
    private Integer catpreediCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "catpreedi_grupo")
    private String catpreediGrupo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "catpreedi_subgrupo")
    private String catpreediSubgrupo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "catpreedi_bloque")
    private String catpreediBloque;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "catpreedi_piso")
    private String catpreediPiso;
    @Column(name = "catpreedi_valor")
    private Integer catpreediValor;
    @JoinColumn(name = "catpre_codigo", referencedColumnName = "catpre_codigo")
    @ManyToOne(optional = false)
    private CatastroPredial catpreCodigo;
    @JoinColumn(name = "catdet_codigo", referencedColumnName = "catdet_codigo")
    @ManyToOne
    private CatalogoDetalle catdetCodigo;
    @Transient
    private String nombreGrupo;
    @Transient
    private String nombreSubGrupo;

    public CatastroPredialEdificacion() {
    }

    public CatastroPredialEdificacion(Integer catpreediCodigo) {
        this.catpreediCodigo = catpreediCodigo;
    }

    public CatastroPredialEdificacion(Integer catpreediCodigo, String catpreediGrupo, String catpreediSubgrupo, String catpreediBloque, String catpreediPiso) {
        this.catpreediCodigo = catpreediCodigo;
        this.catpreediGrupo = catpreediGrupo;
        this.catpreediSubgrupo = catpreediSubgrupo;
        this.catpreediBloque = catpreediBloque;
        this.catpreediPiso = catpreediPiso;
    }

    public Integer getCatpreediCodigo() {
        return catpreediCodigo;
    }

    public void setCatpreediCodigo(Integer catpreediCodigo) {
        this.catpreediCodigo = catpreediCodigo;
    }

    public String getCatpreediGrupo() {
        return catpreediGrupo;
    }

    public void setCatpreediGrupo(String catpreediGrupo) {
        this.catpreediGrupo = catpreediGrupo;
    }

    public String getCatpreediSubgrupo() {
        return catpreediSubgrupo;
    }

    public void setCatpreediSubgrupo(String catpreediSubgrupo) {
        this.catpreediSubgrupo = catpreediSubgrupo;
    }

    public String getCatpreediBloque() {
        return catpreediBloque;
    }

    public void setCatpreediBloque(String catpreediBloque) {
        this.catpreediBloque = catpreediBloque;
    }

    public String getCatpreediPiso() {
        return catpreediPiso;
    }

    public void setCatpreediPiso(String catpreediPiso) {
        this.catpreediPiso = catpreediPiso;
    }

    public CatastroPredial getCatpreCodigo() {
        return catpreCodigo;
    }

    public void setCatpreCodigo(CatastroPredial catpreCodigo) {
        this.catpreCodigo = catpreCodigo;
    }

    public CatalogoDetalle getCatdetCodigo() {
        if (catdetCodigo == null) {
            catdetCodigo = new CatalogoDetalle();
        }
        return catdetCodigo;
    }

    public void setCatdetCodigo(CatalogoDetalle catdetCodigo) {
        this.catdetCodigo = catdetCodigo;
    }

    public Integer getCatpreediValor() {
        return catpreediValor;
    }

    public void setCatpreediValor(Integer catpreediValor) {
        this.catpreediValor = catpreediValor;
    }

    public String getNombreGrupo() {
        if (catpreediGrupo.equals("1")) {
            nombreGrupo = "Caracteristicas Generales";
        } else if (catpreediGrupo.equals("2")) {
            nombreGrupo = "Estructura";
        } else if (catpreediGrupo.equals("3")) {
            nombreGrupo = "Acabados";
        } else if (catpreediGrupo.equals("4")) {
            nombreGrupo = "Instalaciones";
        } else if (catpreediGrupo.equals("5")) {
            nombreGrupo = "Otras Inversiones";
        }
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

    public String getNombreSubGrupo() {
        if (catpreediGrupo.equals("1")) {
            if(catpreediSubgrupo.equals("1")){
                nombreSubGrupo="Estructura";
            }else if(catpreediSubgrupo.equals("2")){
                nombreSubGrupo="Edad Cons.";
            }else if(catpreediSubgrupo.equals("3")){
                nombreSubGrupo="Estado Cons.";
            }else if(catpreediSubgrupo.equals("4")){
                nombreSubGrupo="Reparacion";
            }
        } else if (catpreediGrupo.equals("2")) {
            if(catpreediSubgrupo.equals("1")){
                nombreSubGrupo="Columnas";
            }else if(catpreediSubgrupo.equals("2")){
                nombreSubGrupo="Vigas Cad.";
            }else if(catpreediSubgrupo.equals("3")){
                nombreSubGrupo="Entrepisos";
            }else if(catpreediSubgrupo.equals("4")){
                nombreSubGrupo="Paredes";
            }else if(catpreediSubgrupo.equals("5")){
                nombreSubGrupo="Escaleras";
            }else if(catpreediSubgrupo.equals("6")){
                nombreSubGrupo="Cubierta";
            }
        } else if (catpreediGrupo.equals("3")) {
            if(catpreediSubgrupo.equals("1")){
                nombreSubGrupo="Rev. Pisos";
            }else if(catpreediSubgrupo.equals("2")){
                nombreSubGrupo="Rev. Interior";
            }else if(catpreediSubgrupo.equals("3")){
                nombreSubGrupo="Rev. Exterior";
            }else if(catpreediSubgrupo.equals("4")){
                nombreSubGrupo="Rev. Escalera";
            }else if(catpreediSubgrupo.equals("5")){
                nombreSubGrupo="Tumbados";
            }else if(catpreediSubgrupo.equals("6")){
                nombreSubGrupo="Cubierta";
            }else if(catpreediSubgrupo.equals("7")){
                nombreSubGrupo="Puertas";
            }else if(catpreediSubgrupo.equals("8")){
                nombreSubGrupo="Ventanas";
            }else if(catpreediSubgrupo.equals("9")){
                nombreSubGrupo="Cubierta";
            }else if(catpreediSubgrupo.equals("10")){
                nombreSubGrupo="Closets";
            }
        } else if (catpreediGrupo.equals("4")) {
            if(catpreediSubgrupo.equals("1")){
                nombreSubGrupo="Sanitaria";
            }else if(catpreediSubgrupo.equals("2")){
                nombreSubGrupo="Banos";
            }else if(catpreediSubgrupo.equals("3")){
                nombreSubGrupo="Electricas";
            }
        } else if (catpreediGrupo.equals("5")) {
            if(catpreediSubgrupo.equals("1")){
                nombreSubGrupo="Sauna/Turco/Hidromasaje";
            }else if(catpreediSubgrupo.equals("2")){
                nombreSubGrupo="Ascensor";
            }else if(catpreediSubgrupo.equals("3")){
                nombreSubGrupo="Escalera Electrica";
            }else if(catpreediSubgrupo.equals("4")){
                nombreSubGrupo="Aire Acondicionado";
            }else if(catpreediSubgrupo.equals("5")){
                nombreSubGrupo="Sistema Seguridad";
            }else if(catpreediSubgrupo.equals("6")){
                nombreSubGrupo="Piscinas";
            }else if(catpreediSubgrupo.equals("7")){
                nombreSubGrupo="Cerramientos";
            }else if(catpreediSubgrupo.equals("8")){
                nombreSubGrupo="Vias y caminos internos";
            }else if(catpreediSubgrupo.equals("9")){
                nombreSubGrupo="Instalaciones deportivas";
            }
        }
        return nombreSubGrupo;
    }

    public void setNombreSubGrupo(String nombreSubGrupo) {
        this.nombreSubGrupo = nombreSubGrupo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (catpreediCodigo != null ? catpreediCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatastroPredialEdificacion)) {
            return false;
        }
        CatastroPredialEdificacion other = (CatastroPredialEdificacion) object;
        if ((this.catpreediCodigo == null && other.catpreediCodigo != null) || (this.catpreediCodigo != null && !this.catpreediCodigo.equals(other.catpreediCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sirec.ejb.entidades.CatastroPredialEdificacion[ catpreediCodigo=" + catpreediCodigo + " ]";
    }

}
