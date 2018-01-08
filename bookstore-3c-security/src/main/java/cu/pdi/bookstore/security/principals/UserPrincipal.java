/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.pdi.bookstore.security.principals;

import java.io.Serializable;
import java.security.Principal;

/**
 *
 * @author R.S.F
 */
public class UserPrincipal implements Principal, Serializable{
    private String dbUser;
    private String nombre;
    private String pApellido;
    private String sApellido;

    public UserPrincipal(String dbUser) {
        this.dbUser = dbUser;
    }

    public UserPrincipal(String dbUser, String nombre, String pApellido, String sApellido) {
        this.dbUser = dbUser;
        this.nombre = nombre;
        this.pApellido = pApellido;
        this.sApellido = sApellido;
    }
    
    
    @Override
    public String getName() {
        return dbUser;
    }

    @Override
    public String toString() {
        return "Usuario "+dbUser;
    }

    @Override
    public int hashCode() {
        return dbUser.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserPrincipal other = (UserPrincipal) obj;
        if ((this.dbUser == null) ? (other.dbUser != null) : !this.dbUser.equals(other.dbUser)) {
            return false;
        }
        return true;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getpApellido() {
        return pApellido;
    }

    public void setpApellido(String pApellido) {
        this.pApellido = pApellido;
    }

    public String getsApellido() {
        return sApellido;
    }

    public void setsApellido(String sApellido) {
        this.sApellido = sApellido;
    }
    
    
    
}
