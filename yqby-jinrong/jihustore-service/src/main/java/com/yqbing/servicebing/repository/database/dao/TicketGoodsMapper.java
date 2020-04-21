package com.yqbing.servicebing.repository.database.dao;

import com.yqbing.servicebing.repository.database.bean.TicketGoods;
import com.yqbing.servicebing.repository.database.bean.TicketGoodsWithBLOBs;
import java.util.List;

public interface TicketGoodsMapper {
    int countByExample(TicketGoodsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TicketGoodsWithBLOBs record);

    int insertSelective(TicketGoodsWithBLOBs record);

    List<TicketGoodsWithBLOBs> selectByExampleWithBLOBs(TicketGoodsExample example);

    List<TicketGoods> selectByExample(TicketGoodsExample example);

    TicketGoodsWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TicketGoodsWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(TicketGoodsWithBLOBs record);

    int updateByPrimaryKey(TicketGoods record);
}