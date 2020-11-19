package com.nobugs.controller;

import com.alibaba.fastjson.JSONObject;
import com.nobugs.entity.*;
import com.nobugs.service.PlanService;
import com.nobugs.service.TProductOrderService;
import com.nobugs.service.TScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    @Autowired
    private TScheduleService tScheduleService;


    //显示所有
    @RequestMapping(value = "/getall")
    public @ResponseBody String getallschedule(){
        List<TProductSchedule> tProductSchedules=tScheduleService.getschedule();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("tProductSchedules",tProductSchedules);
        System.out.println(jsonObject.toJSONString());
        return jsonObject.toJSONString();
    }

    //分页
    @RequestMapping("/getallByPage")
    public @ResponseBody String getJsonPage(@RequestParam(defaultValue = "1") Integer page,
                                            @RequestParam(defaultValue = "5") Integer limit,
                                            @RequestParam(required = false) String key){
        //1
        Page<TProductSchedule> pages = new Page<TProductSchedule>(page, limit);
        pages.setKey(key==""||key==null?null:key);
        //2调用业务逻辑层 获取取数据
        ArrayList<TProductSchedule> schedules=tScheduleService.getallByPage(pages);

        //将数据封装
        HashMap<String,Object> resultMap=new HashMap<>();
        resultMap.put("pages",pages);

        resultMap.put("schedules",schedules);

        return JSONObject.toJSONString(resultMap);
    }


    //删除
    @RequestMapping(value = "/deleteSchedule",method = {RequestMethod.POST})
    public @ResponseBody String delectSchedule(@RequestParam(required = true) Integer id){
        int res = tScheduleService.deleteSchedule(id);
        JsonResult jsonResult = new JsonResult();
        if(res == 1){
            jsonResult.setResult(true);
        } else{
            jsonResult.setResult(false);
        }
        return jsonResult.toString();
    }


