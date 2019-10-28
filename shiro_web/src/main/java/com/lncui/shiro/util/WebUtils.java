package com.lncui.shiro.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class WebUtils {

    private static final transient Logger log = LoggerFactory.getLogger(WebUtils.class);

    /*
    * 获取用户的真是Ip地址
    * */
    public static String getRemoteHost(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        log.info("x-forwarded-for ip: " + ip);
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if (ip.indexOf(",") != -1) {
                ip = ip.split(",")[0];
            }
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
            log.info("Proxy-Client-IP ip: " + ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
            log.info("WL-Proxy-Client-IP ip: " + ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
            log.info("HTTP_CLIENT_IP ip: " + ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            log.info("HTTP_X_FORWARDED_FOR ip: " + ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
            log.info("X-Real-IP ip: " + ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            log.info("getRemoteAddr ip: " + ip);
        }
        log.info("获取客户端ip: " + ip);
        return ip;
    }


    /*
    * 获取请求路径上下文
    * */
    public static String getBasePath(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        String port = request.getServerPort() == 80 ? "" : ":" + request.getServerPort();
        return request.getScheme() + "://" + request.getServerName() + port + contextPath;
    }

    /*
    * 判断是否是ajax请求
    * */
    public static boolean isAjax(HttpServletRequest request) {
        return "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"));
    }

    /*
    * 判断是否为Get请求
    * */
    public static boolean isGet(HttpServletRequest request) {
        return "get".equalsIgnoreCase(request.getMethod());
    }

    /*
   * 判断是否为Post请求
   * */
    public static boolean isPost(HttpServletRequest request) {
        return "post".equalsIgnoreCase(request.getMethod());
    }

    /*
    * 获取ip地址
    * 代理转发前IP识别由Apache/Nginx实现，取到的即为真实ip，无需考虑代理中转问题
    * */
    public static String getIp(HttpServletRequest req) {
        // 代理转发前IP识别由Apache/Nginx实现，取到的即为真实ip，无需考虑代理中转问题
        return req.getRemoteAddr();
    }

    /*
    * 获取url地址
    * */
    public static String getUrl(HttpServletRequest req) {
        StringBuffer url = req.getRequestURL();

        String queryString = req.getQueryString();
        if (StringUtils.isNotEmpty(queryString)) {
            url.append('?').append(queryString);
        }
        return url.toString();
    }

    /*
    * 编码Url地址
    * */
    public static String encodeUrl(String url) {
        if (StringUtils.isEmpty(url)) {
            return "";
        }
        try {
            return URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("Exception:",e);
            e.printStackTrace();
            return "";
        }
    }

    /*
    * 解码Url地址
    * */
    public static String decodeUrl(String url) {
        if (StringUtils.isEmpty(url)) {
            return "";
        }
        try {
            return URLDecoder.decode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("Exception:", e);
            return "";
        }
    }
}
