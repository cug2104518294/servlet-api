package javax.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

/**
 * Defines an object to assist a servlet in sending a response to the client. The servlet container creates a
 * <code>ServletResponse</code> object and passes it as an argument to the servlet's <code>service</code> method.
 *
 * <p>
 * To send binary data in a MIME body response, use the {@link ServletOutputStream} returned by
 * {@link #getOutputStream}. To send character data, use the <code>PrintWriter</code> object returned by
 * {@link #getWriter}. To mix binary and text data, for example, to create a multipart response, use a
 * <code>ServletOutputStream</code> and manage the character sections manually.
 *
 * <p>
 * The charset for the MIME body response can be specified explicitly using any of the following techniques: per
 * request, per web-app (using {@link ServletContext#setRequestCharacterEncoding}, deployment descriptor), and per
 * container (for all web applications deployed in that container, using vendor specific configuration). If multiple of
 * the preceding techniques have been employed, the priority is the order listed. For per request, the charset for the
 * response can be specified explicitly using the {@link #setCharacterEncoding} and {@link #setContentType} methods, or
 * implicitly using the {@link #setLocale} method. Explicit specifications take precedence over implicit specifications.
 * If no charset is explicitly specified, ISO-8859-1 will be used. The <code>setCharacterEncoding</code>,
 * <code>setContentType</code>, or <code>setLocale</code> method must be called before <code>getWriter</code> and before
 * committing the response for the character encoding to be used.
 *
 * <p>
 * See the Internet RFCs such as <a href="http://www.ietf.org/rfc/rfc2045.txt"> RFC 2045</a> for more information on
 * MIME. Protocols such as SMTP and HTTP define profiles of MIME, and those standards are still evolving.
 *
 * @author Various
 * @see ServletOutputStream
 */
public interface ServletResponse {

    /**
     * Returns the name of the character encoding (MIME charset) used for the body sent in this response. The following
     * methods for specifying the response character encoding are consulted, in decreasing order of priority: per
     * request, perweb-app (using {@link ServletContext#setResponseCharacterEncoding}, deployment descriptor), and per
     * container (for all web applications deployed in that container, using vendor specific configuration). The first
     * one of these methods that yields a result is returned. Per-request, the charset for the response can be specified
     * explicitly using the {@link #setCharacterEncoding} and {@link #setContentType} methods, or implicitly using the
     * setLocale(java.util.Locale) method. Explicit specifications take precedence over implicit specifications. Calls
     * made to these methods after <code>getWriter</code> has been called or after the response has been committed have
     * no effect on the character encoding. If no character encoding has been specified, <code>ISO-8859-1</code> is
     * returned.
     * <p>
     * See RFC 2047 (http://www.ietf.org/rfc/rfc2047.txt) for more information about character encoding and MIME.
     *
     * @return a <code>String</code> specifying the name of the character encoding, for example, <code>UTF-8</code>
     */
    public String getCharacterEncoding();

    /**
     * Sets the character encoding (MIME charset) of the response being sent to the client, for example, to UTF-8. If
     * the response character encoding has already been set by the {@link ServletContext#setResponseCharacterEncoding},
     * deployment descriptor, or using the setContentType() or setLocale() methods, the value set in this method
     * overrides any of those values. Calling {@link #setContentType} with the <code>String</code> of
     * <code>text/html</code> and calling this method with the <code>String</code> of <code>UTF-8</code> is equivalent
     * with calling <code>setContentType</code> with the <code>String</code> of <code>text/html; charset=UTF-8</code>.
     * <p>
     * This method can be called repeatedly to change the character encoding. This method has no effect if it is called
     * after <code>getWriter</code> has been called or after the response has been committed.
     * <p>
     * Containers must communicate the character encoding used for the servlet response's writer to the client if the
     * protocol provides a way for doing so. In the case of HTTP, the character encoding is communicated as part of the
     * <code>Content-Type</code> header for text media types. Note that the character encoding cannot be communicated
     * via HTTP headers if the servlet does not specify a content type; however, it is still used to encode text written
     * via the servlet response's writer.
     *
     * @param charset a String specifying only the character set defined by IANA Character Sets
     *                (http://www.iana.org/assignments/character-sets)
     * @see #setContentType
     * @see #setLocale
     * @since Servlet 2.4
     */
    public void setCharacterEncoding(String charset);

