package workshop.structure101.persistence;

import java.util.Optional;

import workshop.structure101.core.CustomerRating;

/**
 * @author Stefan Schulze, PENTASYS AG
 * @since 20.10.2017
 */
public interface CustomerRatingRepository {
    Optional<CustomerRating> selectById(String customerId);

    Optional<CustomerRating> deleteById(String customerId);

    Optional<CustomerRating> update(CustomerRating modifiedRating);

    Optional<CustomerRating> insert(CustomerRating newRating);
}
