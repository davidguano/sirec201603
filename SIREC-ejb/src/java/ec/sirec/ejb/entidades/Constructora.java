/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.sirec.ejb.entidades;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author DAVID GUAN
 */
@Entity
@Table(name = "sirec.constructora")
@NamedQueries({
    @NamedQuery(name = "Constructora.findAll", query = "SELECT c FROM Constructora c")})
public class Constructora implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "con_codigo")
    private Integer conCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "con_tipo")
    private String conTipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "con_tipo_persona")
    private String conTipoPersona;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 13)
    @Column(name = "con_identificacion")
    private String conIdentificacion;
    @Size(max = 200)
    @Column(name = "con_apellidos")
    private String conApellidos;
    @Size(max = 200)
    @Column(name = "con_nombres")
    private String conNombres;
    @Column(name = "con_fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date conFechaNacimiento;
    @Size(max = 2147483647)
    @Column(name = "con_razon_social")
    private String conRazonSocial;
    @Size(max = 2147483647)
    @Column(name = "con_direccion")
    private String conDireccion;
    @Size(max = 100)
    @Column(name = "con_telefono")
    private String conTelefono;
    @Size(max = 100)
    @Column(name = "con_correo")
    private String conCorreo;
    @Column(name = "con_discapacidad")
    private Boolean conDiscapacidad;
    @Size(max = 2147483647)
    @Column(name = "con_observaciones")
    private String conObservaciones;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conCodigo")
    private List<ObraProyecto> obraProyectoList;
    @JoinColumn(name = "catdet_ciudad", referencedColumnName = "catdet_codigo")
    @ManyToOne(optional = false)
    private CatalogoDetalle catdetCiudad;

    public Constructora() {
    }

    public Constructora(Integer conCodigo) {
        this.conCodigo = conCodigo;
    }

    public Constructora(Integer conCodigo, String conTipo, String conTipoPersona, String conIdentificacion) {
        this.conCodigo = conCodigo;
        this.conTipo = conTipo;
        this.conTipoPersona = conTipoPersona;
        this.conIdentificacion = conIdentificacion;
    }

    public Integer getConCodigo() {
        return conCodigo;
    }

    public void setConCodigo(Integer conCodigo) {
        this.conCodigo = conCodigo;
    }

    public String getConTipo() {
        return conTipo;
    }

    public void setConTipo(String conTipo) {
        this.conTipo = conTipo;
    }

    public String getConTipoPersona() {
        return conTipoPersona;
    }

    public void setConTipoPersona(String conTipoPersona) {
        this.conTipoPersona = conTipoPersona;
    }

    public String getConIdentificacion() {
        return conIdentificacion;
    }

    public void setConIdentificacion(String conIdentificacion) {
        this.conIdentificacion = conIdentificacion;
    }

    public String getConApellidos() {
        return conApellidos;
    }

    public void setConApellidos(String conApellidos) {
        this.conApellidos = conApellidos;
    }

    public String getConNombres() {
        return conNombres;
    }

    public void setConNombres(String conNombres) {
        this.conNombres = conNombres;
    }

    public Date getConFechaNacimiento() {
        return conFechaNacimiento;
    }

    public void setConFechaNacimiento(Date conFechaNacimiento) {
        this.conFechaNacimiento = conFechaNacimiento;
    }

    public String getConRazonSocial() {
        return conRazonSocial;
    }

    public void setConRazonSocial(String conRazonSocial) {
        this.conRazonSocial = conRazonSocial;
    }

    public String getConDireccion() {
        return conDireccion;
    }

    public void setConDireccion(String conDireccion) {
        this.conDireccion = conDireccion;
    }

    public String getConTelefono() {
        return conTelefono;
    }

    public void setConTelefono(String conTelefono) {
        this.conTelefono = conTelefono;
    }

    public String getConCorreo() {
        return conCorreo;
    }

    public void setConCorreo(String conCorreo) {
        this.conCorreo = conCorreo;
    }

    public Boolean getConDiscapacidad() {
        return conDiscapacidad;
    }

    public void setConDiscapacidad(Boolean conDiscapacidad) {
        this.conDiscapacidad = conDiscapacidad;
    }

    public String getConObservaciones() {
        return conObservaciones;
    }

    public void setConObservaciones(String conObservaciones) {
        this.conObservaciones = conObservaciones;
    }

    public List<ObraProyecto> getObraProyectoList() {
        return obraProyectoList;
    }

    public void setObraProyectoList(List<ObraProyecto> obraProyectoList) {
        this.obraProyectoList = obraProyectoList;
    }

    public CatalogoDetalle getCatdetCiudad() {
        return catdetCiudad;
    }

    public void setCatdetCiudad(CatalogoDetalle catdetCiudad) {
        this.catdetCiudad = catdetCiudad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (conCodigo != null ? conCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Constructora)) {
            return false;
        }
        Constructora other = (Constructora) object;
        if ((this.conCodigo == null && other.conCodigo != null) || (this.conCodigo != null && !this.conCodigo.equals(other.conCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sirec.ejb.entidades.Constructora[ conCodigo=" + conCodigo + " ]";
    }
    
}
