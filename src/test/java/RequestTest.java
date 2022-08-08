import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

public class RequestTest {
    @Test
    void checkSingleUser() {

        String body = "data: { id: 2, email: \"janet.weaver@reqres.in\", first_name: \"Janet\", last_name:" +
                " \"Weaver\", avatar: \"https://reqres.in/img/faces/2-image.jpg\" }, support: { url:" +
                " \"https://reqres.in/#support-heading\", text: \"To keep ReqRes free, " +
                "contributions towards server costs are appreciated!\" } }";

        given()
                .contentType(JSON)
                .body(body)
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .log().body()
                .log().status()
                .statusCode(200);
    }

    @Test
    void checkSingleUsers23() {

        String body = "{}";

        given()
                .body(body)
                .when()
                .get("https://reqres.in/api/users/23")
                .then()
                .log().body()
                .log().status()
                .statusCode(404);
    }

    @Test
    void checkSingleUnknown23() {

        String body = "{}";

        given()
                .body(body)
                .when()
                .get("https://reqres.in/api/unknown/23")
                .then()
                .log().body()
                .log().status()
                .statusCode(404);
    }

    @Test
    void checkAuth() {
        String body = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\" }";
        given()
                .contentType(JSON)
                .body(body)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .body("token", notNullValue());
    }


    @Test
    void checkUNSUCCESSFUL() {
        String body = "{ \"email\": \"peter@klaven\" }";
        given()
                .contentType(JSON)
                .body(body)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

}