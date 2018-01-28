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
public class RolPrincipal implements Principal, Serializable{
    private String rol;

    public RolPrincipal(String rol) {
        this.rol = rol;
    }
    
    
    @Override
    public String getName() {
        return rol;
    }

    @Override
    public String toString() {
        return "Rol: "+rol;
    }
}
