package workshop.structure101.core;

import java.util.Optional;

/**
 * @author Stefan Schulze, PENTASYS AG
 * @since 20.10.2017
 */
public interface CustomerRatingService {

    Optional<CustomerRating> retrieveById(String customerId);

    Optional<CustomerRating> removeById(String customerId);

    Optional<CustomerRating> modifyById(CustomerRating modifiedRating);

    Optional<CustomerRating> createNewRating(CustomerRating newRating);

}
