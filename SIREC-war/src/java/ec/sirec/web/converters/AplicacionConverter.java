/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sirec.web.converters;
import ec.sirec.ejb.entidades.SegAplicacion;
import ec.sirec.web.base.BaseControlador;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

/**
 *
 * @author dvaldas
 */
@ManagedBean(name = "aplicacionConverter") 
@ViewScoped
public class AplicacionConverter extends BaseControlador implements Converter {
    

    @Override
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) throws ConverterException {
 
        if (arg2.trim().equals("")|| arg2.equals("null")) {
            return null;
        } else {
            return new SegAplicacion(Integer.valueOf(arg2));
        } 
 
    }
    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2)
            throws ConverterException {
        if (arg2 == null || arg2.equals("")) {
            return "";
        } else {
            return String.valueOf(((SegAplicacion) arg2).getApliCodigo());
        }
    }
}
