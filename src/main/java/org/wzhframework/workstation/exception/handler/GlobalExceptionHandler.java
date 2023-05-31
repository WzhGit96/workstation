package org.wzhframework.workstation.exception.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.wzhframework.workstation.common.GenericObject;
import org.wzhframework.workstation.common.dto.GenericRspDTO;
import org.wzhframework.workstation.common.msg.MsgEnum;
import org.wzhframework.workstation.context.RequestGenericContextHolder;
import org.wzhframework.workstation.exception.domain.ServiceException;


import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author wzh
 * @since 2023/2/22
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({Exception.class, RuntimeException.class})
    public GenericRspDTO<?> handleException(Exception e, HttpServletRequest request) throws JsonProcessingException {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生系统异常.", requestURI, e);
        GenericRspDTO<?> rspDTO = new GenericRspDTO<>();
        rspDTO.setCode(MsgEnum.SYSTEM_ERROR.code);
        rspDTO.setMsg(MsgEnum.SYSTEM_ERROR.msg);
        log.info(toJson(rspDTO));
        return rspDTO;
    }

    @ExceptionHandler(ServiceException.class)
    public GenericRspDTO<?> handleServiceException(ServiceException e) throws JsonProcessingException {
        GenericRspDTO<?> rspDTO = new GenericRspDTO<>();
        rspDTO.setCode(e.getCode());
        rspDTO.setMsg(e.getMsg());
        log.info(toJson(rspDTO));
        return rspDTO;
    }

    private String toJson(GenericObject obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        mapper.registerModule(javaTimeModule);
        return mapper.writeValueAsString(obj);
    }
}
