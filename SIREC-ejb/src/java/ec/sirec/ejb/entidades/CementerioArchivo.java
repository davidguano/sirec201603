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
import javax.validation.constraints.Size;

/**
 *
 * @author DAVID GUAN
 */
@Entity
@Table(name = "sirec.cementerio_archivo")
@NamedQueries({
    @NamedQuery(name = "CementerioArchivo.findAll", query = "SELECT c FROM CementerioArchivo c")})
public class CementerioArchivo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cemarc_codigo")
    private Integer cemarcCodigo;
    @Size(max = 2147483647)
    @Column(name = "cemarc_nombre")
    private String cemarcNombre;
    @Column(name = "cemarc_data")
    private byte[] cemarcData;
    @Size(max = 2)
    @Column(name = "cemarc_tipo")
    private String cemarcTipo;
    @Size(max = 20)
    @Column(name = "usu_identificacion")
    private String usuIdentificacion;
    @Size(max = 2147483647)
    @Column(name = "ultacc_detalle")
    private String ultaccDetalle;
    @Column(name = "ultacc_marca_tiempo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultaccMarcaTiempo;
    @JoinColumn(name = "cem_codigo", referencedColumnName = "cem_codigo")
    @ManyToOne(optional = false)
    private Cementerio cemCodigo;

    public CementerioArchivo() {
    }

    public CementerioArchivo(Integer cemarcCodigo) {
        this.cemarcCodigo = cemarcCodigo;
    }

    public Integer getCemarcCodigo() {
        return cemarcCodigo;
    }

    public void setCemarcCodigo(Integer cemarcCodigo) {
        this.cemarcCodigo = cemarcCodigo;
    }

    public String getCemarcNombre() {
        return cemarcNombre;
    }

    public void setCemarcNombre(String cemarcNombre) {
        this.cemarcNombre = cemarcNombre;
    }

    public byte[] getCemarcData() {
        return cemarcData;
    }

    public void setCemarcData(byte[] cemarcData) {
        this.cemarcData = cemarcData;
    }

    public String getCemarcTipo() {
        return cemarcTipo;
    }

    public void setCemarcTipo(String cemarcTipo) {
        this.cemarcTipo = cemarcTipo;
    }

    public String getUsuIdentificacion() {
        return usuIdentificacion;
    }

    public void setUsuIdentificacion(String usuIdentificacion) {
        this.usuIdentificacion = usuIdentificacion;
    }

    public String getUltaccDetalle() {
        return ultaccDetalle;
    }

    public void setUltaccDetalle(String ultaccDetalle) {
        this.ultaccDetalle = ultaccDetalle;
    }

    public Date getUltaccMarcaTiempo() {
        return ultaccMarcaTiempo;
    }

    public void setUltaccMarcaTiempo(Date ultaccMarcaTiempo) {
        this.ultaccMarcaTiempo = ultaccMarcaTiempo;
    }

    public Cementerio getCemCodigo() {
        return cemCodigo;
    }

    public void setCemCodigo(Cementerio cemCodigo) {
        this.cemCodigo = cemCodigo;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cemarcCodigo != null ? cemarcCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CementerioArchivo)) {
            return false;
        }
        CementerioArchivo other = (CementerioArchivo) object;
        if ((this.cemarcCodigo == null && other.cemarcCodigo != null) || (this.cemarcCodigo != null && !this.cemarcCodigo.equals(other.cemarcCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sirec.ejb.entidades.CementerioArchivo[ cemarcCodigo=" + cemarcCodigo + " ]";
    }
    
}
