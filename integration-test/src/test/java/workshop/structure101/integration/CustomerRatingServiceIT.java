package workshop.structure101.integration;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

import io.restassured.http.ContentType;
import org.junit.Test;
import org.springframework.test.annotation.DirtiesContext;

/**
 * @author Stefan Schulze, PENTASYS AG
 * @since 23.10.2017
 */
public class CustomerRatingServiceIT extends ITBase {

    private static final String CUSTOMER_ID = "01234567";

    private static final String MODIFY_REQUEST = "{" +
        "\"firstName\": \"Otto\"," +
        "\"lastName\": \"Normalverbraucher\"," +
        "\"typeOfAccount\": \"PRIVATE\"," +
        "\"rating\": \"A\"" +
        "}";

    private static final String CREATE_REQUEST = "{" +
        "\"customerId\": \"01234567\", " +
        "\"firstName\": \"Otto\", " +
        "\"lastName\": \"Normalverbraucher\", " +
        "\"typeOfAccount\": \"PRIVATE\", " +
        "\"rating\": " +
        "\"A\"" +
        "}";

    @Test
    public void successfulGet() throws Exception {
        given()
            .pathParam("customerId", CUSTOMER_ID)
            .when()
            .get("/{customerId}")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("customerId", equalTo(CUSTOMER_ID))
            .body("firstName", equalTo("BASE"))
            .body("lastName", equalTo("S.A.R.L"))
            .body("accountType", equalTo("BUSINESS"))
            .body("score", equalTo("E"));
    }

    @Test
    @DirtiesContext
    public void getAfterDelete() throws Exception {
        given()
            .pathParam("customerId", CUSTOMER_ID)
            .when()
            .delete("/{customerId}")
            .then()
            .statusCode(202)
            .body("customerId", equalTo(CUSTOMER_ID))
            .body("firstName", equalTo("BASE"))
            .body("lastName", equalTo("S.A.R.L"))
            .body("accountType", equalTo("BUSINESS"))
            .body("score", equalTo("E"));

        given()
            .pathParam("customerId", CUSTOMER_ID)
            .when()
            .get("/{customerId}")
            .then()
            .statusCode(404);
    }

    @Test
    @DirtiesContext
    public void successfulPut() throws Exception {
        given()
            .pathParam("customerId", CUSTOMER_ID)
            .body(MODIFY_REQUEST)
            .contentType(ContentType.JSON)
            .when()
            .put("/{customerId}")
            .then()
            .statusCode(202)
            .body("customerId", equalTo(CUSTOMER_ID))
            .body("firstName", equalTo("Otto"))
            .body("lastName", equalTo("Normalverbraucher"))
            .body("accountType", equalTo("PRIVATE"))
            .body("score", equalTo("A"));
    }

    @Test
    @DirtiesContext
    public void successfulPost() throws Exception {
        given()
            .body(CREATE_REQUEST)
            .contentType(ContentType.JSON)
            .when()
            .post()
            .then()
            .statusCode(201)
            .header("Location", endsWith("/rating/01234567"));

    }
}
