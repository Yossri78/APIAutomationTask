package tests;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.ExcelReader;
import utilities.GenericMethods;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class GetUserDetailsTest {

    ExcelReader reader = new ExcelReader();


    @DataProvider(name = "testData")
    public Object[][] readData() throws IOException {
        return reader.readTestDataFromExcel("Sheet1");
    }

    @Test(dataProvider = "testData")
    public void getUserDetails(String id, String userName, String firstName, String lastName, String emailAddress, String password, String phoneNumber, String userStatus, String updatedEmailAddress) throws IOException {
        RestAssured.baseURI = GenericMethods.getProperty("baseURL");
        ExtractableResponse<Response> fullResponse = given().header("Content-Type" , "application/json")
                .when()
                .get(GenericMethods.getProperty("getUserDetailsEndPoint")+userName)
                .then()
                .assertThat().statusCode(200)
                .log()
                .all()
                .extract();
    }
}
