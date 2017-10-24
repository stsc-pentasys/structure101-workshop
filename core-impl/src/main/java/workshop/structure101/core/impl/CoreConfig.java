package workshop.structure101.core.impl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import workshop.structure101.core.CustomerRatingService;
import workshop.structure101.notification.NotificationPort;
import workshop.structure101.persistence.memory.InMemoryCustomerRatingRepository;

/**
 * @author Stefan Schulze, PENTASYS AG
 * @since 20.10.2017
 */
@Configuration
public class CoreConfig {

    @Bean
    public CustomerRatingService customerRatingService(
        NotificationPort notificationPort,
        InMemoryCustomerRatingRepository repository) {
        return new CustomerRatingServiceBean(notificationPort, repository);
    }
}
