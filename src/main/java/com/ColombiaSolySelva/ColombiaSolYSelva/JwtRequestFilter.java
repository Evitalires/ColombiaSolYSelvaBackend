package com.ColombiaSolySelva.ColombiaSolYSelva;

import com.ColombiaSolySelva.ColombiaSolYSelva.service.clienteService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private clienteService clienteService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        // 🔎 LOG para confirmar que el filtro corre
        System.out.println("🟢 JwtRequestFilter ejecutándose en: " + request.getRequestURI());

        final String authHeader = request.getHeader("Authorization");

        // Si no hay header o no empieza por Bearer, continúa sin autenticar
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = authHeader.substring(7);

        try {
            String username = jwtUtil.extractUsername(jwt);

            if (username != null &&
                    SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails =
                        clienteService.loadUserByUsername(username);

                if (jwtUtil.validateToken(jwt, userDetails)) {

                    System.out.println("✅ TOKEN VÁLIDO para usuario: " + username);

                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );

                    // 🔥 LÍNEA CLAVE (SIN ESTO HAY 403)
                    authToken.setDetails(
                            new WebAuthenticationDetailsSource()
                                    .buildDetails(request)
                    );

                    SecurityContextHolder.getContext()
                            .setAuthentication(authToken);
                } else {
                    System.out.println("❌ TOKEN INVÁLIDO");
                    System.out.println("🔍 Authorities del usuario: " + userDetails.getAuthorities());
                    System.out.println("🔍 Authentication seteada correctamente");
                }
            }

        } catch (Exception e) {
            System.out.println("❌ Error procesando JWT: " + e.getMessage());
            // No bloqueamos, dejamos que Security decida
            filterChain.doFilter(request, response);
            return;
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getMethod().equalsIgnoreCase("OPTIONS");
    }
}