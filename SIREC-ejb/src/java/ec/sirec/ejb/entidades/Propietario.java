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
@Table(name = "sirec.propietario")
@NamedQueries({
    @NamedQuery(name = "Propietario.findAll", query = "SELECT p FROM Propietario p")})
public class Propietario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 13)
    @Column(name = "pro_ci")
    private String proCi;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "pro_tipo_persona")
    private String proTipoPersona;
    @Size(max = 200)
    @Column(name = "pro_apellidos")
    private String proApellidos;
    @Size(max = 200)
    @Column(name = "pro_nombres")
    private String proNombres;
    @Column(name = "pro_fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date proFechaNacimiento;
    @Size(max = 2147483647)
    @Column(name = "pro_razon_social")
    private String proRazonSocial;
    @Size(max = 2147483647)
    @Column(name = "pro_direccion")
    private String proDireccion;
    @Size(max = 100)
    @Column(name = "pro_telefono")
    private String proTelefono;
    @Size(max = 100)
    @Column(name = "pro_correo")
    private String proCorreo;
    @Column(name = "pro_discapacidad")
    private Boolean proDiscapacidad;
    @Size(max = 2147483647)
    @Column(name = "pro_observaciones")
    private String proObservaciones;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proCi")
    private List<RecaudacionCab> recaudacionCabList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proCi")
    private List<Cementerio> cementerioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proCi")
    private List<Servicios> serviciosList;
    @JoinColumn(name = "usu_identificacion", referencedColumnName = "usu_identificacion")
    @ManyToOne(optional = false)
    private SegUsuario usuIdentificacion;
    @JoinColumn(name = "catdet_ciudad", referencedColumnName = "catdet_codigo")
    @ManyToOne(optional = false)
    private CatalogoDetalle catdetCiudad;
    @JoinColumn(name = "catdet_tipoperjur", referencedColumnName = "catdet_codigo")
    @ManyToOne
    private CatalogoDetalle catdetTipoperjur;

    public Propietario() {
        catdetCiudad=new CatalogoDetalle();
        catdetTipoperjur=new CatalogoDetalle();
    }

    public Propietario(String proCi) {
        this.proCi = proCi;
    }

    public Propietario(String proCi, String proTipoPersona, String proApellidos, String proNombres, Date proFechaNacimiento, String proRazonSocial) {
        this.proCi = proCi;
        this.proTipoPersona = proTipoPersona;
        this.proApellidos = proApellidos;
        this.proNombres = proNombres;
        this.proFechaNacimiento = proFechaNacimiento;
        this.proRazonSocial = proRazonSocial;
    }

    public String getProCi() {
        return proCi;
    }

    public void setProCi(String proCi) {
        this.proCi = proCi;
    }

    public String getProTipoPersona() {
        return proTipoPersona;
    }

    public void setProTipoPersona(String proTipoPersona) {
        this.proTipoPersona = proTipoPersona;
    }

    public String getProApellidos() {
        return proApellidos;
    }

    public void setProApellidos(String proApellidos) {
        this.proApellidos = proApellidos;
    }

    public String getProNombres() {
        return proNombres;
    }

    public void setProNombres(String proNombres) {
        this.proNombres = proNombres;
    }

    public Date getProFechaNacimiento() {
        return proFechaNacimiento;
    }

    public void setProFechaNacimiento(Date proFechaNacimiento) {
        this.proFechaNacimiento = proFechaNacimiento;
    }

    public String getProRazonSocial() {
        return proRazonSocial;
    }

    public void setProRazonSocial(String proRazonSocial) {
        this.proRazonSocial = proRazonSocial;
    }

    public String getProDireccion() {
        return proDireccion;
    }

    public void setProDireccion(String proDireccion) {
        this.proDireccion = proDireccion;
    }

    public String getProTelefono() {
        return proTelefono;
    }

    public void setProTelefono(String proTelefono) {
        this.proTelefono = proTelefono;
    }

    public String getProCorreo() {
        return proCorreo;
    }

    public void setProCorreo(String proCorreo) {
        this.proCorreo = proCorreo;
    }

    public Boolean getProDiscapacidad() {
        return proDiscapacidad;
    }

    public void setProDiscapacidad(Boolean proDiscapacidad) {
        this.proDiscapacidad = proDiscapacidad;
    }

    public String getProObservaciones() {
        return proObservaciones;
    }

    public void setProObservaciones(String proObservaciones) {
        this.proObservaciones = proObservaciones;
    }

    public List<RecaudacionCab> getRecaudacionCabList() {
        return recaudacionCabList;
    }

    public void setRecaudacionCabList(List<RecaudacionCab> recaudacionCabList) {
        this.recaudacionCabList = recaudacionCabList;
    }


    public List<Cementerio> getCementerioList() {
        return cementerioList;
    }

    public void setCementerioList(List<Cementerio> cementerioList) {
        this.cementerioList = cementerioList;
    }

    public List<Servicios> getServiciosList() {
        return serviciosList;
    }

    public void setServiciosList(List<Servicios> serviciosList) {
        this.serviciosList = serviciosList;
    }

    public SegUsuario getUsuIdentificacion() {
        return usuIdentificacion;
    }

    public void setUsuIdentificacion(SegUsuario usuIdentificacion) {
        this.usuIdentificacion = usuIdentificacion;
    }

    public CatalogoDetalle getCatdetCiudad() {
        return catdetCiudad;
    }

    public void setCatdetCiudad(CatalogoDetalle catdetCiudad) {
        this.catdetCiudad = catdetCiudad;
    }

    public CatalogoDetalle getCatdetTipoperjur() {
        if(catdetTipoperjur==null){
            catdetTipoperjur=new CatalogoDetalle();
        }
        return catdetTipoperjur;
    }

    public void setCatdetTipoperjur(CatalogoDetalle catdetTipoperjur) {
        this.catdetTipoperjur = catdetTipoperjur;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proCi != null ? proCi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Propietario)) {
            return false;
        }
        Propietario other = (Propietario) object;
        if ((this.proCi == null && other.proCi != null) || (this.proCi != null && !this.proCi.equals(other.proCi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sirec.ejb.entidades.Propietario[ proCi=" + proCi + " ]";
    }
    
}
