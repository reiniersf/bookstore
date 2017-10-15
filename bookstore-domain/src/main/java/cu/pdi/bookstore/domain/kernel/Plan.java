package cu.pdi.bookstore.domain.kernel;

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
public class Plan implements Serializable {
    @NonNull
    String planName;

    public static Plan withName(String planName) {
        return new Plan(planName);
    }
}
