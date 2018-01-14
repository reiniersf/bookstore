package cu.pdi.bookstore.fx.components.security;

import cu.pdi.bookstore.fx.components.ui.MessageGUIBuilder;
import cu.pdi.bookstore.security.context.JaasSecurityContext;
import cu.pdi.bookstore.security.module.exception.LoginCancelledException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.security.auth.Subject;
import java.io.IOException;

@Aspect
@Component
public class SecurityAspectBookstore {

    private final JaasSecurityContext jaasSecurityContext;
    private final MessageGUIBuilder messageGUIBuilder;

    @Autowired
    public SecurityAspectBookstore(JaasSecurityContext jaasSecurityContext, MessageGUIBuilder messageGUIBuilder) {
        this.jaasSecurityContext = jaasSecurityContext;
        this.messageGUIBuilder = messageGUIBuilder;
    }

    @Around("execution(* cu.pdi.bookstore.fx.gui.LauncherBookstore.launchApp(..)) && " +
            "@annotation(cu.pdi.bookstore.fx.components.security.annotation.LoginRequired)")
    public void checkCredentials(ProceedingJoinPoint proceedingJoinPoint) throws IOException {

        try {
            Subject subject = jaasSecurityContext.logIn();
            if (subject != null) {
                proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
            }
        }catch (LoginCancelledException lce){
            //do nothing...
        }
        catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }
}
