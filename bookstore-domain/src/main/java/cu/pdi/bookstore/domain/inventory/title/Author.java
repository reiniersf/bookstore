package cu.pdi.bookstore.domain.inventory.title;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by taiyou
 * on 8/27/17.
 */
@Embeddable
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
public class Author implements Serializable {
    @NonNull
    String authors;

    @Override
    public String toString() {
        return authors;
    }
}
