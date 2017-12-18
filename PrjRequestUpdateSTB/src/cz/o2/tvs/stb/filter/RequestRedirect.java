package cz.o2.tvs.stb.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class RequestRedirect implements Filter {

    private FilterConfig _filterConfig = null;

    public void init(FilterConfig filterConfig) throws ServletException {
        _filterConfig = filterConfig;
    }

    public void destroy() {
        _filterConfig = null;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("public void doFilter");
        //
        RequestDispatcher rd = request.getRequestDispatcher("/confirmconnect");
        if (rd != null) {
            System.out.println("public void doFilter rd != null");
            rd.forward(request, response);
        }
        else{
            System.out.println("public void doFilter rd == null");
        }
        //   response. .sendError (HttpServletResponse.SC_NO_CONTENT);
        //chain.doFilter(request, response);
    }
}
