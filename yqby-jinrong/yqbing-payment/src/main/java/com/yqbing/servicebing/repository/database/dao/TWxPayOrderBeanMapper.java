package com.yqbing.servicebing.repository.database.dao;

import com.yqbing.servicebing.repository.database.abstracts.TWxPayOrderBeanExample;
import com.yqbing.servicebing.repository.database.bean.TWxPayOrderBean;



import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TWxPayOrderBeanMapper {
    int countByExample(TWxPayOrderBeanExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TWxPayOrderBean record);

    int insertSelective(TWxPayOrderBean record);

    List<TWxPayOrderBean> selectByExample(TWxPayOrderBeanExample example);

    TWxPayOrderBean selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TWxPayOrderBean record);

    int updateByPrimaryKey(TWxPayOrderBean record);

	TWxPayOrderBean getOrder(@Param("platOrder")String platOrder,@Param("appChanl")Integer appChanl);

	TWxPayOrderBean getOutOrder(@Param("platOrder")String platOrder);
}