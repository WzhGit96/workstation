package org.wzhframework.workstation.aspectj;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.builder.SqlSourceBuilder;
import org.apache.ibatis.reflection.ArrayUtil;
import org.apache.ibatis.session.SqlSessionFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.weaver.patterns.Declare;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.wzhframework.workstation.common.DataBase;
import org.wzhframework.workstation.common.GenericObject;
import org.wzhframework.workstation.common.Request;
import org.wzhframework.workstation.common.Response;
import org.wzhframework.workstation.common.dto.GenericDTO;
import org.wzhframework.workstation.common.dto.GenericDataBaseDTO;
import org.wzhframework.workstation.common.dto.GenericRspDTO;
import org.wzhframework.workstation.context.RequestGenericContextHolder;
import org.wzhframework.workstation.utils.IdGen;
import org.wzhframework.workstation.utils.IpUtil;
import org.wzhframework.workstation.utils.SqlUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Array;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Stream;

/**
 * @author wzh
 * @since 2023/2/23
 */
@Aspect
@Component
public class LogAspect {
    private static final Logger REQUEST_LOG = LoggerFactory.getLogger(Request.class);
    private static final Logger RESPONSE_LOG = LoggerFactory.getLogger(Response.class);

    private static final Logger DATABASE_LOG = LoggerFactory.getLogger(DataBase.class);

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Around("bean(*Controller)")
    public Object requestLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();
        GenericDTO genericDTO = (GenericDTO) Stream.of(args).filter(item -> (item instanceof GenericDTO)).findFirst().orElse(null);
        LocalDateTime startTime = LocalDateTime.now();
        String ip = IpUtil.getIpAddr(getRequest());
        String requestUrl = StringUtils.substring(getRequest().getRequestURI(), 0, 255);
        String method = proceedingJoinPoint.getSignature().getName();
        String requestId = IdGen.getRequestId();
        if (genericDTO != null) {
            genericDTO.setRequestId(requestId);
            genericDTO.set_startTime(startTime);
            genericDTO.setIp(ip);
            genericDTO.setRequestUrl(requestUrl);
            genericDTO.setDuration(0);
            genericDTO.setMethod(method);
            genericDTO.setRequestType("request");
            REQUEST_LOG.info(toJson(genericDTO));
        }
        RequestGenericContextHolder.setContext(genericDTO);
        Object obj = proceedingJoinPoint.proceed();
        LocalDateTime endTime = LocalDateTime.now();
        if (obj instanceof GenericRspDTO) {
            GenericRspDTO<?> rspDTO = (GenericRspDTO<?>) obj;
            rspDTO.setRequestId(requestId);
            rspDTO.set_startTime(startTime);
            rspDTO.set_endTime(endTime);
            rspDTO.setDuration((endTime.getNano() - startTime.getNano()) / 1000);
            rspDTO.setIp(ip);
            rspDTO.setRequestUrl(requestUrl);
            rspDTO.setMethod(method);
            rspDTO.setRequestType("response");
            RESPONSE_LOG.info(toJson(rspDTO));
        }
        return obj;
    }

    //@Around("execution(public * com.wzh.workstation.dao..*(..))")
    public Object databaseLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        GenericDTO genericDTO = (GenericDTO) RequestGenericContextHolder.getContext();
        GenericDataBaseDTO dataBaseDTO = new GenericDataBaseDTO();
        BeanUtils.copyProperties(genericDTO, dataBaseDTO);
        dataBaseDTO.setSql(SqlUtil.getMybatisSql(proceedingJoinPoint, sqlSessionFactory));
        Object[] args = proceedingJoinPoint.getArgs();
        dataBaseDTO.setParameter(getParamters(args));
        Object obj = proceedingJoinPoint.proceed();
        LocalDateTime endTime = LocalDateTime.now();
        dataBaseDTO.setDuration((endTime.getNano() - dataBaseDTO.get_startTime().getNano()) / 1000);
        if (obj instanceof Collection) {
            dataBaseDTO.setTotal(((Collection) obj).size());
        } else if (obj instanceof Boolean) {
            dataBaseDTO.setTotal((Boolean) obj ? 1 : 0);
        } else if (obj instanceof Integer) {
            dataBaseDTO.setTotal((Integer) obj);
        } else if (obj == null) {
            dataBaseDTO.setTotal(0);
        } else {
            dataBaseDTO.setTotal(1);
        }
        DATABASE_LOG.info(toJson(dataBaseDTO));
        return obj;
    }

    private String getParamters(Object[] args) {
        List<Object> typeList = new ArrayList<>();
        for (Object value : args) {
            if (value == null) {
                typeList.add("null");
            } else {
                typeList.add(objectValueString(value) + "(" + value.getClass().getSimpleName() + ")");
            }
        }
        final String parameters = typeList.toString();
        return parameters.substring(1, parameters.length() - 1);
    }

    private String objectValueString(Object value) {
        if (value instanceof Array) {
            try {
                return ArrayUtil.toString(((Array) value).getArray());
            } catch (SQLException e) {
                return value.toString();
            }
        }
        return value.toString();
    }

    private HttpServletRequest getRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attributes.getRequest();
    }

    private String toJson(GenericObject obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        objectMapper.registerModule(javaTimeModule);
        return objectMapper.writeValueAsString(obj);
    }
}
