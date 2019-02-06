package cu.pdi.bookstore.fx.components.logging;

import cu.pdi.bookstore.fx.components.logging.annotation.LogMessage;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Component
public class LoggerAspectBookstore {
    private Logger log;

    @Before("@annotation(cu.pdi.bookstore.fx.components.logging.annotation.LogMessage)")
    public void logMessage(JoinPoint joinPoint) {
        LogMessage annotation = extractLogAnnotation(joinPoint);
        log = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        log.info(annotation.message());
    }

    private LogMessage extractLogAnnotation(JoinPoint joinPoint) {

        if (joinPoint.getKind().equals("method-execution")) {
            String methodName = joinPoint.getSignature().getName();
            Class[] argClasses = Arrays.stream(joinPoint.getArgs()).map(Object::getClass).toArray(Class[]::new);
            try {
                Method declaredMethod = joinPoint.getTarget().getClass().getDeclaredMethod(methodName, argClasses);
                return declaredMethod.getDeclaredAnnotation(LogMessage.class);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return joinPoint.getTarget().getClass().getAnnotation(LogMessage.class);
    }
}
