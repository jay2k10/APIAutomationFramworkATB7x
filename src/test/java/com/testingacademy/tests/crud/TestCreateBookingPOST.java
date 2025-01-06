package com.testingacademy.tests.crud;

import com.testingacademy.base.BaseTest;
import com.testingacademy.endpoints.APIConstants;
import com.testingacademy.pojos.BookingResponse;

import io.qameta.allure.*;
import io.restassured.RestAssured;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;


public class TestCreateBookingPOST extends BaseTest {

    @Link(name = "Website", url = "https://hdor.com/")
    @Issue("Post-001")
    @TmsLink("TMS-001")  //Test management system
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Jay Shankar")
    @Description("Verify the post request is working fine!!")
    @Test
    public void testVerifyCreateBookingPost() {

        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given(requestSpecification).when()
                .body(payloadManager.createPayloadBookingAsString()).post();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        //Default Rest Assured
        validatableResponse.body("booking.firstname", Matchers.equalTo("James"));

        BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());

        // AssertJ
        assertThat(bookingResponse.getBookingid()).isNotNull();
        assertThat(bookingResponse.getBooking().getFirstname()).isNotNull().isNotBlank();
        assertThat(bookingResponse.getBooking().getFirstname()).isEqualTo("James");


        //TestNG Assertions
        Assert.assertEquals(true, true);
    }

}
