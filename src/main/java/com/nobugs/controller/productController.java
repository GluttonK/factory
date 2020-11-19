package com.nobugs.controller;

import com.alibaba.fastjson.JSONObject;
import com.nobugs.entity.JsonResult;
import com.nobugs.entity.Page;
import com.nobugs.entity.TProduct;
import com.nobugs.entity.TProductOrder;
import com.nobugs.service.TProductService;
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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value ="/product",produces = {"application/json;charset=UTF-8"})
public class productController {
    private Date date=new Date();
    private SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMdd");

    @Autowired
   private TProductService tProductService;

    @RequestMapping(value = "/justgetAll", method = {RequestMethod.GET})
    public  @ResponseBody String getAllProduct(){
        List<TProduct> tProducts= tProductService.getProduct();
        JsonResult<List<TProduct>> jsonResult = new JsonResult<List<TProduct>>();
        jsonResult.setData(tProducts);
        jsonResult.setResult(true);
        return jsonResult.toString();
      }
      @RequestMapping("/updateById")
      public  @ResponseBody void updateById(@RequestParam(value = "productname") String productname,
                                       @RequestParam(value = "productImgUrl") String productImgUrl,
                                       @RequestParam(value = "productId") int productId     ){
           TProduct tProduct=new TProduct();
           tProduct.setProductName(productname);
           tProduct.setProductImgUrl(productImgUrl);
           tProduct.setId(productId);
           tProductService.updateProductById(tProduct);

      }
    @RequestMapping("/getAllProductByPage")
    public @ResponseBody String getJsonPage(@RequestParam(defaultValue = "1") Integer page,
                                            @RequestParam(defaultValue = "5") Integer limit,
                                            @RequestParam(required = false) String key){
        //1
        Page<TProduct> pages = new Page<TProduct>(page, limit);
        pages.setKey(key==""||key==null?null:key);
        //2调用业务逻辑层 获取取数据
        ArrayList<TProduct> products=tProductService.getAllByPage(pages);

        //将数据封装
        HashMap<String,Object> resultMap=new HashMap<>();
        resultMap.put("pages",pages);
        resultMap.put("products",products);

        return JSONObject.toJSONString(resultMap);
    }
    @RequestMapping(value = "/getAllByName", method = {RequestMethod.POST})
    public  @ResponseBody String getAllProductByName(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer limit,
            @RequestParam(required = false) String key,
            @RequestParam(value = "productname") String productname
    ){
        Page<TProduct> pages = new Page<TProduct>(page, limit);
        pages.setKey(key==""||key==null?null:key);
        TProduct tProduct=new TProduct();
        tProduct.setProductName(productname);
        pages.setT(tProduct);
        ArrayList<TProduct> tProducts= tProductService.getProduct(pages);
        HashMap<String,Object> resultMap=new HashMap<>();
        resultMap.put("pages",pages);
        resultMap.put("products",tProducts);
        System.out.println(productname);
        return JSONObject.toJSONString(resultMap);
    }
    @RequestMapping("/deleteByProductId")
     public @ResponseBody void deleteByProductId(@RequestParam(required = true) int productid){
        tProductService.deleteByProductId(productid);
    }
    @RequestMapping("/insert")
    public  @ResponseBody void InsertProduct(
                                             @RequestParam(required = true) String uploadProductName,
                                             @RequestParam(required = true) String url){
         TProduct tProduct=new TProduct();
         tProduct.setFactoryId(1);
         tProduct.setProductImgUrl(url);
         tProduct.setProductName(uploadProductName);
         tProduct.setProductNum("S"+ TimeStamp.getTimeStamp());
         tProduct.setFlag(0);
        tProductService.Insertfour(tProduct);


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
