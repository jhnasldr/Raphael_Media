package com.example.customerservic.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.Authentication;


import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JwtTokenFilter extends OncePerRequestFilter {
    //Checks and validates jwt
    //Gör den tillgänglig för resten av applikationen

    // hämtar jwt från authorization header i requesten.
    //Verifierar att jwt stämmer och är valid och "skriver in det" i securitycontext (håller info om nuvarande autentiserade user,
    // så kan resten av applikationen nå info om kunden när som)
    private final String SECRET_KEY = "your-secret-key"; // Replace with your actual secret key

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);

            try {
                Claims claims = Jwts.parser()
                        .setSigningKey(SECRET_KEY.getBytes())
                        .parseClaimsJws(token)
                        .getBody();

                String username = claims.getSubject();

                List<SimpleGrantedAuthority> authorities = ((List<Map<String, String>>) claims.get("roles")).stream()
                        .map(role -> new SimpleGrantedAuthority(role.get("authority")))
                        .collect(Collectors.toList());


                Authentication auth = new UsernamePasswordAuthenticationToken(username, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch (Exception e) {
                SecurityContextHolder.clearContext();
            }
        }

        filterChain.doFilter(request, response);
    }

}
