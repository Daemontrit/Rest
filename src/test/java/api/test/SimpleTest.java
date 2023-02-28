package api.test;

import api.ListResourse;
import api.Register;
import api.Specifications;
import api.SucsessReg;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class SimpleTest {
    public static String URL= "https://reqres.in/";
    @Test
    void reqTest() {
        Response response = RestAssured.get("https://reqres.in/api/users?page=2");
        System.out.print(response.asString());
    }

    @Test
    public void postResponse() {
        Specifications.installSpecification(Specifications.requestSpecification(URL),Specifications.responseSpecification200());
        Integer id=4;
        String token = "QpwL5tke4Pnpja7X4";
        Register user=new Register("eve.holt@reqres.in","pistol");
        SucsessReg registersUsers = given()
                .body(user)
                .when()
                .post("api/register")
                .then()
                .log().all()
                .extract().as(SucsessReg.class);
        Assertions.assertNotNull(registersUsers.getId());
        Assertions.assertNotNull(registersUsers.getToken());

        Assertions.assertEquals(id,registersUsers.getId());
        Assertions.assertEquals(token,registersUsers.getToken());
    }
    @Test
    public void sortYear(){
        List<ListResourse> resourses=given()
                .when()
                .contentType(ContentType.JSON)
                .get(URL+"api/unknown")
                .then().log().all()
                .extract().body().jsonPath().getList("data", ListResourse.class);

        List<Integer> actual= resourses.stream().map(ListResourse::getYear).collect(Collectors.toList());
        List<Integer> exeption=actual.stream().sorted().collect(Collectors.toList());
        Assertions.assertEquals(exeption,actual);
    }
}
