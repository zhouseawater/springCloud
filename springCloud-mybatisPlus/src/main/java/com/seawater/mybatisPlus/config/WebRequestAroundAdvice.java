package com.seawater.mybatisPlus.config;


import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

/**
 * 请求出入参拦截
 * Created by zhouhaishui on 2018/7/3.
 */
@Aspect
@Component
public class WebRequestAroundAdvice {

    private static final Logger logger = LoggerFactory.getLogger(WebRequestAroundAdvice.class);

    @Pointcut( value = "execution(* com.seawater.mybatisPlus.controller.*.*(..))" )
    public void pointcut(){}

    @Around("pointcut()")
    public Object handle(ProceedingJoinPoint joinPoint) throws Throwable{

        preHandle();

        Object retVal = joinPoint.proceed();

        postHandle(retVal);

        return retVal;
    }


    private void preHandle() {
        HttpServletRequest request = ( (ServletRequestAttributes) RequestContextHolder.getRequestAttributes() ).getRequest();
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
        StringBuffer sb = new StringBuffer();
        sb.append("{");

        Enumeration<String> headers = request.getHeaderNames();
        int i = 0;
        while (headers.hasMoreElements()) {
            String header = headers.nextElement();

            if (i > 0)
                sb.append(", ");
            sb.append(header + ": " + request.getHeader(header));
            i++;
        }
        sb.append("}");

        logger.debug("Pre handling request: {}, headers: {}", getRequestInfo(request, true), sb.toString());
        logger.info("Pre handling request: {}, headers: {}", getRequestInfo(request, true));
        logger.info("parameters: {}",getParamString(request.getParameterMap()).toString());
    }

    private void postHandle(Object retVal) {
        HttpServletRequest request = ( (ServletRequestAttributes) RequestContextHolder.getRequestAttributes() ).getRequest();
        long startTime = (Long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        long executeTime = endTime - startTime;
        logger.debug("Post handling request: {}, response: {}", getRequestInfo(request, false), JSONObject.toJSONString(retVal));
        logger.info("Post handling request: {}, response: {}", getRequestInfo(request, false), JSONObject.toJSONString(retVal));
        logger.debug("request cost: "+ executeTime + " ms");
        logger.info("request cost: "+ executeTime + " ms");
    }

    private String getRequestInfo(HttpServletRequest request, boolean requestDetails) {
        StringBuffer sb = new StringBuffer();
        sb.append(request.getMethod()).append(" ");
        sb.append(request.getRequestURI());
        if (requestDetails) {
            Enumeration<String> e = request.getParameterNames();
            sb.append(" ").append("{");
            int i = 0;
            while (e.hasMoreElements()) {
                String name = e.nextElement();
                String val = request.getParameter(name);

                if (val != null && !val.isEmpty()) {
                    if (i > 0)
                        sb.append(", ");
                    sb.append(name).append(": ").append(val);

                    i++;
                }
            }
            sb.append("}");
        }

        return sb.toString();
    }

    private String getParamString(Map<String, String[]> map) {
        StringBuilder sb = new StringBuilder();
        sb.append(" ").append("{");
        for(Map.Entry<String,String[]> e:map.entrySet()){
            sb.append(e.getKey()).append("=");
            String[] value = e.getValue();
            if(value != null && value.length == 1){
                sb.append(value[0]).append(";");
            }else{
                sb.append(Arrays.toString(value)).append(";");
            }
        }
        sb.append("}");
        return sb.toString();
    }
}
