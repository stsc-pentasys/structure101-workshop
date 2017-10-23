package workshop.structure101.core.impl;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.Test;
import workshop.structure101.core.AccountType;
import workshop.structure101.core.CustomerRating;
import workshop.structure101.core.CustomerRatingService;
import workshop.structure101.core.Score;
import workshop.structure101.notification.CustomerRatingEvent;
import workshop.structure101.notification.NotificationPort;
import workshop.structure101.persistence.CustomerRatingRepository;

public class CustomerRatingServiceBeanTest {

    private static final String CUSTOMER_ID = "01234567";
    private static final CustomerRating RATING = new CustomerRating(
        CUSTOMER_ID,
        "Otto",
        "Normalverbraucher",
        AccountType.PRIVATE,
        Score.A
    );
    private final NotificationPort notificationPortMock = mock(NotificationPort.class);
    private final CustomerRatingRepository repositoryMock = mock(CustomerRatingRepository.class);
    private final CustomerRatingService underTest =
        new CustomerRatingServiceBean(notificationPortMock, repositoryMock);

    @Test
    public void retrieveByExistingId() throws Exception {
        when(repositoryMock.selectById(CUSTOMER_ID)).thenReturn(Optional.of(RATING));
        Optional<CustomerRating> result = underTest.retrieveById(CUSTOMER_ID);
        assertThat(result).contains(RATING);
        verify(notificationPortMock).ratingRequested(any(CustomerRatingEvent.class));
    }

    @Test
    public void retrieveByMissingId() throws Exception {
        when(repositoryMock.selectById(CUSTOMER_ID)).thenReturn(Optional.empty());
        Optional<CustomerRating> result = underTest.retrieveById(CUSTOMER_ID);
        assertThat(result).isEmpty();
        verifyZeroInteractions(notificationPortMock);
    }

    @Test
    public void removeByExistingId() throws Exception {
        when(repositoryMock.deleteById(CUSTOMER_ID)).thenReturn(Optional.of(RATING));
        Optional<CustomerRating> result = underTest.removeById(CUSTOMER_ID);
        assertThat(result).contains(RATING);
        verify(notificationPortMock).ratingDeleted(any(CustomerRatingEvent.class));
    }

    @Test
    public void removeByMissingId() throws Exception {
        when(repositoryMock.deleteById(CUSTOMER_ID)).thenReturn(Optional.empty());
        Optional<CustomerRating> result = underTest.removeById(CUSTOMER_ID);
        assertThat(result).isEmpty();
        verifyZeroInteractions(notificationPortMock);
    }

    @Test
    public void modifyByExistingId() throws Exception {
        when(repositoryMock.update(RATING)).thenReturn(Optional.of(RATING));
        Optional<CustomerRating> result = underTest.modifyById(RATING);
        assertThat(result).contains(RATING);
        verify(notificationPortMock).ratingModified(any(CustomerRatingEvent.class));
    }

    @Test
    public void modifyByMissingId() throws Exception {
        when(repositoryMock.update(RATING)).thenReturn(Optional.empty());
        Optional<CustomerRating> result = underTest.modifyById(RATING);
        assertThat(result).isEmpty();
        verifyZeroInteractions(notificationPortMock);
    }

    @Test
    public void createWithNewId() throws Exception {
        when(repositoryMock.insert(RATING)).thenReturn(Optional.of(RATING));
        Optional<CustomerRating> result = underTest.createNewRating(RATING);
        assertThat(result).contains(RATING);
        verify(notificationPortMock).ratingCreated(any(CustomerRatingEvent.class));
    }

    @Test
    public void createWithExistingId() throws Exception {
        when(repositoryMock.insert(RATING)).thenReturn(Optional.empty());
        Optional<CustomerRating> result = underTest.createNewRating(RATING);
        assertThat(result).isEmpty();
        verifyZeroInteractions(notificationPortMock);
    }

}