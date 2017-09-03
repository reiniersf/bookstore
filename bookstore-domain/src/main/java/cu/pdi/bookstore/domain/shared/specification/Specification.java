package cu.pdi.bookstore.domain.shared.specification;

/**
 * Created by taiyou
 * on 9/1/17.
 */
public interface Specification<T> {

    boolean isSatisfiedBy(T object);

    default Specification<T> and(Specification<T> specification) {
        return (T object) -> this.isSatisfiedBy(object) && specification.isSatisfiedBy(object);
    }

    default Specification<T> or(Specification<T> specification) {
        return (T object) -> this.isSatisfiedBy(object) || specification.isSatisfiedBy(object);
    }

    static <T> Specification<T> not(Specification<T> specification) {
        return (T object) -> !specification.isSatisfiedBy(object);
    }
}
