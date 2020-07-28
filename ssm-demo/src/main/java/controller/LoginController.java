package controller;

import entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    public String login(@Validated User user, BindingResult bindingResult, HttpSession Session, String name, Model model) {
        Session.setAttribute("userInfo", name);
        Map<String, Object> errorsMap = new HashMap<>();
        // 如果校验时有不符合校验规则的情况出现，springMVC会将错误信息放在BindingResult对象的错误提示信息里面
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {

                System.out.println("错误消息提示：" + error.getDefaultMessage());
                System.out.println("错误的字段是？" + error.getField());
                System.out.println(error);
                System.out.println("------------------------");
                errorsMap.put(error.getField(), error.getDefaultMessage());
            }
            //可以吧错误信息使用jsp进行回显
            model.addAttribute("errorInfo", errorsMap);
            System.out.println("有校验错误");
            return "login";
        } else {
            return "redirect:/success";
        }
    }
    @RequestMapping("/goOut")
    public String out(HttpSession Session){
        Session.invalidate();
        return "index";
    }
}
