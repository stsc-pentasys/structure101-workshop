package workshop.structure101.resource;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ErrorMessage that = (ErrorMessage) o;
        return Objects.equals(errorCode, that.errorCode) &&
            Objects.equals(errorMessage, that.errorMessage) &&
            Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errorCode, errorMessage, description);
    }
}
