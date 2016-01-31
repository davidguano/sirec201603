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
@Table(name = "sirec.seg_bitacora")
@NamedQueries({
    @NamedQuery(name = "SegBitacora.findAll", query = "SELECT s FROM SegBitacora s")})
public class SegBitacora implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "bit_codigo")
    private Integer bitCodigo;
    @Size(max = 100)
    @Column(name = "bit_entidad")
    private String bitEntidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "bit_entidad_clave")
    private int bitEntidadClave;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "bit_tipo_accion")
    private String bitTipoAccion;
    @Size(max = 2147483647)
    @Column(name = "bit_detalle_accion")
    private String bitDetalleAccion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "bit_marcatiempo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date bitMarcatiempo;
    @JoinColumn(name = "usu_identificacion", referencedColumnName = "usu_identificacion")
    @ManyToOne(optional = false)
    private SegUsuario usuIdentificacion;

    public SegBitacora() {
    }

    public SegBitacora(Integer bitCodigo) {
        this.bitCodigo = bitCodigo;
    }

    public SegBitacora(Integer bitCodigo, int bitEntidadClave, String bitTipoAccion, Date bitMarcatiempo) {
        this.bitCodigo = bitCodigo;
        this.bitEntidadClave = bitEntidadClave;
        this.bitTipoAccion = bitTipoAccion;
        this.bitMarcatiempo = bitMarcatiempo;
    }

    public Integer getBitCodigo() {
        return bitCodigo;
    }

    public void setBitCodigo(Integer bitCodigo) {
        this.bitCodigo = bitCodigo;
    }

    public String getBitEntidad() {
        return bitEntidad;
    }

    public void setBitEntidad(String bitEntidad) {
        this.bitEntidad = bitEntidad;
    }

    public int getBitEntidadClave() {
        return bitEntidadClave;
    }

    public void setBitEntidadClave(int bitEntidadClave) {
        this.bitEntidadClave = bitEntidadClave;
    }

    public String getBitTipoAccion() {
        return bitTipoAccion;
    }

    public void setBitTipoAccion(String bitTipoAccion) {
        this.bitTipoAccion = bitTipoAccion;
    }

    public String getBitDetalleAccion() {
        return bitDetalleAccion;
    }

    public void setBitDetalleAccion(String bitDetalleAccion) {
        this.bitDetalleAccion = bitDetalleAccion;
    }

    public Date getBitMarcatiempo() {
        return bitMarcatiempo;
    }

    public void setBitMarcatiempo(Date bitMarcatiempo) {
        this.bitMarcatiempo = bitMarcatiempo;
    }

    public SegUsuario getUsuIdentificacion() {
        return usuIdentificacion;
    }

    public void setUsuIdentificacion(SegUsuario usuIdentificacion) {
        this.usuIdentificacion = usuIdentificacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bitCodigo != null ? bitCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SegBitacora)) {
            return false;
        }
        SegBitacora other = (SegBitacora) object;
        if ((this.bitCodigo == null && other.bitCodigo != null) || (this.bitCodigo != null && !this.bitCodigo.equals(other.bitCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sirec.ejb.entidades.SegBitacora[ bitCodigo=" + bitCodigo + " ]";
    }
    
}
