/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sirec.ejb.servicios;

import ec.sirec.ejb.entidades.DatoGlobal;
import ec.sirec.ejb.entidades.Patente;
import ec.sirec.ejb.entidades.Patente15xmilValoracion;
import ec.sirec.ejb.entidades.Patente15xmilValoracionExtras;

import ec.sirec.ejb.facade.DatoGlobalFacade;
import ec.sirec.ejb.facade.Patente15xmilValoracionExtrasFacade;
import ec.sirec.ejb.facade.Patente15xmilValoracionFacade;
import ec.sirec.ejb.facade.PatenteFacade;

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
public class UnoPCinoPorMilServicio {

    @EJB
    private Patente15xmilValoracionExtrasFacade patente15xmilValoracionExtrasDao;

    @EJB
    private Patente15xmilValoracionFacade patente15xmilValoracionDao;

    @EJB
    private DatoGlobalFacade datoGlobalDao;
    @EJB
    private PatenteFacade patenteDao;

    private String ENTIDAD_PATENTE = "Patente";
    private String ENTIDAD_DATO_GLOBAL = "DatoGlobal";
    private String ENTIDAD_PATENTE_VAL15XMIL_EXTRA = "Patente15xmilValoracionExtras";
    private String ENTIDAD_PATENTE_VALO15XMIL = "Patente15xmilValoracion";
    @PersistenceContext(unitName = "SIREC-ejbPU")
    private EntityManager em;

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

    public Patente cargarMaxObjPatente() throws Exception {
        return patenteDao.retornaNumSecuencial();
    }

    public DatoGlobal buscaMensajeTransaccion(String nombre) throws Exception {
        return datoGlobalDao.buscarPorCampo(ENTIDAD_DATO_GLOBAL, "datgloNombre", nombre);
    }
//*****************************Metodos Exoneracion Deduccion y Multas de Patente*************************

    public String crearPatenteValoracion15xMilExtra(Patente15xmilValoracionExtras patvalexCodigo) throws Exception {
        patente15xmilValoracionExtrasDao.crear(patvalexCodigo);
        return "se ha creado la patente val  ext" + patvalexCodigo;
    }

    public String editarPatenteValoracion15xMilExtra(Patente15xmilValoracionExtras patvalexCodigo) throws Exception {
        patente15xmilValoracionExtrasDao.editar(patvalexCodigo);
        return "se ha modificado la patente val ext" + patvalexCodigo;
    }

    public boolean existePatenteValoracion15xMilExtra(int patvalexCodigo) throws Exception {
        return patente15xmilValoracionExtrasDao.existePorCampo(ENTIDAD_PATENTE_VAL15XMIL_EXTRA, "pat15valCosigo", patvalexCodigo);
    }

    public Patente15xmilValoracionExtras buscaPatVal15xMilExtraPorPatValoracion(int patvalCodigo) throws Exception {
        return patente15xmilValoracionExtrasDao.buscarPorCampo(ENTIDAD_PATENTE_VAL15XMIL_EXTRA, "pat15valCodigo.pat15valCodigo", patvalCodigo);
    }
//*****************************Metodos Valoracion 1.5 x Mil de patente*************************
    public String crearPatenteValoracion15xMil(Patente15xmilValoracion patvalCodigo) throws Exception {
        patente15xmilValoracionDao.crear(patvalCodigo);
        return "se ha creado la patente valoracion" + patvalCodigo;
    }

    public String editarPatenteValoracion15xMil(Patente15xmilValoracion patvalCodgo) throws Exception {
        patente15xmilValoracionDao.editar(patvalCodgo);
        return "se ha modificado la patente valoraciont" + patvalCodgo;
    }

    public boolean existePatenteValoracion15xMil(int patvalCodigo) throws Exception {
        return patente15xmilValoracionDao.existePorCampo(ENTIDAD_PATENTE_VALO15XMIL, "pat15ValCodigo", patvalCodigo);
    }

    public Patente15xmilValoracion buscaPatValoracion15xMil(int patCodigo) throws Exception {
        return patente15xmilValoracionDao.buscarPorCampo(ENTIDAD_PATENTE_VALO15XMIL, "patCodigo.patCodigo", patCodigo);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public void persist(Object object) {
        em.persist(object);
    }
}
