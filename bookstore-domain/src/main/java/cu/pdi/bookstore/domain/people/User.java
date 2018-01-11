package cu.pdi.bookstore.domain.people;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "sec_user")
public class User {
    @Id
    protected String username;

    @Column(name = "user_password")
    protected String password;

    @ManyToOne
    protected Role role;
}
