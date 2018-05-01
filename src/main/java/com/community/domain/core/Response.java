package com.community.domain.core;

import com.community.common.enums.ApiHttpStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Response<D> implements Serializable {
    private int status = ApiHttpStatus.FAIL.getCode();
    private String message = ApiHttpStatus.FAIL.getDesc();
    private D data;
    private boolean success;

    public Response(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public Response(int status, String message, D data, boolean success) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.success = success;
    }

    /**
     * 成功
     */
    public static <D> Response<D> success() {
        return new Response(ApiHttpStatus.OK.getCode(), ApiHttpStatus.OK.getDesc(), null, true);
    }

    /**
     * 成功
     *
     * @param data 响应信息
     */
//    public static Response success(final String message) {
//        return new Response(ApiHttpStatus.OK.getCode(), message, null, true);
//    }
    public static <D> Response<D> success(final D data) {
        return new Response(ApiHttpStatus.OK.getCode(), ApiHttpStatus.OK.getDesc(), data, true);
    }


    /**
     * 失败
     *
     * @param message 响应信息
     */
    public static <D> Response<D> fail(final String message) {
        return new Response(ApiHttpStatus.FAIL.getCode(), message, null, false);
    }

    public static <D> Response<D> fail(final int code, final String message) {
        return new Response(code, message, null, false);
    }

    /**
     * 异常
     *
     * @return
     */
    public static <D> Response<D> exception() {
        return new Response(ApiHttpStatus.SYSTEM_EXCEPTION.getCode(), ApiHttpStatus.SYSTEM_EXCEPTION.getDesc(), null, false);
    }

    /**
     * 异常
     *
     * @param message 响应信息
     * @return
     */
    public static <D> Response<D> exception(final String message) {
        return new Response(ApiHttpStatus.SYSTEM_EXCEPTION.getCode(), message, null, false);
    }


}
