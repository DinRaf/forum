package com.forum.server.security;

import com.forum.server.security.exceptions.TokenAuthenticationHeaderNotFound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.google.common.base.Strings.isNullOrEmpty;

/**
 * Created by Arthur on 18.2.16.
 */
public class TokenAuthenticationFilter extends GenericFilterBean {
    private static final String header = "Auth-Token";
    private static final boolean ignoreFault = false;
    private final Logger log = LoggerFactory.getLogger(TokenAuthenticationFilter.class);
    private final AuthenticationManager authenticationManager;
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    public TokenAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        log.debug("created new TokenAuthenticationFilter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println(("in filter"));

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        forTesting(httpServletRequest);


        try {
            String headerValue = httpServletRequest.getHeader(header);
            if (!isSecuredMethod(httpServletRequest)) {
                log.info("method not secured::" + httpServletRequest.getRequestURI());
                System.out.println(httpServletRequest.getHeader("Confirm-Code"));
                System.out.println(httpServletRequest.getHeader("TempAuth-Token"));
                filterChain.doFilter(servletRequest, servletResponse);
            } else if (isNullOrEmpty(headerValue)) {
                throw new TokenAuthenticationHeaderNotFound("Header " + header + " is not found.", null);
            } else {
                Authentication authentication = authenticationManager.authenticate(new TokenAuthentication(headerValue));
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } catch (AuthenticationException authenticationException) {
            if (!ignoreFault) {
                authenticationEntryPoint.commence(httpServletRequest, httpServletResponse, authenticationException);
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
    }

    private void forTesting(HttpServletRequest httpServletRequest) {
        System.out.println(httpServletRequest.getRequestURI());
        System.out.println(httpServletRequest.getHeaderNames().toString());
        System.out.println(httpServletRequest.getMethod());
        System.out.println(httpServletRequest.getRemotePort());
        System.out.println(httpServletRequest.getPathInfo());
    }

    private boolean isSecuredMethod(HttpServletRequest request) {
        String requestParamForUpload = request.getParameter("reason");
        System.out.println(requestParamForUpload);
        return !(((request.getRequestURI().endsWith("/users") || request.getRequestURI().endsWith("/login"))
                && request.getMethod().equals("POST")));
    }
}