    /**
     * Returns the content type used for the MIME body sent in this response. The content type proper must have been
     * specified using {@link #setContentType} before the response is committed. If no content type has been specified,
     * this method returns null. If a content type has been specified, and a character encoding has been explicitly or
     * implicitly specified as described in {@link #getCharacterEncoding} or {@link #getWriter} has been called, the
     * charset parameter is included in the string returned. If no character encoding has been specified, the charset
     * parameter is omitted.
     *
     * @return a <code>String</code> specifying the content type, for example, <code>text/html; charset=UTF-8</code>, or
     * null
     * @since Servlet 2.4
     */
    public String getContentType();

    /**
     * Sets the content type of the response being sent to the client, if the response has not been committed yet. The
     * given content type may include a character encoding specification, for example,
     * <code>text/html;charset=UTF-8</code>. The response's character encoding is only set from the given content type
     * if this method is called before <code>getWriter</code> is called.
     * <p>
     * This method may be called repeatedly to change content type and character encoding. This method has no effect if
     * called after the response has been committed. It does not set the response's character encoding if it is called
     * after <code>getWriter</code> has been called or after the response has been committed.
     * <p>
     * Containers must communicate the content type and the character encoding used for the servlet response's writer to
     * the client if the protocol provides a way for doing so. In the case of HTTP, the <code>Content-Type</code> header
     * is used.
     *
     * @param type a <code>String</code> specifying the MIME type of the content
     * @see #setLocale
     * @see #setCharacterEncoding
     * @see #getOutputStream
     * @see #getWriter
     */
    public void setContentType(String type);

    /**
     * Returns a {@link ServletOutputStream} suitable for writing binary data in the response. The servlet container
     * does not encode the binary data.
     *
     * <p>
     * Calling flush() on the ServletOutputStream commits the response.
     * <p>
     * Either this method or {@link #getWriter} may be called to write the body, not both, except when {@link #reset}
     * has been called.
     *
     * @return a {@link ServletOutputStream} for writing binary data
     * @throws IllegalStateException if the <code>getWriter</code> method has been called on this response
     * @throws IOException           if an input or output exception occurred
     * @see #getWriter
     * @see #reset
     */
    public ServletOutputStream getOutputStream() throws IOException;

    /**
     * Returns a <code>PrintWriter</code> object that can send character text to the client. The
     * <code>PrintWriter</code> uses the character encoding returned by {@link #getCharacterEncoding}. If the response's
     * character encoding has not been specified as described in <code>getCharacterEncoding</code> (i.e., the method
     * just returns the default value <code>ISO-8859-1</code>), <code>getWriter</code> updates it to
     * <code>ISO-8859-1</code>.
     * <p>
     * Calling flush() on the <code>PrintWriter</code> commits the response.
     * <p>
     * Either this method or {@link #getOutputStream} may be called to write the body, not both, except when
     * {@link #reset} has been called.
     *
     * @return a <code>PrintWriter</code> object that can return character data to the client
     * @throws java.io.UnsupportedEncodingException if the character encoding returned by
     *                                              <code>getCharacterEncoding</code> cannot be used
     * @throws IllegalStateException                if the <code>getOutputStream</code> method has already been called for this
     *                                              response object
     * @throws IOException                          if an input or output exception occurred
     * @see #getOutputStream
     * @see #setCharacterEncoding
     * @see #reset
     */
    public PrintWriter getWriter() throws IOException;

    /**
     * Sets the length of the content body in the response In HTTP servlets, this method sets the HTTP Content-Length
     * header.
     *
     * @param len an integer specifying the length of the content being returned to the client; sets the Content-Length
     *            header
     */
    public void setContentLength(int len);

    /**
     * Sets the length of the content body in the response In HTTP servlets, this method sets the HTTP Content-Length
     * header.
     *
     * @param len a long specifying the length of the content being returned to the client; sets the Content-Length
     *            header
     * @since Servlet 3.1
     */
    public void setContentLengthLong(long len);

    /**
     * Returns the actual buffer size used for the response. If no buffering is used, this method returns 0.
     *
     * @return the actual buffer size used
     * @see #setBufferSize
     * @see #flushBuffer
     * @see #isCommitted
     * @see #reset
     */
    public int getBufferSize();

