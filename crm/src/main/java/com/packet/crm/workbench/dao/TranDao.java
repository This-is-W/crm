package com.packet.crm.workbench.dao;

import com.packet.crm.workbench.domain.Tran;

import java.util.List;
import java.util.Map;

public interface TranDao {

    int save(Tran t);

    List<Map<String, Object>> getCharts();

    int getTotal();

    int changeStage(Tran t);

    Tran detail(String id);
}
