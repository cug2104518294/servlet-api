package javax.servlet.http;

import java.util.EventListener;

/**
 * Interface for receiving notification events about HttpSession id changes.
 *
 * <p>
 * In order to receive these notification events, the implementation class must be either declared in the deployment
 * descriptor of the web application, annotated with {@link javax.servlet.annotation.WebListener}, or registered via one
 * of the addListener methods defined on {@link javax.servlet.ServletContext}.
 *
 * <p>
 * The order in which implementations of this interface are invoked is unspecified.
 *
 * @since Servlet 3.1
 */
public interface HttpSessionIdListener extends EventListener {

    /**
     * Receives notification that session id has been changed in a session.
     *
     * @param event        the HttpSessionBindingEvent containing the session and the name and (old) value of the
     *                     attribute that was replaced
     * @param oldSessionId the old session id
     */
    void sessionIdChanged(HttpSessionEvent event, String oldSessionId);

}
