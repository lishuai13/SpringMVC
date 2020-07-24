package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }


    @RequestMapping("/goLogin")
    public String goLogin(){
        return "login";
    }

    @RequestMapping("/login")
    public String login(HttpSession Session,String username){
        Session.setAttribute("userInfo",username);
        return "redirect:/allBook";
    }

    @RequestMapping("/goOut")
    public String out(HttpSession Session,String username){
        Session.invalidate();
        return "index";
    }
}
