package cn.smbms.controller;

import cn.smbms.pojo.User;
import cn.smbms.service.user.UserService;
import cn.smbms.service.user.UserServiceImpl;
import cn.smbms.tools.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

/**
 * <h3>项目名称:smbms3</h3>
 * <p>本API描述:</p>
 * <p>@author : LiZhangyong</p>
 * <p>@date : 2021-04-02 15:43</p>
 **/
@Controller
public class LoginController {

    @Autowired
    private UserService userService ;

    public void login(HttpServletRequest request,
                      HttpServletResponse response) throws IOException, ServletException {
        System.out.println("login ============ " );
        //获取用户名和密码
        String userCode = request.getParameter("userCode");
        String userPassword = request.getParameter("userPassword");
        //调用service方法，进行用户匹配

        User user = userService.login(userCode,userPassword);
        if(null != user){//登录成功
            //放入session
            request.getSession().setAttribute(Constants.USER_SESSION, user);
            //页面跳转（frame.jsp）
            response.sendRedirect("jsp/frame.jsp");
        }else{
            //页面跳转（login.jsp）带出提示信息--转发
            request.setAttribute("error", "用户名或密码不正确");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    @RequestMapping(value = "/login.do",
            method = {RequestMethod.POST,RequestMethod.GET},
            params = {"userCode"})
    public String login2(String userCode,
                         @RequestParam String userPassword,
                         HttpSession session,
                         Model model,
                         HttpServletRequest request){

        /*Enumeration<String> headerNames = request.getHeaderNames();
        System.out.println(headerNames);*/

        User user = userService.login(userCode,userPassword);
        if(user!=null){
            //放入session
            session.setAttribute(Constants.USER_SESSION, user);
            //throw new RuntimeException("异常啦");
            //页面跳转（frame.jsp）
           return "redirect:jsp/frame.jsp";
        }else{
            //页面跳转（login.jsp）带出提示信息--转发
            model.addAttribute("error", "用户名或密码不正确");
            return "login";
        }
    }


    /*@ExceptionHandler({RuntimeException.class})
    public String exceptionHandle(RuntimeException e,
                                  HttpServletRequest request){
        request.setAttribute("exception", e);
        return "error";
    }*/



}
