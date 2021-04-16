package com.ln.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @作者: 李跃辉
 * @时间: 2021/4/13 22:22
 */
public class Myfilter implements Filter {

     Set<String> noFilterUrl = new HashSet<String>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // web.xml设置了不过滤的路径，我们需要在init方法中按 按配置的名字读取
        String nofilterUrl = filterConfig.getInitParameter("noFilterUrl");
        // 我们在配置了多个路径的时候可以用逗号和分号隔开 :<param-value>/us/goLogin.do,/us/xxxx.do</param-value>
        // 遍历分开的url路径，装在 noFilterUrl里
        for (String url : nofilterUrl.split(",")) {
            noFilterUrl.add(url.trim());
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 获取前台请求的路径
        HttpServletRequest req = (HttpServletRequest) request;
        String requestURI = req.getRequestURI();
        if (noFilterUrl.contains(requestURI)){
            // 放行
            chain.doFilter(request,response);
        }else{
            // 获取session的登录对象
            Object user = ((HttpServletRequest) request).getSession().getAttribute("user");
            if (user==null){
                request.getRequestDispatcher("index.html").forward(request,response);
            }else{
                chain.doFilter(request,response);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
