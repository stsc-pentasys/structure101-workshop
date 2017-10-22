package workshop.structure101.resource.jersey;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import workshop.structure101.core.ErrorCode;
import workshop.structure101.core.ServiceException;
import workshop.structure101.resource.ErrorMessage;

import javax.ws.rs.core.Response;

public class ServiceExceptionMapperTest {

    private static final ErrorMessage EXPECTED = new ErrorMessage(
            ErrorCode.DATABASE_ERROR.getCode(),
            ErrorCode.DATABASE_ERROR.getMessage(),
            "Test"
    );

    private final ServiceExceptionMapper underTest = new ServiceExceptionMapper();

    @Test
    public void mapsExceptionToErrorMessage() throws Exception {
        ServiceException e = new ServiceException(ErrorCode.DATABASE_ERROR, "Test");
        Response response = underTest.toResponse(e);
        SoftAssertions.assertSoftly(s -> {
            s.assertThat(response.getStatus()).isEqualTo(500);
            s.assertThat(response.getEntity()).isEqualTo(EXPECTED);
        });
    }
}