package cn.smbms.controller;

import cn.smbms.pojo.Role;
import cn.smbms.pojo.User;
import cn.smbms.service.role.RoleService;
import cn.smbms.service.user.UserService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <h3>项目名称:smbms3</h3>
 * <p>本API描述:</p>
 * <p>@author : LiZhangyong</p>
 * <p>@date : 2021-04-07 08:35</p>
 **/
@Controller
@RequestMapping(value = "/t0407")
public class T0407Controller {

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @RequestMapping(value = "showUserById/{id}",
            method = {RequestMethod.GET},
            produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public Object showUserById(@PathVariable String id){
        User user = userService.getUserById(id);
        return JSON.toJSONString(user);
    }

    @RequestMapping(value = "login.html")
    @ResponseBody
    public String login(User user, HttpSession session){
        System.out.println("****user = " + user);
        User userLogin = userService.login(user.getUserCode(), user.getUserPassword());
        System.out.println("****userLogin = " + userLogin);
        if(userLogin!=null){
            session.setAttribute("session_userLogin", userLogin);
            return "ok";
        }else{
            return "ko";
        }
    }

    @RequestMapping(value = "modifyPwd.html")
    @ResponseBody
    public String modifyPwd(@RequestParam("id") String id,
                            @RequestParam("userPassword2") String userPassword){
        boolean b = userService.updatePwd(Integer.parseInt(id), userPassword);
        if(b){
            return "ok";
        }else{
            return "ko";
        }
    }

    @RequestMapping(value = "getRoleList.html")
    @ResponseBody
    public Object getRoleList(){

        List<Role> roleList = roleService.getRoleList();

        return JSONArray.toJSONString(roleList);
    }


}
