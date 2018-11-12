package cu.pdi.bookstore.extension.annotation.bdd;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AssumeUserAuthenticated {
    String username() default "admin";
    String password() default "admin1234";
}
