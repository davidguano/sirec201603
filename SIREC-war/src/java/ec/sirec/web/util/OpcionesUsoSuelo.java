/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.sirec.web.util;

import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;

/**
 *
 * @author DAVID GUAN
 */
public class OpcionesUsoSuelo {
    
    private List<SelectItem> listaGrupos;
    private List<SelectItem> listaSubGrupos;
    
    public OpcionesUsoSuelo(){
        listaGrupos=new ArrayList<SelectItem>();
        listaSubGrupos=new ArrayList<SelectItem>();
        
        listaGrupos.add(new SelectItem("1","1.-Produccion"));
        listaGrupos.add(new SelectItem("2","2.-Consumo"));
        listaGrupos.add(new SelectItem("3","3.-Intercambio"));
        listaGrupos.add(new SelectItem("4","4.-Gestion"));
    }
    
    public void cargarSubgruposPorgrupo(String vgrupo){
        if(vgrupo.equals("1")){
            listaSubGrupos.add(new SelectItem("11","11.-Industrial"));
            listaSubGrupos.add(new SelectItem("12","12.-Artesanal"));
            listaSubGrupos.add(new SelectItem("13","13.-Agropecuario"));
        }
        if(vgrupo.equals("2")){
            listaSubGrupos.add(new SelectItem("21","21.-Residencial"));
            listaSubGrupos.add(new SelectItem("22","22.-Alimentos y Bebidas"));
            listaSubGrupos.add(new SelectItem("23","23.-Recreacion"));
            listaSubGrupos.add(new SelectItem("24","24.-Educacion"));
            listaSubGrupos.add(new SelectItem("25","25.-Salud"));
        }
        if(vgrupo.equals("3")){
            listaSubGrupos.add(new SelectItem("31","31.-Servicios Privados"));
            listaSubGrupos.add(new SelectItem("32","32.-Comercio"));
            listaSubGrupos.add(new SelectItem("33","33.-Comunicacion"));
        }
        if(vgrupo.equals("4")){
            listaSubGrupos.add(new SelectItem("41","41.-Institucion Publica"));
            listaSubGrupos.add(new SelectItem("42","42.-Institucion Privada"));
        }
    }

    public List<SelectItem> getListaGrupos() {
        return listaGrupos;
    }

    public void setListaGrupos(List<SelectItem> listaGrupos) {
        this.listaGrupos = listaGrupos;
    }

    public List<SelectItem> getListaSubGrupos() {
        return listaSubGrupos;
    }

    public void setListaSubGrupos(List<SelectItem> listaSubGrupos) {
        this.listaSubGrupos = listaSubGrupos;
    }
    
    
    
}
