package org.wzhframework.workstation.common.dto;


import org.wzhframework.workstation.common.MsgEntity;
import org.wzhframework.workstation.common.msg.MsgEnum;

/**
 * @author wzh
 * @since 2023/2/22
 */
public class GenericRspDTO<T> extends GenericDTO {
    private static final long serialVersionUID = -8898421248778674368L;

    public GenericRspDTO() {
        super();
    }


    public GenericRspDTO(String code, String msg) {
        this(code, msg, null);
    }

    public GenericRspDTO(MsgEntity entity) {
        this(entity.getCode(), entity.getMsg(), null);
    }

    public GenericRspDTO(MsgEntity entity, T data) {
        this(entity.getCode(), entity.getMsg(), data);
    }

    public GenericRspDTO(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private String code;

    private String msg;

    private T data;

    public static<T> GenericRspDTO<T> newSuccessInstance() {
        return new GenericRspDTO<>(MsgEnum.SUCCESS);
    }

    public static<T> GenericRspDTO<T> newSuccessInstance(T data) {
        return new GenericRspDTO<>(MsgEnum.SUCCESS, data);
    }

    public static<T> GenericRspDTO<T> newInstance(String code, String msg) {
        return new GenericRspDTO<>(code, msg);
    }

    public static<T> GenericRspDTO<T> newInstance(String code, String msg, T data) {
        return new GenericRspDTO<>(code, msg, data);
    }

    public static<T> GenericRspDTO<T> newInstance(MsgEntity entity, T data) {
        return new GenericRspDTO<>(entity, data);
    }

    public static<T> GenericRspDTO<T> newInstance(MsgEntity entity) {
        return new GenericRspDTO<>(entity);
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GenericRspDTO{" + "code='" + code + '\'' + ", msg='" + msg + '\'' + ", data=" + data + '}';
    }
}
