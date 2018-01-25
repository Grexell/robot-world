package by.jazzteam.util;

import javax.servlet.*;
import java.io.IOException;

public class CharsetFilter implements Filter {

    public static final String DEFAULT_ENCODING = "UTF-8";

    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("filter-encoding");
        if (encoding == null) {
            encoding = DEFAULT_ENCODING;
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (!encoding.equals(request.getCharacterEncoding())) {
            request.setCharacterEncoding(encoding);
        }

        response.setCharacterEncoding(encoding);

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
