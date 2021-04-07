package cn.smbms.controller;

import cn.smbms.tools.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <h3>项目名称:smbms3</h3>
 * <p>本API描述:</p>
 * <p>@author : LiZhangyong</p>
 * <p>@date : 2021-04-02 15:43</p>
 **/
@Controller
@RequestMapping("/jsp")
public class LogoutController {

    @RequestMapping("logout.do")
    public void logout(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        //清除session
        request.getSession().removeAttribute(Constants.USER_SESSION);
        response.sendRedirect(request.getContextPath()+"/login.jsp");
    }


}
