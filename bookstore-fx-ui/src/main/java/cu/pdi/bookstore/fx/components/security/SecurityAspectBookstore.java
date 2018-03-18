package cu.pdi.bookstore.fx.components.security;

import cu.pdi.bookstore.fx.components.ui.MessageUIBuilder;
import cu.pdi.bookstore.fx.components.ui.MessageUIConfig;
import cu.pdi.bookstore.security.context.JaasSecurityContext;
import cu.pdi.bookstore.security.module.exception.LoginCancelledException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.security.auth.login.LoginException;
import java.io.IOException;

@Aspect
@Component
public class SecurityAspectBookstore {

    private final JaasSecurityContext jaasSecurityContext;
    private final MessageUIBuilder messageUIBuilder;

    @Autowired
    public SecurityAspectBookstore(JaasSecurityContext jaasSecurityContext, MessageUIBuilder messageUIBuilder) {
        this.jaasSecurityContext = jaasSecurityContext;
        this.messageUIBuilder = messageUIBuilder;
    }

    @Around("execution(* cu.pdi.bookstore.fx.gui.LauncherBookstore.launchApp(..)) && " +
            "@annotation(cu.pdi.bookstore.fx.components.security.annotation.LoginRequired)")
    public Object checkCredentials(ProceedingJoinPoint proceedingJoinPoint) throws IOException {
        int retryTimes = 0;
        Object proceed = null;
        while (retryTimes < 4) {
            try {
                if (jaasSecurityContext.logIn()) {
                    proceed = proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
                    retryTimes = 4;
                }
            } catch (LoginCancelledException lce) {
                System.exit(0);
            } catch (LoginException le) {
                if (retryTimes >= 3) {
                    retryTimes++;
                    messageUIBuilder.createMessage("Ha excedido el número máximo de intentos.", MessageUIConfig.INFORMATION)
                            .show();
                } else {
                    retryTimes++;
                    messageUIBuilder.createMessage("Su usuario o contraseña contiene errores." + "\n" + "Por favor rectifique.", MessageUIConfig.WARNING)
                            .show();
                }
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }

        return proceed;
    }
}
