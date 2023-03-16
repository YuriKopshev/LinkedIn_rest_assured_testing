import org.example.Product;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.samePropertyValuesAs;

public class ProductResponseVerifyTests {
    @Test
    public void checkStatusCodeTest() {
        String endpoint = "http://localhost:80/api_testing/product/read_one.php";
        var response = given().
                queryParam("id", 18).
                when().
                get(endpoint).
                then().
                log().headers().assertThat().statusCode(200);
        response.log().body();
    }

    @Test
    public void checkHeaderContentTypeTest() {
        String endpoint = "http://localhost:80/api_testing/product/read_one.php";
        given().
                queryParam("id", 18).
                when().
                get(endpoint).
                then().
                log().headers().assertThat().statusCode(200).
                header("Content-Type", equalTo("application/json"));

    }

    @Test
    public void checkResponseBodyTest() {
        String endpoint = "http://localhost:80/api_testing/product/read_one.php";
        Product expectedProduct = new Product(18,
                "Multi-Vitamin (90 capsules)",
                "A daily dose of our Multi-Vitamins fulfills a days nutritional needs for over 12 vitamins and minerals.",
                10.00,
                4,
                "Supplements");
        Product actualProduct = given().
                when().
                queryParam("id", "18").
                get(endpoint).as(Product.class);
        assertThat(actualProduct, samePropertyValuesAs(expectedProduct));
    }

    @Test
    public void getMultiVitamins() {
        String endpoint = "http://localhost:80/api_testing/product/read_one.php";
        given().
                queryParam("id", 18).
                when().
                get(endpoint).
                then().
                assertThat().
                statusCode(200).
                header("Content-Type", equalTo("application/json")).
                body("id", equalTo("18")).
                body("name", equalTo("Multi-Vitamin (90 capsules)")).
                body("description", equalTo("A daily dose of our Multi-Vitamins fulfills a days nutritional needs " +
                        "for over 12 vitamins and minerals.")).
                body("price", equalTo("10.00")).
                body("category_id", equalTo("4")).
                body("category_name", equalTo("Supplements"));
    }
}


