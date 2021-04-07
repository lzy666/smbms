package cn.smbms.controller;

import cn.smbms.pojo.User;
import cn.smbms.service.user.UserService;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * <h3>项目名称:smbms3</h3>
 * <p>本API描述:</p>
 * <p>@author : LiZhangyong</p>
 * <p>@date : 2021-04-05 13:39</p>
 **/
@Controller
@RequestMapping("/test2")
public class Test2Controller {

    @Resource
    private UserService userService;

    @RequestMapping(value = "test1.html",method = {RequestMethod.POST})
    public String test1(User user){
        //可能产生异常的代码
        throw  new RuntimeException("发生异常啦");
        //return "test2/info";
    }

    @ExceptionHandler({RuntimeException.class})
    public String exceptionHandle(RuntimeException e,
                                  HttpServletRequest request){
        request.setAttribute("exception", e);
        return "test2/error2";
    }

    @RequestMapping(value = "login.html",method = RequestMethod.POST)
    public String login( User user,
                        HttpSession session){
        //省略service操作
        session.setAttribute("session_user", user);
        return "test2/info";
    }

    @RequestMapping(value = "logout.html",
            method = RequestMethod.GET)
    public String logout(HttpSession session){
        //清除session里的用户
        session.removeAttribute("session_user");
        return "test2/login";
    }

    @RequestMapping(value = "addUser.html",
            method = RequestMethod.GET)
    public String addUserJsp(Model model){
        User user = new User();
        user.setUserName("张三");
        user.setAddress("北京市");
        model.addAttribute("user", user);
        return "test2/userAdd";
    }

    @RequestMapping(value = "addUser.html",
            method = RequestMethod.POST)
    public String addUser(@Valid User user,
                          BindingResult bindingResult,
                          @RequestParam(value = "attaches",required = true) MultipartFile[] attaches,
                          HttpServletRequest request){
        //检查前端传来的参数为对象属性赋值时，是否符合校验规则
        if(bindingResult.hasErrors()){
            return "test2/userAdd";
        }
        //文件上传操作-保存路径。statics/upload/
        String path="statics/upload";
        //获取web项目里某个相对路径在电脑磁盘里的绝对路径
        String realPath = request.getServletContext().getRealPath(path);
        System.out.println("realPath = " + realPath);
        //用file对象关联上述文件夹的位置
        File dirFile = new File(realPath);
        //判断文件夹是否存在，若不存在就创建
        if(!dirFile.exists()){
            dirFile.mkdirs();
        }
        //处理上传后的多个文件
        if(attaches!=null && attaches.length>0){
            for(int i=0;i<attaches.length;i++){
                MultipartFile attach=attaches[i];
                //判断文件是否为空
                if(!attach.isEmpty()){
                    //先判断文件大小
                    if(attach.getSize()>5000000){
                        if(i==0){
                            request.setAttribute("idPicPathError", "证件照上传文件过大。。");
                        }else if(i==1){
                            request.setAttribute("workPicPathError", "工作照上传文件过大");
                        }
                        return "test2/userAdd";
                    }
                    //获取上传文件原来的 名称,以及图片扩展名
                    String originalFilename = attach.getOriginalFilename();
                    String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
                    //要求图片格式为jpg,jpeg,png,pneg
                    if(extension.equalsIgnoreCase("jpg")
                            ||extension.equalsIgnoreCase("jpeg")
                            ||extension.equalsIgnoreCase("png")
                            ||extension.equalsIgnoreCase("pneg")){
                        //生成新的文件名，避免重复及中文乱码
                        String fileName = System.currentTimeMillis() + RandomUtils.nextInt(10000) + "_user." + extension;
                        //创建File对象，关联上传后的文件
                        File destFile = new File(dirFile, fileName);
                        //调用文件上传的方法
                        try {
                            attach.transferTo(destFile);
                            //文件上传完毕，把文件路径存到用户对象里
                            if(i==0){
                                user.setIdPicPath(path+"/"+fileName);
                            }else if(i==1){
                                user.setWorkPicPath(path+"/"+fileName);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        user.setCreatedBy(1);
        user.setCreationDate(new Date());
        boolean flag = userService.add(user);
        if(!flag){
            throw new RuntimeException("用户添加失败");
        }else{
            return "test2/list";
        }
    }

    @RequestMapping(value = "updateUser.html",
            method = RequestMethod.GET)
    public String updateUser(@RequestParam String id,
                             Model model){
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "test2/updateUser";
    }

    @RequestMapping(value = "updateUser.html",
            method = RequestMethod.POST)
    @ResponseBody
    public Object updateUser(User user){
        boolean modify = userService.modify(user);
        if(modify){
            return "修改成功";
        }
        return "修改失败";
    }

    //REST风格
    @RequestMapping(value = "updateUser/{cid}",
            method = RequestMethod.GET)
    public String updateUser(Model model,
                            @PathVariable("cid") String id){
        System.out.println("****id = " + id);
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "test2/updateUser";
    }



}
