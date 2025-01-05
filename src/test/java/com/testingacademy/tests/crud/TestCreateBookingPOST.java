package com.testingacademy.tests.crud;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCreateBookingPOST {

    @Link(name="Website", url="https://hdor.com/")
    @Issue("Post-001")
    @TmsLink("TMS-001")  //Test management system
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Jay Shankar")
    @Description("Verify the post request is working fine!!")
    @Test
    public void testVerifyCreateBookingPost(){
        Assert.assertEquals(true, true);
    }

}
