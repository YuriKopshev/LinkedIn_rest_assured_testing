import org.example.Product;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class LessonNewProductTests {

    @Test
    public void createSweatBand() {
        String endpoint = "http://localhost:80/api_testing/product/create.php";

        Product product = new Product(
                "Sweatband",
                "Good sweetband",
                5.0,
                3
        );
        var response = given().
                body(product).
                when().
                post(endpoint).
                then();
        response.log().status();
        response.log().body();
    }

    @Test
    public void updateSweatBand() {
        String endpoint = "http://localhost:80/api_testing/product/update.php";
        String body = """
                 {
                "id": 22,
                "price": 6.0,
                }
                """;
        var response = given().
                body(body).
                when().
                put(endpoint).
                then();
        response.log().status();
        response.log().body();
    }

    @Test
    public void getSweetBand() {
        String endpoint = "http://localhost:80/api_testing/product/read_one.php";
        var response = given().
                queryParam("id", 22).
                when().
                get(endpoint).
                then();
        response.log().status();
        response.log().body();
    }

    @Test
    public void deleteSweetBand() {
        String endpoint = "http://localhost:80/api_testing/product/delete.php";
        String body = """
                {
                "id": 22
                }
                """;
        var response = given().
                body(body).
                when().
                delete(endpoint).
                then();
        response.log().status();
        response.log().body();
    }
}
