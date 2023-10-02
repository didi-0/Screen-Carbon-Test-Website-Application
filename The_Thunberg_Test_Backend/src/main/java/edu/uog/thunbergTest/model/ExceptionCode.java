package edu.uog.thunbergTest.model;

import lombok.Getter;

/**
 * @Description:
 * @Author: Xin Zhou(2756254z)
 * @Date: 2023/08/18/10:10
 */
@Getter
public enum ExceptionCode {
    ERROR(2000, "ERROR");

    private int code;
    private String msg;

    ExceptionCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
