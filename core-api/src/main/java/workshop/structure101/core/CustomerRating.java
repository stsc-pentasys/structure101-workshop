package workshop.structure101.core;

import java.util.Objects;

/**
 * @author Stefan Schulze, PENTASYS AG
 * @since 20.10.2017
 */
public class CustomerRating {

    private final String customerId;
    private final String firstName;
    private final String lastName;
    private final AccountType accountType;
    private final Score score;

    public CustomerRating(String customerId, String firstName, String lastName, AccountType accountType, Score score) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountType = accountType;
        this.score = score;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public Score getScore() {
        return score;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CustomerRating{");
        sb.append("customerId='").append(customerId).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", accountType=").append(accountType);
        sb.append(", score=").append(score);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerRating that = (CustomerRating) o;
        return Objects.equals(customerId, that.customerId) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                accountType == that.accountType &&
                score == that.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, firstName, lastName, accountType, score);
    }
}
