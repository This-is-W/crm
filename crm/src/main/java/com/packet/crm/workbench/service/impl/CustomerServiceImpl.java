package com.packet.crm.workbench.service.impl;

import com.packet.crm.utils.SqlSessionUtil;
import com.packet.crm.workbench.dao.CustomerDao;
import com.packet.crm.workbench.service.CustomerService;

import java.util.List;


public class CustomerServiceImpl implements CustomerService {

    private CustomerDao customerDao = SqlSessionUtil.getSqlSession().getMapper(CustomerDao.class);

    public List<String> getCustomerName(String name) {

        List<String> sList = customerDao.getCustomerName(name);

        return sList;
    }
}
















