package Product_Management;

import common.DefaultSetting;
import common.util;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class IdCenterTest {

    @BeforeEach
    public void beforeEach() throws Exception {
        RestAssured.baseURI = DefaultSetting.getBaseUrl();
    }

    @Test
    @DisplayName("id center 인스턴스 목록 조회")
    public void test1() throws Exception {

        String url =  "/identity-center/v1/instances";
        String signature = util.getSignature(url, "GET");

        given().
        when().
            spec(util.getRequestSpec()).
            header("X-Cmp-Signature", signature).
            get(RestAssured.baseURI + url).
        then().
            statusCode(200).
            assertThat().
            body("totalCount", equalTo(1)).log().all().extract();

    }

    @Test
    @DisplayName("사용자 목록 조회")
    public void test2() throws Exception {

        String url =  "/identity-center/v1/users";
        String signature = util.getSignature(url, "GET");


    }

}
