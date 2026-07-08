package com.JobTrack.JobTrack.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtils {

    // Clé secrète utilisée pour signer et vérifier les JWT
    @Value("${app.jwt.secret}")
    private String secretKey;

    // Temps de validité du JWT (en millisecondes)
    @Value("${app.jwt.expiration}")
    private Long expiratioNTime;

    // Génère un JWT à partir de l'email de l'utilisateur
    public String generateToken(String email) {
        Map<String, Object> claims = new HashMap<>();

        // Pour l'instant aucun claim personnalisé
        return createToken(claims, email);
    }

    // Construit le JWT
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()

                // Informations supplémentaires du JWT
                .setClaims(claims)

                // Identifiant principal (ici l'email)
                .setSubject(subject)

                // Date de création du token
                .setIssuedAt(new Date(System.currentTimeMillis()))

                // Date d'expiration du token
                .setExpiration(new Date(System.currentTimeMillis() + expiratioNTime))

                // Signature du JWT grâce à la clé secrète
                .signWith(getSignKey(), SignatureAlgorithm.HS256)

                // Génère le JWT sous forme de String
                .compact();
    }

    // Transforme la secretKey (String) en objet Key
    private Key getSignKey() {
        byte[] keyBytes = secretKey.getBytes();
        return new SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    // Vérifie que le JWT appartient bien à l'utilisateur
    // et qu'il n'est pas expiré
    public Boolean validateToken(String token, UserDetails userDetails) {

        // Récupération de l'email contenu dans le JWT
        String email = extractEmail(token);

        return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpirationDate(token).before(new Date());
    }

    // Retourne uniquement l'email (subject) contenu dans le JWT
    public String extractEmail(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    private Date extractExpirationDate(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

    // Méthode générique permettant de récupérer n'importe quel claim
    // (email, expiration, date de création, etc.)
    private <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {

        // Lecture complète du JWT afin de récupérer tous les claims
        final Claims claims = extractAllClaims(token);

        // Retourne uniquement le claim demandé
        return claimsResolver.apply(claims);
    }

    // Lit le JWT :
    // - vérifie sa signature avec la secretKey
    // - décode son contenu
    // - retourne tous les claims
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

}
