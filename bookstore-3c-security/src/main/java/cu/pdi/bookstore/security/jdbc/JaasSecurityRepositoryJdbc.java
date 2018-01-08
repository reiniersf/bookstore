package cu.pdi.bookstore.security.jdbc;

import cu.pdi.bookstore.security.Encriptador;
import cu.pdi.bookstore.security.entities.SecurityPerson;
import cu.pdi.bookstore.security.entities.SecurityRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by R.S.F.
 */
@Component
public class JaasSecurityRepositoryJdbc implements JaasSecurityRepository {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final Map<String, String> queries = new HashMap<>();

    @Autowired
    public JaasSecurityRepositoryJdbc(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    @Override
    public Optional<SecurityPerson> getUserAssociatedPerson(String username, String password) {
        Map<String, String> params = new HashMap<>();
        params.put("user", username);
        params.put("pass", password);
        List<SecurityPerson> securityPeople = namedParameterJdbcTemplate.query(queries.get("userQuery"), params, (ResultSet resultSet, int i) ->
                new SecurityPerson(resultSet.getString("nombre"),
                        resultSet.getString("primer_apellido"),
                        resultSet.getString("segundo_apellido"))
        );
        return Optional.of(securityPeople.get(0));
    }

    @Override
    public List<SecurityRole> getUserAssociatedRoles(String username) {
        Map<String, String> params = new HashMap<>();
        params.put("user", username);

        List<SecurityRole> securityRoles = namedParameterJdbcTemplate.query(queries.get("roleQuery"), params, new RowMapper<SecurityRole>() {
            @Override
            public SecurityRole mapRow(ResultSet resultSet, int i) throws SQLException {
                return new SecurityRole(resultSet.getInt("id_rol"),
                        resultSet.getString("rol"));
            }
        });
        return securityRoles;
    }

    @Override
    public void initQueries(Map<String, ?> queries) {
        this.queries.put("userQuery", (String) queries.get("userQuery"));
        this.queries.put("roleQuery", (String) queries.get("roleQuery"));
    }

    @Override
    public void createDefaultAdminUser() {
        String adminQuery = "Select * From Usuario as u Where u.usuario = 'admin'";
        String adminInsertQuery = "Insert into Usuario Values(1,'admin','" + Encriptador.getStringMessageDigest("admin1234", Encriptador.SHA256) + "' )";
        List<SecurityPerson> securityPeople = namedParameterJdbcTemplate.query(adminQuery, (ResultSet resultSet, int i) ->
                new SecurityPerson(resultSet.getString("nombre"),
                        resultSet.getString("primer_apellido"),
                        resultSet.getString("segundo_apellido"))
        );

        if (!Optional.of(securityPeople.get(0)).isPresent()) {
            namedParameterJdbcTemplate.getJdbcOperations().execute(adminInsertQuery);
        }

    }
}
