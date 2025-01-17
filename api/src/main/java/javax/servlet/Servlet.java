package javax.servlet;

import java.io.IOException;

/**
 * Defines methods that all servlets must implement.
 *
 * <p>
 * A servlet is a small Java program that runs within a Web server. Servlets receive and respond to requests from Web
 * clients, usually across HTTP, the HyperText Transfer Protocol.
 *
 * <p>
 * To implement this interface, you can write a generic servlet that extends <code>javax.servlet.GenericServlet</code>
 * or an HTTP servlet that extends <code>javax.servlet.http.HttpServlet</code>.
 *
 * <p>
 * This interface defines methods to initialize a servlet, to service requests, and to remove a servlet from the server.
 * These are known as life-cycle methods and are called in the following sequence:
 * <ol>
 * <li>The servlet is constructed, then initialized with the <code>init</code> method.
 * <li>Any calls from clients to the <code>service</code> method are handled.
 * <li>The servlet is taken out of service, then destroyed with the <code>destroy</code> method, then garbage collected
 * and finalized.
 * </ol>
 *
 * <p>
 * In addition to the life-cycle methods, this interface provides the <code>getServletConfig</code> method, which the
 * servlet can use to get any startup information, and the <code>getServletInfo</code> method, which allows the servlet
 * to return basic information about itself, such as author, version, and copyright.
 *
 * @author Various
 * @see GenericServlet
 * @see javax.servlet.http.HttpServlet
 */
public interface Servlet {

    /**
     * Called by the servlet container to indicate to a servlet that the servlet is being placed into service.
     * <p>
     * The servlet container calls the init method exactly once after instantiating the servlet. The
     * init method must complete successfully before the servlet can receive any requests.
     * <p>
     * The servlet container cannot place the servlet into service if the init method
     * <ol>
     * <li>Throws a <code>ServletException</code>
     * <li>Does not return within a time period defined by the Web server
     * </ol>
     *
     * @param config a <code>ServletConfig</code> object containing the servlet's configuration and initialization
     *               parameters
     * @throws ServletException if an exception has occurred that interferes with the servlet's normal operation
     * @see UnavailableException
     * @see #getServletConfig
     */
    /**
     * 负责初始化Servlet对象
     * 在Servlet的生命周期中，该方法执行一次
     * 该方法执行在单线程的环境下，因此开发者不用考虑线程安全的问题
     */
    void init(ServletConfig config) throws ServletException;

    /**
     * Returns a {@link ServletConfig} object, which contains initialization and startup parameters for this servlet.
     * The <code>ServletConfig</code> object returned is the one passed to the <code>init</code> method.
     *
     * <p>
     * Implementations of this interface are responsible for storing the <code>ServletConfig</code> object so that this
     * method can return it. The {@link GenericServlet} class, which implements this interface, already does this.
     *
     * @return the <code>ServletConfig</code> object that initializes this servlet
     * @see #init
     */
    ServletConfig getServletConfig();

    /**
     * Called by the servlet container to allow the servlet to respond to a request.
     *
     * <p>
     * This method is only called after the servlet's <code>init()</code> method has completed successfully.
     *
     * <p>
     * The status code of the response always should be set for a servlet that throws or sends an error.
     *
     *
     * <p>
     * Servlets typically run inside multithreaded servlet containers that can handle multiple requests concurrently.
     * Developers must be aware to synchronize access to any shared resources such as files, network connections, and as
     * well as the servlet's class and instance variables.
     *
     * @param req the <code>ServletRequest</code> object that contains the client's request
     * @param res the <code>ServletResponse</code> object that contains the servlet's response
     * @throws ServletException if an exception occurs that interferes with the servlet's normal operation
     * @throws IOException      if an input or output exception occurs
     */
    /**
     * 负责响应客户的请求
     * 为了提高效率，Servlet规范要求一个 Servlet 实例必须能够同时服务于多个客户端请求
     * 即 service() 方法运行在多线程的环境下，Servlet 开发者必须保证该方法的线程安全性
     */
    void service(ServletRequest req, ServletResponse res) throws ServletException, IOException;

    /**
     * Returns information about the servlet, such as author, version, and copyright.
     *
     * <p>
     * The string that this method returns should be plain text and not markup of any kind (such as HTML, XML, etc.).
     *
     * @return a <code>String</code> containing servlet information
     */
    String getServletInfo();

    /**
     * Called by the servlet container to indicate to a servlet that the servlet is being taken out of service. This
     * method is only called once all threads within the servlet's <code>service</code> method have exited or after a
     * timeout period has passed. After the servlet container calls this method, it will not call the
     * <code>service</code> method again on this servlet.
     *
     * <p>
     * This method gives the servlet an opportunity to clean up any resources that are being held (for example, memory,
     * file handles, threads) and make sure that any persistent state is synchronized with the servlet's current state
     * in memory.
     */
    /**
     * 当 Servlet 对象退出生命周期时，负责释放占用的资源
     */
    void destroy();
}
