package com.wzh.workstation.service.impl;

import com.wzh.workstation.bo.LoginReqBO;
import com.wzh.workstation.bo.LoginRspBO;
import com.wzh.workstation.bo.RegisterReqBO;
import com.wzh.workstation.dao.WorkUserMapper;
import com.wzh.workstation.entity.WorkUser;
import com.wzh.workstation.service.IWorkUserService;
import com.wzh.workstation.util.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.wzhframework.workstation.exception.domain.ServiceException;
import org.wzhframework.workstation.utils.PasswordUtils;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * @author wzh
 * @since 2023/2/27
 */
@Service
public class WorkUserServiceImpl implements IWorkUserService {
    @Resource
    private WorkUserMapper workUserMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public LoginRspBO login(LoginReqBO reqBO) throws Exception {
        WorkUser entity = new WorkUser();
        entity.setAccount(reqBO.getAccount());
        entity.setPwd(PasswordUtils.encrypt(reqBO.getPassword()));
        WorkUser result = workUserMapper.login(entity);
        LoginRspBO rspBO = new LoginRspBO();
        rspBO.setSuccess(result != null);
        String uuid = UUID.randomUUID().toString();
        stringRedisTemplate.opsForValue().set(uuid, String.valueOf(result.getId()), 30, TimeUnit.MINUTES);
        Cookie cookie = new Cookie("sid", uuid);
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletAttr = (ServletRequestAttributes) attributes;
        HttpServletResponse response = servletAttr.getResponse();
        response.addCookie(cookie);
        return rspBO;
    }

    @Override
    public void register(RegisterReqBO reqBO) throws Exception {
        WorkUser entity = new WorkUser();
        entity.setAccount(reqBO.getAccount());
        entity.setPwd(PasswordUtils.encrypt(reqBO.getPassword()));
        entity.setCreateBy("system");
        entity.setCreateTime(new Date());
        entity.setStatus(1);
        int result = workUserMapper.insert(entity);
        ServiceException.isTrue(result > 0, "注册失败");
    }

    @Override
    public LoginRspBO validateLogin() {
        HttpServletRequest request = ServletUtils.getRequest();
        Cookie[] cookies = request.getCookies();
        LoginRspBO rspBO = new LoginRspBO();
        Stream.of(cookies).forEach(item -> {
            if ("sid".equals(item.getName())) {
                Cookie sidCookie = item;
                String uid = stringRedisTemplate.opsForValue().get(sidCookie.getValue());
                rspBO.setUid(uid);
            }
        });
        return rspBO;
    }
}
