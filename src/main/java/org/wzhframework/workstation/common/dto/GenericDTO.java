package org.wzhframework.workstation.common.dto;

import org.wzhframework.workstation.common.GenericObject;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author wzh
 * @since 2023/2/22
 */
public class GenericDTO implements Serializable, GenericObject {
    private static final long serialVersionUID = -1344154128597684825L;
    private String requestId;
    private String ip;

    private String requestUrl;

    private String requestType;

    private String method;

    private LocalDateTime _startTime;

    private LocalDateTime _endTime;

    private Integer duration;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public LocalDateTime get_startTime() {
        return _startTime;
    }

    public void set_startTime(LocalDateTime _startTime) {
        this._startTime = _startTime;
    }

    public LocalDateTime get_endTime() {
        return _endTime;
    }

    public void set_endTime(LocalDateTime _endTime) {
        this._endTime = _endTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "GenericDTO{" +
                "requestId='" + requestId + '\'' +
                ", ip='" + ip + '\'' +
                ", requestUrl='" + requestUrl + '\'' +
                ", requestType='" + requestType + '\'' +
                ", method='" + method + '\'' +
                ", _startTime=" + _startTime +
                ", _endTime=" + _endTime +
                ", duration=" + duration +
                '}';
    }
}
