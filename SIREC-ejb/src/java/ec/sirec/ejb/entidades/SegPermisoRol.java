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
@Table(name = "sirec.seg_permiso_rol")
@NamedQueries({
    @NamedQuery(name = "SegPermisoRol.findAll", query = "SELECT s FROM SegPermisoRol s")})
public class SegPermisoRol implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "perrol_codigo")
    private Integer perrolCodigo;
    @JoinColumn(name = "rol_codigo", referencedColumnName = "rol_codigo")
    @ManyToOne(optional = false)
    private SegRol rolCodigo;
    @JoinColumn(name = "per_codigo", referencedColumnName = "per_codigo")
    @ManyToOne(optional = false)
    private SegPermiso perCodigo;

    public SegPermisoRol() {
    }

    public SegPermisoRol(Integer perrolCodigo) {
        this.perrolCodigo = perrolCodigo;
    }

    public Integer getPerrolCodigo() {
        return perrolCodigo;
    }

    public void setPerrolCodigo(Integer perrolCodigo) {
        this.perrolCodigo = perrolCodigo;
    }

    public SegRol getRolCodigo() {
        return rolCodigo;
    }

    public void setRolCodigo(SegRol rolCodigo) {
        this.rolCodigo = rolCodigo;
    }

    public SegPermiso getPerCodigo() {
        return perCodigo;
    }

    public void setPerCodigo(SegPermiso perCodigo) {
        this.perCodigo = perCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (perrolCodigo != null ? perrolCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SegPermisoRol)) {
            return false;
        }
        SegPermisoRol other = (SegPermisoRol) object;
        if ((this.perrolCodigo == null && other.perrolCodigo != null) || (this.perrolCodigo != null && !this.perrolCodigo.equals(other.perrolCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sirec.ejb.entidades.SegPermisoRol[ perrolCodigo=" + perrolCodigo + " ]";
    }
    
}
