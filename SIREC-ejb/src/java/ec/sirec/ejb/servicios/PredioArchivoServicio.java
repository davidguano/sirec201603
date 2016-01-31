/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sirec.ejb.servicios;

import ec.sirec.ejb.entidades.CatastroPredial;
import ec.sirec.ejb.entidades.PredioArchivo;
import ec.sirec.ejb.entidades.SegUsuario;
import ec.sirec.ejb.facade.PredioArchivoFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author vespinoza
 */
@Stateless
@LocalBean
public class PredioArchivoServicio {

    @EJB
    private PredioArchivoFacade predioArchivoDao;
    private String ENTIDAD_PREDIO_ARCHIVO = "PredioArchivo";

//    public List<SegAplicacion> listarAplicaciones(String usuario) throws Exception {
//        return segAplicacionDao.listarAccesoAplicacion(usuario);
//    }
//
//    public List<SegAplicacion> listarAplicacionesTodos() throws Exception {
//        return segAplicacionDao.listarOrdenada(ENTIDAD_APLICACION, "apliCodigo", "asc");
    //}

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public String crearPredioArchivo(PredioArchivo predioArchivo) throws Exception {
        predioArchivoDao.crear(predioArchivo);
        return "Se ha creado la archivo" + predioArchivo.getPrearcCodigo();
    }

    public String editarPredioArchivo(PredioArchivo predioArchivo) throws Exception {
        predioArchivoDao.editar(predioArchivo);
        return "Se ha modificado la archivo" + predioArchivo.getPrearcCodigo();
    }
    
        public String eliminarPredioArchivo(PredioArchivo predioArchivo) throws Exception {
        predioArchivoDao.eliminarGenerico(ENTIDAD_PREDIO_ARCHIVO, "prearcCodigo", predioArchivo.getPrearcCodigo());
        return "se ha eliminado el archivo";
    }
    
        public List<PredioArchivo> listarArchivos(SegUsuario usuario) throws Exception {
        return predioArchivoDao.listarPorCampoOrdenada(ENTIDAD_PREDIO_ARCHIVO, "usuIdentificacion", usuario, "ultaccMarcatiempo", "asc");
    }
    public List<PredioArchivo> listarArchivosXImpuesto(CatastroPredial catastro, String tipoImp) throws Exception {
        return predioArchivoDao.listarPor2CamposOrdenada(ENTIDAD_PREDIO_ARCHIVO, "catpreCodigo", catastro, "prearcTipo", tipoImp, "ultaccMarcatiempo", "asc");
    }
    
    public List<PredioArchivo> listarArchivosXImpuesto(CatastroPredial catastro, String tipoImp, Integer anio) throws Exception {
        return predioArchivoDao.listarPor3CamposOrdenada(ENTIDAD_PREDIO_ARCHIVO, "catpreCodigo", catastro, "prearcTipo", tipoImp, "prearcAnio", anio, "ultaccMarcatiempo", "asc");
    }

//    public boolean existeAplicacion(Integer vApli) throws Exception {
//        return segAplicacionDao.existePorCampo(ENTIDAD_APLICACION, "apliCodigo", vApli);
//    }
//

}
