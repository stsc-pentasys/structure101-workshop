package workshop.structure101.resource.jersey;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import workshop.structure101.core.CustomerRatingService;
import workshop.structure101.resource.CustomerRatingResource;

/**
 * @author Stefan Schulze, PENTASYS AG
 * @since 20.10.2017
 */
@Configuration
public class ResourcesConfig {

    @Bean
    public CustomerRatingResource customerRatingResource(CustomerRatingService customerRatingService) {
        CustomerRatingResource customerRatingResource =
            new CustomerRatingResourceBean(customerRatingService, uriBuilder());
        resourceConfig().register(customerRatingResource);
        return customerRatingResource;
    }

    @Bean
    public UriBuilder uriBuilder() {
        return new UriBuilder();
    }

    @Bean
    public ResourceConfig resourceConfig() {
        ResourceConfig resourceConfig = new ResourceConfig();
        resourceConfig.register(ServiceExceptionMapper.class);
        return resourceConfig;
    }
}
