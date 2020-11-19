package com.nobugs.controller;


import com.alibaba.fastjson.JSONObject;
import com.nobugs.entity.*;
import com.nobugs.service.TProductOrderService;
import com.sun.org.apache.xpath.internal.objects.XNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/productOrder")
public class ProductOrderController {


    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    @Autowired
    private TProductOrderService tProductOrderService;



    @RequestMapping("/getall")
    public @ResponseBody String getAll(){
        List<TProductOrder> list;
        list = tProductOrderService.getAllOrder();
        JsonResult<List<TProductOrder>> jsonResult = new JsonResult<List<TProductOrder>>();
        jsonResult.setData(list);
        jsonResult.setResult(true);
        return jsonResult.toString();
    }




    //这个类将不再使用
    @RequestMapping("/getAllByPage")
    public @ResponseBody String getJsonPage(@RequestParam(defaultValue = "1") Integer page,
                                            @RequestParam(defaultValue = "5") Integer limit,
                                            @RequestParam(required = false) String key){
        //1
        Page<TProductOrder> pages = new Page<TProductOrder>(page, limit);
        pages.setKey(key==""||key==null?null:key);
        //2调用业务逻辑层 获取取数据
        ArrayList<TProductOrder> orders=tProductOrderService.getAllByPage(pages);

        //将数据封装
        HashMap<String,Object> resultMap=new HashMap<>();
        resultMap.put("pages",pages);
        resultMap.put("orders",orders);

        return JSONObject.toJSONString(resultMap);
    }


    /**
     * 查询和正常获取数据都是使用这个方法。正常获取数据将相当于没有条件的查询
     * @param orderSeq
     * @param orderStatus
     * @param productName
     * @param page
     * @param limit
     * @param key
     * @return
     */
    @RequestMapping("/searchOrderByPage")
    public @ResponseBody String searchPage(@RequestParam(value = "orderSeq") String orderSeq,
                                            @RequestParam(value = "orderStatus") Integer orderStatus,
                                            @RequestParam(value = "productName") String productName,
                                            @RequestParam(defaultValue = "1") Integer page,
                                            @RequestParam(defaultValue = "5") Integer limit,
                                            @RequestParam(required = false) String key,
                                           HttpSession session){
        //1
        TUser tUser = (TUser) session.getAttribute("user");

        Page<TProductOrder> pages = new Page<TProductOrder>(page, limit);
        pages.setKey(key==""||key==null?null:key);

        TProductOrder tProductOrder = new TProductOrder();
        System.out.println("nb : " + tProductOrder.getOrderSeq());

        System.out.println(orderSeq.equals("null"));

        //要用equals判断字符串是否相等，直接用 = 无效
        if(orderSeq != null && !orderSeq.equals("")) tProductOrder.setOrderSeq(orderSeq);
        if(orderStatus != null && !orderStatus.equals("")) tProductOrder.setOrderStatus(orderStatus);
        if(productName != null && !productName.equals("")) tProductOrder.setProductName(productName);
        tProductOrder.setFactoryId(1);
        //将查询参数封装成对象，放到page里面
        pages.setT(tProductOrder);

        //2调用业务逻辑层 获取取数据
        ArrayList<TProductOrder> orders=tProductOrderService.searchByPage(pages);

        //将数据封装
        HashMap<String,Object> resultMap=new HashMap<>();
        resultMap.put("pages",pages);
        resultMap.put("orders",orders);

        return JSONObject.toJSONString(resultMap);
    }



