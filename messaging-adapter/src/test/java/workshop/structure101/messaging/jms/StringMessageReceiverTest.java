package workshop.structure101.messaging.jms;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import workshop.structure101.core.CustomerRating;
import workshop.structure101.core.CustomerRatingService;

/**
 * @author Stefan Schulze, PENTASYS AG
 * @since 24.10.2017
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
public class StringMessageReceiverTest {

    private static final String RATING_CSV = "01234567;Otto;Normalverbraucher;PRIVATE;A";
    private static final String DEFECT_CSV = "01234567;Otto;Normalverbraucher;PUBLIC;A";

    @Autowired
    private StringMessageReceiver underTest;

    @Autowired
    private CustomerRatingService serviceMock;

    @Autowired
    private TestReceiver testReceiver;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${workshop.structure101.destinationIn}")
    private String destinationIn;

    private ArgumentCaptor<CustomerRating> ratingCaptor = ArgumentCaptor.forClass(CustomerRating.class);

    @Test
    public void receiveMessage() throws Exception {
        CountDownLatch counter = new CountDownLatch(1);
        when(serviceMock.createNewRating(ratingCaptor.capture())).thenAnswer(invocation -> {
            counter.countDown();
            return Optional.of(invocation.getArgumentAt(0, CustomerRating.class));
        });
        jmsTemplate.send(destinationIn, s -> s.createTextMessage(RATING_CSV));
        counter.await(1000, TimeUnit.MILLISECONDS);
        assertThat(ratingCaptor.getValue().getCustomerId()).isEqualTo("01234567");
    }

    @Test
    public void receiveDefectMessage() throws Exception {
        jmsTemplate.send(destinationIn, s -> s.createTextMessage(DEFECT_CSV));
        testReceiver.assertMessageReceived(1000, DEFECT_CSV);
    }
}