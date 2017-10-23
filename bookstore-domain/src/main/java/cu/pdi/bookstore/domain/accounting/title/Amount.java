package cu.pdi.bookstore.domain.accounting.title;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
public class Amount {
    @NonNull
    private Double amount;
}
