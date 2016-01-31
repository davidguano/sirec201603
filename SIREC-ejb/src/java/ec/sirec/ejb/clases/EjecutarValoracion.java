/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.sirec.ejb.clases;

import ec.sirec.ejb.entidades.CatastroPredial;
import ec.sirec.ejb.entidades.CatastroPredialAlcabalaValoracion;
import ec.sirec.ejb.entidades.CatastroPredialPlusvaliaValoracion;
import ec.sirec.ejb.entidades.CatastroPredialValoracion;
import ec.sirec.ejb.entidades.Propietario;
import ec.sirec.ejb.entidades.SegUsuario;
import java.math.BigDecimal;

/**
 *
 * @author vespinoza
 */


public class EjecutarValoracion {
   
    private String catpreClaveCatastal;
    
    private CatastroPredialValoracion catastroPredialValoracion;
    
    private BigDecimal totalRecargos;
    private BigDecimal totalDeduciones;
    private BigDecimal totalExoneracion;
    private BigDecimal totalRegistro;
    
    private CatastroPredial CatastroPredial;
            
    private Integer catpreCodigo; 
  
    private Double catpreAreaTotal;
   
    private Double catpreAreaTotalEsc;
 
    private Double catpreAreaTotalCons;
    
    private SegUsuario usuIdentificacion;
  
    private Propietario proCi;    
       
    private BigDecimal totalAlcabalaDeducciones;
    private BigDecimal totalAlcabalaExenciones;
    
    private CatastroPredialAlcabalaValoracion catastroPredialAlcabalaValoracion;
    private CatastroPredialPlusvaliaValoracion catastroPredialPlusvaliaValoracion;
    
    public EjecutarValoracion(){
        
    }

    public EjecutarValoracion(Integer catpreCodigo) {
        this.catpreCodigo = catpreCodigo;
    }
    
    

    public Integer getCatpreCodigo() {
        return catpreCodigo;
    }

    public void setCatpreCodigo(Integer catpreCodigo) {
        this.catpreCodigo = catpreCodigo;
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

    public SegUsuario getUsuIdentificacion() {
        return usuIdentificacion;
    }

    public void setUsuIdentificacion(SegUsuario usuIdentificacion) {
        this.usuIdentificacion = usuIdentificacion;
    }

    public Propietario getProCi() {
        return proCi;
    }

    public void setProCi(Propietario proCi) {
        this.proCi = proCi;
    }  

    public String getCatpreClaveCatastal() {
        return catpreClaveCatastal;
    }

    public void setCatpreClaveCatastal(String catpreClaveCatastal) {
        this.catpreClaveCatastal = catpreClaveCatastal;
    }

    public CatastroPredialValoracion getCatastroPredialValoracion() {
        return catastroPredialValoracion;
    }

    public void setCatastroPredialValoracion(CatastroPredialValoracion catastroPredialValoracion) {
        this.catastroPredialValoracion = catastroPredialValoracion;
    }

    public BigDecimal getTotalRecargos() {
        return totalRecargos;
    }

    public void setTotalRecargos(BigDecimal totalRecargos) {
        this.totalRecargos = totalRecargos;
    }

    public BigDecimal getTotalDeduciones() {
        return totalDeduciones;
    }

    public void setTotalDeduciones(BigDecimal totalDeduciones) {
        this.totalDeduciones = totalDeduciones;
    }

    public BigDecimal getTotalExoneracion() {
        return totalExoneracion;
    }

    public void setTotalExoneracion(BigDecimal totalExoneracion) {
        this.totalExoneracion = totalExoneracion;
    }

    public BigDecimal getTotalRegistro() {
        return totalRegistro;
    }

    public void setTotalRegistro(BigDecimal totalRegistro) {
        this.totalRegistro = totalRegistro;
    }

    public CatastroPredial getCatastroPredial() {
        return CatastroPredial;
    }

    public void setCatastroPredial(CatastroPredial CatastroPredial) {
        this.CatastroPredial = CatastroPredial;
    }

    public BigDecimal getTotalAlcabalaDeducciones() {
        return totalAlcabalaDeducciones;
    }

    public void setTotalAlcabalaDeducciones(BigDecimal totalAlcabalaDeducciones) {
        this.totalAlcabalaDeducciones = totalAlcabalaDeducciones;
    }

    public BigDecimal getTotalAlcabalaExenciones() {
        return totalAlcabalaExenciones;
    }

    public void setTotalAlcabalaExenciones(BigDecimal totalAlcabalaExenciones) {
        this.totalAlcabalaExenciones = totalAlcabalaExenciones;
    }  

    public CatastroPredialAlcabalaValoracion getCatastroPredialAlcabalaValoracion() {
        return catastroPredialAlcabalaValoracion;
    }

    public void setCatastroPredialAlcabalaValoracion(CatastroPredialAlcabalaValoracion catastroPredialAlcabalaValoracion) {
        this.catastroPredialAlcabalaValoracion = catastroPredialAlcabalaValoracion;
    }

    public CatastroPredialPlusvaliaValoracion getCatastroPredialPlusvaliaValoracion() {
        return catastroPredialPlusvaliaValoracion;
    }

    public void setCatastroPredialPlusvaliaValoracion(CatastroPredialPlusvaliaValoracion catastroPredialPlusvaliaValoracion) {
        this.catastroPredialPlusvaliaValoracion = catastroPredialPlusvaliaValoracion;
    }
                
    }
