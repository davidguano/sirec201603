package ec.sirec.web.seguridad;

import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;
import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

/**
 * Wrapper for exception handlerWrapper.
 *
 * @author inec
 *
 */
public class ViewExpiredExceptionExceptionHandler extends ExceptionHandlerWrapper {

    /**
     * Logger de la clase.
     */
    private static final Logger LOGGER = Logger
            .getLogger(ViewExpiredExceptionExceptionHandler.class.getName());
    private ExceptionHandler wrapped;

    /**
     *
     * @param wrapped
     */
    public ViewExpiredExceptionExceptionHandler(ExceptionHandler wrapped) {
        this.wrapped = wrapped;
    }

    /**
     *
     * @return
     */
    @Override
    public ExceptionHandler getWrapped() {
        return this.wrapped;
    }

    /**
     *
     * @throws FacesException
     */
    @Override
    public void handle() throws FacesException {
        for (Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents().iterator(); i.hasNext();) {
            ExceptionQueuedEvent event = i.next();
            ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();
            Throwable t = context.getException();
            if (t instanceof ViewExpiredException) {
                ViewExpiredException vee = (ViewExpiredException) t;
                FacesContext fc = FacesContext.getCurrentInstance();
                Map<String, Object> requestMap = fc.getExternalContext().getRequestMap();
                NavigationHandler nav =
                        fc.getApplication().getNavigationHandler();
                try {
                    // Push some useful stuff to the request scope for
                    // use in the page
                    requestMap.put("currentViewId", vee.getViewId());

                    LOGGER.info("REDIRECTING... TO LOGIN.");


                    StringBuilder loginString = new StringBuilder("/");
                    loginString.append("index.xhtml");
                    loginString.append("?faces-redirect=true&redirected=true");
                    nav.handleNavigation(fc, null, loginString.toString());
                    fc.renderResponse();

                } finally {
                    i.remove();
                }
            }
        }
        // At this point, the queue will not contain any ViewExpiredEvents.
        // Therefore, let the parent handle them.
        getWrapped().handle();

    }
}
