package com.yqbing.servicebing.repository.database.dao;

import com.yqbing.servicebing.repository.database.abstracts.StoreOrderBeanExample;
import com.yqbing.servicebing.repository.database.bean.StoreOrderBean;

import java.util.List;

public interface StoreOrderBeanMapper {
    int countByExample(StoreOrderBeanExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(StoreOrderBean record);

    int insertSelective(StoreOrderBean record);

    List<StoreOrderBean> selectByExample(StoreOrderBeanExample example);

    StoreOrderBean selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StoreOrderBean record);

    int updateByPrimaryKey(StoreOrderBean record);
}