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
import javax.persistence.Lob;
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
@Table(name = "sirec.patente_archivo")
@NamedQueries({
    @NamedQuery(name = "PatenteArchivo.findAll", query = "SELECT p FROM PatenteArchivo p")})
public class PatenteArchivo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "patarc_codigo")
    private Integer patarcCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "patarc_nombre")
    private String patarcNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "patarc_data")
    private byte[] patarcData;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "patarc_tipo")
    private String patarcTipo;
    @Size(max = 2147483647)
    @Column(name = "ultacc_detalle")
    private String ultaccDetalle;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ultacc_marcatiempo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultaccMarcatiempo;
    @JoinColumn(name = "usu_identificacion", referencedColumnName = "usu_identificacion")
    @ManyToOne(optional = false)
    private SegUsuario usuIdentificacion;
    @JoinColumn(name = "pat_codigo", referencedColumnName = "pat_codigo")
    @ManyToOne(optional = false)
    private Patente patCodigo;

    public PatenteArchivo() {
    }

    public PatenteArchivo(Integer patarcCodigo) {
        this.patarcCodigo = patarcCodigo;
    }

    public PatenteArchivo(Integer patarcCodigo, String patarcNombre, byte[] patarcData, String patarcTipo, Date ultaccMarcatiempo) {
        this.patarcCodigo = patarcCodigo;
        this.patarcNombre = patarcNombre;
        this.patarcData = patarcData;
        this.patarcTipo = patarcTipo;
        this.ultaccMarcatiempo = ultaccMarcatiempo;
    }

    public Integer getPatarcCodigo() {
        return patarcCodigo;
    }

    public void setPatarcCodigo(Integer patarcCodigo) {
        this.patarcCodigo = patarcCodigo;
    }

    public String getPatarcNombre() {
        return patarcNombre;
    }

    public void setPatarcNombre(String patarcNombre) {
        this.patarcNombre = patarcNombre;
    }

    public byte[] getPatarcData() {
        return patarcData;
    }

    public void setPatarcData(byte[] patarcData) {
        this.patarcData = patarcData;
    }

    public String getPatarcTipo() {
        return patarcTipo;
    }

    public void setPatarcTipo(String patarcTipo) {
        this.patarcTipo = patarcTipo;
    }

    public String getUltaccDetalle() {
        return ultaccDetalle;
    }

    public void setUltaccDetalle(String ultaccDetalle) {
        this.ultaccDetalle = ultaccDetalle;
    }

    public Date getUltaccMarcatiempo() {
        return ultaccMarcatiempo;
    }

    public void setUltaccMarcatiempo(Date ultaccMarcatiempo) {
        this.ultaccMarcatiempo = ultaccMarcatiempo;
    }

    public SegUsuario getUsuIdentificacion() {
        return usuIdentificacion;
    }

    public void setUsuIdentificacion(SegUsuario usuIdentificacion) {
        this.usuIdentificacion = usuIdentificacion;
    }

    public Patente getPatCodigo() {
        return patCodigo;
    }

    public void setPatCodigo(Patente patCodigo) {
        this.patCodigo = patCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (patarcCodigo != null ? patarcCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatenteArchivo)) {
            return false;
        }
        PatenteArchivo other = (PatenteArchivo) object;
        if ((this.patarcCodigo == null && other.patarcCodigo != null) || (this.patarcCodigo != null && !this.patarcCodigo.equals(other.patarcCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sirec.ejb.entidades.PatenteArchivo[ patarcCodigo=" + patarcCodigo + " ]";
    }
    
}
