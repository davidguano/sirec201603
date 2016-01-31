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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author DAVID GUAN
 */
@Entity
@Table(name = "sirec.fitto_corvini")
@NamedQueries({
    @NamedQuery(name = "FittoCorvini.findAll", query = "SELECT f FROM FittoCorvini f")})
public class FittoCorvini implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "edad_vida")
    private Integer edadVida;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "clase_1")
    private Double clase1;
    @Column(name = "clase_1_5")
    private Double clase15;
    @Column(name = "clase_2")
    private Double clase2;
    @Column(name = "clase_2_5")
    private Double clase25;
    @Column(name = "clase_3")
    private Double clase3;
    @Column(name = "clase_3_5")
    private Double clase35;
    @Column(name = "clase_4")
    private Double clase4;
    @Column(name = "clase_4_5")
    private Double clase45;
    @Column(name = "clase_5")
    private Double clase5;

    public FittoCorvini() {
    }

    public FittoCorvini(Integer edadVida) {
        this.edadVida = edadVida;
    }

    public Integer getEdadVida() {
        return edadVida;
    }

    public void setEdadVida(Integer edadVida) {
        this.edadVida = edadVida;
    }

    public Double getClase1() {
        return clase1;
    }

    public void setClase1(Double clase1) {
        this.clase1 = clase1;
    }

    public Double getClase15() {
        return clase15;
    }

    public void setClase15(Double clase15) {
        this.clase15 = clase15;
    }

    public Double getClase2() {
        return clase2;
    }

    public void setClase2(Double clase2) {
        this.clase2 = clase2;
    }

    public Double getClase25() {
        return clase25;
    }

    public void setClase25(Double clase25) {
        this.clase25 = clase25;
    }

    public Double getClase3() {
        return clase3;
    }

    public void setClase3(Double clase3) {
        this.clase3 = clase3;
    }

    public Double getClase35() {
        return clase35;
    }

    public void setClase35(Double clase35) {
        this.clase35 = clase35;
    }

    public Double getClase4() {
        return clase4;
    }

    public void setClase4(Double clase4) {
        this.clase4 = clase4;
    }

    public Double getClase45() {
        return clase45;
    }

    public void setClase45(Double clase45) {
        this.clase45 = clase45;
    }

    public Double getClase5() {
        return clase5;
    }

    public void setClase5(Double clase5) {
        this.clase5 = clase5;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (edadVida != null ? edadVida.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FittoCorvini)) {
            return false;
        }
        FittoCorvini other = (FittoCorvini) object;
        if ((this.edadVida == null && other.edadVida != null) || (this.edadVida != null && !this.edadVida.equals(other.edadVida))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sirec.ejb.entidades.FittoCorvini[ edadVida=" + edadVida + " ]";
    }
    
}
