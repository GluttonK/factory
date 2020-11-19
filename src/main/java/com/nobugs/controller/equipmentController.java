package com.nobugs.controller;

import com.alibaba.fastjson.JSONObject;
import com.nobugs.entity.*;
import com.nobugs.service.TEquipmentProductService;
import com.nobugs.service.TEquipmentService;
import com.nobugs.util.TimeStamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value ="/equipment",produces = {"application/json;charset=UTF-8"})
public class equipmentController {
    private Date date=new Date();
    private SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMdd");
    @Autowired
    private TEquipmentService tEquipmentService;
    @Autowired
    private TEquipmentProductService tEquipmentProductService;

    @RequestMapping(value = "/justgetAll", method = {RequestMethod.GET})
    public  @ResponseBody
    String getAllProduct(){
        List<TEquipment> tEquipments= tEquipmentService.getEquipment();
        JsonResult<List<TEquipment>> jsonResult = new JsonResult<List<TEquipment>>();
        jsonResult.setData(tEquipments);
        jsonResult.setResult(true);
        return jsonResult.toString();
    }
    @RequestMapping("/updateById")
    public  @ResponseBody void updateById( @RequestParam(required = true) int productid,
                                           @RequestParam(required = true) String yield,
                                           @RequestParam(required = true) int unit,
                                           @RequestParam(required = true) String uploadEquipmentSeq,
                                           @RequestParam(required = true) String url,
                                           @RequestParam(required = true) int status){
        TEquipment tEquipment=new TEquipment();
        tEquipment.setEquipmentSeq(uploadEquipmentSeq);
        tEquipment.setEquipmentImgUrl(url);
        tEquipment.setEquipmentStatus(status);
        int id =tEquipmentService.selectBySeq(uploadEquipmentSeq).getId();
        tEquipment.setId(id);
        tEquipmentService.updateEquipmentById(tEquipment);
        TEquipmentProduct tEquipmentProduct= new TEquipmentProduct();
        tEquipmentProduct.setEquipmentId(id);
        int yield1= Integer.parseInt(yield);
        tEquipmentProduct.setYield(yield1);
        tEquipmentProduct.setUnit(unit);
        tEquipmentProduct.setProductId(productid);
        tEquipmentProduct.setFactoryId(1);
        tEquipmentProductService.updatefive(tEquipmentProduct);


    }
    @RequestMapping("/updateStatusById")
    public  @ResponseBody void updateStatusById(
                                          @RequestParam(value = "equipmentStatus") int equipmentStatus,


                                          @RequestParam(value = "equipmentId") int equipmentId ){
        TEquipment tEquipment=new TEquipment();
        tEquipment.setEquipmentStatus(equipmentStatus);
        tEquipment.setId(equipmentId);

        tEquipmentService.updateStatusById(tEquipment);

    }
    @RequestMapping("/getAllEquipmentByPage")
    public @ResponseBody String getJsonPage(@RequestParam(defaultValue = "1") Integer page,
                                            @RequestParam(defaultValue = "5") Integer limit,
                                            @RequestParam(required = false) String key){
        //1
        Page<TEquipment> pages = new Page<TEquipment>(page, limit);
        pages.setKey(key==""||key==null?null:key);
        //2调用业务逻辑层 获取取数据
        ArrayList<TEquipment> equipments=tEquipmentService.getAllByPage(pages);

        //将数据封装
        HashMap<String,Object> resultMap=new HashMap<>();
        resultMap.put("pages",pages);
        resultMap.put("equipments",equipments);

        return JSONObject.toJSONString(resultMap);
    }
    @RequestMapping(value="/getProductByPage" )
    public @ResponseBody String getJsonPage2(@RequestParam(defaultValue = "1") Integer page,
                                            @RequestParam(defaultValue = "5") Integer limit,
                                            @RequestParam(required = false) String key,
                                             @RequestParam(value = "productname") String productname,
                                             @RequestParam(value = "equipmentSeq") String equipmentSeq){
        //1
        Page<TEquipment> pages = new Page<TEquipment>(page, limit);
        pages.setKey(key==""||key==null?null:key);
        TEquipment tEquipment=new TEquipment();
        tEquipment.setProductName(productname);
        tEquipment.setEquipmentSeq(equipmentSeq);
        pages.setT(tEquipment);
        ArrayList<TEquipment> tEquipments= tEquipmentService.selectProductsPage(pages);
        HashMap<String,Object> resultMap=new HashMap<>();
        resultMap.put("pages",pages);
        resultMap.put("equipments",tEquipments);
        System.out.println("123");
        return JSONObject.toJSONString(resultMap);
    }
    @RequestMapping("/getProductNameByPage")
    public @ResponseBody String getJsonPage1(@RequestParam(defaultValue = "1") Integer page,
                                            @RequestParam(defaultValue = "5") Integer limit,
                                            @RequestParam(required = false) String key,
                                              @RequestParam(value = "productname") String productname,
                                            @RequestParam(value = "equipmentSeq") String equipmentSeq){
        Page<TEquipment> pages = new Page<TEquipment>(page, limit);
        pages.setKey(key==""||key==null?null:key);
        TEquipment tEquipment=new TEquipment();
        tEquipment.setProductName(productname);
        tEquipment.setEquipmentSeq(equipmentSeq);
        pages.setT(tEquipment);
        ArrayList<TEquipment> tEquipments= tEquipmentService.getProductName(pages);
        HashMap<String,Object> resultMap=new HashMap<>();
        resultMap.put("pages",pages);
        resultMap.put("equipments",tEquipments);
        System.out.println("123");
        return JSONObject.toJSONString(resultMap);
    }
    @RequestMapping(value = "/getAllBySeq", method = {RequestMethod.POST})
    public  @ResponseBody String getAllEquipmentByName(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer limit,
            @RequestParam(required = false) String key,
            @RequestParam(value = "equipmentSeq") String equipmentSeq
    ){
        Page<TEquipment> pages = new Page<TEquipment>(page, limit);
        pages.setKey(key==""||key==null?null:key);
        TEquipment tEquipment=new TEquipment();
        tEquipment.setEquipmentSeq(equipmentSeq);
        pages.setT(tEquipment);
        ArrayList<TEquipment> tEquipments= tEquipmentService.getEquipment(pages);
        HashMap<String,Object> resultMap=new HashMap<>();
        resultMap.put("pages",pages);
        resultMap.put("equipments",tEquipments);
        System.out.println("123");
        return JSONObject.toJSONString(resultMap);
    }
    @RequestMapping("/deleteByEquipmentId")
    public @ResponseBody void deleteByEquipmentId(@RequestParam(required = true) int equipmentid){
        tEquipmentService.deleteByEquipmentId(equipmentid);
        tEquipmentProductService.deleteByEquipmentId(equipmentid);
    }
    @RequestMapping("/insert")
    public  @ResponseBody void InsertProduct(
            @RequestParam(required = true) int productid,
            @RequestParam(required = true) String yield,
            @RequestParam(required = true) int unit,
            @RequestParam(required = true) String uploadEquipmentSeq,
            @RequestParam(required = true) String url,
            @RequestParam(required = true) int status){

        TEquipment tEquipment=new TEquipment();
        tEquipment.setFactoryId(1);
        tEquipment.setEquipmentImgUrl(url);
        tEquipment.setEquipmentSeq(uploadEquipmentSeq);
        tEquipment.setEquipmentStatus(status);
        tEquipment.setFlag(0);
        tEquipmentService.Insertfive(tEquipment);
        int id =tEquipmentService.selectBySeq(uploadEquipmentSeq).getId();
        TEquipmentProduct tEquipmentProduct= new TEquipmentProduct();
        tEquipmentProduct.setEquipmentId(id);
        int yield1= Integer.parseInt(yield);
        tEquipmentProduct.setYield(yield1);
        tEquipmentProduct.setUnit(unit);
        tEquipmentProduct.setProductId(productid);
        tEquipmentProduct.setFactoryId(1);
         tEquipmentProductService.Insertfive(tEquipmentProduct);


    }

