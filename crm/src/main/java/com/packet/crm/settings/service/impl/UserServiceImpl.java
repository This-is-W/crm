package com.packet.crm.settings.service.impl;

import com.packet.crm.settings.dao.UserDao;
import com.packet.crm.settings.domain.User;
import com.packet.crm.settings.service.UserService;
import com.packet.crm.utils.DateTimeUtil;
import com.packet.crm.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

import javax.security.auth.login.LoginException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {


    private UserDao userDao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);

    public User login(String loginAct,String loginPwd,String ip) throws LoginException {

        Map<String,String> map = new HashMap<String,String>();
        map.put("loginAct",loginAct);
        map.put("loginPwd",loginPwd);

        User user = userDao.login(map);

        if (user == null){

            throw new LoginException("账号密码错了");
        }

        String expireTime = user.getExpireTime();
        String currentTime = DateTimeUtil.getSysTime();
        if (expireTime.compareTo(currentTime)<0){

            throw new LoginException("账号已经过期了");
        }

        String lockState = user.getLockState();
        if ("0".equals(lockState)){

            throw new LoginException("账号已被锁住了");
        }

        String allowIps = user.getAllowIps();
        if (!allowIps.contains(ip)){

            throw new LoginException("ip地址受限");
        }

        return user;
    }

    @Override
    public List<User> getUserList() {

        List<User> uList =userDao.getUserList();

        return uList;
    }
}
