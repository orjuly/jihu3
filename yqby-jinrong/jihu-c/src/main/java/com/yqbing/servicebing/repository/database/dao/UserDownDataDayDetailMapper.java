package com.yqbing.servicebing.repository.database.dao;

import com.yqbing.servicebing.repository.database.bean.UserDownDataDayDetail;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface UserDownDataDayDetailMapper {
    int countByExample(UserDownDataDayDetailExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserDownDataDayDetail record);

    int insertSelective(UserDownDataDayDetail record);

    List<UserDownDataDayDetail> selectByExample(UserDownDataDayDetailExample example);

    UserDownDataDayDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserDownDataDayDetail record);

    int updateByPrimaryKey(UserDownDataDayDetail record);

	List<UserDownDataDayDetail> queryInvite(@Param("code")String[] code, @Param("stime")String stime,
			@Param("etime")String etime, @Param("page")Integer page, @Param("size")Integer size);
}