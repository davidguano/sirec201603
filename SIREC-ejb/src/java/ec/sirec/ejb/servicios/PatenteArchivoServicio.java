/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sirec.ejb.servicios;

import ec.sirec.ejb.entidades.Cementerio;
import ec.sirec.ejb.entidades.CementerioArchivo;
import ec.sirec.ejb.entidades.Patente;
import ec.sirec.ejb.entidades.PatenteArchivo;
import ec.sirec.ejb.facade.CementerioArchivoFacade;
import ec.sirec.ejb.facade.PatenteArchivoFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Darwin Aldas
 */
@Stateless
@LocalBean
public class PatenteArchivoServicio {

    @EJB
    private CementerioArchivoFacade cementerioArchivoDao;
    String ENTIDAD_CEM_ARCHIVO = "CementerioArchivo";

    @EJB
    private PatenteArchivoFacade patenteArchivoDao;
    String ENTIDAD_PAT_ARCHIVO = "PatenteArchivo";

    //***********************************METODOS ARCHIVO DE PATENTES*************************************
    public List<PatenteArchivo> listarArchivoPorPatente(Patente codigo) throws Exception {
        return patenteArchivoDao.listarPorCampoOrdenada(ENTIDAD_PAT_ARCHIVO, "patCodigo", codigo, "patCodigo", "asc");
        //return archivoServicio.listarTodos();
    }

    public List<PatenteArchivo> listarArchivoPatentePorTipo(Patente patCodigo, String codTipo) throws Exception {
        return patenteArchivoDao.listarPor2CamposOrdenada(ENTIDAD_PAT_ARCHIVO, "patCodigo", patCodigo, "patarcTipo", codTipo, "patCodigo", "asc");
        //return archivoServicio.listarTodos();
    }

    public String guardarArchivo(PatenteArchivo archivo) throws Exception {
        patenteArchivoDao.crear(archivo);
        return "Se ha creado el archivo" + archivo.getPatarcCodigo();
    }

    public String eliminarArchivo(PatenteArchivo archivo) throws Exception {
        patenteArchivoDao.eliminarGenerico(ENTIDAD_PAT_ARCHIVO, "patarcCodigo", archivo.getPatarcCodigo());
        return "se ha eliminado el archivo" + archivo;
    }
//************************************METODOS ARCHIVOS DE CEMENTERIO************************

    public List<CementerioArchivo> listarArchivoPorPatente(Cementerio codigo) throws Exception {
        return cementerioArchivoDao.listarPorCampoOrdenada(ENTIDAD_PAT_ARCHIVO, "patCodigo", codigo, "patCodigo", "asc");
        //return archivoServicio.listarTodos();
    }

    public List<CementerioArchivo> listarArchivoPatentePorTipo(Cementerio patCodigo, String codTipo) throws Exception {
        return cementerioArchivoDao.listarPor2CamposOrdenada(ENTIDAD_PAT_ARCHIVO, "patCodigo", patCodigo, "patarcTipo", codTipo, "patCodigo", "asc");
        //return archivoServicio.listarTodos();
    }

    public String guardarCementerioArchivo(CementerioArchivo archivo) throws Exception {
        cementerioArchivoDao.crear(archivo);
        return "Se ha creado el archivo" + archivo.getCemarcNombre();
    }

    public String eliminarCementerioArchivo(CementerioArchivo archivo) throws Exception {
        cementerioArchivoDao.eliminarGenerico(ENTIDAD_CEM_ARCHIVO, "arcCodigo", archivo.getCemarcCodigo());
        return "se ha eliminado el archivo" + archivo;
    }

}