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

    private static final String REQUEST_PREFIX = "Request: ";
    private static final String RESPONSE_PREFIX = "Response: ";

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

        logger.info("=================="+ request.getHeader("userAgent"));

        String params = "";
        String requestURl = request.getRequestURL().toString();
        String method = request.getMethod();
        if("POST".equalsIgnoreCase(method)){
            params = this.getBodyString(request.getReader());
        }else{
            Map<String,String[]> pramMaps = request.getParameterMap();
            Enumeration<String> enu = request.getParameterNames();
            Map<String, Object> pramMap = new HashMap<>();
            while (enu.hasMoreElements()) {
                String paramName = enu.nextElement().trim();
                pramMap.put(paramName, request.getParameter(paramName));
            }
            params = pramMap.toString();
        }
        logger.info(REQUEST_PREFIX + "[" + method + "]" + requestURl  + "," +  params);

        ResponseWrapper responseWrapper = new ResponseWrapper(response);

        filterChain.doFilter(servletRequest,responseWrapper);
        String result = responseWrapper.getResponseData(response.getCharacterEncoding());
        //获取response返回的内容并重新写入response
        response.getOutputStream().write(result.getBytes());
        long exeCost = System.currentTimeMillis()-start;
        logger.info( RESPONSE_PREFIX + "[" + method + "]" +requestURl +  "," + result + ",exeCost[" + exeCost + "]");
    }

    @Override
    public void destroy() {

    }

    //获取request请求body中参数
    private static String getBodyString(BufferedReader br) {
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
