package org.example.h5.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.h5.entity.ResponseCodeEnums;

import java.io.Serializable;
@AllArgsConstructor
@Data
public class CommonResponseDto<T> implements Serializable {
    private String code;
    private String msg;
    private T data;
    // static 级别的方法可以直接通过类名调用  不需要实列化
    public static <T> CommonResponseDto<T> success(T data){
        return  new CommonResponseDto<T>(ResponseCodeEnums.SUCCESS.getCode(),ResponseCodeEnums.SUCCESS.getMessage(),data);
    }

    public  static  <T> CommonResponseDto success(String msg,T data){
        return  new CommonResponseDto<T>(ResponseCodeEnums.SUCCESS.getCode(),msg,data);
    }
    public static CommonResponseDto success() {
        return success(null);
    }
    /**
     * 失败携带返回对象
     * @param data
     * @param <T>
     * @return
     */
    public static <T> CommonResponseDto fail(T data) {
        return new CommonResponseDto(ResponseCodeEnums.FAIL.getCode(),
                ResponseCodeEnums.FAIL.getMessage(), data);
    }
    /**
     * 快速失败
     * @param <T>
     * @return
     */
    public static <T> CommonResponseDto fail() {
        return fail(null);
    }
    /**
     * 异常，携带自定义消息
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> CommonResponseDto error(String msg) {
        return new CommonResponseDto(ResponseCodeEnums.FAIL.getCode(), msg, null);
    }

    /**
     * 异常， 携带状态码和自定义返回消息
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> CommonResponseDto error(String code, String msg) {
        return new CommonResponseDto<T>(code, msg, null);
    }
    /**
     * 异常 自定义状态码
     * @param responseCode
     * @param <T>
     * @return
     */
    public static <T> CommonResponseDto error(ResponseCodeEnums responseCode) {
        return new CommonResponseDto(responseCode.getCode(), responseCode.getMessage(), null);
    }
    /**
     * 判断是否是处理成功
     * @param result
     * @return
     */
    public static boolean isSuccess(CommonResponseDto<?> result) {
        return result != null && ResponseCodeEnums.SUCCESS.getCode().equals(result.getCode());
    }
}
