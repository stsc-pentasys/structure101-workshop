package workshop.structure101.messaging.jms;

import workshop.structure101.core.AccountType;
import workshop.structure101.core.CustomerRating;
import workshop.structure101.core.Score;

/**
 * @author Stefan Schulze, PENTASYS AG
 * @since 23.10.2017
 */
class CustomerRatingMapper {

    String fromRating(CustomerRating rating) {
        return rating.getCustomerId() + ";" +
            rating.getFirstName() + ";" +
            rating.getLastName() + ";" +
            rating.getAccountType().name() + ";" +
            rating.getScore().name();
    }

    CustomerRating fromString(String rating) {
        String[] fields = rating.split(";");
        try {
        return new CustomerRating(
            fields[0],
            fields[1],
            fields[2],
            AccountType.valueOf(fields[3]),
            Score.valueOf(fields[4])
        ); } catch (IllegalArgumentException e) {
            throw new MappingException(e);
        }
    }

}
