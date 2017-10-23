package workshop.structure101.integration;

import static io.restassured.RestAssured.*;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import workshop.structure101.CustomerRatingApplication;

/**
 * @author Stefan Schulze, PENTASYS AG
 * @since 23.10.2017
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = CustomerRatingApplication.class)
public abstract class ITBase {

    @LocalServerPort
    protected int serverPort;

    @Before
    public void setUpRestAssured() throws Exception {
        baseURI = "http://localhost";
        basePath = "/rating";
        port = serverPort;
    }

}
