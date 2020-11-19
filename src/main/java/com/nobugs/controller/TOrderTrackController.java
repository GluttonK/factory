package com.nobugs.controller;

import com.alibaba.fastjson.JSONObject;
import com.nobugs.entity.*;
import com.nobugs.service.TOrderTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/orderTrack")
public class TOrderTrackController {

    @Autowired
    private TOrderTrackService tOrderTrackService;

    //分页
    @RequestMapping("/getallByPage")
    public @ResponseBody
    String getJsonPage(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "5") Integer limit,
                       @RequestParam(required = false) String key){
        //1
        Page<TOrderTrack> pages = new Page<TOrderTrack>(page, limit);
        pages.setKey(key==""||key==null?null:key);
        //2调用业务逻辑层 获取取数据
        ArrayList<TOrderTrack> ordertracks=tOrderTrackService.getByPage(pages);

        //将数据封装
        HashMap<String,Object> resultMap=new HashMap<>();
        resultMap.put("pages",pages);

        resultMap.put("ordertracks",ordertracks);

        return JSONObject.toJSONString(resultMap);
    }

    /**
     * 查询和正常获取数据都是使用这个方法。正常获取数据将相当于没有条件的查询
     * @param scheduleSeq
     * @param scheduleStatus
     * @param productName
     * @param equipmentSeq
     * @param page
     * @param limit
     * @param key
     * @return
     */
    @RequestMapping("/searchTrackByPage")
    public @ResponseBody String searchPage(@RequestParam(value = "scheduleSeq") String scheduleSeq,
                                           @RequestParam(value = "scheduleStatus") Integer scheduleStatus,
                                           @RequestParam(value = "productName") String productName,
                                           @RequestParam(value = "equipmentSeq") String equipmentSeq,
                                           @RequestParam(defaultValue = "1") Integer page,
                                           @RequestParam(defaultValue = "5") Integer limit,
                                           @RequestParam(required = false) String key){
        //1
        Page<TOrderTrack> pages = new Page<TOrderTrack>(page, limit);
        pages.setKey(key==""||key==null?null:key);

        TOrderTrack tOrderTrack = new TOrderTrack();

        //要用equals判断字符串是否相等，直接用 = 无效
        if(scheduleSeq != null && !scheduleSeq.equals("")) tOrderTrack.setScheduleSeq(scheduleSeq);
        if(scheduleStatus != null && !scheduleStatus.equals("")) tOrderTrack.setScheduleStatus(scheduleStatus);
        if(productName != null && !productName.equals("")) tOrderTrack.setProductName(productName);
        if(equipmentSeq != null && !equipmentSeq.equals("")) tOrderTrack.setEquipmentSeq(equipmentSeq);
        tOrderTrack.setFactoryId(1);
        //将查询参数封装成对象，放到page里面
        pages.setT(tOrderTrack);

        //2调用业务逻辑层 获取取数据
        ArrayList<TOrderTrack> orderTracks = tOrderTrackService.getByPage(pages);

        //将数据封装
        HashMap<String,Object> resultMap=new HashMap<>();
        resultMap.put("pages",pages);
        resultMap.put("orderTracks",orderTracks);

        return JSONObject.toJSONString(resultMap);
    }

    @RequestMapping("/searchTrackByPage1")
    public @ResponseBody String searchPage1(@RequestParam(value = "scheduleId") Integer scheduleId,
                                           @RequestParam(defaultValue = "1") Integer page,
                                           @RequestParam(defaultValue = "5") Integer limit,
                                           @RequestParam(required = false) String key){
        //1
        Page<TDailyWork> pages = new Page<TDailyWork>(page, limit);
        pages.setKey(key==""||key==null?null:key);

        TDailyWork tDailyWork = new TDailyWork();

        //要用equals判断字符串是否相等，直接用 = 无效
        tDailyWork.setScheduleId(scheduleId);
        //if(scheduleId != null && !scheduleId.equals("")) tDailyWork.setScheduleId(scheduleId);
        tDailyWork.setFactoryId(1);
        //将查询参数封装成对象，放到page里面
        pages.setT(tDailyWork);

        //2调用业务逻辑层 获取取数据
        ArrayList<TDailyWork> orderTracks = tOrderTrackService.getByPage1(pages);

        //将数据封装
        HashMap<String,Object> resultMap=new HashMap<>();
        resultMap.put("pages",pages);
        resultMap.put("orderTracks",orderTracks);

        return JSONObject.toJSONString(resultMap);
    }

    //删除
    @RequestMapping(value = "/delete")
    public @ResponseBody String delectorderTrack(@RequestParam(required = true) Integer id){
        int ordertrack=tOrderTrackService.deleteordertrack(id);
          return JSONObject.toJSONString(ordertrack);
    }

    //编辑
    @RequestMapping(value = "/updateTrack", method = {RequestMethod.POST}, produces = {"application/json;charset=UTF-8"})
    public @ResponseBody String updateTrack(@RequestParam(value = "updatetrack") String updatetrack,
                                            @RequestParam(value = "workingCount") Integer workingCount,
                                            @RequestParam(value = "qualifiedCount") Integer qualifiedCount,
                                            @RequestParam(value = "unqualifiedCout") Integer unqualifiedCout,
                                            @RequestParam(value = "updatesTime") String updatesTime,
                                            @RequestParam(value = "updateeTime") String updateeTime,
                                            @RequestParam(value = "bak") String bak) throws ParseException {


        TOrderTrack tOrderTrack0 = JSONObject.parseObject(updatetrack, TOrderTrack.class);
        //首先将传送过来的JSON字符串转换成java对象

//        System.out.println(tProductOrder);
//        System.out.println("nbbbb");

        //利用这个对象赋值
        TDailyWork tDailyWork = new TDailyWork();
        tDailyWork.setId(tOrderTrack0.getId());
        tDailyWork.setWorkingCount(workingCount);
        tDailyWork.setQualifiedCount(qualifiedCount);
        tDailyWork.setUnqualifiedCout(unqualifiedCout);
        tDailyWork.setBak(bak);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        tDailyWork.setStartTime(simpleDateFormat.parse(updatesTime));
        tDailyWork.setEndTime(simpleDateFormat.parse(updateeTime));
        System.out.println(updatesTime);
        System.out.println(updateeTime);

        System.out.println(tDailyWork);
        int res = tOrderTrackService.updateOrderTrack(tDailyWork);
        JsonResult jsonResult = new JsonResult();
        if(res == 1){
            jsonResult.setResult(true);
        } else{
            jsonResult.setResult(false);
            jsonResult.setErrMsg("编辑失败");
        }
        return jsonResult.toString();
    }

    //新增报工
    @RequestMapping(value = "/addTrack", method = {RequestMethod.POST}, produces = {"application/json;charset=UTF-8"})
    public @ResponseBody String addTrack(@RequestParam(value = "scheduleId") Integer scheduleId,
                                            @RequestParam(value = "workingCount") Integer workingCount,
                                            @RequestParam(value = "qualifiedCount") Integer qualifiedCount,
                                            @RequestParam(value = "unqualifiedCout") Integer unqualifiedCout,
                                            @RequestParam(value = "startTime") String startTime,
                                            @RequestParam(value = "endTime") String endTime,
                                            @RequestParam(value = "bak") String bak) throws ParseException {


//        TOrderTrack tOrderTrack0 = JSONObject.parseObject(updatetrack, TOrderTrack.class);
        //首先将传送过来的JSON字符串转换成java对象

//        System.out.println(tProductOrder);
//        System.out.println("nbbbb");

        //利用这个对象赋值
        TDailyWork tDailyWork = new TDailyWork();
//        tDailyWork.setId(tOrderTrack0.getId());
        tDailyWork.setFactoryId(1);
        tDailyWork.setScheduleId(scheduleId);
        tDailyWork.setWorkingCount(workingCount);
        tDailyWork.setQualifiedCount(qualifiedCount);
        tDailyWork.setUnqualifiedCout(unqualifiedCout);
        tDailyWork.setBak(bak);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        tDailyWork.setStartTime(simpleDateFormat.parse(startTime));
        tDailyWork.setEndTime(simpleDateFormat.parse(endTime));

        System.out.println(tDailyWork);
        int res = tOrderTrackService.addOrderTrack(tDailyWork);
        JsonResult jsonResult = new JsonResult();
        if(res == 1){
            jsonResult.setResult(true);
        } else{
            jsonResult.setResult(false);
            jsonResult.setErrMsg("新增失败");
        }
        return jsonResult.toString();
    }

    //修改状态
    @RequestMapping(value = "/updateStatus", method = {RequestMethod.POST}, produces = {"application/json;charset=UTF-8"})
    public @ResponseBody String updateStatus(@RequestParam(value = "scheduleId") Integer scheduleId) throws ParseException {
        TProductSchedule tProductSchedule = new TProductSchedule();

        tProductSchedule.setId(scheduleId);
        int res = tOrderTrackService.updateOrderTrackStatus(tProductSchedule);
        JsonResult jsonResult = new JsonResult();
        if(res == 1){
            jsonResult.setResult(true);
        } else{
            jsonResult.setResult(false);
            //jsonResult.setErrMsg("编辑失败");
        }
        return jsonResult.toString();
    }

//    @RequestMapping(value = "/getscheduleSeqs", produces = {"application/json;charset=UTF-8"})
//    public @ResponseBody String getscheduleSeqs(){
//        ArrayList<TProductSchedule> scheduleSeqs = new ArrayList<>();
//        scheduleSeqs = tOrderTrackService.getScheduleSeqs();
//        JsonResult< ArrayList<TProductSchedule> > jsonResult = new JsonResult< ArrayList<TProductSchedule> >();
//        jsonResult.setResult(true);
//        jsonResult.setData(scheduleSeqs);
//        return jsonResult.toString();
//    }
}
