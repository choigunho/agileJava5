package common;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.sql.Timestamp;
import java.util.Base64;

public class util {

    public static Timestamp timestamp_tmp = new Timestamp(System.currentTimeMillis());
    public static String timestamp = String.valueOf(timestamp_tmp.getTime());

    public static String accept = DefaultSetting.getAccept();
    public static String projectId = DefaultSetting.getProjectId();
    public static String baseUrl = DefaultSetting.getBaseUrl();
    public static String assessKey = DefaultSetting.getAssessKey();
    public static String secretKey = DefaultSetting.getSecretKey();
    public static String clientType = DefaultSetting.getClientType();
    public static String lang =  DefaultSetting.getLang();

    private static RequestSpecification requestSpec;

    public static String getSignature(String url, String method) throws Exception {

        String body = method + baseUrl + url + timestamp + assessKey + projectId + clientType;
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(secretKeySpec);
        byte[] hash = mac.doFinal(body.getBytes());
        String hashInBase64 = Base64.getEncoder().encodeToString(hash);

        return hashInBase64;
    }

    public static RequestSpecification getRequestSpec() {

        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.addHeader("Accept", accept);
        builder.addHeader("X-Cmp-ProjectId", projectId);
        builder.addHeader("X-Cmp-Timestamp", timestamp);
        builder.addHeader("X-Cmp-AccessKey", assessKey);
        builder.addHeader("X-Cmp-ClientType", clientType);
        builder.addHeader("X-Cmp-Language", lang);
        requestSpec = builder.build();

        return requestSpec;
    }
}
