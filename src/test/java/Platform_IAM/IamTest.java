package Platform_IAM;

import common.DefaultSetting;
import common.util;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class IamTest {


    @BeforeEach
    public void beforeEach() throws Exception {
        RestAssured.baseURI = DefaultSetting.getBaseUrl();
    }

    @Test
    @DisplayName("정책 목록을 조회한다.")
    public void test1() throws Exception {

        String url =  "/iam/v2/policys";
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

    }

    @Test
    @DisplayName("그룹을 생성한다.")
    public void test2() throws Exception {

        String url =  "/iam/v2/groups";
        String signature = util.getSignature(url, "POST");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("groupName", "TestGroup");
        jsonObject.put("description", "이 그룹은 테스트 자동화로 만들어진 그룹입니다.");

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

}
