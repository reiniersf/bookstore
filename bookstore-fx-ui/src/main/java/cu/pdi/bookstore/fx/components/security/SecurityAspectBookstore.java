package cu.pdi.bookstore.fx.components.security;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SecurityAspectBookstore {

    private static final Logger log = LoggerFactory.getLogger(SecurityAspectBookstore.class);


    @Before("execution(* cu.pdi.bookstore.fx.gui.LauncherBookstore.launchApp(..))")
    public void checkCredentials(){
        log.info("Checking valid credentials...");
    }
}
