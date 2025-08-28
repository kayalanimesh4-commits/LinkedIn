package com.linkedIn.post_service.auth;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class UserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        String userId = request.getHeaders("X-User_Id").toString();

        if(userId==null){
            UserContextLoader.setCurrentUserId(Long.valueOf(userId));
        }
        return HandlerInterceptor.super.preHandle(request,response,handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex) throws Exception {
        UserContextLoader.clear();

    }

}
