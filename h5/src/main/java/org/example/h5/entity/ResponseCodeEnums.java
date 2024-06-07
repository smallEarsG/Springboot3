package org.example.h5.entity;

public enum ResponseCodeEnums {
    SUCCESS("200", "返回成功", "返回成功", "Return Success"),

    FAIL("500", "返回失败", "返回失敗", "Return Failed"),

    SERVICE_UNAVAILABLE("503", "服务器未响应", "服務器未響應", "Service Unavailable"),

    ILLEGAL_ARGUMENT("400", "非法参数", "非法參數", "Illegal Argument"),

    UNAUTHORIZED("401", "认证失败", "認證失敗", "Unauthorized"),

    FORBIDDEN("403", "禁止访问", "Forbidden"),

    TOKEN_EXPIRED("410", "访问令牌已过期", "Token Expired"),

    INCORRECTCREDENTIAL("428", "用户名或密码错误", "User name or password is incorrect"),

    USER_NOT_EXIST("5002", "用户不存在",""),
    ONLY_POST("400", "仅允许post请求",""),

    ACCESS_UNAUTHORIZED("5003", "权限不足,请联系管理员"),
    TOKEN_INVALID_OR_EXPIRED("5004", "您的token无效或已过期,请重新获取"),
    TOKEN_ACCESS_FORBIDDEN("5005", "您已被被禁止访问"),

    FAIL_COMMON("500", "调用失败,请联系管理员", "调用失败,请联系管理员", "Return Failed"),


    ;


    private final String code;

    private final String message;

    private String enMessage = "";

    private String tcMessage = "";

    ResponseCodeEnums(String code, String message) {
        this.code = code;
        this.message = message;
    }

    ResponseCodeEnums(String code, String message, String enMessage) {
        this.code = code;
        this.message = message;
        this.enMessage = enMessage;
    }

    ResponseCodeEnums(String code, String message, String tcMessage, String enMessage) {
        this.code = code;
        this.message = message;
        this.tcMessage = tcMessage;
        this.enMessage = enMessage;
    }

    public static String getEnumMessage(ResponseCodeEnums responseCode) {
        for (ResponseCodeEnums farmErrorCode : values()) {
            if (farmErrorCode.equals(responseCode)) {
                return farmErrorCode.message;
            }
        }
        return null;
    }


    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getTcMessage() {
        return tcMessage;
    }

    public String getEnMessage() {
        return enMessage;
    }


}