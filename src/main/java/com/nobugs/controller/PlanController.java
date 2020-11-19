package com.nobugs.controller;

import com.alibaba.fastjson.JSONObject;
import com.nobugs.entity.*;
import com.nobugs.service.PlanService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/plan")
public class PlanController {

    @Autowired
    private PlanService planService;


    @RequestMapping(value = "/getall")
    public @ResponseBody String getallplan(){
        List<TProductPlan> tproductplans=planService.getplan();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("tproductplans",tproductplans);
        System.out.println(jsonObject.toJSONString());
        return jsonObject.toJSONString();
    }

    //分页
    @RequestMapping("/getallByPage")
    public @ResponseBody String getJsonPage(@RequestParam(defaultValue = "1") Integer page,
                                            @RequestParam(defaultValue = "5") Integer limit,
                                            @RequestParam(required = false) String key){
        //1
        Page<TProductPlan> pages = new Page<TProductPlan>(page, limit);
        pages.setKey(key==""||key==null?null:key);
        //2调用业务逻辑层 获取取数据
        ArrayList<TProductPlan> plans=planService.getallByPage(pages);

        //将数据封装
        HashMap<String,Object> resultMap=new HashMap<>();
        resultMap.put("pages",pages);

        resultMap.put("plans",plans);

        return JSONObject.toJSONString(resultMap);
    }

    //删除
    @RequestMapping(value = "/delete")
    public @ResponseBody String delectPlan(@RequestParam(required = true) Integer id){
        int plan=planService.deleteplan(id);
        return JSONObject.toJSONString(plan);
    }

