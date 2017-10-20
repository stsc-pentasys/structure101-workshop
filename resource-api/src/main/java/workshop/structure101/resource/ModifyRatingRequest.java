package workshop.structure101.resource;

/**
 * @author Stefan Schulze, PENTASYS AG
 * @since 20.10.2017
 */
public class ModifyRatingRequest {
    private String firstName;
    private String lastName;

    private String typeOfAccount;
    private String rating;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTypeOfAccount() {
        return typeOfAccount;
    }

    public void setTypeOfAccount(String typeOfAccount) {
        this.typeOfAccount = typeOfAccount;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ModifyRatingRequest{");
        sb.append("firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", typeOfAccount='").append(typeOfAccount).append('\'');
        sb.append(", rating='").append(rating).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
