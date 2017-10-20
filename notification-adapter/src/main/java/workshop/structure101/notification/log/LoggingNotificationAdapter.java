package workshop.structure101.notification.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import workshop.structure101.notification.CustomerRatingEvent;
import workshop.structure101.notification.NotificationPort;

/**
 * @author Stefan Schulze, PENTASYS AG
 * @since 20.10.2017
 */
public class LoggingNotificationAdapter implements NotificationPort {

    private static final Logger LOG = LoggerFactory.getLogger(LoggingNotificationAdapter.class);

    @Override
    public void ratingRequested(CustomerRatingEvent event) {
        LOG.info("Rating requested: {}", event.getRating());
    }

    @Override
    public void ratingDeleted(CustomerRatingEvent event) {
        LOG.info("Rating deleted: {}", event.getRating());
    }

    @Override
    public void ratingModified(CustomerRatingEvent event) {
        LOG.info("Rating modified: {}", event.getRating());
    }

    @Override
    public void ratingCreated(CustomerRatingEvent event) {
        LOG.info("Rating created: {}", event.getRating());
    }
}
