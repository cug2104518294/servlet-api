package javax.servlet;

import java.util.Enumeration;

/**
 * A servlet configuration object used by a servlet container to pass information to a servlet during initialization.
 */

/**
 * servlet 容器使用的 servlet 配置对象在初始化期间将信息传递给 servlet
 */
public interface ServletConfig {

    /**
     * Returns the name of this servlet instance. The name may be provided via server administration, assigned in the
     * web application deployment descriptor, or for an unregistered (and thus unnamed) servlet instance it will be the
     * servlet's class name.
     *
     * @return the name of the servlet instance
     */

    /**
     * 返回此 servlet 实例的名称
     * 在 web.xml 对应于 servlet-name 结点(<servlet><servlet-name></servlet-name></servlet>)
     * 或者对应于注解 @WebServlet(value = "/hello", name = "HelloServlet") 中的 name 属性
     */
    String getServletName();

    /**
     * Returns a reference to the {@link ServletContext} in which the caller is executing.
     * @return a {@link ServletContext} object, used by the caller to interact with its servlet container
     */
    /**
     * 返回调用者正在执行的 ServletContext 的引用
     */
    ServletContext getServletContext();

    /**
     * Gets the value of the initialization parameter with the given name.
     *
     * @param name the name of the initialization parameter whose value to get
     * @return a <code>String</code> containing the value of the initialization parameter, or <code>null</code> if the
     * initialization parameter does not exist
     */
    /**
     * 获取具有给定名称的初始化参数的值
     * 在 web.xml 对应于 <init-param><param-name></param-name><param-value></param-value></init-param>
     * 使用注解 @WebServlet(value = "/hello", name = "HelloServlet", initParams = {@WebInitParam(name = "name", value = "lgh")})
     */
    String getInitParameter(String name);

    /**
     * Returns the names of the servlet's initialization parameters as an <code>Enumeration</code> of
     * <code>String</code> objects, or an empty <code>Enumeration</code> if the servlet has no initialization
     * parameters.
     *
     * @return an <code>Enumeration</code> of <code>String</code> objects containing the names of the servlet's
     * initialization parameters
     */
    /**
     * 以 Enumeration<String> 形式返回 Servlet 的初始化参数的名称
     * 如果 Servlet 没有初始化参数，则返回一个空的 Enumeration
     */
    Enumeration<String> getInitParameterNames();

}
