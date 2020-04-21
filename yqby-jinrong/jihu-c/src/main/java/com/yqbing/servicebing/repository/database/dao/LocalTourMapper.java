package com.yqbing.servicebing.repository.database.dao;

import com.yqbing.servicebing.repository.database.bean.LocalTour;
import com.yqbing.servicebing.repository.database.bean.LocalTourWithBLOBs;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface LocalTourMapper {
    int countByExample(LocalTourExample example);

    int deleteByPrimaryKey(Long id);

    int insert(LocalTourWithBLOBs record);

    int insertSelective(LocalTourWithBLOBs record);

    List<LocalTourWithBLOBs> selectByExampleWithBLOBs(LocalTourExample example);

    List<LocalTour> selectByExample(LocalTourExample example);

    LocalTour selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LocalTourWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(LocalTourWithBLOBs record);

    int updateByPrimaryKey(LocalTour record);
    
    List<LocalTour> queryLocalNearCity(@Param("belongCode")String belongCode, @Param("id")Long id);
}