package com.eboy.honey.response.demo.interceptor;


import com.eboy.honey.response.constant.HoneyUCExceptionException;
import com.eboy.honey.response.demo.annotation.AuthSkip;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author yangzhijie
 * @date 2020/7/14 16:13
 */
public class AuthenticationInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        // 从 http 请求头中取出 token
        String token = httpServletRequest.getHeader("token");
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        // 检查是否有authSkip注释，有则跳过认证
        if (method.isAnnotationPresent(AuthSkip.class)) {
            AuthSkip authSkip = method.getAnnotation(AuthSkip.class);
            if (authSkip.required()) {
                return true;
            }
        }
        // 执行认证
       HoneyUCExceptionException.TOKEN_NOT_FOUND.assertNotNull(token);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}

