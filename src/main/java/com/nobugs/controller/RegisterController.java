package com.nobugs.controller;


import com.nobugs.entity.JsonResult;
import com.nobugs.entity.TFactory;
import com.nobugs.entity.TUser;
import com.nobugs.service.TUserRegisterService;
import org.eclipse.jdt.internal.compiler.env.ISourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class RegisterController {

    @Autowired
    private TUserRegisterService tUserRegisterService;





    @RequestMapping(value = "/register", method = {RequestMethod.POST})
    public @ResponseBody String registerService(HttpServletRequest request){
        System.out.println("cxll");

        System.out.println(request.getParameter("username"));
        System.out.println(request.getParameter("password"));
        System.out.println(request.getParameter("factoryname"));


        TUser tUser = new TUser();
        TFactory tFactory = new TFactory();
        tUser.setUserName(request.getParameter("username"));
        tUser.setUserPasswd(request.getParameter("password"));
        tFactory.setFactoryName(request.getParameter("factoryname"));


        int res = tUserRegisterService.register(tUser, tFactory);
        System.out.println(res);

        JsonResult jsonResult = new JsonResult();
        if(res == 1){
            jsonResult.setErrMsg("用户名已存在");
            jsonResult.setResult(false);
        } else if(res == 2){
            jsonResult.setErrMsg("工厂名已存在");
            jsonResult.setResult(false);
        } else if(res == 3){
            jsonResult.setErrMsg("数据库插入失败");
            jsonResult.setResult(false);
        } else if(res == 0){
//            jsonResult.setErrMsg("数据库插入失败");
            System.out.println(111);
            jsonResult.setResult(true);
        }
        return jsonResult.toString();


    }

    @RequestMapping("/getUser")
    public @ResponseBody String getUser(HttpSession session){
        TUser tUser = (TUser) session.getAttribute("user");
        JsonResult<String> jsonResult = new JsonResult<>();
        if(tUser == null){
            jsonResult.setResult(false);
        }else{
            jsonResult.setResult(true);
            jsonResult.setData(tUser.getUserName());
        }
        return jsonResult.toString();
    }

    @RequestMapping("/logout")
    public @ResponseBody void logout(HttpSession session){
        session.invalidate();
    }
}