//    查询
    @RequestMapping(value = "/select",method = {RequestMethod.GET})
    public @ResponseBody String selectSchedule(@RequestParam(required = true) String planSeq,
                                               @RequestParam(required = true) String scheduleSeq){

        TProductSchedule schedule=tScheduleService.selectschedule(planSeq,scheduleSeq);

        return JSONObject.toJSONString(schedule);
    }


    //新建
    @RequestMapping(value = "/addSchedule", method = {RequestMethod.POST}, produces = {"application/json;charset=UTF-8"})
    public @ResponseBody String addSchedule(@RequestParam(value = "planId") Integer planId,
                                         @RequestParam(value = "productId") Integer productId,
                                         @RequestParam(value = "scheduleCount") Integer scheduleCount,
                                         @RequestParam(value = "equipmentId") Integer equipmentId,
                                         @RequestParam(value = "addsDate") String addsDate,
                                         @RequestParam(value = "addeDate") String addeDate) throws ParseException {
        TProductSchedule tProductSchedule = new TProductSchedule();
        tProductSchedule.setFactoryId(1);
        tProductSchedule.setPlanId(planId);
        tProductSchedule.setProductId(productId);
        tProductSchedule.setScheduleCount(scheduleCount);
        tProductSchedule.setEquipmentId(equipmentId);
        tProductSchedule.setStartDate(simpleDateFormat.parse(addsDate));
        tProductSchedule.setEndDate(simpleDateFormat.parse(addeDate));

        int res = tScheduleService.addSchedule(tProductSchedule);
        JsonResult jsonResult = new JsonResult();
        if(res == 1){
            jsonResult.setResult(true);
        } else{
            jsonResult.setResult(false);
            jsonResult.setErrMsg("新建失败");
        }

        return jsonResult.toString();
    }

    //编辑
    @RequestMapping(value = "/updateSchedule", method = {RequestMethod.POST}, produces = {"application/json;charset=UTF-8"})
    public @ResponseBody String addSchedule(@RequestParam(value = "scheduleId") Integer scheduleId,
                                            @RequestParam(value = "scheduleCount") Integer scheduleCount,
                                            @RequestParam(value = "equipmentId") Integer equipmentId,
                                            @RequestParam(value = "startDate") String startDate,
                                            @RequestParam(value = "endDate") String endDate) throws ParseException {



//        System.out.println(tProductOrder);
//        System.out.println("nbbbb");

        //利用这个对象赋值
        TProductSchedule tProductSchedule = new TProductSchedule();
        tProductSchedule.setId(scheduleId);
        tProductSchedule.setScheduleCount(scheduleCount);
        tProductSchedule.setEquipmentId(equipmentId);
        tProductSchedule.setStartDate(simpleDateFormat.parse(startDate));
        tProductSchedule.setEndDate(simpleDateFormat.parse(endDate));

        int res = tScheduleService.updateSchedule(tProductSchedule);
        JsonResult jsonResult = new JsonResult();
        if(res == 1){
            jsonResult.setResult(true);
        } else{
            jsonResult.setResult(false);
            jsonResult.setErrMsg("编辑失败");
        }
        return jsonResult.toString();
    }

    //启动
    @RequestMapping(value = "/changeStatus", method = {RequestMethod.POST}, produces = {"application/json;charset=UTF-8"} )
    public @ResponseBody String planStatus(HttpServletRequest request){

        TProductSchedule tProductSchedule = new TProductSchedule();
        tProductSchedule.setId(Integer.parseInt(request.getParameter("id")));
        tProductSchedule.setScheduleStatus(Integer.parseInt(request.getParameter("status")));

        int res = tScheduleService.changeStatus(tProductSchedule);
        JsonResult<TProductSchedule> jsonResult = new JsonResult<TProductSchedule>();
        if(res == 1){
            jsonResult.setResult(true);
            tProductSchedule = tScheduleService.getByID(tProductSchedule.getId());
            jsonResult.setData(tProductSchedule);
        } else{
            jsonResult.setResult(false);
            jsonResult.setErrMsg("数据库访问出错");
        }
        return jsonResult.toString();
    }
    //计划
    @RequestMapping(value = "/getPlans", produces = {"application/json;charset=UTF-8"})
    public @ResponseBody String getPlans(){
        ArrayList<TProductPlan> plans = new ArrayList<>();
        plans = tScheduleService.getPlans(1);
        JsonResult< ArrayList<TProductPlan> > jsonResult = new JsonResult< ArrayList<TProductPlan> >();
        jsonResult.setResult(true);
        jsonResult.setData(plans);
        return jsonResult.toString();
    }

    //产品
    @RequestMapping(value = "/getProducts", produces = {"application/json;charset=UTF-8"})
    public @ResponseBody String getProducts(){
        ArrayList<TProduct> products = new ArrayList<>();
        products = tScheduleService.getProducts(1);
        JsonResult< ArrayList<TProduct> > jsonResult = new JsonResult< ArrayList<TProduct> >();
        jsonResult.setResult(true);
        jsonResult.setData(products);
        return jsonResult.toString();
    }

    //设备
    @RequestMapping(value = "/getEquipments", produces = {"application/json;charset=UTF-8"})
    public @ResponseBody String getEquipments(){
        ArrayList<TEquipment> equipments = new ArrayList<>();
        equipments = tScheduleService.getEquipments(1);
        JsonResult< ArrayList<TEquipment> > jsonResult = new JsonResult< ArrayList<TEquipment> >();
        jsonResult.setResult(true);
        jsonResult.setData(equipments);
        return jsonResult.toString();
    }

    /*搜索分页*/
    @RequestMapping("/searchScheduleByPage")
    public @ResponseBody String searchPage(@RequestParam(value = "planSeq") String planSeq,
                                           @RequestParam(value = "scheduleSeq") String scheduleSeq,
                                           @RequestParam(value = "productName") String productName,
                                           @RequestParam(defaultValue = "1") Integer page,
                                           @RequestParam(defaultValue = "5") Integer limit,
                                           @RequestParam(required = false) String key){
        //1
        Page<TProductSchedule> pages = new Page<TProductSchedule>(page, limit);
        pages.setKey(key==""||key==null?null:key);

        TProductSchedule tProductSchedule = new TProductSchedule();

        //要用equals判断字符串是否相等，直接用 = 无效
        if(planSeq != null && !planSeq.equals("")) tProductSchedule.setPlanSeq(planSeq);
        if(scheduleSeq != null && !scheduleSeq.equals("")) tProductSchedule.setScheduleSeq(scheduleSeq);
        if(productName != null && !productName.equals("")) tProductSchedule.setProductName(productName);
        tProductSchedule.setFactoryId(1);
        //将查询参数封装成对象，放到page里面
        pages.setT(tProductSchedule);

        //2调用业务逻辑层 获取取数据
        ArrayList<TProductSchedule> schedules = tScheduleService.searchByPage(pages);

        //将数据封装
        HashMap<String,Object> resultMap=new HashMap<>();
        resultMap.put("pages",pages);
        resultMap.put("schedules",schedules);

        return JSONObject.toJSONString(resultMap);
    }



}

