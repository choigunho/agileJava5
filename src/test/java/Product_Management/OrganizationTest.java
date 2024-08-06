package Product_Management;

import common.DefaultSetting;
import common.util;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.hamcrest.MatcherAssert.assertThat;

public class OrganizationTest {

    static String organizationRequestId;
    static String organizationId;

    String accountId = DefaultSetting.getAccountId();
    String orgName = "Test Org";

    @BeforeEach
    public void beforeEach() {
        RestAssured.baseURI = DefaultSetting.getBaseUrl();
    }

    @Test
    @Order(1)
    @DisplayName("Org 생성을 신청한다.")
    public void test1() throws Exception {

        String url =  "/organization/v1/accounts/requests";
        String signature = util.getSignature(url, "POST");

        // body 구성
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("requestOrganizationName", orgName);

        ExtractableResponse<Response> response =
                given().
                        spec(util.getRequestSpec()).
                        header("X-Cmp-Signature", signature).
                        body(jsonObject.toString()).contentType(ContentType.JSON).
                when().
                        post(RestAssured.baseURI + url).
                then().
                        statusCode(200).
                        log().all().extract();

    }

    @Test
    @Order(2)
    @DisplayName("Org 생성 신청건이 조직 신청 목록에서 조회되는 것을 확인한다.")
    public void test2() throws Exception {

        String url =  "/organization/v1/accounts/" + accountId + "/requests";
        String signature = util.getSignature(url, "GET");

        ExtractableResponse<Response> response =
                given().
                when().
                        spec(util.getRequestSpec()).
                        header("X-Cmp-Signature", signature).
                        get(RestAssured.baseURI + url).
                then().
                        statusCode(200).
                        assertThat().body("contents[0].requestOrganizationName", equalTo(orgName)). // 생성 요청시 사용한 org 명 확인
                        assertThat().body("contents[0].requestState", equalTo("REQUESTING")). // 상태 확인
                        log().all().extract();

        JsonPath result = response.jsonPath();
        organizationRequestId = result.getString("contents.organizationRequestId[0]");

    }

    @Test
    @Order(3)
    @DisplayName("Org 생성을 승인한다.")
    public void test3() throws Exception {

//        test2();

        String url =  "/organization/v1/accounts/" + accountId + "/requests/" + organizationRequestId + "/approve";
        String signature = util.getSignature(url, "POST");

        ExtractableResponse<Response> response =
                given().
                when().
                        spec(util.getRequestSpec()).
                        header("X-Cmp-Signature", signature).
                        post(RestAssured.baseURI + url).
                then().
                        statusCode(200).
                        log().all().extract();

    }

    @Test
    @Order(4)
    @DisplayName("Org 생성 정보를 조회한다.")
    public void test4() throws Exception {

        String url =  "/organization/v1/organizations";
        String signature = util.getSignature(url, "GET");

        ExtractableResponse<Response> response =
                given().
                when().
                        spec(util.getRequestSpec()).
                        header("X-Cmp-Signature", signature).
                        get(RestAssured.baseURI + url).
                then().
                        statusCode(200).
                        log().all().extract();

        JsonPath result = response.jsonPath();
        organizationId = result.get("organizationId");

    }

    @Test
    @Order(5)
    @DisplayName("Org 삭제한다.")
    public void test5() throws Exception {

//        test4();

        String url =  "/organization/v1/organizations/" + organizationId;
        String signature = util.getSignature(url, "DELETE");

        ExtractableResponse<Response> response =
                given().
                when().
                        spec(util.getRequestSpec()).
                        header("X-Cmp-Signature", signature).
                        delete(RestAssured.baseURI + url).
                then().
                        statusCode(200).
                        log().all().extract();

    }
}
