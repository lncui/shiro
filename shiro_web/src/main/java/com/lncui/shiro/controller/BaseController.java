package com.lncui.shiro.controller;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public abstract class BaseController {

    protected final transient Logger log = LoggerFactory.getLogger(super.getClass());

    protected Object buildErrJson(String msg) {
        return buildJson(-1, msg, null);
    }

    protected Object buildSuccJson() {
        return buildSuccJson(null);
    }

    protected Object buildSuccJson(Object data) {
        return buildJson(0, "成功", data);
    }

    protected Object buildJson(int code, String msg) {
        return buildJson(code, msg, null);
    }

    protected Object buildJson(int code, String msg, Object data) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", code);
        result.put("msg", msg);

        if (data == null) {
            result.put("data", new HashMap<String, Object>());
        } else {
            result.put("data", data);
        }
        return result;
    }


    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, HttpServletRequest req, HttpServletResponse res) {
        log.error("Exception:",ex);
        if(ex instanceof UnauthorizedException) {
            return "unauthorized";
        }else if(ex instanceof UnauthenticatedException){
            return "unauthorized";
        } else if(ex instanceof AuthorizationException){
            return "unauthorized";
        }
        return "500";
    }


    public boolean isWx(HttpServletRequest request) {
        String ua = request.getHeader("user-agent").toLowerCase();
        boolean validation = false;
        if (ua.indexOf("micromessenger") > 0) {// 是微信浏览器
            validation = true;
        }
        return validation;
    }
}