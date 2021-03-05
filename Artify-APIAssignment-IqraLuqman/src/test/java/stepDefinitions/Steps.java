package stepDefinitions;

import org.junit.Assert;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Steps {

	private static final String URI = "https://donutservice.com";
	private static final String Client_ID = "123";
	private static final String Quantity = "2";

	private static Response response;

	@Given("User set donut service api endpoint")
	public void user_set_donut_service_api_endpoint() {

		//API URI setup
		RestAssured.baseURI = URI;
	}

	@When("User set request HEADER")
	public void user_set_request_HEADER() {

		//setting Headers
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		request.header("Accept","application/json");

	}

	@And("User set request BODY")
	public void user_set_request_Body() {

		//setting BODY for API request
		RequestSpecification request = RestAssured.given();
		request.body("{ \"clientId\": \"" + Client_ID + "\", " + "\"quantity\": \"" + Quantity + "\"}");	

	}

	@And("User set request BODY with status cancel")
	public void user_set_request_BODY_with_client_ID() {

		//setting BODY for API request
		RequestSpecification request = RestAssured.given();
		request.body("{ \"orderStatus\": \"" + "cancelled" + "\"}");	
	}

	@And("Send a POST HTTP request")
	public void Send_a_POST_HTTP_request() {

		//send POST request
		RequestSpecification request = RestAssured.given();
		response = request.post("/api/v1/orders");

	}

	@And("Send a GET HTTP request")
	public void Send_a_GET_HTTP_request() {

		//send GET request
		RequestSpecification request = RestAssured.given();
		request.get("/api/v1/orders");

	}

	@And("Send a GET HTTP request with path parameter client ID")
	public void Send_a_GET_HTTP_request_with_path_parameter_client_ID() {

		//send GET request
		RequestSpecification request = RestAssured.given();
		request.get("/api/v1/123");
	}

	@And("Send a GET HTTP request with path parameter nextdelivery")
	public void Send_a_GET_HTTP_request_with_path_parameter_nextdelivery() {

		//send GET request with client ID
		RequestSpecification request = RestAssured.given();
		request.get("/api/v1/nextdelivery");

	}

	@And("Send a PATCH HTTP request with path parameter client ID")
	public void Send_a_PATCH_HTTP_request_with_path_parameter_client_ID() {

		//send PATCH request with client ID
		RequestSpecification request = RestAssured.given();
		request.patch("/api/v1/123");
	}

	@Then("User receive status code {int}")
	public void user_receive_status_code(int code) {

		//validate status code
		Assert.assertEquals(code, response.getStatusCode());
		int StatusCode = response.getStatusCode();
		System.out.println("Status Code --->" + StatusCode);
	}

	@Then("User receive status code {int} OK")
	public void user_receive_status_code_OK(int code) {

		//validate status code
		Assert.assertEquals(code, response.getStatusCode());
		int StatusCode = response.getStatusCode();
		System.out.println("Status Code --->" + StatusCode);
	}

	@And("Response include message resource created")
	public void response_include_message_resource_created() {

		//validate response message
		String responseBody = response.getBody().asString();
		Assert.assertEquals(responseBody.contains(Client_ID), true);
	}

	@And("response includes all order entries and wait time.")
	public void response_includes_all_order_entries_and_wait_time() {

		// Write response to file
		String responseBody = response.getBody().toString();
		System.out.println("responseBody --->" + responseBody);

		// Validate the response
		Assert.assertEquals(responseBody.contains("orders"), true);	 
		System.out.println("responseBody contains orders");

		Assert.assertEquals(responseBody.contains("waitTime"), true);
		System.out.println("responseBody contains wait time");
	}

	@And("User receive valid response that contains queue position number and wait time.")
	public void user_receive_valid_response_that_contains_queue_position_number_and_wait_time() {

		// Write response to file
		String responseBody = response.getBody().toString();
		System.out.println("responseBody --->" + responseBody);


		// First get the JsonPath object instance from the Response interface
		JsonPath jsonPathEvaluator = response.jsonPath();

		// Then simply query the JsonPath object to get a String value of the node
		int Order_num = jsonPathEvaluator.get("order No");

		// Validate the response
		Assert.assertEquals(responseBody.contains("order No"), true);
		Assert.assertTrue(Order_num>=1);
		System.out.println("responseBody contains order number");

		Assert.assertEquals(responseBody.contains("waitTime"), true);
		System.out.println("responseBody contains wait time");

	}

	@And("response should include the order status as cancelled")
	public void Response_should_include_the_order_status_as_cancelled() {

		// Write response to file
		String responseBody = response.getBody().toString();
		System.out.println("responseBody --->" + responseBody);

		// First get the JsonPath object instance from the Response interface
		JsonPath jsonPathEvaluator = response.jsonPath();

		// Then simply query the JsonPath object to get a String value of the node
		int OrderStatus = jsonPathEvaluator.get("orderStatus");

		// Validate the response
		Assert.assertEquals(responseBody.contains("orderStatus"), true);
		Assert.assertEquals(OrderStatus, "cancelled");
		System.out.println("Order status in response is: " + OrderStatus );
	}

	@And("Response include next delivery details")
	public void Response_include_next_delivery_details() {

		// Write response to file
		String responseBody = response.getBody().toString();
		System.out.println("responseBody --->" + responseBody);

		// Validate the response
		Assert.assertEquals(responseBody.contains("order No"), true);
		System.out.println("responseBody contains order number");

		Assert.assertEquals(responseBody.contains("waitTime"), true);
		System.out.println("responseBody contains wait time");

	}

}
