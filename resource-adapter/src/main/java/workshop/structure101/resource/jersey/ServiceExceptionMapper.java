package workshop.structure101.resource.jersey;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import workshop.structure101.core.ErrorCode;
import workshop.structure101.core.ServiceException;
import workshop.structure101.resource.ErrorMessage;

/**
 * @author Stefan Schulze, PENTASYS AG
 * @since 20.10.2017
 */
@Provider
public class ServiceExceptionMapper implements ExceptionMapper<ServiceException> {

    private static final Logger LOG = LoggerFactory.getLogger(ServiceExceptionMapper.class);

    @Override
    public Response toResponse(ServiceException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        ErrorMessage errorMessage = new ErrorMessage(
            errorCode.getCode(),
            errorCode.getMessage(),
            exception.getDescription());
        LOG.error("System error {}", errorMessage);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorMessage).build();
    }
}
