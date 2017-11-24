package com.mao.aspect;

import com.mao.common.constant.CookieConstant;
import com.mao.common.constant.RedisConstant;
import com.mao.common.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by myseital  on 2017/11/24.
 */
@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Pointcut("execution(public * com.mao.controller.Seller*.*(..))" +
    "&& !execution(public * com.mao.controller.SellerUserController.*(..))")
    public void verify() {

    }

    @Pointcut("execution(public * com.mao.controller.Seller*.*(..))")
    public void verify1() {

    }

    /*@Before("verify()")
    public void doVerify() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 查询cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie == null) {
            log.warn("[登录校验] cookie中查询不到token");
            //抛出异常 aop处理登录校验异常
            //throw new SellerAuthorizeException();
        }

        //去redis中查询
        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
        if (StringUtils.isEmpty(tokenValue)) {
            log.warn("[登录校验] redis中查询不到token");
            //抛出异常 aop处理登录校验异常
            //throw new SellerAuthorizeException();
        }
    }*/

    @Before("verify1()")
    public void doVerify1() {

    }
}
