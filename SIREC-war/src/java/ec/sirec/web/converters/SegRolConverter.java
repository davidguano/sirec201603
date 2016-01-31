/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.sirec.web.converters;

import ec.sirec.ejb.entidades.SegRol;
import ec.sirec.web.base.BaseControlador;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

/**
 *
 * @author dvaldas
 */
@ManagedBean(name = "segRolConverter")
@ViewScoped
public class SegRolConverter extends BaseControlador implements Converter {

    @Override
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) throws ConverterException {

        if (arg2.trim().equals("") || arg2.equals("null")) {
            return null;
        } else {
            return new SegRol(Integer.valueOf(arg2));
        }

    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2)
            throws ConverterException {
        if (arg2 == null || arg2.equals("")) {
            return "";
        } else {
            return String.valueOf(((SegRol) arg2).getRolCodigo());
        }
    }

    @SuppressWarnings("unchecked")
    private SegRol getObjectFromUIPickListComponent(UIComponent component, String value) {
        final DualListModel<SegRol> dualList;
        try {
            dualList = (DualListModel<SegRol>) ((PickList) component).getValue();
            SegRol estructuraD = getObjectFromList(dualList.getSource(), Integer.valueOf(value));
            if (estructuraD == null) {
                estructuraD = getObjectFromList(dualList.getTarget(), Integer.valueOf(value));
            }
            return estructuraD;
        } catch (ClassCastException cce) {
            throw new ConverterException();
        } catch (NumberFormatException nfe) {
            throw new ConverterException();
        }
    }

    private SegRol getObjectFromList(final List<?> list, final Integer identifier) {
        for (final Object object : list) {
            final SegRol estructuraDatos = (SegRol) object;
            if (estructuraDatos.getRolCodigo().equals(identifier)) {
                return estructuraDatos;
            }
        }
        return null;
    }
}