    /**
     * Sets the preferred buffer size for the body of the response. The servlet container will use a buffer at least as
     * large as the size requested. The actual buffer size used can be found using <code>getBufferSize</code>.
     *
     * <p>
     * A larger buffer allows more content to be written before anything is actually sent, thus providing the servlet
     * with more time to set appropriate status codes and headers. A smaller buffer decreases server memory load and
     * allows the client to start receiving data more quickly.
     *
     * <p>
     * This method must be called before any response body content is written; if content has been written or the
     * response object has been committed, this method throws an <code>IllegalStateException</code>.
     *
     * @param size the preferred buffer size
     * @throws IllegalStateException if this method is called after content has been written
     * @see #getBufferSize
     * @see #flushBuffer
     * @see #isCommitted
     * @see #reset
     */
    public void setBufferSize(int size);

    /**
     * Forces any content in the buffer to be written to the client. A call to this method automatically commits the
     * response, meaning the status code and headers will be written.
     *
     * @throws IOException if the act of flushing the buffer cannot be completed.
     * @see #setBufferSize
     * @see #getBufferSize
     * @see #isCommitted
     * @see #reset
     */
    public void flushBuffer() throws IOException;

    /**
     * Clears the content of the underlying buffer in the response without clearing headers or status code. If the
     * response has been committed, this method throws an <code>IllegalStateException</code>.
     *
     * @see #setBufferSize
     * @see #getBufferSize
     * @see #isCommitted
     * @see #reset
     * @since Servlet 2.3
     */
    public void resetBuffer();

    /**
     * Returns a boolean indicating if the response has been committed. A committed response has already had its status
     * code and headers written.
     *
     * @return a boolean indicating if the response has been committed
     * @see #setBufferSize
     * @see #getBufferSize
     * @see #flushBuffer
     * @see #reset
     */
    public boolean isCommitted();

    /**
     * Clears any data that exists in the buffer as well as the status code, headers. The state of calling
     * {@link #getWriter} or {@link #getOutputStream} is also cleared. It is legal, for instance, to call
     * {@link #getWriter}, {@link #reset} and then {@link #getOutputStream}. If {@link #getWriter} or
     * {@link #getOutputStream} have been called before this method, then the corrresponding returned Writer or
     * OutputStream will be staled and the behavior of using the stale object is undefined. If the response has been
     * committed, this method throws an <code>IllegalStateException</code>.
     *
     * @throws IllegalStateException if the response has already been committed
     * @see #setBufferSize
     * @see #getBufferSize
     * @see #flushBuffer
     * @see #isCommitted
     */
    public void reset();

    /**
     * Returns the locale specified for this response using the {@link #setLocale} method. Calls made to
     * <code>setLocale</code> after the response is committed have no effect. If no locale has been specified, the
     * container's default locale is returned.
     *
     * @return the Locale for this response.
     * @see #setLocale
     */
    public Locale getLocale();

    /**
     * Sets the locale of the response, if the response has not been committed yet. It also sets the response's
     * character encoding appropriately for the locale, if the character encoding has not been explicitly set using
     * {@link #setContentType} or {@link #setCharacterEncoding}, <code>getWriter</code> hasn't been called yet, and the
     * response hasn't been committed yet. If the deployment descriptor contains a
     * <code>locale-encoding-mapping-list</code> element, and that element provides a mapping for the given locale, that
     * mapping is used. Otherwise, the mapping from locale to character encoding is container dependent.
     * <p>
     * This method may be called repeatedly to change locale and character encoding. The method has no effect if called
     * after the response has been committed. It does not set the response's character encoding if it is called after
     * {@link #setContentType} has been called with a charset specification, after {@link #setCharacterEncoding} has
     * been called, after <code>getWriter</code> has been called, or after the response has been committed.
     * <p>
     * Containers must communicate the locale and the character encoding used for the servlet response's writer to the
     * client if the protocol provides a way for doing so. In the case of HTTP, the locale is communicated via the
     * <code>Content-Language</code> header, the character encoding as part of the <code>Content-Type</code> header for
     * text media types. Note that the character encoding cannot be communicated via HTTP headers if the servlet does
     * not specify a content type; however, it is still used to encode text written via the servlet response's writer.
     *
     * @param loc the locale of the response
     * @see #getLocale
     * @see #setContentType
     * @see #setCharacterEncoding
     */
    public void setLocale(Locale loc);

}
