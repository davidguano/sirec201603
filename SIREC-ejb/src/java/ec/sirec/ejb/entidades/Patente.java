/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.sirec.ejb.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "sirec.patente")
@NamedQueries({
    @NamedQuery(name = "Patente.findAll", query = "SELECT p FROM Patente p")})
public class Patente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pat_codigo")
    private Integer patCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "pat_estado")
    private String patEstado;
    @Size(max = 200)
    @Column(name = "pat_representante_legal")
    private String patRepresentanteLegal;
    @Size(max = 200)
    @Column(name = "pat_contador")
    private String patContador;
    @Size(max = 2147483647)
    @Column(name = "pat_nombre_comercial")
    private String patNombreComercial;
    @Size(max = 13)
    @Column(name = "pat_ruc_contador")
    private String patRucContador;
    @Size(max = 10)
    @Column(name = "pat_placa")
    private String patPlaca;
    @Size(max = 2147483647)
    @Column(name = "pat_desc_act_eco")
    private String patDescActEco;
    @Column(name = "pat_inicio_act_eco")
    @Temporal(TemporalType.DATE)
    private Date patInicioActEco;
    @Column(name = "pat_artesano_calificado")
    private Boolean patArtesanoCalificado;
    @Column(name = "pat_obligado_cont")
    private Boolean patObligadoCont;
    @Size(max = 5)
    @Column(name = "pat_horario_desde")
    private String patHorarioDesde;
    @Size(max = 5)
    @Column(name = "pat_horario_hasta")
    private String patHorarioHasta;
    @Column(name = "pat_func_lunes")
    private Boolean patFuncLunes;
    @Column(name = "pat_func_martes")
    private Boolean patFuncMartes;
    @Column(name = "pat_func_jueves")
    private Boolean patFuncJueves;
    @Column(name = "pat_func_miercoles")
    private Boolean patFuncMiercoles;
    @Column(name = "pat_func_viernes")
    private Boolean patFuncViernes;
    @Column(name = "pat_func_sabado")
    private Boolean patFuncSabado;
    @Column(name = "pat_func_domingo")
    private Boolean patFuncDomingo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "pat_area_local")
    private Double patAreaLocal;
    @Column(name = "pat_local_aforo")
    private Integer patLocalAforo;
    @Size(max = 200)
    @Column(name = "pat_categoria_turistica")
    private String patCategoriaTuristica;
    @Size(max = 20)
    @Column(name = "pat_num_reg_turistico")
    private String patNumRegTuristico;
    @Size(max = 2147483647)
    @Column(name = "pat_observaciones")
    private String patObservaciones;
    @Size(max = 2147483647)
    @Column(name = "ultacc_detalle")
    private String ultaccDetalle;
    @Column(name = "ultacc_marcatiempo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultaccMarcatiempo;
     @Column(name = "pat_fecha_adjudicacion")
    @Temporal(TemporalType.DATE)
    private Date patFechaAdjudicacion;
    @Column(name = "pat_fecha_vencimiento")
    @Temporal(TemporalType.DATE)
    private Date patFechaVencimiento;
    @Column(name = "pat_deuda_inicial")
    private BigDecimal patDeudaInicial;
   
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patCodigo")
    private List<PatenteArchivo> patenteArchivoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patCodigo")
    private List<PatenteValoracion> patenteValoracionList;
    @JoinColumn(name = "usu_identificacion", referencedColumnName = "usu_identificacion")
    @ManyToOne(optional = false)
    private SegUsuario usuIdentificacion;
    @JoinColumn(name = "catpre_codigo", referencedColumnName = "catpre_codigo")
    @ManyToOne(optional = false)
    private CatastroPredial catpreCodigo;
    @JoinColumn(name = "catdet_horario_func", referencedColumnName = "catdet_codigo")
    @ManyToOne(optional = false)
    private CatalogoDetalle catdetHorarioFunc;
    @JoinColumn(name = "catdet_tipo_act_eco", referencedColumnName = "catdet_codigo")
    @ManyToOne(optional = false)
    private CatalogoDetalle catdetTipoActEco;
    @JoinColumn(name = "catdet_tipo_local", referencedColumnName = "catdet_codigo")
    @ManyToOne(optional = false)
    private CatalogoDetalle catdetTipoLocal;
    @JoinColumn(name = "catdet_tipo_empresa", referencedColumnName = "catdet_codigo")
    @ManyToOne(optional = false)
    private CatalogoDetalle catdetTipoEmpresa;
    @JoinColumn(name = "catdet_tipo_est", referencedColumnName = "catdet_codigo")
    @ManyToOne(optional = false)
    private CatalogoDetalle catdetTipoEst;
    @JoinColumn(name = "catdet_especialidad", referencedColumnName = "catdet_codigo")
    @ManyToOne(optional = false)
    private CatalogoDetalle catdetEspecialidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patCodigo")
    private List<Patente15xmilValoracion> patente15xmilValoracionList;

    public Patente() {
    }

    public Patente(Integer patCodigo) {
        this.patCodigo = patCodigo;
    }

    public Patente(Integer patCodigo, String patEstado) {
        this.patCodigo = patCodigo;
        this.patEstado = patEstado;
    }

    public Integer getPatCodigo() {
        return patCodigo;
    }

    public void setPatCodigo(Integer patCodigo) {
        this.patCodigo = patCodigo;
    }

    public String getPatEstado() {
        return patEstado;
    }

    public void setPatEstado(String patEstado) {
        this.patEstado = patEstado;
    }

    public String getPatRepresentanteLegal() {
        return patRepresentanteLegal;
    }

    public void setPatRepresentanteLegal(String patRepresentanteLegal) {
        this.patRepresentanteLegal = patRepresentanteLegal;
    }

    public String getPatContador() {
        return patContador;
    }

    public void setPatContador(String patContador) {
        this.patContador = patContador;
    }

    public String getPatNombreComercial() {
        return patNombreComercial;
    }

    public void setPatNombreComercial(String patNombreComercial) {
        this.patNombreComercial = patNombreComercial;
    }

    public String getPatRucContador() {
        return patRucContador;
    }

    public void setPatRucContador(String patRucContador) {
        this.patRucContador = patRucContador;
    }

    public String getPatPlaca() {
        return patPlaca;
    }

    public void setPatPlaca(String patPlaca) {
        this.patPlaca = patPlaca;
    }

    public String getPatDescActEco() {
        return patDescActEco;
    }

    public void setPatDescActEco(String patDescActEco) {
        this.patDescActEco = patDescActEco;
    }

    public Date getPatInicioActEco() {
        return patInicioActEco;
    }

    public void setPatInicioActEco(Date patInicioActEco) {
        this.patInicioActEco = patInicioActEco;
    }

    public Boolean getPatArtesanoCalificado() {
        return patArtesanoCalificado;
    }

    public void setPatArtesanoCalificado(Boolean patArtesanoCalificado) {
        this.patArtesanoCalificado = patArtesanoCalificado;
    }

    public Boolean getPatObligadoCont() {
        return patObligadoCont;
    }

    public void setPatObligadoCont(Boolean patObligadoCont) {
        this.patObligadoCont = patObligadoCont;
    }

    public String getPatHorarioDesde() {
        return patHorarioDesde;
    }

    public void setPatHorarioDesde(String patHorarioDesde) {
        this.patHorarioDesde = patHorarioDesde;
    }

    public String getPatHorarioHasta() {
        return patHorarioHasta;
    }

    public void setPatHorarioHasta(String patHorarioHasta) {
        this.patHorarioHasta = patHorarioHasta;
    }

    public Boolean getPatFuncLunes() {
        return patFuncLunes;
    }

    public void setPatFuncLunes(Boolean patFuncLunes) {
        this.patFuncLunes = patFuncLunes;
    }

    public Boolean getPatFuncMartes() {
        return patFuncMartes;
    }

    public void setPatFuncMartes(Boolean patFuncMartes) {
        this.patFuncMartes = patFuncMartes;
    }

    public Boolean getPatFuncJueves() {
        return patFuncJueves;
    }

    public void setPatFuncJueves(Boolean patFuncJueves) {
        this.patFuncJueves = patFuncJueves;
    }

    public Boolean getPatFuncMiercoles() {
        return patFuncMiercoles;
    }

    public void setPatFuncMiercoles(Boolean patFuncMiercoles) {
        this.patFuncMiercoles = patFuncMiercoles;
    }

    public Boolean getPatFuncViernes() {
        return patFuncViernes;
    }

    public void setPatFuncViernes(Boolean patFuncViernes) {
        this.patFuncViernes = patFuncViernes;
    }

    public Boolean getPatFuncSabado() {
        return patFuncSabado;
    }

    public void setPatFuncSabado(Boolean patFuncSabado) {
        this.patFuncSabado = patFuncSabado;
    }

    public Boolean getPatFuncDomingo() {
        return patFuncDomingo;
    }

    public void setPatFuncDomingo(Boolean patFuncDomingo) {
        this.patFuncDomingo = patFuncDomingo;
    }

    public Double getPatAreaLocal() {
        return patAreaLocal;
    }

    public void setPatAreaLocal(Double patAreaLocal) {
        this.patAreaLocal = patAreaLocal;
    }

    public Integer getPatLocalAforo() {
        return patLocalAforo;
    }

    public void setPatLocalAforo(Integer patLocalAforo) {
        this.patLocalAforo = patLocalAforo;
    }

    public String getPatCategoriaTuristica() {
        return patCategoriaTuristica;
    }

    public void setPatCategoriaTuristica(String patCategoriaTuristica) {
        this.patCategoriaTuristica = patCategoriaTuristica;
    }

    public String getPatNumRegTuristico() {
        return patNumRegTuristico;
    }

    public void setPatNumRegTuristico(String patNumRegTuristico) {
        this.patNumRegTuristico = patNumRegTuristico;
    }

    public String getPatObservaciones() {
        return patObservaciones;
    }

    public void setPatObservaciones(String patObservaciones) {
        this.patObservaciones = patObservaciones;
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

    public List<PatenteArchivo> getPatenteArchivoList() {
        return patenteArchivoList;
    }

    public void setPatenteArchivoList(List<PatenteArchivo> patenteArchivoList) {
        this.patenteArchivoList = patenteArchivoList;
    }

    public List<PatenteValoracion> getPatenteValoracionList() {
        return patenteValoracionList;
    }

    public void setPatenteValoracionList(List<PatenteValoracion> patenteValoracionList) {
        this.patenteValoracionList = patenteValoracionList;
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

    public CatalogoDetalle getCatdetHorarioFunc() {
        return catdetHorarioFunc;
    }

    public void setCatdetHorarioFunc(CatalogoDetalle catdetHorarioFunc) {
        this.catdetHorarioFunc = catdetHorarioFunc;
    }

    public CatalogoDetalle getCatdetTipoActEco() {
        return catdetTipoActEco;
    }

    public void setCatdetTipoActEco(CatalogoDetalle catdetTipoActEco) {
        this.catdetTipoActEco = catdetTipoActEco;
    }

    public CatalogoDetalle getCatdetTipoLocal() {
        return catdetTipoLocal;
    }

    public void setCatdetTipoLocal(CatalogoDetalle catdetTipoLocal) {
        this.catdetTipoLocal = catdetTipoLocal;
    }

    public CatalogoDetalle getCatdetTipoEmpresa() {
        return catdetTipoEmpresa;
    }

    public Date getPatFechaAdjudicacion() {
        return patFechaAdjudicacion;
    }

    public void setPatFechaAdjudicacion(Date patFechaAdjudicacion) {
        this.patFechaAdjudicacion = patFechaAdjudicacion;
    }

    public Date getPatFechaVencimiento() {
        return patFechaVencimiento;
    }

    public void setPatFechaVencimiento(Date patFechaVencimiento) {
        this.patFechaVencimiento = patFechaVencimiento;
    }

    public BigDecimal getPatDeudaInicial() {
        return patDeudaInicial;
    }

    public void setPatDeudaInicial(BigDecimal patDeudaInicial) {
        this.patDeudaInicial = patDeudaInicial;
    }

    public void setCatdetTipoEmpresa(CatalogoDetalle catdetTipoEmpresa) {
        this.catdetTipoEmpresa = catdetTipoEmpresa;
    }

    public CatalogoDetalle getCatdetTipoEst() {
        return catdetTipoEst;
    }

    public void setCatdetTipoEst(CatalogoDetalle catdetTipoEst) {
        this.catdetTipoEst = catdetTipoEst;
    }

    public CatalogoDetalle getCatdetEspecialidad() {
        return catdetEspecialidad;
    }

    public void setCatdetEspecialidad(CatalogoDetalle catdetEspecialidad) {
        this.catdetEspecialidad = catdetEspecialidad;
    }

    public List<Patente15xmilValoracion> getPatente15xmilValoracionList() {
        return patente15xmilValoracionList;
    }

    public void setPatente15xmilValoracionList(List<Patente15xmilValoracion> patente15xmilValoracionList) {
        this.patente15xmilValoracionList = patente15xmilValoracionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (patCodigo != null ? patCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Patente)) {
            return false;
        }
        Patente other = (Patente) object;
        if ((this.patCodigo == null && other.patCodigo != null) || (this.patCodigo != null && !this.patCodigo.equals(other.patCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sirec.ejb.entidades.Patente[ patCodigo=" + patCodigo + " ]";
    }
    
}
