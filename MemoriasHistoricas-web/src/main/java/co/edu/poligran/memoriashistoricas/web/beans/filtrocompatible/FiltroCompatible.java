package co.edu.poligran.memoriashistoricas.web.beans.filtrocompatible;

import co.edu.poligran.memoriashistoricas.web.beans.sesion.LoginBean;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */
public class FiltroCompatible implements Filter, Serializable {

    private static final boolean DEBUG = true;

    private FilterConfig filterConfig = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (DEBUG) {
                log("FiltroCompatible:Initializing filter");
            }
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        if (DEBUG) {
            log("FiltroCompatible:doFilter()");
        }

        doBeforeProcessing(request, response);
        HttpServletRequest req = (HttpServletRequest) request;

        //NUEVO
        HttpServletResponse resp = (HttpServletResponse) response;

        String url = req.getServletPath();

        LoginBean loginBean
                = (LoginBean) req.getSession()
                .getAttribute("loginBean");
        if (loginBean != null) {
            if (loginBean.isAutenticado()) {
                try {
                    loginBean.autenticar();
                } catch (Exception ex) {
                    Logger.getLogger(FiltroCompatible.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            } else {
                resp.sendRedirect("/MemoriasHistoricas-web/login/login.xhtml");
            }
        } else {
            resp.sendRedirect("/MemoriasHistoricas-web/login/login.xhtml");
        }

        Throwable problem = null;
        try {
            chain.doFilter(request, response);
        } catch (Throwable t) {
            // If an exception is thrown somewhere down the filter chain,
            // we still want to execute our after processing, and then
            // rethrow the problem after that.
            problem = t;
            t.printStackTrace();
        }

        doAfterProcessing(request, response);

        // If there was a problem, we want to rethrow it if it is
        // a known type, otherwise log it.
        if (problem != null) {
            if (problem instanceof ServletException) {
                throw (ServletException) problem;
            }
            if (problem instanceof IOException) {
                throw (IOException) problem;
            }
            sendProcessingError(problem, response);
        }
    }

    @Override
    public void destroy() {

    }

    private void doBeforeProcessing(ServletRequest request,
            ServletResponse response)
            throws IOException, ServletException {
        if (DEBUG) {
            log("SecurityFilter:DoBeforeProcessing");
        }

    }

    private void doAfterProcessing(ServletRequest request,
            ServletResponse response)
            throws IOException, ServletException {
        if (DEBUG) {
            log("SecurityFilter:DoAfterProcessing");
        }
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n"
                        .concat("<body>\n"));
                pw.print("<h1>The resource did not process correctly</h1>\n"
                        .concat("<pre>\n"));
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>");
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("FiltroCompatible()");
        }
        StringBuffer sb = new StringBuffer("FiltroCompatible()");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }
}
