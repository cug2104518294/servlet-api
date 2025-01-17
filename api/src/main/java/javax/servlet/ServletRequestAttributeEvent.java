package javax.servlet;

/**
 * This is the event class for notifications of changes to the attributes of the servlet request in an application.
 *
 * @see ServletRequestAttributeListener
 * @since Servlet 2.4
 */
public class ServletRequestAttributeEvent extends ServletRequestEvent {

    private static final long serialVersionUID = -1466635426192317793L;

    private String name;
    private Object value;

    /**
     * Construct a ServletRequestAttributeEvent giving the servlet context of this web application, the ServletRequest
     * whose attributes are changing and the name and value of the attribute.
     *
     * @param sc      the ServletContext that is sending the event.
     * @param request the ServletRequest that is sending the event.
     * @param name    the name of the request attribute.
     * @param value   the value of the request attribute.
     */
    public ServletRequestAttributeEvent(ServletContext sc, ServletRequest request, String name, Object value) {
        super(sc, request);
        this.name = name;
        this.value = value;
    }

    /**
     * Return the name of the attribute that changed on the ServletRequest.
     *
     * @return the name of the changed request attribute
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the value of the attribute that has been added, removed or replaced. If the attribute was added, this is
     * the value of the attribute. If the attribute was removed, this is the value of the removed attribute. If the
     * attribute was replaced, this is the old value of the attribute.
     *
     * @return the value of the changed request attribute
     */
    public Object getValue() {
        return this.value;
    }
}
