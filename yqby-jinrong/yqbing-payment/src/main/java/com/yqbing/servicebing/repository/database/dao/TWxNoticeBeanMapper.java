package com.yqbing.servicebing.repository.database.dao;

import com.yqbing.servicebing.repository.database.abstracts.TWxNoticeBeanExample;
import com.yqbing.servicebing.repository.database.bean.TWxNoticeBean;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface TWxNoticeBeanMapper {
    int countByExample(TWxNoticeBeanExample example);

    int deleteByPrimaryKey(Long noticeId);

    int insert(TWxNoticeBean record);

    int insertSelective(TWxNoticeBean record);

    List<TWxNoticeBean> selectByExample(TWxNoticeBeanExample example);

    TWxNoticeBean selectByPrimaryKey(Long noticeId);

    int updateByPrimaryKeySelective(TWxNoticeBean record);

    int updateByPrimaryKey(TWxNoticeBean record);

	TWxNoticeBean selectByoutTradeNo(@Param("outTradeNo")String outTradeNo, @Param("appChanl")Integer appChanl);
}