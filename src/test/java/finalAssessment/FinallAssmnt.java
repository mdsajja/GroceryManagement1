package finalAssessment;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class FinallAssmnt {
	@BeforeClass
    public static void setup() {
        RestAssured.baseURI = "http://localhost:5000"; // URL of your API server
    }

    @Test
    public void testGetAllEmployees() {
        given()
            .when().get("/employees")
            .then()
            .statusCode(200)
            .body("[0].name", equalTo("Sajjad"));
    }

    @Test
    public void testGetEmployeeById() {
        given()
            .when().get("/employees/1")
            .then()
            .statusCode(200)
            .body("name", equalTo("Sajjad"));
    }

    @Test
    public void testAddEmployee() {
        String requestBody = "{ \"name\": \"John\", \"city\": \"New York\" }";

        Response response = given()
            .header("Content-Type", "application/json")
            .body(requestBody)
            .when().post("/employees");

        response.then()
            .statusCode(201)
            .body("name", equalTo("John"));

        // Verify the new employee is in the list
        given()
            .when().get("/employees")
            .then()
            .statusCode(200)
            .body("[2].name", equalTo("John"));
    }

    @Test
    public void testUpdateEmployee() {
        String requestBody = "{ \"name\": \"Sajjad\", \"city\": \"Delhi\" }";

        given()
            .header("Content-Type", "application/json")
            .body(requestBody)
            .when().put("/employees/1")
            .then()
            .statusCode(200)
            .body("city", equalTo("Delhi"));
    }

    @Test
    public void testDeleteEmployee() {
        given()
            .when().delete("/employees/2")
            .then()
            .statusCode(200);

        // Verify the employee has been deleted
        given()
            .when().get("/employees/2")
            .then()
            .statusCode(404);
    }

}
