package common;

import java.util.ResourceBundle;

public class DefaultSetting {


    public static String getBaseUrl() {
        ResourceBundle rb = ResourceBundle.getBundle("UserSetting");
        return rb.getString("baseUrl");
    }

    public static String getProjectId() {
        ResourceBundle rb = ResourceBundle.getBundle("UserSetting");
        return rb.getString("projectId");
    }

    public static String getSecretKey() {
        ResourceBundle rb = ResourceBundle.getBundle("UserSetting");
        return rb.getString("secretKey");
    }
    public static String getAssessKey() {
        ResourceBundle rb = ResourceBundle.getBundle("UserSetting");
        return rb.getString("assessKey");
    }

    public static String getAccept() {
        ResourceBundle rb = ResourceBundle.getBundle("UserSetting");
        return rb.getString("accept");
    }

    public static String getClientType() {
        ResourceBundle rb = ResourceBundle.getBundle("UserSetting");
        return rb.getString("clientType");
    }

    public static String getLang() {
        ResourceBundle rb = ResourceBundle.getBundle("UserSetting");
        return rb.getString("lang");
    }

    public static String getAccountId() {
        ResourceBundle rb = ResourceBundle.getBundle("UserSetting");
        return rb.getString("accountId");
    }

}
