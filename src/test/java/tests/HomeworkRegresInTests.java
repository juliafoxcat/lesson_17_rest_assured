package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class HomeworkRegresInTests {

    public final int STATUS_CODE_200 = 200;
    public final int STATUS_CODE_404 = 404;
    public final int STATUS_CODE_400 = 400;

    @Test
    @DisplayName("Успешная регистрация пользователя")
    void registerSuccessfulTest() {
        final String uri = "https://reqres.in/api/register";
        final int id = 4;
        final String token = "QpwL5tke4Pnpja7X4";

        JSONObject requestCredits = new JSONObject()
                .put("email", "eve.holt@reqres.in")
                .put("password", "pistol");

        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(requestCredits.toString())
                .when()
                .post(uri)
                .then()
                .log().status()
                .log().body()
                .statusCode(STATUS_CODE_200)
                .body("id", is(id))
                .body("token", is(token));
    }

    @Test
    @DisplayName("Неуспешная регистрация пользователя")
    void registerUnsuccessfulTest() {
        final String uri = "https://reqres.in/api/register";
        final String error = "Missing password";

        JSONObject requestCredits = new JSONObject()
                .put("email", "sydney@fife");

        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(requestCredits.toString())
                .when()
                .post(uri)
                .then()
                .log().status()
                .log().body()
                .statusCode(STATUS_CODE_400)
                .body("error", is(error));
    }

    @Test
    @DisplayName("Неуспешный поиск пользователя")
    void searchUnsuccessfulTest() {
        final String uri = "https://reqres.in/api/users/23";

        given()
                .log().uri()
                .when()
                .get(uri)
                .then()
                .log().status()
                .statusCode(STATUS_CODE_404);
    }

    @Test
    @DisplayName("Успешный логин")
    void loginSuccessfulTest() {
        final String uri = "https://reqres.in/api/login";
        final String token = "QpwL5tke4Pnpja7X4";

        JSONObject requestCredits = new JSONObject()
                .put("email", "eve.holt@reqres.in")
                .put("password", "cityslicka");

        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(requestCredits.toString())
                .when()
                .post(uri)
                .then()
                .log().status()
                .log().body()
                .statusCode(STATUS_CODE_200)
                .body("token", is(token));
    }

    @Test
    @DisplayName("Неуспешный логин")
    void loginUnsuccessfulTest() {
        final String uri = "https://reqres.in/api/register";
        final String error = "Missing password";

        JSONObject requestCredits = new JSONObject()
                .put("email", "peter@klaven");

        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(requestCredits.toString())
                .when()
                .post(uri)
                .then()
                .log().status()
                .log().body()
                .statusCode(STATUS_CODE_400)
                .body("error", is(error));
    }
}
