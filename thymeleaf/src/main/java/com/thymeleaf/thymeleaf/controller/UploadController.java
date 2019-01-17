package com.thymeleaf.thymeleaf.controller;

import com.alibaba.fastjson.JSONObject;
import com.thymeleaf.thymeleaf.utils.FileUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 文件上传页面
 */
@Controller
public class UploadController {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
    String formatStr =formatter.format(new Date());
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public @ResponseBody JSONObject upload(MultipartFile file, ServletRequest request){
        JSONObject jsonObject = new JSONObject();
        try{
            if(file.isEmpty()){
                jsonObject.put("result",-1);
            }
            //后期可以加用户在时间前面,文件名称需要用GUID来生成
            StringBuilder filePathSB = new StringBuilder();
            filePathSB.append("../testFile/").append(formatStr).append("/").append(file.getOriginalFilename());
            File filePath = new File(filePathSB.toString());
            FileUtils.writeByteArrayToFile(filePath,file.getBytes());
            boolean isReadyImage = FileUtil.imgCheck(filePathSB.toString());
            if(!isReadyImage){
                //如果是非图片，则删除
                filePath.deleteOnExit();
                jsonObject.put("result",-1);
                jsonObject.put("message","请上传正确的图片文件!");
            }else{
                jsonObject.put("result",1);
                jsonObject.put("message","ok");
            }
        }catch (Exception e){
            jsonObject.put("result",0);
            jsonObject.put("message","系统错误，请联系管理员！");
            jsonObject.put("excetionInfo",e.getCause().getMessage());
        }
        return jsonObject;

    }
    @RequestMapping(value = "/checkImg",method = RequestMethod.POST)
    public @ResponseBody JSONObject checkImg(@RequestParam(value = "mFile",required = false)MultipartFile file){
        JSONObject result = new JSONObject();
        try{
            boolean isReadyImage = FileUtil.imgCheck(FileUtil.multipartFileToFile(file));
            if (isReadyImage){
                result.put("result",0);
                result.put("message","校验通过");
            }else{
                result.put("result",-1);
                result.put("message","校验失败,请选择正确的图片文件!");
            }
        }catch (Exception e){
            e.printStackTrace();
            result.put("result",0);
            result.put("message","系统错误,请联系系统管理员！");
            result.put("exceptionInfo",e.getCause().getMessage());
        }
        return result;
    }

    // 判断文件夹是否存在
    public void judeDirExists(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                System.out.println("dir exists");
            } else {
                System.out.println("the same name file exists, can not create dir");
            }
        } else {
            System.out.println("dir not exists, create it ...");
            file.mkdir();
        }
    }
}
