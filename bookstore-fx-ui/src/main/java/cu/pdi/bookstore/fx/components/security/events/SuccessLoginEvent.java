package cu.pdi.bookstore.fx.components.security.events;

import cu.pdi.bookstore.security.principals.RolePrincipal;
import cu.pdi.bookstore.security.principals.UserPrincipal;

import javax.security.auth.Subject;
import java.util.Optional;

public class SuccessLoginEvent {
    private final Subject authenticatedSubject;

    public SuccessLoginEvent(Subject authenticatedSubject) {

        this.authenticatedSubject = authenticatedSubject;
    }

    public static SuccessLoginEvent of(Subject authenticatedSubject) {
        return new SuccessLoginEvent(authenticatedSubject);
    }

    public Optional<String> authenticatedUsername() {
        return authenticatedSubject.getPrincipals(UserPrincipal.class).stream()
                .findFirst()
                .map(UserPrincipal::getName);
    }

    public Optional<String> authenticatedUserRoleName() {
        return authenticatedSubject.getPrincipals(RolePrincipal.class).stream()
                .findFirst()
                .map(RolePrincipal::getName);
    }
}
