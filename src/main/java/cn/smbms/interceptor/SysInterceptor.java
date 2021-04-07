package cn.smbms.interceptor;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <h3>项目名称:smbms3</h3>
 * <p>本API描述:</p>
 * <p>@author : LiZhangyong</p>
 * <p>@date : 2021-04-06 05:13</p>
 **/
public class SysInterceptor extends HandlerInterceptorAdapter {
    private Logger logger = Logger.getLogger(SysInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        logger.debug("SysInterceptor preHandle ==========================");
        Object userSession = request.getSession().getAttribute("userSession");
        System.out.println("userSession = " + userSession);
        if(userSession==null){
            response.sendRedirect(request.getContextPath()+"/error.jsp");
            return false;
        }else{
            return true;
        }

    }
}
