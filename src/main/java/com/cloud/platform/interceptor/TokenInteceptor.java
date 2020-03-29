package com.cloud.platform.interceptor;

import com.auth0.jwt.interfaces.Claim;
import com.cloud.platform.utils.JwtUtil;
import com.cloud.platform.utils.UrlUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Service
public class TokenInteceptor implements HandlerInterceptor {

    @Override
    @CrossOrigin(value = UrlUtils.CROS,maxAge = 1800,allowedHeaders = "*")
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getMethod().equals("OPTIONS"))
            return true;
        String token = request.getHeader("Authorization");
        if(token == null)
            return false;
        Map<String, Claim> claimMap = JwtUtil.verify(token);
        request.setAttribute("userRid",claimMap.get("userRid").asInt());
        request.setAttribute("username",claimMap.get("username").asString());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
