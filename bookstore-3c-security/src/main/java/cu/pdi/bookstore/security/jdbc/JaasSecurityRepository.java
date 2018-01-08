package cu.pdi.bookstore.security.jdbc;


import cu.pdi.bookstore.security.entities.SecurityPerson;
import cu.pdi.bookstore.security.entities.SecurityRole;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by R.S.F.
 */
public interface JaasSecurityRepository {

    Optional<SecurityPerson> getUserAssociatedPerson(String username, String password);

    List<SecurityRole> getUserAssociatedRoles(String username);

    void initQueries(Map<String, ?> queries);

    void createDefaultAdminUser();
}