    @RequestMapping(value = "/orderStatus", method = {RequestMethod.POST}, produces = {"application/json;charset=UTF-8"} )
    public @ResponseBody String orderStatus(HttpServletRequest request, HttpSession session){
        TProductOrder tProductOrder = new TProductOrder();
        System.out.println(request.getParameter("id"));

        TUser tUser = (TUser) session.getAttribute("user");

        tProductOrder.setId(Integer.parseInt(request.getParameter("id")));
        tProductOrder.setOrderStatus(Integer.parseInt(request.getParameter("status")));
        tProductOrder.setUpdateUserid(tUser.getId());
        tProductOrder.setUpdateTime(new Date(System.currentTimeMillis()));

        int res = tProductOrderService.orderStatus(tProductOrder);
        JsonResult<TProductOrder> jsonResult = new JsonResult<TProductOrder>();

        if(res == 1){
            jsonResult.setResult(true);
            tProductOrder = tProductOrderService.getByID(tProductOrder.getId());
            jsonResult.setData(tProductOrder);
        } else{
            jsonResult.setResult(false);
            jsonResult.setErrMsg("数据库访问出错");
        }
        return jsonResult.toString();
    }


    @RequestMapping(value = "/getProducts", produces = {"application/json;charset=UTF-8"})
    public @ResponseBody String getProducts(){
        ArrayList<TProduct> products = new ArrayList<>();
        products = tProductOrderService.getProducts(1);
        JsonResult< ArrayList<TProduct> > jsonResult = new JsonResult< ArrayList<TProduct> >();
        jsonResult.setResult(true);
        jsonResult.setData(products);
        return jsonResult.toString();
    }


    /**
     * 新建订单
     * @param productId
     * @param productCount
     * @param endDate
     * @param session
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/addOrder", method = {RequestMethod.POST}, produces = {"application/json;charset=UTF-8"})
    public @ResponseBody String addOrder(@RequestParam(value = "productId") Integer productId,
                                         @RequestParam(value = "productCount") Integer productCount,
                                         @RequestParam(value = "eDate") String endDate,
                                         HttpSession session) throws ParseException {
        TUser tUser = (TUser) session.getAttribute("user");

        TProductOrder tProductOrder = new TProductOrder();
        tProductOrder.setCreateUserid(tUser.getId());
        tProductOrder.setCreateTime(new Date(System.currentTimeMillis()));
        tProductOrder.setFactoryId(1);
        tProductOrder.setProductId(productId);
        tProductOrder.setProductCount(productCount);
        tProductOrder.setEndDate(simpleDateFormat.parse(endDate));
        int res = tProductOrderService.addOrder(tProductOrder);
        JsonResult jsonResult = new JsonResult();
        if(res == 1){
            jsonResult.setResult(true);
        } else{
            jsonResult.setResult(false);
            jsonResult.setErrMsg("新建失败");
        }

        return jsonResult.toString();
    }


    /**
     * 完成订单
     * @param id
     * @param status
     * @param bak
     * @return
     */
    @RequestMapping(value = "/comOrder", method = {RequestMethod.POST}, produces = {"application/json;charset=UTF-8"})
    public @ResponseBody String completeOrder(@RequestParam(value = "id") Integer id,
                                              @RequestParam(value = "status") Integer status,
                                              @RequestParam(value = "bak") String bak){
        TProductOrder tProductOrder = new TProductOrder();
        tProductOrder.setId(id);
        tProductOrder.setOrderStatus(status);
        tProductOrder.setBak(bak);
        tProductOrder.setUpdateTime(new Date(System.currentTimeMillis()));
        int res = tProductOrderService.completeOrder(tProductOrder);
        JsonResult<TProductOrder> jsonResult = new JsonResult<TProductOrder>();
        if(res == 1){
            jsonResult.setResult(true);
            tProductOrder = tProductOrderService.getByID(tProductOrder.getId());
            jsonResult.setData(tProductOrder);
        } else{
            jsonResult.setResult(false);
            jsonResult.setErrMsg("数据库访问出错");
        }
        return jsonResult.toString();
    }


