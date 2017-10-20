package workshop.structure101.core;

/**
 * @author Stefan Schulze, PENTASYS AG
 * @since 20.10.2017
 */
public class ServiceException extends RuntimeException {

    private final ErrorCode errorCode;
    private final String description;

    public ServiceException(ErrorCode errorCode, String description) {
        super(errorCode.getMessage() + ": " + description);
        this.errorCode = errorCode;
        this.description = description;
    }

    public ServiceException(ErrorCode errorCode, String description, Throwable cause) {
        super(errorCode.getMessage() + ": " + description, cause);
        this.errorCode = errorCode;
        this.description = description;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public String getDescription() {
        return description;
    }
}
