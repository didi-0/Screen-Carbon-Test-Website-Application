package edu.uog.thunbergTest.ResultVo;

/**
 * @Description: ResultCode
 * @Author: Xin Zhou(2756254z)
 * @Date: 2023/08/18/09:41
 */
public enum ResultCode implements StatusCode {

    SUCCESS(1000, "success"),

    FAILED(1001, "fail"),

    VALIDATE_ERROR(1002, "Parameter validation failed"),

    RESPONSE_PACK_ERROR(1003, "Response pack error");


    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
