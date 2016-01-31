/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sirec.ejb.servicios;

import ec.sirec.ejb.entidades.DatoGlobal;
import ec.sirec.ejb.entidades.Patente;
import ec.sirec.ejb.entidades.Patente15xmilValoracion;
import ec.sirec.ejb.entidades.PatenteValoracion;
import ec.sirec.ejb.entidades.PatenteValoracionExtras;
import ec.sirec.ejb.facade.DatoGlobalFacade;
import ec.sirec.ejb.facade.Patente15xmilValoracionExtrasFacade;
import ec.sirec.ejb.facade.Patente15xmilValoracionFacade;
import ec.sirec.ejb.facade.PatenteFacade;
import ec.sirec.ejb.facade.PatenteValoracionExtrasFacade;
import ec.sirec.ejb.facade.PatenteValoracionFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Darwin Aldas
 */
@Stateless
@LocalBean
public class PatenteServicio {

    @EJB
    private PatenteValoracionFacade patenteValoracionDao;
    @EJB
    private PatenteValoracionExtrasFacade patenteValoracionExtrasDao;
    @EJB
    private DatoGlobalFacade datoGlobalDao;
    @EJB
    private PatenteFacade patenteDao;

    private String ENTIDAD_PATENTE = "Patente";
    private String ENTIDAD_DATO_GLOBAL = "DatoGlobal";
    private String ENTIDAD_PATENTE_VALORACION_EXTRA = "PatenteValoracionExtras";
    private String ENTIDAD_PATENTE_VALORACION = "PatenteValoracion";
    @PersistenceContext(unitName = "SIREC-ejbPU")
    private EntityManager em;
    private String ENTIDAD_PATENTE_15MIL_VALORACION = "Patente15xmilValoracion";
//************************Metodos Patente******************************

    public Patente cargarObjPatentePorCatastro(int codCatastro) throws Exception {
        return patenteDao.buscarPorCampo(ENTIDAD_PATENTE, "catpreCodigo.catpreCodigo", codCatastro);
    }

    public boolean existeCodigoPatente(int codPatente) throws Exception {
        return patenteDao.existePorCampo(ENTIDAD_PATENTE, "patCodigo", codPatente);
    }

    public String crearPatente(Patente codPatente) throws Exception {
        patenteDao.crear(codPatente);
        return "se ha creado la patente" + codPatente;
    }

    public String editarPatente(Patente codPatente) throws Exception {
        patenteDao.editar(codPatente);
        return "se ha modificado la patente" + codPatente;
    }

    public String eliminarPatente(Patente codPatente) throws Exception {
        patenteDao.eliminarGenerico(ENTIDAD_PATENTE, "ageCodigo", codPatente.getPatCodigo());
        return "se ha eliminado la patente" + codPatente;
    }

    public List<Patente> listarPatentes() throws Exception {
        return patenteDao.listarTodos();
    }

    public Patente cargarMaxObjPatente() throws Exception {
        return patenteDao.retornaNumSecuencial();
    }

    public Patente cargarObjPatente(int codPatente) throws Exception {
        return patenteDao.buscarPorCampo(ENTIDAD_PATENTE, "patCodigo", codPatente);
    }

    public DatoGlobal cargarObjDatGloPorNombre(String nombre) throws Exception {
        return datoGlobalDao.buscarPorCampo(ENTIDAD_DATO_GLOBAL, "datgloNombre", nombre);
    }

    public List<Object[]> listarEmisionAnioPatente(int codPatente,int anio) {
        return patenteDao.listaDatosEmisionAnioPatente(codPatente,anio);
    }
    public List<Object[]> listarEmisionAnioParroquia(int anio,int parroquia) {
        return patenteDao.listaDatosEmisionAnioParroquia(anio,parroquia);
    }
    public List<Object[]> listarEmisionAnioGlobal(int anio) {
        return patenteDao.listaDatosEmisionAnioGlobal(anio);
    }

    public Patente buscaParPrRucActEco(String cedula, int actEcon) throws Exception {
        return patenteDao.buscaPatentePorRucActEcon(cedula, actEcon);
    }

//*****************************Metodos Exoneracion Deduccion y Multas de Patente*************************
    public String crearPatenteValoracionExtra(PatenteValoracionExtras patvalexCodigo) throws Exception {
        patenteValoracionExtrasDao.crear(patvalexCodigo);
        return "se ha creado la patente val  ext" + patvalexCodigo;
    }

    public String editarPatenteValoracionExtra(PatenteValoracionExtras patvalexCodigo) throws Exception {
        patenteValoracionExtrasDao.editar(patvalexCodigo);
        return "se ha modificado la patente val ext" + patvalexCodigo;
    }

    public boolean existePatenteValoracionExtra(int patvalexCodigo) throws Exception {
        return patenteValoracionExtrasDao.existePorCampo(ENTIDAD_PATENTE_VALORACION_EXTRA, "patvalextCodigo", patvalexCodigo);
    }

    public PatenteValoracionExtras buscaPatValExtraPorPatValoracion(int patvalCodigo) throws Exception {
        return patenteValoracionExtrasDao.buscarPorCampo(ENTIDAD_PATENTE_VALORACION_EXTRA, "patvalCodigo.patvalCodigo", patvalCodigo);
    }
//*****************************Metodos Valoracion de patente*************************

    public String crearPatenteValoracion(PatenteValoracion patvalCodigo) throws Exception {
        patenteValoracionDao.crear(patvalCodigo);
        return "se ha creado la patente valoracion" + patvalCodigo;
    }

    public String editarPatenteValoracion(PatenteValoracion patvalCodgo) throws Exception {
        patenteValoracionDao.editar(patvalCodgo);
        return "se ha modificado la patente valoraciont" + patvalCodgo;
    }

    public boolean existePatenteValoracion(int patvalCodigo) throws Exception {
        return patenteValoracionDao.existePorCampo(ENTIDAD_PATENTE_VALORACION, "patenteValoracion", patvalCodigo);
    }

    public PatenteValoracion buscaPatValoracion(int patCodigo) throws Exception {
        return patenteValoracionDao.buscarPorCampo(ENTIDAD_PATENTE_VALORACION, "patCodigo.patCodigo", patCodigo);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public void persist(Object object) {
        em.persist(object);
    }
}