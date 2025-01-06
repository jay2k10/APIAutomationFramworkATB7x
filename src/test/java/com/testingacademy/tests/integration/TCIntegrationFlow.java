package com.testingacademy.tests.integration;

import com.testingacademy.base.BaseTest;
import com.testingacademy.endpoints.APIConstants;
import com.testingacademy.pojos.Booking;
import com.testingacademy.pojos.BookingResponse;
import com.testingacademy.utils.PropertyReader;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TCIntegrationFlow extends BaseTest {

    //Create a Booking,  Create a Token
    //Get Booking
    //Update the booking
    //Delete the booking

    @Test(groups = "Integrations", priority = 1)
    @Owner("Jay Shankar")
    @Description("TC#INT1 - Step1 - Verify the booking id is created")
    public void testCreateBooking(ITestContext iTestContext) {
        iTestContext.setAttribute("token", getToken());

        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given(requestSpecification).when()
                .body(payloadManager.createPayloadBookingAsString()).post();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(Integer.parseInt(PropertyReader.readkey("booking.post.status.code.success")));

        //Default Rest Assured
        validatableResponse.body("booking.firstname", Matchers.equalTo(PropertyReader.readkey("booking.post.firstname")));

        BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());

        // AssertJ
        assertThat(bookingResponse.getBookingid()).isNotNull();
        assertThat(bookingResponse.getBooking().getFirstname()).isNotNull().isNotBlank();
        assertThat(bookingResponse.getBooking().getFirstname()).isEqualTo(PropertyReader.readkey("booking.post.firstname"));

        //Set the booking id

        //iTestContext.setAttribute("booking",bookingResponse.getBooking());
        iTestContext.setAttribute("bookingid",bookingResponse.getBookingid());


    }

    @Test(groups = "integration", priority = 2)
    @Owner("Jay Shankar")
    @Description("TC#INT1 - Step 2. Verify that the Booking By ID")
    public void testVerifyBookingId(ITestContext iTestContext) {

        // bookingId -
        System.out.println(iTestContext.getAttribute("bookingid"));
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");



        // GET Req
        String basePathGET = APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingid;
        System.out.println(basePathGET);

        requestSpecification.basePath(basePathGET);
        response = RestAssured
                .given(requestSpecification)
                .when().get();
        validatableResponse = response.then().log().all();
        // Validatable Assertion
        validatableResponse.statusCode(200);

        Booking booking = payloadManager.getResponseFromJSON(response.asString());

        assertThat(booking.getFirstname()).isNotNull().isNotBlank();
        assertThat(booking.getFirstname()).isEqualTo(PropertyReader.readkey("booking.get.firstname"));


    }
    @Test(groups = "integration", priority = 3)
    @Owner("Jay Shankar")
    @Description("TC#INT1 - Step 3. Verify Updated Booking by ID")
    public void testUpdateBookingByID(ITestContext iTestContext) {
        System.out.println("Token - " + iTestContext.getAttribute("token"));
        String token = (String) iTestContext.getAttribute("token");
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");

        String basePathPUTPATCH = APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingid;
        System.out.println(basePathPUTPATCH);


        requestSpecification.basePath(basePathPUTPATCH);
        response = RestAssured
                .given(requestSpecification).cookie("token", token)
                .when().body(payloadManager.fullUpdatePayloadAsString()).put();
        validatableResponse = response.then().log().all();
        // Validatable Assertion
        validatableResponse.statusCode(200);

        Booking booking = payloadManager.getResponseFromJSON(response.asString());

        assertThat(booking.getFirstname()).isNotNull().isNotBlank();
        assertThat(booking.getFirstname()).isEqualTo(PropertyReader.readkey("booking.put.firstname"));
        assertThat(booking.getLastname()).isEqualTo(PropertyReader.readkey("booking.put.lastname"));



    }

    @Test(groups = "integration", priority = 4)
    @Owner("Promode")
    @Description("TC#INT1 - Step 4. Delete the Booking by ID")
    public void testDeleteBookingById(ITestContext iTestContext) {

        String token = (String) iTestContext.getAttribute("token");
        Assert.assertTrue(true);

        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");
        String basePathDELETE = APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingid;
        System.out.println(basePathDELETE);

        requestSpecification.basePath(basePathDELETE).cookie("token", token);
        validatableResponse = RestAssured.given().spec(requestSpecification)
                .when().delete().then().log().all();
        validatableResponse.statusCode(201);


    }


}
