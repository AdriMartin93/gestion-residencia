package es.adri.gestorResi.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Duration;

@Component
public class GlobalIdempotencyFilter extends OncePerRequestFilter {


    @Autowired(required = false)
    private StringRedisTemplate redisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String method = request.getMethod();

        if ("POST".equalsIgnoreCase(method) || "PUT".equalsIgnoreCase(method) || "DELETE".equalsIgnoreCase(method)) {

            String userIdentifier = request.getUserPrincipal() != null
                    ? request.getUserPrincipal().getName()
                    : request.getRemoteAddr();


            String queryString = request.getQueryString() != null ? "?" + request.getQueryString() : "";
            String idempotencyKey = "idempotency:" + userIdentifier + ":" + request.getRequestURI() + queryString;


            if (redisTemplate != null) {

                Boolean isFirstRequest = redisTemplate.opsForValue()
                        .setIfAbsent(idempotencyKey, "LOCKED", Duration.ofSeconds(3));

                if (Boolean.FALSE.equals(isFirstRequest)) {

                    response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write("{\"error\": \"Petición duplicada detectada. Por favor espera unos segundos.\"}");
                    return;
                }
            }
        }

        // 5. Continuamos con el flujo normal de la aplicación
        filterChain.doFilter(request, response);
    }
}