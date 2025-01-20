package com.plazoleta.infraestructure.security.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.plazoleta.domain.exception.NoDataFoundException;
import org.springframework.stereotype.Component;

import static com.plazoleta.domain.constants.ErrorAuthConstants.INVALID_TOKEN;

@Component
public class JwtUtils {

    private String privateKey = "5c169ddbe6c9f8f5e7a4f04fdec406333e9882cd289f6fd06c6b30c1a95e5697";
    private String userGenerator = "AUTH0JWT-BACKEND";

    public DecodedJWT verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.privateKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(this.userGenerator)
                    .build();
            return verifier.verify(token);
        }catch (JWTVerificationException exception){
            throw new NoDataFoundException(INVALID_TOKEN);
        }
    }

    public String getUsernameFromToken(DecodedJWT token) {
        return token.getSubject();
    }

    public Claim getSpecificClaim(DecodedJWT token, String claimName) {
        return token.getClaim(claimName);
    }
}