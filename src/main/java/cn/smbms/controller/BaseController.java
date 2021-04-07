package cn.smbms.controller;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <h3>项目名称:smbms3</h3>
 * <p>本API描述:使用@InitBinder解决SpringMVC日期类型无法绑定的问题</p>
 * <p>@author : LiZhangyong</p>
 * <p>@date : 2021-04-06 05:07</p>
 **/
public class BaseController {
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));

    }
}
