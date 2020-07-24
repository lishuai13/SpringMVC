package config;


import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 拦截器
 */
public class MyInterceptor implements HandlerInterceptor {

    //在请求处理的方法之前执行
    //如果返回true执行下一个拦截器
    //如果返回false就不执行下一个拦截器
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        HttpSession session = request.getSession();
        Object userInfo = session.getAttribute("userInfo");
        System.out.println(userInfo);
        if (session.getAttribute("userInfo")!=null)
            return true;

        else if (request.getRequestURI().contains("ogin"))
            return true;

        else if (request.getRequestURI().contains("index"))
            return true;

        else {
            response.sendRedirect("/goLogin");
            return false;
        }
    }
}
