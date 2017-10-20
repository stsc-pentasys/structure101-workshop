package workshop.structure101.persistence.memory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import workshop.structure101.persistence.CustomerRatingRepository;

/**
 * @author Stefan Schulze, PENTASYS AG
 * @since 20.10.2017
 */
@Configuration
public class PersistenceConfig {

    @Bean
    public CustomerRatingRepository customerRatingRepository() {
        return new InMemoryCustomerRatingRepository();
    }
}
