package workshop.structure101.core;

/**
 * @author Stefan Schulze, PENTASYS AG
 * @since 20.10.2017
 */
public enum ErrorCode {
    DATABASE_ERROR("Failed to connect to database");

    private String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getCode() {
        return name();
    }

    public String getMessage() {
        return message;
    }

}
