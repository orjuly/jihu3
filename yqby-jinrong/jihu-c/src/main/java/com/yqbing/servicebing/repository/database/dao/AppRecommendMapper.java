package com.yqbing.servicebing.repository.database.dao;

import com.yqbing.servicebing.repository.database.bean.AppRecommend;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface AppRecommendMapper {
    int countByExample(AppRecommendExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AppRecommend record);

    int insertSelective(AppRecommend record);

    List<AppRecommend> selectByExample(AppRecommendExample example);

    AppRecommend selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppRecommend record);

    int updateByPrimaryKey(AppRecommend record);
    
    /**
     * 
    
     * @param size 
     * @param page 
     * @param long1 
     * @Title: queryTagId
    
     * @Description: TODO
    
     * @param fldkey
     * @return
    
     * @return: List<AppRecommend>
     */
	List<AppRecommend> queryTagId(@Param("tagId")String tagId, @Param("userId")Long userId,@Param("page") int page, @Param("size")int size);
	
	/**
	 * 
	
	 * @Title: queryTagRankId
	
	 * @Description: TODO
	
	 * @param tagId
	 * @param userId
	 * @param page
	 * @param size
	 * @return
	
	 * @return: List<AppRecommend>
	 */
	List<AppRecommend> queryTagRankId(@Param("tagId")String tagId, @Param("userId")Long userId,@Param("page") int page, @Param("size")int size);
   
}