package com.testingacademy.tests.crud;

import com.testingacademy.base.BaseTest;
import com.testingacademy.endpoints.APIConstants;
import com.testingacademy.pojos.BookingResponse;

import com.testingacademy.utils.PropertyReader;
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
        validatableResponse.statusCode(Integer.parseInt(PropertyReader.readkey("booking.post.status.code.success")));

        //Default Rest Assured
        validatableResponse.body("booking.firstname", Matchers.equalTo(PropertyReader.readkey("booking.post.firstname")));

        BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());

        // AssertJ
        assertThat(bookingResponse.getBookingid()).isNotNull();
        assertThat(bookingResponse.getBooking().getFirstname()).isNotNull().isNotBlank();
        assertThat(bookingResponse.getBooking().getFirstname()).isEqualTo(PropertyReader.readkey("booking.post.firstname"));


        //TestNG Assertions
        Assert.assertEquals(true, true);
    }

}
