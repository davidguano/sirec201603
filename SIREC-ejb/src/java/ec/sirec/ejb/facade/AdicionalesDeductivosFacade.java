/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.sirec.ejb.facade;

import ec.sirec.ejb.entidades.AdicionalesDeductivos;
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
public class AdicionalesDeductivosFacade extends AbstractFacade<AdicionalesDeductivos> {
    @PersistenceContext(unitName = "SIREC-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdicionalesDeductivosFacade() {
        super(AdicionalesDeductivos.class);
    }
    
    public BigDecimal obteneValorXAdicional(Object vvalor1, Object vvalor2, Object vvalor3) throws Exception {
        String sql = " select sum(d.adidedValorfijo) from CatastroPredial c, CatastroPredialValoracion v, CpValoracionExtras e, AdicionalesDeductivos d"
                + " where c.catpreCodigo=:vvalor1 and "
                + " c.catpreCodigo=v.catpreCodigo and "
                + " v.catprevalCodigo=e.catprevalCodigo and "
                + " d.adidedCodigo=e.adidedCodigo and "
                + " d.adidedTipoImpuesto=:vvalor2 and "
                + " d.adidedTipo=:vvalor3";                        
        Query q = getEntityManager().createQuery(sql);
        q.setParameter("vvalor1", vvalor1);
        q.setParameter("vvalor2", vvalor2);
        q.setParameter("vvalor3", vvalor3);
        return (BigDecimal)q.getSingleResult();
    }        
//    SELECT  sum(d.adided_valorfijo)
//  FROM sirec.catastro_predial c, sirec.catastro_predial_valoracion v, sirec.cp_valoracion_extras e, sirec.adicionales_deductivos d
//  where c.catpre_codigo=1 and 
//  c.catpre_codigo=v.catpre_codigo and 
//  v.catpreval_codigo=e.catpreval_codigo and 
//  d.adided_codigo=e.adided_codigo and
//  d.adided_tipo_impuesto='PA' and
//  d.adided_tipo='E';
    
    public List<AdicionalesDeductivos> recuperarAdicionalesDeductivos(Object vvalor1, Object vvalor2, Object vvalor3) throws Exception {

      String sql = " select d from CpValoracionExtras e, AdicionalesDeductivos d "
                + " where e.adidedCodigo=d.adidedCodigo and "
                + " e.catprevalCodigo=:vvalor1 and "
                + " d.adidedTipo=:vvalor2 and "
                + " d.adidedTipoImpuesto=:vvalor3";             
                                                
        Query q = getEntityManager().createQuery(sql);
        q.setParameter("vvalor1", vvalor1 ); // tipo        
        q.setParameter("vvalor2", vvalor2 ); // tipo        
        q.setParameter("vvalor3", vvalor3 ); // 
       
        return q.getResultList();
    } 
//    SELECT * from sirec.cp_valoracion_extras e, sirec.adicionales_deductivos d
//  where e.adided_codigo=d.adided_codigo and 
//  e.catpreval_codigo=17 and
//  d.adided_tipo_impuesto='PR' and
//  adided_tipo='D'
}





