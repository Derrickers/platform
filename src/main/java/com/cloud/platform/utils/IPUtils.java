package com.cloud.platform.utils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class IPUtils {
    public static String getIp(HttpServletRequest request){
        String ipAddress = null;
        try{
            ipAddress = request.getHeader("x-forwarded-for");
            if(ipAddress==null||ipAddress.length()==0||"unknown".equalsIgnoreCase(ipAddress)){
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if(ipAddress==null||ipAddress.length()==0||"unknown".equalsIgnoreCase(ipAddress)){
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if(ipAddress==null||ipAddress.length()==0||"unknown".equalsIgnoreCase(ipAddress)){
                ipAddress = request.getRemoteAddr();
//                if(ipAddress.equals("127.0.0.1")){
//                    //根据网卡获取本机ip
//                    InetAddress inet = null;
//                    try {
//                        inet = InetAddress.getLocalHost();
//                    } catch (UnknownHostException e) {
//                        e.printStackTrace();
//                    }
//                    ipAddress = inet.getHostAddress();
//                }
            }

            //对于通过多个代理的情况，第一个IP为客户端真实IP，IP之间通过','分割
            if(ipAddress!=null&&ipAddress.length()>15){
                if(ipAddress.indexOf(",")>0){
                    ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
                }
            }
        }catch (Exception e){
            ipAddress = "";
        }
        return ipAddress;
    }
}