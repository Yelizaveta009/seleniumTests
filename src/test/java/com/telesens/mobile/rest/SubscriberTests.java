package com.telesens.mobile.rest;
import com.telesens.mobile.model.Gender;
import com.telesens.mobile.model.Subscriber;
import io.restassured.RestAssured;
import io.restassured.response.ResponseBody;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class SubscriberTests {

    @BeforeClass
    public void setUp(@Optional("chrome") String browser) throws Exception {
        RestAssured.baseURI = "http://localhost/rest/json";
        RestAssured.port = 8081;
    }

    @Test
    public void testGet() {
        RestAssured.port = 8081;

        ResponseBody body = given()
                .get("/subscribers")
                .body();

        String jsonSubscribers = body.asString();
        System.out.println(jsonSubscribers);
    }

    @Test
    public void testAdd() {

        JSONObject json = new JSONObject();
        json.put("id", 666);
        json.put("firstName", "Maria");
        json.put("lastName", "Ivanova");
        json.put("age", 24);
        json.put("gender", "f");

        given()
                .header("Content-Type", "application/json")
                .body(json.toJSONString())
                .post("/subscribers");

    }

    @Test(dataProvider="subscriberProvider")
    public void testUpdate(Subscriber subscriber) {
        if (!isPresent(subscriber))
            add(subscriber);

        JSONObject json = new JSONObject();
        json.put("id", subscriber.getId()); // Cast
        json.put("firstName", "Kristina"); // Cast
        json.put("lastName", "Pupkina");
        json.put("age", "25");
        json.put("gender", "f");

    }

    @Test(dataProvider="subscriberProvider")
    public void testDelete(Subscriber subscriber) {

        if (!isPresent(subscriber))
            add(subscriber);

        Set<Subscriber> before = new HashSet<>(
                getAll()
        );

        given().log().all()
                .delete("/subscribers/{id}", subscriber.getId())
                .then().assertThat()
                .statusCode(200);

        Set<Subscriber> after = new HashSet<>(
                getAll()
        );
    }

    private boolean isPresent(Subscriber subscriber) {
        try {
            return
                    given().log().all()
                            .header("Content-Type", "application/json")
                            .when()
                            .get("/subscribers/{id}", subscriber.getId())
                            .then()
                            .assertThat()
                            .statusCode(200)
                            .and()
                            .extract()
                            .body()
                            .jsonPath()
                            .getObject(".", Subscriber.class)
                            .equals(subscriber);
        } catch (AssertionError err) {
            return false;
        }
    }

    private void add(Subscriber subscriber) {
        JSONObject json = new JSONObject();
        json.put("id", subscriber.getId());
        json.put("firstName", subscriber.getFirstName()); // Cast
        json.put("lastName", subscriber.getLastName());
        json.put("age", subscriber.getAge());
        json.put("gender", subscriber.getGender().toString());

        given().log().all()
                .header("Content-Type", "application/json")
                .body(json.toJSONString())
                .post("/subscribers");
    }

    private void remove(Subscriber subscriber) {
        given().log().all()
                .delete("/subscribers/{id}", subscriber.getId());
    }

    private List<Subscriber> getAll() {
        return
                given().log().all()
                        .contentType("application/json; charset=UTF-8")
                        .when()
                        .get("/subscribers")
                        .then()
                        .extract()
                        .body()
                        .jsonPath()
                        .getList(".", Subscriber.class);
    }

    @DataProvider
    private Object[] subscriberProvider() {
        return new Object[] {
                Subscriber.newSubscriber()
                        .id(28L)
                        .firstName("Yelizaveta")
                        .lastName("Zhurba")
                        .age(24)
                        .gender( Gender.FEMALE)
                        .build()
        };
    }

    @DataProvider
    Object[] subscriberFirstProvider() {
        return new Object[] {
                Subscriber.newSubscriber()
                        .id(1L)
                        .firstName("Peter")
                        .lastName("Pechking")
                        .age(24)
                        .gender(Gender.MALE)
                        .build()
        };
    }
}
