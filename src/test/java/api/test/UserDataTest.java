package api.test;

import api.Specifications;
import api.UserData;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.json.config.JsonPathConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;


public class UserDataTest {

    private final static String URL = "https://reqres.in/";

    @Test
    public void checkNameAvatar() {
Specifications.installSpecification(Specifications.requestSpecification(URL),Specifications.responseSpecification200());
            List<UserData> users =given()
                .when()

                .get("api/users?page=2")
                .then()
                .log().all()
                .extract().body().jsonPath().getList("data",UserData.class);
            users.stream().forEach(x-> Assertions.assertTrue(x.getAvatar().contains(x.getId().toString())));

            Assertions.assertTrue(users.stream().allMatch(x->x.getEmail().endsWith("@reqres.in")));
       // System.out.println(users.toString());


    }


}