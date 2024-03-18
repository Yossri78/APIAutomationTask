package tests;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.ExcelReader;
import utilities.GenericMethods;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class CreateUserTest {

    ExcelReader reader = new ExcelReader();


        @DataProvider(name = "testData")
        public Object[][] readData() throws IOException {
            return reader.readTestDataFromExcel("Sheet1");
        }

        @Test (dataProvider = "testData")
        public void createUser(String id, String userName, String firstName, String lastName, String emailAddress, String password, String phoneNumber, String userStatus, String updatedEmailAddress) throws IOException {

            RestAssured.baseURI = GenericMethods.getProperty("baseURL");
            ExtractableResponse<Response> fullResponse = given().header("Content-Type" , "application/json")
                    .body("{\n" +
                            "  \"id\":"+ id+",\n" +
                            "  \"username\": \""+userName+"\",\n" +
                            "  \"firstName\": \""+firstName+"\",\n" +
                            "  \"lastName\": \""+lastName+"\",\n" +
                            "  \"email\": \""+emailAddress+"\",\n" +
                            "  \"password\": \""+password+"\",\n" +
                            "  \"phone\": \""+phoneNumber+"\",\n" +
                            "  \"userStatus\":"+userStatus+"\n" +
                            "}")
                    .log()
                    .all()
                    .when()
                    .post(GenericMethods.getProperty("createUserEndPoint"))
                    .then()
                    .assertThat().statusCode(200)
                    .log()
                    .all()
                    .extract();
        }

}
