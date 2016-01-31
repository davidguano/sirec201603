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
@Table(name = "sirec.seg_usuario")
@NamedQueries({
    @NamedQuery(name = "SegUsuario.findAll", query = "SELECT s FROM SegUsuario s")})
public class SegUsuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 13)
    @Column(name = "usu_identificacion")
    private String usuIdentificacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "usu_nombres")
    private String usuNombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "usu_apellidos")
    private String usuApellidos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "usu_usuario")
    private String usuUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "usu_clave")
    private String usuClave;
    @Basic(optional = false)
    @NotNull
    @Column(name = "usu_fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date usuFechaCreacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "usu_fecha_modificacion")
    @Temporal(TemporalType.DATE)
    private Date usuFechaModificacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "usu_estado")
    private boolean usuEstado;
    @Size(max = 100)
    @Column(name = "usu_mail")
    private String usuMail;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuIdentificacion")
    private List<RecaudacionCab> recaudacionCabList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuIdentificacion")
    private List<PatenteArchivo> patenteArchivoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuIdentificacion")
    private List<CatastroPredial> catastroPredialList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuIdentificacion")
    private List<SegBitacora> segBitacoraList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuIdentificacion")
    private List<SegUsuarioRol> segUsuarioRolList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuIdentificacion")
    private List<Patente> patenteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuIdentificacion")
    private List<PredioArchivo> predioArchivoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuIdentificacion")
    private List<Cementerio> cementerioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuIdentificacion")
    private List<Baja> bajaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuIdentificacion")
    private List<ConvenioPago> convenioPagoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuIdentificacion")
    private List<Propietario> propietarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuIdentificacion")
    private List<Coactiva> coactivaList;

    public SegUsuario() {
    }

    public SegUsuario(String usuIdentificacion) {
        this.usuIdentificacion = usuIdentificacion;
    }

    public SegUsuario(String usuIdentificacion, String usuNombres, String usuApellidos, String usuUsuario, String usuClave, Date usuFechaCreacion, Date usuFechaModificacion, boolean usuEstado) {
        this.usuIdentificacion = usuIdentificacion;
        this.usuNombres = usuNombres;
        this.usuApellidos = usuApellidos;
        this.usuUsuario = usuUsuario;
        this.usuClave = usuClave;
        this.usuFechaCreacion = usuFechaCreacion;
        this.usuFechaModificacion = usuFechaModificacion;
        this.usuEstado = usuEstado;
    }

    public String getUsuIdentificacion() {
        return usuIdentificacion;
    }

    public void setUsuIdentificacion(String usuIdentificacion) {
        this.usuIdentificacion = usuIdentificacion;
    }

    public String getUsuNombres() {
        return usuNombres;
    }

    public void setUsuNombres(String usuNombres) {
        this.usuNombres = usuNombres;
    }

    public String getUsuApellidos() {
        return usuApellidos;
    }

    public void setUsuApellidos(String usuApellidos) {
        this.usuApellidos = usuApellidos;
    }

    public String getUsuUsuario() {
        return usuUsuario;
    }

    public void setUsuUsuario(String usuUsuario) {
        this.usuUsuario = usuUsuario;
    }

    public String getUsuClave() {
        return usuClave;
    }

    public void setUsuClave(String usuClave) {
        this.usuClave = usuClave;
    }

    public Date getUsuFechaCreacion() {
        return usuFechaCreacion;
    }

    public void setUsuFechaCreacion(Date usuFechaCreacion) {
        this.usuFechaCreacion = usuFechaCreacion;
    }

    public Date getUsuFechaModificacion() {
        return usuFechaModificacion;
    }

    public void setUsuFechaModificacion(Date usuFechaModificacion) {
        this.usuFechaModificacion = usuFechaModificacion;
    }

    public boolean getUsuEstado() {
        return usuEstado;
    }

    public void setUsuEstado(boolean usuEstado) {
        this.usuEstado = usuEstado;
    }

    public String getUsuMail() {
        return usuMail;
    }

    public void setUsuMail(String usuMail) {
        this.usuMail = usuMail;
    }

    public List<RecaudacionCab> getRecaudacionCabList() {
        return recaudacionCabList;
    }

    public void setRecaudacionCabList(List<RecaudacionCab> recaudacionCabList) {
        this.recaudacionCabList = recaudacionCabList;
    }

    public List<PatenteArchivo> getPatenteArchivoList() {
        return patenteArchivoList;
    }

    public void setPatenteArchivoList(List<PatenteArchivo> patenteArchivoList) {
        this.patenteArchivoList = patenteArchivoList;
    }

    public List<CatastroPredial> getCatastroPredialList() {
        return catastroPredialList;
    }

    public void setCatastroPredialList(List<CatastroPredial> catastroPredialList) {
        this.catastroPredialList = catastroPredialList;
    }

    public List<SegBitacora> getSegBitacoraList() {
        return segBitacoraList;
    }

    public void setSegBitacoraList(List<SegBitacora> segBitacoraList) {
        this.segBitacoraList = segBitacoraList;
    }

    public List<SegUsuarioRol> getSegUsuarioRolList() {
        return segUsuarioRolList;
    }

    public void setSegUsuarioRolList(List<SegUsuarioRol> segUsuarioRolList) {
        this.segUsuarioRolList = segUsuarioRolList;
    }

    public List<Patente> getPatenteList() {
        return patenteList;
    }

    public void setPatenteList(List<Patente> patenteList) {
        this.patenteList = patenteList;
    }

    public List<PredioArchivo> getPredioArchivoList() {
        return predioArchivoList;
    }

    public void setPredioArchivoList(List<PredioArchivo> predioArchivoList) {
        this.predioArchivoList = predioArchivoList;
    }

    public List<Cementerio> getCementerioList() {
        return cementerioList;
    }

    public void setCementerioList(List<Cementerio> cementerioList) {
        this.cementerioList = cementerioList;
    }

    public List<Baja> getBajaList() {
        return bajaList;
    }

    public void setBajaList(List<Baja> bajaList) {
        this.bajaList = bajaList;
    }

    public List<ConvenioPago> getConvenioPagoList() {
        return convenioPagoList;
    }

    public void setConvenioPagoList(List<ConvenioPago> convenioPagoList) {
        this.convenioPagoList = convenioPagoList;
    }

    public List<Propietario> getPropietarioList() {
        return propietarioList;
    }

    public void setPropietarioList(List<Propietario> propietarioList) {
        this.propietarioList = propietarioList;
    }

    public List<Coactiva> getCoactivaList() {
        return coactivaList;
    }

    public void setCoactivaList(List<Coactiva> coactivaList) {
        this.coactivaList = coactivaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuIdentificacion != null ? usuIdentificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SegUsuario)) {
            return false;
        }
        SegUsuario other = (SegUsuario) object;
        if ((this.usuIdentificacion == null && other.usuIdentificacion != null) || (this.usuIdentificacion != null && !this.usuIdentificacion.equals(other.usuIdentificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sirec.ejb.entidades.SegUsuario[ usuIdentificacion=" + usuIdentificacion + " ]";
    }
    
}
