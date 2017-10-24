package workshop.structure101.persistence.memory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Stefan Schulze, PENTASYS AG
 * @since 20.10.2017
 */
@Configuration
public class PersistenceConfig {

    @Bean
    public InMemoryCustomerRatingRepository customerRatingRepository() {
        return new InMemoryCustomerRatingRepository();
    }
}
