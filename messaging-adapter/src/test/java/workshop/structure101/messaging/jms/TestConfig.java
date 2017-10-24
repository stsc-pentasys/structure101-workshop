package workshop.structure101.messaging.jms;

import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import workshop.structure101.core.CustomerRatingService;

/**
 * @author Stefan Schulze, PENTASYS AG
 * @since 24.10.2017
 */
@SpringBootApplication
public class TestConfig {

    @Bean
    public TestReceiver testReceiver() {
        return new TestReceiver();
    }

    @Bean
    public CustomerRatingService customerRatingService() {
        return Mockito.mock(CustomerRatingService.class);
    }

}
