package workshop.structure101.core.impl;

import java.util.Optional;

import workshop.structure101.core.CustomerRating;
import workshop.structure101.core.CustomerRatingService;
import workshop.structure101.notification.CustomerRatingEvent;
import workshop.structure101.notification.NotificationPort;
import workshop.structure101.persistence.CustomerRatingRepository;

/**
 * @author Stefan Schulze, PENTASYS AG
 * @since 20.10.2017
 */
public class CustomerRatingServiceBean implements CustomerRatingService {

    private final NotificationPort notificationPort;
    private final CustomerRatingRepository repository;

    CustomerRatingServiceBean(NotificationPort notificationPort, CustomerRatingRepository repository) {
        this.notificationPort = notificationPort;
        this.repository = repository;
    }

    @Override
    public Optional<CustomerRating> retrieveById(String customerId) {
        Optional<CustomerRating> rating = repository.selectById(customerId);
        rating.ifPresent(r -> notificationPort.ratingRequested(new CustomerRatingEvent(r)));
        return rating;
    }

    @Override
    public Optional<CustomerRating> removeById(String customerId) {
        Optional<CustomerRating> rating = repository.deleteById(customerId);
        rating.ifPresent(r -> notificationPort.ratingDeleted(new CustomerRatingEvent(r)));
        return rating;
    }

    @Override
    public Optional<CustomerRating> modifyById(CustomerRating modifiedRating) {
        Optional<CustomerRating> rating = repository.update(modifiedRating);
        rating.ifPresent(r -> notificationPort.ratingModified(new CustomerRatingEvent(r)));
        return rating;
    }

    @Override
    public Optional<CustomerRating> createNewRating(CustomerRating newRating) {
        Optional<CustomerRating> rating = repository.insert(newRating);
        rating.ifPresent(r -> notificationPort.ratingCreated(new CustomerRatingEvent(r)));
        return rating;
    }
}
