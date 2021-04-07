package cn.smbms.controller;

import cn.smbms.pojo.User;
import cn.smbms.service.user.UserService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <h3>项目名称:smbms3</h3>
 * <p>本API描述:</p>
 * <p>@author : LiZhangyong</p>
 * <p>@date : 2021-04-04 17:08</p>
 **/
@Controller
@RequestMapping(value = "/test")
public class TestController extends BaseController{

    @Autowired
    private UserService userService;

    @RequestMapping(value = "test1.html")
    public String test1(@ModelAttribute(value = "user") User user){
        return "test/mv";
    }

    @RequestMapping(value = "userAdd.html",method = {RequestMethod.GET})
    public String showUser(Model model){

        User user = new User();
        user.setUserName("张三wsx");
        model.addAttribute("user", user);
        return "jsp/user/userAdd";
    }


    @RequestMapping(value = "userAdd.html",method = {RequestMethod.POST})
    public String userAdd(@Valid User user,
                          BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "jsp/user/userAdd";
        }
        return "jsp/user/userInfo";
    }

    @RequestMapping(value = "userAdd2.html",method = {RequestMethod.GET})
    public String showUser2(Model model){

        User user = new User();
        user.setUserName("张三wsx");
        model.addAttribute("user", user);
        return "jsp/user/userAdd2";
    }

    @RequestMapping(value = "userAdd22.html",
            method = {RequestMethod.POST})
    public String userAdd22(User user,
                          HttpServletRequest request,
                          @RequestParam(value = "a_idPicPaht",required = false) MultipartFile attach){

        String idPicPath=null;
        String  uploadPath="statics" + "/" + "upload";
        if(!attach.isEmpty()){
            String path = request.getServletContext().getRealPath(uploadPath);
            File dirFile = new File(path);
            if(!dirFile.exists()){
                dirFile.mkdirs();
            }
            String oldFilename = attach.getOriginalFilename();
            String suffix = FilenameUtils.getExtension(oldFilename);
            final int fileSize=500000;
            if(attach.getSize()>fileSize){
                request.setAttribute("uploadFileError", "文件上传大小不得超过500KB");
                return "jsp/user/userAdd2";
            }else if(suffix.equalsIgnoreCase("jpg") || suffix.equalsIgnoreCase("jpeg")
                    || suffix.equalsIgnoreCase("png")
                    || suffix.equalsIgnoreCase("pneg")){
                String fileName = System.currentTimeMillis() +
                        RandomUtils.nextInt(1000000) + "_Person." + suffix;
                File targetFile = new File(dirFile, fileName);
                try {
                    attach.transferTo(targetFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                idPicPath=uploadPath+"/"+fileName;
            }else{
                request.setAttribute("uploadFileError",
                        "文件上传格式不正确");
            }
        }
        user.setIdPicPath(idPicPath);
        System.out.println("***user = " + user);
        if(userService.add(user)){
            return "jsp/user/userInfo";
        }else{
            return "jsp/user/userAdd2";
        }
    }

    @RequestMapping(value = "userAdd23.html",
            method = {RequestMethod.POST})
    public String userAdd23(@Valid User user,
                           BindingResult bindingResult,
                           HttpServletRequest request,
                           @RequestParam(value = "attaches",required = false) MultipartFile[] attaches){
        if(bindingResult.hasErrors()){
            return "jsp/user/userAdd2";
        }

        String idPicPath = null;
        String workPicPath = null;
        String errorInfo = null;


        String  uploadPath="statics" + "/" + "upload";
        String path = request.getServletContext().getRealPath(uploadPath);
        File dirFile = new File(path);
        if(!dirFile.exists()){
            dirFile.mkdirs();
        }
        if(attaches!=null && attaches.length>0){
            for(int i=0;i<attaches.length;i++){
                MultipartFile attach = attaches[i];
                if(!attach.isEmpty()){
                    if(i==0){
                        errorInfo="uploadFileError";
                    }else if(i==1){
                        errorInfo="uploadWkError";
                    }
                    String oldFilename = attach.getOriginalFilename();
                    String suffix = FilenameUtils.getExtension(oldFilename);
                    final int fileSize=500000;
                    if(attach.getSize()>fileSize){
                        request.setAttribute(errorInfo, "文件上传大小不得超过500KB");
                        return "jsp/user/userAdd2";
                    }else if(suffix.equalsIgnoreCase("jpg")
                            || suffix.equalsIgnoreCase("jpeg")
                            || suffix.equalsIgnoreCase("png")
                            || suffix.equalsIgnoreCase("pneg")){
                        String fileName = System.currentTimeMillis() +
                                RandomUtils.nextInt(1000000) + "_Person." + suffix;
                        File targetFile = new File(dirFile, fileName);
                        try {
                            attach.transferTo(targetFile);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if(i==0){
                            idPicPath=uploadPath+"/"+fileName;
                        }else if(i==1){
                            workPicPath=uploadPath+"/"+fileName;
                        }
                    }else{
                        request.setAttribute(errorInfo,
                                "文件上传格式不正确");
                    }
                }
            }
        }
        user.setIdPicPath(idPicPath);
        user.setWorkPicPath(workPicPath);

        if(userService.add(user)){
            return "jsp/user/userInfo";
        }else{
            return "jsp/user/userAdd2";
        }
    }

    @RequestMapping(value = "userAdd3.html",method = {RequestMethod.GET})
    public String showUser3(Model model){

        User user = new User();
        user.setUserName("张三wsx");
        model.addAttribute("user", user);
        return "jsp/user/userAdd3";
    }

    @RequestMapping(value = "userAdd3.html",
            method = {RequestMethod.POST})
    public String userAdd3(@Valid User user,
                            BindingResult bindingResult,
                            HttpServletRequest request,
                            @RequestParam(value = "attaches",required = false) MultipartFile[] attaches){
        if(bindingResult.hasErrors()){
            return "jsp/user/userAdd3";
        }

        String idPicPath = null;
        String workPicPath = null;
        String errorInfo = null;

        String  uploadPath="statics" + "/" + "upload";
        String path = request.getServletContext().getRealPath(uploadPath);
        File dirFile = new File(path);
        if(!dirFile.exists()){
            dirFile.mkdirs();
        }
        if(attaches!=null && attaches.length>0){
            for(int i=0;i<attaches.length;i++){
                MultipartFile attach = attaches[i];
                if(!attach.isEmpty()){
                    if(i==0){
                        errorInfo="uploadFileError";
                    }else if(i==1){
                        errorInfo="uploadWkError";
                    }
                    String oldFilename = attach.getOriginalFilename();
                    String suffix = FilenameUtils.getExtension(oldFilename);
                    final int fileSize=500000;
                    if(attach.getSize()>fileSize){
                        request.setAttribute(errorInfo, "文件上传大小不得超过500KB");
                        return "jsp/user/userAdd3";
                    }else if(suffix.equalsIgnoreCase("jpg")
                            || suffix.equalsIgnoreCase("jpeg")
                            || suffix.equalsIgnoreCase("png")
                            || suffix.equalsIgnoreCase("pneg")){
                        String fileName = System.currentTimeMillis() +
                                RandomUtils.nextInt(1000000) + "_Person." + suffix;
                        File targetFile = new File(dirFile, fileName);
                        try {
                            attach.transferTo(targetFile);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if(i==0){
                            idPicPath=uploadPath+"/"+fileName;
                        }else if(i==1){
                            workPicPath=uploadPath+"/"+fileName;
                        }
                    }else{
                        request.setAttribute(errorInfo,
                                "文件上传格式不正确");
                    }
                }
            }
        }
        user.setIdPicPath(idPicPath);
        user.setWorkPicPath(workPicPath);

        if(userService.add(user)){
            return "jsp/user/userInfo";
        }else{
            return "jsp/user/userAdd3";
        }
    }


    @RequestMapping(value = "index.html",method = RequestMethod.GET)
    public String index(){
        return "jsp/user/index";
    }

    @RequestMapping(value = "view/{id}")
    public String view(@PathVariable String id,Model model){

        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "jsp/user/userInfo";
    }

    @RequestMapping(value = "userCodeExists.html",method = RequestMethod.POST)
    @ResponseBody
    public Object userCodeExists(@RequestParam String userCode){
        User user = userService.selectUserCodeExist(userCode);
        Map<String,String> map=new HashMap<>();
        if(user!=null){
            map.put("userCode", "exist");
        }else{
            map.put("userCode", "noExist");
        }
        //return JSONArray.toJSONString(map);
        return JSON.toJSONString(map);
    }

    @RequestMapping(value = "showInfo/{id}",
            method = RequestMethod.GET,
            produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public Object showInfo(@PathVariable("id") String id){

        User user = userService.getUserById(id);

        return JSON.toJSONString(user);
    }

    @RequestMapping(value = "showInfo.json")
    @ResponseBody
    public Object showInfoJson(String id){
        User user = userService.getUserById(id);
        return JSON.toJSONString(user);
    }

    @RequestMapping(value = "showInfo.html")
    @ResponseBody
    public Object showInfoHtml(String id){
        User user = userService.getUserById(id);
        return JSON.toJSONString(user);
    }

}
