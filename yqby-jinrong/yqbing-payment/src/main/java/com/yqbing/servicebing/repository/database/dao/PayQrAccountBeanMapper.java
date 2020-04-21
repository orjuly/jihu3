package com.yqbing.servicebing.repository.database.dao;

import com.yqbing.servicebing.repository.database.abstracts.PayQrAccountBeanExample;
import com.yqbing.servicebing.repository.database.bean.PayQrAccountBean;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface PayQrAccountBeanMapper {
    int countByExample(PayQrAccountBeanExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PayQrAccountBean record);

    int insertSelective(PayQrAccountBean record);

    List<PayQrAccountBean> selectByExample(PayQrAccountBeanExample example);

    PayQrAccountBean selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PayQrAccountBean record);

    int updateByPrimaryKey(PayQrAccountBean record);

	PayQrAccountBean selectBywxORaliORphone(@Param("wx")String wx, @Param("ali")String ali, @Param("phone")String phone);

	PayQrAccountBean selectByPid(@Param("pID")String pID);
}