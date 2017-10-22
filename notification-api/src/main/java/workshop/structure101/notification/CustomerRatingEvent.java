package workshop.structure101.notification;

import java.time.LocalDateTime;

import workshop.structure101.core.CustomerRating;

/**
 * @author Stefan Schulze, PENTASYS AG
 * @since 20.10.2017
 */
public class CustomerRatingEvent {

    private final CustomerRating rating;

    private final LocalDateTime timestamp;

    public CustomerRatingEvent(CustomerRating rating) {
        this.rating = rating;
        this.timestamp = LocalDateTime.now();
    }

    public CustomerRating getRating() {
        return rating;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CustomerRatingEvent{");
        sb.append("rating=").append(rating);
        sb.append(", timestamp=").append(timestamp);
        sb.append('}');
        return sb.toString();
    }


}
