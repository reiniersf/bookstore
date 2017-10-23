package cu.pdi.bookstore.domain.accounting.title;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
public class Price {
    @NonNull
    private Double price;
}
