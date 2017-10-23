package workshop.structure101.messaging.jms;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import workshop.structure101.core.AccountType;
import workshop.structure101.core.CustomerRating;
import workshop.structure101.core.Score;

/**
 * @author Stefan Schulze, PENTASYS AG
 * @since 23.10.2017
 */
public class CustomerRatingMapperTest {

    private static final CustomerRating RATING = new CustomerRating(
        "01234567",
        "Otto",
        "Normalverbraucher",
        AccountType.PRIVATE,
        Score.A
    );

    private static final String RATING_CSV = "01234567;Otto;Normalverbraucher;PRIVATE;A";
    private static final String DEFECT_ACCOUNT_TYPE_CSV = "01234567;Otto;Normalverbraucher;PUBLIC;A";
    private static final String DEFECT_SCORE_CSV = "01234567;Otto;Normalverbraucher;PRIVATE;X";

    private final CustomerRatingMapper underTest = new CustomerRatingMapper();

    @Test
    public void serialization() throws Exception {
        String result = underTest.fromRating(RATING);
        assertThat(result).isEqualTo("01234567;Otto;Normalverbraucher;PRIVATE;A");
    }

    @Test
    public void deserialization() throws Exception {
        CustomerRating customerRating = underTest.fromString(RATING_CSV);
        assertThat(customerRating).isEqualTo(RATING);
    }

    @Test(expected = MappingException.class)
    public void deserializationFailsWithDefectAccountType() throws Exception {
        underTest.fromString(DEFECT_ACCOUNT_TYPE_CSV);
    }

    @Test(expected = MappingException.class)
    public void deserializationFailsWithDefectScore() throws Exception {
        underTest.fromString(DEFECT_SCORE_CSV);
    }
}