    //创建
    @RequestMapping(value = "/addPlan", method = {RequestMethod.POST}, produces = {"application/json;charset=UTF-8"})
    public @ResponseBody String addPlan(
//                                         @RequestParam(value = "planSeq") String planSeq,
                                         @RequestParam(value = "orderId") Integer orderId,
                                         @RequestParam(value = "productId") Integer productId,
                                         @RequestParam(value = "planCount") Integer planCount,
                                         @RequestParam(value = "adddDate") String adddDate,
                                         @RequestParam(value = "addsDate") String addsDate,
                                         @RequestParam(value = "addeDate") String addeDate) throws ParseException {
        TProductPlan tProductPlan = new TProductPlan();
        tProductPlan.setFactoryId(1);
//        tProductPlan.setPlanSeq(planSeq);
        tProductPlan.setOrderId(orderId);
        tProductPlan.setProductId(productId);
        tProductPlan.setPlanCount(planCount);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        tProductPlan.setDeliveryDate(simpleDateFormat.parse(adddDate));
        tProductPlan.setPlanStartDate(simpleDateFormat.parse(addsDate));
        tProductPlan.setPlanEndDate(simpleDateFormat.parse(addeDate));
        int res = planService.addPlan(tProductPlan);
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
    @RequestMapping(value = "/updatePlan", method = {RequestMethod.POST}, produces = {"application/json;charset=UTF-8"})
    public @ResponseBody String updatePlan(@RequestParam(value = "plan") String plan,
                                        @RequestParam(value = "planCount") Integer planCount,
                                        @RequestParam(value = "updatedDate") String updatedDate,
                                        @RequestParam(value = "updatesDate") String updatesDate,
                                        @RequestParam(value = "updateeDate") String updateeDate) throws ParseException {


        TProductPlan tProductPlan0 = JSONObject.parseObject(plan, TProductPlan.class);
        //首先将传送过来的JSON字符串转换成java对象

//        System.out.println(tProductOrder);
//        System.out.println("nbbbb");

        //利用这个对象赋值
        TProductPlan tProductPlan = new TProductPlan();
        tProductPlan.setId(tProductPlan0.getId());
        tProductPlan.setPlanCount(planCount);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        tProductPlan.setDeliveryDate(simpleDateFormat.parse(updatedDate));
        tProductPlan.setPlanStartDate(simpleDateFormat.parse(updatesDate));
        tProductPlan.setPlanEndDate(simpleDateFormat.parse(updateeDate));

        System.out.println(tProductPlan);
        int res = planService.updatePlan(tProductPlan);
        JsonResult jsonResult = new JsonResult();
        if(res == 1){
            jsonResult.setResult(true);
        } else{
            jsonResult.setResult(false);
            jsonResult.setErrMsg("编辑失败");
        }
        return jsonResult.toString();
    }

    //查询
    @RequestMapping("/searchPlanByPage")
    public @ResponseBody String searchPage(@RequestParam(value = "planSeq") String planSeq,
                                           @RequestParam(value = "orderSeq") String orderSeq,
                                           @RequestParam(value = "planStatus") Integer planStatus,
                                           @RequestParam(value = "productName") String productName,
                                           @RequestParam(defaultValue = "1") Integer page,
                                           @RequestParam(defaultValue = "5") Integer limit,
                                           @RequestParam(required = false) String key){
        //1
        Page<TProductPlan> pages = new Page<TProductPlan>(page, limit);
        pages.setKey(key==""||key==null?null:key);

        TProductPlan tProductPlan = new TProductPlan();
//        System.out.println("nb : " + tProductPlan.getOrderSeq());
//
//        System.out.println(orderSeq.equals("null"));

        //要用equals判断字符串是否相等，直接用 = 无效
        if(planSeq != null && !planSeq.equals("")) tProductPlan.setPlanSeq(planSeq);
        if(orderSeq != null && !orderSeq.equals("")) tProductPlan.setOrderSeq(orderSeq);
        if(planStatus != null && !planStatus.equals("")) tProductPlan.setPlanStatus(planStatus);
        if(productName != null && !productName.equals("")) tProductPlan.setProductName(productName);
        tProductPlan.setFactoryId(1);
        //将查询参数封装成对象，放到page里面
        pages.setT(tProductPlan);

        //2调用业务逻辑层 获取取数据
        ArrayList<TProductPlan> plans=planService.searchByPage(pages);

        //将数据封装
        HashMap<String,Object> resultMap=new HashMap<>();
        resultMap.put("pages",pages);
        resultMap.put("plans",plans);

        return JSONObject.toJSONString(resultMap);
    }



    //启动
    @RequestMapping(value = "/planStatus", method = {RequestMethod.POST}, produces = {"application/json;charset=UTF-8"} )
    public @ResponseBody String orderStatus(HttpServletRequest request){
        TProductPlan tProductPlan = new TProductPlan();
        System.out.println(request.getParameter("id"));
        tProductPlan.setId(Integer.parseInt(request.getParameter("id")));
        tProductPlan.setPlanStatus(Integer.parseInt(request.getParameter("status")));
        int res = planService.planStatus(tProductPlan);
        JsonResult<TProductPlan> jsonResult = new JsonResult<TProductPlan>();
        if(res == 1){
            jsonResult.setResult(true);
            tProductPlan = planService.getBtID(tProductPlan.getId());
            jsonResult.setData(tProductPlan);
        } else{
            jsonResult.setResult(false);
            jsonResult.setErrMsg("数据库访问出错");
        }
        return jsonResult.toString();
    }

    //启动创建工单
    @RequestMapping(value = "/addschedule", method = {RequestMethod.POST}, produces = {"application/json;charset=UTF-8"} )
    public @ResponseBody String addschedule(@RequestParam(value = "plan") String plan){
        TProductPlan tProductPlan0 = JSONObject.parseObject(plan, TProductPlan.class);
        //首先将传送过来的JSON字符串转换成java对象

//        System.out.println(tProductOrder);
//        System.out.println("nbbbb");

        //利用这个对象赋值
        TProductSchedule tProductSchedule= new TProductSchedule();
        tProductSchedule.setPlanId(tProductPlan0.getId());
        tProductSchedule.setProductId(tProductPlan0.getProductId());
        tProductSchedule.setStartDate(tProductPlan0.getPlanStartDate());
        tProductSchedule.setEndDate(tProductPlan0.getPlanEndDate());
        tProductSchedule.setScheduleCount(tProductPlan0.getPlanCount());
        int res = planService.addSchedule(tProductSchedule);
        JsonResult jsonResult = new JsonResult();
        if(res == 1){
            jsonResult.setResult(true);
        } else{
            jsonResult.setResult(false);
            //jsonResult.setErrMsg("新建失败");
        }

        return jsonResult.toString();
    }


    @RequestMapping(value = "/getProducts", produces = {"application/json;charset=UTF-8"})
    public @ResponseBody String getProducts(){
        ArrayList<TProduct> products = new ArrayList<>();
        products = planService.getProducts(1);
        JsonResult< ArrayList<TProduct> > jsonResult = new JsonResult< ArrayList<TProduct> >();
        jsonResult.setResult(true);
        jsonResult.setData(products);
        return jsonResult.toString();
    }

    @RequestMapping(value = "/getOrders", produces = {"application/json;charset=UTF-8"})
    public @ResponseBody String getOrders(){
        ArrayList<TProductOrder> orders = new ArrayList<>();
        orders = planService.getOrders();
        JsonResult< ArrayList<TProductOrder> > jsonResult = new JsonResult< ArrayList<TProductOrder> >();
        jsonResult.setResult(true);
        jsonResult.setData(orders);
        return jsonResult.toString();
    }

}
