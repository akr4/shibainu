package net.physalis.shibainu;

import net.physalis.shibainu.presentation.requestid.RequestIdGenerator;
import org.slf4j.MDC;

import javax.servlet.*;
import java.io.IOException;

public class MdcFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            MDC.clear();
            MDC.put("requestId", RequestIdGenerator.generate());
            chain.doFilter(request, response);
        } finally {
            MDC.clear();
        }
    }

    @Override
    public void destroy() {
    }
}
