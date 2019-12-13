package javax.servlet;

import java.util.Enumeration;

/**
 * A filter configuration object used by a servlet container to pass information to a filter during initialization.
 *
 * @see Filter
 * @since Servlet 2.3
 */
public interface FilterConfig {

    /**
     * Returns the filter-name of this filter as defined in the deployment descriptor.
     *
     * @return the filter name of this filter
     */
    String getFilterName();

    /**
     * Returns a reference to the {@link ServletContext} in which the caller is executing.
     *
     * @return a {@link ServletContext} object, used by the caller to interact with its servlet container
     * @see ServletContext
     */
    ServletContext getServletContext();

    /**
     * Returns a <code>String</code> containing the value of the named initialization parameter, or <code>null</code> if
     * the initialization parameter does not exist.
     *
     * @param name a <code>String</code> specifying the name of the initialization parameter
     * @return a <code>String</code> containing the value of the initialization parameter, or <code>null</code> if the
     * initialization parameter does not exist
     */
    String getInitParameter(String name);

    /**
     * Returns the names of the filter's initialization parameters as an <code>Enumeration</code> of <code>String</code>
     * objects, or an empty <code>Enumeration</code> if the filter has no initialization parameters.
     *
     * @return an <code>Enumeration</code> of <code>String</code> objects containing the names of the filter's
     * initialization parameters
     */
    Enumeration<String> getInitParameterNames();

}
