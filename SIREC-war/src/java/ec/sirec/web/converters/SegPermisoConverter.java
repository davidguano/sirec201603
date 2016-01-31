package ec.sirec.web.converters;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import ec.sirec.ejb.entidades.SegPermiso;
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
@ManagedBean(name = "segPermisoConverter")
@ViewScoped
public class SegPermisoConverter extends BaseControlador implements Converter {

    /**
     * Creates a new instance of SegPermisoConverter
     */
      @Override
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) throws ConverterException {

        if (arg2.trim().equals("") || arg2.equals("null")) {
            return null;
        } else {
            return new SegPermiso(Integer.valueOf(arg2));
        }

    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2)
            throws ConverterException {
        if (arg2 == null || arg2.equals("")) {
            return "";
        } else {
            return String.valueOf(((SegPermiso) arg2).getPerCodigo());
        }
    }

    @SuppressWarnings("unchecked")
    private SegPermiso getObjectFromUIPickListComponent(UIComponent component, String value) {
        final DualListModel<SegPermiso> dualList;
        try {
            dualList = (DualListModel<SegPermiso>) ((PickList) component).getValue();
            SegPermiso acc = getObjectFromList(dualList.getSource(), Integer.valueOf(value));
            if (acc == null) {
                acc = getObjectFromList(dualList.getTarget(), Integer.valueOf(value));
            }
            return acc;
        } catch (ClassCastException cce) {
            throw new ConverterException();
        } catch (NumberFormatException nfe) {
            throw new ConverterException();
        }
    }

    private SegPermiso getObjectFromList(final List<?> list, final Integer identifier) {
        for (final Object object : list) {
            final SegPermiso per = (SegPermiso) object;
            if (per.getPerCodigo().equals(identifier)) {
                return per;
            }
        }
        return null;
    }
}
