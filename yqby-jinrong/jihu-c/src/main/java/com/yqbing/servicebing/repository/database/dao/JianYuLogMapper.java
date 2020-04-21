package com.yqbing.servicebing.repository.database.dao;

import com.yqbing.servicebing.repository.database.bean.JianYuLog;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JianYuLogMapper {
    int countByExample(JianYuLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(JianYuLog record);

    int insertSelective(JianYuLog record);

    List<JianYuLog> selectByExample(JianYuLogExample example);

    JianYuLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JianYuLog record);

    int updateByPrimaryKey(JianYuLog record);

    JianYuLog selectByUserIdAndAppPack(@Param("userId")Long userId, @Param("appPack")String appPack);
}