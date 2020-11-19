package com.nobugs.controller;

import com.nobugs.entity.JsonResult;
import com.nobugs.entity.TUser;
import com.nobugs.service.TUserLoginService;
import com.nobugs.service.TUserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
@CrossOrigin
@Controller
@RequestMapping(value ="/user",produces = {"application/json;charset=UTF-8"})
public  class LoginController {
    @Autowired
    private TUserLoginService tUserLoginService;
    @RequestMapping(value = "/ajax/login",method = {RequestMethod.POST},produces = {"application/json;charset=UTF-8"})
    public @ResponseBody String ajaxLogin(@RequestParam(required = true) String uname,
                                          @RequestParam(required = true) String upwd,
                                          HttpSession session) throws IOException {
        //1取  取参数  见形参
        // 2调 调用业务逻辑层
        TUser user= tUserLoginService.getUser(uname,upwd);
        //不为空则登录成功
        if(user!=null){
            //记录登录状态 让后面访问的页面都知道我已登录
            // 将用户信息存放入session中
            // session对象存储在服务端
            session.setAttribute("user",user);
            //组织json返回数据
            JsonResult<TUser> result=new JsonResult<>();
            result.setResult(true);
            result.setData(user);
            return result.toString();
        }
        else
        {
            JsonResult<TUser> result=new JsonResult<>(false,"用户名或密码不正确！");
            return result.toString();
        }
    }

}
