package cu.pdi.bookstore.domain.people;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "sec_role")
@Getter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_id_seq")
    @SequenceGenerator(name = "role_id_seq", allocationSize = 1, sequenceName = "role_id_seq", initialValue = 2)
    @Column(name = "id_role")
    private Long idRole;

    private String role;

    @Column(name = "description_label")
    private String descriptionLabel;
}
