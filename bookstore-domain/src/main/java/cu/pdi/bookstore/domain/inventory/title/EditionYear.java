package cu.pdi.bookstore.domain.inventory.title;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by
 * taiyou on 8/27/17.
 */
@Embeddable
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
public class EditionYear implements Serializable {
    @NonNull
    Integer editionYear;
}
