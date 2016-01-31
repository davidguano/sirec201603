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
@Table(name = "sirec.seg_rol")
@NamedQueries({
    @NamedQuery(name = "SegRol.findAll", query = "SELECT s FROM SegRol s")})
public class SegRol implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rol_codigo")
    private Integer rolCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "rol_nombre")
    private String rolNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "rol_nemonico")
    private String rolNemonico;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "rol_tipo_clave")
    private String rolTipoClave;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rol_dias_vigencia")
    private int rolDiasVigencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rol_estado")
    private boolean rolEstado;
    @JoinColumn(name = "apli_codigo", referencedColumnName = "apli_codigo")
    @ManyToOne(optional = false)
    private SegAplicacion apliCodigo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rolCodigo")
    private List<SegUsuarioRol> segUsuarioRolList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rolCodigo")
    private List<SegPermisoRol> segPermisoRolList;

    public SegRol() {
    }

    public SegRol(Integer rolCodigo) {
        this.rolCodigo = rolCodigo;
    }

    public SegRol(Integer rolCodigo, String rolNombre, String rolNemonico, String rolTipoClave, int rolDiasVigencia, boolean rolEstado) {
        this.rolCodigo = rolCodigo;
        this.rolNombre = rolNombre;
        this.rolNemonico = rolNemonico;
        this.rolTipoClave = rolTipoClave;
        this.rolDiasVigencia = rolDiasVigencia;
        this.rolEstado = rolEstado;
    }

    public Integer getRolCodigo() {
        return rolCodigo;
    }

    public void setRolCodigo(Integer rolCodigo) {
        this.rolCodigo = rolCodigo;
    }

    public String getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(String rolNombre) {
        this.rolNombre = rolNombre;
    }

    public String getRolNemonico() {
        return rolNemonico;
    }

    public void setRolNemonico(String rolNemonico) {
        this.rolNemonico = rolNemonico;
    }

    public String getRolTipoClave() {
        return rolTipoClave;
    }

    public void setRolTipoClave(String rolTipoClave) {
        this.rolTipoClave = rolTipoClave;
    }

    public int getRolDiasVigencia() {
        return rolDiasVigencia;
    }

    public void setRolDiasVigencia(int rolDiasVigencia) {
        this.rolDiasVigencia = rolDiasVigencia;
    }

    public boolean getRolEstado() {
        return rolEstado;
    }

    public void setRolEstado(boolean rolEstado) {
        this.rolEstado = rolEstado;
    }

    public SegAplicacion getApliCodigo() {
        return apliCodigo;
    }

    public void setApliCodigo(SegAplicacion apliCodigo) {
        this.apliCodigo = apliCodigo;
    }

    public List<SegUsuarioRol> getSegUsuarioRolList() {
        return segUsuarioRolList;
    }

    public void setSegUsuarioRolList(List<SegUsuarioRol> segUsuarioRolList) {
        this.segUsuarioRolList = segUsuarioRolList;
    }

    public List<SegPermisoRol> getSegPermisoRolList() {
        return segPermisoRolList;
    }

    public void setSegPermisoRolList(List<SegPermisoRol> segPermisoRolList) {
        this.segPermisoRolList = segPermisoRolList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolCodigo != null ? rolCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SegRol)) {
            return false;
        }
        SegRol other = (SegRol) object;
        if ((this.rolCodigo == null && other.rolCodigo != null) || (this.rolCodigo != null && !this.rolCodigo.equals(other.rolCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sirec.ejb.entidades.SegRol[ rolCodigo=" + rolCodigo + " ]";
    }
    
}
