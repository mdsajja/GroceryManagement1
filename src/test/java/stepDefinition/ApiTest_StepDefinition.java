package stepDefinition;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.hamcrest.Matchers.equalTo;

public class ApiTest_StepDefinition {

    private RequestSpecification request;
    private Response response;

    @Given("I set up the base URI")
    public void i_set_up_the_base_uri() {
        RestAssured.baseURI = "http://localhost:4000";
        request = RestAssured.given().header("Content-Type", "application/json");
    }

    @When("I register a new user with username {string}, password {string}, and email {string}")
    public void i_register_a_new_user(String username, String password, String email) {
        response = request.body("{ \"username\": \"" + username + "\", \"password\": \"" + password + "\", \"email\": \"" + email + "\" }")
                          .post("/users/register");
    }

    @When("I login with username {string} and password {string}")
    public void i_login_with_username_and_password(String username, String password) {
        response = request.body("{ \"username\": \"" + username + "\", \"password\": \"" + password + "\" }")
                          .post("/users/login");
    }

    @When("I add a product with name {string}, category {string}, price {int}, and stockQuantity {int}")
    public void i_add_a_product(String name, String category, int price, int stockQuantity) {
        response = request.body("{ \"name\": \"" + name + "\", \"category\": \"" + category + "\", \"price\": " + price + ", \"stockQuantity\": " + stockQuantity + " }")
                          .post("/products");
    }

    @When("I search for a product by name {string}")
    public void i_search_for_a_product_by_name(String name) {
        response = request.get("/products/search?name=" + name);
    }

    @When("I place an order with userId {int}, productId {int}, quantity {int}, and totalPrice {int}")
    public void i_place_an_order(int userId, int productId, int quantity, int totalPrice) {
        response = request.body("{ \"userId\": " + userId + ", \"productId\": " + productId + ", \"quantity\": " + quantity + ", \"totalPrice\": " + totalPrice + " }")
                          .post("/orders");
    }

    @When("I retrieve the order with orderId {int}")
    public void i_retrieve_the_order_with_orderId(int orderId) {
        response = request.get("/orders/" + orderId);
    }

    @When("I update the user with userId {int} to username {string} and email {string}")
    public void i_update_the_user_profile(int userId, String username, String email) {
        response = request.body("{ \"username\": \"" + username + "\", \"email\": \"" + email + "\" }")
                          .put("/users/" + userId);
    }

    @When("I delete the user with userId {int}")
    public void i_delete_the_user(int userId) {
        response = request.delete("/users/" + userId);
    }

    @When("I filter products by category {string}")
    public void i_filter_products_by_category(String category) {
        response = request.get("/products/filter?category=" + category);
    }

    @Then("the status code should be {int}")
    public void the_status_code_should_be(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("the response should contain the username {string}")
    public void the_response_should_contain_username(String username) {
        response.then().body("username", equalTo(username));
    }

    @Then("the response message should be {string}")
    public void the_response_message_should_be(String message) {
        response.then().body("message", equalTo(message));
    }

    @Then("the response should contain the product name {string}")
    public void the_response_should_contain_product_name(String productName) {
        response.then().body("[0].name", equalTo(productName));
    }

    @Then("the response should contain the userId {int}")
    public void the_response_should_contain_userId(int userId) {
        response.then().body("userId", equalTo(userId));
    }

    @Then("the response should contain the orderId {int}")
    public void the_response_should_contain_orderId(int orderId) {
        response.then().body("id", equalTo(orderId));
    }

    @Then("the response should contain the updated username {string}")
    public void the_response_should_contain_updated_username(String updatedUsername) {
        response.then().body("username", equalTo(updatedUsername));
    }

    @Then("the response should contain the category {string}")
    public void the_response_should_contain_category(String category) {
        response.then().body("[0].category", equalTo(category));
    }
}

