package main.java.com.jetsly.webapp.shiro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

public class JWSAuthenticationFilter extends GenericFilterBean {

      private static final Logger log = LoggerFactory.getLogger(JWSAuthenticationFilter.class);

      protected static final String AUTHORIZATION_HEADER = "Authorization";

      //BASIC
      private String  authcScheme =HttpServletRequest.BASIC_AUTH;

      @Override
      public void doFilter(final ServletRequest req,
                           final ServletResponse res,
                           final FilterChain chain) throws IOException, ServletException {
          final HttpServletRequest request = (HttpServletRequest) req;

          final String authHeader = request.getHeader(AUTHORIZATION_HEADER);
          if (authHeader == null || !authHeader.startsWith(authcScheme)) {
              throw new ServletException("Missing or invalid Authorization header.");
          }

          final String token = authHeader.substring(authcScheme.length()+1);
          final String secrectkey="Frostmourne2015Snowsong";
          try {
              final Claims claims = Jwts.parser().setSigningKey(secrectkey)
                  .parseClaimsJws(token).getBody();
              request.setAttribute("claims", claims);
          }
          catch (final SignatureException e) {
              throw new ServletException("Invalid token.");
          }

          chain.doFilter(req, res);
      }

}
