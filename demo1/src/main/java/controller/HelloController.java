package controller;


import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//注意：这里我们先导入Controller接口
public class HelloController implements Controller{

    @Override
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        //处理的相关代码
        String result = "Hello,SpringMVC";
        //传值
        modelAndView.addObject("msg",result);
        //指定页面
        modelAndView.setViewName("hello");

        return modelAndView;
    }
}