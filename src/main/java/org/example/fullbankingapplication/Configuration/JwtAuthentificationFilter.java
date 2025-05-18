package org.example.fullbankingapplication.Configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/*OncePerRequestFilter est une classe abstraite de Spring que tu peux étendre pour créer ton propre filtre personnalisé, tout en évitant qu'il s'exécute plusieurs fois dans une même requête.*/
@Component
public class JwtAuthentificationFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService; // une interface spring son role est de Fournir un objet UserDetails contenant les données d’un utilisateur (nom, mot de passe, rôles, etc.) à Spring Security.

    public JwtAuthentificationFilter(JwtTokenProvider jwtTokenProvider, UserDetailsService userDetailsService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getTokenFromRequest(request);
        if (StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) { //  On vérifie que le token n’est ni nul ni vide par hasText
            // si le token est NotNull et il est valide on pass
            // Charge les informations complètes de l’utilisateur depuis la base (ou mémoire).
            String username = jwtTokenProvider.getUsername(token);
            System.out.println("Username extrait du token : " + username);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            // Ce token est un "passeport interne" pour Spring Security.
            //Il sert à dire :
            // “Cet utilisateur est vérifié, voici ses rôles, continuez la requête.”
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); // ajouter es informations secondaire au token comme ip et navigateur utiliser
            SecurityContextHolder.getContext().setAuthentication(authenticationToken); // Voilà un utilisateur authentifié avec ce token. Fais-lui confiance.
        }
        filterChain.doFilter(request, response);
    }


    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
