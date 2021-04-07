package cn.smbms.controller;

import cn.smbms.pojo.User;
import cn.smbms.service.user.UserService;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * <h3>项目名称:smbms3</h3>
 * <p>本API描述:</p>
 * <p>@author : LiZhangyong</p>
 * <p>@date : 2021-04-06 17:17</p>
 **/
@Controller
@RequestMapping(value = "/test3")
public class Test3Controller {

    @Resource
    private UserService userService;

    @RequestMapping(value = "validUser.html",method = RequestMethod.POST)
    @ResponseBody
    public Object validUserExist(String userCode){
        User user = userService.selectUserCodeExist(userCode);
        Map<String,String> map=new HashMap<>();
        if(user!=null){
            map.put("userCode", "exist");
        }else{
            map.put("userCode", "noExist");
        }
        return JSON.toJSONString(map);
    }


}
