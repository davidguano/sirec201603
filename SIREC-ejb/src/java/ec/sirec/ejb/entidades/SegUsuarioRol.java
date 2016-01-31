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
@Table(name = "sirec.seg_usuario_rol")
@NamedQueries({
    @NamedQuery(name = "SegUsuarioRol.findAll", query = "SELECT s FROM SegUsuarioRol s")})
public class SegUsuarioRol implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "usurol_codigo")
    private Integer usurolCodigo;
    @JoinColumn(name = "usu_identificacion", referencedColumnName = "usu_identificacion")
    @ManyToOne(optional = false)
    private SegUsuario usuIdentificacion;
    @JoinColumn(name = "rol_codigo", referencedColumnName = "rol_codigo")
    @ManyToOne(optional = false)
    private SegRol rolCodigo;

    public SegUsuarioRol() {
    }

    public SegUsuarioRol(Integer usurolCodigo) {
        this.usurolCodigo = usurolCodigo;
    }

    public Integer getUsurolCodigo() {
        return usurolCodigo;
    }

    public void setUsurolCodigo(Integer usurolCodigo) {
        this.usurolCodigo = usurolCodigo;
    }

    public SegUsuario getUsuIdentificacion() {
        return usuIdentificacion;
    }

    public void setUsuIdentificacion(SegUsuario usuIdentificacion) {
        this.usuIdentificacion = usuIdentificacion;
    }

    public SegRol getRolCodigo() {
        return rolCodigo;
    }

    public void setRolCodigo(SegRol rolCodigo) {
        this.rolCodigo = rolCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usurolCodigo != null ? usurolCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SegUsuarioRol)) {
            return false;
        }
        SegUsuarioRol other = (SegUsuarioRol) object;
        if ((this.usurolCodigo == null && other.usurolCodigo != null) || (this.usurolCodigo != null && !this.usurolCodigo.equals(other.usurolCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sirec.ejb.entidades.SegUsuarioRol[ usurolCodigo=" + usurolCodigo + " ]";
    }
    
}
