package cu.pdi.bookstore.security.entities;

/**
 * Created by R.S.F.
 */
public class SecurityRole {

    private int roleId;
    private String role;

    public SecurityRole(int idRol, String rol) {
        this.roleId = idRol;
        this.role = rol;

    }

    public String getRole() {
        return role;
    }
}
