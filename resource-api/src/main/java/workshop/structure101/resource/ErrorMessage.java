package workshop.structure101.resource;

/**
 * @author Stefan Schulze, PENTASYS AG
 * @since 20.10.2017
 */
public class ErrorMessage {

    private final String errorCode;
    private final String errorMessage;
    private final String description;

    public ErrorMessage(String errorCode, String errorMessage, String description) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.description = description;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ErrorMessage{");
        sb.append("errorCode='").append(errorCode).append('\'');
        sb.append(", errorMessage='").append(errorMessage).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
