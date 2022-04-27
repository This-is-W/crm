package com.packet.crm.settings.web.controller;

import com.packet.crm.settings.domain.User;
import com.packet.crm.settings.service.UserService;
import com.packet.crm.settings.service.impl.UserServiceImpl;
import com.packet.crm.utils.MD5Util;
import com.packet.crm.utils.PrintJson;
import com.packet.crm.utils.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = request.getServletPath();
        if ("/settings/user/login.do".equals(path)){

            login(request,response);

        }
}

    private void login(HttpServletRequest request, HttpServletResponse response) {

        String loginAct = request.getParameter("loginAct");
        String loginPwd = request.getParameter("loginPwd");

        loginPwd = MD5Util.getMD5(loginPwd);

        String ip = request.getRemoteAddr();
        System.out.println("我的IP地址是"+ip);

        //动态代理
        UserService us = (UserService) ServiceFactory.getService(new UserServiceImpl());
        try {

            User user = us.login(loginAct,loginPwd,ip);

            request.getSession().setAttribute("user",user);

            PrintJson.printJsonFlag(response,true);

        }catch (Exception e){

            e.printStackTrace();

            String msg = e.getMessage();

            Map<String,Object>map = new HashMap<String,Object>();
            map.put("success",false);
            map.put("msg",msg);
            PrintJson.printJsonObj(response,map);

        }
    }
    }
