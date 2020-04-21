package com.yqbing.servicebing.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yqbing.servicebing.common.ErrorCodeEnum;
import com.yqbing.servicebing.repository.database.bean.StoreInfo;
import com.yqbing.servicebing.repository.database.bean.StoreUser;
import com.yqbing.servicebing.repository.database.bean.UserInfoBean;
import com.yqbing.servicebing.repository.database.dao.StoreInfoMapper;
import com.yqbing.servicebing.repository.database.dao.StoreUserMapper;
import com.yqbing.servicebing.repository.database.dao.UserInfoBeanMapper;
import com.yqbing.servicebing.service.CommonService;
import com.yqbing.servicebing.service.LaXinService;
import com.yqbing.servicebing.utils.NotifyUtil;


@Service("laXinService")
@SuppressWarnings("all")
public class LaXinServiceImpl  extends CommonService implements LaXinService{
	
	@Resource
	private UserInfoBeanMapper userInfoBeanMapper = null;
	@Resource
	private StoreInfoMapper  storeInfoMapper;
	@Resource
	private StoreUserMapper  storeUserMapper;
	@Override
	public String queryStore(Long storeId) {
		
		 StoreInfo infoBean = storeInfoMapper.selectByPrimaryKey(storeId);
		 
		 return NotifyUtil.success(infoBean);
	
	}

	@Override
	public String queryUserId(Long userId) {
		
		UserInfoBean infoBean = userInfoBeanMapper.selectByPrimaryKey(userId);
		
		if(infoBean == null){
			return NotifyUtil.error(ErrorCodeEnum.NOLOGIN, "用户不存在");
			
		}
		
		StoreUser su = storeUserMapper.selectByUserId(infoBean.getId());
		if(su != null){
			
			infoBean.setStoreId(su.getStoreId());
			infoBean.setFirstStoreId(su.getStatus().intValue());
		}
		
		return NotifyUtil.success(infoBean);
	}

	@Override
	public String queryToken(String token) {
		
		UserInfoBean infoBean = queryTokenByUser(token);
		
		if(infoBean == null){
			return NotifyUtil.error(ErrorCodeEnum.NOLOGIN, "用户不存在");
			
		}
		
		StoreUser su = storeUserMapper.selectByUserId(infoBean.getId());
		if(su != null){
			
			infoBean.setStoreId(su.getStoreId());
			infoBean.setFirstStoreId(su.getStatus().intValue());
		}
		if(infoBean == null){
			return NotifyUtil.error(ErrorCodeEnum.NOLOGIN, "用户不存在");
			
		} 
		return NotifyUtil.success(infoBean);
	}

}
