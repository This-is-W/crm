package com.packet.crm.settings.service.impl;

import com.packet.crm.settings.dao.DicTypeDao;
import com.packet.crm.settings.dao.DicValueDao;
import com.packet.crm.settings.domain.DicType;
import com.packet.crm.settings.domain.DicValue;
import com.packet.crm.settings.service.DicService;
import com.packet.crm.utils.SqlSessionUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DicServiceImpl implements DicService {

    private DicTypeDao dicTypeDao = SqlSessionUtil.getSqlSession().getMapper(DicTypeDao.class);
    private DicValueDao dicValueDao = SqlSessionUtil.getSqlSession().getMapper(DicValueDao.class);


    @Override
    public Map<String, List<DicValue>> getAll() {

        Map<String, List<DicValue>> map = new HashMap<String, List<DicValue>>();

        List<DicType> dtList = dicTypeDao.getTypeList();

        for (DicType dt : dtList){

            String code = dt.getCode();

            List<DicValue> dvList = dicValueDao.getListByCode(code);

            map.put(code+"List",dvList);

        }

        return map;
    }
}
