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
@Table(name = "sirec.baja")
@NamedQueries({
    @NamedQuery(name = "Baja.findAll", query = "SELECT b FROM Baja b")})
public class Baja implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "baj_codigo")
    private Integer bajCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "baj_fecha")
    @Temporal(TemporalType.DATE)
    private Date bajFecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "baj_tipo")
    private String bajTipo;
    @Size(max = 2147483647)
    @Column(name = "baj_observaciones")
    private String bajObservaciones;
    @Size(max = 20)
    @Column(name = "baj_nombre_archivo")
    private String bajNombreArchivo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "baj_estado")
    private String bajEstado;
    @JoinColumn(name = "usu_identificacion", referencedColumnName = "usu_identificacion")
    @ManyToOne(optional = false)
    private SegUsuario usuIdentificacion;
    @JoinColumn(name = "recdet_codigo", referencedColumnName = "recdet_codigo")
    @ManyToOne(optional = false)
    private RecaudacionDet recdetCodigo;
    

    public Baja() {
    }

    public Baja(Integer bajCodigo) {
        this.bajCodigo = bajCodigo;
    }

    public Baja(Integer bajCodigo, Date bajFecha, String bajTipo, String bajEstado) {
        this.bajCodigo = bajCodigo;
        this.bajFecha = bajFecha;
        this.bajTipo = bajTipo;
        this.bajEstado = bajEstado;
    }

    public Integer getBajCodigo() {
        return bajCodigo;
    }

    public void setBajCodigo(Integer bajCodigo) {
        this.bajCodigo = bajCodigo;
    }

    public Date getBajFecha() {
        return bajFecha;
    }

    public void setBajFecha(Date bajFecha) {
        this.bajFecha = bajFecha;
    }

    public String getBajTipo() {
        return bajTipo;
    }

    public void setBajTipo(String bajTipo) {
        this.bajTipo = bajTipo;
    }

    public String getBajObservaciones() {
        return bajObservaciones;
    }

    public void setBajObservaciones(String bajObservaciones) {
        this.bajObservaciones = bajObservaciones;
    }

    public String getBajNombreArchivo() {
        return bajNombreArchivo;
    }

    public void setBajNombreArchivo(String bajNombreArchivo) {
        this.bajNombreArchivo = bajNombreArchivo;
    }

    public String getBajEstado() {
        return bajEstado;
    }

    public void setBajEstado(String bajEstado) {
        this.bajEstado = bajEstado;
    }

    public SegUsuario getUsuIdentificacion() {
        return usuIdentificacion;
    }

    public void setUsuIdentificacion(SegUsuario usuIdentificacion) {
        this.usuIdentificacion = usuIdentificacion;
    }

    public RecaudacionDet getRecdetCodigo() {
        return recdetCodigo;
    }

    public void setRecdetCodigo(RecaudacionDet recdetCodigo) {
        this.recdetCodigo = recdetCodigo;
    }

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bajCodigo != null ? bajCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Baja)) {
            return false;
        }
        Baja other = (Baja) object;
        if ((this.bajCodigo == null && other.bajCodigo != null) || (this.bajCodigo != null && !this.bajCodigo.equals(other.bajCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sirec.ejb.entidades.Baja[ bajCodigo=" + bajCodigo + " ]";
    }
    
}
