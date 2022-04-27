package com.packet.crm.workbench.dao;

import com.packet.crm.workbench.domain.Customer;

import java.util.List;

public interface CustomerDao {

    int save(Customer cus);

    Customer getCustomerByName(String company);

    List<String> getCustomerName(String name);
}
