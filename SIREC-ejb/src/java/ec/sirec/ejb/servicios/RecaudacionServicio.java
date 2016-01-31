/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sirec.ejb.servicios;

import ec.sirec.ejb.entidades.RecaudacionCab;
import ec.sirec.ejb.entidades.RecaudacionDet;
import ec.sirec.ejb.facade.RecaudacionCabFacade;
import ec.sirec.ejb.facade.RecaudacionDetFacade;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author dguano
 */
@Stateless
@LocalBean
public class RecaudacionServicio {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB
    private RecaudacionCabFacade recCabeceraDao;
    @EJB
    private RecaudacionDetFacade recDetalleDao;

    public void crearCabecera(RecaudacionCab vcabecera) throws Exception {
        recCabeceraDao.crear(vcabecera);
    }

    public void editarCabecera(RecaudacionCab vcabecera) throws Exception {
        recCabeceraDao.editar(vcabecera);
    }

    public void crearDetalle(RecaudacionDet vdetalle) throws Exception {
        recDetalleDao.crear(vdetalle);
    }

    public void editarDetalle(RecaudacionDet vdetalle) throws Exception {
        recDetalleDao.editar(vdetalle);
    }

    public List<RecaudacionCab> listaRecaudacionesPorPropietario(String vci) throws Exception {
        return recCabeceraDao.listarPorCampoOrdenada("RecaudacionCab", "proCi.proCi", vci, "recFecha", "desc");
    }

    public List<RecaudacionDet> listaDetallesARecaudarPorCiAnio(String vci, Integer vAnio) throws Exception {
        return recDetalleDao.listaDetallesARecaudarPorCiAnio(vci, vAnio);
    }

    public List<RecaudacionDet> listaDetallesARecaudados(RecaudacionCab vrec) throws Exception {
        return recDetalleDao.listarPorCampoOrdenada("RecaudacionDet", "recCodigo", vrec, "recdetCodigo", "asc");
    }

    public BigDecimal obtenerTotalRecaudacion(List<RecaudacionDet> lstDets) throws Exception {
        BigDecimal t = BigDecimal.ZERO;
        if (!lstDets.isEmpty()) {
            for (RecaudacionDet det : lstDets) {
                if (det.getRecdetValor() != null) {
                    t = t.add(det.getRecdetValor());
                }
            }
        }
        return t;
    }

    public void guardarRecaudacion(RecaudacionCab vcabecera, List<RecaudacionDet> lstDets) throws Exception {
        vcabecera.setRecTotal(obtenerTotalRecaudacion(lstDets));
        vcabecera.setRecFecha(java.util.Calendar.getInstance().getTime());
        vcabecera.setRecEstado("R");
        if (vcabecera.getRecTotal() != BigDecimal.ZERO) {
            this.crearCabecera(vcabecera);
            if (!lstDets.isEmpty()) {
                for (RecaudacionDet det : lstDets) {
                    if (det.getActivo()) {
                        det.setRecCodigo(vcabecera);
                        if (det.getRecdetValor() == null) {
                            det.setRecdetValor(BigDecimal.ZERO);
                        }
                        this.recDetalleDao.crear(det);
                    }
                }
                recDetalleDao.actualizarEstadosValoracion(lstDets);
            }
        }
    }

    public void editarRecaudacion(RecaudacionCab vcabecera, List<RecaudacionDet> lstDets) throws Exception {
        vcabecera.setRecTotal(obtenerTotalRecaudacion(lstDets));
        this.editarCabecera(vcabecera);
        if (!lstDets.isEmpty()) {
            for (RecaudacionDet det : lstDets) {
                if (det.getActivo()) {
                    if (det.getRecdetValor() == null) {
                        det.setRecdetValor(BigDecimal.ZERO);
                    }
                    this.recDetalleDao.editar(det);
                }
            }
        }
    }

}
