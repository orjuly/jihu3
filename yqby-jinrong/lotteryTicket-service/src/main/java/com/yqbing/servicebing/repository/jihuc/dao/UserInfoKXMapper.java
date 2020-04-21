package com.yqbing.servicebing.repository.jihuc.dao;

import java.util.List;

import com.yqbing.servicebing.repository.jihuc.bean.UserInfoKX;

public interface UserInfoKXMapper {
    int countByExample(UserInfoKXExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserInfoKX record);

    int insertSelective(UserInfoKX record);

    List<UserInfoKX> selectByExample(UserInfoKXExample example);

    UserInfoKX selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserInfoKX record);

    int updateByPrimaryKey(UserInfoKX record);

    UserInfoKX queryAndOpenId(String openId);

	UserInfoKX queryAndPhone(String phone);

	UserInfoKX queryAndAliUserId(String aliUserId);
}