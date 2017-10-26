package workshop.structure101.messaging.jms;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.assertj.core.api.Assertions;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;

/**
 * @author Stefan Schulze, PENTASYS AG
 * @since 24.10.2017
 */
public class TestReceiver {

    private final CountDownLatch counter = new CountDownLatch(1);

    private String message;

    @JmsListener(destination = "${workshop.structure101.destinationError}")
    public void receive(@Payload String message) {
        this.message = message;
        counter.countDown();
    }

    void assertMessageReceived(long after, String expected) throws InterruptedException {
        counter.await(after, TimeUnit.MILLISECONDS);
        Assertions.assertThat(message).isEqualTo(expected);
    }
}
