package workshop.structure101.persistence.memory;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import org.junit.Test;
import workshop.structure101.core.AccountType;
import workshop.structure101.core.CustomerRating;
import workshop.structure101.core.Score;
import workshop.structure101.persistence.CustomerRatingRepository;

public class InMemoryCustomerRatingRepositoryTest {

    private static final String CUSTOMER_ID = "01234567";
    private static final CustomerRating EXPECTED = new CustomerRating(
        CUSTOMER_ID,
        "BASE",
        "S.A.R.L",
        AccountType.BUSINESS,
        Score.E
    );
    private static final CustomerRating MODIFIED = new CustomerRating(
        CUSTOMER_ID,
        "Otto",
        "Normalverbraucher",
        AccountType.PRIVATE,
        Score.A
    );

    private final CustomerRatingRepository underTest = new InMemoryCustomerRatingRepository();

    @Test
    public void selectByIdUnmodified() throws Exception {
        Optional<CustomerRating> result = underTest.selectById(CUSTOMER_ID);
        assertThat(result).contains(EXPECTED);
    }

    @Test
    public void selectByIdDeleted() throws Exception {
        Optional<CustomerRating> step1 = underTest.deleteById(CUSTOMER_ID);
        assertThat(step1).contains(EXPECTED);
        Optional<CustomerRating> step2 = underTest.selectById(CUSTOMER_ID);
        assertThat(step2).isEmpty();
    }

    @Test
    public void selectByIdUpdated() throws Exception {
        Optional<CustomerRating> step1 = underTest.update(MODIFIED);
        assertThat(step1).contains(MODIFIED);
        Optional<CustomerRating> step2 = underTest.selectById(CUSTOMER_ID);
        assertThat(step2).contains(MODIFIED);
    }

    @Test
    public void updateAfterDelete() throws Exception {
        Optional<CustomerRating> step1 = underTest.deleteById(CUSTOMER_ID);
        assertThat(step1).contains(EXPECTED);
        Optional<CustomerRating> step2 = underTest.update(MODIFIED);
        assertThat(step2).isEmpty();
    }

    @Test
    public void insertAfterDelete() throws Exception {
        Optional<CustomerRating> step1 = underTest.deleteById(CUSTOMER_ID);
        assertThat(step1).contains(EXPECTED);
        Optional<CustomerRating> step2 = underTest.insert(MODIFIED);
        assertThat(step2).contains(MODIFIED);
    }

    @Test
    public void insertAfterUpdate() throws Exception {
        Optional<CustomerRating> step1 = underTest.update(MODIFIED);
        assertThat(step1).contains(MODIFIED);
        Optional<CustomerRating> step2 = underTest.insert(MODIFIED);
        assertThat(step2).isEmpty();
    }

}