package workshop.structure101.notification.log;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import workshop.structure101.notification.NotificationPort;

/**
 * @author Stefan Schulze, PENTASYS AG
 * @since 20.10.2017
 */
@Configuration
public class NotificationConfig {

    @Bean
    public NotificationPort notificationPort() {
        return new LoggingNotificationAdapter();
    }
}
