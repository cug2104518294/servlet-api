package javax.servlet.http;

import java.util.EventListener;

/**
 * Interface for receiving notification events about HttpSession lifecycle changes.
 *
 * <p>
 * In order to receive these notification events, the implementation class must be either declared in the deployment
 * descriptor of the web application, annotated with {@link javax.servlet.annotation.WebListener}, or registered via one
 * of the addListener methods defined on {@link javax.servlet.ServletContext}.
 *
 * <p>
 * Implementations of this interface are invoked at their {@link #sessionCreated} method in the order in which they have
 * been declared, and at their {@link #sessionDestroyed} method in reverse order.
 *
 * @see HttpSessionEvent
 * @since Servlet 2.3
 */
public interface HttpSessionListener extends EventListener {

    /**
     * Receives notification that a session has been created.
     *
     * @param se the HttpSessionEvent containing the session
     * @implSpec The default implementation takes no action.
     */
    default public void sessionCreated(HttpSessionEvent se) {
    }

    /**
     * Receives notification that a session is about to be invalidated.
     *
     * @param se the HttpSessionEvent containing the session
     * @implSpec The default implementation takes no action.
     */
    default public void sessionDestroyed(HttpSessionEvent se) {
    }
}
