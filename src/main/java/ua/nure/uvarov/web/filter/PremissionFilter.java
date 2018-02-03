package ua.nure.uvarov.web.filter;

import ua.nure.uvarov.constants.Parameters;
import ua.nure.uvarov.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.Set;

@WebFilter
public class PremissionFilter implements Filter {
    private Map<String, Set<String>> accessMap;
    private Set<String> urlsSet;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        accessMap = (Map<String, Set<String>>) filterConfig.getServletContext().getAttribute("accessMap");
        urlsSet = (Set<String>) filterConfig.getServletContext().getAttribute("urlPatterns");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponce = (HttpServletResponse) response;
        User user = (User) httpRequest.getSession().getAttribute(Parameters.S_USER);
        String role = Parameters.ROLE_UNKNOWN;
        URL url = new URL(httpRequest.getRequestURL().toString());
        String realPath = url.getPath();
        System.out.println(realPath);
        String important = realPath.substring(httpRequest.getContextPath().length(), realPath.length());
        if (urlsSet.contains(important)) {
            if (user != null) {
                role = user.getRole().name().toLowerCase();
            }
            Set<String> roleAccess = accessMap.get(role);
            if (roleAccess.contains(important)) {
                chain.doFilter(httpRequest, httpResponce);
            } else {
                httpResponce.setStatus(HttpServletResponse.SC_FORBIDDEN);
                httpResponce.sendRedirect("404.jsp");
            }
        } else {
            chain.doFilter(httpRequest, httpResponce);
        }
    }

    @Override
    public void destroy() {

    }
}