/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.pdi.bookstore.security.principals;

import java.io.Serializable;
import java.security.Principal;

/**
 *
 * @author developer
 */
public class PasswordPrincipal implements Principal, Serializable{
    private String dbPass;

    public PasswordPrincipal(String dbPass) {
        this.dbPass = dbPass;
    }

    @Override
    public int hashCode() {
        return dbPass.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PasswordPrincipal other = (PasswordPrincipal) obj;
        return (this.dbPass == null) ? other.dbPass == null : this.dbPass.equals(other.dbPass);
    }
    
      
    @Override
    public String getName() {
       return dbPass;
    }

    @Override
    public String toString() {
        return "Password "+dbPass;
    }
    
    
}
