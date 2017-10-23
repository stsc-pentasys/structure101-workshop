package workshop.structure101.messaging.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import workshop.structure101.core.CustomerRating;
import workshop.structure101.core.CustomerRatingService;

/**
 * @author Stefan Schulze, PENTASYS AG
 * @since 23.10.2017
 */
public class StringMessageReceiver {

    private static final Logger LOG = LoggerFactory.getLogger(StringMessageReceiver.class);
    private final CustomerRatingService service;
    private final CustomerRatingMapper mapper;
    private final JmsTemplate template;
    private final String errorDestination;

    public StringMessageReceiver(
        CustomerRatingService service,
        CustomerRatingMapper mapper,
        JmsTemplate template,
        String errorDestination) {
        this.service = service;
        this.mapper = mapper;
        this.template = template;
        this.errorDestination = errorDestination;
    }

    @JmsListener(destination = "${workshop.structure101.destinationIn}")
    public void receive(String message) {
        LOG.info("Received message {}", message);

        try {
            CustomerRating customerRating = mapper.fromString(message);
            service.createNewRating(customerRating);
        } catch (MappingException e) {
            template.send(errorDestination, session -> session.createTextMessage(message));
        }

    }
}
