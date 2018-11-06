package cu.pdi.bookstore.fx.components.security;

import cu.pdi.bookstore.fx.components.logging.annotation.LogMessage;
import cu.pdi.bookstore.fx.components.security.callbackHandler.SimpleCallbackHandler;
import cu.pdi.bookstore.fx.components.security.events.FailedLoginEvent;
import cu.pdi.bookstore.fx.components.security.events.ReadyToLoginEvent;
import cu.pdi.bookstore.fx.components.ui.MessageUIConfig;
import cu.pdi.bookstore.fx.components.security.events.SuccessLoginEvent;
import cu.pdi.bookstore.security.context.JaasSecurityContext;
import cu.pdi.bookstore.security.module.exception.LoginCancelledException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginException;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class SecurityListener {

    private final ApplicationEventPublisher eventPublisher;
    private final JaasSecurityContext jaasSecurityContext;
    private final AtomicInteger retryTimes;

    @Autowired
    public SecurityListener(ApplicationEventPublisher eventPublisher, JaasSecurityContext jaasSecurityContext) {
        this.eventPublisher = eventPublisher;
        this.jaasSecurityContext = jaasSecurityContext;
        this.retryTimes = new AtomicInteger(0);
    }

    @EventListener
    @LogMessage(message = "Executing security checks...")
    public void checkCredentials(ReadyToLoginEvent readyToLoginEvent) {

        CallbackHandler fxCallbackHandler = new SimpleCallbackHandler(readyToLoginEvent.getCredentials());

        try {
            if (jaasSecurityContext.logIn(fxCallbackHandler)) {
                retryTimes.set(4);
                eventPublisher.publishEvent(SuccessLoginEvent.of(jaasSecurityContext.getAuthenticatedSubject()));
            }
        } catch (LoginCancelledException lce) {
            System.exit(0);
        } catch (LoginException le) {
            if (retryTimes.getAndIncrement() >= 3) {
                eventPublisher.publishEvent(FailedLoginEvent.with("Ha excedido el número máximo de intentos.", MessageUIConfig.INFORMATION));
            } else {
                retryTimes.incrementAndGet();
                eventPublisher.publishEvent(FailedLoginEvent.with("Su usuario o contraseña contiene errores." + "\n" + "Por favor rectifique.", MessageUIConfig.WARNING));

            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }


}
