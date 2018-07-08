package com.example.demo.filter;


import com.example.demo.Application;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sun.misc.Contended;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 过滤器实现接口请求参数及返回数据打印
 * */
@Contended
public class LogCostFilter implements Filter{

    Logger logger = LogManager.getLogger(Application.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long start = System.currentTimeMillis();
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        if(servletRequest instanceof HttpServletRequest) {
            request = (HttpServletRequest)servletRequest;
        }
        if(servletResponse instanceof HttpServletResponse){
            response = (HttpServletResponse)servletResponse;
        }

        String param = "";
        if (logger.isDebugEnabled()) {

        }
        String method = request.getMethod();
        String requestURI = request.getRequestURI();
        if("POST".equalsIgnoreCase(method)){
            param = this.getBodyString(request.getReader());

        }else{
            Map<String,String[]> pramMaps = request.getParameterMap();
            Enumeration<String> enu = request.getParameterNames();
            Map<String, Object> pramMap = new HashMap<>();
            while (enu.hasMoreElements()) {
                String paramName = enu.nextElement().trim();
                pramMap.put(paramName, request.getParameter(paramName));
            }
            requestURI = pramMap.toString();
        }
        logger.info(requestURI  + ",[" + method + "]request:" + param);

        ResponseWrapper responseWrapper = new ResponseWrapper(response);
        filterChain.doFilter(servletRequest,responseWrapper);
        String result = responseWrapper.getResponseData(response.getCharacterEncoding());
        //获取response返回的内容并重新写入response
        response.getOutputStream().write(result.getBytes());
        long exec = System.currentTimeMillis()-start;
        logger.info( requestURI + ",[" + method + "]response:" + result + ",[" +exec + "]");
    }

    @Override
    public void destroy() {

    }

    //获取request请求body中参数
    public static String getBodyString(BufferedReader br) {
        String inputLine;
        String str = "";
        try {
            while ((inputLine = br.readLine()) != null) {
                str += inputLine;
            }
            br.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
        return str;
    }

}
