package pbkk.spring.tcdelivery.service;

import java.io.IOException;
import java.io.InputStream;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

@Service
public class SecurityServiceImpl implements SecurityService{
	//public static final String secretKey= "4C8okt4LxyKWYLM78sKdXrzbBjDCFyfXUm9sZSI6IkN1c3RvbWVyIiwiVXNlcm5hbWUiOiJ3";
	private static final String ISSUER = "tcdelivery";
	
	private String readFile(String fileName) throws IOException{
		
		InputStream input = new ClassPathResource(fileName).getInputStream();
        byte[] bytes = IOUtils.toByteArray(input);
        return new String(bytes);
	}
	
	@Override
	public DecodedJWT verifyToken(String token) throws IOException {
		try
		{
			RSAPublicKey publicKey=RSAKeysLoader.createPublicKeyPKCS1Format(readFile("key/jwtRS256.key"));//Get the key instance
			Algorithm algorithmRSCheck = Algorithm.RSA256(publicKey, null);
			
			JWTVerifier verifier = JWT.require(algorithmRSCheck)
					.withIssuer(ISSUER)
					.build(); //Reusable verifier instance
			return verifier.verify(token);
		}
		catch (JWTVerificationException exception){
		    //Invalid signature/claims
			return null;
		}	
	}
}