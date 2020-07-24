package controller;

import entity.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import utils.JsonUtils;

@Controller//相当于实现Controller接口
public class HelloController {

//    //http://localhost:8080/hello?a=1&b=2
//    @RequestMapping("/hello")//前端请求
//    public String test(int a,int b,Model model){
//        int result = a+b;
//        model.addAttribute("msg",result);
//        return "hello"; //指定跳转的页面
//    }

    //http://localhost:8080/hello/1/2
    @GetMapping("/hello2/{a}/{b}")//前端请求
    public String test1(@PathVariable int a,@PathVariable int b, Model model){
        int result = a+b;
        model.addAttribute("msg","加："+result);
        return "hello"; //指定跳转的页面
    }
    //http://localhost:8080/hello/1/2
    @PostMapping("/hello2/{a}/{b}")//前端请求
    public String test2(@PathVariable int a,@PathVariable int b, Model model){
        int result = a-b;
        model.addAttribute("msg","减："+result);
        return "hello"; //指定跳转的页面
    }

    @RequestMapping("/json")
    @ResponseBody//表示不返回页面，返回实际的值，也可在类上使用@RestController
    public String test3(){
        Person person = new Person(560,"李帅");
        return JsonUtils.getJson(person);
    }
}
