package org.wzhframework.workstation.exception.domain;

import org.wzhframework.workstation.common.MsgEntity;
import org.wzhframework.workstation.common.msg.MsgEnum;

/**
 * 业务异常
 *
 * @author wzh
 * @since 2023/2/22
 */
public class ServiceException extends RuntimeException {
    private String code;

    private String msg;

    public ServiceException() {
        super();
    }

    public ServiceException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static void isTrue(boolean condition, String msg) {
        if (condition) {
            return;
        }
        throwServiceException(MsgEnum.SYSTEM_ERROR.code, msg);
    }

    public static void throwServiceException() {
        throw new ServiceException();
    }

    public static void throwServiceException(String code, String msg) {
        throw new ServiceException(code, msg);
    }

    public static void throwServiceException(MsgEntity msg) {
        throw new ServiceException(msg.getCode(), msg.getMsg());
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
