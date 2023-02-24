package org.wzhframework.workstation.common.msg;

import org.wzhframework.workstation.common.MsgEntity;

/**
 * @author wzh
 * @since 2023/2/22
 */
public enum MsgEnum implements MsgEntity {
    /**
     * 成功
     */
    SUCCESS("00000", "成功"),
    /**
     * 系统正忙，请稍后再试
     */
    SYSTEM_ERROR("99999", "系统正忙，请稍后再试");

    public final String code;

    public final String msg;

    MsgEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
