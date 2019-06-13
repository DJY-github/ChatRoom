package com.test.config;

/**
 * Created by Administrator on 2016/11/22.
 */
public enum ResultStatus {
    SUCCESS(200, "�ɹ�"),
    USERNAME_OR_PASSWORD_ERROR(-1001, "�û������������"),
    USER_NOT_FOUND(-1002, "�û�������"),
    USER_NOT_LOGIN(-1003, "�û�δ��¼");

    /**
     * ������
     */
    private int code;

    /**
     * ���ؽ������
     */
    private String message;

    ResultStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
