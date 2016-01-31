/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sirec.ejb.entidades;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author DAVID GUAN
 */
@Entity
@Table(name = "sirec.catastro_predial")
@NamedQueries({
    @NamedQuery(name = "CatastroPredial.findAll", query = "SELECT c FROM CatastroPredial c")})
public class CatastroPredial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "catpre_codigo")
    private Integer catpreCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "catpre_cod_nacional")
    private String catpreCodNacional;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "catpre_cod_local")
    private String catpreCodLocal;
    @Size(max = 10)
    @Column(name = "catpre_numero")
    private String catpreNumero;
    @Basic(optional = false)
    @NotNull
    @Size(max = 2147483647)
    @Column(name = "catpre_nombre_sector")
    private String catpreNombreSector;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "catpre_coord_latitud")
    private Double catpreCoordLatitud;
    @Column(name = "catpre_coord_longitud")
    private Double catpreCoordLongitud;
    @Column(name = "catpre_longitud_frente_principal")
    private Double catpreLongitudFrentePrincipal;
    @Column(name = "catpre_longitud_fondo")
    private Double catpreLongitudFondo;
    @Column(name = "catpre_area_total")
    private Double catpreAreaTotal;
    @Column(name = "catpre_area_total_esc")
    private Double catpreAreaTotalEsc;
    @Column(name = "catpre_area_total_cons")
    private Double catpreAreaTotalCons;
    @Size(max = 2147483647)
    @Column(name = "catpre_colindante_norte")
    private String catpreColindanteNorte;
    @Size(max = 2147483647)
    @Column(name = "catpre_colindante_sur")
    private String catpreColindanteSur;
    @Size(max = 2147483647)
    @Column(name = "catpre_colindante_este")
    private String catpreColindanteEste;
    @Size(max = 2147483647)
    @Column(name = "catpre_colindante_oeste")
    private String catpreColindanteOeste;
    @Column(name = "catpre_existe_escrituras")
    private Boolean catpreExisteEscrituras;
    @Size(max = 10)
    @Column(name = "catpre_num_notaria")
    private String catpreNumNotaria;
    @Size(max = 100)
    @Column(name = "catpre_lugar_escritura")
    private String catpreLugarEscritura;
    @Column(name = "catpre_fecha_reg_propiedad")
    @Temporal(TemporalType.DATE)
    private Date catpreFechaRegPropiedad;
    @Column(name = "catpre_tiene_legalizacion")
    private Boolean catpreTieneLegalizacion;
    @Column(name = "catpre_num_medidores_luz")
    private Integer catpreNumMedidoresLuz;
    @Column(name = "catpre_num_medidores_agua")
    private Integer catpreNumMedidoresAgua;
    @Column(name = "catpre_num_carriles_acceso")
    private Integer catpreNumCarrilesAcceso;
    @Column(name = "catpre_negocio_vivienda")
    private Boolean catpreNegocioVivienda;
    @Column(name = "catpre_porc_vivienda")
    private Integer catprePorcVivienda;
    @Column(name = "catpre_porc_bodega")
    private Integer catprePorcBodega;
    @Column(name = "catpre_porc_garage")
    private Integer catprePorcGarage;
    @Column(name = "catpre_porc_otros")
    private Integer catprePorcOtros;
    @Column(name = "catpre_linderos_definidos")
    private Boolean catpreLinderosDefinidos;
    @Column(name = "catpre_colaboracion_prop")
    private Boolean catpreColaboracionProp;
    @Size(max = 2147483647)
    @Column(name = "catpre_nombre_fuente")
    private String catpreNombreFuente;
    @Column(name = "catpre_per_discapacidad")
    private Boolean catprePerDiscapacidad;
    @Size(max = 20)
    @Column(name = "catpre_disc_cicarnet")
    private String catpreDiscCicarnet;
    @Size(max = 200)
    @Column(name = "catpre_disc_nombre")
    private String catpreDiscNombre;
    @Column(name = "catpre_disc_porc")
    private Integer catpreDiscPorc;
    @Size(max = 200)
    @Column(name = "catpre_empadronador")
    private String catpreEmpadronador;
    @Size(max = 10)
    @Column(name = "catpre_empadronador_ci")
    private String catpreEmpadronadorCi;
    @Size(max = 200)
    @Column(name = "catpre_supervisor")
    private String catpreSupervisor;
    @Size(max = 10)
    @Column(name = "catpre_supervisor_ci")
    private String catpreSupervisorCi;
    @Size(max = 200)
    @Column(name = "catpre_qcqa")
    private String catpreQcqa;
    @Size(max = 2147483647)
    @Column(name = "ultacc_detalle")
    private String ultaccDetalle;
    @Column(name = "ultacc_marcatiempo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultaccMarcatiempo;
    @Size(max = 200)
    @Column(name = "catpre_via_principal")
    private String catpreViaPrincipal;
    @Size(max = 200)
    @Column(name = "catpre_interseccion")
    private String catpreInterseccion;
    @Size(max = 10)
    @Column(name = "catpre_num_lote")
    private String catpreNumLote;
    @Column(name = "catpre_num_bloques")
    private Integer catpreNumBloques;
    @Column(name = "catpre_num_pisos")
    private Integer catpreNumPisos;
    @Size(max = 200)
    @Column(name = "catpre_otro_propietario")
    private String catpreOtroPropietario;
    @Size(max = 200)
    @Column(name = "catpre_ident_predio")
    private String catpreIdentPredio;
    @Column(name = "catpre_num_fam_bloque")
    private Integer catpreNumFamBloque;
    @Column(name = "catpre_docrel_cedula")
    private Boolean catpreDocrelCedula;
    @Column(name = "catpre_docrel_carne")
    private Boolean catpreDocrelCarne;
    @Column(name = "catpre_docrel_escrituras")
    private Boolean catpreDocrelEscrituras;
    @Size(max = 10)
    @Column(name = "catpre_bloque_nuevoampl")
    private String catpreBloqueNuevoampl;
    @JoinColumn(name = "usu_identificacion", referencedColumnName = "usu_identificacion")
    @ManyToOne(optional = false)
    private SegUsuario usuIdentificacion;
    @JoinColumn(name = "catdet_fuente_informacion", referencedColumnName = "catdet_codigo")
    @ManyToOne
    private CatalogoDetalle catdetFuenteInformacion;
    @JoinColumn(name = "catdet_alicuotas", referencedColumnName = "catdet_codigo")
    @ManyToOne
    private CatalogoDetalle catdetAlicuotas;
    @JoinColumn(name = "catdet_dimension", referencedColumnName = "catdet_codigo")
    @ManyToOne
    private CatalogoDetalle catdetDimension;
    @JoinColumn(name = "catdet_tipo_func_negocio", referencedColumnName = "catdet_codigo")
    @ManyToOne
    private CatalogoDetalle catdetTipoFuncNegocio;
    @JoinColumn(name = "catdet_tipo_negocio", referencedColumnName = "catdet_codigo")
    @ManyToOne
    private CatalogoDetalle catdetTipoNegocio;
    @JoinColumn(name = "catdet_localizacion", referencedColumnName = "catdet_codigo")
    @ManyToOne
    private CatalogoDetalle catdetLocalizacion;
    @JoinColumn(name = "catdet_topografia", referencedColumnName = "catdet_codigo")
    @ManyToOne
    private CatalogoDetalle catdetTopografia;
    @JoinColumn(name = "catdet_forma", referencedColumnName = "catdet_codigo")
    @ManyToOne
    private CatalogoDetalle catdetForma;
    @JoinColumn(name = "catdet_doc_relevamiento", referencedColumnName = "catdet_codigo")
    @ManyToOne
    private CatalogoDetalle catdetDocRelevamiento;
    @JoinColumn(name = "catdet_tipo_via", referencedColumnName = "catdet_codigo")
    @ManyToOne
    private CatalogoDetalle catdetTipoVia;
    @JoinColumn(name = "catdet_tipo_ubicacion", referencedColumnName = "catdet_codigo")
    @ManyToOne
    private CatalogoDetalle catdetTipoUbicacion;
    @JoinColumn(name = "catdet_tipo_prop1", referencedColumnName = "catdet_codigo")
    @ManyToOne
    private CatalogoDetalle catdetTipoProp1;
    @JoinColumn(name = "catdet_tipo_prop2", referencedColumnName = "catdet_codigo")
    @ManyToOne
    private CatalogoDetalle catdetTipoProp2;
    @JoinColumn(name = "catdet_ref_cartografica", referencedColumnName = "catdet_codigo")
    @ManyToOne
    private CatalogoDetalle catdetRefCartografica;
    @JoinColumn(name = "catdet_dominio", referencedColumnName = "catdet_codigo")
    @ManyToOne
    private CatalogoDetalle catdetDominio;
    @JoinColumn(name = "catdet_traslacion_dominio", referencedColumnName = "catdet_codigo")
    @ManyToOne
    private CatalogoDetalle catdetTraslacionDominio;
    @JoinColumn(name = "catdet_ocupacion", referencedColumnName = "catdet_codigo")
    @ManyToOne
    private CatalogoDetalle catdetOcupacion;
    @JoinColumn(name = "catdet_no_edificado", referencedColumnName = "catdet_codigo")
    @ManyToOne
    private CatalogoDetalle catdetNoEdificado;
    @JoinColumn(name = "catdet_en_construccion", referencedColumnName = "catdet_codigo")
    @ManyToOne
    private CatalogoDetalle catdetEnConstruccion;
    @JoinColumn(name = "catdet_caracteristicas_suelo", referencedColumnName = "catdet_codigo")
    @ManyToOne
    private CatalogoDetalle catdetCaracteristicasSuelo;
    @JoinColumn(name = "catdet_parroquia", referencedColumnName = "catdet_codigo")
    @ManyToOne
    private CatalogoDetalle catdetParroquia;
    @JoinColumn(name = "catdet_sector", referencedColumnName = "catdet_codigo")
    @ManyToOne
    private CatalogoDetalle catdetSector;
    @Transient
    private List<PropietarioPredio> listaPropietariosPredio;
    @Transient
    private String claveCatastral;
    @Transient
    private Double porcentajeAreas;

    public CatastroPredial() {
        listaPropietariosPredio = new ArrayList<PropietarioPredio>();
        catdetTipoProp1 = new CatalogoDetalle();
        catdetTipoProp2 = new CatalogoDetalle();
        catdetTipoVia = new CatalogoDetalle();
        catdetTipoUbicacion = new CatalogoDetalle();
        catdetRefCartografica = new CatalogoDetalle();
        catdetDominio = new CatalogoDetalle();
        catdetTraslacionDominio = new CatalogoDetalle();
        catdetOcupacion = new CatalogoDetalle();
        catdetNoEdificado = new CatalogoDetalle();
        catdetEnConstruccion = new CatalogoDetalle();
        catdetCaracteristicasSuelo = new CatalogoDetalle();
        catdetForma = new CatalogoDetalle();
        catdetTopografia = new CatalogoDetalle();
        catdetLocalizacion = new CatalogoDetalle();
        catdetSector = new CatalogoDetalle();
        catdetParroquia = new CatalogoDetalle();
    }

    public CatastroPredial(Integer catpreCodigo) {
        this.catpreCodigo = catpreCodigo;
    }

    public CatastroPredial(Integer catpreCodigo, String catpreCodNacional, String catpreCodLocal, String catpreNombreSector) {
        this.catpreCodigo = catpreCodigo;
        this.catpreCodNacional = catpreCodNacional;
        this.catpreCodLocal = catpreCodLocal;
        this.catpreNombreSector = catpreNombreSector;
    }

    public Integer getCatpreCodigo() {
        return catpreCodigo;
    }

    public void setCatpreCodigo(Integer catpreCodigo) {
        this.catpreCodigo = catpreCodigo;
    }

    public String getCatpreCodNacional() {
        return catpreCodNacional;
    }

    public void setCatpreCodNacional(String catpreCodNacional) {
        this.catpreCodNacional = catpreCodNacional;
    }

    public String getCatpreCodLocal() {
        return catpreCodLocal;
    }

    public void setCatpreCodLocal(String catpreCodLocal) {
        this.catpreCodLocal = catpreCodLocal;
    }

    public String getCatpreNumero() {
        return catpreNumero;
    }

    public void setCatpreNumero(String catpreNumero) {
        this.catpreNumero = catpreNumero;
    }

    public String getCatpreNombreSector() {
        return catpreNombreSector;
    }

    public void setCatpreNombreSector(String catpreNombreSector) {
        this.catpreNombreSector = catpreNombreSector;
    }

    public Double getCatpreCoordLatitud() {
        return catpreCoordLatitud;
    }

    public void setCatpreCoordLatitud(Double catpreCoordLatitud) {
        this.catpreCoordLatitud = catpreCoordLatitud;
    }

    public Double getCatpreCoordLongitud() {
        return catpreCoordLongitud;
    }

    public void setCatpreCoordLongitud(Double catpreCoordLongitud) {
        this.catpreCoordLongitud = catpreCoordLongitud;
    }

    public Double getCatpreLongitudFrentePrincipal() {
        return catpreLongitudFrentePrincipal;
    }

    public void setCatpreLongitudFrentePrincipal(Double catpreLongitudFrentePrincipal) {
        this.catpreLongitudFrentePrincipal = catpreLongitudFrentePrincipal;
    }

    public Double getCatpreAreaTotal() {
        return catpreAreaTotal;
    }

    public void setCatpreAreaTotal(Double catpreAreaTotal) {
        this.catpreAreaTotal = catpreAreaTotal;
    }

    public Double getCatpreAreaTotalEsc() {
        return catpreAreaTotalEsc;
    }

    public void setCatpreAreaTotalEsc(Double catpreAreaTotalEsc) {
        this.catpreAreaTotalEsc = catpreAreaTotalEsc;
    }

    public Double getCatpreAreaTotalCons() {
        return catpreAreaTotalCons;
    }

    public void setCatpreAreaTotalCons(Double catpreAreaTotalCons) {
        this.catpreAreaTotalCons = catpreAreaTotalCons;
    }

    public String getCatpreColindanteNorte() {
        return catpreColindanteNorte;
    }

    public void setCatpreColindanteNorte(String catpreColindanteNorte) {
        this.catpreColindanteNorte = catpreColindanteNorte;
    }

    public String getCatpreColindanteSur() {
        return catpreColindanteSur;
    }

    public void setCatpreColindanteSur(String catpreColindanteSur) {
        this.catpreColindanteSur = catpreColindanteSur;
    }

    public String getCatpreColindanteEste() {
        return catpreColindanteEste;
    }

    public void setCatpreColindanteEste(String catpreColindanteEste) {
        this.catpreColindanteEste = catpreColindanteEste;
    }

    public String getCatpreColindanteOeste() {
        return catpreColindanteOeste;
    }

    public void setCatpreColindanteOeste(String catpreColindanteOeste) {
        this.catpreColindanteOeste = catpreColindanteOeste;
    }

    public Boolean getCatpreExisteEscrituras() {
        return catpreExisteEscrituras;
    }

    public void setCatpreExisteEscrituras(Boolean catpreExisteEscrituras) {
        this.catpreExisteEscrituras = catpreExisteEscrituras;
    }

    public String getCatpreNumNotaria() {
        return catpreNumNotaria;
    }

    public void setCatpreNumNotaria(String catpreNumNotaria) {
        this.catpreNumNotaria = catpreNumNotaria;
    }

    public String getCatpreLugarEscritura() {
        return catpreLugarEscritura;
    }

    public void setCatpreLugarEscritura(String catpreLugarEscritura) {
        this.catpreLugarEscritura = catpreLugarEscritura;
    }

    public Date getCatpreFechaRegPropiedad() {
        return catpreFechaRegPropiedad;
    }

    public void setCatpreFechaRegPropiedad(Date catpreFechaRegPropiedad) {
        this.catpreFechaRegPropiedad = catpreFechaRegPropiedad;
    }

    public Boolean getCatpreTieneLegalizacion() {
        return catpreTieneLegalizacion;
    }

    public void setCatpreTieneLegalizacion(Boolean catpreTieneLegalizacion) {
        this.catpreTieneLegalizacion = catpreTieneLegalizacion;
    }

    public Integer getCatpreNumMedidoresLuz() {
        return catpreNumMedidoresLuz;
    }

    public void setCatpreNumMedidoresLuz(Integer catpreNumMedidoresLuz) {
        this.catpreNumMedidoresLuz = catpreNumMedidoresLuz;
    }

    public Integer getCatpreNumMedidoresAgua() {
        return catpreNumMedidoresAgua;
    }

    public void setCatpreNumMedidoresAgua(Integer catpreNumMedidoresAgua) {
        this.catpreNumMedidoresAgua = catpreNumMedidoresAgua;
    }

    public Integer getCatpreNumCarrilesAcceso() {
        return catpreNumCarrilesAcceso;
    }

    public void setCatpreNumCarrilesAcceso(Integer catpreNumCarrilesAcceso) {
        this.catpreNumCarrilesAcceso = catpreNumCarrilesAcceso;
    }

    public Boolean getCatpreNegocioVivienda() {
        return catpreNegocioVivienda;
    }

    public void setCatpreNegocioVivienda(Boolean catpreNegocioVivienda) {
        this.catpreNegocioVivienda = catpreNegocioVivienda;
    }

    public Integer getCatprePorcVivienda() {
        return catprePorcVivienda;
    }

    public void setCatprePorcVivienda(Integer catprePorcVivienda) {
        this.catprePorcVivienda = catprePorcVivienda;
    }

    public Integer getCatprePorcBodega() {
        return catprePorcBodega;
    }

    public void setCatprePorcBodega(Integer catprePorcBodega) {
        this.catprePorcBodega = catprePorcBodega;
    }

    public Integer getCatprePorcGarage() {
        return catprePorcGarage;
    }

    public void setCatprePorcGarage(Integer catprePorcGarage) {
        this.catprePorcGarage = catprePorcGarage;
    }

    public Integer getCatprePorcOtros() {
        return catprePorcOtros;
    }

    public void setCatprePorcOtros(Integer catprePorcOtros) {
        this.catprePorcOtros = catprePorcOtros;
    }

    public Boolean getCatpreLinderosDefinidos() {
        return catpreLinderosDefinidos;
    }

    public void setCatpreLinderosDefinidos(Boolean catpreLinderosDefinidos) {
        this.catpreLinderosDefinidos = catpreLinderosDefinidos;
    }

    public Boolean getCatpreColaboracionProp() {
        return catpreColaboracionProp;
    }

    public void setCatpreColaboracionProp(Boolean catpreColaboracionProp) {
        this.catpreColaboracionProp = catpreColaboracionProp;
    }

    public String getCatpreNombreFuente() {
        return catpreNombreFuente;
    }

    public void setCatpreNombreFuente(String catpreNombreFuente) {
        this.catpreNombreFuente = catpreNombreFuente;
    }

    public Boolean getCatprePerDiscapacidad() {
        return catprePerDiscapacidad;
    }

    public void setCatprePerDiscapacidad(Boolean catprePerDiscapacidad) {
        this.catprePerDiscapacidad = catprePerDiscapacidad;
    }

    public String getCatpreDiscCicarnet() {
        return catpreDiscCicarnet;
    }

    public void setCatpreDiscCicarnet(String catpreDiscCicarnet) {
        this.catpreDiscCicarnet = catpreDiscCicarnet;
    }

    public String getCatpreDiscNombre() {
        return catpreDiscNombre;
    }

    public void setCatpreDiscNombre(String catpreDiscNombre) {
        this.catpreDiscNombre = catpreDiscNombre;
    }

    public Integer getCatpreDiscPorc() {
        return catpreDiscPorc;
    }

    public void setCatpreDiscPorc(Integer catpreDiscPorc) {
        this.catpreDiscPorc = catpreDiscPorc;
    }

    public String getCatpreEmpadronador() {
        return catpreEmpadronador;
    }

    public void setCatpreEmpadronador(String catpreEmpadronador) {
        this.catpreEmpadronador = catpreEmpadronador;
    }

    public String getCatpreEmpadronadorCi() {
        return catpreEmpadronadorCi;
    }

    public void setCatpreEmpadronadorCi(String catpreEmpadronadorCi) {
        this.catpreEmpadronadorCi = catpreEmpadronadorCi;
    }

    public String getCatpreSupervisor() {
        return catpreSupervisor;
    }

    public void setCatpreSupervisor(String catpreSupervisor) {
        this.catpreSupervisor = catpreSupervisor;
    }

    public String getCatpreSupervisorCi() {
        return catpreSupervisorCi;
    }

    public void setCatpreSupervisorCi(String catpreSupervisorCi) {
        this.catpreSupervisorCi = catpreSupervisorCi;
    }

    public String getCatpreQcqa() {
        return catpreQcqa;
    }

    public void setCatpreQcqa(String catpreQcqa) {
        this.catpreQcqa = catpreQcqa;
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

    public String getCatpreViaPrincipal() {
        return catpreViaPrincipal;
    }

    public void setCatpreViaPrincipal(String catpreViaPrincipal) {
        this.catpreViaPrincipal = catpreViaPrincipal;
    }

    public String getCatpreInterseccion() {
        return catpreInterseccion;
    }

    public void setCatpreInterseccion(String catpreInterseccion) {
        this.catpreInterseccion = catpreInterseccion;
    }

    public SegUsuario getUsuIdentificacion() {
        return usuIdentificacion;
    }

    public void setUsuIdentificacion(SegUsuario usuIdentificacion) {
        this.usuIdentificacion = usuIdentificacion;
    }

    public CatalogoDetalle getCatdetFuenteInformacion() {
        if (catdetFuenteInformacion == null) {
            catdetFuenteInformacion = new CatalogoDetalle();
        }
        return catdetFuenteInformacion;
    }

    public void setCatdetFuenteInformacion(CatalogoDetalle catdetFuenteInformacion) {
        this.catdetFuenteInformacion = catdetFuenteInformacion;
    }

    public CatalogoDetalle getCatdetAlicuotas() {
        if (catdetAlicuotas == null) {
            catdetAlicuotas = new CatalogoDetalle();
        }
        return catdetAlicuotas;
    }

    public void setCatdetAlicuotas(CatalogoDetalle catdetAlicuotas) {
        this.catdetAlicuotas = catdetAlicuotas;
    }

    public CatalogoDetalle getCatdetDimension() {
        if (catdetDimension == null) {
            catdetDimension = new CatalogoDetalle();
        }
        return catdetDimension;
    }

    public void setCatdetDimension(CatalogoDetalle catdetDimension) {
        this.catdetDimension = catdetDimension;
    }

    public CatalogoDetalle getCatdetTipoFuncNegocio() {
        if (catdetTipoFuncNegocio == null) {
            catdetTipoFuncNegocio = new CatalogoDetalle();
        }
        return catdetTipoFuncNegocio;
    }

    public void setCatdetTipoFuncNegocio(CatalogoDetalle catdetTipoFuncNegocio) {
        this.catdetTipoFuncNegocio = catdetTipoFuncNegocio;
    }

    public CatalogoDetalle getCatdetTipoNegocio() {
        if (catdetTipoNegocio == null) {
            catdetTipoNegocio = new CatalogoDetalle();
        }
        return catdetTipoNegocio;
    }

    public void setCatdetTipoNegocio(CatalogoDetalle catdetTipoNegocio) {
        this.catdetTipoNegocio = catdetTipoNegocio;
    }

    public CatalogoDetalle getCatdetLocalizacion() {
        if (catdetLocalizacion == null) {
            catdetLocalizacion = new CatalogoDetalle();
        }
        return catdetLocalizacion;
    }

    public void setCatdetLocalizacion(CatalogoDetalle catdetLocalizacion) {
        this.catdetLocalizacion = catdetLocalizacion;
    }

    public CatalogoDetalle getCatdetTopografia() {
        if (catdetTopografia == null) {
            catdetTopografia = new CatalogoDetalle();
        }
        return catdetTopografia;
    }

    public void setCatdetTopografia(CatalogoDetalle catdetTopografia) {
        this.catdetTopografia = catdetTopografia;
    }

    public CatalogoDetalle getCatdetForma() {
        if (catdetForma == null) {
            catdetForma = new CatalogoDetalle();
        }
        return catdetForma;
    }

    public void setCatdetForma(CatalogoDetalle catdetForma) {
        this.catdetForma = catdetForma;
    }

    public CatalogoDetalle getCatdetDocRelevamiento() {
        if (catdetDocRelevamiento == null) {
            catdetDocRelevamiento = new CatalogoDetalle();
        }
        return catdetDocRelevamiento;
    }

    public void setCatdetDocRelevamiento(CatalogoDetalle catdetDocRelevamiento) {
        this.catdetDocRelevamiento = catdetDocRelevamiento;
    }

    public CatalogoDetalle getCatdetTipoVia() {
        if (catdetTipoVia == null) {
            catdetTipoVia = new CatalogoDetalle();
        }
        return catdetTipoVia;
    }

    public void setCatdetTipoVia(CatalogoDetalle catdetTipoVia) {
        this.catdetTipoVia = catdetTipoVia;
    }

    public CatalogoDetalle getCatdetTipoUbicacion() {
        if (catdetTipoUbicacion == null) {
            catdetTipoUbicacion = new CatalogoDetalle();
        }
        return catdetTipoUbicacion;
    }

    public void setCatdetTipoUbicacion(CatalogoDetalle catdetTipoUbicacion) {
        this.catdetTipoUbicacion = catdetTipoUbicacion;
    }

    public CatalogoDetalle getCatdetTipoProp1() {
        if (catdetTipoProp1 == null) {
            catdetTipoProp1 = new CatalogoDetalle();
        }
        return catdetTipoProp1;
    }

    public void setCatdetTipoProp1(CatalogoDetalle catdetTipoProp1) {
        this.catdetTipoProp1 = catdetTipoProp1;
    }

    public CatalogoDetalle getCatdetTipoProp2() {
        if (catdetTipoProp2 == null) {
            catdetTipoProp2 = new CatalogoDetalle();
        }
        return catdetTipoProp2;
    }

    public void setCatdetTipoProp2(CatalogoDetalle catdetTipoProp2) {
        this.catdetTipoProp2 = catdetTipoProp2;
    }

    public CatalogoDetalle getCatdetRefCartografica() {
        if (catdetRefCartografica == null) {
            catdetRefCartografica = new CatalogoDetalle();
        }
        return catdetRefCartografica;
    }

    public void setCatdetRefCartografica(CatalogoDetalle catdetRefCartografica) {
        this.catdetRefCartografica = catdetRefCartografica;
    }

    public CatalogoDetalle getCatdetDominio() {
        if (catdetDominio == null) {
            catdetDominio = new CatalogoDetalle();
        }
        return catdetDominio;
    }

    public void setCatdetDominio(CatalogoDetalle catdetDominio) {
        this.catdetDominio = catdetDominio;
    }

    public CatalogoDetalle getCatdetTraslacionDominio() {
        if (catdetTraslacionDominio == null) {
            catdetTraslacionDominio = new CatalogoDetalle();
        }
        return catdetTraslacionDominio;
    }

    public void setCatdetTraslacionDominio(CatalogoDetalle catdetTraslacionDominio) {
        this.catdetTraslacionDominio = catdetTraslacionDominio;
    }

    public CatalogoDetalle getCatdetOcupacion() {
        if (catdetOcupacion == null) {
            catdetOcupacion = new CatalogoDetalle();
        }
        return catdetOcupacion;
    }

    public void setCatdetOcupacion(CatalogoDetalle catdetOcupacion) {
        this.catdetOcupacion = catdetOcupacion;
    }

    public CatalogoDetalle getCatdetNoEdificado() {
        if (catdetNoEdificado == null) {
            catdetNoEdificado = new CatalogoDetalle();
        }
        return catdetNoEdificado;
    }

    public void setCatdetNoEdificado(CatalogoDetalle catdetNoEdificado) {
        this.catdetNoEdificado = catdetNoEdificado;
    }

    public CatalogoDetalle getCatdetEnConstruccion() {
        if (catdetEnConstruccion == null) {
            catdetEnConstruccion = new CatalogoDetalle();
        }
        return catdetEnConstruccion;
    }

    public void setCatdetEnConstruccion(CatalogoDetalle catdetEnConstruccion) {
        this.catdetEnConstruccion = catdetEnConstruccion;
    }

    public CatalogoDetalle getCatdetCaracteristicasSuelo() {
        if (catdetCaracteristicasSuelo == null) {
            catdetCaracteristicasSuelo = new CatalogoDetalle();
        }
        return catdetCaracteristicasSuelo;
    }

    public void setCatdetCaracteristicasSuelo(CatalogoDetalle catdetCaracteristicasSuelo) {
        this.catdetCaracteristicasSuelo = catdetCaracteristicasSuelo;
    }

    public List<PropietarioPredio> getListaPropietariosPredio() {
        return listaPropietariosPredio;
    }

    public void setListaPropietariosPredio(List<PropietarioPredio> listaPropietariosPredio) {
        this.listaPropietariosPredio = listaPropietariosPredio;
    }

    public String getCatpreNumLote() {
        return catpreNumLote;
    }

    public void setCatpreNumLote(String catpreNumLote) {
        this.catpreNumLote = catpreNumLote;
    }

    public Integer getCatpreNumBloques() {
        return catpreNumBloques;
    }

    public void setCatpreNumBloques(Integer catpreNumBloques) {
        this.catpreNumBloques = catpreNumBloques;
    }

    public Integer getCatpreNumPisos() {
        return catpreNumPisos;
    }

    public void setCatpreNumPisos(Integer catpreNumPisos) {
        this.catpreNumPisos = catpreNumPisos;
    }

    public CatalogoDetalle getCatdetParroquia() {
        if (catdetParroquia == null) {
            catdetParroquia = new CatalogoDetalle();
        }
        return catdetParroquia;
    }

    public void setCatdetParroquia(CatalogoDetalle catdetParroquia) {
        this.catdetParroquia = catdetParroquia;
    }

    public CatalogoDetalle getCatdetSector() {
        if (catdetSector == null) {
            catdetSector = new CatalogoDetalle();
        }
        return catdetSector;
    }

    public void setCatdetSector(CatalogoDetalle catdetSector) {
        this.catdetSector = catdetSector;
    }

    public String getClaveCatastral() {
        if (catpreCodNacional != null && catpreCodLocal != null) {
            claveCatastral = catpreCodNacional + catpreCodLocal;
        }
        return claveCatastral;
    }

    public void setClaveCatastral(String claveCatastral) {
        this.claveCatastral = claveCatastral;
    }

    public Double getPorcentajeAreas() {
        if(catpreAreaTotal!=null && catpreAreaTotalEsc!=null){
            porcentajeAreas=((catpreAreaTotalEsc-catpreAreaTotal)/catpreAreaTotalEsc)*100;
        }
        return porcentajeAreas;
    }

    public void setPorcentajeAreas(Double porcentajeAreas) {
        this.porcentajeAreas = porcentajeAreas;
    }

    public String getCatpreOtroPropietario() {
        return catpreOtroPropietario;
    }

    public void setCatpreOtroPropietario(String catpreOtroPropietario) {
        this.catpreOtroPropietario = catpreOtroPropietario;
    }

    public String getCatpreIdentPredio() {
        return catpreIdentPredio;
    }

    public void setCatpreIdentPredio(String catpreIdentPredio) {
        this.catpreIdentPredio = catpreIdentPredio;
    }

    public Double getCatpreLongitudFondo() {
        return catpreLongitudFondo;
    }

    public void setCatpreLongitudFondo(Double catpreLongitudFondo) {
        this.catpreLongitudFondo = catpreLongitudFondo;
    }

    public Integer getCatpreNumFamBloque() {
        return catpreNumFamBloque;
    }

    public void setCatpreNumFamBloque(Integer catpreNumFamBloque) {
        this.catpreNumFamBloque = catpreNumFamBloque;
    }

    public Boolean getCatpreDocrelCedula() {
        return catpreDocrelCedula;
    }

    public void setCatpreDocrelCedula(Boolean catpreDocrelCedula) {
        this.catpreDocrelCedula = catpreDocrelCedula;
    }

    public Boolean getCatpreDocrelCarne() {
        return catpreDocrelCarne;
    }

    public void setCatpreDocrelCarne(Boolean catpreDocrelCarne) {
        this.catpreDocrelCarne = catpreDocrelCarne;
    }

    public Boolean getCatpreDocrelEscrituras() {
        return catpreDocrelEscrituras;
    }

    public void setCatpreDocrelEscrituras(Boolean catpreDocrelEscrituras) {
        this.catpreDocrelEscrituras = catpreDocrelEscrituras;
    }

    public String getCatpreBloqueNuevoampl() {
        return catpreBloqueNuevoampl;
    }

    public void setCatpreBloqueNuevoampl(String catpreBloqueNuevoampl) {
        this.catpreBloqueNuevoampl = catpreBloqueNuevoampl;
    }

    
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (catpreCodigo != null ? catpreCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatastroPredial)) {
            return false;
        }
        CatastroPredial other = (CatastroPredial) object;
        if ((this.catpreCodigo == null && other.catpreCodigo != null) || (this.catpreCodigo != null && !this.catpreCodigo.equals(other.catpreCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.sirec.ejb.entidades.CatastroPredial[ catpreCodigo=" + catpreCodigo + " ]";
    }

}
