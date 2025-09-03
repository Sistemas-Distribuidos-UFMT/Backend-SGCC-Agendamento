package br.com.ufmt.backendsgccagendamento.services;

import br.com.ufmt.backendsgccagendamento.dtos.TokenDTO;
import br.com.ufmt.backendsgccagendamento.entities.Pessoa;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;


@Service
public class TokenService {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(TokenService.class);
    @Value("${api.security.token.secret}")
    private String secret;
    @Value("${token.expiration.time}")
    private Long expirationTime;
    private static final String ISSUER = "auth-api";
    private static final String TYPE_CLAIM = "type";

    public String generateToken(Pessoa user) {
        return generateToken(user, null, generateExpirationDate());
    }

    public String generate1HourToken(Pessoa user, String tipo) {
        return generateToken(user, tipo, generateExpirationDate(1));
    }

    public String generate1WeekToken(Pessoa user, String tipo) {
        return generateToken(user, tipo, generateExpirationDate(168));
    }

    private String generateToken(Pessoa user, String tipo, Instant expiration) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTCreator.Builder token = JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(user.getCodigo_pessoa().toString())
                    .withExpiresAt(expiration)
                    .withIssuedAt(Instant.now());

            if (tipo != null)
                token.withClaim(TYPE_CLAIM, tipo);

            return token.sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token", exception);
        }
    }

    public Optional<TokenDTO> validateToken(String token) {
        return validateToken(token, null);
    }

    public Optional<TokenDTO> validateToken(String token, String tipo) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build();

            DecodedJWT jwt = verifier.verify(token);
            String type = jwt.getClaim(TYPE_CLAIM).asString();

            if (Objects.equals(type, tipo)) {
                UUID codigoLogin = UUID.fromString(jwt.getSubject());
                LocalDateTime dataCriacao = jwt.getIssuedAt().toInstant().atZone(ZoneOffset.systemDefault()).toLocalDateTime();
                return Optional.of(new TokenDTO(codigoLogin, dataCriacao));
            }

            return Optional.empty();
        } catch (Exception exception) {
            log.error("Erro ao verificar token", exception.getMessage());
            return Optional.empty();
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusSeconds(expirationTime).toInstant(ZoneOffset.of("-04:00"));
    }

    private Instant generateExpirationDate(Integer hours) {
        return LocalDateTime.now().plusHours(hours).toInstant(ZoneOffset.of("-04:00"));
    }
}
