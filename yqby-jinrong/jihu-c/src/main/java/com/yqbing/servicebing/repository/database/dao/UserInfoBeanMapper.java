package com.yqbing.servicebing.repository.database.dao;

import com.yqbing.servicebing.repository.database.bean.UserInfoBean;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoBeanMapper {
    int countByExample(UserInfoBeanExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserInfoBean record);

    int insertSelective(UserInfoBean record);

    List<UserInfoBean> selectByExample(UserInfoBeanExample example);

    UserInfoBean selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserInfoBean record);

    int updateByPrimaryKey(UserInfoBean record);

	UserInfoBean queryAndPhone(@Param("phone")String phone);
	
	UserInfoBean queryPhoneAndpassword(@Param("phone")String phone,@Param("password")String password);

	UserInfoBean queryToken(String token);

	Integer queryDay30(@Param("invitationCode")String invitationCode,@Param("sTime")String sTime,@Param("eTime")String eTime);
}