    @RequestMapping(value = "/upload",method = {RequestMethod.POST})
    public @ResponseBody String upLoadFile(@RequestParam(value ="uploadFile") MultipartFile multipartFile, HttpServletRequest request) throws IOException {
        String oldFileName=multipartFile.getOriginalFilename();
        HashMap<String,String> resultMap=new HashMap<>();
        //判断上传的文件是否存在
        if(multipartFile==null || null==oldFileName && oldFileName.length()<=0){
            resultMap.put("result","false");
            resultMap.put("errMsg","上传文件不存在");
            return  JSONObject.toJSONString(resultMap);
        }

        String suffix=getSuffix(oldFileName);
        if(!issLegalSuffix(suffix)){
            resultMap.put("result","false");
            resultMap.put("errMsg","上传文件不合法!");
            return  JSONObject.toJSONString(resultMap);
        }

        //物理存储路径
        String rootPath= request.getServletContext().getRealPath("/");
        //组合子目录
        String savePath=rootPath+"upload\\"+simpleDateFormat.format(date)+"\\";
        //文件访问的相对路径
        String url="upload/"+simpleDateFormat.format(date)+"/";

        //判断目录是否已创建
        File mk=new File(savePath);
        if(!mk.exists())
        {
            //创建目录,如果父级目录不存在,则一起创建
            mk.mkdirs();
        }

        //获取新的文件名
        String newFileName=creatNewFileName(suffix);

        //实例化文件对象
        File newFile=new File(savePath+newFileName);
        //将文件写入磁盘
        multipartFile.transferTo(newFile);

        resultMap.put("result","true");
        resultMap.put("url",url+newFileName);
        return  JSONObject.toJSONString(resultMap);


    }


    /**
     * 一般使用时间戳做文件名
     * 组合文件名
     * @return
     */
    public String creatNewFileName(String suffix){
        return System.currentTimeMillis()+"."+suffix;
    }


    /**
     * 截取文件后缀
     * @param fileName
     * @return
     */
    public String getSuffix(String fileName){
        String suffix=fileName.substring( fileName.lastIndexOf(".")+1);
        return  suffix;
    }

    /**
     * 判断文件后缀是否合法
     * @param suffix 文件后缀
     * @return
     */
    public  boolean issLegalSuffix(String suffix){
        String[] legalSuffixs={"jpg","png","gif","bmp"};
        for (String suf:legalSuffixs) {
            if(suf.equalsIgnoreCase(suffix)){
                return true;
            }
        }
        return false;
    }
}