    /**
     * 转成生产计划
     * @param order
     * @param stDate
     * @param eDate
     * @param session
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/addPlan", method = {RequestMethod.POST}, produces = {"application/json;charset=UTF-8"})
    public @ResponseBody String addPlan(@RequestParam(value = "order") String order,
                                        @RequestParam(value = "planSt") String stDate,
                                        @RequestParam(value = "planEnd") String eDate,
                                        HttpSession session) throws ParseException {



        TUser tUser = (TUser) session.getAttribute("user");
        TProductOrder tProductOrder = JSONObject.parseObject(order, TProductOrder.class);
        //首先将传送过来的JSON字符串转换成java对象

        System.out.println(tProductOrder);

        //利用这个对象赋值
        TProductPlan tProductPlan = new TProductPlan();
        tProductPlan.setOrderId(tProductOrder.getId());
        tProductPlan.setProductId(tProductOrder.getProductId());
        tProductPlan.setPlanCount(tProductOrder.getProductCount());
        tProductPlan.setDeliveryDate(tProductOrder.getEndDate());
        tProductPlan.setFactoryId(1);
        tProductPlan.setCreateUserid(tUser.getId());
        tProductPlan.setCreateTime(new Date(System.currentTimeMillis()));


        tProductPlan.setPlanStartDate(simpleDateFormat.parse(stDate));
        tProductPlan.setPlanEndDate(simpleDateFormat.parse(eDate));

        int res = tProductOrderService.toPlan(tProductPlan, tProductOrder);
        JsonResult jsonResult = new JsonResult();
        if(res == 1){
            jsonResult.setResult(true);
        } else{
            jsonResult.setResult(false);
            jsonResult.setErrMsg("新建计划失败");
        }
        return jsonResult.toString();
    }

    /**
     * 查看订单方法
     * @param model
     * @param orderId
     * @return
     */
    @RequestMapping("/checkOrder")
    public String checkOrder(Model model,
                             @RequestParam(value = "orderId") Integer orderId){
        System.out.println("orderId:" + orderId);
        TProductOrder tProductOrder = tProductOrderService.getByID(orderId);
        System.out.println("bak" + tProductOrder.getBak());
        model.addAttribute("order", tProductOrder);
        if(tProductOrder.getOrderStatus() == 4 || tProductOrder.getOrderStatus() == 5){
            HashMap<String,Object> resultMap = tProductOrderService.checkOrder(tProductOrder);
            model.addAttribute("plan", resultMap.get("plan"));
            model.addAttribute("schedules", resultMap.get("schedules"));
            model.addAttribute("dailyWorks", resultMap.get("dailyWorks"));
            System.out.println(resultMap.get("dailyWorks").toString());
        }
        return "orderCheck";

    }

    @RequestMapping(value = "/getYields", method = {RequestMethod.GET}, produces = {"application/json;charset=UTF-8"})
    public @ResponseBody String getYield(HttpSession session){
        ArrayList<ProductYield> productYields = new ArrayList<>();
        TUser tUser = (TUser) session.getAttribute("user");
        productYields = tProductOrderService.getYields(1);
        JsonResult< ArrayList<ProductYield> > jsonResult = new JsonResult<>();
        jsonResult.setResult(true);
        jsonResult.setData(productYields);
        return jsonResult.toString();
    }


    /**
     * 拒绝订单
     * @param id
     * @param status
     * @param bak
     * @param session
     * @return
     */
    @RequestMapping("/Reorder")
    public @ResponseBody String Reorder(@RequestParam(value = "id") Integer id,
                                        @RequestParam(value = "status") Integer status,
                                        @RequestParam(value = "bak") String bak,
                                        HttpSession session){
        TProductOrder tProductOrder = new TProductOrder();

        TUser tUser = (TUser) session.getAttribute("user");

        tProductOrder.setId(id);
        tProductOrder.setOrderStatus(2);
        tProductOrder.setBak(bak);
        tProductOrder.setUpdateUserid(tUser.getId());
        tProductOrder.setUpdateTime(new Date(System.currentTimeMillis()));

        int res = tProductOrderService.orderStatus(tProductOrder);
        JsonResult<TProductOrder> jsonResult = new JsonResult<TProductOrder>();

        if(res == 1){
            jsonResult.setResult(true);
            tProductOrder = tProductOrderService.getByID(tProductOrder.getId());
            jsonResult.setData(tProductOrder);
        } else{
            jsonResult.setResult(false);
            jsonResult.setErrMsg("数据库访问出错");
        }
        return jsonResult.toString();
    }

}
