package pbkk.spring.tcdelivery.service;

import java.io.IOException;

import com.auth0.jwt.interfaces.DecodedJWT;

public interface SecurityService {
	DecodedJWT verifyToken(String token) throws IOException;
}