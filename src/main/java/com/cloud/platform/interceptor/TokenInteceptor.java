package com.cloud.platform.interceptor;

import com.auth0.jwt.interfaces.Claim;
import com.cloud.platform.Enum.ResponseType;
import com.cloud.platform.utils.IPUtils;
import com.cloud.platform.utils.JwtUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenInteceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getMethod().equals("OPTIONS"))
            return true;
        String token = request.getHeader("Authorization");
        if(token == null)
            return false;
        Map<String, Claim> claimMap = new HashMap<>();
        try{
            claimMap = JwtUtil.verify(token);
        }catch (Exception e){
            response.sendError(ResponseType.FAIL.getValue(),"验证失败");
            return false;
        }
        request.setAttribute("userId",claimMap.get("userId").asInt());
        request.setAttribute("username",claimMap.get("username").asString());
        request.setAttribute("account",claimMap.get("account").asString());
        request.setAttribute("ip", IPUtils.getIp(request));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
