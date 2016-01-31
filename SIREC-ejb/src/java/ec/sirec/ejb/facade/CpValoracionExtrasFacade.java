/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.sirec.ejb.facade;

import ec.sirec.ejb.entidades.CpValoracionExtras;
import java.math.BigDecimal;
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
public class CpValoracionExtrasFacade extends AbstractFacade<CpValoracionExtras> {
    @PersistenceContext(unitName = "SIREC-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CpValoracionExtrasFacade() {
        super(CpValoracionExtras.class);
    }
    
    public CpValoracionExtras buscarXNemonico(Object vvalor1, Object vvalor2) throws Exception {
      String sql = " select e from CpValoracionExtras e, AdicionalesDeductivos d "
                + " where e.adidedCodigo=d.adidedCodigo and "
                + " d.adidedNemonico=:vvalor1 and "         
                + " e.catprevalCodigo=:vvalor2 ";                                                             
        Query q = getEntityManager().createQuery(sql);
        q.setParameter("vvalor1", vvalor1 ); // tipo        
        q.setParameter("vvalor2", vvalor2 ); // tipo                      
       List<CpValoracionExtras> resultado = q.getResultList();
        if (resultado.size() > 0) {
            return (CpValoracionExtras) resultado.get(0);
        } else {
            return null;
        }
    } 
//    SELECT *
//  FROM sirec.adicionales_deductivos d, sirec.cp_valoracion_extras e
//  where d.adided_nemonico='R_INE' and
//  d.adided_codigo=e.adided_codigo and
//  e.catpreval_codigo=5;
    
     public BigDecimal obteneValorTipoAdicional(Object vvalor1, Object vvalor4, Object vvalor2, Object vvalor3) throws Exception {
        String sql = " select sum(e.cpvalextValor) from CatastroPredial c, CatastroPredialValoracion v, CpValoracionExtras e, AdicionalesDeductivos d"
                + " where c.catpreCodigo=:vvalor1 and "
                + " c.catpreCodigo=v.catpreCodigo and "
                + " v.catprevalCodigo=e.catprevalCodigo and "
                + " d.adidedCodigo=e.adidedCodigo and "
                + " d.adidedTipoImpuesto=:vvalor2 and "
                + " d.adidedTipo=:vvalor3 and "                        
                + " v.catprevalCodigo=:vvalor4";                        
        Query q = getEntityManager().createQuery(sql);
        q.setParameter("vvalor1", vvalor1);
        q.setParameter("vvalor2", vvalor2);
        q.setParameter("vvalor3", vvalor3);
        q.setParameter("vvalor4", vvalor4);
        return (BigDecimal)q.getSingleResult();
    } 
//    SELECT  sum(e.cpvalext_valor)
//  FROM sirec.catastro_predial c, sirec.catastro_predial_valoracion v, sirec.cp_valoracion_extras e, sirec.adicionales_deductivos d
//  where c.catpre_codigo=21 and 
//  c.catpre_codigo=v.catpre_codigo and 
//  v.catpreval_codigo=e.catpreval_codigo and 
//  d.adided_codigo=e.adided_codigo and
//  d.adided_tipo_impuesto='PR' and
//  d.adided_tipo='R' and
//     v.catpreval_codigo=5;;
}
