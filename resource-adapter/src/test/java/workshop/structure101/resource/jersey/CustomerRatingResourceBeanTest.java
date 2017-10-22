package workshop.structure101.resource.jersey;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import workshop.structure101.core.AccountType;
import workshop.structure101.core.CustomerRating;
import workshop.structure101.core.CustomerRatingService;
import workshop.structure101.core.Score;
import workshop.structure101.resource.CustomerRatingResource;
import workshop.structure101.resource.ModifyRatingRequest;
import workshop.structure101.resource.NewRatingRequest;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.SoftAssertions.*;
import static org.mockito.Mockito.*;

public class CustomerRatingResourceBeanTest {

    private static final String CUSTOMER_ID = "01234567";
    private static final CustomerRating RATING = new CustomerRating(
            CUSTOMER_ID,
            "Otto",
            "Normalverbraucher",
            AccountType.PRIVATE,
            Score.C
    );
    private final CustomerRatingResource underTest;

    private final CustomerRatingService serviceMock;

    private final UriBuilder uriBuilderMock;

    public CustomerRatingResourceBeanTest() {
        serviceMock = mock(CustomerRatingService.class);
        uriBuilderMock = mock(UriBuilder.class);
        underTest = new CustomerRatingResourceBean(serviceMock, uriBuilderMock);
    }

    @Test
    public void successfulGet() throws Exception {
        when(serviceMock.retrieveById(CUSTOMER_ID)).thenReturn(Optional.of(RATING));
        Response result = underTest.getRatingByCustomerId(CUSTOMER_ID);
        assertSoftly(s -> {
            s.assertThat(result.getStatus()).isEqualTo(200);
            s.assertThat(result.getEntity()).isEqualTo(RATING);
        });
    }

    @Test
    public void failedGet() throws Exception {
        when(serviceMock.retrieveById(CUSTOMER_ID)).thenReturn(Optional.empty());
        Response result = underTest.getRatingByCustomerId(CUSTOMER_ID);
        assertThat(result.getStatus()).isEqualTo(404);
    }

    @Test
    public void successfulPut() throws Exception {
        when(serviceMock.modifyById(RATING)).thenReturn(Optional.of(RATING));
        ModifyRatingRequest request = createPutRequest();
        Response response = underTest.putExistingRating(CUSTOMER_ID, request);
        assertSoftly(s -> {
            s.assertThat(response.getStatus()).isEqualTo(202);
            s.assertThat(response.getEntity()).isEqualTo(RATING);
        });
    }

    @Test
    public void failedPut() throws Exception {
        when(serviceMock.modifyById(RATING)).thenReturn(Optional.empty());
        ModifyRatingRequest request = createPutRequest();
        Response response = underTest.putExistingRating(CUSTOMER_ID, request);
        assertThat(response.getStatus()).isEqualTo(404);
    }

    private ModifyRatingRequest createPutRequest() {
        ModifyRatingRequest request = new ModifyRatingRequest();
        request.setFirstName(RATING.getFirstName());
        request.setLastName(RATING.getLastName());
        request.setTypeOfAccount(RATING.getAccountType().name());
        request.setRating(RATING.getScore().name());
        return request;
    }


    @Test
    public void successfulDelete() throws Exception {
        when(serviceMock.removeById(CUSTOMER_ID)).thenReturn(Optional.of(RATING));
        Response result = underTest.deleteExistingRating(CUSTOMER_ID);
        assertSoftly(s -> {
            s.assertThat(result.getStatus()).isEqualTo(202);
            s.assertThat(result.getEntity()).isEqualTo(RATING);
        });
    }

    @Test
    public void failedDelete() throws Exception {
        when(serviceMock.removeById(CUSTOMER_ID)).thenReturn(Optional.empty());
        Response result = underTest.deleteExistingRating(CUSTOMER_ID);
        assertThat(result.getStatus()).isEqualTo(404);
    }

    @Test
    public void successfulPost() throws Exception {
        when(serviceMock.createNewRating(RATING)).thenReturn(Optional.of(RATING));
        UriInfo uriInfo = mock(UriInfo.class);
        URI url = URI.create("/test/" + CUSTOMER_ID);
        when(uriBuilderMock.buildURI(RATING, uriInfo)).thenReturn(url);
        Response response = underTest.postNewRating(createPostRequest(), uriInfo);
        assertSoftly(s -> {
            s.assertThat(response.getStatus()).isEqualTo(201);
            s.assertThat(response.getLocation()).isEqualTo(url);
        });
    }

    @Test
    public void failedPost() throws Exception {
        when(serviceMock.createNewRating(RATING)).thenReturn(Optional.empty());
        UriInfo uriInfo = mock(UriInfo.class);
        Response response = underTest.postNewRating(createPostRequest(), uriInfo);
        assertThat(response.getStatus()).isEqualTo(409);
    }

    private NewRatingRequest createPostRequest() {
        NewRatingRequest request = new NewRatingRequest();
        request.setCustomerId(CUSTOMER_ID);
        request.setFirstName(RATING.getFirstName());
        request.setLastName(RATING.getLastName());
        request.setTypeOfAccount(RATING.getAccountType().name());
        request.setRating(RATING.getScore().name());
        return request;
    }

}