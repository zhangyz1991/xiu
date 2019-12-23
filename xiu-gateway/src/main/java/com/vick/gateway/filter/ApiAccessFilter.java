package com.vick.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author zyz
 * @since 2019-12-23
 */
@Slf4j
@WebFilter(filterName = "ApiAccessFilter", urlPatterns = "/*")
public class ApiAccessFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        long start = System.currentTimeMillis(); // 请求进入时间

        log.info("[Api Access] start, uri: {}, method: {}, client: {}",
                request.getRequestURI(), request.getMethod(), getIP(request));
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("[Api Access]   end, uri: {}, method: {}, duration: {}ms",
                request.getRequestURI(), request.getMethod(), System.currentTimeMillis() - start);
    }

    /**
     * 获取IP地址
     *
     * @param request 请求
     * @return request发起客户端的IP地址
     */
    private String getIP(HttpServletRequest request) {
        if (request == null) {
            return "0.0.0.0";
        }

        String Xip = request.getHeader("X-Real-IP");
        String XFor = request.getHeader("X-Forwarded-For");

        String UNKNOWN_IP = "unknown";
        boolean unknown = UNKNOWN_IP.equalsIgnoreCase(XFor);
        if (StringUtils.isNotEmpty(XFor) && !unknown) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = XFor.indexOf(",");
            if (index != -1) {
                return XFor.substring(0, index);
            } else {
                return XFor;
            }
        }

        XFor = Xip;
        if (StringUtils.isNotEmpty(XFor) && !unknown) {
            return XFor;
        }

        if (StringUtils.isBlank(XFor) || unknown) {
            XFor = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(XFor) || unknown) {
            XFor = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(XFor) || unknown) {
            XFor = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isBlank(XFor) || unknown) {
            XFor = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isBlank(XFor) || unknown) {
            XFor = request.getRemoteAddr();
        }
        return XFor;
    }
}
