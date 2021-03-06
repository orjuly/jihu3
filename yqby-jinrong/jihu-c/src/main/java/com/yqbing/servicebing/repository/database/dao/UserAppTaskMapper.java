package com.yqbing.servicebing.repository.database.dao;

import com.yqbing.servicebing.repository.database.bean.UserAppTask;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface UserAppTaskMapper {
    int countByExample(UserAppTaskExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserAppTask record);

    int insertSelective(UserAppTask record);

    List<UserAppTask> selectByExample(UserAppTaskExample example);

    UserAppTask selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserAppTask record);

    int updateByPrimaryKey(UserAppTask record);
    
    UserAppTask queryPackAndUserId(@Param("appPack")String appPack, @Param("userId")Long userId);
    
    
	UserAppTask queryPackAndUserIdByStatus(@Param("appPack")String appPack, @Param("userId")Long userId);


}