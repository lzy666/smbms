package cn.smbms.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <h3>项目名称:smbms3</h3>
 * <p>本API描述:</p>
 * <p>@author : LiZhangyong</p>
 * <p>@date : 2021-04-04 08:57</p>
 **/
public class TestBeanController extends AbstractController {


    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
                                                 HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView("error");

        return mv;
    }

    /*@Override
    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView("error");
        return mv;
    }*/



}
