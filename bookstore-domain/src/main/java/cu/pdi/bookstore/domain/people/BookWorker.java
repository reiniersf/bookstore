package cu.pdi.bookstore.domain.people;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sec_worker")
@Getter
public class BookWorker extends User {

    @Column(name = "first_name")
    private String name;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

}
