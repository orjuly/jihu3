package com.yqbing.servicebing.repository.database.dao;

import com.yqbing.servicebing.repository.database.bean.UserLocalApp;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface UserLocalAppMapper {
    int countByExample(UserLocalAppExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserLocalApp record);

    int insertSelective(UserLocalApp record);

    List<UserLocalApp> selectByExample(UserLocalAppExample example);

    UserLocalApp selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserLocalApp record);

    int updateByPrimaryKey(UserLocalApp record);


	UserLocalApp UserLocalApp(@Param("appPack")String appPack, @Param("userId")String userId);

	void deleteByUserId(Long userId);

	List<UserLocalApp> queryThenVersion(@Param("userId")Long userId);
	
	List<UserLocalApp> queryUserId(@Param("userId")Long userId);


}