package com.ln.intercepter;

import com.ln.entity.UserBean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

/**
 * @作者: 李跃辉
 * @时间: 2021/4/13 20:54
 *
 * 拦截器：mvc
 */
public class MyIntercepter implements HandlerInterceptor {

    private List<String> exceptUrls;

    // 重写三个方法
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取到请求路径
        String requestURI = request.getRequestURI();
        if (exceptUrls.contains(requestURI)){
            // 直接放行，让他去登陆
            return true;
        }else{
            // 如果是访问别的页面，需要拦截，看是否登录
            UserBean user = (UserBean) request.getSession().getAttribute("user");
            if (user==null){
                // 让他返回登录页面
                request.getRequestDispatcher("index.html").forward(request,response);
            }else{
                // 判断用户权限
                Set<String> urls = (Set<String>) request.getSession().getAttribute("urls");
                if (urls!=null){
                    if (urls.contains(requestURI)){
                        return true;
                    }else{
                        // 用户无法访问的页面需要打回
                        response.setContentType("text/html");
                        PrintWriter out = response.getWriter();
                        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
                        out.println("<HTML>");
                        out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
                        out.println("  <BODY>");
                        out.println("<script>alert('哥们做人老实不能非法访问，小心请你媳妇做头发');</script>");
                        out.println("  </BODY>");
                        out.println("</HTML>");
                        out.flush();
                        out.close();
                    }
                }


            }
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    public List<String> getExceptUrls() {
        return exceptUrls;
    }

    public void setExceptUrls(List<String> exceptUrls) {
        this.exceptUrls = exceptUrls;
    }
}
