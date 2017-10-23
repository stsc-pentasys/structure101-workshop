package workshop.structure101.notification;

/**
 * @author Stefan Schulze, PENTASYS AG
 * @since 20.10.2017
 */
public interface NotificationPort {

    void ratingRequested(CustomerRatingEvent event);

    void ratingDeleted(CustomerRatingEvent event);

    void ratingModified(CustomerRatingEvent event);

    void ratingCreated(CustomerRatingEvent event);

}
