package com.packet.crm.workbench.service.impl;

import com.packet.crm.settings.dao.UserDao;
import com.packet.crm.settings.domain.User;
import com.packet.crm.utils.SqlSessionUtil;
import com.packet.crm.vo.PaginationVo;
import com.packet.crm.workbench.dao.ActivityDao;
import com.packet.crm.workbench.dao.ActivityRemarkDao;
import com.packet.crm.workbench.domain.Activity;
import com.packet.crm.workbench.domain.ActivityRemark;
import com.packet.crm.workbench.service.ActivityService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityServiceImpl implements ActivityService {
    private ActivityDao activityDao = SqlSessionUtil.getSqlSession().getMapper(ActivityDao.class);
    private ActivityRemarkDao activityRemarkDao = SqlSessionUtil.getSqlSession().getMapper(ActivityRemarkDao.class);
    private UserDao userDao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);

    public boolean save(Activity a) {

        boolean flag = true;

        int count = activityDao.save(a);
        if (count != 1){

            flag = false;

        }

        return flag;
    }

    public PaginationVo<Activity> pageList(Map<String, Object> map) {

        int total = activityDao.getTotalByCondtion(map);

        List<Activity> dataList = activityDao.getActivityListByCondtion(map);

        PaginationVo<Activity> vo = new PaginationVo<Activity>();
        vo.setTotal(total);
        vo.setDataList(dataList);

        return vo;
    }

    public boolean delete(String[] ids) {

        boolean flag = true;

        int count1 = activityRemarkDao.getCountByAids(ids);

        int count2 = activityRemarkDao.deleteByAids(ids);

        if (count1 != count2){

            flag = false;

        }

        int count3 = activityDao.delete(ids);
        if (count3!=ids.length){

            flag = false;

        }

        return flag;
    }

    public Map<String, Object> getManege(String id) {

        List<User> uList = userDao.getUserList();

        Activity a = activityDao.getById(id);

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("uList",uList);
        map.put("a",a);

        return map;
    }

    public boolean update(Activity a) {
        boolean flag = true;

        int count = activityDao.update(a);
        if (count != 1){

            flag = false;

        }

        return flag;
    }

    public Activity detail(String id) {

        Activity a = activityDao.detail(id);

        return a;
    }

    public List<ActivityRemark> getRemarkListByAid(String activityId) {

        List<ActivityRemark> arList = activityRemarkDao.getRemarkListByAid(activityId);

        return arList;
    }

    public boolean deleteRemark(String id) {

        boolean flag = true;

        int count = activityRemarkDao.deleteById(id);
        if (count != 1){

            flag = false;

        }

        return flag;
    }

    public boolean saveRemark(ActivityRemark ar) {

        boolean flag = true;

        int count =activityRemarkDao.saveRemark(ar);
        if (count != 1){

            flag = false;

        }

        return flag;
    }

    public boolean updateRemark(ActivityRemark ar) {

        boolean flag = true;

        int count =activityRemarkDao.updateRemark(ar);
        if (count != 1){

            flag = false;

        }

        return flag;
    }

    @Override
    public List<Activity> getActivityListByClueId(String clueId) {

        List<Activity> aList = activityDao.getActivityListByClueId(clueId);

        return aList;
    }

    @Override
    public List<Activity> getActivityListByNameAndNotByClueId(Map<String, String> map) {

        List<Activity> aList = activityDao.getActivityListByNameAndNotByClueId(map);

        return aList;
    }

    @Override
    public List<Activity> getActivityListByName(String aname) {

        List<Activity> aList = activityDao.getActivityListByName(aname);

        return aList;
    }
}
