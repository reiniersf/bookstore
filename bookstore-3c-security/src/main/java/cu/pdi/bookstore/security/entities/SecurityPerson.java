package cu.pdi.bookstore.security.entities;

/**
 * Created by R.S.F.
 */
public class SecurityPerson {

    private String nombre;
    private String primerApellido;
    private String segundoApellido;


    public SecurityPerson(String nombre, String primerApellido, String segundoApellido) {
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido =  segundoApellido;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }
}
