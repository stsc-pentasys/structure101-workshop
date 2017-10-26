package workshop.structure101.messaging.jms;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import workshop.structure101.core.CustomerRatingService;

/**
 * @author Stefan Schulze, PENTASYS AG
 * @since 20.10.2017
 */
@Configuration
@EnableJms
public class MessagingConfig {

    @Bean
    public StringMessageReceiver stringMessageReceiver(
        JmsTemplate template,
        @Value("${workshop.structure101.destinationError}") String errorDestination,
        CustomerRatingService service) {
        return new StringMessageReceiver(service, customerRatingMapper(), template, errorDestination);
    }

    @Bean
    public CustomerRatingMapper customerRatingMapper() {
        return new CustomerRatingMapper();
    }

}
