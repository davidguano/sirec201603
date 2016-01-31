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
@Table(name = "sirec.predio_archivo")
@NamedQueries({
    @NamedQuery(name = "PredioArchivo.findAll", query = "SELECT p FROM PredioArchivo p")})
public class PredioArchivo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "prearc_codigo")
    private Integer prearcCodigo;
    @Size(max = 2147483647)
    @Column(name = "prearc_nombre")
    private String prearcNombre;
    @Basic(optional = false)
    @NotNull    
    @Column(name = "prearc_data")
    private byte[] prearcData;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "prearc_tipo")
    private String prearcTipo;
    @Size(max = 2147483647)
    @Column(name = "ultacc_detalle")
    private String ultaccDetalle;
    @Column(name = "ultacc_marcatiempo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultaccMarcatiempo;
     @Column(name = "prearc_anio")
    private Integer prearcAnio;
    @JoinColumn(name = "usu_identificacion", referencedColumnName = "usu_identificacion")
    @ManyToOne(optional = false)
    private SegUsuario usuIdentificacion;
    @JoinColumn(name = "catpre_codigo", referencedColumnName = "catpre_codigo")
    @ManyToOne(optional = false)
    private CatastroPredial catpreCodigo;

    public PredioArchivo() {
    }

    public PredioArchivo(Integer prearcCodigo) {
        this.prearcCodigo = prearcCodigo;
    }

    public PredioArchivo(Integer prearcCodigo, byte[] prearcData, String prearcTipo) {
        this.prearcCodigo = prearcCodigo;
        this.prearcData = prearcData;
        this.prearcTipo = prearcTipo;
    }

    public Integer getPrearcCodigo() {
        return prearcCodigo;
    }

    public void setPrearcCodigo(Integer prearcCodigo) {
        this.prearcCodigo = prearcCodigo;
    }

    public String getPrearcNombre() {
        return prearcNombre;
    }

    public void setPrearcNombre(String prearcNombre) {
        this.prearcNombre = prearcNombre;
    }

    public byte[] getPrearcData() {
        return prearcData;
    }

    public void setPrearcData(byte[] prearcData) {
        this.prearcData = prearcData;
    }

    public String getPrearcTipo() {
        return prearcTipo;
    }

    public void setPrearcTipo(String prearcTipo) {
        this.prearcTipo = prearcTipo;
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

    public CatastroPredial getCatpreCodigo() {
        return catpreCodigo;
    }

    public void setCatpreCodigo(CatastroPredial catpreCodigo) {
        this.catpreCodigo = catpreCodigo;
    }

    public Integer getPrearcAnio() {
        return prearcAnio;
    }

    public void setPrearcAnio(Integer prearcAnio) {
        this.prearcAnio = prearcAnio;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prearcCodigo != null ? prearcCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PredioArchivo)) {
            return false;
        }
        PredioArchivo other = (PredioArchivo) object;
        if ((this.prearcCodigo == null && other.prearcCodigo != null) || (this.prearcCodigo != null && !this.prearcCodigo.equals(other.prearcCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sirec.ejb.entidades.PredioArchivo[ prearcCodigo=" + prearcCodigo + " ]";
    }
    
}
