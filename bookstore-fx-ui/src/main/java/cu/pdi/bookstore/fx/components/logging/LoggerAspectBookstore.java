package cu.pdi.bookstore.fx.components.logging;

import cu.pdi.bookstore.fx.components.security.SecurityAspectBookstore;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspectBookstore {
    private Logger log;

    @Before("@annotation(cu.pdi.bookstore.fx.components.security.annotation.LoginRequired)")
    public void securityLog(JoinPoint joinPoint){
        log = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        log.info("Executing security checks...");
    }
}
