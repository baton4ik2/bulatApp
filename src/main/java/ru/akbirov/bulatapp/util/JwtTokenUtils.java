package ru.akbirov.bulatapp.util;

import io.jsonwebtoken.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.akbirov.bulatapp.exception.JwtAuthenticationException;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtils {

    @Value("${jwt.secret}")
    private String secret; // Секретный ключ для подписи JWT

    @Value("${jwt.lifetime}")
    private Duration jwtLifetime; // Время жизни токена

    private SecretKey secretKey; // Ключ для подписи токена

    @PostConstruct
    public void init() {
        // Инициализация secretKey после внедрения зависимостей
        this.secretKey = new SecretKeySpec(secret.getBytes(), SignatureAlgorithm.HS256.getJcaName());
    }

    public String generateToken(UserDetails userDetails) {
        // Создание полезной нагрузки (claims) для токена
        Map<String, Object> claims = createClaims(userDetails);
        Date issuedDate = new Date(); // Дата создания токена
        Date expiredDate = new Date(issuedDate.getTime() + jwtLifetime.toMillis()); // Дата истечения токена

        // Генерация JWT
        return Jwts.builder()
                .setClaims(claims) // Установка полезной нагрузки
                .setSubject(userDetails.getUsername()) // Установка субъекта (имя пользователя)
                .setIssuedAt(issuedDate) // Установка даты создания
                .setExpiration(expiredDate) // Установка даты истечения
                .signWith(secretKey) // Подпись токена с использованием secretKey
                .compact(); // Компактное представление токена
    }

    private Map<String, Object> createClaims(UserDetails userDetails) {
        // Инициализация карты для хранения полезной нагрузки
        Map<String, Object> claims = new HashMap<>();
        // Получение списка ролей пользователя
        List<String> roleList = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority) // Преобразование ролей в строки
                .toList();
        claims.put("roles", roleList); // Добавление ролей в полезную нагрузку
        return claims; // Возврат карты с полезной нагрузкой
    }

    public String getUsername(String token) {
        // Извлечение имени пользователя из токена
        return getAllClaimsFromToken(token).getSubject();
    }

    public List<String> getRoles(String token) {
        // Извлечение ролей из токена
        List<?> roles = getAllClaimsFromToken(token).get("roles", List.class);
        // Преобразование ролей в список строк
        return roles.stream()
                .map(Object::toString) // Приведение к строке
                .collect(Collectors.toList()); // Сбор в список
    }

    private Claims getAllClaimsFromToken(String token) {
        // Создание парсера для разбора JWT
        JwtParser parser = Jwts.parserBuilder()
                .setSigningKey(secretKey) // Установка ключа подписи
                .build();

        // Разбор токена и получение его полезной нагрузки
        try {
            return parser.parseClaimsJws(token).getBody();
        } catch (JwtException e) {
            throw new JwtAuthenticationException("Invalid JWT token: " + e.getMessage());
        }
    }
}
