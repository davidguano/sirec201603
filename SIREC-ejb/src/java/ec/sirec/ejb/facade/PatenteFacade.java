/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sirec.ejb.facade;

import ec.sirec.ejb.entidades.Patente;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author dguano
 */
@Stateless
public class PatenteFacade extends AbstractFacade<Patente> {

    @PersistenceContext(unitName = "SIREC-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PatenteFacade() {
        super(Patente.class);
    }

    public Patente retornaNumSecuencial() throws Exception {
        String sql = "select max(c) from Patente c ";
        Query q = em.createQuery(sql);
        return (Patente) q.getResultList().get(0);
    }

    public List<Object[]> listaDatosEmisionAnioPatente(int codPatente, int anio) {
        List<Object[]> lista = new ArrayList<Object[]>();
        String sql = " select distinct( pa.pat_codigo) as clavePatente,p.pro_apellidos||' '||p.pro_nombres as nomContribuente ,"
                + " p.pro_ci as identificacion,p.pro_direccion as direccion,cdp.catdet_texto as parroquia,pv.patval_anio as año,pv.patval_patrimonio as patrimonio, "
                + " pv.patval_impuesto as impuestoPatente,pv.patval_tasa_bomb as tasaBomberos,pv.patval_tasa_proc as tasaProcesamiento,"
                + " pv.patval_total as totalPatente,pvmil.pat15val_base_imponible as baseImponible,pvmil.pat15val_impuesto as impuestoxMil,pvmil.pat15val_tasa_proc as tasaProxMil,"
                + " pvmil.pat15val_total as totalValxMil "
                + " from "
                + " sirec.propietario  p,sirec.propietario_predio pp,sirec.catastro_predial cp,"
                + " sirec.patente pa,sirec.patente_valoracion pv,sirec.patente_15xmil_valoracion pvmil,"
                + " sirec.catalogo_detalle cdp"
                + " where p.pro_ci=pp.pro_ci"
                + " and pp.catpre_codigo=cp.catpre_codigo"
                + " and cp.catpre_codigo=pa.catpre_codigo"
                + " and pa.pat_codigo=pv.pat_codigo"
                + " and pa.pat_codigo=pvmil.pat_codigo"
                + " and cp.catdet_parroquia=cdp.catdet_codigo "
                + " and pa.pat_codigo=:codPatente "
                + " and pv.patval_anio=:anio";

        Query q = getEntityManager().createNativeQuery(sql);
        q.setParameter("codPatente", codPatente).setParameter("anio", anio);
        if (q.getResultList().isEmpty()) {
            return null;
        } else {
            System.out.println(sql);
            lista = q.getResultList();
            return lista;
        }
    }

    public List<Object[]> listaDatosEmisionAnioParroquia(int anio, int parroquia) {
        List<Object[]> lista = new ArrayList<Object[]>();
        String sql = " select distinct( pa.pat_codigo) as clavePatente,p.pro_apellidos||' '||p.pro_nombres as nomContribuente ,"
                + " p.pro_ci as identificacion,p.pro_direccion as direccion,cdp.catdet_texto as parroquia,pv.patval_anio as año,pv.patval_patrimonio as patrimonio, "
                + " pv.patval_impuesto as impuestoPatente,pv.patval_tasa_bomb as tasaBomberos,pv.patval_tasa_proc as tasaProcesamiento,"
                + " pv.patval_total as totalPatente,pvmil.pat15val_base_imponible as baseImponible,pvmil.pat15val_impuesto as impuestoxMil,pvmil.pat15val_tasa_proc as tasaProxMil,"
                + " pvmil.pat15val_total as totalValxMil "
                + " from "
                + " sirec.propietario  p,sirec.propietario_predio pp,sirec.catastro_predial cp,"
                + " sirec.patente pa,sirec.patente_valoracion pv,sirec.patente_15xmil_valoracion pvmil,"
                + " sirec.catalogo_detalle cdp"
                + " where p.pro_ci=pp.pro_ci"
                + " and pp.catpre_codigo=cp.catpre_codigo"
                + " and cp.catpre_codigo=pa.catpre_codigo"
                + " and pa.pat_codigo=pv.pat_codigo"
                + " and pa.pat_codigo=pvmil.pat_codigo"
                + " and cp.catdet_parroquia=cdp.catdet_codigo "
                + " and cp.catdet_parroquia=:parroquia "
                + " and pv.patval_anio=:anio";

        Query q = getEntityManager().createNativeQuery(sql);
        q.setParameter("parroquia", parroquia).setParameter("anio", anio);
        if (q.getResultList().isEmpty()) {
            return null;
        } else {
            System.out.println(sql);
            lista = q.getResultList();
            return lista;
        }
    }

    public List<Object[]> listaDatosEmisionAnioGlobal(int anio) {
        List<Object[]> lista = new ArrayList<Object[]>();
        String sql = " select distinct( pa.pat_codigo) as clavePatente,p.pro_apellidos||' '||p.pro_nombres as nomContribuente ,"
                + " p.pro_ci as identificacion,p.pro_direccion as direccion,cdp.catdet_texto as parroquia,pv.patval_anio as año,pv.patval_patrimonio as patrimonio, "
                + " pv.patval_impuesto as impuestoPatente,pv.patval_tasa_bomb as tasaBomberos,pv.patval_tasa_proc as tasaProcesamiento,"
                + " pv.patval_total as totalPatente,pvmil.pat15val_base_imponible as baseImponible,pvmil.pat15val_impuesto as impuestoxMil,pvmil.pat15val_tasa_proc as tasaProxMil,"
                + " pvmil.pat15val_total as totalValxMil "
                + " from "
                + " sirec.propietario  p,sirec.propietario_predio pp,sirec.catastro_predial cp,"
                + " sirec.patente pa,sirec.patente_valoracion pv,sirec.patente_15xmil_valoracion pvmil,"
                + " sirec.catalogo_detalle cdp"
                + " where p.pro_ci=pp.pro_ci"
                + " and pp.catpre_codigo=cp.catpre_codigo"
                + " and cp.catpre_codigo=pa.catpre_codigo"
                + " and pa.pat_codigo=pv.pat_codigo"
                + " and pa.pat_codigo=pvmil.pat_codigo"
                + " and cp.catdet_parroquia=cdp.catdet_codigo "
                + " and pv.patval_anio=:anio";

        Query q = getEntityManager().createNativeQuery(sql);
        q.setParameter("anio", anio);
        if (q.getResultList().isEmpty()) {
            return null;
        } else {
            System.out.println(sql);
            lista = q.getResultList();
            return lista;
        }
    }

    public Patente buscaPatentePorRucActEcon(String cedula, int actEconomica) throws Exception {
        String sql = "select p from Patente p ,Propietario pr, "
                + " PropietarioPredio pp ,CatastroPredial cp"
                + " where pr.proCi=pp.proCi"
                + " and pp.catpreCodigo=cp.catpreCodigo"
                + " and cp.catpreCodigo=cp.catpreCodigo"
                + " and pr.proCi=:cedula "
                + " and  p.catdetTipoActEco.catdetCodigo=:actEco";
        Query q = em.createQuery(sql);
        q.setParameter("cedula", cedula).setParameter("actEco", actEconomica);
        if (q.getResultList().isEmpty()) {
            return null;
        } else {
            return (Patente) q.getResultList().get(0);
        }
    }
}