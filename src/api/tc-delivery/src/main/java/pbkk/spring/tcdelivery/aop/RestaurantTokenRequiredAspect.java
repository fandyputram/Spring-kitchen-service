package pbkk.spring.tcdelivery.aop;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.auth0.jwt.interfaces.DecodedJWT;
import pbkk.spring.tcdelivery.service.*;

@Aspect
@Component
public class RestaurantTokenRequiredAspect {
	@Autowired
	SecurityService securityService;
	
	@Before("@annotation(restaurantTokenRequired)")
	public void tokenRequiredWithAnnotation(RestaurantTokenRequired restaurantTokenRequired) throws Throwable{
		ServletRequestAttributes reqAttributes = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = reqAttributes.getRequest();
		
		// checks for token in request header
		String tokenInHeader = request.getHeader("token");
	//	Token tokenDB=tokenDAO.getByStringToken(tokenInHeader);
		
		if(StringUtils.isEmpty(tokenInHeader)){
			throw new IllegalArgumentException("Empty token");
		}
		
		DecodedJWT verifyToken=securityService.verifyToken(tokenInHeader);
		
		if(verifyToken == null){
			throw new IllegalArgumentException("Token Error : Claim is null");
		}
		
		if(verifyToken.getClaim("username") == null || !verifyToken.getClaim("role").asString().equals("Restaurant")){
			throw new IllegalArgumentException("User token is not authorized");
		}	
	}
}