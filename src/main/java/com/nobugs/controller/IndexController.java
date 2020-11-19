package com.nobugs.controller;

import com.alibaba.fastjson.JSONObject;
import com.nobugs.dao.TUserMapper;
import com.nobugs.entity.*;
import com.nobugs.service.IndexService;
import com.nobugs.service.TUserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.SessionScope;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
@RequestMapping("/indexaction")
public class IndexController {

    @Autowired
    private IndexService indexService;

    @RequestMapping(value = "/getOrderStatusData", method = {RequestMethod.GET}, produces = {"application/json;charset=UTF-8"})
    public @ResponseBody String getOrderStatusData(){
        ArrayList<OStatusData> oStatusDatas =  indexService.getNumByStatus();
        JsonResult< ArrayList<OStatusData> > jsonResult = new JsonResult<>();
        jsonResult.setResult(true);
        jsonResult.setData(oStatusDatas);
        return jsonResult.toString();
    }

    @RequestMapping(value = "/getOrderMonthData", method = {RequestMethod.GET}, produces = {"application/json;charset=UTF-8"})
    public @ResponseBody String getOrderMonthData(){
        int[] oMonthDatas = indexService.getNumByMonth();
        JsonResult< int[] > jsonResult = new JsonResult<>();
        jsonResult.setData(oMonthDatas);
        jsonResult.setResult(true);
        return jsonResult.toString();
    }

    /**
     * 直接从业务逻辑层获取各种状态的设备所占的百分比
     * @return
     */
    @RequestMapping(value = "/getEquipmentStatus", method = {RequestMethod.GET}, produces = {"application/json;charset=UTF-8"})
    public @ResponseBody String getEquipmentStatus(){
        int[] eStatusData = indexService.getEquipmentStatus();
        JsonResult< int[] > jsonResult = new JsonResult<>();
        jsonResult.setData(eStatusData);
        jsonResult.setResult(true);
        return jsonResult.toString();
    }

    @RequestMapping("/getEquipmentsByPage")
    public @ResponseBody String getEquipmentsByPage(@RequestParam(defaultValue = "1") Integer page,
                                                    @RequestParam(defaultValue = "4") Integer limit,
                                                    @RequestParam(required = false) String key){
        //1
        Page<Integer> pages = new Page<Integer>(page, limit);
        pages.setKey(key==""||key==null?null:key);
        pages.setT(1);

        //2调用业务逻辑层 获取取数据
        ArrayList<TEquipment> tEquipments = indexService.getEquipmentsByPage(pages);

        //将数据封装
        HashMap<String,Object> resultMap=new HashMap<>();
        resultMap.put("pages",pages);
        resultMap.put("equipments",tEquipments);

        return JSONObject.toJSONString(resultMap);
    }


}
