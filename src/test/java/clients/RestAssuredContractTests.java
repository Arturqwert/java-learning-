package clients;

import clients.model.EmployeeDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RestAssuredContractTests {
    @BeforeAll
    public static void setUp()
    {
        RestAssured.baseURI = "https://x-clients-be.onrender.com";
        RestAssured.basePath = "/company";
    }

    @Test
    public void shouldReceive200okStatus()
    {
        given()
                .when()
                .get()
                .then()
                .header("content-length", "2115")
                .statusCode(200);
    }

    @Test
    public void shouldReceive201StatusOnCreateEmployee()
    {
        String token = getToken("roxy", "animal-fairy");
        given()
                .when()
                .basePath("/employee")
                .contentType(ContentType.JSON)
                .header(new Header("x-client-token", token))
                .body(
 "{\"id\":222,\"firstName\":\"artur\",\"lastName\":\"morgan\",\"companyId\":4257,\"email\":\"morgan@mail.ru\",\"phone\":\"string\"}"
                )
                .post()
                .then()
                .statusCode(201)
                .log()
                .all()
                .extract()
                .body();

    }

    @Test
    public void shouldReceive201StatusOnCreateEmployeeUseDto()
    {
        String token = getToken("roxy", "animal-fairy");
        EmployeeDto emp = new EmployeeDto(
                1,
                "dto",
                "dto",
                4257,
                "dto@mail.ru",
                "dfsdf"
        );

        given()
                .when()
                .basePath("/employee")
                .contentType(ContentType.JSON)
                .header(new Header("x-client-token", token))
                .body(
                        emp
                )
                .log()
                .all()
                .post()
                .then()
                .statusCode(201)
                .log()
                .all()
                .extract()
                .body();

    }

    public String getToken(String log, String pas)
    {
        return given()
            .when()
            .basePath("/auth")
            .body("{\"username\": \"" + log + "\",\"password\": \""+pas+"\"}")
                .contentType(ContentType.JSON)
            .post("/login")
            .then()
            .extract()
            .jsonPath().getString("userToken");
    }
}
